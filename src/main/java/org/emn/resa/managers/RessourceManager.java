package org.emn.resa.managers;

import java.util.List;

import javax.persistence.Query;

import org.emn.resa.entities.Type;

public class RessourceManager extends AbstractObjectManager {

	/**
	 * Création d'un type qui n'existe pas encore
	 * @param name le nom du type
	 */
	public static void addType(String name){
		init();
		Query q = em.createQuery("SELECT t FROM Type t WHERE t.name = :name");
		q.setParameter("name", name);
		List<Type> listRes = q.getResultList();
		
		if(listRes.size() == 0){
			Type t = new Type();
			t.setName(name);
			em.persist(t);
			em.getTransaction().commit();
		}
		close();
	}

	public static List<Type> getTypeList() {
		init();
		Query q = em.createQuery("SELECT t.name FROM Type t");
		List<Type> list = q.getResultList();
		close();
		return list;
	}
}
