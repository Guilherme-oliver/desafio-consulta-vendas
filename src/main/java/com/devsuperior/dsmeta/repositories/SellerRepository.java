package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleSummaryDTO(obj.seller.name, SUM(obj.amount)) "
            + "FROM Sale obj "
            + "GROUP BY obj.seller.name")
    List<SaleSummaryDTO> getSalesReportForThePeriodAndSeller(
            @Param("minDate") LocalDate minDate,
            @Param("maxDate") LocalDate maxDate
    );
}