PROBLEM 2: Thread-safe Singleton (multiple implementations) 
---
 ### Eager Initialization - Simple and Safe
```java
public class EagerSingleton{
    
    private static final EagerSingleton INSTANCE = new EagerSingleton();
    // private constructor - access within class
    private EagerSingleton(){ }
    
    public static EagerSingleton getInstance(){
        return INSTANCE ;
    }
}
```
### Double check locking - Lazy initialization and efficient 
```java
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
### Initialization on-demand Holder (Lazy, no synchronization overhead)
```java
public class HolderSingleton{
    
    private HolderSingleton(){ }
    
    // Holder inner class to maintain instance
    private static class Holder{
        static final HolderSingleton INSTANCE = new HolderSingleton();
    }
    
    public static HolderSingleton getInstance(){
        return Holder.INSTANCE;
    }
}
```
### Enum (Serialization safe, reflection safe - BEST)
```java
public enum EnumSingleton{
    INSTANCE;

    private final String url;
    private final String username;
    private Connection connection;

    // Constructor (called once by JVM)
    DatabaseConnection() {
        this.url      = System.getenv().getOrDefault("DB_URL",  "jdbc:postgresql://localhost:5432/mydb");
        this.username = System.getenv().getOrDefault("DB_USER", "admin");
        this.connection = null;
    }
    
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, username, "secret");
        }
        return connection;
    }
}
```