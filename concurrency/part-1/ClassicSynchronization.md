## `synchronized` keyword provide mutual exclusion by ensuring that only one thread can execute a specific block of code at a time.

Internally it relies on a concept called an `Intrinsic Lock`  or `Monitor`. 
Every single object has this in-visible lock attached to it.

---

### Let's try to understand how it works ?

✅ `Monitor`: When thread hits `synchronized` block, it must acquire monitor of the object. 
            If another thread already has it, then new thread will be blocked and put into `Wait set`.

✅ `Object Handler`: Inside JVM, `mark word` in an object's memory header stored the LOCK state(unlocked,biased or thin/fat locked).

✅ `Memory Visibility`: Apart from locking, it also ensures that all changes made by a thread before releasing lock 
            are visible to the next thread that acquired it.(known as: Happen-Before relationship).

