package com.example.persistence.entity;

import com.example.domain.api.MessageData;

import javax.persistence.*;

/**
 * author: dmitry.yakubovsky
 * date:   22/09/16
 */
@Entity
public class EntityMessage implements MessageData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "body")
    private String body;

    public EntityMessage(MessageData message) {
        this.body = message.body();
        this.createdAt = message.createdAt();
    }

    public String body() {
        return body;
    }

    public String createdAt() {
        return createdAt;
    }
}
