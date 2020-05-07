package com.student.rafKulas;

import com.student.rafKulas.generators.EliminationGenerator;
import com.student.rafKulas.generators.UniformGenerator;
import com.student.rafKulas.generators.LinearGen;
import com.student.rafKulas.generators.RegisterGen;

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
        RegisterGen rg = new RegisterGen.RegisterGenBuilder(69).build();
        rg.next();

        Scanner in = new Scanner(System.in);
        System.out.println("How many discrete numbers do you want to generate?");
        int counter;
        try {
            counter = in.nextInt();
        } catch (Exception e) {
            counter = 10000;
            in.next();
        }
        for(; counter>0; counter--) {
            lg.next();
            double randomDouble = lg.nextDouble();
            System.out.printf("%5f -> %d\n", randomDouble, discreteNumber(randomDouble));
        }
        System.out.println("How many elimination method random numbers do you want to generate?");
        try {
            counter = in.nextInt();
        } catch (Exception e) {
            counter = 10000;
            in.next();
        }
        EliminationGenerator el = new EliminationGenerator.EliminationGenBuilder(lg).setN(counter).build();
        el.generateDuos().printValues();
    }



    static int discreteNumber(double toDiscrete) {
        if(toDiscrete<0) {
            return -1; // wrong input, toDisrete should be in range (0, 1>
        }
        if(toDiscrete==0) {
            return 0;
        }
        if (toDiscrete <= 0.2) {
            return 1;
        }
        if (toDiscrete <= (0.2 + 0.4)) {
            return 2;
        }
        if (toDiscrete <= (0.2 + 0.4 + 0.3)) {
            return 3;
        }
        if (toDiscrete <= (0.2 + 0.4 + 0.3 + 0.1)) {
            return 4;
        }
        return -1; // wrong input
    }
}
