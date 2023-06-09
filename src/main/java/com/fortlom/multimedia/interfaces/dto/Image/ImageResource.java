package com.fortlom.multimedia.interfaces.dto.Image;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ImageResource {

    private Long id;
    private String imagenUrl;
    private Long userid;
    private String imagenId;
    private Long publicationid;
}
