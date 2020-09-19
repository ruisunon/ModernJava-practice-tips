package io.java8plus.concurrency.callback;

public class AyncCallback {
  private EventListener mListener; // listener field

  // setting the listener
  public void registerOnEventListener(EventListener mListener)
  {
    this.mListener = mListener;
  }

  // My Asynchronous task
  public void doGeekStuff()
  {

    // An Async task always executes in new thread
    new Thread(new Runnable() {
      public void run()
      {

        // perform any operation
        System.out.println("Performing operation in Asynchronous Task");

        // check if listener is registered.
        if (mListener != null) {

          // invoke the callback method of class A
          mListener.onSuccessEvent();
        }
      }
    }).start();
  }

  // Driver Program
  public static void main(String[] args)
  {

    AyncCallback obj = new AyncCallback();
    EventListener mListener = new EventListener() {
      public void onSuccessEvent()
      {
        System.out.println("Performing callback after Asynchronous Task");
        // perform some routine operation
      }
      public void onFailEvent()
      {
        System.out.println("Performing callback after Asynchronous Task");
        // perform some routine operation
      }
    };
    obj.registerOnEventListener(mListener);
    obj.doGeekStuff();
  }
}
