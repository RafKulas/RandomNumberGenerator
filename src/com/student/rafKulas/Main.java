package com.student.rafKulas;

import com.student.rafKulas.generators.LinearGen;
import com.student.rafKulas.generators.RegisterGen;

/**
 * @author Rafał Kulik
 */

public class Main {
    /**
     * Application main method
     * @param args command line arguments
     */

    public static void main(String[] args) {
        int count = 10;
//        LinearGen lG = new LinearGen.LinearGenBuilder(7).build();
//        while((count--)>0) {
//            lG.printNext();
//        }
        RegisterGen rG = new RegisterGen.RegisterGenBuilder(69).build();
        while (count-->0) {
            rG.printNext();
        }

    }
}
