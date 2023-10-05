package com.hotelium.limbo.generic;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class GenericService<T, ID, D> {
    private final JpaRepository<T, ID> repository;
    private final GenericMapper<T, D> mapper;
    private final Class<T> EntityType;
    private final Class<D> DTOtype;

    public GenericService(JpaRepository<T, ID> repository, GenericMapper<T, D> mapper, Class<T> EntityType, Class<D> DTOtype) {
        this.repository = repository;
        this.mapper = mapper;
        this.EntityType = EntityType;
        this.DTOtype = DTOtype;
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public D create(D requestDTO) {
        var entity = mapper.dtoToEntity(requestDTO, EntityType);
        var savedEntity = this.repository.save(entity);
        var savedEntityDTO = mapper.entityToDto(savedEntity, DTOtype);
        return savedEntityDTO;
    }

    public Optional<D> findByIdWithDTO(ID id) {
        Optional<T> entityOptional = repository.findById(id);

        if (entityOptional.isPresent()) {
            T entity = entityOptional.get();
            D foundEntity = mapper.entityToDto(entity, DTOtype);
            return Optional.of(foundEntity);
        } else {
            return Optional.empty();
        }
    }

    public Optional<T> findById(ID id) {
        Optional<T> entity = repository.findById(id);
        if (entity.isPresent()) {
            return entity;
        }
        return null;
    }

    @Transactional
    public Optional<D> update(ID id, D requestDTO) {
        if (id == null || requestDTO == null) {
            throw new IllegalArgumentException("ID and requestDTO must not be null");
        }
        try {
            Optional<T> existingEntityOptional = repository.findById(id);

            if (existingEntityOptional.isPresent()) {
                T existingEntity = existingEntityOptional.get();

                T updatedEntity = repository.save(existingEntity);

                return Optional.of(mapper.entityToDto(updatedEntity, DTOtype));
            }
        } catch (Exception ex) {
            throw new RuntimeException("Failed to update entity with ID " + id, ex);
        }
        return Optional.empty();
    }

    /*
     * 
     * @Transactional(rollbackFor = Exception.class)
     * public ResponseDTO update(Integer id, RequestDTO requestDTO) {
     * Optional<Entity> dbOptional = repository.findById(id);
     * if (dbOptional.isEmpty()) {
     * throw new EntityException("Entity not found.");
     * }
     * 
     * Entity entity = dbOptional.get();
     * this.overridePropertyOnUpdateForDTO(requestDTO);
     * genericRestMapper.updateEntityFromDto(requestDTO, entity);
     * this.overridePropertyOnUpdateForEntity(entity);
     * repository.saveAndFlush(entity);
     * return genericRestMapper.toDTO(entity);
     * }
     * 
     */

    public void delete(ID id) {
        repository.deleteById(id);
        return;
    }
}
