package org.emn.resa.managers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.emn.resa.entities.Ressource;
import org.emn.resa.entities.Type;
import org.h2.util.StringUtils;

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

	/**
	 * Suppression d'un type de ressource avec respect des règles d'intégrités
	 * @param id identifiant du type
	 */
	public static void deleteType(Integer id) {
		init();
		Type t = em.find(Type.class, id);
		if(t != null){
			Query q = em.createQuery("SELECT r FROM Ressource r");
			List<Ressource> ressources = q.getResultList();
			for (Ressource r : ressources) {
				Ressource actual = em.find(Ressource.class, r.getId());
				// suppression de la ressource si elle n'a que le type t d'associé
				if(r.getType().size() == 1 && r.getType().contains(t)){
					if(r != null) em.remove(actual);
				}
				// Suppression du type à toutes les ressources associées
				else if(r.getType().contains(t)){
					actual.getType().remove(t);
				}
				
			}
			em.remove(t);
			em.getTransaction().commit();
		}
		
		/*TODO contrôle d'intégrité: ne pas supprimer le type si
		 * une ressource va être supprimée
		 * et que cette ressource va être/est réservée*/
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

	public static List<Ressource> getRessourceList(String nameQuery) {
		init();
		String query = "SELECT r FROM Ressource r";
		Query q;
		if(! StringUtils.isNullOrEmpty(nameQuery)){
			query += " WHERE r.name LIKE :name";
			q = em.createQuery(query);
			q.setParameter("name", "%"+nameQuery+"%");
		}
		else{
			q = em.createQuery(query);
		}
		List<Ressource> list = q.getResultList();
		close();
		return list;
	}

	public static void deleteRessource(int id) {
		init();
		Ressource r = em.find(Ressource.class, id);
		if(r != null){
			em.remove(r);
			em.getTransaction().commit();
		}
		close();
	}
}
