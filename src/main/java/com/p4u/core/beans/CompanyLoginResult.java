package com.p4u.core.beans;

import com.p4u.core.model.Company;

public class CompanyLoginResult {

	private boolean logged;

	private Company company;
	
	public CompanyLoginResult(boolean logged) {
		super();
		this.logged = logged;
	}

	public CompanyLoginResult(boolean logged, Company company) {
		super();
		this.logged = logged;
		this.company = company;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
