package com.beust.jcommander;

/**
 * Created by jeremysolarz on 5/23/17.
 */
public class WrappedParameters {
    private Parameters p;

    public WrappedParameters(Parameters p) {
        this.p = p;
    }

    public Parameters getParameters() {
        return p;
    }
}
