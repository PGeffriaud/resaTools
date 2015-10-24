package org.emn.resa.utils;

/**
 * Classe ReservationView permettant de stocker les données d'une réservation à afficher
 *
 */
public class ReservationView {

	private int id;
	private String nomRessource;
	private String login;
	private String dateBegin;
	private String dateEnd;
	

	public ReservationView(){};
	
	public ReservationView(int id, String nomRessource, String login, String dateBegin, String dateEnd) {
		this.id = id;
		this.nomRessource = nomRessource;
		this.login = login;
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomRessource() {
		return nomRessource;
	}

	public void setNomRessource(String nomRessource) {
		this.nomRessource = nomRessource;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(String dateBegin) {
		this.dateBegin = dateBegin;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	
}
