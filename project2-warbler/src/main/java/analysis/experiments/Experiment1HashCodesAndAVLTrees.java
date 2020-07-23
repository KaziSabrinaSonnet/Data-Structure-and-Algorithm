package analysis.experiments;

import analysis.utils.AnalysisUtils;
import analysis.utils.PlotWindow;
import datastructures.dictionaries.AVLTreeDictionary;
import datastructures.dictionaries.ChainedHashDictionary;
import datastructures.dictionaries.IDictionary;
import datastructures.lists.IList;
import misc.exceptions.NotYetImplementedException;

import java.util.function.LongUnaryOperator;

public class Experiment1HashCodesAndAVLTrees {
    public static final long MAX_DICTIONARY_SIZE = 50000;
    public static final long STEP = 500;

    public static final int LENGTH_PER_ARRAY = 50;

    public static void main(String[] args) {
        IList<Long> dictionarySizes = AnalysisUtils.makeDoubleLinkedList(0L, MAX_DICTIONARY_SIZE, STEP);

        PlotWindow.launch("Experiment 1", "Dictionary Size", "Elapsed Time (ms)",
                new LongUnaryOperator[]{Experiment1HashCodesAndAVLTrees::test1,
                        Experiment1HashCodesAndAVLTrees::test2,
                        Experiment1HashCodesAndAVLTrees::test3,
                        Experiment1HashCodesAndAVLTrees::test4},
                new String[]{"test1", "test2", "test3", "test4"},
                dictionarySizes, 5, .05);
    }

    public static long test1(long dictionarySize) {
        // This test uses FakeString1 and its `hashCode` function.
        IList<char[]> chars = AnalysisUtils.generateRandomCharArrays((int) dictionarySize, LENGTH_PER_ARRAY);

        long start = System.currentTimeMillis();
        IDictionary<FakeString1, char[]> dict = new ChainedHashDictionary<>();
        for (char[] array : chars) {
            dict.put(new FakeString1(array), array);
        }
        return System.currentTimeMillis() - start;
    }

    public static long test2(long dictionarySize) {
        // This test uses FakeString2 and its `hashCode` function.
        IList<char[]> chars = AnalysisUtils.generateRandomCharArrays((int) dictionarySize, LENGTH_PER_ARRAY);

        long start = System.currentTimeMillis();
        IDictionary<FakeString2, char[]> dict = new ChainedHashDictionary<>();
        for (char[] array : chars) {
            dict.put(new FakeString2(array), array);
        }
        return System.currentTimeMillis() - start;
    }

    public static long test3(long dictionarySize) {
        // This test uses FakeString3 and its `hashCode` function.
        IList<char[]> chars = AnalysisUtils.generateRandomCharArrays((int) dictionarySize, LENGTH_PER_ARRAY);

        long start = System.currentTimeMillis();
        IDictionary<FakeString3, char[]> dict = new ChainedHashDictionary<>();
        for (char[] array : chars) {
            dict.put(new FakeString3(array), array);
        }
        return System.currentTimeMillis() - start;
    }

    public static long test4(long dictionarySize) {
        // This test uses an AVLTreeDictionary instead of a ChainedHashDictionary, and thus does not
        // use hash codes at all.
        IList<char[]> chars = AnalysisUtils.generateRandomCharArrays((int) dictionarySize, LENGTH_PER_ARRAY);

        long start = System.currentTimeMillis();
        IDictionary<FakeString, char[]> dict = new AVLTreeDictionary<>();
        for (char[] array : chars) {
            dict.put(new FakeString(array), array);
        }
        return System.currentTimeMillis() - start;
    }


    public static class FakeString implements Comparable<FakeString> {
        protected char[] chars;

        public FakeString(char[] chars) {
            this.chars = chars;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof FakeString)) {
                return false;
            }
            FakeString otherFake = (FakeString) other;
            if (this.chars.length != otherFake.chars.length) {
                return false;
            }
            for (int i = 0; i < this.chars.length; i++) {
                if (this.chars[i] != otherFake.chars[i]) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            throw new NotYetImplementedException();
        }

        public int compareTo(FakeString other) {
            // include a `compareTo` implementation for the AVL tree
            for (int i = 0; i < Math.min(this.chars.length, other.chars.length); i++) {
                int difference = this.chars[i] - other.chars[i];
                if (difference != 0) {
                    return difference;
                }
            }
            return this.chars.length - other.chars.length;
        }
    }

    public static class FakeString1 extends FakeString {
        public FakeString1(char[] chars) {
            super(chars);
        }

        @Override
        public boolean equals(Object other) {
            return super.equals(other);
        }

        @Override
        public int hashCode() {
            // sum the first 4 chars
            return this.chars[0] + this.chars[1] + this.chars[2] + this.chars[3];
        }
    }

    public static class FakeString2 extends FakeString {
        public FakeString2(char[] chars) {
            super(chars);
        }

        @Override
        public boolean equals(Object other) {
            return super.equals(other);
        }

        @Override
        public int hashCode() {
            // sum all the chars
            int out = 0;
            for (char c : this.chars) {
                out += c;
            }
            return out;
        }
    }

    public static class FakeString3 extends FakeString {
        public FakeString3(char[] chars) {
            super(chars);
        }

        @Override
        public boolean equals(Object other) {
            return super.equals(other);
        }

        @Override
        public int hashCode() {
            // Note: this is basically what Java's `List.hashCode` implementation does.
            // See https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/util/List.html#hashCode()
            int out = 1;
            for (char c : this.chars) {
                out = out * 31 + c;
            }
            return out;
        }
    }
}
