package test.bdd;

public class AClass {
	  void method(int i)
	    {
	        //method(int)
	    }
	  void method(Integer i) {
		  
	  }
	  
	  public static void main(String[] args)
	    {
	        //Integer i = new Integer(null); // numberFormatException: null
	 
	       // String s = new String(null);// ambigious
	        
	        String s = "ONE"+1+2+"TWO"+"THREE"+3+4+"FOUR"+"FIVE"+5; 
	        int i = 10 + + 11 - - 12 + + 13 - - 14 + + 15;
	        
	        System.out.println(i);
	        
	        System.out.println(s);
	        Integer i1 = 127;
	        
	        Integer i2 = 127;
	 
	        System.out.println(i1 == i2);
	 
	        Integer i3 = 128;
	 
	        Integer i4 = 128;
	 
	        System.out.println(i3 == i4);
	    }
}
