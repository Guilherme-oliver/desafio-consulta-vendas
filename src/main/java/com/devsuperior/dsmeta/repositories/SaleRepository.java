package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT sale FROM Sale sale " +
            "WHERE sale.date >= :minDate AND sale.date <= :maxDate " +
            "AND UPPER(sale.seller.name) LIKE UPPER(CONCAT('%', :sellerName, '%'))")
    List<Sale> getSalesReportForThePeriodAndSeller(
            @Param("minDate") LocalDate minDate,
            @Param("maxDate") LocalDate maxDate,
            @Param("sellerName") String sellerName
    );

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleSummaryDTO(s.name, ROUND(SUM(sa.amount), 2)) " +
            "FROM com.devsuperior.dsmeta.entities.Seller s " +
            "INNER JOIN com.devsuperior.dsmeta.entities.Sale sa ON sa.seller = s " +
            "WHERE sa.date >= :minDate AND sa.date <= :maxDate " +
            "GROUP BY s.name")
    List<SaleSummaryDTO> getSalesSummaryOfSalesBySalesPersonInThePeriod(
            @Param("minDate") LocalDate minDate,
            @Param("maxDate") LocalDate maxDate
    );
}