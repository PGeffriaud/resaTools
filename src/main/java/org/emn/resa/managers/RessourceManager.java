package org.emn.resa.managers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.emn.resa.entities.Ressource;
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
		Query q = em.createQuery("SELECT t FROM Type t");
		List<Type> list = q.getResultList();
		close();
		return list;
	}

	public static void deleteType(Integer id) {
		init();
		Type t = em.find(Type.class, id);
		if(t != null){
			em.remove(t);
			em.getTransaction().commit();
		}
		/*TODO supprimer les associations avec les ressources
		Et si une ressource n'a que ce type ? supprimer la ressource ? */
		close();
	}

	/**
	 * Ajout d'une ressource avec les types associés
	 * @param name nom de la ressource
	 * @param desc description de la ressource
	 * @param types liste des types associés (ids dans un String[])
	 */
	public static void addRessource(String name, String desc, String[] types) {
		init();
		List<Type> liste = new ArrayList<>();
		for (int i = 0; i < types.length; i++) {
			Type t = em.find(Type.class, Integer.parseInt(types[i]));
			if(t != null) liste.add(t);
		}
		Ressource ress = new Ressource();
		ress.setName(name);
		ress.setDescription(desc);
		ress.setType(liste);
		
		em.persist(ress);
		em.getTransaction().commit();
		
		close();
	}

	public static List<Ressource> getRessourceList() {
		init();
		Query q = em.createQuery("SELECT r FROM Ressource r");
		List<Ressource> list = q.getResultList();
		close();
		return list;
	}
}
