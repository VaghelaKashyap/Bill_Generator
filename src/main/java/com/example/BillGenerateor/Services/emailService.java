package com.example.BillGenerateor.Services;
import org.springframework.web.multipart.MultipartFile;

public interface emailService {
    String sendMail(MultipartFile[] file, String to, String subject, String body);
}
