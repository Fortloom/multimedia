package com.fortlom.multimedia.application.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fortlom.multimedia.domain.imageAgreegate.service.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;




import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    Cloudinary cloudinary;

    private Map<String, String> valuesMap = new HashMap<>();

    public CloudinaryServiceImpl() {
        valuesMap.put("cloud_name", "doi25qlq7");
        valuesMap.put("api_key", "727234657482974");
        valuesMap.put("api_secret", "SgSricW1F_2Ja9Bd_ZT8aDQ4AMc");
        cloudinary = new Cloudinary(valuesMap);
    }
    @Override
    public Map upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        file.delete();
        return result;
    }

    @Override
    public Map delete(String id) throws IOException {
        Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
        return result;
    }

    @Override
    public File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
