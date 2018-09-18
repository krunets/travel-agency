package by.runets.travelagency.util.fileuploader.impl;

import by.runets.travelagency.exception.ResourceNotFoundException;
import by.runets.travelagency.util.fileuploader.IFileUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class PhotoUtil implements IFileUtil {
	
	@Override
	public boolean save (MultipartFile file, String path, String newFileName) {
		if (!file.isEmpty()) {
			try {
				File dir = new File(path);
				if (file.getContentType().equalsIgnoreCase("image/jpeg") || file.getContentType().equalsIgnoreCase("image/png")) {
					File serverFile = new File(dir.getAbsolutePath() + File.separator + newFileName);
					file.transferTo(serverFile);
				}
			} catch (IOException e) {
				throw new ResourceNotFoundException();
			}
		}
		return false;
	}
}
