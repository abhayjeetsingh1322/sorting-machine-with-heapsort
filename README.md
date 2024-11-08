# Sorting Machine with Heapsort

## Description
The **Sorting Machine with Heapsort** project implements a `SortingMachine` data structure layered on top of `Queue` and arrays. The project utilizes the **Heapsort** algorithm to efficiently sort elements while maintaining modularity and adhering to strict kernel-level specifications.

This project emphasizes:
- Kernel-level programming using a priority queue implemented with a heap.
- Recursive algorithms for heap operations.
- Designing and executing a systematic test plan to ensure correctness and robustness.

---

## Objectives
1. Implement the `SortingMachineKernel` interface with a heap-based approach.
2. Develop kernel methods, including:
   - `add`
   - `changeToExtractionMode`
   - `removeFirst`
   - `size`
3. Use recursive implementations for heap operations such as `siftDown` and `heapify`.
4. Design and execute a complete specification-based test plan.

---

## Features
### 1. Efficient Sorting Machine
- Two modes of operation:
  - **Insertion Mode**: Allows adding elements to the sorting machine.
  - **Extraction Mode**: Enables retrieval of sorted elements.
- Supports sorting using a comparator for custom order definitions.

### 2. Heapsort Algorithm
- Implements Heapsort for efficient sorting.
- Recursive methods used for:
  - **`siftDown`**: Ensures heap property is maintained.
  - **`heapify`**: Converts an unsorted array into a valid heap.
  - **`buildHeap`**: Prepares the heap for sorting.

### 3. Key Operations
- **`add`**: Adds an element to the sorting machine.
- **`changeToExtractionMode`**: Finalizes the heap for element extraction in sorted order.
- **`removeFirst`**: Retrieves and removes the smallest (or largest, depending on the comparator) element.
- **`size`**: Returns the total number of elements in the sorting machine.

---

## Technologies Used
- **Java**: For implementing the heapsort-based `SortingMachine` and kernel methods.
- **JUnit**: For unit testing and validating the implementation.

---

## How to Run
### Prerequisites
- Java Development Kit (JDK)
- Any Java-compatible IDE or terminal

### Steps
1. Clone the repository:
   ```bash
   git clone [repository URL]
