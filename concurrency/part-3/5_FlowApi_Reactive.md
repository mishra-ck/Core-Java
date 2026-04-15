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