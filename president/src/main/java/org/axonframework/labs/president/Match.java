package org.axonframework.labs.president;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Entity
@Aggregate
public class Match {

    @Id
    @AggregateIdentifier
    private String aggregateIdentifier;

    public Match() {
        // Required by Axon
    }

    public Match(CreateMatchCommand cmd) {
        apply(new MatchCreatedEvent(cmd.getAggregateIdentifier()));
        aggregateIdentifier = cmd.getAggregateIdentifier();
    }

    public void handle(JoinMatchCommand cmd) {
        apply(new MatchJoinedEvent(cmd.getAggregateIdentifier()));
    }

    public void handle(StartMatchCommand cmd) {
        apply(new GameStartedEvent(cmd.getAggregateIdentifier()));
    }

    public void handle(PlayCardsCommand cmd) {
        apply(new CardsPlayedEvent(cmd.getAggregateIdentifier()));
    }

    public void handle(PassCommand cmd) {
        apply(new PlayerPassedEvent(cmd.getAggregateIdentifier()));
    }

}
