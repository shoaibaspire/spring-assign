package com.daofab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.daofab.entity.ChildEntity;
import com.daofab.model.Child;

@Mapper( componentModel = "spring")
public interface ChildMapper {
    @Mappings({
      @Mapping(target="id", source="entity.id"),
      @Mapping(target="parentId", source="entity.parentId"),
      @Mapping(target="paidAmount", source="entity.paidAmount")
    })
    ChildEntity childDTOtoChildEntity(Child entity);
    @Mappings({
      @Mapping(target="id", source="dto.id"),
      @Mapping(target="parentId", source="dto.parentId"),
      @Mapping(target="paidAmount", source="dto.paidAmount")
    })
    Child childEntitytoChildDto(ChildEntity dto);
    
    //List<Parent> map(List<ParentEntity> employees);
    //List<ChildEntity> map(List<Child> child);

}