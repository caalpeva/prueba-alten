package kalpeva.sampleforalten.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kalpeva.sampleforalten.Settings;
import kalpeva.sampleforalten.model.Price;
import kalpeva.sampleforalten.rest.PriceRequest;
import kalpeva.sampleforalten.service.PriceService;

@RestController
@RequestMapping("/api")
public class ApiRestController {
	
	@Autowired
	private PriceService priceService;
	
	@PostMapping("/prices")
	public ResponseEntity<Price> getPrices(@RequestBody PriceRequest priceRequest) {
		Optional<Price> price = priceService.getPriceToApply(priceRequest.getProductId(),
				priceRequest.getBrandId(),
				priceRequest.getDate());
		
		if (price.isPresent()) {
			  return new ResponseEntity<>(price.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Data Binding for properties of type Date
	 * @param webDataBinder
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(Settings.SIMPLE_DATE_FORMAT, true));
	}
}
