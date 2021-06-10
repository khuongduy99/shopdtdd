package com.spring.controller.admin.api;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.spring.model.FileImage;
import com.spring.model.MessageAlertModel;
import com.spring.service.IFileService;

@RestController
public class FileAPI {
	@Autowired
	private IFileService fileService;
	
	private static String FOLDER_ROOT_IMAGES = "/resources/admin/images";

	@RequestMapping(value = "/api/uploadImages", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public MessageAlertModel upload(HttpServletRequest request, @RequestParam("file") CommonsMultipartFile[] fileDatas,
			@RequestParam(value = "nowPath", required = false) String nowPath) {	
		byte[] bytes = nowPath.getBytes(StandardCharsets.ISO_8859_1);
		nowPath = new String(bytes, StandardCharsets.UTF_8);
		return fileService.uploadImages(request.getServletContext().getRealPath(FOLDER_ROOT_IMAGES + File.separator + nowPath), fileDatas);
	}
	
	@PostMapping(value = "/api/folder")
	public MessageAlertModel createFolder(HttpServletRequest request, @RequestBody Folder folder) {	
		return fileService.createFolder(request.getServletContext().getRealPath(FOLDER_ROOT_IMAGES + File.separator +  folder.getNameFolder()));
	}
	
	@DeleteMapping(value = "/api/folder")
	public MessageAlertModel deleteFolder(HttpServletRequest request, @RequestBody Folder folder) {	
		for(int i = 0; i < folder.getPathToDelete().length; i++) {
			folder.getPathToDelete()[i] = request.getServletContext().getRealPath(FOLDER_ROOT_IMAGES + File.separator +  folder.getPathToDelete()[i]);
		}
		return fileService.deleteFile(folder.getPathToDelete());
	}
	
	@GetMapping(value = "/api/images")
    @ResponseBody
    public List<FileImage> getAllImagesByPath(HttpServletRequest request, @RequestParam(value = "path", required = false) String path) {
		if(path==null) path="";
        return fileService.getAllFileImagesByPath(request.getServletContext().getRealPath(FOLDER_ROOT_IMAGES + File.separator +  path), path);
    }
	
}

class Folder {
	private String nameFolder;
	private String [] pathToDelete;

	public String getNameFolder() {
		return nameFolder;
	}

	public void setNameFolder(String nameFolder) {
		this.nameFolder = nameFolder;
	}

	public String[] getPathToDelete() {
		return pathToDelete;
	}

	public void setPathToDelete(String[] pathToDelete) {
		this.pathToDelete = pathToDelete;
	}
	
	
	
	
}