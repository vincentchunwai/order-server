package com.dev.apigateway.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class GatewayService {

  public void acknowledgeOrder() {
    int delayMillis = new Random().nextInt(5000);
    try {
      Thread.sleep(delayMillis);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
