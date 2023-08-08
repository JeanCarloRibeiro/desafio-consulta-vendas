package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.interfaces.SaleReportProjection;
import com.devsuperior.dsmeta.interfaces.SaleSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

  @Query(value =
          "SELECT obj " +
          "FROM Sale obj " +
          "WHERE obj.seller.name LIKE concat('%', :sellerName, '%') ")
  List<Sale> getSale(String sellerName);

  @Query(nativeQuery = true, value =
          "SELECT SE.NAME as sellerName, CAST(SUM(SA.AMOUNT) AS NUMBER(16,2)) AS amount " +
          "FROM TB_SALES SA,  TB_SELLER SE " +
          "WHERE SA.SELLER_ID = SE.ID " +
          "AND SA.DATE >= :minDate AND SA.DATE <= :maxDate " +
          "GROUP BY SE.NAME")
  List<SaleSummaryProjection> getSaleSummary(LocalDate minDate, LocalDate maxDate);

  @Query(nativeQuery = true, value =
          "SELECT SE.ID AS id, SA.DATE AS date, CAST(SA.AMOUNT AS NUMBER(16,2)) AS amount, SE.NAME as sellerName " +
                  "FROM TB_SALES SA,  TB_SELLER SE " +
                  "WHERE SA.SELLER_ID = SE.ID " +
                  "AND SA.DATE >= :minDate AND SA.DATE <= :maxDate " +
                  "AND lower(SE.NAME) like concat('%', :name, '%') ",
          countQuery =
                  "SELECT COUNT(1)" +
                  "FROM TB_SALES SA,  TB_SELLER SE " +
                  "WHERE SA.SELLER_ID = SE.ID " +
                  "AND SA.DATE >= :minDate AND SA.DATE <= :maxDate " +
                  "AND lower(SE.NAME) like concat('%', lower(:name), '%')")
  Page<SaleReportProjection> getSaleReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

}
