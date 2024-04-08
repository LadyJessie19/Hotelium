package com.hotelium.limbo.generic;

import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;

@Mapper(componentModel = "spring")
public class GenericMapper<E, D> {
    private final ModelMapper modelMapper;

    public GenericMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public D entityToDto(E entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public E dtoToEntity(D requestDTO, Class<E> entityClass) {
        return modelMapper.map(requestDTO, entityClass);
    }

    public void updateEntityFromDto(E entity, D dto) {
        modelMapper.map(dto, entity);
    }

    public void dtoToEntityFull(D dto, E entity) {
        modelMapper.map(dto, entity);
    }
}
