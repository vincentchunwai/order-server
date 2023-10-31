package com.dev.orderservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "instruments")
public class Instrument {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "symbol")
  private String symbol;

  @Column(name = "symbol_sfx")
  private String symbolSfx;

  @Column(name = "security_id")
  private String securityID;

  @Column(name = "security_id_source")
  private String securityIDSource;

  @Column(name = "product")
  private String product;

  @Column(name = "cfi_code")
  private String cfiCode;

  @Column(name = "security_type")
  private String securityType;

  @Column(name = "maturity_month_year")
  private String maturityMonthYear;

  @Column(name = "maturity_date")
  private String maturityDate;

  public Instrument(String symbol, String symbolSfx, String product,String securityID, String securityIDSource, String cfiCode, String securityType, String maturityMonthYear, String maturityDate){
    this.symbol = symbol;
    this.symbolSfx = symbolSfx;
    this.product = product;
    this.securityID = securityID;
    this.securityIDSource = securityIDSource;
    this.cfiCode = cfiCode;
    this.securityType = securityType;
    this.maturityMonthYear = maturityMonthYear;
    this.maturityDate = maturityDate;
  }
}
