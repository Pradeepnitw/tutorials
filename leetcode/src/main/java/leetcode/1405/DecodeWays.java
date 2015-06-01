package leetcode.may2014;

public class DecodeWays {
    public int numDecodings(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(s.length()==0) return 0;
        int[] hist = new int[2];
        hist[0]=1;
        hist[1]=1;
        
        for(int i=0;i<s.length();i++){
            int temp=0;
            if(s.charAt(i)!='0')
                temp+=hist[1];
            if(i>0){
                int a = Integer.parseInt(s.substring(i-1,i+1));
                if(s.charAt(i-1)!='0' && a<=26 && a>=1)
                    temp+=hist[0];
            }
            hist[0]=hist[1];
            hist[1]=temp;
        }
        
        return hist[1];
    }
}
