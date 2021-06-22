package com.daofab.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Table(name = "child")
public class ChildEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2022776826116704920L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	public Long id;
	
	public Long pId;
	
	public int paidAmount;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = false)
	@JsonBackReference
    private ParentEntity mainparent = new ParentEntity();
	
	public void addParent(ParentEntity parentToAdd) {
		//mainparent.addChildren(this);
		//entityToAdd.setMainparent(this);
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChildEntity )) return false;
        return id != null && id.equals(((ChildEntity) o).getId()) &&
        	   pId != null && pId.equals(((ChildEntity) o).getPId()) &&
        	   paidAmount == (((ChildEntity) o).getPaidAmount());
    }
 
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

	@Override
	public String toString() {
		return "ChildEntity [id=" + id + ", pId=" + pId + ", paidAmount=" + paidAmount + ", mainparent=" + mainparent
				+ "]";
	}
	
}
