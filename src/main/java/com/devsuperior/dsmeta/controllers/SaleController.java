package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.dto.SalesWithSellerReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.time.LocalDate;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/report")
	public ResponseEntity<List<SalesWithSellerReportDTO>> getSalesReportForThePeriodAndSeller(
			@RequestParam(value = "minDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate minDate,
			@RequestParam(value = "maxDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate maxDate,
			@RequestParam(value = "name", defaultValue = "") String name) {
		try {
			List<SalesWithSellerReportDTO> salesReport = service.getSalesReportForThePeriodAndSeller(minDate, maxDate, name);
			return ResponseEntity.ok(salesReport);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/summary")
	public ResponseEntity<List<SaleSummaryDTO>> getSalesSummaryOfSalesBySalesPersonInThePeriod(
			@RequestParam(value = "minDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate minDate,
			@RequestParam(value = "maxDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate maxDate) {
		try {
			List<SaleSummaryDTO> salesSummary = service.getSalesSummaryOfSalesBySalesPersonInThePeriod(minDate, maxDate);
			return ResponseEntity.ok(salesSummary);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}