package com.ecommercekine.tienda_cesar.service.image;

import com.ecommercekine.tienda_cesar.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
    Image saveImage(MultipartFile file, Long productId );
    void updateImage(MultipartFile file, Long id);
    Image getImage(Long id);
    void deleteImage(Long id);

}
