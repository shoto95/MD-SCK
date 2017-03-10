import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Font;

import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.HierarchyEvent;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.text.JTextComponent;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.SystemColor;
import java.awt.Window.Type;

import javax.swing.border.BevelBorder;

public class user extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	// ID・パスワード入力変数
	public JPasswordField passwatext = new JPasswordField(25);
	public JTextField textField;

	// ID・パスワード格納配列
	public static String Userstr[] = new String[2];

	// アカウント情報ファイル　作成確認　フラグ
	public boolean flg = true;

	// ID検知　フラグ
	private boolean IDflg = false;

	// パスワード検知　フラグ
	private boolean Passflg = false;

	// ログイン可能確認　フラグ
	public boolean Okflg = false;

	// 初めて対象のIDのログイン確認　フラグ
	static boolean fast = false;

	// ログイン入力回数
	private int count = 1;

	// アカウント情報のファイル内容　格納配列
	private String str[] = new String[51];

	// アカウント情報ファイルパス　格納変数
	public static File newfile;

	// 文字列インデックス番号　格納変数
	private int index = 0;

	// ログインした回数
	public static int logincnt = 0;

	// アカウント登録数
	private final int Ac_count = 50;

	File file;
	private NotUser notu;

	Color INACTIVE = Color.LIGHT_GRAY;

	private JButton confirm = new JButton("\u78BA\u8A8D");
	static JButton confirmB;
	/**
	 * Launch the application.
	 */
	private nUserID use;
	private BufferedWriter writer;
	private boolean KeyFlag = false;
	private list list;

	private MouseEvent start;// WindowDrugMoveクラス内で使うがここに設置しないと動かない
	static  JLabel lblNewLabel_1;
	static JButton CloseB;
	static JButton okB;
	static JLabel lblNewLabel;

	/**
	 * Create the dialog.
	 */
	public user() {
		
		
		
		setBounds(new Rectangle(0, 0, 704, 651));
		getContentPane().setBounds(new Rectangle(0, 0, 704, 651));
		contentPanel.addMouseListener(new WindowDrugMove()); // マウスを最初に掴んだ時
		contentPanel.addMouseMotionListener(new WindowDrugMove()); // マウスをドラッグした時

		addWindowListener(new WindowAdapter() {
			@Override
			// ×ボタンが押されたとき
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}

		});

		setTitle("\u30E6\u30FC\u30B6\u8A8D\u8A3C");
		setBounds(100, 100, 351, 406);

//		contentPanel.setBorder(new LineBorder(Color.CYAN, 1, false));
//		contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		// 画面の縁を消す
		setUndecorated(true);

//		setOpacity(0.5f);
		logincnt++;

		

		confirmB = new JButton("");
		//透明化
		confirmB.setContentAreaFilled(false);
		
		
		confirmB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				passwatext.setEchoChar((char) 0);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				passwatext.setEchoChar('●');
			}
		});
		confirmB.setBounds(291, 207, 41, 27);
		confirmB.setIcon(new ImageIcon(main.path + "\\ログイン画面\\path7651.png"));
		contentPanel.add(confirmB);

		getContentPane().setLayout(null);
//		((JComponent) getContentPane()).setOpaque(false);
		contentPanel.setOpaque(false);
		contentPanel.setLocation(0, 0);
		contentPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPanel.setSize(new Dimension(541, 530));

		passwatext = new JPasswordField(null);
		passwatext.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		passwatext.setForeground(INACTIVE);
		passwatext.setEchoChar((char) 0);
		passwatext.setText("パスワード");
		confirmB.setVisible(false);

		passwatext.addFocusListener(new FocusAdapter() {

			// 　passwatext にキャレットがある場合
			@Override
			public void focusGained(FocusEvent arg0) {
				// パスワード入力部分が空欄でない場合
				if ("パスワード".equals(passwatext.getText())
						&& INACTIVE.equals(passwatext.getForeground())) {
					confirmB.setVisible(true);
					passwatext.setText("");
					Color ok = Color.BLACK;
					passwatext.setForeground(ok);
					passwatext.setEchoChar('●');
					passwatext
							.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
				}
			}

			// 　passwatext にキャレットがない場合
			@Override
			public void focusLost(FocusEvent arg0) {

				// passwatext が空欄である場合
				if ("".equals(passwatext.getText().trim())) {
					confirm.setVisible(false);
					passwatext
							.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
					passwatext.setForeground(INACTIVE);
					passwatext.setEchoChar((char) 0);
					passwatext.setText("パスワード");

				}

			}
		});
		passwatext.setBounds(92, 207, 187, 26);
		passwatext.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		contentPanel.add(passwatext);

		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		textField = new JTextField("");

		textField.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		textField.setForeground(INACTIVE);
		textField.setText("ID");

		textField.addFocusListener(new FocusAdapter() {

			// キャレットが選択されたとき
			@Override
			public void focusGained(FocusEvent arg0) {

				// 入力された時
				if ("ID".equals(textField.getText())
						&& INACTIVE.equals(textField.getForeground())) {
					textField.setText("");
					Color ok = Color.BLACK;
					textField.setForeground(ok);
					textField.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
				}
			}

			@Override
			// キャレットが選択されない時
			public void focusLost(FocusEvent arg0) {

				// textfieldが空欄である場合
				if ("".equals(textField.getText().trim())) {
					textField.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
					textField.setForeground(INACTIVE);
					textField.setText("ID");
				}
			}
		});

		textField.addKeyListener(null);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				passwatext.requestFocus();
			}
		});
		textField.setBounds(92, 135, 187, 29);
		textField.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		contentPanel.add(textField);
		textField.setColumns(10);

		okB = new JButton("");
		okB.setContentAreaFilled(false);
		okB.setBounds(28, 274, 146, 58);
		contentPanel.add(okB);
		okB.setForeground(new Color(240, 240, 240));
		okB.setBorder(BorderFactory.createEmptyBorder());
//		okButton.setContentAreaFilled(false);
		okB.setIcon(new ImageIcon(main.path + "\\ログイン画面\\rect4247.png"));
		okB.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				// 各Okフラグを初期化
				IDflg = false;
				Passflg = false;
				Okflg = false;

				// アカウント情報ファイル作成
				filecreate();

				// アカウント情報を　配列に格納
				Userstr[0] = textField.getText();// ユーザID
				Userstr[1] = passwatext.getText();// パスワード

				try {

					// 両方空白でないか
					if (!(Userstr[0].equals("ID"))
							&& !(Userstr[1].equals("パスワード"))
							&& !(Userstr[1].equals(""))
							&& (!(Userstr[0].equals("")))) {

						if (flg == false) { // 既に作成済みの場合

							str = ReadAccount();

							@SuppressWarnings("resource")
							BufferedReader br = new BufferedReader(
									new FileReader(newfile));
							int i = 0;
							String tmp;

							// ユーザファイル取得
							while (!(tmp = br.readLine()).equals("&")) {
								str[i] = tmp;
								i++;
							}

							// ID ・　パスワード　ハッシュ変換
							String UserID = encryptStr(Userstr[0]);
							String Userpasswd = encryptStr(Userstr[1]);

							// ID探索
							int Index = IDsearch(UserID);

							// ID発見されたか
							if (IDflg == true) {
								PASSWARDsearch(Userpasswd, Index); // ID発見済　パスワード探索

								// パスワード発見されたか
								if (Passflg == true) {
									Okflg = true;

								} else { // パスワード発見できなかった場合

									// アカウント情報と一致しなかった お知らせ
									NotUser();

									// アカウント入力画面でOKクリックされた場合
									if (notu.getflg() == 1) {

										// 3回 アカウント情報入力された場合
										if (count == 3) {
											System.exit(0); // プログラムを終了

										} else { // 1,2回アカウント情報が正しくない時
											// ID　・　パスワード入力部分　クリア
											reset();
											textField.requestFocus();
											count++;
										}
									}
								}

							} else { // 一度アカウント情報が登録されている場合

								// 新しいアカウントを入力された場合
								nUserID();

								// 新規作成画面で OKボタンをクリックされた場合
								if (use.getflg() == 1) {
									// アカウント情報追加
									fast = false;
									Acwriter();

								} else {
									System.out.println("2");
									reset();
									textField.requestFocus();
									setVisible(true);
								}
							}

						} else { // 初めて起動された場合

							// 新しいアカウントを入力された場合
							nUserID();

							// 新規作成画面で OKボタンをクリックされた場合
							if (use.getflg() == 1) {
								// 今回ファイル作成
								Fastaccount(true);

							} else {
								Fastaccount(false);
							}
						}
					} else {
						NotUser();
						reset();
						textField.requestFocus();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				// 新規作成　又は　正しいアカウントを入力された場合
				if (Okflg == true) {
					reset();
					setVisible(false); // ユーザ認証画面　非表示
					list = new list(); // 一覧表 画面生成
					main.label.setText( Userstr[0]); // メイン画面　ID表示
					main.frame.setVisible(true); // メイン画面　表示

					if (!KeyFlag) { // KeyFlag　が　falseであれば
						Key key = new Key();
						KeyFlag = true;
					}

				}

			}
		});
		getRootPane().setDefaultButton(okB);

		CloseB = new JButton("");
		CloseB.setContentAreaFilled(false);
		CloseB.setIcon(new ImageIcon(main.path + "\\ログイン画面\\rect4237.png"));
		CloseB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		CloseB.setBounds(186, 274, 121, 58);
		contentPanel.add(CloseB);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(main.path + "\\ログイン画面\\rect440.png"));
		lblNewLabel_1.setBounds(28, 18, 335, 95);
		contentPanel.add(lblNewLabel_1);

//		JLabel lblNewLabel_1 = new JLabel("New label");
//		lblNewLabel_1.setIcon(new ImageIcon(main.path + "ログイン画面\\rect4247.png"));
//		lblNewLabel_1.setBounds(25, 35, 436, 386);
//		contentPanel.add(lblNewLabel_1);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, -200, 894, 800);
		contentPanel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("C:\\Java\\kadai\\workspace\\\u30B0\u30EB\u30FC\u30D7\\\u30ED\u30B0\u30A4\u30F3\u753B\u9762\\\u30ED\u30B0\u30A4\u30F3\u80CC\u666F.png"));
		
		user.confirmB.setIcon(new ImageIcon(main.path + "\\ログイン画面２\\確認.png"));
		user.okB.setIcon(new ImageIcon(main.path + "\\ログイン画面２\\ログイン.png"));
		user.CloseB.setIcon(new ImageIcon(main.path + "\\ログイン画面２\\閉じる２.png"));
		user.lblNewLabel.setIcon(new ImageIcon(main.path + "\\ログイン画面２\\ログイン背景.png"));
		user.lblNewLabel_1.setIcon(new ImageIcon(main.path + "\\ログイン画面２\\アイコンサブ.png"));
		
	}

	// アカウント情報ファイルを作成　メソッド
	public void filecreate() {

		// アカウント情報のファイル読み込み
		newfile = new File(main.path + "\\bin\\" + "account.txt");

		try {

			// ファイルが作成されていないか
			if (newfile.createNewFile()) {
				System.out.println("ファイル作成完了");
				flg = true;

			} else {
				System.out.println("ファイル作成済み");
				flg = false;
			}

		} catch (IOException e) {
			System.out.println("例外が発生しました。");
			System.out.println(e);
		}

	}

	@SuppressWarnings("resource")
	public String[] ReadAccount() throws FileNotFoundException {

		BufferedReader br = new BufferedReader(new FileReader(newfile));
		int i = 0;
		String tmp;

		// ユーザファイル取得
		try {
			while (!(tmp = br.readLine()).equals("&")) {
				str[i] = tmp;
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	// IDを探索するメソッド
	public int IDsearch(String UserID) {

		int index = -1;
		for (int i = 0; str[i].equals("%") == false && i < Ac_count; i++) {
			index = str[i].indexOf(":");
			if (str[i].substring(0, index).equals(UserID)) {
				IDflg = true;
				index = i;
				break;
			}
		}
		return index;
	}

	// パスワードを探索するメソッド
	public void PASSWARDsearch(String Userpasswd, int i) {

		index = str[i].indexOf(":");
		if (str[i].substring(index + 1).equals(Userpasswd)) {
			Passflg = true;
		}
	}

	// 誤った入力を知らせる　メソッド
	public void NotUser() {
		this.setAlwaysOnTop(false);
		notu = new NotUser();
		notu.setAlwaysOnTop(true);
		notu.setLocationRelativeTo(null);
		notu.setVisible(true);

	}

	// 新規アカウントを作るか知らせる　メソッド
	public void nUserID() {
		this.setAlwaysOnTop(false);
//		use = new nUserID();
		use.setAlwaysOnTop(true);
		use.setLocationRelativeTo(null);
		use.setVisible(true);

	}

	// 追加アカウントを登録する　メソッド
	public void Acwriter() throws IOException {

		writer = new BufferedWriter(new FileWriter(newfile));
		int j;

		for (j = 0; j < Ac_count; j++) {

			// アカウント情報がすでに入っている場合
			if (str[j].equals("%") == false)
				;

			// 「%」 であるところ検知
			else if (str[j].equals("%") && fast == false) {
				// 　「%」 を　アカウント情報と入れ替え
				str[j] = encryptStr(Userstr[0]) + ":" + encryptStr(Userstr[1]);
				fast = true;
			}
		}

		// 新しいIDを書き込む
		str[j] = encryptStr(Userstr[0]) + ":" + encryptStr(Userstr[1]);

		// 配列の内容をアカウント情報のファイルに記入
		for (j = 0; j < Ac_count; j++) {
			writer.write(str[j]);
			writer.newLine();
		}

		// 　最後の　＆　を記入
		writer.write("&");
		writer.close();

		// ログイン可能フラグに OK を代入
		Okflg = true;

	}

	// 初めてアカウント情報を記入する　メソッド
	public void Fastaccount(boolean flg) throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(newfile));

		if (flg == true) {
			// アカウント情報をファイルに記入
			writer.write(encryptStr(Userstr[0]) + ":" + encryptStr(Userstr[1]));
			Okflg = true;
		} else {
			writer.write("%");
			Okflg = false;
		}
		// 初めてログインされたことを保存
		fast = true;

		for (int i = 0; i < Ac_count; i++) {

			// アカウント情報の後に改行
			if (i == 0) {
				writer.newLine();
			}

			// 未登録部分を「%」 記入
			writer.write("%");
			writer.newLine();
		}

		writer.write("&");
		writer.close();

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

//	// 文字列の中が数値のみか判定
//	static boolean isNum(String number) {
//		try {
//
//			Integer.parseInt(number); // 数値に変換できるか
//			return true; // 変換できたので　true
//		} catch (NumberFormatException e) {
//			return false; // 数値以外にも文字が含まれている　false
//		}
//	}

	public void reset() {
		// ID入力部分　初期化
		textField.setText("");
		Color ok = Color.BLACK;
		textField.setForeground(ok);
		textField.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));

		// パスワード入力部分　初期化
		passwatext.setText("パスワード");
		passwatext.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		passwatext.setForeground(INACTIVE);
		passwatext.setEchoChar((char) 0);
		confirmB.setVisible(false);

		// 各OKフラグ初期化
		IDflg = false;
		Passflg = false;
		Okflg = false;

	}

	public list getList() {
		return list;
	}

	public int getcnt() {
		return logincnt;
	}
	
	public void setnUserID(nUserID u){
		use = u;
	}

	/**
	 * 
	 * @author ウィンドウをドラッグする際に、x・ｙ軸を更新するクラス
	 *
	 */
	public class WindowDrugMove implements MouseMotionListener, MouseListener {
		private Point loc;

		public void mouseMoved(MouseEvent me) {
		}

		public void mouseDragged(MouseEvent me) {
			Window window = user.this;
			loc = window.getLocation(loc);
			int x = loc.x - start.getX() + me.getX();
			int y = loc.y - start.getY() + me.getY();
			window.setLocation(x, y);
		}

		public void mouseClicked(MouseEvent me) {
		}

		public void mouseEntered(MouseEvent me) {
		}

		public void mouseExited(MouseEvent me) {
		}

		public void mousePressed(MouseEvent me) {
			start = me;
		}// 最初に掴んだポイントを記憶

		public void mouseReleased(MouseEvent me) {
		}
	}
}
