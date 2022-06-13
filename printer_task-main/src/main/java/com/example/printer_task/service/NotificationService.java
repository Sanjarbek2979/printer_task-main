package com.example.printer_task.service;

import com.example.printer_task.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService {
    final NotificationRepository notificationRepository;

}
