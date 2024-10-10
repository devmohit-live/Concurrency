# Dining Philosopher Problem:
Statement :

Five(N) silent philosophers sit at a round table with bowls of spaghetti. Forks are placed between each pair of adjacent philosophers.

Each philosopher must alternately think and eat. However, a philosopher can only eat spaghetti when they have both left and right forks. Each fork can be held by only one philosopher and so a philosopher can use the fork only if it is not being used by another philosopher. After an individual philosopher finishes eating, they need to put down both forks so that the forks become available to others. A philosopher can take the fork on their right or the one on their left as they become available, but cannot start eating before getting both forks.

Eating is not limited by the remaining amounts of spaghetti or stomach space; an infinite supply and an infinite demand are assumed.

Design a discipline of behaviour (a concurrent algorithm) such that no philosopher will starve; i.e., each can forever continue to alternate between eating and thinking, assuming that no philosopher can know when others may want to eat or think.

[Similar LC Problem](https://leetcode.com/problems/the-dining-philosophers/description/)


![Image](https://upload.wikimedia.org/wikipedia/commons/7/7b/An_illustration_of_the_dining_philosophers_problem.png)

## Note:

### Problem with normal Wait and Notify:
In normal sync and notify approach: the threads waits for the other thread(inside sync block) to complete it's 
execution.
There is no such approach such that a thread can check if a lock is required adn then return immediately instead of 
waiting there for the resource to be free(getting a signal)

For this statement we can use the Java's Lock Apis.

The Lock Api's provide us with the functions to try acquiring a lock on a resource, if the resource is already occupied by some other thread then it immediately returns back to the caller.

In the lock api's we just have to make sure to unlock the resource we have acquired a lock on.
