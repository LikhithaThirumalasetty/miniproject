import java.util.*;

/*
 * STUDENT RANKING SYSTEM
 * Mini Project using Heap (Max-Heap) and Sorting Algorithms
 * Algorithms implemented: Bubble Sort O(n^2), Merge Sort O(n log n),
 * Quick Sort O(n log n) avg, Heap Sort O(n log n)
 * Heap used to efficiently fetch Top-K students without sorting entire list.
 */

public class StudentRankingSystem {
    static List<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            choice = readInt();
            switch (choice) {
                case 1: addStudent(); break;
                case 2: displayStudents(); break;
                case 3: sortMenu(); break;
                case 4: topKUsingHeap(); break;
                case 5: System.out.println("Exiting... Thank you!"); break;
                default: System.out.println("Invalid choice, try again.");
            }
        } while (choice != 5);
        sc.close();
    }

    static int readInt() {
        while (!sc.hasNextInt()) { sc.next(); }
        return sc.nextInt();
    }

    static void printMenu() {
        System.out.println("\n===== Student Ranking System =====");
        System.out.println("1. Add Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Rank Students (Choose Sorting Algorithm)");
        System.out.println("4. Get Top K Students (Using Max-Heap)");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }

    static void addStudent() {
        System.out.print("Enter Roll No: ");
        int roll = readInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Marks (0-100): ");
        double marks = sc.nextDouble();
        students.add(new Student(roll, name, marks));
        System.out.println("Student added successfully!");
    }

    static void displayStudents() {
        if (students.isEmpty()) { System.out.println("No students added yet."); return; }
        for (Student s : students) System.out.println(s);
    }

    static void sortMenu() {
        if (students.isEmpty()) { System.out.println("No students to sort."); return; }
        System.out.println("Choose Sorting Algorithm:");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Merge Sort");
        System.out.println("3. Quick Sort");
        System.out.println("4. Heap Sort");
        int ch = readInt();
        Student[] arr = students.toArray(new Student[0]);
        long start = System.nanoTime();
        switch (ch) {
            case 1: Sorter.bubbleSort(arr); break;
            case 2: Sorter.mergeSort(arr, 0, arr.length - 1); break;
            case 3: Sorter.quickSort(arr, 0, arr.length - 1); break;
            case 4: Sorter.heapSort(arr); break;
            default: System.out.println("Invalid choice."); return;
        }
        long end = System.nanoTime();
        System.out.println("\n--- Ranking (Highest to Lowest Marks) ---");
        int rank = 1;
        for (Student s : arr) System.out.println("Rank " + (rank++) + ": " + s);
        System.out.println("Time taken: " + (end - start) + " ns");
    }

    static void topKUsingHeap() {
        if (students.isEmpty()) { System.out.println("No students added yet."); return; }
        System.out.print("Enter K (number of top students): ");
        int k = readInt();
        MaxHeap heap = new MaxHeap(students.size());
        for (Student s : students) heap.insert(s);
        List<Student> topK = heap.getTopK(k);
        System.out.println("\n--- Top " + k + " Students (Using Max-Heap) ---");
        int rank = 1;
        for (Student s : topK) System.out.println("Rank " + (rank++) + ": " + s);
    }
}

class Student {
    int rollNo;
    String name;
    double marks;

    Student(int rollNo, String name, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
    }

    public String toString() {
        return "Roll No: " + rollNo + " | Name: " + name + " | Marks: " + marks;
    }
}

// Custom Max-Heap implementation (array based, no library used)
class MaxHeap {
    private Student[] heap;
    private int size;
    private int capacity;

    MaxHeap(int capacity) {
        this.capacity = Math.max(capacity, 1);
        this.size = 0;
        heap = new Student[this.capacity];
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }

    private void swap(int i, int j) {
        Student temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    void insert(Student s) {
        if (size == capacity) {
            capacity *= 2;
            heap = Arrays.copyOf(heap, capacity);
        }
        heap[size] = s;
        int i = size;
        size++;
        while (i != 0 && heap[parent(i)].marks < heap[i].marks) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    Student extractMax() {
        if (size <= 0) return null;
        Student root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapify(0);
        return root;
    }

    private void heapify(int i) {
        int l = left(i), r = right(i), largest = i;
        if (l < size && heap[l].marks > heap[largest].marks) largest = l;
        if (r < size && heap[r].marks > heap[largest].marks) largest = r;
        if (largest != i) {
            swap(i, largest);
            heapify(largest);
        }
    }

    // Returns top K students without destroying original heap (uses a copy)
    List<Student> getTopK(int k) {
        MaxHeap clone = copy();
        List<Student> result = new ArrayList<>();
        for (int i = 0; i < k && clone.size > 0; i++) {
            result.add(clone.extractMax());
        }
        return result;
    }

    private MaxHeap copy() {
        MaxHeap c = new MaxHeap(this.capacity);
        c.heap = Arrays.copyOf(this.heap, this.heap.length);
        c.size = this.size;
        c.capacity = this.capacity;
        return c;
    }
}

// All sorting algorithms - each sorts in DESCENDING order of marks (Rank 1 = highest)
class Sorter {

    static void bubbleSort(Student[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j].marks < arr[j + 1].marks) {
                    Student temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }

    static void mergeSort(Student[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(Student[] arr, int l, int m, int r) {
        Student[] left = Arrays.copyOfRange(arr, l, m + 1);
        Student[] right = Arrays.copyOfRange(arr, m + 1, r + 1);
        int i = 0, j = 0, k = l;
        while (i < left.length && j < right.length) {
            if (left[i].marks >= right[j].marks) arr[k++] = left[i++];
            else arr[k++] = right[j++];
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    static void quickSort(Student[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Student[] arr, int low, int high) {
        double pivot = arr[high].marks;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].marks >= pivot) {
                i++;
                Student temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Student temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    static void heapSort(Student[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) heapify(arr, n, i);
        for (int i = n - 1; i > 0; i--) {
            Student temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
        // Above builds ascending order; reverse to get descending (Rank 1 = highest)
        for (int i = 0; i < n / 2; i++) {
            Student t = arr[i];
            arr[i] = arr[n - 1 - i];
            arr[n - 1 - i] = t;
        }
    }

    private static void heapify(Student[] arr, int n, int i) {
        int largest = i, l = 2 * i + 1, r = 2 * i + 2;
        if (l < n && arr[l].marks > arr[largest].marks) largest = l;
        if (r < n && arr[r].marks > arr[largest].marks) largest = r;
        if (largest != i) {
            Student temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }
    }
}