Fork/Join Framework, java 7+ designed for CPU bound **Divide and Conquer** parallel algorithms.
---
### Work-Stealing-Algorithm
The differentiator between a ThreadPoolExecutor and Fork-Join Pool is its `work-stealing-algorithm`.

### How it functions ?
1. **Thread-Local deque**: Every worker in pool maintain its own Double-Ended Queue(Deque) of tasks.
2. **LIFO for Owner**: When a worker thread generates new sub-tasks (using fork()), it pushes them onto the head of its own deque. When looking for work, it pops from the head (LIFO order). 
   This optimizes for cache locality because the most recently created sub-tasks are likely working on memory that is currently hot in the CPU cache.
3. **FIFO for Thieves**: If a worker thread empties its own deque, it doesn't just block. Instead, it randomly selects another worker thread and "steals" a task from the tail of that thread's deque (FIFO order). 
   This minimizes contention, as the owner is working on one end of the queue while the thief is stealing from the other.

### To implement Fork-Join algorithm, we should extend one of these classes.
1. `RecursiveAction`: For tasks that perform computation but do not return result(ex: modifying an array in place). 
2. `RecursiveTask<V>`: For tasks that compute and return result of type V (ex: parallel map-reduce)

### Core Architecture:

```
                        ┌──────────────────────────┐
                        │      ForkJoinPool        │
                        │  (Common Pool or Custom) │
                        └──────────┬───────────────┘
                                   │
              ┌────────────────────┼───────────────────┐
              │      LIFO          │      LIFO         │
     ┌────────▼───────┐  ┌──────── ▼──────┐  ┌────────▼───────┐
     │  Worker Thread │  │  Worker Thread │  │  Worker Thread │
     │  ┌──────────┐  │  │  ┌──────────┐  │  │  ┌──────────┐  │
     │  │  Deque   │  │  │  │  Deque   │  │  │  │  Deque   │  │
     │  │ (Tasks)  │  │  │  │ (Tasks)  │  │  │  │ (Tasks)  │  │
     │  └──────────┘  │  │  └──────────┘  │  │  └──────────┘  │
     └────────────────┘  └────────────────┘  └────────────────┘
              ▲                                        │
              │          Work Stealing(FIFO) ◄─────────┘
              └────────────────────────────────────────
```