package interview.yahoo;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.BeforeClass;
import org.junit.Test;

public class QuickSortTest {
	private static QuickSort cut;
	
	@BeforeClass
	public static void beforeClass() {
		cut = new QuickSort();
	}
	
	@Test
	public void testCase1() {
		int[] a = new int[]{4,5,6,3,7,12,11};
		cut.quickSort(a);
	}
	
}
