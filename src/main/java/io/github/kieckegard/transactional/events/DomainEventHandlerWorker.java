/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.transactional.events;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class DomainEventHandlerWorker {
    
    private DomainEventRepository domainEventRepository;
    private DomainEventResolver domainEventResolver;

    public DomainEventHandlerWorker(DomainEventRepository domainEventRepository, DomainEventResolver domainEventResolver) {
        this.domainEventRepository = domainEventRepository;
        this.domainEventResolver = domainEventResolver;
    }
    
    //@Transactional
    public void run(int domainEventsSize) {
        this.domainEventRepository.listUnhandled(domainEventsSize)
                .forEach(this.domainEventResolver::resolve);
    }
    
    public void work(final int msRate, int domainEventsSize) {
        while(true) {
            try {
                Thread.sleep(msRate);
                this.run(domainEventsSize);
            } catch (InterruptedException ex) {
                Logger.getLogger(DomainEventHandlerWorker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
