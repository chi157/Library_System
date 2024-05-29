package com.example.demo;

import org.apache.tomcat.util.codec.binary.Base64;

public class ImageUtils {
    public static String getBase64Image(byte[] imageBytes) {
        return "data:image/jpeg;base64," + Base64.encodeBase64String(imageBytes);
    }
}