package com.student.rafKulas.generators;

import java.util.ArrayList;

public class QuantileFunctionGenerator extends AnyDistGenerator{
    private final double[] distribution;
    private final long[] Y;
    private final double[] cumSum;


    public QuantileFunctionGenerator(QuantileFunGenBuilder qfgb) {
        this.distribution = qfgb.distribution;
        this.Y = qfgb.Y;
        this.n = qfgb.n;
        this.gen = qfgb.gen;
        cumSum = new double[distribution.length];
        double sum=0;
        for(int i = 0; i<distribution.length; ++i) {
            sum+=distribution[i];
            cumSum[i] = sum;
        }
        this.list = new ArrayList<>();
    }

    public QuantileFunctionGenerator generateNumbers() {
        for(int index = 0; index<n; index++) {
            gen.next();
            list.add(gen.nextDouble());
            list.add(0.0); // making space for reversed dist
            gen.next();
        }
        return this;
    }

    public QuantileFunctionGenerator invertDistributionForAll() {
        for(int index = 0; index<n; index++) {
            list.set(index*2+1, invertDist(list.get(index*2).doubleValue()));
        }
        return this;
    }

    public long invertDist(double toInv) {
        if(toInv==0) {
            return 0;
        }
        for(int index = 0; index<cumSum.length; ++index) {
            if(toInv <= cumSum[index]) {
                return Y[index];
            }
        }
        return -1; // wrong input
    }

    @Override
    public void printOneValue(int index) {
        System.out.printf("%5f -> %4d\n", list.get(index).doubleValue(), list.get(index+1).longValue());
        gen.next();
    }

    @Override
    public void printValues() {
        for(int index = 0; index<n; ++index) {
            printOneValue(index*2);
        }
    }

    public static class QuantileFunGenBuilder {
        private double[] distribution;
        private long[] Y;
        private int n;
        private final UniformGenerator gen;

        public QuantileFunGenBuilder(UniformGenerator ug) {
            distribution = new double[]{0.2, 0.4, 0.3, 0.1};
            Y = new long[]{1L, 2L, 3L, 4L};
            n = 10000;
            this.gen = ug;
        }

        public QuantileFunGenBuilder setDistribution(double[] distribution) {
            this.distribution = distribution;
            return this;
        }

        public QuantileFunGenBuilder setY(long[] y) {
            Y = y;
            return this;
        }

        public QuantileFunGenBuilder setN(int n) {
            this.n = n;
            return this;
        }

        public QuantileFunctionGenerator build() {
            return new QuantileFunctionGenerator(this);
        }
    }
}
