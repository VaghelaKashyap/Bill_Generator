package com.example.BillGenerateor.Repository;

import com.example.BillGenerateor.Entity.bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface billRepo extends JpaRepository<bill, Long> {
}
