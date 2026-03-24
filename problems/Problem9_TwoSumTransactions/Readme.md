# 💳 Problem 9: Two-Sum Variants for Financial Transactions

## 📌 Problem Statement

Given a large number of financial transactions, design a system to efficiently:

* Find transaction pairs that sum to a target amount
* Detect duplicate transactions
* Support fast lookup for fraud detection

---

## 🎯 Objectives

* Implement **Two-Sum in O(n)** time
* Detect duplicate transactions (same amount + merchant)
* Handle large-scale transaction data efficiently

---

## 🧠 Approach

### 1. Two-Sum Logic

For each transaction:

```id="k5u2e3"
complement = target - current_amount
```

Check if complement exists in HashMap → if yes, pair found.

---

### 2. Data Structures Used

```id="v1k3l9"
HashMap<Integer, Transaction>  // amount → transaction
HashMap<String, List<Integer>> // (amount+merchant) → transaction IDs
```

---

### 3. Duplicate Detection

We group transactions using a key:

```id="9d2k1m"
key = amount + "_" + merchant
```

If multiple IDs exist → duplicate detected.

---

## ⚙️ Operations

### ✅ findTwoSum(transactions, target)

* Returns pairs of transaction IDs whose sum = target

### ✅ detectDuplicates(transactions)

* Prints duplicate transactions

---

## ⏱️ Time Complexity

| Operation           | Complexity |
| ------------------- | ---------- |
| Two-Sum             | O(n)       |
| Duplicate Detection | O(n)       |

---

## 📊 Example

```id="2j4l9p"
Transactions:
(1, 500), (2, 300), (3, 200)

Target = 500

Output:
Pair → (2, 3)  // 300 + 200
```

Duplicate Example:

```id="8n3q0s"
(1, 500, StoreA)
(4, 500, StoreA)

Output:
Duplicate → [1, 4]
```

---

## ⚡ Key Concepts

* HashMap (O(1) lookup)
* Complement technique
* Frequency grouping
* Efficient data processing

---

## 🚀 Possible Improvements

* Add **time window filtering (last 1 hour)**
* Extend to **K-Sum problems**
* Use **stream processing (Kafka + Spark)**
* Detect suspicious patterns using ML

---

## 🏁 Conclusion

This problem demonstrates how **hash tables enable fast financial analysis**, which is critical in fraud detection systems and payment processing platforms.
