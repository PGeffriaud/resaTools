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
	
	public static HttpServletRequest addUser(HttpServletRequest request){
		init();
		HttpServletRequest localRequest = request;
		if(verifyLogin(request.getParameter("login"))){
			User user = new User();
			user.setFirstname(request.getParameter("name"));
			boolean admin = request.getParameter("admin") != null ;
			user.setIsAdmin(admin);
			user.setLogin(request.getParameter("login"));
			user.setPassword(request.getParameter("pwd"));
			user.setMail(request.getParameter("email"));
			user.setName(request.getParameter("firstname"));
			user.setPhone(request.getParameter("phone"));
			em.persist(user);
			em.getTransaction().commit();
		}
		else{
			localRequest.setAttribute("errorMessage", " Un utilisateur existe déjà avec ce login");
		}
		close();
		return localRequest;
	}
	
	private static boolean verifyLogin(String login){
		Query q = em.createQuery("SELECT u FROM User u WHERE u.login = :login");
		q.setParameter("login", login);
		List utilisateurs = q.getResultList();
		if (utilisateurs.isEmpty()) {
			return true;
		} return false;
	}
}
