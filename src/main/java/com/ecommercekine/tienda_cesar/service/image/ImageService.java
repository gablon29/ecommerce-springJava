package com.ecommercekine.tienda_cesar.service.image;

import com.ecommercekine.tienda_cesar.exceptions.ResourceNotFoundException;
import com.ecommercekine.tienda_cesar.model.Image;
import com.ecommercekine.tienda_cesar.repository.ImageRepository;
import com.ecommercekine.tienda_cesar.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.spi.SqlScriptException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;


@Service
@RequiredArgsConstructor
public class ImageService  implements  IImageService{

    private final ImageRepository imageRepository;
    private final IProductService productService;

    @Override
    public Image saveImage(MultipartFile file, Long productId) {
        return null;
    }

    @Override
    public void updateImage(MultipartFile file, Long id) {
        Image imagen = getImage(id);
        try {
            imagen.setFileName(file.getOriginalFilename());
            imagen.setFileType(file.getContentType());
            imagen.setData(new SerialBlob(file.getBytes()));
            imageRepository.save(imagen);
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Error al guardar la imagen" + id);
        }
    }

    @Override
    public Image getImage(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image not found"));
    }

    @Override
    public void deleteImage(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete , () -> {
            throw new ResourceNotFoundException("Image not found" + id);
        });
    }
}
