public class Stack {
    int top;
    int[] stackArray;

    // Constructor to initialize stack
    Stack(int size) {
        stackArray = new int[size];
        top = -1;
    }

    // Method to add an element to the stack
    public void push(int value) {
        if (top == stackArray.length - 1) {
            System.out.println("Stack overflow");
        } else {
            top++;
            stackArray[top] = value;
            System.out.println("Pushed element: " + value);
        }
    }

    // Method to remove an element from the stack
    public int pop() {
        if (top == -1) {
            System.out.println("Stack underflow");
            return -1;
        } else {
            int popped = stackArray[top];
            top--;
            System.out.println("Popped element: " + popped);
            return popped;
        }
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        return (top == -1);
    }
}