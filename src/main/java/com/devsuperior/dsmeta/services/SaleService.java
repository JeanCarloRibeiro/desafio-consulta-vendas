package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.interfaces.SaleReportProjection;
import com.devsuperior.dsmeta.interfaces.SaleSummaryProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	private LocalDate minDate = null;
	private LocalDate maxDate = null;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public List<SaleSummaryDTO> getSaleSummary(String minDateParam, String maxDateParam) {

		minDate = validaDateMin(minDateParam, maxDateParam);
		maxDate = validaDateMax(maxDateParam);
		System.out.println("getSaleSummary--> minDate: " + minDate + " maxDate: "+ maxDate);

		List<SaleSummaryProjection> result = repository.getSaleSummary(minDate, maxDate);
		return result.stream().map( r -> new SaleSummaryDTO(r)).collect(Collectors.toList());
	}

	public Page<SaleReportDTO> getSaleReport(String minDateParam, String maxDateParam, String name, Pageable pageable) {

		minDate = validaDateMin(minDateParam, maxDateParam);
		maxDate = validaDateMax(maxDateParam);
		System.out.println("getSaleReport--> minDate: " + minDate + " maxDate: "+ maxDate);

		Page<SaleReportProjection> result = repository.getSaleReport(minDate, maxDate, name, pageable);
		return result.map(r -> new SaleReportDTO(r));
	}

	private LocalDate validaDateMin(String minDateParam, String maxDateParam) {
		if (minDateParam == null) {
			LocalDate minhaDataFinal = validaDateMax(maxDateParam);
			minDate = minhaDataFinal.minusYears(1L);
		} else {
			minDate = LocalDate.parse(minDateParam);
		}
		return minDate;
	}
	private LocalDate validaDateMax(String maxDateParam) {
		if (maxDateParam == null) {
			maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		} else {
			maxDate = LocalDate.parse(maxDateParam);
		}
		return maxDate;
	}


}
