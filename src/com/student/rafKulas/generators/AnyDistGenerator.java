package com.student.rafKulas.generators;
import java.util.List;

public abstract class AnyDistGenerator {
    protected UniformGenerator gen;
    protected List<Number> list; // list of created random values
    protected long n; // amount of created random values
    public abstract void printOneValue(int index);
    public abstract void printValues();
}
