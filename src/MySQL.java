import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class MySQL {
	public static Connection con = null;
	private Connection con1 = null;
	static Statement stm;
	private String sql;
	private ResultSet rs;
	private boolean SuccessFlag = false;

	/**
	 * 対象ユーザのログインをする　メソッド
	 * 
	 * @param ID
	 *            対象ユーザのID
	 * @param Password
	 *            対象ユーザのパスワード
	 */
	public MySQL(String ID, String Password) {
		try {
			con = null;
			stm = null;
			rs = null;
			SuccessFlag = false;
			System.out.println("MySQL：ID=" + ID);
			System.out.println("MySQL：Password=" + Password);

			System.out.println("MySQLコンストラクタ--------------");
			
			// JDBCドライバのロード
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("ドライバのロード成功");

			// MySQLに接続
			con1 = DriverManager.getConnection("jdbc:mysql://192.168.100.103:49523/root", "naka", "123");
			
			  con = DriverManager.getConnection("jdbc:mysql://192.168.100.103:49523/" + ID, "naka", "123");
			  System.out.println("my conへの代入");


//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
//					+ ID, ID, Password);
//			System.out.println("MySQLに接続できました。");

			// MySQL用のステートメントを生成
			stm = con.createStatement();
			System.out.println("my stmの生成");

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			System.out.println("JDBCドライバのロードに失敗しました。");
		} catch (SQLException e) { // 対象ユーザのデータベースが一度も作成されていない場合
			System.out.println("catch---");
			
			//con1がnullならそこでエラーが起こった
			if( con1 == null ){
				return;
			}
			
/////////////////////////////////////////////////////////////////初めて作る段階でMySQLを生成してcreateすれば...！！			
			My_create(ID, Password); // ユーザのDB・テーブルを作成
		} finally {
//
//			// テーブルが存在していたので、既存テーブルを削除
//			try {
//				System.out.println("MySQL_finally--------------");
//				
//				// データベースを選択
//				sql = "USE " + ID;
//				System.out.println("データベースの選択代入");
//				stm.execute(sql);
//				System.out.println("実行");
//
//
//				// 対象ユーザのテーブルを削除
//				sql = "DROP TABLE " + encryptStr(Password);
//				stm.execute(sql);
//
//				// 新たにテーブルを作成
//				sql = "CREATE TABLE "
//						+ encryptStr(Password)
//						+ " (fileid int ,filename VARCHAR(10000),filepass VARCHAR(255),picture VARCHAR(255), keycode INT, keycount INT,comand VARCHAR(255))";
//				stm.executeUpdate(sql);
//
//			} catch (SQLException e) {
//				// TODO 自動生成された catch ブロック
//				e.printStackTrace();
//			}

		}

	}

	public MySQL() {
	}

	/**
	 * MySQLに対象ユーザ専用の「DB」と「テーブル」を作成する　メソッド
	 * 
	 * @param ID
	 *            対象ユーザのID
	 * @param Password
	 *            　対象ユーザのパスワード
	 */
	private void My_create(String ID, String Password) {

		try {
			System.out.println("My_create--------------");
			
			// JDBCドライバのロード
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("cre ドライバのロード");
			
			// 管理者として、サーバをログイン
			
			  con = DriverManager.getConnection(
			  "jdbc:mysql://192.168.100.103:49523/root", "naka", "123");
			  System.out.println("cre conへの代入");

//			con = DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/bas", "PHONE1", "PENGUIN");

			System.out.println("MySQLに接続できました。");

			// MySQL用のステートメントを生成
			stm = con.createStatement();
			System.out.println("cre stm生成");
			
			// ユーザ用のデータベースを作成
			sql = "CREATE DATABASE " +  ID;
			System.out.println("SQL文作成");
			
			stm.execute(sql);
			System.out.println("create database実行");

			// 作成したデータベースを使用
			sql = "USE " + ID;
			stm.execute(sql);
			System.out.println("use実行");

			// 対象のユーザのテーブルを作成
			sql = "CREATE TABLE "
					+ encryptStr(Password)
					+ " (fileid int ,filename VARCHAR(10000),filepass VARCHAR(255),picture VARCHAR(255), keycode INT, keycount INT,comand VARCHAR(255))";
			stm.executeUpdate(sql);
			System.out.println("table create実行");

//			// 初めてバックアップされるので、ユーザを追加
//			sql = "GRANT SELECT,INSERT,DELETE ON " + ID + "." + encryptStr(Password) + " TO "
//					+ ID + "@192.168.100.103 IDENTIFIED BY '" + Password + "'";
//			stm.execute(sql);
//			System.out.println("user create実行");

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			System.out.println("JDBCドライバのロードに失敗しました。");
		} catch (SQLException e) {
			System.out.println(e);

			// テーブルが存在していたので、既存テーブルを削除
			try {
				System.out.println("MySQL_finally--------------");
				
				// データベースを選択
				sql = "USE " + ID;
				System.out.println("データベースの選択代入");
				stm.execute(sql);
				System.out.println("実行");


				// 対象ユーザのテーブルを削除
				sql = "DROP TABLE " + encryptStr(Password);
				stm.execute(sql);

				// 新たにテーブルを作成
				sql = "CREATE TABLE "
						+ encryptStr(Password)
						+ " (fileid int ,filename VARCHAR(10000),filepass VARCHAR(255),picture VARCHAR(255), keycode INT, keycount INT,comand VARCHAR(255))";
				stm.executeUpdate(sql);

			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}

		} finally {
		}

	}

	/**
	 * H2の登録情報をMySQLにバックアップするメソッド
	 * 
	 * @param ID
	 *            使用中のユーザID
	 * @param Mys
	 *            H2の情報を取得している変数
	 */
	public void My_backup(String ID,String Password ,ResultSet Mys) {

		try {
			System.out.println("My_backup処理中---------");
			// MySQLにバックアップ文
			sql = "INSERT INTO " + encryptStr(Password) + " VALUES(?,?,?,?,?,?,?)";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);

			// H2の登録情報を１行ずつ取得
			while (Mys.next()) {
				ps.setInt(1, Mys.getInt("fileid"));
				ps.setString(2, Mys.getString("filename"));
				ps.setString(3, Mys.getString("filepass"));
				ps.setString(4, Mys.getString("picture"));
				ps.setString(5, Mys.getString("keycode"));
				ps.setString(6, Mys.getString("keycount"));
				ps.setString(7, Mys.getString("comand"));

				// バッチに１行分記入
				ps.addBatch();
			}
			System.out.println("while終わり");
			// 取得した全ての情報をMySQLに
			ps.executeBatch();
			System.out.println("処理終了");
			SuccessFlag = true;
			//JOptionPane.showMessageDialog(null, "データベースへの登録が完了しました");

		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(null, "データベースへの登録に失敗しました");
			System.out.println(e);
		} finally {
			System.out.println("finally処理中");
			if (con != null) {
				System.out.println("if突入！");
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println("MySQLのクローズに失敗しました。");
				}
			}
			System.out.println("finally終わり");
		}
		return;
	}

	public ResultSet My_import(String ID, String Password) {

		try {
			
			// JDBCドライバのロード
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			

			// MySQLに接続
			
//			  con =
//			  DriverManager.getConnection("jdbc:mysql://10.16.226.212:49523/" +
//			  ID, ID, Password);

			con = DriverManager.getConnection("jdbc:mysql://192.168.100.103:49523/root", "naka", "123");


			System.out.println("MySQLに接続できました。");
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
			//		+ ID, ID, Password);
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bas", "PHONE1", "PENGUIN" );
			
			
			System.out.println("MySQLに接続できました。");
			
			// MySQL用のステートメントを生成
			stm = con.createStatement();
			
			// 作成したデータベースを使用
			sql = "USE " + ID;
			stm.execute(sql);
			

			
			sql = "select * from " + encryptStr(Password) + " order by fileid";
			rs = stm.executeQuery(sql);
			

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			System.out.println("JDBCドライバのロードに失敗しました。");
		} catch (SQLException e) { // 対象ユーザのデータベースが一度も作成されていない場合
			//My_create(ID, Password); // ユーザのDB・テーブルを作成
		} finally {
		
		}
		
		return rs;
	}


	public static String encryptStr(String text) {
		// 変数初期化
		MessageDigest md = null;
		StringBuffer buffer = new StringBuffer();

		try {
			// メッセージダイジェストインスタンス取得
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// 例外発生時、エラーメッセージ出力
			System.out.println("指定された暗号化アルゴリズムがありません");
		}

		// メッセージダイジェスト更新
		md.update(text.getBytes());

		// ハッシュ値を格納
		byte[] valueArray = md.digest();

		// ハッシュ値の配列をループ
		for (int i = 0; i < valueArray.length; i++) {
			// 値の符号を反転させ、16進数に変換
			String tmpStr = Integer.toHexString(valueArray[i] & 0xff);

			if (tmpStr.length() == 1) {
				// 値が一桁だった場合、先頭に0を追加し、バッファに追加
				buffer.append('0').append(tmpStr);
			} else {
				// その他の場合、バッファに追加
				buffer.append(tmpStr);
			}
		}

		// 完了したハッシュ計算値を返却
		return buffer.toString();
	}

	public boolean getSuccessFlag(){
		return SuccessFlag;
	}
	
	
}