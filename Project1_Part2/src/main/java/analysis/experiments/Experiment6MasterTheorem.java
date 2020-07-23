package analysis.experiments;

import analysis.utils.AnalysisUtils;
import analysis.utils.PlotWindow;
import datastructures.lists.IList;

import java.util.function.LongUnaryOperator;


public class Experiment6MasterTheorem {
    /*
    Note: please do not change these constants (or the constants in any of the other experiments)
    while working on your writeup
    */
    public static final int MAX_INPUT_SIZE = 300000;
    public static final int STEP = 1000;

    public static void main(String[] args) {
        IList<Long> inputSizes = AnalysisUtils.makeDoubleLinkedList(0L, MAX_INPUT_SIZE, STEP);
        PlotWindow.launch("Experiment 6", "Input Size [n]", "Elapsed Time (ms)",
                new LongUnaryOperator[]{Experiment6MasterTheorem::test1, Experiment6MasterTheorem::test2},
                new String[]{"test1", "test2"}, inputSizes, 2, .05);

        System.out.println("All done!");
    }

    public static long test1(long inputSize) {
        long start = System.currentTimeMillis();

        mystery((int) inputSize, 1, 2, 1);

        return System.currentTimeMillis() - start;
    }

    public static long test2(long inputSize) {
        long start = System.currentTimeMillis();

        mystery((int) inputSize, 2, 2, 1);

        return System.currentTimeMillis() - start;
    }

    private static void mystery(int n, int a, int b, int c) {
        int x = 0;
        if (n > 0) {
            for (int i = 0; i < a; i++) {
                mystery(n / b, a, b, c);
            }
        }
        for (int i = 0; i < Math.pow(n, c); i++) {
                    x = x + 1;
        }
    }
}

