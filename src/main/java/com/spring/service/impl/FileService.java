package com.spring.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.spring.model.FileImage;
import com.spring.model.MessageAlertModel;
import com.spring.service.IFileService;

@Service
public class FileService implements IFileService {
	private static String ROOT_IMAGES = "/admin/images";
	@Override
	public MessageAlertModel uploadImages(String path, CommonsMultipartFile[] fileDatas) {
		// Thư mục gốc upload file.

		File uploadRootDir = new File(path);

		//
		// Tạo thư mục gốc upload nếu nó không tồn tại.
		if (!uploadRootDir.exists()) {
			return new MessageAlertModel("danger", "Lỗi khi upload", new Date());

		}
		for (CommonsMultipartFile fileData : fileDatas) {

			// Tên file gốc tại Client.
			String name = fileData.getOriginalFilename();

			if (name != null && name.length() > 0) {
				try {
					// Tạo file tại Server.
					File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);

					// Luồng ghi dữ liệu vào file trên Server.
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(fileData.getBytes());
					stream.close();
				} catch (Exception e) {
					return new MessageAlertModel("danger", "Lỗi khi upload", new Date());
				}
			}
		}

		return new MessageAlertModel("success", "Đã Upload Xong", new Date());
	}

	@Override
	public List<FileImage> getAllFileImagesByPath(String root, String path) {
		File directoryPath = new File(root);
		if(path == null) path = "";
		List<FileImage> res = new ArrayList<FileImage>();
		if(!directoryPath.exists()) return res;
		File files[] = directoryPath.listFiles();
		

		int id = 0;
		for (File file : files) {
			id++;
			res.add(new FileImage(id, file.getName(), ROOT_IMAGES + "/" + path + "/" + file.getName(), file.isDirectory() ? true : false));
		}
		
		Collections.sort(res, new Comparator<FileImage>() {
	        @Override
	        public int compare(FileImage abc1, FileImage abc2) {
	            return Boolean.compare(abc2.getIsFolder(),abc1.getIsFolder());
	        }
	    });
		return res;
	}

	@Override
	public MessageAlertModel createFolder(String path) {
		File directoryPath = new File(path);
		if(!directoryPath.exists()) {
			directoryPath.mkdirs();
		} else {
			return new MessageAlertModel("danger", "Folder đã tồn tại", new Date());
		}
		return new MessageAlertModel("success", "Folder đã được tạo", new Date());
	}

	@Override
	public MessageAlertModel deleteFile(String[] paths) {
		try {
			for(String path : paths) {
            File file = new File(path);
	            if (file.delete()) {
	                System.out.println(file.getName() + " is deleted!");
	            } else {
	                System.out.println("Delete operation is failed.");
	            }
			}
        } catch (Exception e) {
            return new MessageAlertModel("danger", "Lỗi Khi Xóa", new Date());
        }
		return new MessageAlertModel("success", "Đã Xóa", new Date());
	}

}
