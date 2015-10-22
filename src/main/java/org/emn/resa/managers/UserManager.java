package org.emn.resa.managers;

import java.util.List;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.emn.resa.entities.User;

public class UserManager extends AbstractObjectManager {

	/**
	 * Retourne la liste des utilisateurs enregistrés
	 * @return La liste des utilisateurs 
	 */
	public static List<User> getUserList() {
		init();
		Query q = em.createQuery("SELECT t FROM User t");
		List<User> list = q.getResultList();
		close();
		return list;
	}
	
	/**
	 * Cette méthode permet de supprimer un utilisateur en fonction de son ID
	 * @param id
	 */
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
	
	public static void addUser(HttpServletRequest request){
		init();
		User user = new User();
		user.setFirstname(request.getParameter("name"));
		user.setIsAdmin(Boolean.valueOf(request.getParameter("admin")));
		user.setLogin(request.getParameter("login"));
		user.setMail(request.getParameter("mail"));
		user.setName(request.getParameter("firstname"));
		user.setPhone(request.getParameter("phone"));
		em.persist(user);
		close();
	}
}
