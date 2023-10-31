package com.dev.orderservice;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.dev.orderservice.controller.OrderController;
import com.dev.orderservice.infra.ApiResponse;
import com.dev.orderservice.model.OrderCreateRequestDTO;
import com.dev.orderservice.model.OrderRecord;
import com.dev.orderservice.model.helper.OrdType;
import com.dev.orderservice.model.helper.Side;
import com.dev.orderservice.service.OrderService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class OrderControllerTest {

  @InjectMocks
  private OrderController orderController;

  @Mock
  private OrderService orderService;

  @Test
  public void testCreateOrderAcknowledged() throws MethodArgumentNotValidException {

    when(orderService.forwardOrder(any(OrderCreateRequestDTO.class)))
    .thenAnswer(invocation -> true);

    OrderCreateRequestDTO request = new OrderCreateRequestDTO();


    ResponseEntity<ApiResponse<OrderCreateRequestDTO>> response = orderController.create(request);

    assertNotNull(response);
    assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    assertEquals("successful order request! Order approved.", response.getBody().getMessage());
    assertEquals(request, response.getBody().getData());
  }
  
  @Test
  public void testCreateOrderNotAcknowledged() throws MethodArgumentNotValidException {

    when(orderService.forwardOrder(any(OrderCreateRequestDTO.class)))
    .thenAnswer(invocation -> false);

    OrderCreateRequestDTO request = new OrderCreateRequestDTO();


    ResponseEntity<ApiResponse<OrderCreateRequestDTO>> response = orderController.create(request);

    assertNotNull(response);
    assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
    assertEquals("Failed to acknowledge order request.", response.getBody().getMessage());
    assertEquals(request, response.getBody().getData());
  }
}
