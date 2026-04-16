Structured Concurrency - ensures forked subtasks don't outlive their parent scope
---
In traditional concurrency using ExecutorService , subtasks can easily outlive their parents.
Leading to `orphan threads` and resource leaks.

### Core Problem - Unstructured Concurrency
In ExecutorService tasks are independent, if main thread fails or interrupted, subtasks keep running in the background.

### Why it is risky ?
```
✅ Thread Leaks : Subtasks might run forever if not manually cancelled
✅ Observability : Thread dump will not show any relationship between parent and its child.
✅ Error Handling : If one subtask fails, others continue wasting resources even if the result is irrelevant.

```
### The Solution - StructuredTaskScope
```
StructuredTaskScope ensures that all subtasks finish(pass/fail) before the scope closes.
Workflow:
✅ Open Scope: Use try-with-resources.
✅ Fork: Start subtasks.
✅ Join: Wait for subtasks to finish.
✅ Handle Results: Process the output or handle exceptions.

```
