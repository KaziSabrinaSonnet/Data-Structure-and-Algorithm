# Feedback

Group mahogany: ksonnet, tianfw2

Commit hash: 460d5536d2ddd728aaadb07327dd5c6724952055

Raw score: 54 / 60

## Checkstyle

Score: 5 / 5

## ArrayHeap

Score: 20 / 25

<details>
<summary>✔ <b>PASS</b>: (weight=1.0) <code>testAddAndRemoveMinBasic()</code> <i>[expand for description]</i></summary>

> A basic test case that inserts a few elements (order of magnitude 10), then checks that `removeMin`
> and `size` return the correct values.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=1.0) <code>testAddDuplicateEntries()</code> <i>[expand for description]</i></summary>

> Adds the same few unique elements twice and checks that the correct exception is thrown during for
> the second, duplicate insertion.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=1.0) <code>testAddEquivalentValues()</code> <i>[expand for description]</i></summary>

> Adds many `IntPair`s with equivalent values into the heap, and then verifies correct ordering by
> repeatedly calling `peekMin` and `removeMin`.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=1.0) <code>testAddNullEntriesThrowsException()</code> <i>[expand for description]</i></summary>

> Verifies that the correct exception is thrown when null values are added into the heap.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=1.0) <code>testChecksAllChildren()</code> <i>[expand for description]</i></summary>

> Verifies that percolating downwards works when the minimum child is in any of the 4 possible
> positions. (Checks the internal array to verify that it exactly matches the expected state.)

</details>

<details open>
<summary>❌ <b>FAIL</b>: (weight=0.4) <code>testContainsAfterRemove()</code> <i>[expand for description]</i></summary>

> Verifies that `contains` works correctly after calling `remove` or `removeMin`.
>
> <details>
> <summary>
> 
> ``` java
> java.lang.IllegalArgumentException
>     at datastructures.priorityqueues.ArrayHeapPriorityQueue.swap(ArrayHeapPriorityQueue.java:253)
>     at datastructures.priorityqueues.ArrayHeapPriorityQueue.remove(ArrayHeapPriorityQueue.java:126)
>     at datastructures.priorityqueues.TestSecretArrayHeapPriorityQueue.testContainsAfterRemove(TestSecretArrayHeapPriorityQueue.java:530)
> ```
> </summary>
> 
> ``` java
>     at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
>     at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
>     at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
>     at java.base/java.lang.reflect.Method.invoke(Method.java:567)
>     at secret.TimeoutPreemptivelyExtension$MethodTimeoutInterceptor.lambda$intercept$0(TimeoutPreemptivelyExtension.java:115)
>     at org.junit.jupiter.api.AssertTimeout.lambda$assertTimeoutPreemptively$4(AssertTimeout.java:132)
>     at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
>     at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
>     at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
>     at java.base/java.lang.Thread.run(Thread.java:835)
> ```
> </details>

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=0.4) <code>testContainsAfterReplace()</code> <i>[expand for description]</i></summary>

> Verifies that `contains` works correctly after calling `replace`.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=0.4) <code>testContainsBasic()</code> <i>[expand for description]</i></summary>

> A basic test case for `contains`.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=0.4) <code>testContainsNullThrowsException()</code> <i>[expand for description]</i></summary>

> Verifies that calling `contains` with a null value throws the correct exception.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=0.4) <code>testContainsOnEmptyHeap()</code> <i>[expand for description]</i></summary>

> Verifies that `contains` works correctly value on an empty heap.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=1.0) <code>testInternalStructureAndStress()</code> <i>[expand for description]</i></summary>

> Randomly inserts random elements or calls `removeMin` many times (order of magnitude 10,000). After
> each method call, checks the internal array to verify that it represents a valid min-4-heap (each
> value is less than or equal to its children).

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=0.5) <code>testPeekMin()</code> <i>[expand for description]</i></summary>

> Tests both that `peekMin` returns the minimum and that subsequently calling `removeMin` returns the
> same value.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=0.5) <code>testPeekMinOnEmptyQueueThrowsException()</code> <i>[expand for description]</i></summary>

> Calling `peekMin` on an empty `ArrayHeapPriorityQueue` should throw an `EmptyContainerException` -
> if this test fails, that was not the case for your code. This test also inserts and removes before
> testing the `EmptyContainerException` case.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=1.0) <code>testRandomEntriesStress()</code> <i>[expand for description]</i></summary>

> Adds random values to the heap a random number of times, then empties the heap while verifying that
> `peekMin` and `removeMin` return the expected sorted sequence. This is repeated 1000 times.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=0.3) <code>testRemoveAddSameItem()</code> <i>[expand for description]</i></summary>

> Verifies that an element can be re-added to a heap after it has been removed from the heap.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=0.3) <code>testRemoveEquivalentValuesThrowsException()</code> <i>[expand for description]</i></summary>

> Attempts to remove a new `IntPair` object with the same `compareTo` value as an element in a heap,
> but that is not `equals` to any of them. Expects an exception, since the new `IntPair` object is not
> in the heap.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=1.0) <code>testRemoveMinOnEmptyQueueThrowsException()</code> <i>[expand for description]</i></summary>

> Calling `removeMin` on an empty `ArrayHeapPriorityQueue` should throw an `EmptyContainerException` -
> if this test fails, that was not the case for your code. This test also inserts and removes before
> testing the `EmptyContainerException` case.

</details>

<details open>
<summary>❌ <b>FAIL</b>: (weight=2.0) <code>testRemoveMinStress()</code> <i>[expand for description]</i></summary>

> Inserts many unique integer elements (order of magnitude 10,000) and calls `removeMin` until all
> elements are removed, checking that the values returned increase in value.
>
> <details>
> <summary>
> 
> ``` java
> java.lang.AssertionError: 
> Expected: a value less than <7333>
>      but: <9095> was greater than <7333>
>     at org.hamcrest.MatcherAssert.assertThat(MatcherAssert.java:18)
>     at org.hamcrest.MatcherAssert.assertThat(MatcherAssert.java:6)
>     at datastructures.priorityqueues.TestSecretArrayHeapPriorityQueue.testRemoveMinStress(TestSecretArrayHeapPriorityQueue.java:133)
> ```
> </summary>
> 
> ``` java
>     at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
>     at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
>     at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
>     at java.base/java.lang.reflect.Method.invoke(Method.java:567)
>     at secret.TimeoutPreemptivelyExtension$MethodTimeoutInterceptor.lambda$intercept$0(TimeoutPreemptivelyExtension.java:115)
>     at org.junit.jupiter.api.AssertTimeout.lambda$assertTimeoutPreemptively$4(AssertTimeout.java:132)
>     at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
>     at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
>     at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
>     at java.base/java.lang.Thread.run(Thread.java:835)
> ```
> </details>

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=0.3) <code>testRemoveMissingItemThrowsException()</code> <i>[expand for description]</i></summary>

> Tests that calling remove on an element that isn't in the heap throws the correct exception.
> Afterwards, it also checks that a valid removal is still successful.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=0.3) <code>testRemoveNullItemThrowsException()</code> <i>[expand for description]</i></summary>

> Tests that removing `null` from an empty heap throws the correct exception.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=0.3) <code>testRemoveOnEmptyQueueThrowsException()</code> <i>[expand for description]</i></summary>

> Tests that calling `remove` on an empty heap throws the correct exception.

</details>

<details open>
<summary>❌ <b>FAIL</b>: (weight=0.3) <code>testRemovePercolateDown()</code> <i>[expand for description]</i></summary>

> Removes an element from a heap that should trigger a downwards percolation, and then checks the
> internal array to verify that it represents a valid min-4-heap.
>
> <details>
> <summary>
> 
> ``` java
> java.lang.ArrayIndexOutOfBoundsException: Index 10 out of bounds for length 10
>     at datastructures.priorityqueues.ArrayHeapPriorityQueue.remove(ArrayHeapPriorityQueue.java:127)
>     at datastructures.priorityqueues.TestSecretArrayHeapPriorityQueue.testRemovePercolateDown(TestSecretArrayHeapPriorityQueue.java:381)
> ```
> </summary>
> 
> ``` java
>     at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
>     at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
>     at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
>     at java.base/java.lang.reflect.Method.invoke(Method.java:567)
>     at secret.TimeoutPreemptivelyExtension$MethodTimeoutInterceptor.lambda$intercept$0(TimeoutPreemptivelyExtension.java:115)
>     at org.junit.jupiter.api.AssertTimeout.lambda$assertTimeoutPreemptively$4(AssertTimeout.java:132)
>     at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
>     at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
>     at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
>     at java.base/java.lang.Thread.run(Thread.java:835)
> ```
> </details>

</details>

<details open>
<summary>❌ <b>FAIL</b>: (weight=0.3) <code>testRemovePercolateUp()</code> <i>[expand for description]</i></summary>

> Removes an element from a heap that should trigger an upwards percolation, and then checks the
> internal array to verify that it represents a valid min-4-heap.
>
> <details>
> <summary>
> 
> ``` java
> java.lang.ArrayIndexOutOfBoundsException: Index 10 out of bounds for length 10
>     at datastructures.priorityqueues.ArrayHeapPriorityQueue.remove(ArrayHeapPriorityQueue.java:127)
>     at datastructures.priorityqueues.TestSecretArrayHeapPriorityQueue.testRemovePercolateUp(TestSecretArrayHeapPriorityQueue.java:362)
> ```
> </summary>
> 
> ``` java
>     at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
>     at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
>     at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
>     at java.base/java.lang.reflect.Method.invoke(Method.java:567)
>     at secret.TimeoutPreemptivelyExtension$MethodTimeoutInterceptor.lambda$intercept$0(TimeoutPreemptivelyExtension.java:115)
>     at org.junit.jupiter.api.AssertTimeout.lambda$assertTimeoutPreemptively$4(AssertTimeout.java:132)
>     at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
>     at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
>     at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
>     at java.base/java.lang.Thread.run(Thread.java:835)
> ```
> </details>

</details>

<details open>
<summary>❌ <b>FAIL</b>: (weight=0.3) <code>testRemoveRootConsecutive()</code> <i>[expand for description]</i></summary>

> Adds many elements (order of magnitude 1,000), and then repeatedly removes the value returned by
> `peekMin` (once for each element added). Cerifies the heap is empty afterward. Also, after every
> `remove` call, checks the internal array to verify that it correctly represents a min-4-heap.
>
> <details>
> <summary>
> 
> ``` java
> java.lang.AssertionError: 
> Expected: a valid array heap
>      but: heap invariant broken at index <5> (value: <514>; children: [505, 562, 559, 560])
>     at org.hamcrest.MatcherAssert.assertThat(MatcherAssert.java:18)
>     at org.hamcrest.MatcherAssert.assertThat(MatcherAssert.java:6)
>     at datastructures.priorityqueues.TestSecretArrayHeapPriorityQueue.testRemoveRootConsecutive(TestSecretArrayHeapPriorityQueue.java:343)
> ```
> </summary>
> 
> ``` java
>     at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
>     at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
>     at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
>     at java.base/java.lang.reflect.Method.invoke(Method.java:567)
>     at secret.TimeoutPreemptivelyExtension$MethodTimeoutInterceptor.lambda$intercept$0(TimeoutPreemptivelyExtension.java:115)
>     at org.junit.jupiter.api.AssertTimeout.lambda$assertTimeoutPreemptively$4(AssertTimeout.java:132)
>     at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
>     at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
>     at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
>     at java.base/java.lang.Thread.run(Thread.java:835)
> ```
> </details>

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=0.6) <code>testRemoveStress()</code> <i>[expand for description]</i></summary>

> Verifies that `remove` runs efficiently on large heap sizes (order of magnitude 10,000).

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=0.3) <code>testRemoveUpdatesSize()</code> <i>[expand for description]</i></summary>

> Verifies that calling `remove` correctly updates the size of the heap.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=0.3) <code>testReplaceDoesNotChangeSize()</code> <i>[expand for description]</i></summary>

> Verifies that `size` is correct after calling `replace`.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=0.3) <code>testReplaceLastElement()</code> <i>[expand for description]</i></summary>

> Calls `replace` on the last element in a heap, and then checks the internal array to verify that it
> represents a valid min-4-heap.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=0.3) <code>testReplaceMinElement()</code> <i>[expand for description]</i></summary>

> Calls `replace` on the smallest element in a heap and checks its internal array to verify that it
> represents a valid min-4-heap afterwards.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=0.3) <code>testReplaceOnEmptyHeap()</code> <i>[expand for description]</i></summary>

> Verifies that calling `replace` on an empty heap throws the correct exception.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=0.3) <code>testReplaceOnlyElement()</code> <i>[expand for description]</i></summary>

> Calls `replace` on the only element in a single-element heap, and then calls `removeMin` to verify
> that the element was correctly replaced.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=0.6) <code>testReplaceStress()</code> <i>[expand for description]</i></summary>

> Verifies that `replace` runs efficiently on large heap sizes (order of magnitude 10,000).

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=0.3) <code>testReplaceWithEquivalentValues()</code> <i>[expand for description]</i></summary>

> Verifies that replacing elements only works when the element is `equals` to something in the heap
> (elements with the same `compareTo` value may not be `equals`) and otherwise throws the correct
> exception.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=0.3) <code>testReplaceWithExistingElementThrowsException()</code> <i>[expand for description]</i></summary>

> Verifies that replacing an element with a value that's already in the heap throws the correct
> exception.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=1.0) <code>testStringEntries()</code> <i>[expand for description]</i></summary>

> Inserts a handful of `String` elements and checks that `peekMin` then `removeMin` over the whole
> heap returns the expected sorted sequence.

</details>

## Sorter

Score: 10 / 10

<details>
<summary>✔ <b>PASS</b>: (weight=1.0) <code>testAlreadySortedInput()</code> <i>[expand for description]</i></summary>

> Verifies that an input sorted sequence still results in a correctly sorted output.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=1.0) <code>testEquivalentValuedEntries()</code></summary>
</details>

<details>
<summary>✔ <b>PASS</b>: (weight=1.0) <code>testInputListNotMutated()</code> <i>[expand for description]</i></summary>

> Verifies that the input list does not get mutated.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=5.0) <code>testIsNLogK()</code> <i>[expand for description]</i></summary>

> This test is on the runners; checks whether the runtime of `topKSort` is in
> $`\mathcal{O}(n\log(k))`$ by counting comparison operations.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=1.0) <code>testKEqualsN()</code> <i>[expand for description]</i></summary>

> Verifies that the corrected sorted sequence is returned for a random list with $`n = k \approx
> 100`$.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=1.0) <code>testKIsGreaterThanN()</code> <i>[expand for description]</i></summary>

> Verifies that the correct sorted sequence is returned for a random list with $`k > n \approx 100`$.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=2.0) <code>testKIsLessThanN()</code> <i>[expand for description]</i></summary>

> Verifies that the corrected sorted sequence is returned for a random list with $`n \approx 100`$ and
> $`k < 10`$.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=1.0) <code>testKIsNegative()</code> <i>[expand for description]</i></summary>

> Verifies that the correct exception is thrown for negative values of $`k`$.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=1.0) <code>testKIsOne()</code> <i>[expand for description]</i></summary>

> Verifies that the correct sorted sequence is returned for $`k = 1`$ and a reasonably large $`n`$.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=1.0) <code>testKIsZero()</code> <i>[expand for description]</i></summary>

> Verifies the top 0 elements are returned for $`k = 0`$ (i.e., the output is an empty list).

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=1.0) <code>testNIsOne()</code> <i>[expand for description]</i></summary>

> Verifies the correct sorted sequence is returned for $`n = 1`$ and a small $`k`$.

</details>

<details>
<summary>✔ <b>PASS</b>: (weight=1.0) <code>testNIsZero()</code></summary>
</details>

<details>
<summary>✔ <b>PASS</b>: (weight=1.0) <code>testNonIntegerEntries()</code> <i>[expand for description]</i></summary>

> Verifies that `topKSort` works correctly for a `List<String>` input.

</details>

## TestArrayHeapPriorityQueue

Score: 15 / 15

✔ <b>PASS</b>: (weight=1.5) `AllOk`  
✔ <b>PASS</b>: (weight=1.0) `AddDoesNotResizeArray`  
> This implementation never resizes its internal array.

✔ <b>PASS</b>: (weight=1.0) `AddDoesNotThrowExceptions`  
✔ <b>PASS</b>: (weight=1.0) `PeekMinDoesNotThrowExceptions`  
✔ <b>PASS</b>: (weight=1.5) `PercolateDownAssumesAlwaysFourChildren`  
> This implementation assumes that all elements with at least one child have exactly 4 children while
> percolating.

✔ <b>PASS</b>: (weight=1.5) `PercolateDownChecksOnlyThreeChildren`  
> This implementation uses a faulty downwards percolation that ignores one child when percolating
> elements with four children.

✔ <b>PASS</b>: (weight=1.5) `PercolateDownSwapsOnlyOnce`  
> This implementation only percolates downwards by one level at a time instead of percolating until
> the heap is valid.

✔ <b>PASS</b>: (weight=1.5) `PercolateUpSwapsOnlyOnce`  
> This implementation only percolates upwards by one level at a time instead of percolating until the
> heap is valid.

✔ <b>PASS</b>: (weight=1.0) `RemoveMinDoesNotThrowExceptions`  
✔ <b>PASS</b>: (weight=1.0) `RemovingDoesNotRemoveFromIndicesDictionary`  
> This implementation does not correctly remove elements from the dictionary of indices during
> `remove` or `removeMin`.

✔ <b>PASS</b>: (weight=1.0) `ReplaceDoesNotAddToIndicesDictionary`  
> This implementation has an implementation of `remove` that removes the old value from the indices
> dictionary, but does not add the new value to it.

✔ <b>PASS</b>: (weight=1.5) `SwapDoesNotUpdateIndicesDictionary`  
> This implementation does not correctly track indices of elements when swapping their positions in
> the array.

## TestSorter

Score: 4 / 5

✔ <b>PASS</b>: (weight=1.0) `AllOk`  
✔ <b>PASS</b>: (weight=1.0) `AssumesKAlwaysLessThanN`  
✔ <b>PASS</b>: (weight=1.0) `DoesNotHandleKEqualsZero`  
> This implementation does not explicitly handle the $`k = 0`$ case, which usually ends up throwing an
> exception (except in some corner cases).

✔ <b>PASS</b>: (weight=1.0) `DoesNotThrowExceptionForNegativeK`  
❌ <b>FAIL</b>: (weight=1.0) `MutatesInput`  
> This implementation returns the correct output, but mutates the input list.
> The tests you wrote incorrectly reported this buggy implementation as correct.

✔ <b>PASS</b>: (weight=1.0) `ReturnsLastKElements`  
> This implementation sorts and returns the last $`k`$ elements of the input list.
