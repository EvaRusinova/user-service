package com.example.initial.repository;

import com.example.initial.entity.Photo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PhotoRepository extends JpaRepository<Photo, Long> {

  Optional<Photo> findByName(String name);
}
