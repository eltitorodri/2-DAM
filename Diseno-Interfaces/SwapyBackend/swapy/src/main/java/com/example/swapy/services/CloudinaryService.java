package com.example.swapy.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    // CONSTRUCTOR: Aquí es donde Spring lee tus credenciales del archivo properties
    public CloudinaryService(
            @Value("${cloudinary.cloud_name}") String cloudName,
            @Value("${cloudinary.api_key}") String apiKey,
            @Value("${cloudinary.api_secret}") String apiSecret) {

        // Inicializamos Cloudinary con los valores reales
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret,
                "secure", true // Esto asegura que la URL sea HTTPS
        ));
    }

    public String subirImagen(MultipartFile file) {
        try {
            // Subimos el archivo a Cloudinary
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

            // Devolvemos la URL segura (https) para que se vea bien en Android
            return (String) uploadResult.get("secure_url");

        } catch (IOException e) {
            e.printStackTrace(); // Para ver el error en consola si falla
            throw new RuntimeException("Error al subir imagen a Cloudinary", e);
        }
    }
}