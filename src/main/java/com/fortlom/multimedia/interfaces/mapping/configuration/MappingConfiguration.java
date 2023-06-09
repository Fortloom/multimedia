package com.fortlom.multimedia.interfaces.mapping.configuration;

import com.fortlom.multimedia.interfaces.mapping.entity.ImageMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("enhancedModelMapperConfiguration")
public class MappingConfiguration {

    @Bean
    public EnhancedModelMapper modelMapper() {
        return new EnhancedModelMapper();
    }
    @Bean
    public ImageMapper ImageMapper() {
        return new ImageMapper();
    }
}
