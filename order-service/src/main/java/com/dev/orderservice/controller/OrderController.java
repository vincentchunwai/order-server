package com.dev.orderservice.controller;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.orderservice.infra.ApiResponse;
import com.dev.orderservice.model.OrderCreateRequestDTO;
import com.dev.orderservice.service.OrderService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;

@RequestMapping
@RestController
public class OrderController {

  @Autowired
  private OrderService orderService;

  //version 1
  @PostMapping(value = "/order", produces = "application/json")
  public ResponseEntity<ApiResponse<OrderCreateRequestDTO>> create(@Valid @RequestBody OrderCreateRequestDTO request)
    throws MethodArgumentNotValidException {

    orderService.create(request);

    CompletableFuture<Boolean> acknowledgmentFuture = CompletableFuture.supplyAsync(() -> {
        return orderService.forwardOrder(request);
    });

    boolean isAcknowledged;
    try {
        isAcknowledged = acknowledgmentFuture.get(); 
    } catch (InterruptedException | ExecutionException e) {
        isAcknowledged = false; 
    }

    String message = isAcknowledged ? "successful order request! Order approved." : "Failed to acknowledge order request.";

    HttpStatus httpStatus = isAcknowledged ? HttpStatus.ACCEPTED : HttpStatus.BAD_GATEWAY;

    return ResponseEntity.status(httpStatus)
        .body(ApiResponse.<OrderCreateRequestDTO>builder()
            .message(message)
            .data(request)
            .build());
  }

  //version 2
  @PostMapping(value = "/order2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public ResponseEntity<Flux<ServerSentEvent<String>>> create2(@Valid @RequestBody OrderCreateRequestDTO request)
      throws MethodArgumentNotValidException {

    orderService.create(request);

    Flux<ServerSentEvent<String>> eventFlux = Flux.create(sink -> {
      sink.next(ServerSentEvent.builder("successful order request!").build());

      CompletableFuture<Boolean> acknowledgmentFuture = CompletableFuture.supplyAsync(() -> {
        return orderService.forwardOrder(request);
      });

      acknowledgmentFuture.thenAccept(isAcknowledged -> {
        if (isAcknowledged) {
          sink.next(ServerSentEvent.builder("Order approved!").build());
        }
        sink.complete();
      });
    });

    return ResponseEntity.accepted()
        .body(eventFlux);
  }
}
