## Difference between the logic of going to sync block again from outer loop vs having a inner while loop for matching order 




Both Code1 and Code2 are implementations of a `Runnable` class `OrderExecutor` that handles the execution of matching orders from an order book. There are some notable differences between them both in terms of logic and potential performance.

### Differences in Execution Logic:

1. **Loop Structure and Synchronization:**
    - **Code1:**
      ```java
      synchronized (state) {
          while (state.orderBook.isEmpty()) {
              state.wait();
          }
          matchingOrder = getMatchingOrder();
          if (matchingOrder == null) {
              state.wait();
          ...
      ```
      The code synchronizes on `state` and waits if the order book is empty. If no matching order is found, it waits again. The outer loop starts again if notified.

    - **Code2:**
      ```java
      synchronized (state) {
          while (true) {
              while (state.orderBook.isEmpty()) {
                  state.wait();
              }
              matchingOrder = getMatchingOrder();
              if (matchingOrder == null) {
                  state.wait();
              } else {
                  ...
                  break;
              }
          }
      ```
      The code introduces an inner `while (true)` loop within the synchronized block. If no matching order is found, it waits again. If a matching order is found, it breaks out of the inner loop while remaining in the outer `while (true)` loop.

2. **Control Flow and Loop Breaking:**
    - **Code1:**
      If `matchingOrder` is null, the inner `if` condition makes the thread wait again. The outer loop starts over each time a notification happens before grabbing a lock on the state.

    - **Code2:**
      Uses nested loops where the inner loop keeps trying to find a matching order, breaking out only when a match is found. This means the search for a matching order will continuously repeat until successful within a single synchronized block.

### Impact on Performance:

1. **Lock Contention:**
    - **Code1** might release and re-acquire the lock more frequently because it returns to the synchronized block’s beginning every time it re-enters the loop.
    - **Code2** holds onto the lock for the entire duration of the inner loop until a matching order is found, which could be more efficient in scenarios where frequent lock releasing/acquiring is a bottleneck.

2. **Responsiveness:**
    - **Code1** potentially allows other threads more opportunities to acquire the lock on `state` because it exits the synchronized block more frequently.
    - **Code2** might hold onto the lock longer, potentially making other threads wait more if the order matchmaking takes a long time.

### Which One is Correct?

Both implementations are logically correct; however, the choice between them depends on what behavior and performance characteristics are desired:

- **Code1** might be preferable if you want to give other threads more opportunities to act on the `state` by frequently releasing the lock.
- **Code2** might be preferable if you want to minimize the cost of repeatedly acquiring and releasing the lock on `state` by holding the lock until a matching order is definitively found or no longer can be found.

### Conclusion:

- **Execution Logic**: Code2 re-evaluates matches within the synchronized block more efficiently.
- **Performance**: Code2 might be more performant due to reduced lock contention but less responsive to other threads due to longer lock holding.

It's crucial to profile both approaches in your specific application context to make a data-driven decision on which performs better given your workload characteristics.




## ConcurrentMap vs sync(this) for stock exchange functions:

ConcurrentMaps will provide the atomic operations but if you write code something like this : 

```java
  public void addStock(Stock stock) {
        if (stockWorkers.containsKey(stock.getSymbol())) {
            throw new StockAlreadyPresent();
        }
        StockWorker stockWorker = new StockWorker(stock);
        stockWorkers.put(stock.getSymbol(), stockWorker);
        stockWorker.addWorkerCount(DEFAULT_WORKER_COUNT);
        System.out.println("Added stock: " + stock);
    }
```

It may cause problem as containsKey/get , put are written in separate line, ie we are'nt using the putIfAbsent, computeIfAbsent, etc.
So different lines have read-write problems, to avoid that use single statement operations or use synchronized blocks
