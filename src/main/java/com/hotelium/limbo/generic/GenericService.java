package com.hotelium.limbo.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;

import java.util.Optional;

public class GenericService<T, ID, D, R> {
    private final JpaRepository<T, ID> repository;
    private final GenericMapper<T, D, R> mapper;
    private final Class<T> EntityType;
    private final Class<D> DTOtype;
    private final Class<R> ResponseType;

    public GenericService(JpaRepository<T, ID> repository, GenericMapper<T, D, R> mapper, Class<T> EntityType,
            Class<D> DTOtype, Class<R> ResponseType) {
        this.repository = repository;
        this.mapper = mapper;
        this.EntityType = EntityType;
        this.DTOtype = DTOtype;
        this.ResponseType = ResponseType;
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

    public Optional<R> findById(ID id) {
        Optional<T> found = repository.findById(id);

        if (found.isPresent()) {
            R entity = mapper.entityToDtoFull(found.get(), ResponseType);
            return Optional.of(entity);
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

    public Boolean delete(ID id) {
        Optional<T> entity = repository.findById(id);
        if (entity.isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
