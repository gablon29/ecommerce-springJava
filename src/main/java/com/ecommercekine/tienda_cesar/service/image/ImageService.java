package com.ecommercekine.tienda_cesar.service.image;

import com.ecommercekine.tienda_cesar.dto.ImageDto;
import com.ecommercekine.tienda_cesar.exceptions.ResourceNotFoundException;
import com.ecommercekine.tienda_cesar.model.Image;
import com.ecommercekine.tienda_cesar.model.Product;
import com.ecommercekine.tienda_cesar.repository.ImageRepository;
import com.ecommercekine.tienda_cesar.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ImageService  implements  IImageService{

    private final ImageRepository imageRepository;
    private final IProductService productService;

    @Override
    public List<ImageDto> saveImages(List<MultipartFile> files, Long productId) {
        Product product = productService.findById(productId);
        List<ImageDto> saveImageDto = new ArrayList<>();
        for(MultipartFile file : files) {
            try {
                Image imagen = new Image();
                imagen.setFileName(file.getOriginalFilename());
                imagen.setFileType(file.getContentType());
                imagen.setData(new SerialBlob(file.getBytes()));
                imagen.setProduct(product);
                String buildDownLoadUri = "http://localhost:8080/api/v1/image/download/";
                String downloadUrl = buildDownLoadUri + imagen.getId();
                imagen.setDownloadUri(downloadUrl);
                Image savedImage = imageRepository.save(imagen);
                savedImage.setDownloadUri(buildDownLoadUri + savedImage.getId());
                ImageDto imageDto = new ImageDto();
                imageDto.setImageId(savedImage.getId());
                imageDto.setImageName(savedImage.getFileName());
                imageDto.setDownloadUrl(savedImage.getDownloadUri());
                saveImageDto.add(imageDto);
            } catch (IOException | SQLException e) {
                throw new RuntimeException("Error with the operation process!" + e.getMessage());
            }
        }
        return saveImageDto;
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
