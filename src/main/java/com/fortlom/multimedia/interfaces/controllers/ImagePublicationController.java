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
import java.util.Map;;
@RestController
@CrossOrigin
@RequestMapping("/api/v1/multimedia-service")
public class ImagePublicationController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ImageMapper mapper;


    @PostMapping("/upload/publications/{publicationsId}/images")
    public ResponseEntity<ImageResource> createImageForPublication(@PathVariable Long publicationsId, @RequestParam MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return new ResponseEntity(new Message("image not valid"), HttpStatus.BAD_REQUEST);
        }

        Map result = cloudinaryService.upload(multipartFile);
        Image image=new Image();
        image.setImageUrl((String)result.get("url"));
        image.setImageId((String)result.get("public_id"));

        return ResponseEntity.ok( mapper.toResource(imageService.createForPublication(publicationsId,image)));
    }
    @GetMapping("/publications/{publicationsId}/images")
    public ResponseEntity<Page<ImageResource>> getImageByPublicationId(@PathVariable Long publicationsId, Pageable pageable) {

        return ResponseEntity.ok(mapper.modelListToPage(imageService.getImageByPublicationId(publicationsId), pageable));
    }
}
