package com.student.rafKulas.generators;

public class RegisterGen extends UniformGenerator {

    private final int p;
    private final int q;
    private long x;

    /**
     * Constructor of the RegisterGen(erator):
     * It uses formula that every bit on i-th position is evaluated as:
     * b_i = b_(i-p) xor b_(i-q)
     * First element have to be evaluated as every element
     * @param rGB builder of Register Generator
     */

    public RegisterGen(RegisterGenBuilder rGB) {
        super(rGB.seed);
        this.p = rGB.p;
        this.q = rGB.q;
        this.range = 0xFFFFFFFFL; // 2^31-1

        x = seed;

        for(long b = 7; b<32; ++b) {
            long b_p = ((1<<(b-p))&x) >> (b-p);
            long b_q = ((1<<(b-q))&x) >> (b-q);
            x += (b_p^b_q)<<b;
        }
    }

    @Override
    public long next() {
        long ret = x;
        x = x>>(32-7);
        for(long b = 7; b<32; ++b) {
            long b_p = ((1<<(b-p))&x) >> (b-p);
            long b_q = ((1<<(b-q))&x) >> (b-q);
            x += (b_p^b_q)<<b;
        }
        return ret;
    }

    @Override
    public double nextDouble() {
        double ret = ((double)x/range);
        next();
        return ret;
    }

    public static class RegisterGenBuilder {
        private int p;
        private int q;
        private int seed;

        /**
         * Builder class with default values
         * @param seed helps evaluating other elements
         */

        public RegisterGenBuilder(int seed) {
            p = 7;
            q = 3;
            this.seed = seed;
        }

        public RegisterGenBuilder setP(int p) {
            if(p>7) {
                throw new NumberFormatException("Number can't be bigger than 7");
            }
            this.p = p;
            return this;
        }

        public RegisterGenBuilder setQ(int q) {
            if(q>7) {
                throw new NumberFormatException("Number can't be bigger than 7");
            }
            this.q = q;
            return this;
        }

        public RegisterGenBuilder setSeed(int seed) {
            this.seed = seed;
            return this;
        }

        public RegisterGen build() {
            return new RegisterGen(this);
        }
    }
}
