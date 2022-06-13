package com.example.printer_task.service;

import com.example.printer_task.entity.Brand;
import com.example.printer_task.entity.Model;
import com.example.printer_task.entity.Product;
import com.example.printer_task.entity.Services;
import com.example.printer_task.payload.response.ApiResponse;
import com.example.printer_task.payload.request.ProductDto;
import com.example.printer_task.repository.BrandRepository;
import com.example.printer_task.repository.ModelRepository;
import com.example.printer_task.repository.ProductRepository;
import com.example.printer_task.repository.ServicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    final ProductRepository productRepository;
    final ServicesRepository serviceRepository;
    final BrandRepository brandRepository;
    final ModelRepository modelRepository;

    public ApiResponse<List<Product>> getAll() {
        List<Product> allByActiveTrue = productRepository.findAllByActiveTrue();
        return new ApiResponse<>("Productlar ro'yxati", true, allByActiveTrue);
    }

    public ApiResponse<Product> getOne(Long id) {
        Optional<Product> byId = productRepository.findByIdAndActiveTrue(id);

        if (byId.isEmpty()) {
            return new ApiResponse<>("Bunday idli user topilmadi", false);
        }
        return new ApiResponse<>("Mana", true, byId.get());
    }

    public ApiResponse addProduct(ProductDto productDto) {
        boolean b = productRepository.existsByName(productDto.getName());
        if (b) {
            return new ApiResponse<>("Bunday name li product allaqachon mavjud", false);
        }
        Optional<Brand> byId = brandRepository.findById(productDto.getBrandId());
        if (byId.isEmpty()){
            return new ApiResponse("xatolik",false);
        }
        Brand brand = byId.get();
        Optional<Model> byId1 = modelRepository.findById(productDto.getModelId());
        if (byId1.isEmpty()){
            return new ApiResponse("Xatolik",false);
        }
        Model model = byId1.get();
        Product product = new Product();
        product.setBrand(brand);
        product.setModel(model);
        product.setName(productDto.getName());
        List<Services> allById = serviceRepository.findAllById(productDto.getServiceIds());
        product.setServiceList(allById);
        Product save = productRepository.save(product);
        return new ApiResponse<>("Qoshildi", true);

    }

    public ApiResponse deleteProduct(Long id) {
        Optional<Product> byId = productRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse<>("Bunday id li Product mavjudmas", false);
        }
        Product product = byId.get();
        product.setActive(false);
        productRepository.save(product);
        return new ApiResponse<>("o'chirildi", true);


    }


    public ApiResponse<Product> editProduct(Long id, ProductDto productDto) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isEmpty()) {
            return new ApiResponse<>("Bunday id li mavjud emas", false);
        }
        Product product = byId.get();
        product.setName(productDto.getName());
        List<Services> allById = serviceRepository.findAllById(productDto.getServiceIds());
        product.setServiceList(allById);
        Product save = productRepository.save(product);
        return new ApiResponse<>("O'zgartirildi", true);

    }
}
