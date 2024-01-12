package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.dto.SalesWithSellerReportDTO;
import com.devsuperior.dsmeta.entities.Seller;
import com.devsuperior.dsmeta.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	@Transactional(readOnly = true)
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<SalesWithSellerReportDTO> getSalesReportForThePeriodAndSeller(LocalDate minDate, LocalDate maxDate, String sellerName) {
		if (minDate == null) {
			minDate = LocalDate.now().minusYears(1);
		}

		if (maxDate == null) {
			maxDate = LocalDate.now();
		}
		List<Sale> salesReport = repository.getSalesReportForThePeriodAndSeller(minDate, maxDate, sellerName);
		return salesReport.stream()
				.map(sale -> new SalesWithSellerReportDTO(sale))
				.collect(Collectors.toList()
		);
	}

	@Transactional(readOnly = true)
	public List<SaleSummaryDTO> getSalesSummaryOfSalesBySalesPersonInThePeriod(LocalDate minDate, LocalDate maxDate) {
		if (minDate == null) {
			minDate = LocalDate.now().minusYears(1);
		}

		if (maxDate == null) {
			maxDate = LocalDate.now();
		}
		List<SaleSummaryDTO> result =
				repository.getSalesSummaryOfSalesBySalesPersonInThePeriod(minDate, maxDate);
		return result.stream().collect(Collectors.toList());
	}

	/*
	@Transactional(readOnly = true)
	public List<SaleSummaryDTO> getSalesSummaryOfSalesBySalesPersonInThePeriod(LocalDate minDate, LocalDate maxDate) {
		if (minDate == null) {
			minDate = LocalDate.now().minusYears(1);
		}

		if (maxDate == null) {
			maxDate = LocalDate.now();
		}
		List<Seller> salesSummary = repository.getSalesSummaryOfSalesBySalesPersonInThePeriod(minDate, maxDate);
		return salesSummary.stream()
				.map(sale -> new SaleSummaryDTO(sale))
				.collect(Collectors.toList()
		);
	}


	@Transactional(readOnly = true)
	public List<SalesFromSellerDTO> getSalesReportByDateRangeAndSeller(LocalDate minDate, LocalDate maxDate, String sellerName) {
		maxDate = LocalDate.now();
		minDate = maxDate.minusYears(1);
		List<Sale> result = repository.getSalesReportByDateRangeAndSeller(minDate, maxDate, sellerName);
		return result.stream()
				.map(sale -> new SalesFromSellerDTO(sale))
				.collect(Collectors.toList()
		);
	}

	private String converterLocalDateParaString(LocalDate localDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		return localDate.format(formatter);
	}
	 */
}