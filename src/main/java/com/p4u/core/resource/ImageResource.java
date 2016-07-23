package com.p4u.core.resource;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@Path("/image")
public class ImageResource {
	
	@GET
	@Path("png/{name}")
	@Produces("image/png")
	public Response getFullImage(@PathParam("name") String name) throws IOException {
		Resource resource = new ClassPathResource("img/"+name);
		InputStream resourceInputStream = resource.getInputStream();		
		BufferedImage image = ImageIO.read(resourceInputStream);		
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write(image, "png", baos);
	    byte[] imageData = baos.toByteArray();

	    // uncomment line below to send non-streamed
	    // return Response.ok(imageData).build();

	    // uncomment line below to send streamed
	    return Response.ok(new ByteArrayInputStream(imageData)).build();
	}

}
