package com.example.printer_task.service;

import com.example.printer_task.entity.Brand;
import com.example.printer_task.entity.Model;
import com.example.printer_task.payload.response.ApiResponse;
import com.example.printer_task.payload.request.ModelDto;
import com.example.printer_task.repository.BrandRepository;
import com.example.printer_task.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModelService {
    final ModelRepository modelRepository;
    final BrandRepository brandRepository;

    public ApiResponse<List<Model>> getAll() {
        return new ApiResponse<>("List", true, modelRepository.findAll());
    }

    public ApiResponse<Model> getOne(Long id) {
        Optional<Model> byId = modelRepository.findById(id);
        if (byId.isEmpty()) {
            return new ApiResponse<>("Not found", false);
        }
        return new ApiResponse<>("Mana", true, byId.get());
    }

    public ApiResponse addModel(ModelDto modelDto) {

        Model model = new Model();

        Optional<Model> byName = modelRepository.findByName(modelDto.getName());
        if (byName.isPresent()) {
            return new ApiResponse("Bunday name li model oldindan mavjud", false);
        }
        model.setName(modelDto.getName());

        Model save = modelRepository.save(model);

        return new ApiResponse("Saqlandi", true, save);

    }

    public ApiResponse deleteModel(Long id) {
        modelRepository.deleteById(id);
        return new ApiResponse("Muvaffaqiyatli o`chirildi", true);
    }

    public ApiResponse<Model> editModel(Long id, ModelDto modelDto) {

        Optional<Model> byId1 = modelRepository.findById(id);
        if (byId1.isEmpty()){
            return new ApiResponse<>("Bunday model topilmadi",false);
        }


        Model model = byId1.get();

        model.setName(modelDto.getName());

        Model save = modelRepository.save(model);

        return new ApiResponse("Updated successfully", true,save);
    }
}
