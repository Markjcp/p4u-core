package com.p4u.core.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.p4u.core.dao.PreferenceRepository;
import com.p4u.core.model.Preference;

@Component
@Path("/preference")
public class PreferenceResource {
	
	@Autowired
	@Qualifier("preferenceRepository")
	private PreferenceRepository preferenceRepository;
	
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Preference> preferences() {
		return (List<Preference>) preferenceRepository.findAll();
	}
}
