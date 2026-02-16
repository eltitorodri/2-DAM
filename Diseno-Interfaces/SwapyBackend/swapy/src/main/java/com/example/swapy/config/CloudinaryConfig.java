package com.example.swapy.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryConfig {

    private final Cloudinary cloudinary;

    public CloudinaryConfig() {
        // REEMPLAZA CON TUS CREDENCIALES DE CLOUDINARY
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "TU_CLOUD_NAME",
                "api_key", "TU_API_KEY",
                "api_secret", "TU_API_SECRET"
        ));
    }

    public String subirImagen(MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return (String) uploadResult.get("url"); // Obtenemos la URL pública
        } catch (IOException e) {
            throw new RuntimeException("Error al subir imagen a Cloudinary", e);
        }
    }
}