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
	public static boolean addUser(String name, String fstName, String login, String pwd, String mail, String phone, String admin){
		init();
		boolean transacOk = true;
		if(! verifyLogin(login)) transacOk = false;
		else{
			User user = new User();
			user.setFirstname(fstName);
			user.setIsAdmin(admin != null);
			user.setLogin(login);
			user.setPassword(pwd);
			user.setMail(mail);
			user.setName(name);
			user.setPhone(phone);
			em.persist(user);
			em.getTransaction().commit();
		}
		close();
		return transacOk;
	}
	
	/**
	 * Cette méthode permet d'ajouter un modifier un utilisateur en base
	 * @param request
	 */
	public static boolean modifyUser(String id, String name, String fstName, String login, String pwd, String mail, String phone, String admin){
		init();
		boolean verification = false;
		if(id != null){
			User user = em.find(User.class, Integer.parseInt(id));
			verification = true;
			if(user != null){
				if(!user.getLogin().equals(login)){
					verification = verifyLogin(login);
					}
				}
				if(verification){
					user.setFirstname(fstName);
					user.setIsAdmin(admin != null);
					user.setLogin(login);
					user.setPassword(pwd);
					user.setMail(mail);
					user.setName(name);
					user.setPhone(phone);
					em.getTransaction().commit();
				}
			}
		close();
		return verification;
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
		return (utilisateurs.isEmpty());
	}
}
