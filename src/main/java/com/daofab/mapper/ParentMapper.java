package com.daofab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.daofab.entity.ParentEntity;
import com.daofab.model.Parent;

@Mapper( componentModel = "spring")
public interface ParentMapper {
    @Mappings({
      @Mapping(target="id", source="entity.id"),
      @Mapping(target="sender", source="entity.sender"),
      @Mapping(target="receiver", source="entity.receiver"),
      @Mapping(target="totalAmount", source="entity.totalAmount")
    })
    ParentEntity parentDTOtoParentEntity(Parent entity);
    @Mappings({
      @Mapping(target="id", source="dto.id"),
      @Mapping(target="sender", source="dto.sender"),
      @Mapping(target="receiver", source="dto.receiver"),
      @Mapping(target="totalAmount", source="dto.totalAmount")
    })
    Parent parentEntitytoParentDto(ParentEntity dto);
    
    //List<Parent> map(List<ParentEntity> employees);
    //List<ParentEntity> map(List<Parent> employees);

}