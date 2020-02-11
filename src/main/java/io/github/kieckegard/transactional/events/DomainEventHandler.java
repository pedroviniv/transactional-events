/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.transactional.events;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class DomainEventHandler {
    
    private Object instance;
    private Method method;
    
    public Class<?> getEventType() {
        final Class<?>[] parameterTypes = this.method.getParameterTypes();
        return parameterTypes[0];
    }
    
    public DomainEventHandler(Object instance, Method method) {
        this.instance = instance;
        this.method = method;
    }
    
    public void handle(DomainEvent domainEvent) {
        try {
            this.method.invoke(this.instance, domainEvent);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DomainEventHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(DomainEventHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(DomainEventHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
