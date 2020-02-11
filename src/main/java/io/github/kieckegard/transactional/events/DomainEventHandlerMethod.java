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
public class DomainEventHandlerMethod {
    
    private final Class<?> methodClass;
    private final Method method;
    private final Class<?> eventType;

    public DomainEventHandlerMethod(Method method) {
        this.method = method;
        this.methodClass = method.getDeclaringClass();
        this.eventType = this.getFirstParam();
    }
    
    private Class<?> getFirstParam() {
        return this.method.getParameterTypes()[0];
    }
    
    public void invoke(final Object instance, DomainEvent event) {
        try {
            this.method.invoke(instance, event);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(DomainEventHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Method getMethod() {
        return method;
    }

    public Class<?> getEventType() {
        return eventType;
    }

    public Class<?> getMethodClass() {
        return methodClass;
    }

    @Override
    public String toString() {
        return "DomainEventHandlerMethod{" + "method=" + method + ", eventType=" + eventType + '}';
    }
}
