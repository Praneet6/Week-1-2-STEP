
# 🚗 Problem 8: Parking Lot Management (Open Addressing)

## 📌 Problem Statement

Design a smart parking lot system that efficiently assigns parking spots to vehicles using **hashing techniques**. The system must handle collisions when multiple vehicles map to the same spot and ensure optimal space utilization.

---

## 🎯 Objectives

* Assign parking spots using a **hash function**
* Handle collisions using **linear probing**
* Track vehicle entry and exit
* Calculate parking duration
* Monitor parking occupancy

---

## 🧠 Approach

### 1. Hash Function

Each vehicle (license plate) is mapped to a parking spot:

```
index = hash(licensePlate) % capacity
```

---

### 2. Collision Handling (Linear Probing)

If a spot is occupied:

```
index + 1 → index + 2 → ... (until empty slot found)
```

---

### 3. Data Structure Used

* Array-based hash table:

```
ParkingSpot[] table
```

Each spot stores:

* License Plate
* Entry Time

---

## ⚙️ Operations

### ✅ parkVehicle(licensePlate)

* Assigns a parking spot
* Uses linear probing if collision occurs

### ✅ exitVehicle(licensePlate)

* Frees the parking spot
* Calculates parking duration

### ✅ getStatistics()

* Calculates occupancy percentage

---

## ⏱️ Time Complexity

| Operation      | Complexity            |
| -------------- | --------------------- |
| Park Vehicle   | O(1) avg / O(n) worst |
| Exit Vehicle   | O(1) avg / O(n) worst |
| Search Vehicle | O(1) avg / O(n) worst |

---

## 📊 Example

```
parkVehicle("ABC123") → Spot 2
parkVehicle("XYZ999") → Collision → Spot 3

exitVehicle("ABC123") → Spot freed
Occupancy → 40%
```

---

## ⚡ Key Concepts

* Hash Tables
* Open Addressing
* Linear Probing
* Collision Resolution
* Load Factor

---

## 🚀 Possible Improvements

* Use **Quadratic Probing** to reduce clustering
* Implement **Double Hashing**
* Add **DELETED marker** instead of null
* Dynamic resizing (rehashing)
* Track nearest spot to entrance

---

## 🏁 Conclusion

This problem demonstrates how **hash tables handle collisions in real-world systems** like parking management, memory allocation, and caching mechanisms.
