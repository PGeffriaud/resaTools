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

	public static boolean addReservation(int idUser, int idRess, Date from, Date to) {
		init();
		boolean addOk = false;
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
				addOk = true;
			}
			
		}
		close();
		return addOk;
	}

	/**
	 * Vérifie que la réservation est possible
	 * Pré-requis: EntityManager is opened
	 * Post: EntityManager is not closed
	 * @param resa Reservation a ajouter si les règles sont ok
	 * @return True si la réservation peut etre ajoutée
	 */
	private static boolean checkResa(Reservation myResa) {
		// Les dates ne sont pas nulles
		Date myResaBegin = myResa.getDateBegin();
		Date myResaEnd = myResa.getDateEnd();
		if(myResaBegin == null || myResaEnd == null) return false;
		
		// DateDebut < DateFin
		if(myResaBegin.compareTo(myResaEnd) >= 0) return false;
		
		// Selection des reservations dont l'intersection de temps est non null
		Query q = em.createQuery("Select r from Reservation r where r.dateBegin <= :dateEnd AND r.dateEnd >= :dateBegin", Reservation.class);
		q.setParameter("dateEnd", myResaEnd);
		q.setParameter("dateBegin", myResaBegin);
		return q.getResultList().size() == 0;
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
