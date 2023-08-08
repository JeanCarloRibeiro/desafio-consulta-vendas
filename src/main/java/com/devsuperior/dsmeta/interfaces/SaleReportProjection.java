package com.devsuperior.dsmeta.interfaces;

import java.time.LocalDate;

public interface SaleReportProjection {

  Long getId();
  LocalDate getDate();
  Double getAmount();
  String getSellerName();


}
