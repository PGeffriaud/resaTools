package org.emn.resa.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@ManyToMany(mappedBy="typeCollection")
	private Collection<Ressource> ressourceCollection;
	
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
	public Collection<Ressource> getRessourceCollection() {
		return ressourceCollection;
	}
	public void setRessourceCollection(Collection<Ressource> ressourceCollection) {
		this.ressourceCollection = ressourceCollection;
	}
}
