package br.com.naodeixesobrar.api;
 
 
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement
public class CompanyApi {
 
	private int id;
	private String name;
	private String cpfcnpj;
	private String zipcode;
	private String adress;
	private int numberadress;
	private String complement;
	private String district;
	private String city;
	private String url;
	private String email;
	private String phone;

	public CompanyApi(){
 
	}
 
	public CompanyApi(int id, String name, String cpfcnpj, String zipcode, String adress,
			int numberadress, String complement, String district, String city, String url,
			String email, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.cpfcnpj = cpfcnpj;
		this.zipcode = zipcode;
		this.adress = adress;
		this.numberadress = numberadress;
		this.complement = complement;
		this.district = district;
		this.city = city;
		this.url = url;
		this.email = email;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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