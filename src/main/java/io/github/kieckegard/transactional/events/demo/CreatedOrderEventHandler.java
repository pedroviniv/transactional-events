/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.transactional.events.demo;

import io.github.kieckegard.transactional.events.DomainEventPublisher;
import io.github.kieckegard.transactional.events.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class CreatedOrderEventHandler {
    
    private Logger logger = Logger.getLogger(CreatedOrderEventHandler.class.getName());
    
    private DomainEventPublisher eventBus;

    public CreatedOrderEventHandler(DomainEventPublisher eventBus) {
        this.eventBus = eventBus;
    }
    
    /**
     * This method WILL be called. Since this impl
     * delivers at-least-once, this method can be called
     * multiple times if the event processing can't share
     * the same transaction that will update the event as
     * handled.
     * @param event 
     */
    // @Transactional
    @Handler
    public void onCreatedOrderEvent(CreatedOrderEvent event) {
        logger.log(Level.INFO, "Handling {0}", event);
        this.eventBus.publish(new CreateTickedCommand(event.getAggregateId(), event.getPayload()));
    }
}
