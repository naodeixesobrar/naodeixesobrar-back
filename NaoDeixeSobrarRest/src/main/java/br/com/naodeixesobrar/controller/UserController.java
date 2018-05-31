package br.com.naodeixesobrar.controller;
 
 
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import br.com.naodeixesobrar.api.UserApi;
import br.com.naodeixesobrar.entity.UserEntity;
import br.com.naodeixesobrar.repository.UserRepository;
 
 
/**
 * Essa classe vai expor os nossos métodos para serem acessasdos via http
 * 
 * @Path - Caminho para a chamada da classe que vai representar o nosso serviço
 * */
@Path("/user")
public class UserController {
 
	private final UserRepository repository = new UserRepository();
 
	/**
	 * @Consumes - determina o formato dos dados que vamos postar
	 * @Produces - determina o formato dos dados que vamos retornar
	 * 
	 * Esse método cadastra uma nova pessoa
	 * */
	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/text; charset=UTF-8")
	public String insert(UserApi user){
		UserEntity entity = new UserEntity();
		try {
			entity.setUsername(user.getUsername());
			entity.setPassword(user.getPassword());
			repository.save(entity);
			return "Registro cadastrado com sucesso!";
		} catch (Exception e) {
			return "Erro ao cadastrar um registro " + e.getMessage();
		}
	}
 
	/**
	 * Essse método altera uma pessoa já cadastrada
	 * **/
	@PUT
	@Produces("application/text; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")	
	public String edit(UserApi user){
		UserEntity entity = repository.getUser(user.getId());
		try {
			if (entity == null)
				return "Usuário não encontrado";
			entity.setUsername(user.getUsername());
			entity.setPassword(user.getPassword());
			repository.edit(entity);
			return "Registro alterado com sucesso!";
		} catch (Exception e) {
			return "Erro ao alterar o registro " + e.getMessage();
		}
	}

	/**
	 * Esse método lista todas pessoas cadastradas na base
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/all")
	public List<UserApi> all(){
		List<UserApi> users =  new ArrayList<UserApi>();
		List<UserEntity> listEntityUsers = repository.all();
		for (UserEntity entity : listEntityUsers) {
			users.add(new UserApi(entity.getId(), entity.getUsername(),entity.getPassword()));
		}
		return users;
	}
 
	/**
	 * Esse método busca uma pessoa cadastrada pelo código
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/getUser/{id}")
	public UserApi getUser(@PathParam("id") Integer id){
		UserEntity entity = repository.getUser(id);
		if(entity != null)
			return new UserApi(entity.getId(), entity.getUsername(),entity.getPassword());
		return null;
	}
 
	/**
	 * Excluindo uma pessoa pelo código
	 * */
	@DELETE
	@Produces("application/text; charset=UTF-8")
	@Path("/{id}")	
	public String remove(@PathParam("id") Integer id){
		try {
			repository.remove(id);
			return "Registro excluido com sucesso!";
		} catch (Exception e) {
			return "Erro ao excluir o registro! " + e.getMessage();
		}
	}

	/**
	 * Login validate
	 * */
	@GET
	@Produces("application/text; charset=UTF-8")
	@Path("/login")	
	public String login(@QueryParam("username") String username, 
			@QueryParam("password") String password){
		try {
			if (repository.login(username, password) != null)
				return "Login realizado com sucesso.";
			else
				return "Usuário e/ou senha inválidos.";
		} catch (Exception e) {
			return "Erro ao validar o login: " + e.getMessage();
		}
	}
	
}