/**
 * Licensed to Neo Technology under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Neo Technology licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package ch.rasc.playground.orientdb;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

import com.orientechnologies.orient.core.intent.OIntentMassiveInsert;
import com.orientechnologies.orient.core.metadata.schema.OClass.INDEX_TYPE;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientEdgeType;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;

public class ImdbParser {

	public static void main(String[] args) throws IOException {
		System.setProperty("storage.useWAL", "false");
		System.setProperty("storage.wal.syncOnPageFlush", "false");

		OrientGraphFactory factory = new OrientGraphFactory(
				"plocal:/E:/temp/databases/moviedb");

		// new OServerAdmin("remote:localhost").connect("root", "root")
		// .createDatabase("moviedb", "graph", "plocal").close();
		//
		// OrientGraphFactory factory = new OrientGraphFactory("remote:localhost/moviedb",
		// "admin", "admin");

		long start = System.currentTimeMillis();
		OrientGraphNoTx graphDb = factory.getNoTx();
		graphDb.declareIntent(new OIntentMassiveInsert());

		OrientVertexType movieVertexType = graphDb.createVertexType("Movie");
		movieVertexType.createProperty("title", OType.STRING);
		movieVertexType.createIndex("Movie.title", INDEX_TYPE.UNIQUE, "title");

		OrientVertexType actorVertexType = graphDb.createVertexType("Actor");
		actorVertexType.createProperty("actor", OType.STRING);
		actorVertexType.createIndex("Actor.actor", INDEX_TYPE.UNIQUE, "actor");

		OrientEdgeType actsInEdgeType = graphDb.createEdgeType("ActsIn");
		actsInEdgeType.createProperty("character", OType.STRING);

		try {
			new ImdbParser().readImdbData(graphDb, "E:\\temp\\actresses.list.gz");

			new ImdbParser().readImdbData(graphDb, "E:\\temp\\actors.list.gz");
		}
		finally {
			graphDb.shutdown();
		}

		System.out.println((System.currentTimeMillis() - start) / 1000 + " seconds");
	}

	public void readImdbData(OrientGraphNoTx graphDb, String fileName)
			throws FileNotFoundException, IOException {

		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				new GZIPInputStream(new FileInputStream(fileName)),
				StandardCharsets.ISO_8859_1))) {

			String line = br.readLine();
			Vertex currentActorVertex = null;

			while (line != null) {

				if ("".equals(line.trim())) {
					line = br.readLine();
					continue;
				}

				int actorSep = line.indexOf('\t');
				if (actorSep >= 0) {
					String actor = line.substring(0, actorSep).trim();
					if (!"".equals(actor)) {
						currentActorVertex = graphDb.getVertexByKey("Actor.actor", actor);
						if (currentActorVertex == null) {
							currentActorVertex = graphDb.addVertex("class:Actor",
									"actor", actor);
						}
					}

					String title = line.substring(actorSep).trim();
					if (title.length() == 0 || title.contains("{")
							|| title.startsWith("\"") || title.contains("????")
							|| title.contains(" (TV)") || title.contains(" (VG)")
							|| title.contains(" (V)")) {
						line = br.readLine();
						continue;
					}
					int characterStart = title.indexOf('[');
					int characterEnd = title.indexOf(']');
					String character = null;
					if (characterStart > 0 && characterEnd > characterStart) {
						character = title.substring(characterStart + 1, characterEnd);
					}
					int creditStart = title.indexOf('<');
					if (characterStart > 0) {
						title = title.substring(0, characterStart).trim();
					}
					else if (creditStart > 0) {
						title = title.substring(0, creditStart).trim();
					}
					int spaces = title.indexOf("  ");
					if (spaces > 0) {
						if (title.charAt(spaces - 1) == ')'
								&& title.charAt(spaces + 2) == '(') {
							title = title.substring(0, spaces).trim();
						}
					}
					if (character != null && currentActorVertex != null) {
						Vertex movieVertex = graphDb.getVertexByKey("Movie.title", title);
						if (movieVertex == null) {
							// Iterable<Vertex> movies =
							// graphDb.getVertices("Movie.title",
							// title);
							// Vertex movieVertex;
							// if (movies.iterator().hasNext()) {
							// movieVertex = movies.iterator().next();
							// }
							// else {
							// movieVertex = graphDb.addVertex("class:Movie");
							// movieVertex.setProperty("title", title);

							movieVertex = graphDb
									.addVertex("class:Movie", "title", title);
						}

						Edge edge = graphDb.addEdge("class:ActsIn", currentActorVertex,
								movieVertex, null);
						edge.setProperty("character", character);

					}

				}
				line = br.readLine();
			}

		}
	}

}
