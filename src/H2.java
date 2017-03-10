import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;

import com.mysql.jdbc.PreparedStatement;

public class H2 {
	public static Connection con = null;
	static Statement stm;

	// SQL文　格納変数　
	private static String sql = null;
	static String ID;
	static String PASSWARD;
	private String COMAND;
	static boolean flg;
	static int option;

	// 実行ファイル文　変数
	public static String EXE;

	
	// テーブル用　配列
		public Object[][] str = { {new ImageIcon(""), "", "", "", "" }, { new ImageIcon(""), "", "", "", "" },
				{new ImageIcon(""), "", "", "", "" }, { new ImageIcon(""), "", "", "", "" },
				{new ImageIcon(""), "", "", "", "" }, { new ImageIcon(""), "", "", "", "" },
				{new ImageIcon(""), "", "", "", "" }, { new ImageIcon(""), "", "", "", "" },
				{new ImageIcon(""), "", "", "", "" }, { new ImageIcon(""), "", "", "", "" },
				{new ImageIcon(""), "", "", "", "" }, { new ImageIcon(""), "", "", "", "" },
				{new ImageIcon(""), "", "", "", "" }, { new ImageIcon(""), "", "", "", "" },
				{new ImageIcon(""), "", "", "", "" }, { new ImageIcon(""), "", "", "", "" },
				{new ImageIcon(""), "", "", "", "" }, { new ImageIcon(""), "", "", "", "" },
				{new ImageIcon(""), "", "", "", "" }, { new ImageIcon(""), "", "", "", "" },

		};

	

	// ファイル名
	private static String NAME = null;
	// ファイルパス
	private static String PASS = null;
	// ファイルID
	private String FILEID = null;
	// アイコンパス
	private String PICTURE = null;

	public H2(String id, String passward) {

		try {
			// 入力されたアカウント情報を代入
			ID = id;
			System.out.println(ID);
			PASSWARD = passward;

			// JDBCドライバのロード
			Class.forName("org.h2.Driver").newInstance();

			// H2に接続
			con = DriverManager.getConnection("jdbc:h2:" + main.path
					+ "\\bin\\" + ID, ID, PASSWARD);
			stm = con.createStatement();
			System.out.println("H2に接続できました。");
			flg = true;

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			System.out.println("JDBCドライバのロードに失敗しました。");
		} catch (SQLException e) {
			System.out.println("H2に接続できませんでした。");
		} finally {
		}
	}

	public H2() {
	}

	// whileで回して行数目を数える
	public static void select_key(int keysum, int keycount) {

		try {
			// ステートメント生成
			stm = con.createStatement();
			// SELECT 文
			sql = "SELECT * FROM " + "A" + ID + " WHERE KEYCODE=" + keysum
					+ " AND KEYCOUNT=" + keycount;
			ResultSet rs = stm.executeQuery(sql);

			
			Runtime rt = Runtime.getRuntime();
			while (rs.next()) {
				String filepath = rs.getString(3);
				System.out.println("デバッグ：filepath=" + filepath);
				
				
				Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
				if (filepath == "") {	//ファイルパスが空白ならば、ファイル名にある文字列をクリップボードにコピーする
					clip = ClipboardCopy(rs.getString(2));
					
				} else {
					int keycode = rs.getInt(5);
					int keycnt = rs.getInt(6);
					System.out.println("取得結果 -> " + keycode);
					System.out.println("取得結果 -> " + keycnt);
					EXE = null;
					EXE = new String("explorer " + filepath);

					try {
						rt.exec(EXE);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("H2に接続できませんでした。４");
		}
	}

	// H2データベースの接続を切断　メソッド
	public static void Disconnect() {
		try {
			// 現在のデータベースを切断
			con.close();

			// テーブル更新回数　初期化
			main.cnt = true;

			// ボタンフレーム　・　メイン画面　非表示
			list.Bframe.setVisible(false);
			main.frame.setVisible(false);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 未登録ID テーブル作成する　メソッド
	public static void Create() {

		try {
			// ステートメント生成
			stm = con.createStatement();

			// 未登録ID テーブル作成
			 sql = "CREATE TABLE "
					 + "A" 
					+  ID 
					+ " (fileid int auto_increment,"
					+ "filename VARCHAR(10000),filepass VARCHAR(255),picture VARCHAR(255), keycode INT, keycount INT,comand VARCHAR(255))";
			System.out.println(sql);
			stm.executeUpdate(sql);
			System.out.println("create完了！！");

		} catch (SQLException e) {
			System.out.println("H2に接続できませんでした。2");
			e.printStackTrace();
		} finally {
		}

	}

	// ドラッグされたファイル情報をH2データベースに挿入する　メソッド
	public void insert(String name, String pass, String picture, int code,
			int keycount, String comand) {
		try {
			// ステートメント生成
			stm = con.createStatement();

			// ファイル名・ファイルパス・アイコンのパス　代入
			NAME = name.replace("'", "''");
			PASS = pass.replace("'", "''");;
			PICTURE = picture.replace("'", "''");;

			
			// INSERT INTO 文
			sql = "INSERT INTO " + "A" + ID
					+ " (filename,filepass,picture,KEYCODE,KEYCOUNT,comand)"
					+ "VALUES(" + "'" + NAME + "','" + PASS + "','" + PICTURE
					+ "'," + code + "," + keycount + "," + comand + ")";
			System.out.println(sql);
			stm.executeUpdate(sql);
			System.out.println("Insert完了！！");

		} catch (SQLException e) {
			System.out.println("H2に接続できませんでした。3");
		} finally {
		}
	}

	// 文字列をデータベースに登録する
	public void insert_String(String name, int code, int keycount, String comand) {
		try {
			// ステートメント生成
			// stm = con.createStatement();
			String Empty = main.path + "\\empty.png";
			// INSERT INTO 文
			sql = "INSERT INTO " + "A" + ID
					+ " (filename,filepass,picture,KEYCODE,KEYCOUNT,comand)"
					+ "VALUES(" + "'" + name + "'," + "''" + ",'" + Empty
					+ "'," + code + "," + keycount + "," + comand + ")";
			// sql = "INSERT INTO "+ ID +
			// "(filename,filepass,picture,KEYCODE)VALUES(" + "'" + NAME +"'"+
			// "," + "'" + PASS +"'"+ "," + "'" + PICTURE +"'" + "," + "'" +
			// code +"'" + ")";

			System.out.println(sql);

			stm.executeUpdate(sql);
			System.out.println("Insert完了！！");

		} catch (SQLException e) {
			System.out.println("H2に接続できませんでした。3");
		} finally {
		}

	}

	 /**
	  * MySQL　→　H2 への登録情報をインポート
	  * @param rs MySQL（SELECT文)の結果を取得
	  */
	public void My_inport(ResultSet rs) {

		try {

			// MySQLの登録情報を１行ずつ取得
			while (rs.next()) {

				sql = "INSERT INTO " + "A" + ID + " VALUES(" + rs.getInt("fileid")
						+ ",'" + rs.getString("filename") + "','"
						+ rs.getString("filepass") + "','"
						+ rs.getString("picture") + "',"
						+ rs.getString("keycode") + ","
						+ rs.getString("keycount") + ",'"
						+ rs.getString("comand") + "')";

				stm.executeUpdate(sql);

			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
		}

	}

	// 選択されたファイルを起動する　メソッド
	public static void Fileboot(int row) {

		try {
			// Select文の結果を代入
			ResultSet rs = Select();

			// 対象のファイルを起動変数
			Runtime rt = Runtime.getRuntime();

			// 選択されたファイルのインデックス番号を指定
			if (rs.absolute(row + 1)) {
				PASS = rs.getString(3); // ファイルのパスを取得
				if( PASS == "" ){
					ClipboardCopy(rs.getString(2));
				} else {
					EXE = new String("explorer " + PASS); // 起動文をEXEに代入

					try {
						rt.exec(EXE); // 指定されたファイルをココで起動
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		} catch (SQLException e) {
			System.out.println("H2に接続できませんでした。5");
		} finally {

		}
	}

	// テーブルの情報を返す メソッド
	public Object[][] Listdisplay() {

		try {
			// Select文の結果を代入
			ResultSet rs = Select();

			// 配列の番地 変数
			int count = 0;

			// 初めてログインされた
			boolean flg = false;

			// DBのテーブルの情報を全て読み込むまで
			while (rs.next()) {

				flg = true;

				// ファイル名・ファイルパス・アイコンのパス　情報を代入
				FILEID = String.valueOf(rs.getInt(1));
				NAME = rs.getString(2);
				PASS = rs.getString(3);
				PICTURE = rs.getString(4);
				COMAND = rs.getString(7);

				String tmp = main.path + "/bin/" + NAME + ".png";

				// テーブル用配列へ代入
				str[count] = new Object[] { new ImageIcon(BigIcon(tmp)),
						FILEID, NAME, PASS, COMAND };

				// 番地+1
				count++;

				// デバッグ用
				System.out.println("取得結果 -> " + FILEID + "  " + NAME + "     "
						+ PASS + "     " + COMAND);
			}

			if (flg == false) {
				for (int i = 0; i < 20; i++) {
					str[i] = new Object[] { new ImageIcon(""), "", "", "", "" };
				}
			}
			
			
		} catch (SQLException e) {
			System.out.println("H2に接続できませんでした。6");
		} finally {

		}
		return str;
	}

	/**
	 * 指定された Image　型 のアイコン画像を返す。
	 *
	 * @param icon
	 *            対象のアイコン画像
	 * @return bigImg 対象のアイコン画像サイズ　×　2 大きく　
	 */
	private Image BigIcon(String icon) {
		// 選択されたアイコンのパスを代入
		ImageIcon icon1 = new ImageIcon(icon);

		MediaTracker tracker = new MediaTracker(main.list);

		// getScaledInstanceで大きさを変更します。
		Image bigImg = icon1.getImage().getScaledInstance(
				(int) (icon1.getIconWidth() * 2.0), -1, Image.SCALE_SMOOTH);

		// MediaTrackerで処理の終了を待ちます。
		tracker.addImage(bigImg, 2);
		return bigImg;
	}

	//
	public static ResultSet Select() {
		ResultSet rs = null;
		try {
			stm = con.createStatement();
			sql = "select * from " + "A" + ID  + " order by fileid";
			rs = stm.executeQuery(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return rs;
	}
	
	public int getKeyCount( int fileid ){
		ResultSet rs = null;
		int keycount = 0;
		try {
			sql = "SELECT keycount from A" + ID + " WHERE fileid=" + fileid;
			rs = stm.executeQuery(sql);
			rs.next();
			
			keycount = rs.getInt("keycount");				

		} catch (SQLException e) {
			System.out.println("～～～～～～H2クラスのgetKeyCountメソッドでエラー発生～～～～～～");
			e.printStackTrace();
		}
	
			return keycount;
	}

	// 選択されたファイルを削除する　メソッド
	public void Delete(int[] row, int count) {

		// 一度DBの情報を取得
		ResultSet rs = Select();

		try {

			// ステートメント生成
			stm = con.createStatement();

			// ファイルIDを格納する変数
			String fileid = null;

			// ファイルIDの格納配列　カウント
			int i = 0;

			// 削除する個数まで
			while (i < count) {

				if (rs.absolute((row[i]) + 1)) {
					fileid = String.valueOf(rs.getInt(1)); // ファイルID を取得
				}

				// 対象ファイルIDを削除する SQL文
				sql = "DELETE FROM " + "A" + ID + " WHERE fileid='" + fileid + "'";
				stm.executeUpdate(sql);

				i++; // カウント + 1
			}

			System.out.println("Delete完了！！");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("H2に接続できませんでした。7");
		} finally {

		}

	}

	// 選択されたファイルを削除する　メソッド
	public static void Delete2(String pass) {

		// 一度DBの情報を取得
		ResultSet rs = Select();

		try {

			// ステートメント生成
			stm = con.createStatement();

			// ファイルIDを格納する変数
			String fileid = null;

			// ファイルIDの格納配列　カウント
			int i = 0;

			// 対象ファイルIDを削除する SQL文
			sql = "DELETE FROM " + "A" + ID + " WHERE filepass='" + pass + "'";
			stm.executeUpdate(sql);

			i++; // カウント + 1

			System.out.println("Delete完了！！");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("H2に接続できませんでした。7");
		} finally {

		}

	}

	// ショートカットキーを変更する
	public void update_key(int keycode, int keycount, int fileid, String comand) {
		try {
			// UPDATE 文
			sql = "UPDATE " + "A" + ID + " SET KEYCODE=" + keycode + ", KEYCOUNT="
					+ keycount + ", comand=" + comand + " WHERE fileid="
					+ fileid;
			stm.executeUpdate(sql);

			System.out.println("UPDATE完了！！");

		} catch (SQLException e) {
			System.out.println("UPDATEできませんでした。");
		} finally {
		}
	}

	// 文字列を変更（更新）する
	public void update_String(int keycode, int keycount, int fileid,
			String comand, String str) {
		try {
			// UPDATE 文
			sql = "UPDATE " + "A" + ID + " SET KEYCODE=" + keycode + ", KEYCOUNT="
					+ keycount + ", filename=" + "'" + str + "'" + ", comand="
					+ comand + " WHERE fileid=" + fileid;
			stm.executeUpdate(sql);

			System.out.println("UPDATE完了！！");

		} catch (SQLException e) {
			System.out.println("UPDATEできませんでした。");
		} finally {
		}
	}

	public static void passChange(String password) {
		sql = "ALTER USER " + "A" + ID + " SET PASSWORD '" + password + "'";

		try {
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	/**
	 * 　ファイル一覧からファイルが存在するか
	 *
	 * @param files
	 *            親ディレクトリ内のファイル一覧
	 */
	private static void CheakFileList(File[] files) {
		// ファイル発見　フラグ
		flg = false;

		// ディレクトリ内検索
		for (int i = 0; i < files.length; i++) {

			// 1つのファイルパスを取得
			File file = files[i];
			String tmp = String.valueOf(file);

			// ディレクトリ内にファイルが存在するか
			if (PASS.equals(tmp)) {
				flg = true;
			}
		}

		// ファイルが存在しなかった場合
		if (flg == false) {

			// ファイルが存在しなかったので、消去処理
			H2.Delete2(PASS); // そのファイル情報を削除

			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "「" + NAME
					+ "」　\nファイル移動 又は 削除されたので、データベースから削除しました。");

			main.list.getlatest(); // 最新の情報を更新

		}

	}

	/**
	 * 登録されたファイルが格納先が合っているか確認する
	 */
//	public static void Cheak() {
//
//		try {
//			// Select文の結果を代入
//			ResultSet rs = Select();
//
//			// ファイル探索　オブジェクト生成
//			//fileSearch search = new fileSearch();
//
//			// DBのテーブルの情報を全て読み込むまで
//			while (rs.next()) {
//
//				// ファイル名・ファイルパス・アイコンのパス　情報を代入
//				NAME = rs.getString(2);
//				PASS = rs.getString(3);
//				System.out.println(PASS);
//
//				if (!list.isEmpty(PASS)) {
//
//					int index = PASS.lastIndexOf("\\"); // ファイルパスから親ディレクトリを取得する際のindex
//					String oya = PASS.substring(0, index);// ファイルの親ディレクトリを取得
//
//					// フォルダではないか
//					if (!getSuffix(NAME).equals(NAME)) {
//
//						// 親ディレクトリからファイルを探索
//						File[] files = search.listFiles(oya, "*."
//								+ getSuffix(NAME));
//						CheakFileList(files);
//						search.clear();
//					} else {
//
//						// 親ディレクトリからフォルダを探索
//						File[] files = search.listFiles(oya, NAME,
//								search.TYPE_DIR, true, 0);
//						CheakFileList(files);
//						search.clear();
//					}
//
//				} else {
//				}
//			}
//
//		} catch (SQLException e) {
//			System.out.println("H2に接続できませんでした。6");
//		} finally {
//
//		}
//
//	}

	/**
	 * 指定された String　型 の拡張子を返す。
	 *
	 * @param fileName
	 *            チェックする ファイル名
	 * @return fileName .以降 の拡張子を返す。
	 */
	public static String getSuffix(String fileName) {
		if (fileName == null)
			return null;
		int point = fileName.lastIndexOf(".");
		if (point != -1) {
			return fileName.substring(point + 1);
		}
		return fileName;
	}

	//送られてきた引数filenameの値をクリップボードにコピーするメソッド
	private static Clipboard ClipboardCopy(String filename){
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection selection = new StringSelection(filename);
		clipboard.setContents(selection, selection);
		return clipboard;
	}
}