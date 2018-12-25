package by.runets.travelagency.util.fileuploader;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class FileExtension {
  private FileExtension() {
    //hidden constructor
  }

  public static String reformat(MultipartFile file) {
	String[] fileNameExtension = file.getOriginalFilename().split("\\.");
	return fileNameExtension[0] + "-" + UUID.randomUUID() + "." + fileNameExtension[fileNameExtension.length - 1];
  }
}
