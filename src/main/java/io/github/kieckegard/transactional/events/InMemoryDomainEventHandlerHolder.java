/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.transactional.events;

import java.util.Map;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class InMemoryDomainEventHandlerHolder implements DomainEventHandlerHolder {

    private final Map<Class<?>, DomainEventHandlerMethod> handlers;
    private final BeanProvider beanProvider;

    public InMemoryDomainEventHandlerHolder(
            DomainEventHandlerScanner scanner,
            BeanProvider beanProvider) {
        
        this.handlers = scanner.scan();
        this.beanProvider = beanProvider;
    }
    
    @Override
    public DomainEventHandler get(DomainEvent domainEvent) {

        final DomainEventHandlerMethod domainEventHandlerMethod
                = this.handlers.get(domainEvent.getClass());
        
        final Object instance = beanProvider.provide(domainEventHandlerMethod.getMethodClass());

        return domainEventHandler;
    }

}
