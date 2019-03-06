package com.mfsa.product;

import com.mfsa.product.controller.MyStatApp;

/**
 * Starter class for mfsalogsextract application.
 *
 * @author Heydrich
 */
public class InitApp {

    /**
     * Application entry point.
     *
     * @param args application command line arguments
     */
    public static void main(String[] args) {
        MyStatApp msa = new MyStatApp();
        msa.run(args);
    }
}
