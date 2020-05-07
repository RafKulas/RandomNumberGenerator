package com.student.rafKulas.generators;

import java.util.ArrayList;

public class EliminationGenerator extends AnyDistGenerator{

    private final int a;
    private final int b;
    private final int d;

    public EliminationGenerator(EliminationGenBuilder egb) {
        this.gen = egb.gen;
        this.a = egb.a;
        this.b = egb.b;
        this.d = egb.d;
        this.n = egb.n;
        this.list = new ArrayList<>();
    }

    public EliminationGenerator generateDuos() {
        for(;n>0;n--) {
            // I add 1 to include borders

            long U2 = gen.next()%(d + 1);
            gen.next();
            long U1 = gen.next()%(b-a+1)+a;

            if (f(U1) > U2) {
                this.list.add(U1);
                this.list.add(U2);
            }
        }
        return this;
    }

    @Override
    public void printOneValue(int index) {
        System.out.printf("%7d. %5d, %5d\n",index/2,  this.list.get(index).longValue(), this.list.get(index+1).longValue());
    }

    @Override
    public void printValues() {
        System.out.printf("%9s %5s %5s\n", "", "U1", "U2");
        for(int i = 0; i<list.size(); i+=2) {
            printOneValue(i);
        }
    }

    private long f(long x) {
        return 3*x/2-20;
    }

    public static class EliminationGenBuilder{
        private int a;
        private int b;
        private int d;
        private int n;
        private final UniformGenerator gen;
        public EliminationGenBuilder(UniformGenerator gen) {
            a = 30;
            b = 100;
            d = 50;
            n = 10000;
            this.gen = gen;
        }

        public EliminationGenBuilder setA(int a) {
            this.a = a;
            return this;
        }

        public EliminationGenBuilder setB(int b) {
            this.b = b;
            return this;
        }

        public EliminationGenBuilder setD(int d) {
            this.d = d;
            return this;
        }

        public EliminationGenBuilder setN(int n) {
            this.n = n;
            return this;
        }

        public EliminationGenerator build() {
            return new EliminationGenerator(this);
        }
    }
}
