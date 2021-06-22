package com.daofab.assembler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.daofab.assign.TransacController;
import com.daofab.entity.ParentEntity;
import com.daofab.model.Parent;

@Component
public class ParentAssembler 
	extends RepresentationModelAssemblerSupport<ParentEntity, Parent> {

	public ParentAssembler() {
		super(TransacController.class, Parent.class);
	}

	@Override
	public Parent toModel(ParentEntity entity) 
	{
		Parent parent = instantiateModel(entity);
		
		parent.add(linkTo(
				methodOn(TransacController.class)
				.fetchByPage(entity.getId()))
				.withSelfRel());
		
		parent.setId(entity.getId());
		parent.setSender(entity.getSender());
		parent.setReceiver(entity.getReceiver());
		parent.setTotalAmount(entity.getTotalAmount());
		parent.setTotalPaidAmount(entity.getTotalPaidAmount());
		return parent;
	}
	
//	@Override
//	public CollectionModel<Parent> toCollectionModel(Iterable<? extends ParentEntity> entities) 
//	{
//		CollectionModel<Parent> actorModels = super.toCollectionModel(entities);
//		
//		actorModels.add(linkTo(methodOn(TransacController.class).getAllAlbums()).withSelfRel());
//		
//		return actorModels;
//	}
//
//	private List<Parent> toActorModel(List<ParentEntity> actors) {
//		if (actors.isEmpty())
//			return Collections.emptyList();
//
//		return actors.stream()
//				.map(actor -> Parent.builder()
//						.id(actor.getId())
//						.firstName(actor.getFirstName())
//						.lastName(actor.getLastName())
//						.build()
//						.add(linkTo(
//								methodOn(Parent.class)
//								.getActorById(actor.getId()))
//								.withSelfRel()))
//				.collect(Collectors.toList());
//	}
}