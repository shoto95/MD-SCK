import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.Window;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Choice;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class main extends JFrame  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 実行フォルダのカレントディレクトリ
	public static String path;
	private JPanel contentPane;
	public static main frame;
	public static nUserID use;
	public static JLabel label;
	public static user User;
	public static Desktop_Change DeskC;
	// アカウント情報 ファイルパス
	public static list list;
	private static User_management Ument;

	// テーブル更新回数
	public static boolean cnt = true;
	static JCheckBox KeyCheckBox;
	static JButton DeskB;
	private MouseEvent start;//WindowDrugMoveクラス内で使うがここに設置しないと動かない

	private static JLabel lblNewLabel;
	static JButton Table;
	static JButton LogoutB;
	static JButton TaskB;
	static JButton buttonB;
	static JButton UserB;
	private JLabel lblId;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws IOException, AWTException {
		EventQueue.invokeLater(new Runnable() {
			

			public void run() {
				try {

					use = new nUserID();

					frame = new main(); // メイン画面表示
					frame.setVisible(false); // メイン画面 表示させない
					frame.setLocationRelativeTo(null); // Windows中心に表示

					User = new user(); // ユーザ認証 画面表示
					User.setVisible(true); // メイン画面 表示させない
					User.setLocationRelativeTo(null);// Windows中心に表示
					User.setnUserID(use);
					Ument = new User_management();

					DeskC = new Desktop_Change();
					
			
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public main() throws IOException, AWTException {
	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		try {
			// windows用　画面に設定
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e2) {
			e2.printStackTrace();
		}
		
				
		setTitle("\u30E1\u30A4\u30F3\u753B\u9762");
		setBounds(100, 100, 548, 484);
		contentPane = new JPanel();
		contentPane.addMouseListener( new WindowDrugMove() ); //マウスを最初に掴んだ時
		contentPane.addMouseMotionListener( new WindowDrugMove() ); //マウスをドラッグした時
	
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// path は　カレントディレクトリ取得
		path = new File("user.dir").getAbsoluteFile().getParent();
	
		// テーブル更新回数 + 1
		//cnt++;
		
		lblId = new JLabel("ID:__________");
		lblId.setFont(new Font("MS UI Gothic", Font.BOLD, 35));
		lblId.setBounds(0, 0, 200, 50);
		contentPane.add(lblId);

		label = new JLabel("");
		label.setFont(new Font("MS UI Gothic", Font.BOLD | Font.ITALIC, 35));
		label.setBounds(61, 10, 188, 25);
		contentPane.add(label);

		// 一覧表ボタン
		Table = new JButton("");
		Table.setIcon(new ImageIcon(path + "\\メイン画面\\\\rect4669.png"));
		Table.setContentAreaFilled(false);
		Table.addMouseListener(new MouseAdapter() {
			
			//ボタンの上にカーソルが乗せられた時
			@Override
			public void mouseEntered(MouseEvent arg0) {
				//ツールチップを作成
				Table.createToolTip();
				//ツールチップで表示される文字列
				Table.setToolTipText("一覧表ボタン");
			

			}
		});
		Table.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ファイル一覧表を表示
				list = User.getList();
				list.setVisible(true);

				// 自分自身を非表示
				//setVisible(false);

			}
		});
		Table.setFont(new Font("MS UI Gothic", Font.PLAIN, 34));
		Table.setBounds(10, 156, 198, 80);
		contentPane.add(Table);
		
		DeskB = new JButton("");
		DeskB.setIcon(new ImageIcon(path + "\\メイン画面\\rect47743.png"));
		DeskB.setContentAreaFilled(false);
		DeskB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DeskC.setVisible(true);
				DeskC.DeskTop_first();
				
				
			}
		});
		DeskB.setBounds(149, 287, 183, 76);
		contentPane.add(DeskB);

		buttonB = new JButton(
				"");
		buttonB.setIcon(new ImageIcon(path + "\\メイン画面\\rect4845.png"));
		buttonB.setContentAreaFilled(false);
		buttonB.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {

				// ボタンフレーム画面表示
				list.Bframe.setVisible(true);
			}
		});
		
				UserB = new JButton("");
				UserB.setContentAreaFilled(false);
				UserB.setIcon(new ImageIcon(path + "\\メイン画面\\rect4587.png"));
				UserB.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						Ument.setVisible(true);

					}

				});
				UserB.setBounds(332, 215, 213, 46);
				contentPane.add(UserB);
		buttonB.setBounds(344, 271, 188, 36);
		contentPane.add(buttonB);

		KeyCheckBox = new JCheckBox(
				"\u30B7\u30E7\u30FC\u30C8\u30AB\u30C3\u30C8\u30AD\u30FC\u3092\u4F7F\u7528\u3057\u306A\u3044");
		KeyCheckBox.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		KeyCheckBox.setForeground(Color.YELLOW);
		KeyCheckBox.setOpaque(false);
		KeyCheckBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				list.KeyCheckBox.setSelected(KeyCheckBox.isSelected());
			}
		});
		
				TaskB = new JButton(
						"");
				TaskB.setIcon(new ImageIcon(path + "\\メイン画面\\rect4847.png"));
				TaskB.setContentAreaFilled(false);
				TaskB.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						list = User.getList();
					}
				});
				TaskB.setBounds(367, 317, 143, 46);
				contentPane.add(TaskB);
		
				LogoutB = new JButton("");
				LogoutB.setIcon(new ImageIcon(path + "\\メイン画面\\rect4849.png"));
				LogoutB.setContentAreaFilled(false);
				LogoutB.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						// 一度H2データベースを切断
						H2.Disconnect();

						User.textField.requestFocus();
						User.setVisible(true); // ユーザ認証 画面表示
						User.setAlwaysOnTop(true);// 一番手前に表示
						User.setLocationRelativeTo(null);// Windowsの真ん中に表示

						// 自分自身の非表示
						setVisible(false);

					}
				});
				LogoutB.setBounds(377, 373, 133, 46);
				contentPane.add(LogoutB);
		KeyCheckBox.setBounds(10, 398, 177, 21);
		contentPane.add(KeyCheckBox);
		
		Choice choice = new Choice();
		choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Choice cho = (Choice)arg0.getItemSelectable();
				if(cho.getSelectedIndex() == 0){		//日本語の場合
					
					Desktop_Change.PrivateB.setIcon(new ImageIcon(path + "\\デスクトップ切り替え画面２\\私用.png"));
					Desktop_Change.WorkB.setIcon(new ImageIcon(path + "\\デスクトップ切り替え画面２\\仕事用.png"));
					Desktop_Change.CallB.setIcon(new ImageIcon(path + "\\デスクトップ切り替え画面２\\呼び出し.png"));
					Desktop_Change.MakeB.setIcon(new ImageIcon(path + "\\デスクトップ切り替え画面２\\作成.png"));
					Desktop_Change.DeleteB.setIcon(new ImageIcon(path + "\\デスクトップ切り替え画面２\\削除.png"));
					Desktop_Change.lblNewLabel.setIcon(new ImageIcon(path + "\\デスクトップ切り替え画面２\\デスクトップ背景.png"));
					
					Button_frame.closeB.setIcon(new ImageIcon(path + "\\ボタン画面２\\閉じる.png"));
					Button_frame.lblNewLabel.setIcon(new ImageIcon(path + "\\メイン画面２\\メイン背景.png"));
					
					list.StartB.setIcon(new ImageIcon(path + "\\一覧表２\\起動.png"));
					list.RegistB.setIcon(new ImageIcon(path + "\\一覧表２\\文字列登録.png"));
					list.ChangeB.setIcon(new ImageIcon(path + "\\一覧表２\\変更.png"));
					list.ImportB.setIcon(new ImageIcon(path + "\\一覧表２\\インポート.png"));
					list.BackupB.setIcon(new ImageIcon(path + "\\一覧表２\\バックアップ.png"));
//					list.CloseB.setIcon(new ImageIcon(path + "\\一覧表２\\閉じる３.png"));
					list.lblNewLabel.setIcon(new ImageIcon(path + "\\一覧表２\\一覧表背景.png"));

					nUserID.okButton.setIcon(new ImageIcon(path + "\\ID作成２\\OK.png"));
					nUserID.cancelButton.setIcon(new ImageIcon(path + "\\ID作成２\\キャンセル.png"));
					nUserID.label.setIcon(new ImageIcon(path + "\\ID作成２\\ID作成背景.png"));
					
					main.Table.setIcon(new ImageIcon(path + "\\メイン画面２\\一覧表.png"));
					main.DeskB.setIcon(new ImageIcon(path + "\\メイン画面２\\デスクトップ.png"));
					main.UserB.setIcon(new ImageIcon(path + "\\メイン画面２\\ユーザ.png"));
					main.buttonB.setIcon(new ImageIcon(path + "\\メイン画面２\\ボタン.png"));
					main.TaskB.setIcon(new ImageIcon(path + "\\メイン画面２\\タスク.png"));
					main.LogoutB.setIcon(new ImageIcon(path + "\\メイン画面２\\ログアウト.png"));
					main.lblNewLabel.setIcon(new ImageIcon(path + "\\メイン画面２\\メイン背景.png"));
					
					
					User_management.User_DeleteB.setIcon(new ImageIcon(path + "\\ユーザ管理画面２\\ID削除.png"));
					User_management.pass_changeB.setIcon(new ImageIcon(path + "\\ユーザ管理画面２\\パス変更.png"));
					User_management.LogoutB.setIcon(new ImageIcon(path + "\\ユーザ管理画面２\\ログアウト２.png"));
					User_management.lblNewLabel.setIcon(new ImageIcon(path + "\\ユーザ管理画面２\\ユーザ背景.png"));
					
					user.confirmB.setIcon(new ImageIcon(path + "\\ログイン画面２\\確認.png"));
					user.okB.setIcon(new ImageIcon(path + "\\ログイン画面２\\ログイン.png"));
					user.CloseB.setIcon(new ImageIcon(path + "\\ログイン画面２\\閉じる２.png"));
					user.lblNewLabel.setIcon(new ImageIcon(path + "\\ログイン画面２\\ログイン背景.png"));
					user.lblNewLabel_1.setIcon(new ImageIcon(path + "\\ログイン画面２\\アイコンサブ.png"));
					
				}else{									//英語の場合
					
					Desktop_Change.PrivateB.setIcon(new ImageIcon(path + "\\デスクトップ切り替え画面\\私用.png"));
					Desktop_Change.WorkB.setIcon(new ImageIcon(path + "\\デスクトップ切り替え画面\\仕事用.png"));
					Desktop_Change.CallB.setIcon(new ImageIcon(path + "\\デスクトップ切り替え画面\\呼び出し.png"));
					Desktop_Change.MakeB.setIcon(new ImageIcon(path + "\\デスクトップ切り替え画面\\作成.png"));
					Desktop_Change.DeleteB.setIcon(new ImageIcon(path + "\\デスクトップ切り替え画面\\削除.png"));
					Desktop_Change.lblNewLabel.setIcon(new ImageIcon(path + "\\デスクトップ切り替え画面\\デスクトップ背景.png"));
					
					Button_frame.closeB.setIcon(new ImageIcon(path + "\\ボタン画面\\閉じる.png"));
					Button_frame.lblNewLabel.setIcon(new ImageIcon(path + "\\メイン画面\\メイン背景.png"));
					
					list.StartB.setIcon(new ImageIcon(path + "\\一覧表\\起動.png"));
					list.RegistB.setIcon(new ImageIcon(path + "\\一覧表\\文字列登録.png"));
					list.ChangeB.setIcon(new ImageIcon(path + "\\一覧表\\変更.png"));
					list.ImportB.setIcon(new ImageIcon(path + "\\一覧表\\インポート.png"));
					list.BackupB.setIcon(new ImageIcon(path + "\\一覧表\\バックアップ.png"));
//					list.CloseB.setIcon(new ImageIcon(path + "\\一覧表\\閉じる３.png"));
					list.lblNewLabel.setIcon(new ImageIcon(path + "\\一覧表２\\一覧表背景.png"));
					
					System.out.println("デバッグ:path=" + path);
					nUserID.okButton.setIcon(new ImageIcon(path + "\\ID作成\\OK.png"));
					nUserID.cancelButton.setIcon(new ImageIcon(path + "\\ID作成\\キャンセル.png"));
					nUserID.label.setIcon(new ImageIcon(path + "\\ID作成\\ID作成背景.png"));
					
					main.Table.setIcon(new ImageIcon(path + "\\メイン画面\\一覧表.png"));
					main.DeskB.setIcon(new ImageIcon(path + "\\メイン画面\\デスクトップ.png"));
					main.UserB.setIcon(new ImageIcon(path + "\\メイン画面\\ユーザ.png"));
					main.buttonB.setIcon(new ImageIcon(path + "\\メイン画面\\ボタン.png"));
					main.TaskB.setIcon(new ImageIcon(path + "\\メイン画面\\タスク.png"));
					main.LogoutB.setIcon(new ImageIcon(path + "\\メイン画面\\ログアウト.png"));
					main.lblNewLabel.setIcon(new ImageIcon(path + "\\メイン画面２\\メイン背景.png"));
					
					User_management.User_DeleteB.setIcon(new ImageIcon(path + "\\ユーザ管理画面\\ID削除.png"));
					User_management.pass_changeB.setIcon(new ImageIcon(path + "\\ユーザ管理画面\\パス変更.png"));
					User_management.LogoutB.setIcon(new ImageIcon(path + "\\ユーザ管理画面\\ログアウト２.png"));
					User_management.lblNewLabel.setIcon(new ImageIcon(path + "\\ユーザ管理画面\\ユーザ背景.png"));
					
					user.confirmB.setIcon(new ImageIcon(path + "\\ログイン画面\\確認.png"));
					user.okB.setIcon(new ImageIcon(path + "\\ログイン画面\\ログイン.png"));
					user.CloseB.setIcon(new ImageIcon(path + "\\ログイン画面\\閉じる２.png"));
					user.lblNewLabel.setIcon(new ImageIcon(path + "\\ログイン画面\\ログイン背景.png"));
					user.lblNewLabel_1.setIcon(new ImageIcon(path + "\\ログイン画面\\アイコンサブ.png"));
					
					
				}
				
				
			}
		});
		choice.setBounds(286, 10, 210, 19);
		contentPane.add(choice);
		
		choice.add("日本語");
		choice.add("English");
		
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Java\\kadai\\workspace\\\u30B0\u30EB\u30FC\u30D7\\\u30E1\u30A4\u30F3\u753B\u9762\\\u30E1\u30A4\u30F3\u80CC\u666F.png"));
//		lblNewLabel.setIcon(new ImageIcon(path + "\\メイン画面\\rect45870.png"));
		lblNewLabel.setBounds(0, -134, 722, 596);
		contentPane.add(lblNewLabel);

		/*
		 * タスクトレイ制御　プログラム
		 */
		addWindowListener(new MyWindowListener(this)); // ウィンドウの情報を取得
		SystemTray tray = SystemTray.getSystemTray(); // システムトレイ情報を取得

		// タスクトレイに表示するアイコンをセット
		TrayIcon icon = new TrayIcon(
				ImageIO.read(new File(path + "\\タイトル3.png")));

		// ポップアップメニュー
		PopupMenu menu = new PopupMenu();

		// メイン画面
		MenuItem aItem = new MenuItem("メイン画面");
		aItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// ユーザ認証画面　表示されていないか
				if (!User.isShowing()) {
					setAutoRequestFocus(true); // メイン画面　フォーカスをあてる
					setVisible(true); // メイン画面　表示
				}

			}
		});

		// 終了メニュー
		MenuItem exitItem = new MenuItem("終了");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// タスクトレイにアイコンをダブルクリックされたら
		/*
		 * icon.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { setVisible(true); // メイン画面を表示 } });
		 */

		// メニューにメニューアイテムを追加
		tray.add(icon); // タスクトレイにアイコンをセット
		icon.setPopupMenu(menu);
		menu.add(aItem);
		menu.add(exitItem);
		
		main.Table.setIcon(new ImageIcon(path + "\\メイン画面２\\一覧表.png"));
		main.DeskB.setIcon(new ImageIcon(path + "\\メイン画面２\\デスクトップ.png"));
		main.UserB.setIcon(new ImageIcon(path + "\\メイン画面２\\ユーザ.png"));
		main.buttonB.setIcon(new ImageIcon(path + "\\メイン画面２\\ボタン.png"));
		main.TaskB.setIcon(new ImageIcon(path + "\\メイン画面２\\タスク.png"));
		main.LogoutB.setIcon(new ImageIcon(path + "\\メイン画面２\\ログアウト.png"));
		main.lblNewLabel.setIcon(new ImageIcon(path + "\\メイン画面２\\メイン背景.png"));

	}

	/**
	 * 
	 * @author 
	 * ウィンドウをドラッグする際に、x・ｙ軸を更新するクラス
	 *
	 */
	public class WindowDrugMove implements MouseMotionListener, MouseListener{
		private Point loc;
		public void mouseMoved(MouseEvent me){}
		public void mouseDragged(MouseEvent me){
		Window window = main.this;
		loc = window.getLocation(loc);
		int x = loc.x - start.getX() + me.getX();
		int y = loc.y - start.getY() + me.getY();
		window.setLocation(x, y);
		}

		public void mouseClicked(MouseEvent me){}
		public void mouseEntered(MouseEvent me){}
		public void mouseExited(MouseEvent me){}
		public void mousePressed(MouseEvent me) { start=me; }//最初に掴んだポイントを記憶
		public void mouseReleased(MouseEvent me){}
		}
	
	

	public static boolean getKeyCheckBox() {

		return KeyCheckBox.isSelected();
	}
}

/* タスクトレイ判断用 */
class MyWindowListener extends WindowAdapter {

	private main main = null;

	public MyWindowListener(main initializer) {
		main = initializer;
	}

	//
	public void windowIconified(WindowEvent e) {
		main.setVisible(false);
	}

	// アプリが閉じられた時
	public void windowClosing(WindowEvent e) {
		main.setIconImage(null);
		System.exit(0);
	}
	
	
	
	
}
