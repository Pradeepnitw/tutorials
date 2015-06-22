package interview.airbnb;

public class TwoDArrayIterator {
	char[][] array = null;
	int[] len = null;
	
	public void runCode() {
		array = new char[5][5];
		len = new int[array.length];
		
		array[0][0] = '0';
		array[0][1] = '1';
		len[0] = 2;
		
		array[1][0] = 'A';
		array[1][1] = 'B';
		array[1][2] = 'C';
		len[1] = 3;
		
		len[2] = 0;
		
		array[3][0] = '2';
		array[3][1] = '3';
		array[3][2] = '4';
		array[3][3] = '5';
		len[3] = 4;
		
		array[4][0] = 'D';
		array[4][1] = 'E';
		array[4][2] = 'F';
		array[4][3] = 'G';
		array[4][4] = 'H';
		len[4] = 5;
		
		printArray();
	}
	
	public char remove(int i, int j) {
		if (i < 0 || i > len.length - 1 || len[i] == 0
				|| j < 0 || j > len[i] - 1) throw new IllegalArgumentException();
		
//		System.out.println("Removing i=" + i + " j=" + j);
		
		char res = array[i][j];
		for (int k = j; k < len[i]-1; k++) {
			array[i][k] = array[i][k+1];
//			System.out.print("[" + i + ", " + k + "] ");
		}
		len[i] = len[i] - 1;
		return res;
	}
	
	public class Iterator {
		int i;
		int j;
		
		public Iterator() {
			i = 0;
			j = 0;
			while (i < len.length && len[i] == 0) {
				i++;
			}
			
			// Empty array
			if (i == len.length) {
				i = -1;
				j = -1;
			}
		}
		
		public boolean hasNext() {
			if (i == -1) return false;
			
//			if (j == len[i] - 1) {
//				int ii = i + 1;
//
//				while (ii < len.length && len[ii] == 0) {
//					ii++;
//				}
//				// End of array
//				if (ii == len.length) {
//					i = -1;
//					j = -1;
//					return false;
//				}
//			}
			
			return true;
		}
		
		public char next() {
			
			char res = array[i][j];
			
			if (j >= len[i] - 1) {
				i++;
				j = 0;
				while (i < len.length && len[i] == 0) {
					i++;
				}
				
				if (i == len.length) {
					i = -1;
				}
			} else {
				j++;
			}
			
			
			return res;
		}
		
		public char remove() {
			char res = ' ';
			if (j > 0) {
				res = TwoDArrayIterator.this.remove(i, j-1);
				j--;
			} else {
				int ii = i-1;
				while (ii >= 0 && len[ii] == 0) {
					ii--;
				}
				res = TwoDArrayIterator.this.remove(ii, len[ii]-1);
			}
			
			return res;
		}
		
	}
	
	public void printArray() {
		for (int i = 0; i < len.length; i++) {
			if (len[i] != 0) {
				System.out.print(i + " : ");
				for (int j = 0; j < len[i]; j++) {
					System.out.print(array[i][j]);
				}
				System.out.println();
			}
		}
		
		Iterator it = new Iterator();
		Iterator it2 = new Iterator();
		System.out.print(it.next() + " ");
		System.out.print(it.next() + " ");
		System.out.print(it.next() + " ");
		System.out.print(it.next() + " ");
		System.out.print(it.next() + " ");
		System.out.print(it2.next() + "! ");
		System.out.print(it2.next() + "! ");
		System.out.print(it2.next() + "! ");
		System.out.print(it2.next() + "! ");
		System.out.print("-" + it.remove() + " ");
		System.out.print(it.next() + " ");
		System.out.print(it2.next() + "! ");
		System.out.print(it2.next() + "! ");
		System.out.println();
		
		for (int i = 0; i < len.length; i++) {
			if (len[i] != 0) {
				System.out.print(i + " : ");
				for (int j = 0; j < len[i]; j++) {
					System.out.print(array[i][j]);
				}
				System.out.println();
			}
		}
		
	}
	
	public static void main(String[] arg) {
		new TwoDArrayIterator().runCode();
	}
}
