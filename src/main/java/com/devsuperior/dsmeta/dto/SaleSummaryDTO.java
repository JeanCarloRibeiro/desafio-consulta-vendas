package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.interfaces.SaleSummaryProjection;

public class SaleSummaryDTO {

  private String sellerName;
  private Double amount;

  public SaleSummaryDTO(String sellerName, Double amount) {
    this.sellerName = sellerName;
    this.amount = amount;
  }

  public SaleSummaryDTO(SaleSummaryProjection prj) {
    this.sellerName = prj.getSellerName();
    this.amount = prj.getAmount();
  }

  public String getSellerName() {
    return sellerName;
  }
  public Double getAmount() {
    return amount;
  }


}
