package com.daofab.assign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.daofab.assembler.ParentAssembler;
import com.daofab.entity.ParentEntity;
import com.daofab.model.Parent;
import com.daofab.repo.ParentRepository;

@Service
public class ParentServiceImpl implements ParentService {

	private PagedResourcesAssembler pagedResourcesAssembler;
	private ParentRepository parentRepository;
	private ParentAssembler parentAssembler;

	@Autowired
	public ParentServiceImpl(ParentRepository parentRepository, ParentAssembler parentAssembler,
			PagedResourcesAssembler pagedResourcesAssembler) {
		this.parentRepository = parentRepository;
		this.parentAssembler = parentAssembler;
		this.pagedResourcesAssembler = pagedResourcesAssembler;
	}

	@Override
	public CollectionModel<Parent> findAll(int page, int size, String[] sort, String dir) {
		PageRequest pageRequest;
        Sort.Direction direction;
        if(sort == null) {
            pageRequest = PageRequest.of(page, size);
        }
        else {
            if(dir.equalsIgnoreCase("asc")) direction = Sort.Direction.ASC;
            else direction = Sort.Direction.DESC;
//            for(String eachOne: sort) {
//            	eachOne
//            }
            pageRequest = PageRequest.of(page, size, direction, sort[0]);
        }
        Page<ParentEntity> parentData = parentRepository.findAll(pageRequest);
        if(! CollectionUtils.isEmpty(parentData.getContent())) {
        	return pagedResourcesAssembler.toModel(parentData, parentAssembler);
        }
        return null;	}

}
