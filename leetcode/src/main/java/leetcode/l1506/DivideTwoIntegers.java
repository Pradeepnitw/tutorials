package leetcode.l1506;

public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        //  l d  2*d 2^2*d 2^3*d...
        //  i 0  1   2     3    ...
        //  n 1  2   4     8    ...
        // List<Integer> l = new ArrayList<Integer>();
        // int res = 0;
        
        // l.add(divisor);
        // while (l.get(l.size()-1) + l.get(l.size()-1) < dividend) {
        //     l.add(l.get(l.size()-1) + l.get(l.size()-1));
        // }
        
        // res += Math.pow(2, l.size()-1);
        
        // int topIdx = l.size()-1;
        // int rest = dividend - l.get(topIdx);
        
        // while (topIdx > 0) {
        //     int i = topIdx - 1;
            
        //     while (l.get(i) > rest) {
        //         i--;
        //     }
            
        //     rest -= l.get(i);
        //     res += Math.pow(2, i);
        //     topIdx = i;
        // }
        // return res;
        if (divisor == 0) return Integer.MAX_VALUE;
        if (divisor == 1) return dividend;
        
        boolean isOpposite = false;
        boolean isMIN_MAX = false;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            isOpposite = true;
//            divisor = 0 - divisor;
//            if (divisor <= 0) return Integer.MAX_VALUE;
        }
        
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == Integer.MIN_VALUE) return 1;
            dividend -= divisor;
            isMIN_MAX = true;
        }
        
        if (divisor == Integer.MIN_VALUE) {
            // if (dividend < 0) 
                return 0;
            // else    
            //     return Integer.MAX_VALUE;
            
        }
        
        if (divisor < 0) {
        	divisor = 0 - divisor;
        }
        
        if (dividend < 0) {
            dividend = 0 - dividend;
            if (dividend <= 0) return Integer.MAX_VALUE;
        }
        
        int res = recurse(dividend, divisor, 1)[0];
        
        if (isMIN_MAX && res != Integer.MAX_VALUE) res++;
        
        if (isOpposite) {
            res = 0 - res;
        }
        
        return res;
    }
    
    // int[] {res, restOfDividend}
    private int[] recurse(int dividend, int divisor, int multiplier) {
        if (divisor > dividend) return new int[]{0, -1};
        
        int[] res = null;
        if (Integer.MAX_VALUE - divisor < divisor) {
            res = new int[]{-1, -1};
        } else {
            res = recurse(dividend, divisor + divisor, multiplier + multiplier);
        }
        if (res[1] == -1) {
            res[1] = dividend - divisor;
            res[0] = multiplier;
        } else if (res[1] >= divisor) {
            res[0] = res[0] + multiplier;
            res[1] = res[1] - divisor;
        }
        
        
        return res;
    }
    
    public void runCode() {
    	System.out.println(divide(-2147483648, -1));
//    	System.out.println(divide(1038925803, -2147483648));
    }
    
    public static void main(String[] arg) {
//    	int i = -2147483648;
//    	System.out.println(i);
//    	System.out.println(0 - i);
    	new DivideTwoIntegers().runCode();
    }
}
