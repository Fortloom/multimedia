package com.fortlom.multimedia.domain.imageAgreegate.persistence;
import com.fortlom.multimedia.domain.imageAgreegate.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ImageRepository  extends JpaRepository<Image,Long>{

    List<Image> findByPublicationId(Long publicationId);

    List<Image> findByUserId(Long UserId);
}
