package com.p4u.core.test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p4u.core.beans.PresentBuyOrder;
import com.p4u.core.beans.PresentBuyResult;
import com.p4u.core.model.Present;

public class ImageTest {
	
	private String name;
	
	@Before
	public void setup(){
		name = "img/gray.png";
	}
	
	@Test
	public void testImageInput() throws IOException{
		Resource resource = new ClassPathResource(name);
		InputStream resourceInputStream = resource.getInputStream();
		BufferedImage image = ImageIO.read(resourceInputStream);		
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write(image, "png", baos);
	}
	
	@Test
	public void toBase64() throws IOException{
		Present present = new Present();
		File file = new File("/home/marcos/workspaces/facu/taller-desarrollo-proyectos-I/p4u-core/src/test/resources/img/ballet.jpg");
		FileInputStream stream = new FileInputStream(file);
		byte[] bFile = new byte[(int) file.length()];
		stream.read(bFile);
		present.setImage(bFile);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(present);
		System.out.println(json);
		Present newPresent = new Present();
		newPresent = mapper.readValue(json, Present.class);
		System.out.println(newPresent);
		stream.close();
	}
	
	public static void main(String[] args) throws JsonProcessingException {
		PresentBuyResult order = new PresentBuyResult();
		order.setResultCode(1);
		order.setErrorDescription("Sin stock");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(order);
		System.out.println(json);
	}

}
