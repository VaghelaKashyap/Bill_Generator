package com.example.BillGenerateor.Services;

import com.example.BillGenerateor.DTO.billDTO;
import com.example.BillGenerateor.DTO.response;
import com.example.BillGenerateor.Entity.Customer;
import com.example.BillGenerateor.Entity.Items;
import com.example.BillGenerateor.Entity.bill;
import com.example.BillGenerateor.Repository.billRepo;
import com.example.BillGenerateor.Repository.CustomerRepo;
import com.example.BillGenerateor.Repository.itemsRepo;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class billServices {
    @Autowired
    billRepo billRepo;

    @Autowired
    itemsRepo itemsRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    smsService smsService;


    public response generateBill(billDTO billDTO){
        response response = new response();

        double totalAmount = 0;

        Items items = itemsRepo.findById(billDTO.getP_id()).get();

        if (items.getPcount() < billDTO.getQuantity()){
            String adminMobileNo = "+918141011112";
            String message = items.getPname() + "Out of stock ,Add New Stock!";
            smsService.sendSms(adminMobileNo,message);
            response.setMessage("Product Out Of Stock");
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }

        totalAmount = items.getPrice() * billDTO.getQuantity();
        items.setPcount(items.getPcount()-billDTO.getQuantity());
        itemsRepo.save(items);
        System.out.println("hello");
        if (billDTO.getGst()){
            totalAmount += totalAmount * 0.18;
        }

        Customer customer = customerRepo.findByMobileNo(billDTO.getMobileNo());

        System.out.println("hello");
        bill bill = new bill();
        bill.setCustomer(customer);
        bill.setDate(LocalDate.now());
        bill.setAmount(totalAmount);
        bill.setItems(items);
        bill.setQuantity(billDTO.getQuantity());
        bill.setGst(billDTO.getGst());
        bill.setPaymentStatus(billDTO.getPaymentStatus());

        String Mobile = "+91" + String.valueOf(billDTO.getMobileNo());

        billRepo.save(bill);

        String message = "\nBill Number : " + String.valueOf(bill.getBillNo()) + "\nCustomer Name : " + bill.getCustomer().getName()
                + "\nTotal Amount : "+ String.valueOf(totalAmount) + "\nPayment-Status : " + bill.getPaymentStatus();

        smsService.sendSms(Mobile,message);
        smsService.sendWpMessage(Mobile,message);
        if (items.getPcount() < 10){
            String stockAlert = items.getPname() + " Item is less than minimum stock add new stock";
            String adminMobileNumber = "+918141011112";
            smsService.sendSms(adminMobileNumber,stockAlert);
        }
        response.setHttpStatus(HttpStatus.CREATED);
        response.setMessage("Order Successfully");
        return response;
    }
}
