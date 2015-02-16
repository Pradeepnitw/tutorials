package interview.yahoo;
/**
 * Yahoo technical phone screen 1
 * 
 * Assume no duplicate elements
 * 
 */
public class QuickSort {
	
	public void quickSort(int[] a) {
		if (a == null || a.length == 0) return;
		printArray(a, 0, a.length-1);
		System.out.println("=========");
	    quickSortRecursive(a, 0, a.length-1);
	    printArray(a, 0, a.length-1);
	}

	private void quickSortRecursive(int[] a, int lo, int hi) {
	    if (lo >= hi) return;
//	    // +++++
//	    int mid = lo + (hi-lo)/2;
//	    quickSortRecursive(a, lo, mid);
//	    quickSortRecursive(a, mid+1, hi);
//	    // +++++
	    
	    //a[lo] as the partition element
	    int i=lo+1;
	    int j=hi;
	    
	    // while (j >= i) will cause error i to go beyond hi
	    // or if you break when i==hi, it will go into dead loop
	    while (j > i) {
	        while (a[i] < a[lo]) {
	        	if (i == hi) break;
	            i++;
	        }
	            
	        while (a[j] > a[lo]) {
	        	if (j == lo) break;
	        	j--;
	        }

	        // +++
	        if (j > i) {
		        swap(a, i, j);
	        }
	        // +++
		    printArray(a, lo, hi);
	    }
	    
	    swap(a, lo, j);
	    printArray(a, lo, hi);
	    
	    
	    // since a[j] is at it's right position,
	    // we should break subarry into the part
	    // before j, and the part after j.
	    quickSortRecursive(a, lo, j-1);
	    quickSortRecursive(a, j+1, hi);
	}
	
	private void printArray(int[] a, int lo, int hi) {
		if (lo != 0) {
			for (int i = 0; i < lo; i++) {
				System.out.print("- ");
			}
		}
		
		for (int i = lo; i <= hi; i++) {
			System.out.print(a[i]+" ");
		}
		
		System.out.println();
	}

	private void swap(int[] a, int i, int j) {
	    int temp = a[i];
	    a[i] = a[j];
	    a[j] = temp;
	}

}
