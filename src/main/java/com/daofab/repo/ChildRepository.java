package com.daofab.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.daofab.entity.ChildEntity;
@Repository
@Component("childRepo")
public interface ChildRepository extends JpaRepository<ChildEntity,Long>{

}
