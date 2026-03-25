# 🎬 Problem 10: Multi-Level Cache System

## 📌 Problem Statement

Design a multi-level caching system for a video streaming platform that optimizes data retrieval speed by using different storage layers.

The system should:

* Use multiple cache levels (L1, L2, L3)
* Implement **LRU eviction policy**
* Promote frequently accessed data to faster caches
* Minimize access time for users

---

## 🎯 Objectives

* Implement **3-level caching system**
* Achieve fast data access (O(1))
* Use **LRU (Least Recently Used)** eviction
* Promote data between cache levels
* Simulate real-world caching behavior

---

## 🧠 System Design

### Cache Levels

```text
L1 Cache → Fastest (Memory, small size)
L2 Cache → Medium (SSD, larger size)
L3 → Database (Slowest, all data)
```

---

## ⚙️ Approach

### 1. LRU Cache Implementation

We use:

```java
LinkedHashMap<K, V> (access-order = true)
```

This automatically maintains LRU order.

---

### 2. Data Flow

```text
Request → L1 → L2 → L3 (Database)
```

* If found in L1 → return immediately
* If found in L2 → promote to L1
* If found in DB → store in L2

---

## 🔄 Cache Promotion Strategy

* L2 → L1 when accessed
* L3 → L2 on miss
* Frequently accessed items move upward

---

## ⏱️ Time Complexity

| Operation      | Complexity |
| -------------- | ---------- |
| Get Video      | O(1)       |
| Insert Cache   | O(1)       |
| Eviction (LRU) | O(1)       |

---

## 📊 Example

```text
getVideo("video1") → L3 HIT → stored in L2
getVideo("video1") → L2 HIT → promoted to L1
getVideo("video1") → L1 HIT
```

---

## ⚡ Key Concepts

* HashMap (fast lookup)
* LRU Cache (LinkedHashMap)
* Cache Hierarchy
* Cache Promotion
* Performance Optimization

---

## 🚀 Real-World Applications

* Video streaming platforms
* Content Delivery Networks (CDN)
* Database caching
* Web applications

---

## 🔧 Possible Improvements

* Use **Redis for distributed caching**
* Add **cache invalidation strategy**
* Track **cache hit/miss ratio**
* Use **asynchronous loading**
* Add **multi-threading support**

---

## 🏁 Conclusion

This system demonstrates how multi-level caching significantly improves performance by reducing access time and efficiently managing frequently used data, similar to real-world systems like video streaming platforms.
