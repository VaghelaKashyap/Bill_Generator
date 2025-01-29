package com.example.BillGenerateor.Controller;

import com.example.BillGenerateor.DTO.billDTO;
import com.example.BillGenerateor.DTO.response;

import com.example.BillGenerateor.Repository.CustomerRepo;
import com.example.BillGenerateor.Services.billServices;
import com.example.BillGenerateor.Services.customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
public class billController {

    @Autowired
    billServices billServices;

    @Autowired
    customerService customerService;

    @Autowired
    CustomerRepo customerRepo;

    @PostMapping("/checkout")
    public response generateBill(@RequestBody  billDTO billDTO){
        if (customerRepo.findByMobileNo(billDTO.getMobileNo()) == null){
            response response = new response();
            response.setMessage("No user found");
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return billServices.generateBill(billDTO);
    }
}
