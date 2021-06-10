package com.spring.utils;

import java.text.Normalizer;
import java.util.Collection;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class WebUtils {
	public static String VIETNAMESE_DIACRITIC_CHARACTERS = "ắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴ";
	
	public static String DELIMETER = "1408dt2410";
	
	public static String toString(User user) {
        StringBuilder sb = new StringBuilder();
 
        sb.append("UserName:").append(user.getUsername());
 
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        if (authorities != null && !authorities.isEmpty()) {
            sb.append(" (");
            boolean first = true;
            for (GrantedAuthority a : authorities) {
                if (first) {
                    sb.append(a.getAuthority());
                    first = false;
                } else {
                    sb.append(", ").append(a.getAuthority());
                }
            }
            sb.append(")");
        }
        return sb.toString();
    }
	public static String getMessageWhenErrorEntity(String str) {
		return str.substring(str.indexOf("interpolatedMessage='") + 21, str.indexOf("',"));
	}
	
	public static String formatAlias(String str) {
	    try {
	        String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
	        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	        return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("đ", "d");
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return "";
	}
	
	public static String [] getAllCodeColor() {
		String[] res = {"#f44336", "#03a9f4", "#009688", "#cddc39", "#ffc107", "#795548", "#673ab7", "#8bc34a", "#607d8b"};
		return res;
	}
	
	@SuppressWarnings("resource")
	public static boolean sendEmail(String sendTo, String title, String message) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/emailContext.xml");
	    JavaMailSender mailSender =  (JavaMailSender) context.getBean("mailSender2");
	   
	    MimeMessage mimeMessage = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
	    
	    try {
	    	helper.setText(message, true);
		    helper.setTo(sendTo);
			helper.setSubject(title);
			helper.setFrom("electroshop2410@gmail.com");
		} catch (MessagingException e) {
			return false;
		}
	   
	    mailSender.send(mimeMessage);
	    context.close();
		return true;
	}
}
