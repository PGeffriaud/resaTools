package org.emn.resa.managers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.emn.resa.entities.Reservation;
import org.emn.resa.entities.Ressource;
import org.emn.resa.entities.User;
import org.emn.resa.utils.ReservationView;

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

	public static List<ReservationView> getResaList() {
		init();
		Query q = em.createQuery("SELECT r.id, r.dateBegin, r.dateEnd, ress.name, u.login FROM Reservation r, Ressource ress, User u WHERE r.ressource = ress AND r.user = u");
		List<Object[]> l = q.getResultList();
		// mapping
		List<ReservationView> liste = new ArrayList<>();
		for(Object[] o : l) {
			//2015-10-25 00:00:00.0
			Integer id = (Integer) o[0];
			String dateBegin = new SimpleDateFormat("dd/MM/yyyy").format((Timestamp) o[1]);
			String dateEnd = new SimpleDateFormat("dd/MM/yyyy").format((Timestamp) o[2]);
			String ressName = (String) o[3];
			String login = (String) o[4];
			liste.add(new ReservationView(id, ressName, login, dateBegin, dateEnd));
		}
		close();
		return liste;
	}

	
}
