package com.example.BillGenerateor.Repository;

import com.example.BillGenerateor.Entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface itemsRepo extends JpaRepository<Items,Integer>{
    List<Items> findAllByOrderByPriceDesc();
}
