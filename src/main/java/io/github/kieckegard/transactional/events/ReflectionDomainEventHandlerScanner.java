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
public class ReflectionDomainEventHandlerScanner implements DomainEventHandlerScanner {

    private final Reflections reflections;

    public ReflectionDomainEventHandlerScanner(final String basePkg) {
        this.reflections = new Reflections(basePkg, new MethodAnnotationsScanner());
    }
    
    @Override
    public Map<Class<?>, DomainEventHandlerMethod> scan() {
        
        final Map<Class<?>, DomainEventHandlerMethod> scannedHandlers = this.getDomainEventHandlerMethods()
                .stream()
                .map(method -> {
                    return new DomainEventHandlerMethod(method);
                })
                .collect(
                        toMap(
                                DomainEventHandlerMethod::getEventType,
                                Function.identity()
                        )
                );
        
        return scannedHandlers;
    }

    private Set<Method> getDomainEventHandlerMethods() {

        return reflections
                .getMethodsAnnotatedWith(Handler.class);
    }
    
}
