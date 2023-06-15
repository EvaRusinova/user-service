package com.example.initial.controller;

import com.example.initial.entity.Photo;
import com.example.initial.entity.Post;
import com.example.initial.entity.User;
import com.example.initial.enums.PhotoFormat;
import com.example.initial.repository.PhotoRepository;
import com.example.initial.repository.PostRepository;
import com.example.initial.service.UserService;
import java.io.IOException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/photos")
@RequiredArgsConstructor
public class PhotoController {
  private final PhotoRepository photoRepository;
  private final UserService userService;
  private final PostRepository postRepository;

  @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public Photo uploadPhoto(
      @RequestParam("file") MultipartFile file,
      @RequestParam(value = "userId", defaultValue = "1") Long userId,
      @RequestParam(value = "postId", defaultValue = "1") Long postId)
      throws IOException {
    User user = userService.findById(userId);
    Post post =
        postRepository
            .findById(postId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));

    String fileName = file.getOriginalFilename();
    String fileExtension = FilenameUtils.getExtension(fileName);

    Photo photo =
        Photo.builder()
            .name(fileName)
            .data(file.getBytes())
            .format(PhotoFormat.valueOf(Objects.requireNonNull(fileExtension).toUpperCase()))
            .user(user)
            .post(post)
            .build();
    return photoRepository.save(photo);
  }

  @GetMapping(value = "/download/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
  public ResponseEntity<byte[]> downloadPhoto(@PathVariable("name") final String name) {
    Photo photo =
        photoRepository
            .findByName(name)
            .orElseThrow(() -> new IllegalArgumentException("Invalid photo name"));
    return ResponseEntity.ok()
        .header("Content-Disposition", "attachment; filename=" + name)
        .body(photo.getData());
  }
}
