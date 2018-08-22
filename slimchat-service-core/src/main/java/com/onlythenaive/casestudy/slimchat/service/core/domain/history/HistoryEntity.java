package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.lang.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "histories")
public class HistoryEntity {

    @Id
    private String id;

    @Field
    private String ownerId;

    @Field
    private boolean personal;

    @Field
    @Nullable
    private String referencedUserId;

    @Field
    @Nullable
    private String referencedGroupId;

    @Field
    private Collection<String> messageIds;
}
