package com.example.BillGenerateor.Services;

import com.example.BillGenerateor.DTO.response;
import com.example.BillGenerateor.Entity.Customer;
import com.example.BillGenerateor.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class customerService {

    @Autowired
    CustomerRepo customerRepo;

    public response save(Customer c){
        response response = new response();
       try {
           customerRepo.save(c);
           response.setHttpStatus(HttpStatus.CREATED);
           response.setMessage("Customer Registered");
           return response;
       }catch (Exception e){
           response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
           response.setMessage(String.valueOf(e));
           return response;
       }
    }
}
