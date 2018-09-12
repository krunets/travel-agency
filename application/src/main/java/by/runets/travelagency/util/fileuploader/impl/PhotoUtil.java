package by.runets.travelagency.util.fileuploader.impl;

import by.runets.travelagency.exception.ResourceNotFoundException;
import by.runets.travelagency.util.fileuploader.IFileUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class PhotoUtil implements IFileUtil {
	
	@Override
	public boolean save (MultipartFile file, String path, String newFileName) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				File dir = new File(path);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				if (file.getContentType().equalsIgnoreCase("image/jpeg") || file.getContentType().equalsIgnoreCase("image/png")) {
					File serverFile = new File(dir.getAbsolutePath() + File.separator + newFileName);
					file.transferTo(serverFile);
				/*	BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();*/
				}
			} catch (IOException e) {
				throw new ResourceNotFoundException();
			}
		}
		return false;
	}
}
