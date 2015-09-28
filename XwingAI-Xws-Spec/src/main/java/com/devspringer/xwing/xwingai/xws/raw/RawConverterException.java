package com.devspringer.xwing.xwingai.xws.raw;

import java.io.FileNotFoundException;

/**
 * Created by dspringer on 9/28/2015.
 */
public class RawConverterException extends RuntimeException {
    public RawConverterException(String message) {
        super(message);
    }

    public RawConverterException(String message, Exception e) {
        super(message, e);
    }
}
