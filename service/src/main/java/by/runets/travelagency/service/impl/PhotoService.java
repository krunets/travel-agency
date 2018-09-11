package by.runets.travelagency.service.impl;

import by.runets.travelagency.exception.ResourceNotFoundException;
import by.runets.travelagency.service.IPhotoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class PhotoService implements IPhotoService {
	@Override
	public boolean save (MultipartFile file, String path, String fileGroup) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				File dir = new File(path + File.separator + fileGroup);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getName());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
			} catch (IOException e) {
				throw new ResourceNotFoundException();
			}
		}
		return false;
	}
}
