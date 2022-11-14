import java.util.Arrays;

public class ArrayOrdered() extends OrderedAbilities {
    private int[] array;
    public int[] getArray() {
        return array;
    }

    public ArrayOrdered(int len) {
        this.array = new int[len];
    }

    // fill an array with numbers 1-10
    public void generateArray(){
        for (int i = 0; i < array.length; i++) {
            array[i] = i+1;
        }
    }

    public int[] deleteElement(int valueToDelete){
        // Find position of element to be deleted
        Integer pos = this.bSearch(valueToDelete, array);

        if (pos == null) {
            System.out.println("Element not found");
            return null;
        }
        for (int i = pos; i < array.length-1; i++) {
            array[i] = array[i+1];
        }
        int[] copiedArray = Arrays.copyOf(array, array.length-1);
        System.out.println(Arrays.toString(copiedArray));
        return copiedArray;
    }

    // place value in array and save order
    public static String insert(int valueToInsert,int[] arr){
        int[] copiedArray = Arrays.copyOf(arr, arr.length+1);
        int i;
        for(i=0; i < copiedArray.length-1; i++){
            if(copiedArray[i] > valueToInsert){
                break;
            }
        }
        for(int k = copiedArray.length-2; k >= i; k--){
            copiedArray[k+1] = copiedArray[k];
        }
        copiedArray[i] = valueToInsert;
        return Arrays.toString(copiedArray);
    }
}
