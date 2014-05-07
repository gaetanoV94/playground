package ch.rasc.mongodb.author;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.apache.tika.io.IOUtils;
import org.apache.tika.parser.ParsingReader;
import org.slf4j.LoggerFactory;

@Named
public class TextExtractor {

	public List<String> extractWords(File file) {
		String text = extractText(file);
		List<String> words = new ArrayList<>();

		if (text != null) {
			for (String word : text.split("\\s")) {
				word = word.replaceAll(";|:|,|\\.", "");
				if (!word.trim().isEmpty()) {
					words.add(word);
				}
			}
		}

		return words;
	}

	private String extractText(File file) {
		try (Reader reader = new ParsingReader(file)) {
			String text = IOUtils.toString(reader);
			return text;
		} catch (FileNotFoundException e) {
			LoggerFactory.getLogger(getClass()).error("extractText", e);
		} catch (IOException e) {
			LoggerFactory.getLogger(getClass()).error("extractText", e);
		}

		return null;
	}
}
