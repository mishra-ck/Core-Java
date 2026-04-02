## Practice `synchronized` keyword usages :
 
```java
/**  synchronized method - lock on `this` */

public class Demo1{  // Safe Counter 
    private int count = 0;
    public synchronized void increment(){
        count++ ;
    }
    public synchronized int getCount(){
        return count;
    }
}

```

```java 
/** synchronized static method - lock on Class */

import java.util.HashMap;
import java.util.Map;

public class Demo2 {  // Key-Value Store 
    private static Map<String, Object> map = new HashMap<>();
    
    public static synchronized void put(String key, Object value){
        map.put(key, value);
    }
}

```

```java 
/** synchronized block - lock on given object */ 

public class Demo3{
    private final Object lock = new Object();  // any lock object 
    private double balance;
    
    public void depositAmount(double amount){
        synchronized (lock){
            balance = balance+amount ;
        }
    }
    
    // NOTE : use lock properly to avoid deadlock 
}

```

```java 
/** Reentrant case - same thread can re-acquire the lock */

public class Demo4{
    public synchronized void firstLock(){
        System.out.println("First time lock acquired..");
        secondLock();  // re-acquires the same lock
    }
    public synchronized void secondLock() {
        System.out.println("Second time lock acquired..");
    }
}

```
