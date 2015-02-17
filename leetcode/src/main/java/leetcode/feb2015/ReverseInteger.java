package leetcode.feb2015;

/*
 * https://oj.leetcode.com/problems/reverse-integer/
 */
public class ReverseInteger {

    public int reverse(int x) {
    	int y = 0;
        
        while(x != 0) {
        	int yy = y*10 + x%10;
        	
        	if ((yy - x%10)/10 != y) return 0;
        	else y = yy;
        	
        	x = x/10;	
        }
    	return y;
    }
	
	public static void main(String[] args) {
		System.out.println(new ReverseInteger().reverse(1534236469));
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.MAX_VALUE);
		//System.out.println(-2%10);
	}
/*
 * 9646324350
 * 4294967295
 */
}
