package test.bdd;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

public class ProcessorTest {
  @Test
  public void processTest() {
    MyService myService = Mockito.mock(MyService.class);
    String processName = "dummy-process-name";
    BDDMockito.given(myService.apply(processName)).willReturn(10);
    MyProcessor myProcessor = new MyProcessor(processName, myService);
    myProcessor.process();
    BDDMockito.then(myService).should().apply(processName);
  }
}
