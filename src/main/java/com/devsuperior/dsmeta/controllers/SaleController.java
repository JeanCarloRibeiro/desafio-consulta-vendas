package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleReportDTO>> getReport(
					@RequestParam(value = "minDate", required = false) String minDateParam,
					@RequestParam(value = "maxDate", required = false) String maxDateParam,
					@RequestParam(value = "name", required = false) String name,
					Pageable pageable
	) {
		// TODO
		Page<SaleReportDTO> dto = service.getSaleReport(minDateParam, maxDateParam, name, pageable);
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SaleSummaryDTO>> getSummary(
					@RequestParam(value = "minDate", required = false) String minDateParam,
					@RequestParam(value = "maxDate", required = false) String maxDateParam)
	{
		// TODO
		List<SaleSummaryDTO> dto = service.getSaleSummary(minDateParam, maxDateParam);
		return ResponseEntity.ok().body(dto);
	}

}
