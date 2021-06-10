package com.spring.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HandleErrorController implements ErrorController {
  @RequestMapping("/errors")
  public String handleError(HttpServletRequest request) {
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    if (status != null) {
      Integer statusCode = Integer.valueOf(status.toString());
      if (statusCode == HttpStatus.NOT_FOUND.value()) {
        return "404-page";
      } 
//      else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//        return "500-page";
//      }
    }
    return "error";
  }

public String getErrorPath() {
	// TODO Auto-generated method stub
	return null;
}
}