package com.example.printer_task.service;

import com.example.printer_task.entity.Brand;
import com.example.printer_task.entity.Product;
import com.example.printer_task.payload.response.ApiResponse;
import com.example.printer_task.payload.request.BrandDto;
import com.example.printer_task.repository.BrandRepository;
import com.example.printer_task.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {
    final BrandRepository brandRepository;
    final ProductRepository productRepository;
    public ApiResponse<List<Brand>> getAll() {
        List<Brand> all = brandRepository.findAll();
        return new ApiResponse<>("Brandlar royxati",true,all);
    }

    public ApiResponse<Brand> getOne(Long id) {
        Optional<Brand> byId = brandRepository.findById(id);
        if(byId.isEmpty()){
            return new ApiResponse<>("Topilmadi",false);
        }
        Brand brand = byId.get();
        return new ApiResponse<>("Topildi",true,brand);

    }

    public ApiResponse<Brand> add(BrandDto brandDto) {
        boolean b = brandRepository.existsByName(brandDto.getName());
        if(b){
            return new ApiResponse<>("Bunday name li brand allaqachon mavjud",false);
        }
        Brand brand=new Brand();
        brand.setName(brandDto.getName());
        Brand save = brandRepository.save(brand);

        return new ApiResponse<>("Qoshildi",true,save);
    }

    public ApiResponse<Brand> edit(Long id, BrandDto brandDto) {
        Optional<Brand> byId = brandRepository.findById(id);
        if(byId.isEmpty()){
            return new ApiResponse<>("Bunday id li brand mavjud emas",false);
        }
        Brand brand = byId.get();
        brand.setName(brandDto.getName());

        Brand save = brandRepository.save(brand);
        return new ApiResponse<>("Update qilindi",true,save);
    }

    public ApiResponse<Brand> delete(Long id) {
        brandRepository.deleteById(id);
        return new ApiResponse<>("Brand delete qilindi",true);

    }
}
