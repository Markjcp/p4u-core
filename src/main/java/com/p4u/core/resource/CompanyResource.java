package com.p4u.core.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.p4u.core.beans.CompanyLoginResult;
import com.p4u.core.dao.CompanyRepository;
import com.p4u.core.model.Company;

@Component
@Path("/company")
public class CompanyResource {
	
	@Autowired
	@Qualifier("companyRepository")
	private CompanyRepository companyRepository;
	
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Company> companies() {
		return (List<Company>) companyRepository.findAll();
	}
	
	@POST
	@Path("register/{name}/{password}/{address}/{location}")
	@Produces(MediaType.APPLICATION_JSON)
	public Company register(@PathParam("name") String name, @PathParam("password") String password,
			@PathParam("address") String address, @PathParam("location") String location ) {
		Company company = new Company();
		company.setName(name);
		company.setPassword(password);
		company.setAddress(address);
		company.setAddress(address);
		company.setCode(0);
		company = companyRepository.save(company);
		company.setCode(Integer.valueOf(company.getId().toString()));
		return companyRepository.save(company);
	}
	
	@POST
	@Path("login/{code}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public CompanyLoginResult register(@PathParam("code") Integer code, @PathParam("password") String password){
		List<Company> results = companyRepository.findByCode(code);
		if(results == null || results.isEmpty()){
			return new CompanyLoginResult(false);
		}
		Company result = results.iterator().next();
		return new CompanyLoginResult(result.getPassword().equals(password),result);
	}

}
