package com.daofab.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.daofab.entity.ChildEntity;
import com.daofab.model.ChildResultDTO;
@Repository
@Component("childRepo")
public interface ChildRepository extends JpaRepository<ChildEntity,Long>{

	@Query("SELECT new com.daofab.model.ChildResultDTO(e.id, d.sender, d.receiver, d.totalAmount,e.paidAmount,e.pId) "
			+ "FROM ParentEntity d, ChildEntity e  WHERE e.pId = d.id ")
	List<ChildResultDTO> fetchEmpDeptDataCrossJoin();
	
	@Query("SELECT new com.daofab.model.ChildResultDTO(e.id, d.sender, d.receiver, d.totalAmount,e.paidAmount,e.pId) "
			+ "FROM ParentEntity d, ChildEntity e WHERE e.pId = d.id and d.id =:id")
	List<ChildResultDTO> fetchEmpDeptDataCrossJoin(Long id);
}
