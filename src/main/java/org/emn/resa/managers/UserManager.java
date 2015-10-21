package org.emn.resa.managers;

import java.util.List;

import javax.persistence.Query;

import org.emn.resa.entities.User;

public class UserManager extends AbstractObjectManager {

	public static List<User> getUserList() {
		init();
		Query q = em.createQuery("SELECT t FROM User t");
		List<User> list = q.getResultList();
		close();
		return list;
	}
	
	public static void deleteUser(String id) {
		if(id != null){
			init();
			User t = em.find(User.class, Integer.parseInt(id));
			if(t != null){
				em.remove(t);
				em.getTransaction().commit();
			}
			/*TODO supprimer les associations avec les ressources*/
			close();
		}
	}
	
}
