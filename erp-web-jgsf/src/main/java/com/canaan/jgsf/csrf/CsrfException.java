package com.canaan.jgsf.csrf;

import org.apache.shiro.ShiroException;

/**
 * Created by briandemers on 1/23/17.
 */
public class CsrfException extends ShiroException {

	private static final long serialVersionUID = -7678155152256051860L;

	public CsrfException(String message) {
        super(message);
    }

    public CsrfException(String message, Throwable t) {
        super(message, t);
    }
}