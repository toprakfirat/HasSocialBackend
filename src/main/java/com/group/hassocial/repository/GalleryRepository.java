package com.group.hassocial.repository;

import com.group.hassocial.data.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GalleryRepository extends JpaRepository<Photo, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE GALLERY SET IsAvatarImage = ?1 WHERE AvatarImageID  = ?2", nativeQuery = true)
    void updateAvatar(Boolean isAvatarImage, Integer id);
}
