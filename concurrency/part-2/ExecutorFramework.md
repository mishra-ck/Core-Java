Executor Framework & Thread Pools
---
### Why java introduced Executor framework ?

There are four core problems that make raw thread outdated :-
1. No thread reuse : New thread per task, means more cost for running app.
2. No thread cap : As number of requests ⬆️, no of threads ⬆️
3. No return values : threads returns void type.
4. No lifecycle control : hard to manage threads during execution.

Executor framework solved all these above problems.