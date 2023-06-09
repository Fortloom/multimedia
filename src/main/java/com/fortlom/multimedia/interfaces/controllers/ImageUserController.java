package com.fortlom.multimedia.interfaces.controllers;
import com.fortlom.multimedia.application.exception.Message;
import com.fortlom.multimedia.domain.imageAgreegate.entity.Image;
import com.fortlom.multimedia.domain.imageAgreegate.service.CloudinaryService;
import com.fortlom.multimedia.domain.imageAgreegate.service.ImageService;
import com.fortlom.multimedia.interfaces.dto.Image.ImageResource;
import com.fortlom.multimedia.interfaces.mapping.entity.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;;
@RestController
@CrossOrigin
@RequestMapping("/api/v1/multimediaservice")
public class ImageUserController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ImageMapper mapper;


    @PostMapping("/upload/users/{userId}/images")
    public ResponseEntity<ImageResource> createimageforuser(@PathVariable Long userId, @RequestParam MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return new ResponseEntity(new Message("imagen no válida"), HttpStatus.BAD_REQUEST);
        }

        Map result = cloudinaryService.upload(multipartFile);
        Image image=new Image();
        image.setImagenUrl((String)result.get("url"));
        image.setImagenId( (String)result.get("public_id"));

        return ResponseEntity.ok( mapper.toResource(imageService.createforuser(userId,image)));
    }
    @GetMapping("/users/{userId}/images")
    public ResponseEntity<Page<ImageResource>> getImageByUserId(@PathVariable Long userId, Pageable pageable) {

        return ResponseEntity.ok(mapper.modelListToPage(imageService.getImageByUserId(userId), pageable));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id)throws IOException {
        if(!imageService.exists(id))
            return new ResponseEntity(new Message("no exists"), HttpStatus.NOT_FOUND);
        Image image = imageService.getById(id);
        Map result = cloudinaryService.delete(image.getImagenId());
        imageService.delete(id);
        return new ResponseEntity(new Message("imagen deleted"), HttpStatus.OK);
    }

}
