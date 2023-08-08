package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.interfaces.SaleReportProjection;

import java.time.LocalDate;

public class SaleReportDTO {

  private Long id;
  private LocalDate date;
  private Double amount;
  private String sellerName;


  public SaleReportDTO(String sellerName, Double amount) {
    this.sellerName = sellerName;
    this.amount = amount;
  }

  public SaleReportDTO(SaleReportProjection prj) {
    this.id = prj.getId();
    this.amount = prj.getAmount();
    this.sellerName = prj.getSellerName();
    this.date = prj.getDate();
  }

  public Long getId() {
    return id;
  }
  public String getSellerName() {
    return sellerName;
  }
  public Double getAmount() {
    return amount;
  }
  public LocalDate getDate() {
    return date;
  }


}
