package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.dto.SalesWithSellerReportDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
		LocalDate adjustedMinDate = (minDate == null) ? LocalDate.now().minusYears(1) : minDate;
		LocalDate adjustedMaxDate = (maxDate == null) ? LocalDate.now() : maxDate;
		List<Sale> salesReport = repository.getSalesReportForThePeriodAndSeller(adjustedMinDate, adjustedMaxDate, sellerName);
		return salesReport.stream()
				.map(SalesWithSellerReportDTO::new)
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<SaleSummaryDTO> getSalesSummaryOfSalesBySalesPersonInThePeriod(LocalDate minDate, LocalDate maxDate) {
		LocalDate adjustedMinDate = (minDate == null) ? LocalDate.now().minusYears(1) : minDate;
		LocalDate adjustedMaxDate = (maxDate == null) ? LocalDate.now() : maxDate;

		List<SaleSummaryDTO> salesSummary = repository.getSalesSummaryOfSalesBySalesPersonInThePeriod(adjustedMinDate, adjustedMaxDate);
		return salesSummary.stream().collect(Collectors.toList());
	}
}