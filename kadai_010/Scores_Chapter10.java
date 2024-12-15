package kadai_010;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Scores_Chapter10 {

    // データベース接続情報
    private static final String URL = "jdbc:mysql://localhost/challenge_java";
    private static final String USER = "root";
    private static final String PASSWORD = "sakura10969";

    public static void main(String[] args) {
        try {
            // ①データベースに接続する
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            // ②idが5のレコードのスコアを更新する
            String updateScoreQuery = "UPDATE sorce SET score_math = 95, score_english = 80 WHERE id = 5";
            statement.executeUpdate(updateScoreQuery);

            // ③SQLクエリで点数順に並べる（数学→英語の順で降順）
            String selectQuery = "SELECT * FROM sorce ORDER BY score_math DESC, score_english DESC";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            // ④並べ替え結果を表示する
            int count = 1;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int mathScore = resultSet.getInt("score_math");
                int englishScore = resultSet.getInt("score_english");

                System.out.println(count + "件目：生徒ID=" + id + "／氏名=" + name +
                        "／数学=" + mathScore + "／英語=" + englishScore);
                count++;
            }

            // 接続を閉じる
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // データベース接続メソッド
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
