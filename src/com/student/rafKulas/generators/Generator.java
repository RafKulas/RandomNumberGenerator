package com.student.rafKulas.generators;

public abstract class Generator {
    /**
     * seed for random numbers generator
     */
    protected int seed;

    /**
     * x is last element returned by next() function
     */
    protected long x;
    /**
     * Range is maximum number that can be get from generator
     */
    protected long range;

    /**
     * Constructor of the Generator
     * Subclass of the Generator should implement it's own way of making first element (x)
     * @param seed which help creating next elements
     */
    public Generator(int seed) {
        this.seed = seed;
    }

    /**
     * Generate next random number
     * @return current random number
     */
    public abstract long next();

    /**
     * Print in console value of current element and generate next one
     */
    public void printNext() {
        System.out.println(next());
    }

    /**
     * Function to peek current random number, doesn't generate next number
     * @return current random number
     */
    public long getX() {
        return x;
    }

    /**
     * Generate next double type random number
     * @return double type random number
     */
    public  abstract double nextDouble();

}
