package com.hotelium.limbo.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.Optional;

public class GenericService<T, ID, D> {
    private final JpaRepository<T, ID> repository;
    private final GenericMapper<T, D> mapper;
    private final Class<T> EntityType;
    private final Class<D> DTOtype;

    public GenericService(JpaRepository<T, ID> repository, GenericMapper<T, D> mapper, Class<T> EntityType,
            Class<D> DTOtype) {
        this.repository = repository;
        this.mapper = mapper;
        this.EntityType = EntityType;
        this.DTOtype = DTOtype;
    }

    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public D create(D requestDTO) {
        var entity = mapper.dtoToEntity(requestDTO, EntityType);
        var savedEntity = this.repository.save(entity);
        var savedEntityDTO = mapper.entityToDto(savedEntity, DTOtype);
        return savedEntityDTO;
    }

    public Optional<T> findById(ID id) {
        Optional<T> entity = repository.findById(id);
        if (entity.isPresent()) {
            return entity;
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<D> update(ID id, D requestDTO) {
        if (id == null || requestDTO == null) {
            throw new IllegalArgumentException("ID and requestDTO must not be null");
        }
        try {
            Optional<T> existingEntityOptional = repository.findById(id);

            if (existingEntityOptional.isPresent()) {
                mapper.dtoToEntityFull(requestDTO, existingEntityOptional.get());

                T updatedEntity = repository.saveAndFlush(existingEntityOptional.get());

                return Optional.of(mapper.entityToDto(updatedEntity, DTOtype));
            }
        } catch (Exception ex) {
            throw new RuntimeException("Failed to update entity with ID " + id, ex);
        }
        return Optional.empty();
    }

    public void delete(ID id) {
        Optional<T> entity = repository.findById(id);
        if (entity.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException(EntityType.getSimpleName() + " not found with id " + id);
        }
    }
}
