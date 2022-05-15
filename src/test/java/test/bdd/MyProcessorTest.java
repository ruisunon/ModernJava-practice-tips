package test.bdd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

class MyProcessorTest {
  @Test
  public void processTest() {
    MyService myService = Mockito.mock(MyService.class);
    String processName = "dummy-process-name";
    BDDMockito.given(myService.apply(processName)).willReturn(10);
    MyProcessor myProcessor = new MyProcessor(processName, myService);
    myProcessor.process();
    BDDMockito.then(myService).should().apply(processName);
  }
  @Test
  public void processTest2(){
    MyService myService = Mockito.mock(MyService.class);
    BDDMockito.willReturn(10).given(myService).apply("Testing");
    MyProcessor myProcessor = new MyProcessor("Testing", myService);
    Assertions.assertEquals(10, myProcessor.process());
  }
  }