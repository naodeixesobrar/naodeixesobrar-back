package br.com.naodeixesobrar.api;
 
 
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement
public class UserApi {
 
	private int id;
	private String username;
	private String password;
	private String token;
 
	public UserApi(){
 
	}
 
	public UserApi(int id, String username, String password, String token) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.token = token;
	}

	public UserApi(int id, String username, String token) {
		super();
		this.id = id;
		this.username = username;
		this.token = token;
	}

	public UserApi(int id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
 
}