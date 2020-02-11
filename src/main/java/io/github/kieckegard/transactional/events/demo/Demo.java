/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.transactional.events.demo;

import io.github.kieckegard.transactional.events.BeanProvider;
import io.github.kieckegard.transactional.events.DefaultDomainEventPublisher;
import io.github.kieckegard.transactional.events.DomainEventHandlerWorker;
import io.github.kieckegard.transactional.events.DomainEventPublisher;
import io.github.kieckegard.transactional.events.DomainEventRepository;
import io.github.kieckegard.transactional.events.DomainEventResolver;
import io.github.kieckegard.transactional.events.InMemoryDomainEventHandlerHolder;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class Demo {
    
    public static void main(String[] args) {
        
        final DomainEventRepository domainEventRepository = new InMemoryDomainEventRepository();
        final DomainEventPublisher domainEventPublisher = new DefaultDomainEventPublisher(domainEventRepository);
        
        final Map<Class<?>, Object> instances = new HashMap<>();
        
        final BeanProvider beanProvider
                = instances::get;
        
        instances.put(CreateTickedCommandHandler.class, new CreateTickedCommandHandler());
        instances.put(CreatedOrderEventHandler.class, new CreatedOrderEventHandler(domainEventPublisher));
        
        final DomainEventResolver resolver = new DomainEventResolver(new InMemoryDomainEventHandlerHolder(
                beanProvider, "io.github.kieckegard"), domainEventRepository);
        final DomainEventHandlerWorker worker = new DomainEventHandlerWorker(domainEventRepository, resolver);
        
        
        final Order createdOrder = Order.builder()
                .id("1")
                .client("Pedro")
                .status("PENDING")
                .build();
        
        final CreatedOrderEvent createdOrderEvent = 
                new CreatedOrderEvent(createdOrder.getId(), createdOrder);
        
        domainEventPublisher.publish(createdOrderEvent);
        
        worker.work(2000, 2);
    }
}
