package br.com.naodeixesobrar.entity;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
 
@Entity
@Table(name="company", schema="naodeixesobrar")
public class CompanyEntity {
 
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companies_seq_gen")
	@SequenceGenerator(schema="naodeixesobrar", name = "companies_seq_gen", sequenceName = "company_userid", allocationSize=1)
	@Column(name="id")
	private Integer id;
 
	@Column(name="name")	
	private String name;
	
	@Column(name="cpfcnpj")	
	private String cpfcnpj;
	
	@Column(name="zipcode")	
	private String zipcode;
	
	@Column(name="adress")	
	private String adress;
	
	@Column(name="numberadress")	
	private int numberadress;
	
	@Column(name="complement")	
	private String complement;

	@Column(name="district")	
	private String district;

	@Column(name="city")	
	private String city;

	@Column(name="url")	
	private String url;

	@Column(name="email")	
	private String email;

	@Column(name="phone")	
	private String phone;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCpfcnpj() {
		return cpfcnpj;
	}
	
	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}
	
	public String getZipcode() {
		return zipcode;
	}
	
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public String getAdress() {
		return adress;
	}
	
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	public int getNumberadress() {
		return numberadress;
	}
	
	public void setNumberadress(int numberadress) {
		this.numberadress = numberadress;
	}
	
	public String getComplement() {
		return complement;
	}
	
	public void setComplement(String complement) {
		this.complement = complement;
	}
	
	public String getDistrict() {
		return district;
	}
	
	public void setDistrict(String district) {
		this.district = district;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
 
}