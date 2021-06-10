package com.spring.service;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.spring.model.FileImage;
import com.spring.model.MessageAlertModel;

public interface IFileService {
	MessageAlertModel uploadImages(String path, CommonsMultipartFile[] fileDatas);
	MessageAlertModel createFolder(String path);
	MessageAlertModel deleteFile(String[] paths);
	List<FileImage> getAllFileImagesByPath(String root, String path);
}
