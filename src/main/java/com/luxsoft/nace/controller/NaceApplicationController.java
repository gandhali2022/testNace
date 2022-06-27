package com.luxsoft.nace.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.luxsoft.nace.entity.NaceEntity;
import com.luxsoft.nace.service.NaceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Nace endpoint for Nace Operations")
@RequestMapping("nace")
public class NaceApplicationController {

	private NaceService naceService;

	@Autowired
	public void setNaceService(NaceService naceService) {
		this.naceService = naceService;
	}

	/**
	 * Method to fetch nace details from db
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "Get nace details for an id")
	@ApiResponses(value = { 
			@ApiResponse(code = 404, message = "Nace details Not found."),
			@ApiResponse(code = 200, message = "Fetched details successfully", response = NaceEntity.class) })
	@GetMapping("/{id}")
	public NaceEntity getNaceDetails(@PathVariable String id) {
		return naceService.getNaceDetailsById(id);
	}

	/**
	 * Method to save nace details in the DB
	 * 
	 * @param file
	 * @throws IOException
	 */
	@ApiOperation(value = "save nace details.")
	@ApiResponses(value = { 
			@ApiResponse(code = 400, message = "Request cannot be processed due to malformed data."),
			@ApiResponse(code = 200, message = "saved details successful") })
	@PostMapping(consumes = "multipart/form-data")
	public void saveNaceDetails(@RequestParam("file") MultipartFile file) throws IOException {

		CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
		CsvMapper mapper = new CsvMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MappingIterator<NaceEntity> readValues = mapper.readerFor(NaceEntity.class).with(bootstrapSchema)
				.readValues(file.getInputStream());
		naceService.insertNaceData(readValues.readAll());
	}
}
