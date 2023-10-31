package com.dev.orderservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dev.orderservice.infra.ApiResponse;
import com.dev.orderservice.model.Instrument;
import com.dev.orderservice.model.OrderCreateRequestDTO;
import com.dev.orderservice.model.OrderRecord;
import com.dev.orderservice.model.helper.OrdType;
import com.dev.orderservice.model.helper.Side;
import com.dev.orderservice.model.mapper.OrderMapper;
import com.dev.orderservice.repository.OrderRecordRepository;
import com.dev.orderservice.service.OrderService;

@ContextConfiguration
@DataJpaTest
public class OrderServiceTest {

  @InjectMocks
  private OrderService orderService;

  @Mock
  private OrderRecordRepository orderRecordRepository;

  @Test
  public void testCreate() {
    OrderCreateRequestDTO request = new OrderCreateRequestDTO();
    request.setCheckSum("193");
    request.setClOrdID("CL1234");
    request.setInstrument(new Instrument());
    request.setMessageHeader('D');
    request.setOrderQty(100.0);
    request.setOrdType('1');
    request.setSide('2');
    request.setTransactTime(LocalDateTime.now());

    orderService.create(request);

    verify(orderRecordRepository, times(1)).save(any(OrderRecord.class));

    ArgumentCaptor<OrderRecord> orderRecordCaptor = ArgumentCaptor.forClass(OrderRecord.class);
    verify(orderRecordRepository).save(orderRecordCaptor.capture());
    
    OrderRecord savedOrder = orderRecordCaptor.getValue();

    assertNotNull(savedOrder);
    assertEquals('D', savedOrder.getMessageHeader());
    assertEquals(100.0, savedOrder.getOrderQty());
    assertEquals(OrdType.BUY, savedOrder.getOrdType());
    assertEquals(Side.LIMIT, savedOrder.getSide());
  }

}
