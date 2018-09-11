package by.runets.travelagency.service;

import org.springframework.web.multipart.MultipartFile;

public interface IPhotoService {
	boolean save (MultipartFile file, String path, String fileGroup);
}