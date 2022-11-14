public class App {
    public static void main(String[] args) {
        // False to disable duplicates
        ArrayOrdered arrayOrdered = new ArrayOrdered(10, false);
        arrayOrdered.insertValue(2);
        arrayOrdered.insertValue(2);
        arrayOrdered.insertValue(2);
        arrayOrdered.printArray(arrayOrdered.getArray());

        // Task 2
        ArrayOrdered arrayOrdered_100 = new ArrayOrdered(100, false);
        ArrayOrdered arrayOrdered_1_000 = new ArrayOrdered(1_000, false);
        ArrayOrdered arrayOrdered_10_000 = new ArrayOrdered(10_000, false);
        ArrayOrdered arrayOrdered_100_000 = new ArrayOrdered(100_000, false);
    }
}
