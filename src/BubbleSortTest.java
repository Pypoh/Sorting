import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortTest {

    @Test
    public void shouldDoNothingWithEmptyArray() {
        int[] values = {};

        BubbleSort.bubbleSort(values);
    }

    @Test
    public void shouldDoNothingWithOneElementArray() {
        int[] values = {7};

        BubbleSort.bubbleSort(values);

        assertArrayEquals(new int[]{7}, values);
    }

    @Test
    public void shouldSortValues() {
        int[] values = {7, 3, 6, 1, 4, 9};
        int[] expectedOrder = {1, 3, 4, 6, 7, 9};

        BubbleSort.bubbleSort(values);

        assertArrayEquals(expectedOrder, values);
    }

    @Test
    public void databaseConnectionTest() {
        String successStatusMessage = "Connection Successful";
        String failStatusMessage = "Connection Failed";

        MySQLConnection testObject = MySQLConnection.getInstance();

        assertEquals(successStatusMessage, testObject.openConnection());
    }

    @Test
    public void insertDatabaseTest() {
        int[] values = {7, 3, 6, 1, 4, 9};
        String successStatusMessage = "Insert Success";

        MySQLConnection testObject = MySQLConnection.getInstance();

        assertEquals(successStatusMessage, testObject.insertToDB(values));
    }

    @Test
    public void selectDatabaseTest() {
        int[] expectedOrder = {7, 3, 6, 1, 4, 9};

        MySQLConnection testObject = MySQLConnection.getInstance();

        try {
            assertArrayEquals(expectedOrder, testObject.selectAllFromDB());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}