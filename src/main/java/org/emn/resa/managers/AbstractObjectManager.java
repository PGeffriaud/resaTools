package org.emn.resa.managers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractObjectManager {
	private static EntityManagerFactory emf;
	protected static EntityManager em;
	
	protected static void init(){
		emf = Persistence.createEntityManagerFactory("resaTools");
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}
	
	protected static void close(){
		emf.close();
		em.close();
	}
}
