package com.fortlom.multimedia.interfaces.mapping.entity;
import com.fortlom.multimedia.domain.imageAgreegate.entity.Image;
import com.fortlom.multimedia.interfaces.dto.Image.ImageResource;
import com.fortlom.multimedia.interfaces.mapping.configuration.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
public class ImageMapper {

    @Autowired
    EnhancedModelMapper mapper;



    public ImageResource toResource(Image model) {
        return mapper.map(model, ImageResource.class);
    }

    public Page<ImageResource> modelListToPage(List<Image> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, ImageResource.class), pageable, modelList.size());
    }





}
