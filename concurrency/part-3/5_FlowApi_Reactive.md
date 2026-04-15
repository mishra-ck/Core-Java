Flow API & Reactive Streams
---
Flow API was introduced in Java 9 to provide a standard set of interfaces for asynchronous, 
stream-oriented data processing with `non-blocking backpressure`.

1. Core Concept: 
    ```
   In standard  data Stream, if a Producer sends data faster than a Consumer can process it,
   then consumer will eventually run out of memory and crash.
   
   Backpressure is a feedback mechanism, allows a Consumer to tell Producer thet it can handle only 
   certain number of items. Do not send more than that.
   ```

