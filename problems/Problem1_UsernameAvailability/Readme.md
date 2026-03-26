# 👤 Problem 1: Social Media Username Availability Checker

## 📌 Problem Statement

Design a system to check whether a username is available in real-time for a social media platform with millions of users.

The system should:

* Check username availability in **O(1)** time
* Suggest alternative usernames if taken
* Track frequency of attempted usernames
* Handle high concurrent requests

---

## 🎯 Objectives

* Fast username lookup using **HashMap**
* Generate meaningful username suggestions
* Track most attempted usernames
* Ensure scalability for large user base

---

## 🧠 Approach

### 1. Data Structures Used

```java
HashMap<String, Integer> usernameMap       // username → userId
HashMap<String, Integer> attemptFrequency  // username → attempt count
```

---

### 2. Username Availability Check

```text
If username exists → Not Available
Else → Available
```

Time Complexity → **O(1)**

---

### 3. Suggest Alternatives

If username is taken:

* Append numbers → username1, username2
* Replace characters → john_doe → john.doe
* Try variations until available

---

### 4. Track Popular Usernames

Each search updates frequency:

```java
attemptFrequency.getOrDefault(username, 0) + 1
```

Used to identify trending usernames.

---

## ⚙️ Operations

### ✅ checkAvailability(username)

Returns true if available, false otherwise.

### ✅ registerUser(username, userId)

Adds username to database.

### ✅ suggestAlternatives(username)

Returns list of available alternatives.

### ✅ getMostAttempted()

Returns most searched username.

---

## ⏱️ Time Complexity

| Operation            | Complexity |
| -------------------- | ---------- |
| Check Availability   | O(1)       |
| Register User        | O(1)       |
| Suggest Alternatives | O(k)       |
| Get Most Attempted   | O(n)       |

---

## 📊 Example

```text
checkAvailability("john_doe") → false
checkAvailability("jane_smith") → true

suggestAlternatives("john_doe")
→ [john_doe1, john_doe2, john.doe]

getMostAttempted()
→ "admin"
```

---

## ⚡ Key Concepts

* HashMap for fast lookup
* Frequency counting
* String manipulation
* Real-time system design

---

## 🚀 Real-World Applications

* Social media platforms
* Email services
* Gaming usernames
* Online account registration

---

## 🔧 Possible Improvements

* Use **Trie** for smarter suggestions
* Use **Bloom Filters** for faster existence checks
* Store data in **Redis for scalability**
* Handle distributed systems with sharding

---

## 🏁 Conclusion

This problem demonstrates how **hash tables enable real-time availability checks**, making them essential for scalable systems like username registration platforms.
