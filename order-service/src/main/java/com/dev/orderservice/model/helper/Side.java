package com.dev.orderservice.model.helper;

import com.dev.orderservice.exception.InvalidSideCodeException;

public enum Side {
  MARKET('1'),
  LIMIT('2'),
  STOP('3'),
  STOP_LIMIT('4'),
  WITH_OR_WITHOUT('6'),
  ON_BASIS('9'),
  MARKET_IF_TOUCHED('J'),
  MARKET_WITH_LEFTOVER_AS_LIMIT('K'),
  ;

  private char code;
  private Side(char code){
    this.code = code;
  }

  public static Side getSide(char code){
     switch(code){
      case '1': return MARKET;
      case '2': return LIMIT;
      case '3': return STOP;
      case '4': return STOP_LIMIT;
      case '6': return WITH_OR_WITHOUT;
      case '9': return ON_BASIS;
      case 'J': return MARKET_IF_TOUCHED;
      case 'K': return MARKET_WITH_LEFTOVER_AS_LIMIT;
     }
    throw new InvalidSideCodeException("Invalid side code: " + code);
  }

}
