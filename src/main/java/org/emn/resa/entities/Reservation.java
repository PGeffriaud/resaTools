package org.emn.resa.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="RESERVATION")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", nullable=false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "RESSOURCE_ID")
	private Reservation reservation;
	
	@Column(name="DATE_BEGIN")	
	private Timestamp dateBegin;
	
	@Column(name="DATE_END")
	private Timestamp dateEnd;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	public Timestamp getDateBegin() {
		return dateBegin;
	}
	public void setDateBegin(Timestamp dateBegin) {
		this.dateBegin = dateBegin;
	}
	public Timestamp getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Timestamp dateEnd) {
		this.dateEnd = dateEnd;
	}
}
