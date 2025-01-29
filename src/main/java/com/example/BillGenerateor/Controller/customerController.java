package com.example.BillGenerateor.Controller;

import com.example.BillGenerateor.Entity.Customer;
import com.example.BillGenerateor.Repository.CustomerRepo;
import com.example.BillGenerateor.Services.customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Customer")
public class customerController {

    @Autowired
    customerService customerService;

    @Autowired
    CustomerRepo customerRepo;

    @PostMapping("/Add_Customer")
    public void save(@RequestBody Customer c){
        customerService.save(c);
    }


}
