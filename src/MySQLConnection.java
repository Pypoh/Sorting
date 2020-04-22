import java.sql.*;

public class MySQLConnection {

    private static MySQLConnection instance;
    private Connection conn;

    private MySQLConnection() {
        openConnection();
    }

    public String openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/sorting?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT";
            String user = "root";
            String password = "";
            conn = DriverManager.getConnection(url, user, password);

            if (conn != null) {
                System.out.println("Connection Successful");
                return "Connection Successful";
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static MySQLConnection getInstance() {
        if (instance == null) {
            instance = new MySQLConnection();
        }
        return instance;
    }

    public int[] selectAllFromDB() throws SQLException {
        String query = "SELECT * FROM result";
        String resultString = "";
        int[] resultArray = new int[0];
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                resultString = resultSet.getString("result_string");
                System.out.println(resultString);
                resultArray = new int[resultString.length()];

                for (int i = 0; i < resultString.length(); i++) {
                    resultArray[i] = Integer.parseInt(String.valueOf(resultString.charAt(i)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultArray;
    }

    public String insertToDB(int[] arrayInput) {
        System.out.println("Inserting to DB...");

        StringBuilder inputString = new StringBuilder();

        for (int value : arrayInput) {
            inputString.append(value);
        }

        String query = "INSERT INTO result (result_string) values (" + inputString.toString() + ")";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
//            preparedStatement.setString(1, inputString.toString());
            preparedStatement.execute();
            return "Insert Success";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
