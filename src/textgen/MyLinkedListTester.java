/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<String> shortList2;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
		shortList2 = new MyLinkedList<String>();
		shortList2.add("a");
		shortList2.add("b");
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		shortList.add("C");
		assertEquals("Get: check element 2 is correct ", (String)"C", shortList.get(2));
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		try {
			shortList.remove(1000);
			fail("check not working");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		try {
			shortList.remove(-1);
			fail("check not working");
		}
		catch (IndexOutOfBoundsException e) {
		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // DONE
		
		try {
			shortList.add(null);
			fail("check not working");
		}
		catch (NullPointerException e) {
		}
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// DONE
		assertEquals("size: check size is correct ", 2, shortList.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // DONE
		try {
			shortList.add(null);
			fail("check not working");
		}
		catch (NullPointerException e) {
		}
		
		try {
			shortList.add(1000,"test");
			fail("check not working");
		}
		catch (IndexOutOfBoundsException e) {
		}
		try {
			shortList.add(-1,"test");
			fail("check not working");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		shortList.add(1, "testIndex1");
		assertEquals("Get: check element @ 1 is correct ", (String)"testIndex1", shortList.get(1));
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		shortList.set(1, "Zangief");
		assertEquals("Get: check element @ 1 is correct ", (String)"Zangief", shortList.get(1));
	    
		try {
			shortList.set(1000,"test");
			fail("check not working");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		try {
			shortList.set(-1, "test");
			fail("check not working");
		}
		catch (IndexOutOfBoundsException e) {
		}
	}
	
	
	// TODO: Optionally add more test methods.
	
}