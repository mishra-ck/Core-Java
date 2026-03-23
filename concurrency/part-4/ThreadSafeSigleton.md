PROBLEM 2: Thread-safe Singleton (multiple implementations) 
---
 
```java

// Eager Initialization - Simple and Safe 
public class EagerSingleton{
    
    private static final EagerSingleton INSTANCE = new EagerSingleton();
    // private constructor - access within class
    private EagerSingleton(){ }
    
    public static EagerSingleton getInstance(){
        return INSTANCE ;
    }
}

// Double check locking - Lazy initialization and efficient
public class DoubleCheckSingleton{
    
    // volatile is required
    private static volatile DoubleCheckSingleton instance;  
    
    private DoubleCheckSingleton(){ }
    
    public static DoubleCheckSingleton getInstance(){
        if(instance == null){
            synchronized (DoubleCheckSingleton.class){
                if(instance == null){
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}

```