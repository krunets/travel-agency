package by.runets.travelagency.util.fileuploader;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUtil {
	boolean save (MultipartFile file, String path, String newFileName);
}