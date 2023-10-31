package com.dev.orderservice.model.helper;

import com.dev.orderservice.exception.InvalidOrdTypeCodeException;

public enum OrdType {
  
  BUY('1'),
  SELL('2'),
  BUY_MINUS('3'),
  SELL_PLUS('4'),
  SELL_SHORT('5'),
  CROSS('8'),
  ;

  private char code;

  private OrdType(char code){
    this.code = code;
  }

  public static OrdType getOrdType(char code){
      switch(code){
        case '1': return BUY;
        case '2': return SELL;
        case '3': return BUY_MINUS;
        case '4': return SELL_PLUS;
        case '5': return SELL_SHORT;
        case '8': return CROSS;
      }
      throw new InvalidOrdTypeCodeException("Invalid order type code:" + code);
  }
}
