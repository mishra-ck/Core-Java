Virtual Threads - Project Loom
---
### Overview of Transition
```
* Earlier: 1 request = 1 platform thread = 1 OS thread.
* OS Threads are expensive(~1MB) in memory and require kernal context switching.
* Blocking I/O: thread sits idle waiting for network/disk/DB.

Virtual Thread Solution:
* 1 virtual thread(~1KB) heap, JVM managed, can have millions.
* Blocking I/O - JVM parks virtual threads, OS thread(carrier) is freed for other virtual threads.

```
### How it works ?
```
* Mounting: The JVM scheduler picks a Virtual Thread and "mounts" it onto a Carrier Thread. 
            The stack frames of the virtual thread are copied from the Heap to the carrier thread's stack.
* Execution: The code runs normally on CPU.
* Yield: When blocking i/o call happens, the virtual thread yields.
* Unmounting: The JVM takes a snapshot of the current stack and saves it back to the Heap.
             The carrier thread is now free to go do other work.
* Resuming: Once blocking call is done, the scheduler finds an available Carrier thread,
          restores the stack from Heap, and Virtual thread continues where if left out.
```
### Creating Virtual Threads
```java
class CreateVirtualThread{
    public static void main(String[] args) {
        // 1. Direct Start
        Thread vt = Thread.ofVirtual()
                .name(vt-1)
                .start(() ->{
                    String data = Files.readString(Path.of("file-location"));
                    process(data);
                });

        // 2. Via Executor (suggested)
        try(ExecutorService vtExecutor = Executors.newVirtualThreadPerTaskExecutor()){
            // every submitted task will get its own virtual thread
            List<Future<String>> futures  = IntStream.range(0,10000)
                    .mapToObj(i -> vtExecutor.submit( () -> processRequest(i)))
                    .collect(Collectors.toList());
        }
    }
}
```
### What to use Virtual Thread and what not ?
```
Good to use:
1. Web Servers/ Rest APIs with DB-Network calls
2. Batch processing with I/O 
3. Microservices making downstream calls, any I/O workload

NOT for:
1. CPU bound work
2. Already async reactive code( CompletableFuture pipelines)
3. Tight loops without I/O

PINNING - when Virtual threads get stuck on Carrier

Virtual thread gets PINNED(cannot unmount) when-
1. Inside synchronized block/method during I/O
2. Calling native methods(JNI)

NOTE: Pinned VT thread still works but blocks the carrier thread..
```
### Thread.Builder API (Java 21)
```java
class ThreadBuilderAPI{
    public static void main(String[] args) {
        // Platform thread builder
        Thread.Builder.OfPlatform platformBuilder = Thread.ofPlatform()
                .name("platform-",0)
                .priority(Thread.NORM_PRIORITY)
                .deamon(false)
                .stackSize(0);  
        
        // Virtual thread builder
        Thread.Builder.OfVirtual virtualBuilder = Thread.ofVirtual()
                .name("virtual-",0)
                .uncaughtExceptionHandler((t,e) -> log.error("VT error",e));
        
        // Start directly 
        Thread t = virtualBuilder.start(() -> doWork());
        
        // Use as ThreadFactory
        ThreadFactory vtFactory = Thread.ofVirtual().name("virtual-", 0).factory();
        ExecutorService exec = Executors.newThreadPerTaskExecutor(vtFactory);
    }
}

```