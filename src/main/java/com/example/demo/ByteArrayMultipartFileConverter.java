package com.example.demo;

import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ByteArrayMultipartFileConverter implements Converter<MultipartFile, byte[]> {

    @Override
    public byte[] convert(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException("Error converting MultipartFile to byte[]", e);
        }
    }
}