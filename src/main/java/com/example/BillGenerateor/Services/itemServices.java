package com.example.BillGenerateor.Services;

import com.example.BillGenerateor.DTO.response;
import com.example.BillGenerateor.Entity.Items;
import com.example.BillGenerateor.Repository.itemsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Optional;


@Service
public class itemServices {

    @Autowired
    itemsRepo itemsRepo;
    @Autowired
    smsService smsService;

    public response save(Items i){
        response response = new response();
        try {
            itemsRepo.save(i);
            response.setHttpStatus(HttpStatus.CREATED);
            response.setMessage("Product Added");
        }catch (Exception e){
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
        }
        return response;
    }
    public response saveAll(List<Items> i){
        response response = new response();
        try {
            itemsRepo.saveAll(i);
            response.setHttpStatus(HttpStatus.CREATED);
            response.setMessage("All Product Added");
        }catch (Exception e){
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public response update(int id,int count){
        response response = new response();
        try {
            Optional<Items> items =  itemsRepo.findById(id);
            items.get().setPcount(items.get().getPcount()+count);
            itemsRepo.save(items.get());
            response.setHttpStatus(HttpStatus.CREATED);
            response.setMessage("Stock Updated");
        }catch (Exception e){
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
        }
        String adminMobileNumber = "+918141011112";
        String message = itemsRepo.findById(id).get().getPname() + " Item added successfully \nQuantity : " + String.valueOf(itemsRepo.findById(id).get().getPcount());
        smsService.sendSms(adminMobileNumber,message);

        return response;
    }

    public response generateProductReportCsv(String filePath) throws IOException {
        response response = new response();
        List<Items> itemsList = itemsRepo.findAll();
        try(Writer writer=new FileWriter(filePath);
            ICsvBeanWriter csvBeanWriter=new CsvBeanWriter(writer, CsvPreference.EXCEL_PREFERENCE)){

            String[] headers={"id", "pname", "price" ,"pcount"};
            csvBeanWriter.writeHeader(headers);

            for (Items items : itemsList){
                csvBeanWriter.write(items,headers);
            }

            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("CSV fill Generated. ");
        }
        catch (Exception e){
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
