package org.emn.resa.managers;

import javax.persistence.Query;

import org.emn.resa.entities.User;

public class ConnexionManager extends AbstractObjectManager {
	
	/**
	 * Retourne un utilisateur si l'utilisteur existe en base
	 * @param login Le login saisi par l'utilisateur
	 * @param pass Le mot de pass saisi par l'utilisateur
	 * @return User L'utilisateur si il existe en base, null sinon
	 */
	public static User connect(String login, String pass){
		User user = null;
		init();
		Query q = em.createQuery("SELECT u FROM User u WHERE u.login = :login AND u.password = :pass");
		q.setParameter("login", login);
		q.setParameter("pass", pass);
		try{
			user = (User) q.getSingleResult();
			if (login.equalsIgnoreCase(user.getLogin()) && pass.equals(user.getPassword())) {
				System.out.println("Success");
			}
		}
		catch (Exception e){
			System.out.println("Exception : " + e.getMessage());
		}
		finally{
			close();
		}
		return user;
	}

}
