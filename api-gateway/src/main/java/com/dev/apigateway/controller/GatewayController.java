package com.dev.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.apigateway.model.OrderCreateRequestDTO;
import com.dev.apigateway.service.GatewayService;

@RestController
@RequestMapping
public class GatewayController {

  @Autowired
  private GatewayService gatewayService;

  @PostMapping("/processOrder")
  public ResponseEntity<String> processOrder(@RequestBody OrderCreateRequestDTO orderRequest) {
    gatewayService.acknowledgeOrder();
    return ResponseEntity.accepted().body("Order acknowledged.");
  }

  
}
