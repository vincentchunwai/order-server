package com.dev.orderservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.dev.orderservice.exception.UpdateOrderUnsuccessful;
import com.dev.orderservice.model.OrderCreateRequestDTO;
import com.dev.orderservice.model.OrderRecord;
import com.dev.orderservice.model.helper.OrderStatus;
import com.dev.orderservice.model.mapper.OrderMapper;
import com.dev.orderservice.repository.OrderRecordRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class OrderService {
  
  @Autowired
  private OrderRecordRepository orderRecordRepository;

  @Value(value = "${gateway.url}")
  private String gatewayUrl;

  @Autowired
  private RestTemplate restTemplate;

  public OrderRecord create(OrderCreateRequestDTO orderDTO){
    OrderRecord orderRecord = OrderMapper.map(orderDTO);
    orderRecord.setOrderStatus(OrderStatus.ACCEPTED);
    orderRecordRepository.save(orderRecord);
    return orderRecord;
  }

  public Optional<OrderRecord> findOrderRecordByClOrdID(String clOrdID){
    return orderRecordRepository.findByClOrdID(clOrdID);
  }

  public boolean forwardOrder(OrderCreateRequestDTO request){
    try{
      ResponseEntity<String> response = restTemplate.postForEntity(gatewayUrl, request, String.class);
      if(response.getStatusCode() == HttpStatus.ACCEPTED){
        updateOrderStatus(request);
      }
      return true;
    } catch (RestClientException ex){
      throw new UpdateOrderUnsuccessful("Failed to send the order to Gateway");
    }
  }

  public void updateOrderStatus(OrderCreateRequestDTO orderRequest){
    Optional<OrderRecord> optionalOrder = orderRecordRepository.findByClOrdID(orderRequest.getClOrdID());
    if(optionalOrder.isPresent()){
      OrderRecord order = optionalOrder.get();
      order.setOrderStatus(OrderStatus.SENT);
      orderRecordRepository.save(order);
    }
    else{
      throw new UpdateOrderUnsuccessful("order record update failed");
    }
  }
}
