package com.example.BillGenerateor.Controller;


import com.example.BillGenerateor.Services.emailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/mail")
public class emailController {
    @Autowired
    private emailService services;

    public emailController(emailService services) {
        this.services = services;
    }

    @PostMapping("/send")
    public String sendmail(@RequestParam(value = "file" ,required = false) MultipartFile[] file , String to , String subject , String body){
        return services.sendMail(file, to, subject, body);
    }

}
