package com.example.Service.cloudinary;

import java.io.IOException;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

@Service
public class CloudinaryService{
	
	@Autowired
	private Cloudinary cloudinary;
	
	
	public Map uploadImage(MultipartFile file) {
		try {
			Map data=this.cloudinary.uploader().upload(file.getBytes(), Map.of());
			return data;
		} catch (IOException e) {
			
			throw new RuntimeException("Image upload error");
		}
	}
	
}