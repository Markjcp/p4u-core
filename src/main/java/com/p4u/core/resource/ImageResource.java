package com.p4u.core.resource;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.p4u.core.dao.PresentRepository;
import com.p4u.core.model.Present;

@Component
@Path("/image")
public class ImageResource {
	
	private static final Logger logger = Logger.getLogger(ImageResource.class.toString());
	
	@Autowired
	@Qualifier("presentRepository")
	private PresentRepository presentRepository;
	
	@GET
	@Path("{name}")
	@Produces("image/png")
	public Response getFullImage(@PathParam("name") String name) {
		byte[] imageData = null;
		try {
			Resource resource = new ClassPathResource("img/"+name);
			InputStream resourceInputStream = resource.getInputStream();		
			BufferedImage image = ImageIO.read(resourceInputStream);		
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "png", baos);
			imageData = baos.toByteArray();			
		} catch (IOException e) {
			logger.log(Level.INFO, "Image not found in local resource");
			String fileSplitted[] = name.split("/");
			String imageName = fileSplitted[fileSplitted.length-1];
			String presentId = imageName.split("\\.")[0];
			Present present = presentRepository.findOne(Long.valueOf(presentId));
			imageData = present.getImage();
		}
	    return Response.ok(new ByteArrayInputStream(imageData)).build();
	}

}
