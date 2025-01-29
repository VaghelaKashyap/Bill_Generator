package com.example.BillGenerateor.Repository;

import com.example.BillGenerateor.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer>{

    Customer findByName(String name);

    Customer findByMobileNo(Long mobileNo);
}
