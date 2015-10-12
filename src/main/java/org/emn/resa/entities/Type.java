package org.emn.resa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TYPE")
public class Type {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", nullable=false)
	private Integer id;
	
	@Column(name="NAME")
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
