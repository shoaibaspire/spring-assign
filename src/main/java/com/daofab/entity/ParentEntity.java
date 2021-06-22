package com.daofab.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Table(name = "parent")
public class ParentEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1812575609213911494L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parent_id")
	public Long id;
	public String sender;
	public String receiver;
	public int totalAmount;
	public int totalPaidAmount;
	
	@OneToMany(mappedBy = "mainparent",fetch = FetchType.LAZY)
	@JsonManagedReference
    private Set<ChildEntity> children = new HashSet<>();
	
	public void addChildren(ChildEntity entityToAdd) {
		
		this.children.add(entityToAdd);
		entityToAdd.setMainparent(this);
	}
	
	public void removeChildren(ChildEntity entityToAdd) {
		
		this.children.remove(entityToAdd);
		entityToAdd.setMainparent(null);
	}

	@Override
	public String toString() {
		return "ParentEntity [id=" + id + ", sender=" + sender + ", receiver=" + receiver + ", totalAmount="
				+ totalAmount + ", totalPaidAmount=" + totalPaidAmount + ", children=" + children + "]";
	}
}
