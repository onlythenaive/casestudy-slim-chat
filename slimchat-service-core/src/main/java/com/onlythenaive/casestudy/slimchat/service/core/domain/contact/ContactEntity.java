package com.onlythenaive.casestudy.slimchat.service.core.domain.contact;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "contacts")
public class ContactEntity {

    @Id
    @Field
    private String id;

    @Field
    private String initiatorId;

    @Field
    private String acceptorId;

    @Field
    private String introduction;

    @Field
    private boolean accepted;

    @Field
    private Instant createdAt;
}
