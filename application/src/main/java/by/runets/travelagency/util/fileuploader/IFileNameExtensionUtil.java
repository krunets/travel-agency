package by.runets.travelagency.util.fileuploader;

import org.springframework.web.multipart.MultipartFile;

public interface IFileNameExtensionUtil {
  String reformat(MultipartFile multipartFile);
}
