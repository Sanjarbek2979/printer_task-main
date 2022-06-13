package com.example.printer_task.service;

import com.example.printer_task.entity.Services;
import com.example.printer_task.payload.response.ApiResponse;

import com.example.printer_task.payload.request.ServicesDto;
import com.example.printer_task.repository.ServicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicesService {
   final ServicesRepository servicesRepository;


    public ApiResponse<List<Services>> getAll() {
        return new ApiResponse<>("List", true, servicesRepository.findAll());
    }

    public ApiResponse<Services> getOne(Long id) {
        Optional<Services> byId = servicesRepository.findById(id);
        if (byId.isEmpty()) {
            return new ApiResponse<>("Xatolik", false);
        }
        Services services = byId.get();
        return new ApiResponse<>("Mana", true, services);
    }

    public ApiResponse deleteServices(Long id) {
        servicesRepository.deleteById(id);
        return new ApiResponse("Deleted", true);
    }

    public ApiResponse addServices(ServicesDto servicesDto) {

        Optional<Services> byName = servicesRepository.findByName(servicesDto.getName());
        if (byName.isPresent()){
            return new ApiResponse("Bunday service oldindan mavjud",false);
        }

        Services services= new Services();
        services.setName(servicesDto.getName());
        services.setPrice(servicesDto.getPrice());
        Services save = servicesRepository.save(services);
        return new ApiResponse("saved", true, save);
    }

    public ApiResponse<Services> editServices(Long id, ServicesDto servicesDto) {

        Optional<Services> byId = servicesRepository.findById(id);
        if (byId.isEmpty()){
            return new ApiResponse<>("Xatolik",false);
        }
        Services services = byId.get();
        services.setName(servicesDto.getName());
        services.setPrice(servicesDto.getPrice());
        Services save = servicesRepository.save(services);

        return new ApiResponse("Updated", true, save);
    }
}
