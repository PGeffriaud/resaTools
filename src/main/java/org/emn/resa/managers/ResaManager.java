package org.emn.resa.managers;

import java.util.Date;

import org.emn.resa.entities.Reservation;
import org.emn.resa.entities.Ressource;
import org.emn.resa.entities.User;

public class ResaManager extends AbstractObjectManager {

	public static void addReservation(int idUser, int idRess, Date from, Date to) {
		init();
		User u = em.find(User.class, idUser);
		Ressource r = em.find(Ressource.class, idRess);
		if(u != null && r != null){
			Reservation resa = new Reservation();
			resa.setUser(u);
			resa.setRessource(r);
			resa.setDateBegin(from);
			resa.setDateEnd(to);
			
			if(checkResa(resa)){
				em.persist(resa);
				em.getTransaction().commit();
			}
			
		}
		close();
	}

	/**
	 * Vérifie que la réservation est possible
	 * @param resa
	 * @return
	 */
	private static boolean checkResa(Reservation resa) {
		// TODO checkResa
		return true;
	}

	
}
