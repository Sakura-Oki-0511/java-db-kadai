package kadai_007;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Posts_Chapter07 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Connection con = null;
		Statement statement = null;

		try {
			// データベースに接続
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/challenge_java",
					"root",
					"sakura10969");

			System.out.println("データベース接続成功");

			// SQLクエリを準備（複数行データの一括挿入）
			statement = con.createStatement();
			String sql = """
					INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES
					(1003, '2023-02-08', '昨日の夜は徹夜でした・・', 13),
					(1002, '2023-02-08', 'お疲れ様です！', 12),
					(1003, '2023-02-09', '今日も頑張ります！', 18),
					(1001, '2023-02-09', '無理は禁物ですよ！', 17),
					(1002, '2023-02-10', '明日から連休ですね！', 20);
					""";

			// データ追加メッセージ
			System.out.println("レコード追加を実行します");

			// SQLクエリを実行（DBMSに送信）
			int rowCnt = statement.executeUpdate(sql);
			System.out.println(rowCnt + "件のレコードが追加されました");

			// データを検索するSQLクエリ
			String selectSql = """
					SELECT posted_at, post_content, likes
					FROM posts
					WHERE user_id = 1002;
					""";
			
			 // 検索開始メッセージ
            System.out.println("ユーザーIDが1002のレコードを検索しました");
			
			// データ検索の実行
			ResultSet resultSet = statement.executeQuery(selectSql);

			// 件数をカウント
			int count = 1;

			// 取得したデータを1レコードずつ表示
			while (resultSet.next()) {
				Date postedAt = resultSet.getDate("posted_at"); // 投稿日時をDate型で取得
				String postContent = resultSet.getString("post_content");
				int likes = resultSet.getInt("likes");

				System.out.println(count + "件目：投稿日時=" + postedAt + "／投稿内容=" + postContent + "／いいね数=" + likes);
				count++;
			}

		} catch (SQLException e) {
			System.out.println("エラー発生：" + e.getMessage());
		} finally {
			// 使用したオブジェクトを解放
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}

}
