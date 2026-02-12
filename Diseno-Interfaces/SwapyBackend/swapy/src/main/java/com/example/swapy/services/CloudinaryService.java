package com.example.swapy.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    public String uploadImage(String base64Image) throws Exception {
        // Cloudinary entiende directamente el Base64 si lleva el prefijo data:image/jpeg;base64,...
        Map uploadResult = cloudinary.uploader().upload(base64Image, ObjectUtils.emptyMap());
        return uploadResult.get("url").toString(); // Esta es la URL que guardarás en tu tabla
    }
}
