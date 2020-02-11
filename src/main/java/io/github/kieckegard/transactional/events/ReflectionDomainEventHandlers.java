/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.transactional.events;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import static java.util.stream.Collectors.toMap;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class ReflectionDomainEventHandlers implements DomainEventHandlers {

    private final BeanProvider beanProvider;
    private final Map<Class<?>, DomainEventHandler> handlers;

    public ReflectionDomainEventHandlers(BeanProvider beanProvider, String basePkg) {
        this.beanProvider = beanProvider;
        this.handlers = this.scanHandlers(basePkg);
    }

    private Map<Class<?>, DomainEventHandler> scanHandlers(String basePkg) {

        final Map<Class<?>, DomainEventHandler> scannedHandlers = this.getDomainEventHandlerMethods(basePkg)
                .stream()
                .map(method -> {
                    final Object declaringBeanInstance
                            = this.beanProvider.provide(method.getDeclaringClass());
                    return new DomainEventHandler(declaringBeanInstance, method);
                })
                .collect(
                        toMap(
                                DomainEventHandler::getEventType,
                                Function.identity()
                        )
                );

        return scannedHandlers;
    }

    /**
     * TODO: separate in another class, something like
     * DomainEventHandlerScanner
     * @param basePkg
     * @return 
     */
    private Set<Method> getDomainEventHandlerMethods(String basePkg) {
        
        final Reflections reflections = new Reflections(basePkg, new MethodAnnotationsScanner());

        return reflections
                .getMethodsAnnotatedWith(Handler.class);
    }

    @Override
    public DomainEventHandler get(DomainEvent domainEvent) {

        final DomainEventHandler domainEventHandler
                = this.handlers.get(domainEvent.getClass());

        return domainEventHandler;
    }

}
