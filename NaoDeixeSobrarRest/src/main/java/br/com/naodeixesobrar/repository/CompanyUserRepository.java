package br.com.naodeixesobrar.repository;
 
 
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import br.com.naodeixesobrar.entity.CompanyUserEntity;
import br.com.naodeixesobrar.entity.UserEntity;
 

public class CompanyUserRepository {
 
	private final EntityManagerFactory entityManagerFactory;
 	private final EntityManager entityManager;
 
	public CompanyUserRepository(){
		this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence_naodeixesobrar");
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}
 
	public void save(CompanyUserEntity companyUserEntity){
 		this.entityManager.getTransaction().begin();
		this.entityManager.persist(companyUserEntity);
		this.entityManager.getTransaction().commit();
	}
 
	public void remove(CompanyUserEntity companyUserEntity){
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(companyUserEntity);
		this.entityManager.getTransaction().commit();
	}
	
	public CompanyUserEntity getCompanyUser(Integer companyId, Integer userId) {
		try {
			return (CompanyUserEntity) this.entityManager.createQuery("SELECT cmpusr FROM CompanyUserEntity cmpusr where companyid = :companyId and userid = :userId").
					setParameter("companyId", companyId).
					setParameter("userId", userId).
					getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<UserEntity> allUsers(Integer companyId){
		return this.entityManager.createQuery("SELECT u FROM UserEntity u INNER JOIN CompanyUserEntity cmpusr ON cmpusr.companyUserPK.userId = u.id WHERE cmpusr.companyUserPK.companyId = :companyId").
					setParameter("companyId", companyId).
					getResultList();
	}
	
}	