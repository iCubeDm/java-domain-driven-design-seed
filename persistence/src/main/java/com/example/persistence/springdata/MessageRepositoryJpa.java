package com.example.persistence.springdata;

import com.example.persistence.entity.EntityMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface MessageRepositoryJpa extends CrudRepository<EntityMessage, Long> {

}
