package com.fortlom.multimedia.domain.imageAgreegate.service;

import com.fortlom.multimedia.domain.imageAgreegate.entity.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface ImageService {
    List<Image> getAll();
    Page<Image> getAll(Pageable pageable);
    Image getById(Long ForumId);
    Image createForUser(Long userId, Image image);
    Image createForPublication(Long publicationId, Image image);
    List<Image> getImageByUserId(Long userId);
    List<Image> getImageByPublicationId(Long PublicationId);
    ResponseEntity<?> delete(Long PublicationId);
    boolean exists(Long id);
}
