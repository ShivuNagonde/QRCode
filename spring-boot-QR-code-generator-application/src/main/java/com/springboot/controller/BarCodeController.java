package com.springboot.controller;

import java.awt.image.BufferedImage;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/barcodes")
public class BarCodeController {

    @GetMapping(value = "/barbecue/ean13/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barbecueEAN13Barcode(@PathVariable("barcode") String barcode)
    throws Exception {
        return ResponseEntity.ok(BarbecueBarcodeGenerator.generateEAN13BarcodeImage(barcode));
    }

	/*
	 * private ResponseEntity<BufferedImage> okResponse(BufferedImage
	 * generateEAN13BarcodeImage) { // TODO Auto-generated method stub return null;
	 * }
	 */
    
	@Bean
	public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
	    return new BufferedImageHttpMessageConverter();
	}
}
