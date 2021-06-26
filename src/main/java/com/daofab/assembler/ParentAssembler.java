package com.daofab.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.daofab.assign.TransacController;
import com.daofab.entity.ParentEntity;
import com.daofab.model.Parent;

@Component
public class ParentAssembler implements RepresentationModelAssembler<ParentEntity, Parent> {

	@Override
	public Parent toModel(ParentEntity entity) {
		Parent parent = new Parent();
		parent.add(linkTo(methodOn(TransacController.class).fetchAllParent()).withSelfRel());
		return parent;
	}
}
