Design an implement an in-memory leaderboard for a gaming system. This leaderboard needs to maintain player scores and rankings. Here are the core requirements:

Player Updates:

Players can update their scores, and the system should rank players based on their scores.
The leaderboard should always return the top N players when queried, sorted by their score in descending order.
Precomputed Rankings:
The ranking of players should be precomputed efficiently when updating the score, so retrieving the top players is fast.

Efficiency:
The system should be able to handle frequent updates and queries efficiently. A player's score can change multiple times, and you should ensure that updates do not degrade performance.

Thread Safety:
Assume that multiple threads may access and modify the leaderboard concurrently. Your system must be thread-safe, meaning it should handle concurrent updates and queries without causing data inconsistency or crashes.