package com.example.BillGenerateor.Controller;

import com.example.BillGenerateor.DTO.response;
import com.example.BillGenerateor.Entity.Items;
import com.example.BillGenerateor.Services.itemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/Items")
public class itemsController {

    @Autowired
    itemServices itemServices;

    @PostMapping("/Add_Item")
    public response save(@RequestBody Items i){
       return itemServices.save(i);
    }

    @PostMapping("/Add_Items")
    public response saveAll(@RequestBody List<Items> i){
       return itemServices.saveAll(i);
    }

    @PutMapping("/Update_Stock")
    public response update(@RequestParam int id,@RequestParam int count){
        return itemServices.update(id,count);
    }

    @GetMapping("/generate-report")
    public response generateProductReport() throws IOException {
        String filePath = "E:\\product_report.csv";
        return itemServices.generateProductReportCsv(filePath);
    }
}
