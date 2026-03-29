Concurrent HashMap
---

```java 
import java.util.Comparator;
import java.util.concurrent.atomic.LongAdder;

public class ConcurrentHashMapDemo {

    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        // Thread-safe basic ops:
        map.put("key", 1);
        map.get("key");
        map.remove("key");

        // Atomic-compound operations
        map.putIfAbsent("key", 1);  // put if absent
        map.replace("key", 1, 2);  // CAS : replace if current=1
        map.computeIfAbsent("key", k -> expensive()); // compute if absent
        map.computeIfPresent("key", (k, v) -> v + 1);  // compute if present
        map.compute("key", (k, v) -> v == null ? 1 : v + 1);  // always compute
        map.merge("key", 1, Integer::sum);  // merge : put or combine

        // parallel bulk operation(non-blocking)
        map.forEach(1, (k, v) -> System.out.println(k + "=" + v));
        long sum = map.reduceValues(1, v -> v.longValue(), Long::sum);
        String maxKey = map.reduceEntries(
                Map.Entry.comparingByValue(Comparator.reverseOrder())
        ).getKey();

        ConcurrentHashMap<String, LongAdder> frequency = new ConcurrentHashMap<>();
        String word = "hello";
        frequency.merge(word, new LongAdder(),(existing,newAdder) -> {
            existing.increment();
            return existing;
        });

    }
}

```