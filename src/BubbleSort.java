import java.sql.SQLException;

public class BubbleSort {

    private static MySQLConnection dbConnection;

    public static void main(String[] args) throws SQLException {
        int[] arr = {7, 3, 6, 1, 4, 9};

        dbConnection = MySQLConnection.getInstance();

        System.out.println("---Array BEFORE Bubble Sort---");

        printArray(arr);

        bubbleSort(arr); //sorting array elements using bubble sort

        System.out.println("---Array AFTER Bubble Sort---");

        printArray(arr);

        dbConnection.insertToDB(arr);

        int[] newArr = dbConnection.selectAllFromDB();

        System.out.println("---Array AFTER Insertion---");

        printArray(newArr);
    }

    static void bubbleSort(int[] array) {
        int n = array.length;
        int temp = 0;
        for (int i = 0; i < n; i++) // Looping through the array length
        {
            System.out.println("Sort Pass Number " + (i + 1));
            for (int j = 1; j < (n - i); j++) {
                System.out.println("Comparing " + array[j - 1] + " and " + array[j]);
                if (array[j - 1] > array[j]) {
                    //swap elements
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                    System.out.println(array[j] + " is greater than " + array[j - 1]);
                    System.out.println("Swapping Elements: New Array After Swap");
                    printArray(array);
                }
            }
        }
    }

    static void printArray(int[] array) {

        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();

    }
}