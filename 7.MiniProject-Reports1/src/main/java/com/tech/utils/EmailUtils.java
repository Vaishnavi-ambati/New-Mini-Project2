package com.tech.utils;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	
	@Autowired
	private JavaMailSender mailSender;

	public boolean sendEmail(File file) {
		boolean status = false;
		
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return status;
	}
}
