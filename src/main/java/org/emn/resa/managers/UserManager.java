package org.emn.resa.managers;

import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.emn.resa.entities.User;

public class UserManager extends AbstractObjectManager {

	/**
	 * Retourne la liste des utilisateurs enregistrés
	 * @return La liste des utilisateurs 
	 */
	public static HashMap<String, User> getUserList() {
		init();
		HashMap<String, User> listUsers = new HashMap<String, User>();
		Query q = em.createQuery("SELECT t FROM User t");
		List<User> list = q.getResultList();
		for(User user : list){
			listUsers.put(String.valueOf(user.getId()), user);
		}
		close();
		return listUsers;
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
	
	/**
	 * Cette méthode permet d'ajouter un utilisateur en base
	 * @param request
	 */
	public static HttpServletRequest addUser(HttpServletRequest request){
		init();
		HttpServletRequest localRequest = request;
		if(verifyLogin(request.getParameter("login"))){
			User user = new User();
			if(request.getParameter("name") != null){
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
		}
		else{
			localRequest.setAttribute("errorMessage", " Un utilisateur existe déjà avec ce login");
		}
		close();
		return localRequest;
	}
	
	/**
	 * Cette méthode permet d'ajouter un modifier un utilisateur en base
	 * @param request
	 */
	public static HttpServletRequest modifyUser(HttpServletRequest request){
		init();
		HttpServletRequest localRequest = request;
		User user = em.find(User.class, Integer.parseInt(request.getParameter("id")));
		boolean verification = true;
		if(user != null){
			if(!user.getLogin().equals(request.getParameter("login"))){
				if(!verifyLogin(request.getParameter("login"))){
					verification = false;
				}
				else{
					localRequest.setAttribute("errorMessage", " Un utilisateur existe déjà avec ce login");
				}
			}else if(verification){
				user.setFirstname(request.getParameter("name"));
				boolean admin = request.getParameter("admin") != null ;
				user.setIsAdmin(admin);
				user.setLogin(request.getParameter("login"));
				user.setPassword(request.getParameter("pwd"));
				user.setMail(request.getParameter("email"));
				user.setName(request.getParameter("firstname"));
				user.setPhone(request.getParameter("phone"));
				System.out.println("commit");
				em.getTransaction().commit();
			}
		}
		close();
		return localRequest;
	}
	
	/**
	 * Cette méthode permet de vérifier s'il existe déjà un utilisateur avec le même login
	 * @param login
	 * @return
	 */
	private static boolean verifyLogin(String login){
		Query q = em.createQuery("SELECT u FROM User u WHERE u.login = :login");
		q.setParameter("login", login);
		List utilisateurs = q.getResultList();
		if (utilisateurs.isEmpty()) {
			return true;
		} return false;
	}
}
