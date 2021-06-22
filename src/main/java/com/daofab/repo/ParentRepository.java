package com.daofab.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daofab.entity.ParentEntity;
@Repository
public interface ParentRepository extends JpaRepository<ParentEntity,Long>{

}
