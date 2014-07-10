package ch.rasc.mongodb.tail;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.Bytes;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class Reader {

	public static void main(String[] args) throws UnknownHostException, MongoException {
		MongoClient mongo = new MongoClient("localhost");

		DB db = mongo.getDB("testdb");

		DBCollection collection;
		if (!db.collectionExists("log")) {
			BasicDBObject createOptions = new BasicDBObject();
			createOptions.append("capped", true);
			createOptions.append("size", 100000);
			collection = db.createCollection("log", createOptions);
		}
		else {
			collection = db.getCollection("log");
		}

		try (DBCursor cur = collection.find().addOption(Bytes.QUERYOPTION_TAILABLE)
				.addOption(Bytes.QUERYOPTION_AWAITDATA)) {

			while (cur.hasNext()) {
				System.out.println(cur.next());
			}
		}
		System.out.println("END");
	}

}
