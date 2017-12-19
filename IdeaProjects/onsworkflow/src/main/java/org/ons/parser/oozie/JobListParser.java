package org.ons.parser.oozie;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import org.camunda.spin.SpinList;
import org.camunda.spin.json.SpinJsonNode;

import java.util.Iterator;
import java.util.logging.Logger;

import static org.camunda.spin.Spin.*;

/**
 * Created by StephenBall on 11/07/2017.
 */


public class JobListParser implements JavaDelegate {

    // Element Tags
    private static final String TO_STRING = "toString";
    private static final String START_TIME = "startTime";
    private static final String END_TIME = "endTime";

    private final static Logger LOGGER = Logger.getLogger("Job List Parser");

    private String getStringElement(SpinJsonNode fsjpQuery, String elementTag)
    {
        return fsjpQuery.jsonPath("$." + elementTag).stringValue();
    }

    private void logElements(SpinJsonNode fsjpQuery)
    {
        LOGGER.info(getStringElement(fsjpQuery,TO_STRING));
        LOGGER.info("" + START_TIME + "  [" + getStringElement(fsjpQuery, START_TIME) + "]");
        LOGGER.info("" + END_TIME + "    [" + getStringElement(fsjpQuery, END_TIME) + "]\n");
    }

    public void execute(DelegateExecution execution) throws Exception {

        String jobListResponse = (String) execution.getVariable("jobListResponse");

        SpinList<SpinJsonNode> workflows = JSON(jobListResponse).jsonPath("$.workflows").elementList();

        Iterator<SpinJsonNode> wfItr = workflows.iterator();

        while(wfItr.hasNext())
        {
            SpinJsonNode fsjpQuery = wfItr.next();

            logElements(fsjpQuery);
        }

    }




}
