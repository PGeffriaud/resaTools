package org.emn.resa.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="RESSOURCE")
public class Ressource {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", nullable=false)
	private Integer id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@ManyToMany(mappedBy = "ressourceCollection")
	private Collection<Type> typeCollection;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Collection<Type> getType() {
		return typeCollection;
	}
	public void setType(Collection<Type> typeCollection) {
		this.typeCollection = typeCollection;
	}
	
}
