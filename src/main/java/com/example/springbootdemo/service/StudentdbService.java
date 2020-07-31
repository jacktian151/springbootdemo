package com.example.springbootdemo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface StudentdbService {
    void insertdb(InputStream in, MultipartFile file) throws Exception;
}
