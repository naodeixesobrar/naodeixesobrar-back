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
import br.com.naodeixesobrar.entity.CompanyEntity;
import br.com.naodeixesobrar.repository.CompanyRepository;
import br.com.naodeixesobrar.util.JsonUtil;
 
 
/**
 * Essa classe vai expor os nossos métodos para serem acessados via http
 * 
 * @Path - Caminho para a chamada da classe que vai representar o nosso serviço
 * */
@Path("/company")
public class CompanyController {
 
	private final CompanyRepository repository = new CompanyRepository();
 
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

			repository.save(entity);
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
		CompanyEntity entity = repository.getCompany(company.getId());
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

			repository.edit(entity);
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
		List<CompanyEntity> listEntityCompanys = repository.all();
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
		CompanyEntity entity = repository.getCompany(id);
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
			repository.remove(id);
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

}