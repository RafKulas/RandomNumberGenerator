package com.student.rafKulas;

import com.student.rafKulas.generators.Generator;
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
        System.out.println("How many double random numbers do you want to generate?");
        try {
            counter = in.nextInt();
        } catch (Exception e) {
            counter = 10000;
            in.next();
        }
        System.out.printf("\n%9s%3s, %3s\n","", "U1", "U2");
        for(int index = 1; counter>0; counter--, index++) {
            long[] duo = randomDouble(rg);
            rg.next();
            if(duo[0]==-1 && duo[1]==-1) {
                continue;
            }
            System.out.printf("%7d. %3d, %3d\n",index, duo[0], duo[1]);
        }
    }

    // f(x) = 2x-20
    // U2 € <0, d> -> 0  <= U2 <= 50
    // U1 € <a, b> -> 30 <= U1 <= 100
    // a = 30, b = 100, d = 50
    // U2 < F(U1)
    static long[] randomDouble(Generator gen) {
        long U2 = gen.next()%50;
        gen.next();
        long U1 = gen.next()%(80-30)+30;
        if(U2 < (U1-30)) {
            return new long[]{U1, U2};
        }
        return new long[]{-1, -1}; // shouldn't be printed
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
