package com.student.rafKulas.generators;

public class LinearGen extends Generator {

    private int a;
    private int c;
    private long M;

    /**
     * Constructor of the LinearGen(erator)
     * Linear random number generator uses the following formula:
     * x_(n+1) = (a*x_n+c)%M
     * First element is equal to seed
     * @param lGB is a LinearGenBuilder which help setting fields
     */

    public LinearGen(LinearGenBuilder lGB) {
        super(lGB.seed);
        this.x = seed;
        this.a = lGB.a;
        this.c = lGB.c;
        this.M = lGB.M;
    }

    @Override
    public long next() {
        long ret = x;
        x = (a*x+c)%M;
        return ret;
    }

    public static class LinearGenBuilder{

        private int a;
        private int c;
        private long M;
        private int seed;

        /**
         * Default values in linear generator formula
         * @param seed helps evaluate other 'random' numbers
         */

        public LinearGenBuilder(int seed) {
            a = 69069;
            c = 1;
            M = 0xFFFFFFFFL;
            this.seed = seed;
        }

        public LinearGenBuilder setA(int a) {
            this.a = a;
            return this;
        }

        public LinearGenBuilder setC(int c) {
            this.c = c;
            return this;
        }

        public LinearGenBuilder setM(long M) {
            this.M = M;
            return this;
        }

        public LinearGen build() {
            return new LinearGen(this);
        }
    }
}
