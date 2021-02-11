package com.spring.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.helper.ZXingHelper;
import com.spring.model.Product;
import com.spring.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	public static final String url="C:\\Users\\Shivakumar\\Pictures\\Saved Pictures";

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/saveBarcodes", method = RequestMethod.POST,produces = "application/json")
	public Product saveProducts(@RequestParam MultipartFile file,Product product) throws IOException {
		File f = new File(file.getOriginalFilename());
		f.createNewFile();
		FileOutputStream fos = new FileOutputStream(f);
		fos.write(file.getBytes());
		product.setImage(url+file.getOriginalFilename());
		fos.close();
		return productService.saveProduct(product);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("products", productService.findAll());
		return "product/index";
	}
	
	@RequestMapping(value = "/getAllProducts",method = RequestMethod.GET,produces = "application/json")
	public Iterable<Product> getAllProducts() {
		return productService.findAll();
	}
	
	@RequestMapping(value = "/barcode/{id}", method = RequestMethod.GET)
	public void barcode(@PathVariable("id") String id, HttpServletResponse response) throws Exception {
		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(ZXingHelper.getBarCodeImage(id, 200, 200));
		outputStream.flush();
		outputStream.close();
	}

}

