package com.springboot.controller;

import java.awt.image.BufferedImage;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.EAN13Writer;

public class BarbecueBarcodeGenerator {

	public static BufferedImage generateEAN13BarcodeImage(String barcodeText) throws Exception {
	    EAN13Writer barcodeWriter = new EAN13Writer();
	    BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.EAN_13, 300, 150);

	    return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}
}
