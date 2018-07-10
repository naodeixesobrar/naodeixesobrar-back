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

import br.com.naodeixesobrar.api.CompanyApi;
import br.com.naodeixesobrar.api.UserApi;
import br.com.naodeixesobrar.entity.CompanyEntity;
import br.com.naodeixesobrar.entity.CompanyUserEntity;
import br.com.naodeixesobrar.entity.CompanyUserPKEntity;
import br.com.naodeixesobrar.entity.UserEntity;
import br.com.naodeixesobrar.repository.CompanyRepository;
import br.com.naodeixesobrar.repository.CompanyUserRepository;
import br.com.naodeixesobrar.util.JsonUtil;
 
 
/**
 * Essa classe vai expor os nossos métodos para serem acessados via http
 * 
 * @Path - Caminho para a chamada da classe que vai representar o nosso serviço
 * */
@Path("/company")
public class CompanyController {
 
	private final CompanyRepository companyRepository = new CompanyRepository();
	private final CompanyUserRepository companyUserRepository = new CompanyUserRepository();
 
	/**
	 * @Consumes - determina o formato dos dados que vamos postar
	 * @Produces - determina o formato dos dados que vamos retornar
	 * 
	 * Esse método cadastra uma nova empresa
	 * */
	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/text; charset=UTF-8")
	public Response insert(CompanyApi company){
		CompanyEntity entity = new CompanyEntity();
		try {
			entity.setName(company.getName());
			entity.setCpfcnpj(company.getCpfcnpj());
			entity.setZipcode(company.getZipcode());
			entity.setAdress(company.getAdress());
			entity.setNumberadress(company.getNumberadress());
			entity.setComplement(company.getComplement());
			entity.setDistrict(company.getDistrict());
			entity.setCity(company.getCity());
			entity.setUrl(company.getUrl());
			entity.setEmail(company.getEmail());
			entity.setPhone(company.getPhone());

			companyRepository.save(entity);
	        return Response.status(Response.Status.CREATED).entity(
	        		JsonUtil.getJsonMessageReturn("Empresa cadastrada com sucesso")).build();
		} catch (Exception e) {
	        return Response.status(Response.Status.BAD_REQUEST).entity(
	        		JsonUtil.getJsonMessageReturn("Erro ao cadastrar a empresa: " + e.getMessage())).build();
		}
	}
 
	/**
	 * Essse método altera uma empresa já cadastrada
	 * **/
	@PUT
	@Produces("application/text; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")	
	public Response edit(CompanyApi company){
		CompanyEntity entity = companyRepository.getCompany(company.getId());
		try {
			if (entity == null)
		        return Response.status(Response.Status.NOT_FOUND).entity(
		        		JsonUtil.getJsonMessageReturn("Empresa inexistente")).build();
			entity.setName(company.getName());
			entity.setCpfcnpj(company.getCpfcnpj());
			entity.setZipcode(company.getZipcode());
			entity.setAdress(company.getAdress());
			entity.setNumberadress(company.getNumberadress());
			entity.setComplement(company.getComplement());
			entity.setDistrict(company.getDistrict());
			entity.setCity(company.getCity());
			entity.setUrl(company.getUrl());
			entity.setEmail(company.getEmail());
			entity.setPhone(company.getPhone());

			companyRepository.edit(entity);
	        return Response.status(Response.Status.OK).entity(
	        		JsonUtil.getJsonMessageReturn("Empresa alterada com sucesso")).build();
		} catch (Exception e) {
	        return Response.status(Response.Status.BAD_REQUEST).entity(
	        		JsonUtil.getJsonMessageReturn("Erro ao alterar o empresa: " + e.getMessage())).build();
		}
	}

	/**
	 * Esse método lista todas empresas cadastradas na base
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/all")
	public List<CompanyApi> all(){
		List<CompanyApi> companies =  new ArrayList<CompanyApi>();
		List<CompanyEntity> listEntityCompanys = companyRepository.all();
		for (CompanyEntity entity : listEntityCompanys) {
			companies.add(new CompanyApi(entity.getId(), entity.getName(),entity.getCpfcnpj(),entity.getZipcode(),
					entity.getAdress(), entity.getNumberadress(), entity.getComplement(), entity.getDistrict(),
					entity.getCity(), entity.getUrl(), entity.getEmail(), entity.getPhone()));
		}
		return companies;
	}
 
	/**
	 * Esse método busca uma empresa cadastrada pelo código
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/{id}")
	public Response getCompany(@PathParam("id") Integer id){
		CompanyEntity entity = companyRepository.getCompany(id);
		if(entity != null) {
	        return Response.ok().entity(new CompanyApi(entity.getId(), entity.getName(),entity.getCpfcnpj(),entity.getZipcode(),
					entity.getAdress(), entity.getNumberadress(), entity.getComplement(), entity.getDistrict(),
					entity.getCity(), entity.getUrl(), entity.getEmail(), entity.getPhone())).build();
		} else {
	        return Response.status(Response.Status.NOT_FOUND).entity(
	        		JsonUtil.getJsonMessageReturn("Código de empresa inexistente")).build();
		}
	}
 
	/**
	 * Excluindo uma empresa pelo código
	 * */
	@DELETE
	@Produces("application/text; charset=UTF-8")
	@Path("/{id}")	
	public Response remove(@PathParam("id") Integer id){
		try {
			companyRepository.remove(id);
	        return Response.status(Response.Status.OK).entity(
	        		JsonUtil.getJsonMessageReturn("Empresa excluída com sucesso")).build();
		} catch (NullPointerException e) {
	        return Response.status(Response.Status.NOT_FOUND).entity(
	        		JsonUtil.getJsonMessageReturn("Empresa inexistente")).build();
		} catch (Exception e) {
	        return Response.status(Response.Status.BAD_REQUEST).entity(
	        		JsonUtil.getJsonMessageReturn("Erro ao excluir a empresa: " + e.getMessage())).build();
		}
	}
	
	@PUT
	@Produces("application/text; charset=UTF-8")
	@Path("/{id}/addUser/{userId}")	
	public Response addCompanyUser(@PathParam("id") Integer id, @PathParam("userId") Integer userId){
		CompanyUserEntity companyUserEntity = new CompanyUserEntity();
		try {
			CompanyUserPKEntity companyUserPKEntity = new CompanyUserPKEntity();
			companyUserPKEntity.setCompanyId(id);
			companyUserPKEntity.setUserId(userId);
			companyUserEntity.setCompanyUserPK(companyUserPKEntity);
			companyUserRepository.save(companyUserEntity);
	        return Response.status(Response.Status.OK).entity(
	        		JsonUtil.getJsonMessageReturn("Usuário associado com sucesso")).build();
		} catch (Exception e) {
	        return Response.status(Response.Status.BAD_REQUEST).entity(
	        		JsonUtil.getJsonMessageReturn("Erro ao associar o usuário: " + e.getMessage())).build();
		}
	}

	@DELETE
	@Produces("application/text; charset=UTF-8")
	@Path("/{id}/removeUser/{userId}")	
	public Response removeCompanyUser(@PathParam("id") Integer id, @PathParam("userId") Integer userId){
		CompanyUserEntity companyUserEntity = companyUserRepository.getCompanyUser(id, userId);
		try {
			if (companyUserEntity == null)
		        return Response.status(Response.Status.NOT_FOUND).entity(
		        		JsonUtil.getJsonMessageReturn("Associação Empresa/Usuário inexistente")).build();
			companyUserRepository.remove(companyUserEntity);
	        return Response.status(Response.Status.OK).entity(
	        		JsonUtil.getJsonMessageReturn("Usuário dissociado com sucesso")).build();
		} catch (Exception e) {
	        return Response.status(Response.Status.BAD_REQUEST).entity(
	        		JsonUtil.getJsonMessageReturn("Erro ao dissociar o usuário: " + e.getMessage())).build();
		}
	}

	/**
	 * Esse método lista os usuários vinculados a empresa
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/{id}/allusers")
	public List<UserApi> allUsers(@PathParam("id") Integer id){
		List<UserApi> users =  new ArrayList<UserApi>();
		List<UserEntity> listEntityUsers = companyUserRepository.allUsers(id);
		for (UserEntity entity : listEntityUsers) {
			users.add(new UserApi(entity.getId(), entity.getUsername()));
		}
		return users;
	}

}