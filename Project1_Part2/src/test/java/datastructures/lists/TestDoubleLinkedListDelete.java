package datastructures.lists;

import datastructures.lists.DoubleLinkedList.Node;
import misc.BaseTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.Duration;

import static datastructures.lists.TestDoubleLinkedList.makeBasicList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static datastructures.lists.IListMatcher.listContaining;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

/**
 * This class should contain all the tests you implement to verify that your `delete` method behaves
 * as specified. Each test should run quickly; if your tests take longer than 1 second to run, they
 * may get timed out on the runners and during grading.
 */
@Tag("project1")
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class TestDoubleLinkedListDelete extends BaseTest {

    @Test
    void testDeleteBasic() {
        /*
        Feel free to modify or delete this dummy test.

        Below is an example of using casting, so that Java lets us access
        the specific fields of DoubleLinkedList. If you wish, you may
        use this syntax to access the internal pointers within
        DoubleLinkedList objects. Being able to check the internal pointers
        will more easily let you be thorough in your tests. For reference, our
        secret tests will be checking that the internal pointers are correct to more
        easily check your solution.
         */
        IList<String> list = makeBasicList();
        int listSize = list.size();
        list.delete(1);
        assertThat(list.size(), is(listSize - 1));
        assertThat(list, is(listContaining("a", "c")));
        // Node<String> front = ((DoubleLinkedList<String>) list).front;
        // Node<String> back = ((DoubleLinkedList<String>) list).back;
        // assertThat(front.next, is(back.prev));
        // assertThat(front.prev, is(nullValue()));
        // assertThat(back.next, is(nullValue()));
    }

    @Test
    void testDeleteOnEmpty() {
        IList<String> list = new DoubleLinkedList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.delete(1));
    }

    @Test
    void testDeleteWithMutators() {
        IList<String> list = new DoubleLinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.add("a");
        }

        assertThat(list.delete(2), is("a"));
        assertThat(list, is(listContaining("a", "a", "a", "a")));
        assertThat(list.size(), is(4));

        list.insert(2, "b");
        list.delete(4);
        assertThat(list, is(listContaining("a", "a", "b", "a")));
        assertThat(list.size(), is(4));

        list.remove();
        assertThat(list, is(listContaining("a", "a", "b")));
    }

    @Test
    void testDeleteSameIndex() {
        IList<Integer> list = new DoubleLinkedList<>();
        for (int i = 0; i < 5000; i++) {
            list.add(i);
        }

        for (int i = 0; i < 5000; i++) {
            assertThat(list.delete(0), is(i));
            assertThat(list.size(), is(4999 - i));
        }
    }

    @Test
    void testDeleteFromHead() {
        IList<String> list = makeBasicList();
        assertThat(list.delete(0), is("a"));
    }

    @Test
    void testDeleteFromBack() {
        IList<Integer> list = new DoubleLinkedList<>();
        list.add(1);
        list.add(3);
        list.insert(2, 4);
        list.insert(3, 5);
        list.insert(1, 2);
        assertThat(list, is(listContaining(1, 2, 3, 4, 5)));
        assertThat(list.delete(4), is(5));
    }

    @Test
    void testDeleteNearEndIsEfficiency() {
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            IList<Integer> list = new DoubleLinkedList<>();
            for (int i = 0; i < 10000; i++) {
                list.add(i);
            }

            for (int i = 0; i < 10000; i++) {
                list.delete(list.size() - 1);
            }
        });
    }

    @Test
    void testNullElement() {
        // delete null element
        IList<Integer> list = new DoubleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(null);
        list.add(4);
        list.add(5);

        assertThat(list.delete(2), is(nullValue()));
    }

    @Test
    void testBackNode() {
        IList<String> list = makeBasicList();
        Node<String> back = ((DoubleLinkedList<String>) list).back;
        Node<String> front = ((DoubleLinkedList<String>) list).front;
        assertThat(back.next, is(nullValue()));
        assertThat(front.prev, is(nullValue()));

        list.insert(3, "6");
        assertThat(list.delete(3), is("6"));
        assertThat(back.next, is(nullValue()));

        assertThat(list.delete(0), is("a"));
        assertThat(front.prev, is(nullValue()));
    }

    @Test
    void testDeleteInvalidIndex() {
        IList<String> list = makeBasicList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.delete(3));
    }

    @Test
    void testLowerBoundCheck() {
        IList<String> list = makeBasicList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.delete(-1));
    }
}
