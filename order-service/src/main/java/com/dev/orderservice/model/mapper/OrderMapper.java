package com.dev.orderservice.model.mapper;

import com.dev.orderservice.model.OrderCreateRequestDTO;
import com.dev.orderservice.model.OrderRecord;
import com.dev.orderservice.model.helper.OrdType;
import com.dev.orderservice.model.helper.OrderStatus;
import com.dev.orderservice.model.helper.Side;

public class OrderMapper {
  
  public static OrderRecord map(OrderCreateRequestDTO orderDTO){
    return OrderRecord.builder()
        .messageHeader(orderDTO.getMessageHeader())
        .clOrdID(orderDTO.getClOrdID())
        .side(Side.getSide(orderDTO.getSide()))
        .transactTime(orderDTO.getTransactTime())
        .instrument(orderDTO.getInstrument())
        .orderQty(orderDTO.getOrderQty())
        .ordType(OrdType.getOrdType(orderDTO.getOrdType()))
        .orderStatus(OrderStatus.PENDING)
        .build();
  }
}
