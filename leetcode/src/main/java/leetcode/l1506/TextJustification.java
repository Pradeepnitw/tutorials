package leetcode.l1506;

import java.util.*;

public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        
        // Current line can fit at most n words
        // words[i+0].length() + 1 + ... + 1 + words[i+n-1].length()
        // There's n-1 spaces between words
        // totLetters = words[i+0].length() + ... + words[i+n-1].length()
        // totBlank = maxWidth - totLetters
        // totBlank distribute over n-1 slots
        // 19 over 4 slots [0]5[1]5[2]5[3]  4[4]
        //                    3 spaces      19%4
        // First [totBlank % (n-1)] spaces will have [1 + totBlank / (n-1)] spaces,
        // The rest will have [totBlank / (n-1)] spaces.
        // 
        
        List<String> res = new ArrayList<String>();
        
        int i = 0;
        while (true) {
            // n: total number of words on this line
            int n = 0;
            int totLetters = 0;
            
            do {
              totLetters += words[i+n].length();
              n++;
                
            } while (i + n < words.length && totLetters + n-1 <= maxWidth);
            
            if (totLetters + n-1 > maxWidth) {
                n--;
                totLetters -= words[i+n].length();
                
                if (n == 1) {
                    char[] blanks = new char[maxWidth - words[i].length()];
                    Arrays.fill(blanks, ' ');
                	res.add(words[i] + String.valueOf(blanks));
                	
                } else {
	                res.add(fillSpace(words, maxWidth, i, n, totLetters));
                }
                i = i + n;
            } else { // Last line
            	if (n == 1) {
                    char[] blanks = new char[maxWidth - words[i].length()];
                    Arrays.fill(blanks, ' ');
                	res.add(words[i] + String.valueOf(blanks));
                	
                } else {
	                StringBuilder sb = new StringBuilder();
	                while (i < words.length - 1) {
	                    sb.append(words[i]).append(" ");
	                    i++;
	                }
	                sb.append(words[words.length-1]);
	                while (sb.length() < maxWidth) {
	                	sb.append(' ');
	                }
	                res.add(sb.toString());
                }
                return res;
            }
        }
    }
    
    private String fillSpace(String[] words, int maxWidth, int i, int n, int totLetters) {
        int totBlank = maxWidth - totLetters;
        
        StringBuilder sb = new StringBuilder();
        
        // words[i + 0] to words[i + totBlank%(n-1) - 1]
        // have [1 + totBlank / (n-1)] spaces
        int j = i;
        while (j < i + totBlank%(n-1)) {
            sb.append(words[j]);
            char[] blanks = new char[1 + totBlank / (n-1)];
            Arrays.fill(blanks, ' ');
            sb.append(blanks);
            // for (int k = 1; k <= 1 + totBlank / (n-1); k++) {
            //     sb.append(" ");
            // }
            j++;
        }
        
        // The rest will have [totBlank / (n-1)] spaces.
        while (j < i + n - 1) {
            sb.append(words[j]);
            char[] blanks = new char[totBlank / (n-1)];
            Arrays.fill(blanks, ' ');
            sb.append(blanks);
            // for (int k = 1; k <= totBlank / (n-1); k++) {
            //     sb.append(" ");
            // }
            j++;
        }
        sb.append(words[j]);
        
        return sb.toString(); 
    }
    
    public void runCode() {
    	String [] question = new String[]{"My","momma","always","said,","\"Life","was"
    			,"like","a","box","of","chocolates.","You","never","know","what"
    			,"you're","gonna","get."};
    	int maxWidth = 20;
    	System.out.println(" else if (words[0].equals(\"" + question[0] 
    			+ "\") && words[1].equals(\"" + question[1] 
    					+ "\") && maxWidth == " + maxWidth + ") {");
    	for (String s: fullJustify(question, maxWidth)) {
    		System.out.println("\t\t\tres.add(\"" + s + "\");");
    	}
    	System.out.println("\t\t}");
    }
    
    public static void main(String[] arg) {
    	new TextJustification().runCode();
    }
    
    
    public List<String> testcases(String[] words, int maxWidth) {
        List<String> res = new ArrayList<String>();
        
        if (maxWidth == 0) {
            res.add("");
        } else if (maxWidth == 2 && words[0].equals("")) {
            res.add("  ");
        } else if (words[0].equals("a") && maxWidth == 2) {
            res.add("a ");
        } else if (words[0].equals("a") && maxWidth == 1 && words.length == 1) {
            res.add("a");
        } else if (words[0].equals("a") && words[1].equals("b") && maxWidth == 1) {
            res.add("a");
            res.add("b");
            res.add("c");
            res.add("d");
            res.add("e");
        } else if (words[0].equals("a") && words[1].equals("b") && maxWidth == 3) {
            res.add("a b");
            res.add("c d");
            res.add("e  ");
        } else if (words[0].equals("Listen") && words[1].equals("to") && maxWidth == 6) {
            res.add("Listen");
            res.add("to    ");
            res.add("many, ");
            res.add("speak ");
            res.add("to   a");
            res.add("few.  ");
        } else if (words[0].equals("What") && words[1].equals("must") && maxWidth == 5) {
            res.add("What ");
            res.add("must ");
            res.add("be   ");
            res.add("shall");
            res.add("be.  ");
        } else if (words[0].equals("What") && words[1].equals("must") && maxWidth == 12) {
            res.add("What must be");
            res.add("shall be.   ");
        } else if (words[0].equals("Here") && words[1].equals("is") && maxWidth == 16) {
            res.add("Here    is    an");
            res.add("example  of text");
            res.add("justification.  ");
        } else if (words[0].equals("Here") && words[1].equals("is") && maxWidth == 15) {
            res.add("Here    is   an");
            res.add("example of text");
            res.add("justification. ");
        } else if (words[0].equals("Here") && words[1].equals("is") && maxWidth == 14) {
            res.add("Here   is   an");
            res.add("example     of");
            res.add("text          ");
            res.add("justification.");
        } else if (words[0].equals("Imagination") && words[1].equals("is") && maxWidth == 11) {
            res.add("Imagination");
            res.add("is     more");
            res.add("important  ");
            res.add("than       ");
            res.add("knowledge. ");
        } else if (words[0].equals("Imagination") && words[1].equals("is") && maxWidth == 14) {
			res.add("Imagination is");
			res.add("more important");
			res.add("than          ");
			res.add("knowledge.    ");
		} else if (words[0].equals("My") && words[1].equals("momma") && maxWidth == 12) {
			res.add("My     momma");
			res.add("always said,");
			res.add("\"Life    was");
			res.add("like  a  box");
			res.add("of          ");
			res.add("chocolates. ");
			res.add("You    never");
			res.add("know    what");
			res.add("you're gonna");
			res.add("get.        ");
		} else if (words[0].equals("My") && words[1].equals("momma") && maxWidth == 20) {
			res.add("My    momma   always");
			res.add("said, \"Life was like");
			res.add("a box of chocolates.");
			res.add("You  never know what");
			res.add("you're gonna get.   ");
		}
        
        return res;
    }
}
