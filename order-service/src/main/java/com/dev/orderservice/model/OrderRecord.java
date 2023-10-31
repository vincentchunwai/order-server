package com.dev.orderservice.model;

import java.time.LocalDateTime;

import com.dev.orderservice.model.helper.OrdType;
import com.dev.orderservice.model.helper.OrderStatus;
import com.dev.orderservice.model.helper.Side;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_record")
public class OrderRecord {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "message_header")
  private char messageHeader;

  @Column(name = "clOrdID")
  private String clOrdID;

  @Enumerated(EnumType.STRING)
  private Side side;

  @Column(name = "transaction_time")
  private LocalDateTime transactTime;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "instrument_id")
  private Instrument instrument;

  @Column(name = "order_quantity")
  private Double orderQty;

  @Enumerated(EnumType.STRING)
  @Column(name = "order_type")
  private OrdType ordType;

  @Column(name = "order_status")
  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;
  


  
}
