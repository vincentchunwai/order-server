package com.dev.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.orderservice.model.OrderRecord;
import java.util.Optional;


@Repository
public interface OrderRecordRepository extends JpaRepository<OrderRecord, Long>{
  
  public Optional<OrderRecord> findByClOrdID(String clOrdID);
}
