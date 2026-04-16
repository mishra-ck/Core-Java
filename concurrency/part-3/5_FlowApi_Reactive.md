Flow API & Reactive Streams
---
Flow API was introduced in Java 9 to provide a standard set of interfaces for asynchronous, 
stream-oriented data processing with `non-blocking backpressure`.

1. Core Concept: Backpressure
    ```
   In standard  data Stream, if a Producer sends data faster than a Consumer can process it,
   then consumer will eventually run out of memory and crash.
   
   Backpressure is a feedback mechanism, allows a Consumer to tell Producer thet it can handle only 
   certain number of items. Do not send more than that.
   ```
2. Four Core Interfaces
   ```
   Flow API(java.util.concurrent.Flow) is built on 4 interfaces:
   
   1. Publisher<T> : produces item of type T, subscribe(Subscriber s).
   
   2. Subscriber<T> : consumes items, has 4 lifecycle methods.
      2.1 - onSubscribe(Subscription s): called first, gives the subscriber the connection.
      2.2 - onNext(T item): called when a new item arrives.
      2.3 - onError(Throwable t): called if something goes wrong.
      2.4 - onComplete(): called when the publisher is done sending data.
   
   3. Subscription : link between Publisher and Subscriber. It controls backpressure via two methods:
      3.1 - request(long n): the subscriber calls this to ask for n items.
      3.2 - cancel(): the subscriber calls this to stop receiving items.
   
   4. Processor<T,R> : acts as both a Subscriber and a Publisher. 
      It receives data of type T, transforms it, and publishes it as type R.
   
   ```
3. How the interfaces interact ?
   ```
   1. Connection: The Subscriber calls publisher.subscribe(subscriber).
   2. Acknowledgment: The Publisher creates a Subscription object and passes it back to the Subscriber by calling subscriber.onSubscribe(subscription).
   3. The Ask (Backpressure): The Subscriber saves the Subscription and calls subscription.request(n) to ask for a specific number of items.
   4. The Delivery: The Publisher sends up to n items by calling subscriber.onNext(item) for each one.
   5. Completion: Once all data is sent, the Publisher calls subscriber.onComplete().
   
   ```
4. Concept Implementation
```java 

// create a slow subscriber to understand how backpressure works 
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.Flow;

public class FlowApiDemo {
    
   public static void main(String[] args) throws InterruptedException {
       
      // 1. Create a Publisher powered by Virtual Threads
      try (var executor = Executors.newVirtualThreadPerTaskExecutor();
           var publisher = new SubmissionPublisher<String>(executor, Flow.defaultBufferSize())) {

         // 2. Create and register our subscriber
         var subscriber = new SlowSubscriber("Worker-1");
         publisher.subscribe(subscriber);

         // 3. Publish a burst of data extremely fast
         List<String> itemsToPublish = List.of(
                 "Task A", "Task B", "Task C", "Task D", "Task E"
         );

         System.out.println("Publisher starting to emit items...");
         for (String item : itemsToPublish) {
            System.out.println("Publisher submitting: " + item);
            publisher.submit(item); // This sends data to the buffer
         }

         System.out.println("Publisher finished submitting items.");

         // 4. Close the publisher to send the onComplete signal
         publisher.close();

         // Wait a bit, let virtual threads finish processing
         Thread.sleep(6000);
      }
   }
}

// create a publisher and run the flow
public class SlowSubscriber implements Flow.Subscriber<String> {
    
   private Flow.Subscription subscription;
   private final String name;

   public SlowSubscriber(String name) {
      this.name = name;
   }

   @Override
   public void onSubscribe(Flow.Subscription subscription) {
      this.subscription = subscription;
      System.out.println(name + " Subscribed! Requesting first 2 items...");
      // Ask for only 2 items initially - Backpressure
      subscription.request(2);
   }

   @Override
   public void onNext(String item) {
      System.out.println(name + " received: " + item);
      try {
         Thread.sleep(1000); // Simulate slow processing
      } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
      }
      System.out.println(name + " finished processing. Requesting 1 more...");
      // Request the next item only AFTER processing the current one
      subscription.request(1);
   }

   @Override
   public void onError(Throwable throwable) {
      System.err.println(name + " encountered an error: " + throwable.getMessage());
   }

   @Override
   public void onComplete() {
      System.out.println(name + " is done, all items are processed");
   }
}

```