package org.emn.resa.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

import org.emn.resa.entities.Reservation;
import org.emn.resa.entities.Ressource;
import org.emn.resa.entities.Type;
import org.emn.resa.entities.User;
import org.h2.util.StringUtils;

public class RessourceManager extends AbstractObjectManager {

	/**
	 * Création d'un type qui n'existe pas encore
	 * @param name le nom du type
	 */
	public static boolean addType(String name){
		boolean addOk = false;
		if(!name.equals("")){
			init();
			Query q = em.createQuery("SELECT t FROM Type t WHERE t.name = :name");
			q.setParameter("name", name);
			List<Type> listRes = q.getResultList();
			
			if(listRes.size() == 0){
				Type t = new Type();
				t.setName(name);
				em.persist(t);
				em.getTransaction().commit();
				addOk = true;
			}
			close();
		}
		return addOk;
	}
	
	/**
	 * Modification d'un type
	 * @param name le nom du type
	 */
	public static boolean modifyType(String name, String id){
		boolean modify = false;
		if(id != null){
			init();
			Type type = em.find(Type.class, Integer.parseInt(id));
			type.setName(name);
			em.getTransaction().commit();
			modify = true;
			close();
		}
		return modify;
	}

	public static HashMap<String, Type> getTypeList() {
		init();
		HashMap<String,Type> listTypes = new HashMap<String,Type>(); 
		Query q = em.createQuery("SELECT t FROM Type t");
		List<Type> list = q.getResultList();
		for(Type type : list){
			listTypes.put(String.valueOf(type.getId()), type);
		}
		close();
		return listTypes;
	}

	/**
	 * Suppression d'un type de ressource avec respect des règles d'intégrités
	 * @param id identifiant du type
	 */
	public static boolean deleteType(Integer id) {
		init();
		boolean updateOk = false;
		boolean ressourceBooked = false;
		Type t = em.find(Type.class, id);
		if(t != null){
			Query q = em.createQuery("SELECT r FROM Ressource r");
			List<Ressource> ressources = q.getResultList();
			for (Ressource r : ressources) {
				Ressource actual = em.find(Ressource.class, r.getId());
				// suppression de la ressource si elle n'a que le type t d'associé 
				//et qu'elle ne se trouve pas dans une réservation
				if(r.getType().size() == 1 && r.getType().contains(t)){
					if(r != null && !isBooked(r)){
						em.remove(actual);
					}
					else ressourceBooked = true;
				}
				// Suppression du type à toutes les ressources associées
				else if(r.getType().contains(t)){
					actual.getType().remove(t);
				}
				
			}
			if(! ressourceBooked){
				
				em.remove(t);
				em.getTransaction().commit();
				updateOk = true;
			}
		}
		
		close();
		return updateOk;
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
	
	/**
	 * Modification d'une ressource
	 * @param id id de la ressource
	 * @param name nom de la ressource
	 * @param desc description de la ressource
	 * @param types liste des types associés (ids dans un String[])
	 */
	public static void modifyRessource(String id, String[] types, String name, String desc) {
		if(id != null){
			init();
			Ressource ress = em.find(Ressource.class, Integer.parseInt(id));
			List<Type> liste = new ArrayList<>();
			for (int i = 0; i < types.length; i++) {
				Type t = em.find(Type.class, Integer.parseInt(types[i]));
				if(t != null) liste.add(t);
			}
			ress.setName(name);
			ress.setDescription(desc);
			ress.setType(liste);
	
			em.getTransaction().commit();
			
			close();
		}
	}

	public static HashMap<String, Ressource> getRessourceList(String nameQuery) {
		init();
		HashMap<String, Ressource> listRessources = new HashMap<String, Ressource>();
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
		for(Ressource ressource : list){
			listRessources.put(String.valueOf(ressource.getId()), ressource);
		}
		close();
		return listRessources;
	}

	public static boolean deleteRessource(int id) {
		init();
		boolean delOk = false;
		Ressource r = em.find(Ressource.class, id);
		if(r != null && !isBooked(r)){
			em.remove(r);
			em.getTransaction().commit();
			delOk = true;
		}
		close();
		return delOk;
	}
	
	/**
	 * Return true si la ressource est ou va être réservé
	 * Pré: EntityManager is open
	 * Post: EntityManager is not closed
	 * @param r la ressource à vérifier
	 * @return
	 */
	private static boolean isBooked(Ressource r){
		Query q = em.createQuery("Select resa from Reservation resa where resa.ressource = :ress");
		q.setParameter("ress", r);
		List<Reservation> liste = q.getResultList();
		return !liste.isEmpty();
	}
}
