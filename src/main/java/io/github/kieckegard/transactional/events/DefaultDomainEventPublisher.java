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
public class DefaultDomainEventPublisher implements DomainEventPublisher {

    private DomainEventRepository domainEventRepository;

    public DefaultDomainEventPublisher(DomainEventRepository domainEventRepository) {
        this.domainEventRepository = domainEventRepository;
    }
    
    @Override
    public void publish(DomainEvent domainEvent) {
        this.domainEventRepository.persist(domainEvent);
    }
    
}
