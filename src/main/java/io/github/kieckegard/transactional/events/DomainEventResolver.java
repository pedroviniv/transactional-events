/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.transactional.events;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class DomainEventResolver {
    
    private DomainEventHandlerHolder domainEventHandlerHolder;
    private DomainEventRepository domainEventRepository;

    public DomainEventResolver(DomainEventHandlerHolder domainEventHandlers, DomainEventRepository domainEventRepository) {
        this.domainEventHandlerHolder = domainEventHandlers;
        this.domainEventRepository = domainEventRepository;
    }
    
    //@Transactional
    public void resolve(DomainEvent domainEvent) {
        
        final DomainEventHandler domainEventHandler = 
                this.domainEventHandlerHolder.get(domainEvent);
        
        domainEventHandler.handle(domainEvent);
        this.domainEventRepository.markAsHandled(domainEvent.getId());
    }
}
