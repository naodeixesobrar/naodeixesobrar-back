package br.com.naodeixesobrar.repository;
 
 
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.naodeixesobrar.entity.CompanyEntity;
 

public class CompanyRepository {
 
	private final EntityManagerFactory entityManagerFactory;
 	private final EntityManager entityManager;
 
	public CompanyRepository(){
		this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence_naodeixesobrar");
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}
 
	public void save(CompanyEntity companyEntity){
 		this.entityManager.getTransaction().begin();
		this.entityManager.persist(companyEntity);
		this.entityManager.getTransaction().commit();
	}
 
	public void edit(CompanyEntity companyEntity){
 		this.entityManager.getTransaction().begin();
		this.entityManager.merge(companyEntity);
		this.entityManager.getTransaction().commit();
	}
 
	@SuppressWarnings("unchecked")
	public List<CompanyEntity> all(){
		return this.entityManager.createQuery("SELECT c FROM CompanyEntity c ORDER BY c.name").getResultList();
	}
 
	public CompanyEntity getCompany(Integer id){
		return this.entityManager.find(CompanyEntity.class, id);
	}
 
	public void remove(Integer id) throws Exception{
		CompanyEntity company = this.getCompany(id);
		if (company != null) {
			this.entityManager.getTransaction().begin();
			this.entityManager.remove(company);
			this.entityManager.getTransaction().commit();
		} else {
			throw new NullPointerException();
		}
	}
	
}	