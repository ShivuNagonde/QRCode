package com.springboot.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.entity.Product;
import com.springboot.helper.ZXingHelper;
import com.springboot.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	public static final String url="C:\\Users\\Shivakumar\\Pictures\\Saved Pictures";
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Product creatProduct(@RequestParam MultipartFile file, Product product) throws IOException {
		File f = new File(file.getOriginalFilename());
		f.createNewFile();
		FileOutputStream fos = new FileOutputStream(f);
		fos.write(file.getBytes());
		product.setImage(url+file.getOriginalFilename());
		fos.close();
		return productService.saveProducts(product);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("products", productService.findAll());
		return "product/index";
	}
	
	@RequestMapping(value = "/getAllProducts",method = RequestMethod.GET,produces = "application/json")
	@ResponseBody
	public Iterable<Product> getAllProduct() {
		return productService.findAll();
		 
	}
	
	@RequestMapping(value = "/qrcode/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String qrcode(@PathVariable("id") Product product, HttpServletResponse response) throws Exception {
		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(ZXingHelper.getQRCodeImage("Product Id :"+ product.getId()+"\n"+"Product Name :"+ product.getName()
		+"\n"+"Designation :"+ product.getDesignation()+"\n"+"UOM :"+ product.getUom()+"\n"+"HSN :"+ product.getHsn()+"\n"+"Product Image :"+ product.getImage()
		+"\n"+"Total Price :"+ product.getPrice()+"\n"+"Validity :"+ product.getValidity()+"\n"+"ManufactureDate :"+ product.getManufactureDate(), 200, 200));
		outputStream.flush();
		outputStream.close();
		return "QRCode is generated successfully.";
	}
}
