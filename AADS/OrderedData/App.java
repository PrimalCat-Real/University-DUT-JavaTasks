public class App {
    public static void main(String[] args) {
        // False to disable duplicates
        ArrayOrdered arrayOrdered = new ArrayOrdered(2, false);
        arrayOrdered.insertValue(2);
        arrayOrdered.insertValue(2);
        arrayOrdered.insertValue(2);
        arrayOrdered.printArray(arrayOrdered.getArray());
        arrayOrdered.getTwoThirdsOf(arrayOrdered.getArray());

        // Task 2
        // 10
        ArrayOrdered arrayOrdered_10 = new ArrayOrdered(10, false);
        arrayOrdered_10.generateArray();
        arrayOrdered_10.bSearchCounted(arrayOrdered_10.getTwoThirdsOf(arrayOrdered_10.getArray()), arrayOrdered_10.getArray());
        // 100
        ArrayOrdered arrayOrdered_100 = new ArrayOrdered(100, false);
        arrayOrdered_100.generateArray();
        arrayOrdered_100.bSearchCounted(arrayOrdered_100.getTwoThirdsOf(arrayOrdered_100.getArray()), arrayOrdered_100.getArray());

        // 1_000
        ArrayOrdered arrayOrdered_1_000 = new ArrayOrdered(1_000, false);
        arrayOrdered_1_000.generateArray();
        arrayOrdered_1_000.bSearchCounted(arrayOrdered_1_000.getTwoThirdsOf(arrayOrdered_1_000.getArray()), arrayOrdered_1_000.getArray());

        // 10_000
        ArrayOrdered arrayOrdered_10_000 = new ArrayOrdered(10_000, false);
        arrayOrdered_10_000.generateArray();
        arrayOrdered_10_000.bSearchCounted(arrayOrdered_10_000.getTwoThirdsOf(arrayOrdered_10_000.getArray()), arrayOrdered_10_000.getArray());

        // 100_000
        ArrayOrdered arrayOrdered_100_000 = new ArrayOrdered(100_000, false);
        arrayOrdered_100_000.generateArray();
        arrayOrdered_100_000.bSearchCounted(arrayOrdered_100_000.getTwoThirdsOf(arrayOrdered_100_000.getArray()), arrayOrdered_100_000.getArray());
    }
}
