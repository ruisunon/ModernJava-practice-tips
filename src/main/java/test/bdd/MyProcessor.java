package test.bdd;

public class MyProcessor {
  private String processName;
  private MyService myService;

  public MyProcessor(String processName, MyService myService) {
    this.processName = processName;
    this.myService = myService;
  }

  public void process() {
    int returnInteger = processName != null ? myService.apply(processName) : -1;
    System.out.println("My Integer is: " + returnInteger);
  }
}