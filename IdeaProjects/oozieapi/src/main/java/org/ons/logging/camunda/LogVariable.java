package org.ons.logging.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.logging.Logger;

/**
 * Created by StephenBall on 11/07/2017.
 */


public class LogVariable implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger("ONS Log Service");

    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("Oozie Response [" + execution.getVariable("oozieReRunResponse") + "]");


    }

}

