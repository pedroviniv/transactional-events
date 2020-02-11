/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.transactional.events.demo;

import io.github.kieckegard.transactional.events.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class CreateTickedCommandHandler {

    private static final Logger LOG = Logger.getLogger(CreateTickedCommandHandler.class.getName());
    
    @Handler
    public void on(CreateTickedCommand command) {
      LOG.log(Level.INFO, "Handling command {0}", command);
    }
}
