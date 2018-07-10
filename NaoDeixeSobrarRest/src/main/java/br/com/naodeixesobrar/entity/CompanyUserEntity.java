package br.com.naodeixesobrar.entity;
 
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
 
@Entity
@Table(name="companyuser", schema="naodeixesobrar")
public class CompanyUserEntity {
 
	@EmbeddedId
	private CompanyUserPKEntity companyUserPK;

	public CompanyUserPKEntity getCompanyUserPK() {
		return companyUserPK;
	}

	public void setCompanyUserPK(CompanyUserPKEntity companyUserPK) {
		this.companyUserPK = companyUserPK;
	}

}