package com.student.rafKulas;

import com.student.rafKulas.generators.*;

import java.util.Scanner;

/**
 * @author Rafał Kulik
 */

public class Main {
    /**
     * Application main method with user friendly interface
     * @param args command line arguments
     */
    public static void main(String[] args) {
        LinearGen lg = new LinearGen.LinearGenBuilder(7).build();
        lg.next();

        Scanner in = new Scanner(System.in);
        System.out.println("How many reversing the distribution function method numbers do you want to generate?");
        int counter;
        try {
            counter = in.nextInt();
        } catch (Exception e) {
            counter = 10000;
            in.next();
        }
        QuantileFunctionGenerator qfg = new QuantileFunctionGenerator.QuantileFunGenBuilder(lg).setN(counter).build();
        qfg.
                generateNumbers().
                invertDistributionForAll().
                printValues();
        System.out.println("How many elimination method random numbers do you want to generate?");
        try {
            counter = in.nextInt();
        } catch (Exception e) {
            counter = 10000;
            in.next();
        }
        EliminationGenerator el = new EliminationGenerator.EliminationGenBuilder(lg).setN(counter).build();
        el.
                generateDuos().
                printValues();
    }
}
