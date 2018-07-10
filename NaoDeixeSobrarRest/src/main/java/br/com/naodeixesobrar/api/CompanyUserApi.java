package br.com.naodeixesobrar.api;
 
 
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement
public class CompanyUserApi {
 
	private int companyId;
	private int userId;
 
	public CompanyUserApi(){
 
	}
 
	public CompanyUserApi(int companyId, int userId) {
		super();
		this.companyId = companyId;
		this.userId = userId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
 
}