package com.example.domain.api;

import com.example.domain.Message;

/**
 * author: dmitry.yakubovsky
 * date:   13/08/16
 */
public interface MessageClient {

    void send(Message message);
}
