package com.hotelium.limbo.generic;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class GenericController<T, ID, D> {
    private final GenericService<T, ID, D> service;

    public GenericController(GenericService<T, ID, D> service) {
        this.service = service;
    }

    @GetMapping
    public List<T> findAll() {
        return service.findAll();
    }

    @PostMapping
    public D create(@RequestBody D requestDTO) {
        return service.create(requestDTO);
    }
    
    @GetMapping("/dto/{id}")
    public Optional<D> findByIdWithDTO(@PathVariable ID id){
        return service.findByIdWithDTO(id);
    }

    @GetMapping("/{id}")
    public Optional<T> findById(@PathVariable ID id){
        return service.findById(id);
    }

    @PatchMapping("/{id}")
    public Optional<D> update(@PathVariable ID id, @RequestBody D body){
        return service.update(id, body);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable ID id){
        service.delete(id);
        return "The entity was successfully deleted";
    }
}
