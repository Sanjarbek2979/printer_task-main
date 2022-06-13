package com.example.printer_task.service;


import com.example.printer_task.entity.*;
import com.example.printer_task.payload.request.ApplicationDto;
import com.example.printer_task.payload.response.ApiResponse;
import com.example.printer_task.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    final ApplicationRepository applicationRepository;
    final HistoryRepository historyRepository;
    final ServicesProductRepository servicesProductRepository;
    final ServicesRepository servicesRepository;
    final ProductRepository productRepository;
    final UserRepository userRepository;

    public ApiResponse<Application> save(ApplicationDto applicationDto) {

        List<ServiceProduct> serviceProducts = new ArrayList<>();

         long[] amount = applicationDto.getAmount();  // frontdan keladigan servislarni miqdorlarini listi
        long[] serviceIds = applicationDto.getServiceIds(); // frontdan keladigan servicelarni idlarini listi
        long[] productIds = applicationDto.getProductIds(); // frontdan keladigan productlarni idlarini listi
        for (int i = 0; i < serviceIds.length; i++) {

            /*
            BU JOYDA BIZ FOR BILAN AYLANIB CHIQIB FRONTDAN KELGAТ BARCHA SERVICE PRODUCTLARNI DATABASE GA SAQLAB OLDIK

             */


            ServiceProduct serviceProduct = new ServiceProduct();
            Optional<Services> byId = servicesRepository.findById(serviceIds[i]);
            if (byId.isEmpty()) {
                return new ApiResponse<>("Xatolik service topilmadi", false);
            }
            Services services = byId.get();
            Optional<Product> byId1 = productRepository.findById(productIds[i]);
            if (byId1.isEmpty()) {
                return new ApiResponse<>("Xatolik  product topilmadi", false);
            }
            Product product = byId1.get();

            serviceProduct.setService(services);
            serviceProduct.setProduct(product);
            serviceProduct.setAmount(Math.toIntExact(amount[i]));
            serviceProducts.add(serviceProduct);
        }
        List<ServiceProduct> serviceProductsList = servicesProductRepository.saveAll(serviceProducts);

        Application application= new Application();
        application.setDate(new Timestamp(System.currentTimeMillis()));
        application.setDescription(applicationDto.getDescription());
//        User sender = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Department senderDepartment = sender.getDepartment();
//        application.setSender(sender);
//        application.setSenderDepartment(senderDepartment);

        application.setServiceProducts(serviceProductsList);

        Optional<User> byId = userRepository.findById(applicationDto.getReceiverId());
        if (byId.isEmpty()){
            return new ApiResponse<>("Qabul qiluvchi user topilmadi",false);
        }

        User receiver = byId.get();
        application.setReceiver(receiver);
        application.setReceiverDepartment(receiver.getDepartment());

        Application savedApplication = applicationRepository.save(application);

        History history = new History();
        history.setApplication(savedApplication);
        historyRepository.save(history);

        return new ApiResponse<>("Saqlandi",true,savedApplication);
    }

    public ApiResponse<Application> getOne(Long id) {

        Optional<Application> byId = applicationRepository.findById(id);
        if (byId.isEmpty()){
            return new ApiResponse<>("This application not found",false);
        }
        return new ApiResponse<>("Mana",true,byId.get());
    }

    public ApiResponse<List<Application>> getAll() {
        return new ApiResponse<>("List",true,applicationRepository.findAll());
    }


}
