package com.fortlom.multimedia.domain.imageAgreegate.service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;
public interface CloudinaryService {

    Map upload(MultipartFile multipartFile) throws IOException;
    Map delete(String id) throws IOException;
    File convert(MultipartFile multipartFile) throws IOException;
}
