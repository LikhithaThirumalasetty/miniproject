# miniproject
student ranking system using heap and sorting algorithms
# Student Ranking System using Heap and Sorting Algorithms

A Java console application that ranks students by marks using four classical sorting algorithms, and retrieves the Top-K performers efficiently using a custom-built Max-Heap.

## Features

- Add, view, and manage student records (roll number, name, marks)
- Rank all students using a chosen sorting algorithm:
  - Bubble Sort
  - Merge Sort
  - Quick Sort
  - Heap Sort
- Execution time displayed for each sort, for performance comparison
- Retrieve the Top-K students instantly using a custom array-based Max-Heap, without sorting the entire list
- Simple, menu-driven command-line interface

## Tech Stack

- **Language:** Java (JDK 8+)
- **Concepts:** Object-Oriented Programming, Arrays, ArrayList, Recursion
- **Data Structure:** Custom Max-Heap (built from scratch, no `PriorityQueue`)
- **Libraries:** `java.util` (Scanner, ArrayList, Arrays, List)

## Project Files

| File | Description |
|---|---|
| [`StudentRankingSystem.java`](./StudentRankingSystem.java) | Full source code |
| [`Student_Ranking_System_Project_Report.docx`](./Student_Ranking_System_Project_Report.docx) | Project documentation report |
| [`Student_Ranking_System_Presentation.pptx`](./Student_Ranking_System_Presentation.pptx) | Project PPT (5 slides) |

## Project Structure

```
StudentRankingSystem.java
├── class StudentRankingSystem   // main class, menu-driven console UI
├── class Student                // data model: rollNo, name, marks
├── class MaxHeap                // custom max-heap (insert, extractMax, getTopK)
└── class Sorter                 // bubbleSort, mergeSort, quickSort, heapSort
```

## How to Run

**Requirements:** JDK 8 or above installed ([Download here](https://adoptium.net))

```bash
git clone https://github.com/YOUR-USERNAME/student-ranking-system.git
cd student-ranking-system
javac StudentRankingSystem.java
java StudentRankingSystem
```

## Sample Output

```
===== Student Ranking System =====
1. Add Student
2. Display All Students
3. Rank Students (Choose Sorting Algorithm)
4. Get Top K Students (Using Max-Heap)
5. Exit
Enter choice: 3
Choose Sorting Algorithm:
1. Bubble Sort
2. Merge Sort
3. Quick Sort
4. Heap Sort
Enter choice: 3

--- Ranking (Highest to Lowest Marks) ---
Rank 1: Roll No: 102 | Name: Kiran | Marks: 95.0
Rank 2: Roll No: 101 | Name: Ravi  | Marks: 88.0
Rank 3: Roll No: 103 | Name: Sai   | Marks: 70.0
Time taken: 4300 ns
```

## Algorithm Complexity

| Algorithm | Best Case | Average Case | Worst Case | Space |
|---|---|---|---|---|
| Bubble Sort | O(n) | O(n²) | O(n²) | O(1) |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) |
| Quick Sort | O(n log n) | O(n log n) | O(n²) | O(log n) |
| Heap Sort | O(n log n) | O(n log n) | O(n log n) | O(1) |
| Max-Heap Insert/Extract | O(log n) | O(log n) | O(log n) | O(n) |

## Future Enhancements

- GUI using Java Swing or JavaFX
- Persistent storage via file handling or MySQL (JDBC)
- Ranking by multiple subjects with weighted averages
- Additional algorithms: Tim Sort, Radix Sort

## Author

[Likhitha]
