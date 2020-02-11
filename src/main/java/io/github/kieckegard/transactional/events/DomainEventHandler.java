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
public class DomainEventHandler {
    
    private Object instance;
    private DomainEventHandlerMethod method;

    public DomainEventHandler(Object instance, DomainEventHandlerMethod method) {
        this.instance = instance;
        this.method = method;
    }
    
    public void handle(final DomainEvent domainEvent) {
        this.method.invoke(this.instance, domainEvent);
    }

    public Object getInstance() {
        return instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }

    public DomainEventHandlerMethod getMethod() {
        return method;
    }

    public void setMethod(DomainEventHandlerMethod method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "DomainEventHandler{" + "instance=" + instance + ", method=" + method + '}';
    }
}
