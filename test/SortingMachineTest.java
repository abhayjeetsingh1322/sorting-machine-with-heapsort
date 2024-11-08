import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Abhayjeet S., Wesam K., Pravin H.
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    // TODO - add test cases for add, changeToExtractionMode, removeFirst,
    // isInInsertionMode, order, and size

    /**
     * Simple Test Case.
     */
    @Test
    public final void constructorTestNoArguments() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.constructorTest(ORDER);
        SortingMachine<String> sExpected = this.constructorRef(ORDER);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Tests add with empty sortingMachine.
     */
    @Test
    public final void testAddToEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true,
                "red");
        /*
         * Call method under test
         */
        s.add("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Tests add with 1 entry.
     */
    @Test
    public final void testAddNonEmptyToSingleEntries() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true, "red");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true,
                "red", "blue");
        /*
         * Call method under test
         */
        s.add("blue");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Tests add with multiple entry.
     */
    @Test
    public final void testAddNonEmptyToMultipleEntries() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true, "red",
                "blue", "green");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true,
                "red", "blue", "green", "yellow");
        /*
         * Call method under test
         */
        s.add("yellow");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Tests RemoveFirst with 1 entry to empty.
     */
    @Test
    public final void testRemoveFirstToEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, false, "red");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, false);
        /*
         * Call method under test
         */
        String sRemoved = s.removeFirst();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("red", sRemoved);
        assertEquals(sExpected, s);
    }

    /**
     * Tests RemoveFirst with two entries.
     */
    @Test
    public final void testRemoveFirstToSingleEntries() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, false, "red",
                "blue");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, false,
                "red");
        /*
         * Call method under test
         */
        String sRemoved = s.removeFirst();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("blue", sRemoved);
        assertEquals(sExpected, s);
    }

    /**
     * Tests RemoveFirst with multiple entries.
     */
    @Test
    public final void testRemoveFirstToMultipleEntries() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, false, "red",
                "blue", "green", "purple", "black");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, false,
                "red", "blue", "green", "purple");
        /*
         * Call method under test
         */
        String sRemoved = s.removeFirst();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("black", sRemoved);
        assertEquals(sExpected, s);
    }

    /**
     * Tests ChangeToExtractionMode with no entries.
     */
    @Test
    public final void testChangeToExtractionModeOfEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true);
        /*
         * Call method under test
         */
        s.changeToExtractionMode();
        sExpected.changeToExtractionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Tests ChangeToExtractionMode with 1 entry.
     */
    @Test
    public final void testChangeToExtractionModeOfSingleEntries() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true, "red");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true,
                "red");
        /*
         * Call method under test
         */
        s.changeToExtractionMode();
        sExpected.changeToExtractionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Tests ChangeToExtractionMode with multiple entries.
     */
    @Test
    public final void testChangeToExtractionModeOfMultipleEntries() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true, "red",
                "blue", "green");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true,
                "red", "blue", "green");
        /*
         * Call method under test
         */
        s.changeToExtractionMode();
        sExpected.changeToExtractionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Tests Size with zero entries.
     */
    @Test
    public final void testSizeOfEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true);
        /*
         * Call method under test
         */
        int val = s.size();
        final int tVal = 0;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(tVal, val);
        assertEquals(sExpected, s);
    }

    /**
     * Tests Size with multiple entries.
     */
    @Test
    public final void testSizeOfSingleEntries() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true, "red");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true,
                "red");
        /*
         * Call method under test
         */
        int val = s.size();
        final int tVal = 1;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(tVal, val);
        assertEquals(sExpected, s);
    }

    /**
     * Tests Size with multiple entries.
     */

    @Test
    public final void testSizeOfMultipleEntries() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true, "red",
                "blue", "green", "yellow", "purple", "white");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true,
                "red", "blue", "green", "yellow", "purple", "white");
        /*
         * Call method under test
         */
        int val = s.size();
        final int tVal = 6;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(tVal, val);
        assertEquals(sExpected, s);
    }

    /**
     * Tests IsInsertionMode with zero entries but true.
     */

    @Test
    public final void testIsInsertionModeOfEmptyTrue() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true);
        /*
         * Call method under test
         */
        boolean val = s.isInInsertionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, val);
        assertEquals(sExpected, s);
    }

    /**
     * Tests IsInsertionMode with zero entries but false.
     */

    @Test
    public final void testIsInsertionModeOfEmptyFalse() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, false);
        /*
         * Call method under test
         */
        boolean val = s.isInInsertionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, val);
        assertEquals(sExpected, s);
    }

    /**
     * Tests IsInsertionMode with multiple entries.
     */

    @Test
    public final void testIsInsertionModeOfSingleEntriesTrue() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true, "red");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true,
                "red");
        /*
         * Call method under test
         */
        boolean val = s.isInInsertionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, val);
        assertEquals(sExpected, s);
    }

    /**
     * Tests IsInsertionMode with multiple entries.
     */

    @Test
    public final void testIsInsertionModeOfSingleEntriesFalse() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, false, "red");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, false,
                "red");
        /*
         * Call method under test
         */
        boolean val = s.isInInsertionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, val);
        assertEquals(sExpected, s);
    }

    /**
     * Tests IsInsertionMode with multiple entries.
     */

    @Test
    public final void testIsInsertionModeOfMutipleEntriesTrue() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true, "red",
                "blue", "green");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true,
                "red", "blue", "green");
        /*
         * Call method under test
         */
        boolean val = s.isInInsertionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, val);
        assertEquals(sExpected, s);
    }

    /**
     * Tests IsInsertionMode with multiple entries.
     */

    @Test
    public final void testIsInsertionModeOfMultipleEntriesFalse() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, false, "red",
                "blue", "green");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, false,
                "red", "blue", "green");
        /*
         * Call method under test
         */
        boolean val = s.isInInsertionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, val);
        assertEquals(sExpected, s);
    }

    /**
     * Tests Order with zero entries.
     */

    @Test
    public final void testOrderOfEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true);
        /*
         * Call method under test
         */
        Comparator<String> val = s.order();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ORDER, val);
        assertEquals(sExpected, s);
    }

    /**
     * Tests Order with multiple entries.
     */

    @Test
    public final void testOrderOfSingleEntries() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true, "red");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true,
                "red");
        /*
         * Call method under test
         */
        Comparator<String> val = s.order();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ORDER, val);
        assertEquals(sExpected, s);
    }

    /**
     * Tests Order with multiple entries.
     */

    @Test
    public final void testOrderOfMultipleEntries() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true, "red",
                "blue", "green");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true,
                "red", "blue", "green");
        /*
         * Call method under test
         */
        Comparator<String> val = s.order();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ORDER, val);
        assertEquals(sExpected, s);
    }
}
