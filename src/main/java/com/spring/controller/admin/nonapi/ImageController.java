package com.spring.controller.admin.nonapi;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.model.FileImage;
import com.spring.service.IFileService;

@Controller
public class ImageController {
	@Autowired
	private IFileService fileService;

	private static String FOLDER_ROOT_IMAGES = "/resources/admin/images";

	@RequestMapping(value = { "/admin-page/gallary" }, method = RequestMethod.GET)
	public String getGallaryPage(HttpServletRequest request, Model model,
			@RequestParam(value = "path", required = false) String path) {
		if (path == null)
			path = "";
		List<FileImage> filesImage = fileService.getAllFileImagesByPath(request.getServletContext().getRealPath(FOLDER_ROOT_IMAGES + path), path);
		model.addAttribute("ListFile", filesImage);
		String [] pathsplit = path.split("/");
		if(pathsplit.length >= 2) {
			model.addAttribute("prePath", path.replace("/" + pathsplit[pathsplit.length - 1], ""));
			String urlPath = "";
			List<FileImage> listUrlFolder = new ArrayList<FileImage>();
			for(int i = 0; i < pathsplit.length - 1; i++) {
				urlPath += "/" + pathsplit[i + 1];
				listUrlFolder.add(new FileImage(0, pathsplit[i + 1], urlPath, true));
			}
			model.addAttribute("listUrlFolder", listUrlFolder);
		}
		
		model.addAttribute("nowPath", path);
		String scriptActive = "<script type='text/javascript'>$('#gallary-image').addClass('active');</script>";
		model.addAttribute("ScriptFormBackEnd", scriptActive);
		return "admin/image-gallery";
	}
	
	static boolean isPowerOfFour(long n) {
		if(n == 1 || n == 4) return true;
		if(n < 4)  return false;
		return isPowerOfFour(n / 4);
}

	public static void main(String[] args) {
		long n = 5L;
		boolean c = true;
		while(n != 1) {
			if(n % 4 != 0) {
				c = false;
				break;
			}
			n = n / 4;
		}
		
		System.out.println(isPowerOfFour(n));
	}

}
