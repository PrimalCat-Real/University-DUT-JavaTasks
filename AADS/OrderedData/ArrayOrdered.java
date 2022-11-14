import java.util.Arrays;

public class ArrayOrdered extends OrderedAbilities {
    private int[] array;
    private static boolean isAllowDuplicates = true;

    public int[] getArray() {
        return array;
    }

    public ArrayOrdered(int len) {
        this.array = new int[len];
    }

    /** Task 1
     * Make switch between saving duplicates
     * **/
    public ArrayOrdered(int len, boolean isAllowDuplicates) {
        this.array = new int[len];
        this.isAllowDuplicates = isAllowDuplicates;
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

    // Task 1
    public int[] insertValue(int valueToInsert){
        if(!isAllowDuplicates && bSearch(valueToInsert, array) == null){
            insert(valueToInsert);
        }else if(isAllowDuplicates){
            insert(valueToInsert);
        }
        return array;
    }

    // place value in array and save order
    private int[] insert(int valueToInsert){
        int[] copiedArray = Arrays.copyOf(array, array.length+1);
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
        this.array = copiedArray;
        return copiedArray;
    }


}
