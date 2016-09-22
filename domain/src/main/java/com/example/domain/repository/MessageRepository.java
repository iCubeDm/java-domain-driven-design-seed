package com.example.domain.repository;

import com.example.domain.MessageDomain;

/**
 * author: dmitry.yakubovsky
 * date:   13/08/16
 */
public interface MessageRepository {

    void save(MessageDomain messageDomain);
}
