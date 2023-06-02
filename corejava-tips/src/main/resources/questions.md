
https://www.java2novice.com/java_interview_questions/java-8-stream-terminal-operations/

-.What is difference between CountDownLatch and CyclicBarrier in Java?

Answer:
Both CyclicBarrier and CountDownLatch are used to implement a scenario where one Thread waits for one or more
  Thread to complete their job before starts processing. The differences are:

1) CyclicBarrier is resulable, CountDownLatch is not.
2) Both CyclicBarrier and CountDownLatch wait for fixed number of threads.
3) CountDownLatch is advanceable but CyclicBarrier is not.

--. Can we reuse Java-8 Streams?
Answer:
A stream should be operated on (invoking an intermediate or terminal stream operation) only once. 
A stream implementation may throw IllegalStateException if it detects that the stream is being reused.

So the answer is no, streams are not meant to be reused.

--. Does Java 8 Lambda supports recursive call?
    
    Answer:
    In general, Lambda implementations are mostly anonymous functions. In recursion, a method calls itself.
     Since anonymous function doesnot have a name, it cannot be called by itself. That means an anonymous Lambda 
     can not be called by itself. But if we have a Lambda function declaration as a member variable or class variable, 
    Java 8 supports recursion with Lambda functions. Java 8 does not support Lambda function declaration with local variable.
--. What is the difference between HTTP methods GET and POST?
    
    Answer:
    HTTP works as a request-response protocol between a client and server. A web browser may be the client, 
    and an application on a computer that hosts a web site may be the server. 
    Two commonly used HTTP methods to make a request to the server are GET and POST.
    
    When you use GET method, the data will be sent to the server as a query parameters.
     These are appended to the URL as a key value pair. In the below URL, you can see how data is passed to the server
      as key value pair. These values will be visible at the address bar. URL character length is limited,
       so you can not use it if you are sending large data. GET is recommended to use for querying information from server, 
       kind of search operations. GET requests should never be used when dealing with sensitive data.
    
    http://java2novice.com/history?name=madhu&language=java
    
    POST method sends data as part of HTTP message body, data sent to the server, will not be visible to the user. 
    POST requests cannot be cached. It does not have any character length restrictions. 
    POST is recommended to submits data to be processed to a specified resource.--
--. Can Java 8 default methods override equals, hashCode and toString?
Can Java 8 default methods override equals, hashCode and toString?

Answer:
The methods declared in java.lang.Object class can not be override in Java 8 default methods. It is forbidden 
to define default methods in interfaces for methods in java.lang.Object.

Default interface methods can be overwritten in classes implementing the interface and the class implementation 
of the method has a higher precedence than the interface implementation, even if the method is implemented in a superclass.
 Since all classes inherit from java.lang.Object, the methods in java.lang.Object would have precedence over the default
  method in the interface and be invoked instead.