package com.fortlom.multimedia.interfaces.dto.Image;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageResource {

    private Long id;
    private String imageUrl;
    private Long userid;
    private String imageId;
    private Long publicationId;
}
