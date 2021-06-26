package com.daofab.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.daofab.assign.TransacController;
import com.daofab.entity.ParentEntity;
import com.daofab.model.Parent;

@Component
public class ParentAssembler extends RepresentationModelAssemblerSupport<ParentEntity, Parent> {

	public ParentAssembler() {
		super(TransacController.class, Parent.class);
	}
	
	@Override
	public Parent toModel(ParentEntity entity) {
		Parent parent = instantiateModel(entity);
		
		parent.add(linkTo(methodOn(TransacController.class).get(entity.getId())).withSelfRel());
		parent.setId(entity.getId());
		parent.setReceiver(entity.getReceiver());
		parent.setSender(entity.getReceiver());
		parent.setTotalAmount(entity.getTotalAmount());
		parent.setTotalPaidAmount(entity.getTotalPaidAmount());
		return parent;
	}
	
	@Override
	public CollectionModel<Parent> toCollectionModel(Iterable<? extends ParentEntity> entities) 
	{
		CollectionModel<Parent> collectionModels = super.toCollectionModel(entities);
		
		collectionModels.add(linkTo(methodOn(TransacController.class).fetchAllParent()).withSelfRel());
		
		return collectionModels;
	}
	
}
