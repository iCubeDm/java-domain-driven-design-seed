package com.example.persistence.repository;

import com.example.domain.MessageDomain;
import com.example.domain.repository.MessageRepository;
import com.example.persistence.entity.EntityMessage;
import com.example.persistence.springdata.MessageRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * author: dmitry.yakubovsky
 * date:   22/09/16
 */
@Component
public class JpaMessageRepository implements MessageRepository {

    @Autowired
    private MessageRepositoryJpa messageRepositoryJpa;

    @Transactional
    public void save(MessageDomain messageDomain) {
            messageRepositoryJpa.save(new EntityMessage(messageDomain));
    }
}
