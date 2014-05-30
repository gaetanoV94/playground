package ch.rasc.upload.web;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileManager {

	private final String dataDirectory;

	public FileManager(String dataDirectory) {
		this.dataDirectory = dataDirectory;
		Path dataDir = Paths.get(dataDirectory);

		try {
			Files.createDirectories(dataDir);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean chunkExists(String identifier, Integer chunkNumber,
			Long chunkSize) throws IOException {
		Path chunkFile = Paths.get(dataDirectory, identifier,
				chunkNumber.toString());
		if (Files.exists(chunkFile)) {
			long size = (Long) Files.getAttribute(chunkFile, "basic:size");
			return size == chunkSize;
		}
		return false;
	}

	public boolean isSupported(
			@SuppressWarnings("unused") String resumableFilename) {
		return true;
	}

	public void storeChunk(String identifier, Integer chunkNumber,
			InputStream inputStream) throws IOException {
		Path chunkFile = Paths.get(dataDirectory, identifier,
				chunkNumber.toString());
		try {
			Files.createDirectories(chunkFile);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		Files.copy(inputStream, chunkFile, StandardCopyOption.REPLACE_EXISTING);
	}

	public boolean allChunksUploaded(String identifier, Long chunkSize,
			Long totalSize) {

		long noOfChunks = totalSize / chunkSize;

		for (int chunkNo = 1; chunkNo <= noOfChunks; chunkNo++) {
			if (!Files.exists(Paths.get(dataDirectory, identifier,
					String.valueOf(chunkNo)))) {
				return false;
			}
		}
		return true;

	}

	public void mergeAndDeleteChunks(String fileName, String identifier,
			Long chunkSize, final Long totalSize) throws IOException {
		long noOfChunks = totalSize / chunkSize;

		Path newFilePath = Paths.get(dataDirectory, fileName);
		if (Files.exists(newFilePath)) {
			Files.delete(newFilePath);
		}

		try (BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(newFilePath.toFile()))) {
			for (int chunkNo = 1; chunkNo <= noOfChunks; chunkNo++) {
				Path chunkPath = Paths.get(dataDirectory, identifier,
						String.valueOf(chunkNo));
				Files.copy(chunkPath, bos);
				Files.delete(chunkPath);
			}
		}

		Files.delete(Paths.get(dataDirectory, identifier));
	}

}
