
  package com.springboot;
  
  import java.io.File; import java.io.InputStream;
  
  import org.springframework.core.io.ClassPathResource; 
  import org.springframework.core.io.Resource; 
  import org.springframework.http.HttpStatus; 
  import org.springframework.http.MediaType; 
  import org.springframework.http.ResponseEntity; 
  import org.springframework.web.bind.annotation.GetMapping; 
  import org.springframework.web.bind.annotation.PathVariable; 
  import org.springframework.web.bind.annotation.RestController;
  
  @RestController 
  public class QRCodeController {
  
  private static final String QR_CODE_IMAGE_PATH ="./src/main/resources/QRCode.png";
  
  //private static final String QR_CODE_IMAGE_PATH ="C:\\Users\\Shivakumar\\Pictures\\QRCode.png";
  
  
  @GetMapping(value = "/genrateAndDownloadQRCode/{codeText}/{width}/{height}")
  public void download(@PathVariable("codeText") String codeText,@PathVariable("width") Integer width,
                    @PathVariable("height") Integer height) throws Exception {
     QRCodeGenerator.generateQRCodeImage(codeText, width, height,QR_CODE_IMAGE_PATH); }
  
  @GetMapping(value = "/genrateQRCode/{codeText}/{width}/{height}",produces =MediaType.IMAGE_JPEG_VALUE) 
  public ResponseEntity<byte[]> generateQRCode(@PathVariable("codeText") String codeText,@PathVariable("width") Integer width,
          @PathVariable("height") Integer height)throws Exception {
	  try { 
		  Resource resource = new ClassPathResource("QRCode.png"); 
		  InputStream input =resource.getInputStream(); 
		  File file = resource.getFile(); }catch(Exception e) { System.out.println(e);
		  }
	  return ResponseEntity.status(HttpStatus.OK).body(QRCodeGenerator.getQRCodeImage(codeText, width, height)); 
	  }
  }
 