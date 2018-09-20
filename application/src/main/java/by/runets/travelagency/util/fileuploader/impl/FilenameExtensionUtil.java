package by.runets.travelagency.util.fileuploader.impl;

import by.runets.travelagency.util.fileuploader.IFileNameExtensionUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component
public class FilenameExtensionUtil implements IFileNameExtensionUtil {
  @Override
  public String reformat(MultipartFile file) {
	String[] fileNameExtension = file.getOriginalFilename().split("\\.");
	return fileNameExtension[0] + "-" + UUID.randomUUID() + "." + fileNameExtension[fileNameExtension.length - 1];
  }
}
