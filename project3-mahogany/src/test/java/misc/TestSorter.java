package misc;

import datastructures.lists.DoubleLinkedList;
import datastructures.lists.IList;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static datastructures.lists.IListMatcher.listContaining;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * See spec for details on what kinds of tests this class should include.
 */
@Tag("project3")
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class TestSorter extends BaseTest {
    @Test
    public void testSimpleUsage() {
        IList<Integer> list = new DoubleLinkedList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }

        IList<Integer> top = Sorter.topKSort(5, list);
        assertThat(top, is(listContaining(15, 16, 17, 18, 19)));
    }

    @Test
    public void testIllegalInputThrowsException() {
        IList<Integer> list = new DoubleLinkedList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        assertThrows(IllegalArgumentException.class, () -> Sorter.topKSort(5, null));
        assertThrows(IllegalArgumentException.class, () -> Sorter.topKSort(-1, null));
        assertThrows(IllegalArgumentException.class, () -> Sorter.topKSort(-1, list));
    }

    @Test
    public void testEmptyInput() {
        IList<Integer> list = new DoubleLinkedList<>();

        IList<Integer> top = Sorter.topKSort(5, list);
        assertThat(top.size(), is(0));
    }


    @Test
    public void testSingleInput() {
        IList<Integer> list = new DoubleLinkedList<>();
        list.add(3);

        IList<Integer> top = Sorter.topKSort(5, list);
        assertThat(top, is(listContaining(3)));
    }

    @Test
    public void testInputOrderedDescending() {
        IList<Integer> list = new DoubleLinkedList<>();
        for (int i = 100; i > 0; i--){
            list.add(i);
        }

        IList<Integer> top = Sorter.topKSort(5, list);
        assertThat(top, is(listContaining(96, 97, 98, 99, 100)));
    }

    @Test
    public void testInputRandomOrder() {
        IList<Integer> list = new DoubleLinkedList<>();
        list.add(3);
        list.add(2);
        list.add(4);
        list.add(5);
        list.add(7);
        list.add(6);
        list.add(1);
        list.add(9);
        list.add(8);
        list.add(10);

        IList<Integer> top = Sorter.topKSort(2, list);
        assertThat(top, is(listContaining(9, 10)));
    }

    @Test
    public void testInputRandomOrderKZero() {
        IList<Integer> list = new DoubleLinkedList<>();
        list.add(3);
        list.add(2);
        list.add(4);
        list.add(5);
        list.add(7);
        list.add(6);
        list.add(1);
        list.add(9);
        list.add(8);
        list.add(10);

        IList<Integer> top = Sorter.topKSort(0, list);
        assertThat(top.size(), is(0));
    }

    @Test
    public void testCheckInputMutation() {
        IList<Integer> list = new DoubleLinkedList<>();
        list.add(3);
        list.add(2);
        list.add(4);
        list.add(5);
        list.add(1);

        IList<Integer> top = Sorter.topKSort(3, list);
        assertThat(top, is(listContaining(3, 4, 5)));
        assertThat(list, is(listContaining(3, 2, 4, 5, 1)));
    }
}
