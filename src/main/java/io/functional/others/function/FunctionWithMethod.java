package io.functional.others.function;

import java.lang.reflect.Method;

import test.bdd.MyProcessor;

public class FunctionWithMethod {
	
	public static void main(String[] args) throws Exception{
		Class[] parameterTypes=new Class[1];
		parameterTypes[0] = String.class;
		Method method1=FunctionWithMethod.class.getMethod("method1", parameterTypes);
		FunctionWithMethod demo=new FunctionWithMethod();
		demo.method2(demo, method1, "hello man");
		demo.methodOfA();
	}
	 public void method1(String message) {
	        System.out.println(message);
	    }

	 public void method2(Object object, Method method, String message) throws Exception {
	        Object[] parameters = new Object[1];
	        parameters[0] = message;
	        method.invoke(object, parameters);
	 }
	 
	 int methodOfA()
	  {
	      return (true ? null : 0);
	  }
	  
}
