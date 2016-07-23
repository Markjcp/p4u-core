package com.p4u.core.test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

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
	    byte[] imageData = baos.toByteArray();
	}

}
