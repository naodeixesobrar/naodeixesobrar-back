package br.com.naodeixesobrar.repository;
 
 
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import br.com.naodeixesobrar.entity.UserEntity;
 

public class UserRepository {
 
	private final EntityManagerFactory entityManagerFactory;
 	private final EntityManager entityManager;
 
	public UserRepository(){
		this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence_naodeixesobrar");
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}
 
	public void save(UserEntity userEntity){
 
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(userEntity);
		this.entityManager.getTransaction().commit();
	}
 
	public void edit(UserEntity userEntity){
 
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(userEntity);
		this.entityManager.getTransaction().commit();
	}
 
	@SuppressWarnings("unchecked")
	public List<UserEntity> all(){
		return this.entityManager.createQuery("SELECT u FROM UserEntity u ORDER BY u.username").getResultList();
	}
 
	public UserEntity getUser(Integer id){
		return this.entityManager.find(UserEntity.class, id);
	}
 
	public void remove(Integer id) throws Exception{
		UserEntity user = this.getUser(id);
		if (user != null) {
			this.entityManager.getTransaction().begin();
			this.entityManager.remove(user);
			this.entityManager.getTransaction().commit();
		} else {
			throw new Exception("Código não encontrado");
		}
	}
	

	public UserEntity login(String username, String password) {
		try {
			return (UserEntity) this.entityManager.createQuery(
				    "SELECT u FROM UserEntity u WHERE u.username = :username and u.password = :password")
				    .setParameter("username", username)
				    .setParameter("password", password)
				    .getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}

}	