package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

/**
 * Message repository extension implementation.
 *
 * @author Ilia Gubarev
 */
@Repository
public class MessageRepositoryImpl implements MessageRepositoryExtension {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Collection<MessageEntity> getLatestInChat(String observerId) {
        AggregationOperation[] stages = stages(observerId);
        TypedAggregation<MessageEntity> aggregation = aggregation(stages);
        AggregationResults<MessageEntity> results = this.mongoTemplate.aggregate(aggregation, MessageEntity.class);
        return results.getMappedResults();
    }

    private TypedAggregation<MessageEntity> aggregation(AggregationOperation[] stages) {
        return Aggregation.newAggregation(MessageEntity.class, stages);
    }

    private AggregationOperation[] stages(String observerId) {
        Collection<AggregationOperation> stages = new ArrayList<>();
        stages.add(match(observerId));
        stages.add(sort());
        stages.add(group());
        stages.add(replaceRoot());
        return stages.toArray(new AggregationOperation[stages.size()]);
    }

    private AggregationOperation match(String observerId) {
        return Aggregation.match(new Criteria("observerIds").in(observerId));
    }

    private AggregationOperation sort() {
        return Aggregation.sort(Sort.Direction.DESC, "createdAt");
    }

    private AggregationOperation group() {
        return Aggregation.group("chatId").first(Aggregation.ROOT).as("latest");
    }

    private AggregationOperation replaceRoot() {
        return Aggregation.replaceRoot("latest");
    }
}
