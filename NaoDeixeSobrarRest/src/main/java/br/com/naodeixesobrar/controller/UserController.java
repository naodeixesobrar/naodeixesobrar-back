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
import javax.ws.rs.core.Response;

import br.com.naodeixesobrar.api.UserApi;
import br.com.naodeixesobrar.entity.UserEntity;
import br.com.naodeixesobrar.jwt.JWTUtil;
import br.com.naodeixesobrar.repository.UserRepository;
import br.com.naodeixesobrar.util.JsonUtil;
 
/**
 * Essa classe vai expor os nossos métodos para serem acessados via http
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
	 * Esse método cadastra um novo usuário
	 * */
	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/text; charset=UTF-8")
	public Response insert(UserApi user){
		UserEntity entity = new UserEntity();
		try {
			entity.setUsername(user.getUsername());
			entity.setPassword(user.getPassword());
			repository.save(entity);
	        return Response.status(Response.Status.CREATED).entity(
	        		JsonUtil.getJsonMessageReturn("Usuário cadastrado com sucesso")).build();
		} catch (Exception e) {
	        return Response.status(Response.Status.BAD_REQUEST).entity(
	        		JsonUtil.getJsonMessageReturn("Erro ao cadastrar o usuário: " + e.getMessage())).build();
		}
	}
 
	/**
	 * Essse método altera um usuário já cadastrado
	 * **/
	@PUT
	@Produces("application/text; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")	
	public Response edit(UserApi user){
		UserEntity entity = repository.getUser(user.getId());
		try {
			if (entity == null)
		        return Response.status(Response.Status.NOT_FOUND).entity(
		        		JsonUtil.getJsonMessageReturn("Usuário inexistente")).build();
			entity.setUsername(user.getUsername());
			entity.setPassword(user.getPassword());
			repository.edit(entity);
	        return Response.status(Response.Status.OK).entity(
	        		JsonUtil.getJsonMessageReturn("Usuário alterado com sucesso")).build();
		} catch (Exception e) {
	        return Response.status(Response.Status.BAD_REQUEST).entity(
	        		JsonUtil.getJsonMessageReturn("Erro ao alterar o usuário: " + e.getMessage())).build();
		}
	}

	/**
	 * Esse método lista todos os usuários cadastrados na base
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/all")
	public List<UserApi> all(){
		List<UserApi> users =  new ArrayList<UserApi>();
		List<UserEntity> listEntityUsers = repository.all();
		for (UserEntity entity : listEntityUsers) {
			users.add(new UserApi(entity.getId(), entity.getUsername()));
		}
		return users;
	}
 
	/**
	 * Esse método busca um usuário cadastrado pelo código
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/{id}")
	public Response getUser(@PathParam("id") Integer id){
		UserEntity entity = repository.getUser(id);
		if(entity != null) {
	        return Response.ok().entity(new UserApi(entity.getId(), entity.getUsername())).build();
		} else {
	        return Response.status(Response.Status.NOT_FOUND).entity(
	        		JsonUtil.getJsonMessageReturn("Código de usuário inexistente")).build();
		}
	}
 
	/**
	 * Excluindo um usuário pelo código
	 * */
	@DELETE
	@Produces("application/text; charset=UTF-8")
	@Path("/{id}")	
	public Response remove(@PathParam("id") Integer id){
		try {
			repository.remove(id);
	        return Response.status(Response.Status.OK).entity(
	        		JsonUtil.getJsonMessageReturn("Usuário excluído com sucesso")).build();
		} catch (NullPointerException e) {
	        return Response.status(Response.Status.NOT_FOUND).entity(
	        		JsonUtil.getJsonMessageReturn("Usuário inexistente")).build();
		} catch (Exception e) {
	        return Response.status(Response.Status.BAD_REQUEST).entity(
	        		JsonUtil.getJsonMessageReturn("Erro ao excluir o usuário: " + e.getMessage())).build();
		}
	}

	/**
	 * Login validate
	 * */
	@POST
	@Produces("application/json; charset=UTF-8")
	@Path("/login")	
	public Response login(UserApi user){
		UserEntity authenthicatedUser = repository.login(user.getUsername(), user.getPassword());
	    if(authenthicatedUser != null){
	        String token = JWTUtil.create(user.getUsername());
	        UserApi userApi = new UserApi(authenthicatedUser.getId(),
	        		authenthicatedUser.getUsername(),
	        		token);
	        return Response.ok().entity(userApi).build();
	    }else{
	        return Response.status(Response.Status.FORBIDDEN).entity(
	        		JsonUtil.getJsonMessageReturn("Usuário e/ou Senha inválidos")).build();
	    }
	}
	
}