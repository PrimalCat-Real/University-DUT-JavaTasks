import java.util.Arrays;

public class OrderedAbilities {
    public void printArray(int[] array){
        System.out.println(Arrays.toString(array));
    }

    public Integer bSearch(int itemToFind, int[] array) {
        int low = 0;
        int height = array.length - 1;
        int guess;
        int mid;
        while (low <= height) {
            mid = (low + height) / 2;
            guess = array[mid];
            if (guess == itemToFind) {
                return mid;
            } else if (guess > itemToFind) {
                height = mid - 1;
            } else if (guess < itemToFind) {
                low = mid + 1;
            } else {
                System.out.println("WTF you guess");
                break;
            }
        }
        System.out.println("I can't find your item in array: " + itemToFind);
        return null;
    }

    // Task 2
    public Integer getTwoThirdsOf(int[] array){
        return 0;
    }
}
