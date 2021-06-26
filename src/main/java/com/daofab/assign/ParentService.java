package com.daofab.assign;

import org.springframework.hateoas.CollectionModel;

import com.daofab.model.Parent;

public interface ParentService {

    public CollectionModel<Parent> findAll(int page, int size, String[] sort, String dir) ;

}
