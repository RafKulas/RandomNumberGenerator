package com.student.rafKulas;

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
        int ans = 4;
        Scanner in = new Scanner(System.in);
        while(ans!=0) {
            System.out.println("Which generator do you want to use?\n" +
                    "1) Linear Generator\n" +
                    "2) Shift register based generators\n" +
                    "0) Exit");
            try {
                ans = in.nextInt();
                if(ans>2 || ans<0) {
                    throw new NumberFormatException();
                }
            } catch (Exception e) {
                System.out.println("Wrong input, try again...");
                in.next();
                continue;
            }


            if(ans == 1) {
                LinearGen lG;

                System.out.println("Linear generator uses \"x_(n+1) = (a*x_n + c) % M\" formula");
                while (ans != 0) {
                    System.out.println("Do you want to:\n" +
                            "1) Use default parameters seed, a, c, M\n" +
                            "2) Use your own parameters seed, a, c, M\n" +
                            "0) Exit");
                    try {
                        ans = in.nextInt();
                        if (ans>2 || ans<0) {
                            throw new NumberFormatException();
                        }
                    } catch (Exception e) {
                        System.out.println("Wrong input, try again...");
                        //in.next();
                        ans = 4;
                    }

                    if (ans == 1) {
                        lG = new LinearGen.LinearGenBuilder(7).build();
                        System.out.println("How many number do you want to print? (If bad input 100000 will be set)");
                        try {
                            ans = in.nextInt();
                        } catch (Exception e) {
                            ans = 100000;
                        }
                        while(ans-->0) {
                            lG.printNext();
                        }
                        return;
                    }
                    else if(ans == 2) {
                        System.out.println("What seed do you want to use? (If bad input 7 will be set)");
                        int seed, a, c;
                        long M;
                        try {
                            seed = in.nextInt();
                        } catch (Exception e) {
                            seed = 7;
                            in.next();
                        }
                        System.out.println("What value of \"a\" do you want to use? (If bad input 69069 will be set)");
                        try {
                            a = in.nextInt();
                        } catch (Exception e) {
                            a = 69069;
                            in.next();
                        }
                        System.out.println("What value of \"c\" do you want to use? (If bad input 1 will be set)");
                        try {
                            c = in.nextInt();
                        } catch (Exception e) {
                            c = 1;
                            in.next();
                        }
                        System.out.println("What value of \"M\" do you want to use? (If bad input (2^32)-1 will be set)");
                        try {
                            M = in.nextLong();
                        } catch (Exception e) {
                            M = 1;
                            in.next();
                        }
                        lG = new LinearGen.LinearGenBuilder(seed).setA(a).setC(c).setM(M).build();
                        System.out.println("How many number do you want to print? (If bad input 100000 will be set)");
                        try {
                            ans = in.nextInt();
                        } catch (Exception e) {
                            ans = 100000;
                        }
                        while(ans-->0) {
                            lG.printNext();
                        }
                        return;

                    }

                }
            }
            else if(ans == 2) {
                RegisterGen rG; // = new RegisterGen.RegisterGenBuilder(69).build();

                System.out.println("Shift register based generators uses \"b_i = b_(i-p) xor b_(i-q)\" formula,\n" +
                        "where b_i is i-th bit of random number");
                while (ans != 0) {
                    System.out.println("Do you want to:\n" +
                            "1) Use default parameters seed, p, q\n" +
                            "2) Use your own parameters seed, p, q\n" +
                            "0) Exit");
                    try {
                        ans = in.nextInt();
                        if (ans>2 || ans<0) {
                            throw new NumberFormatException();
                        }
                    } catch (Exception e) {
                        System.out.println("Wrong input, try again...");
                        //in.next();
                        ans = 4;
                    }

                    if (ans == 1) {
                        rG = new RegisterGen.RegisterGenBuilder(69).build();
                        System.out.println("How many number do you want to print? (If bad input 100000 will be set)");
                        try {
                            ans = in.nextInt();
                        } catch (Exception e) {
                            ans = 100000;
                        }
                        while(ans-->0) {
                            rG.printNext();
                        }
                        return;
                    }
                    else if(ans == 2) {
                        System.out.println("What seed do you want to use? (If bad input 69 will be set)");
                        // 0b1000101 == 69
                        int p, q, seed;
                        try {
                            seed = in.nextInt();
                        } catch (Exception e) {
                            seed = 69;
                            in.next();
                        }
                        System.out.println("What value of \"p\"  in range <1, 7> do you want to use? (If bad input 7 will be set)");
                        try {
                            p = in.nextInt();
                            if(p<1 || p>7) {
                                throw new NumberFormatException("Number must be int and in range of <1,7>");
                            }
                        } catch (Exception e) {
                            p = 7;
                            in.next();
                        }
                        System.out.println("What value of \"q\" in range <1, 7> do you want to use? (If bad input 3 will be set)");
                        try {
                            q = in.nextInt();
                            if(q<1 || q>7 || q==p) {
                                throw new NumberFormatException("Number must be int and in range of <1,7> and cannot equal p");
                            }
                        } catch (Exception e) {
                            q = 3;
                            in.next();
                        }

                        rG = new RegisterGen.RegisterGenBuilder(seed).setP(p).setQ(q).build();
                        System.out.println("How many number do you want to print? (If bad input 100000 will be set)");
                        try {
                            ans = in.nextInt();
                        } catch (Exception e) {
                            ans = 100000;
                        }
                        while(ans-->0) {
                            rG.printNext();
                        }
                        return;

                    }

                }


            }

        }

    }
}
