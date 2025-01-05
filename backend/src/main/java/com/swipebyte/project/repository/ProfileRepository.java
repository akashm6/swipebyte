package com.swipebyte.project.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import com.swipebyte.project.entity.*;

@Repository
public interface ProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findById(Long id);

    @Query("UPDATE UserProfile u SET u.bio = ?2 WHERE u.id = ?1")
    void updateBio(Long id, String newBio);

}
