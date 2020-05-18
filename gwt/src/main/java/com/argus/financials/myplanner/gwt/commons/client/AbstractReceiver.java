package com.argus.financials.myplanner.gwt.commons.client;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.ServerFailure;
import com.google.gwt.requestfactory.shared.Violation;

/**
 * 
 * @author shibaevv
 *
 * @param <T> value type
 */
public abstract class AbstractReceiver<T> extends Receiver<T>
{
    /** logger */
    private static final Logger LOG = Logger.getLogger(AbstractReceiver.class.getName());

    /* (non-Javadoc)
     * @see com.google.gwt.requestfactory.shared.Receiver#onFailure(com.google.gwt.requestfactory.shared.ServerFailure)
     */
    @Override
    public void onFailure(ServerFailure error)
    {
        LOG.log(Level.SEVERE, getClass() + "::onFailure: " + error.getMessage() + '\n' + error.getStackTraceString());
        super.onFailure(error);
    }

    /* (non-Javadoc)
     * @see com.google.gwt.requestfactory.shared.Receiver#onViolation(java.util.Set)
     */
    @Override
    public void onViolation(Set<Violation> errors)
    {
        super.onViolation(errors);
    }

}