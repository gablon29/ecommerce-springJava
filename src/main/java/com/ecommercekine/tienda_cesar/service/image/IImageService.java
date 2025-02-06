package com.ecommercekine.tienda_cesar.service.image;

import com.ecommercekine.tienda_cesar.dto.ImageDto;
import com.ecommercekine.tienda_cesar.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    List<ImageDto> saveImages(List<MultipartFile> files, Long productId );
    void updateImage(MultipartFile file, Long id);
    Image getImage(Long id);
    void deleteImage(Long id);

}
