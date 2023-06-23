package com.fortlom.multimedia.application.service;

import com.fortlom.multimedia.application.exception.ResourceNotFoundException;
import com.fortlom.multimedia.application.exception.ResourcePerzonalized;
import com.fortlom.multimedia.domain.imageAgreegate.entity.Image;
import com.fortlom.multimedia.domain.imageAgreegate.persistence.ImageRepository;
import com.fortlom.multimedia.domain.imageAgreegate.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class ImageServiceImpl implements ImageService {


    private static final String ENTITY = "Image";
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public List<Image> getAll() {
        return imageRepository.findAll();
    }

    @Override
    public Page<Image> getAll(Pageable pageable) {
        return imageRepository.findAll(pageable);
    }

    @Override
    public Image getById(Long ImageID) {
        return imageRepository.findById(ImageID)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, ImageID));
    }

    @Override
    public Image createForUser(Long userId, Image request) {
        boolean check1 = restTemplate.getForObject("http://localhost:8081/api/v1/user-service/artists/check/" + userId,boolean.class);
        boolean check2 = restTemplate.getForObject("http://localhost:8081/api/v1/user-service/fanatics/check/" + userId,boolean.class);
        if(check1||check2) {
            request.setUserId(userId);
            return imageRepository.save(request);
        } else {
            throw new ResourcePerzonalized("id inexistente");
        }
    }

    @Override
    public Image createForPublication(Long publicationId, Image request) {
        boolean check = restTemplate.getForObject("http://localhost:8082/api/v1/content-service/publications/check/" + publicationId,boolean.class);
       if(check){
           request.setPublicationId(publicationId);
           return imageRepository.save(request);
       }else{
           throw  new ResourcePerzonalized("id inexistente");
       }
    }

    @Override
    public List<Image> getImageByUserId(Long userId) {
        return imageRepository.findByUserId(userId);
    }

    @Override
    public List<Image> getImageByPublicationId(Long PublicationId) {
        return imageRepository.findByPublicationId(PublicationId);
    }

    @Override
    public ResponseEntity<?> delete(Long PublicationId) {
        return imageRepository.findById(PublicationId).map(Publication -> {
            imageRepository.delete(Publication);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, PublicationId));
    }

    @Override
    public boolean exists(Long id) {
        return imageRepository.existsById(id);
    }
}
