import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class User_management extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static File Account = new File(main.path + "\\bin\\"
			+ "account.txt");;
	private  final static int User_cnt = 50;
	static JButton pass_changeB;
	static JButton User_DeleteB;
	static JButton LogoutB;
	static JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */	
	public User_management() {
		setTitle("\u30E6\u30FC\u30B6\u7BA1\u7406\u753B\u9762");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 358, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		User_DeleteB = new JButton("");
		User_DeleteB.setContentAreaFilled(false);
		User_DeleteB.setIcon(new ImageIcon(main.path + "\\ユーザ管理画面\\text7209.png"));
		User_DeleteB.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// ユーザ削除画面　 ダイアログを表示
				JFrame frame = new JFrame();
				int ret = JOptionPane.showConfirmDialog(frame, "この   「"
						+ user.Userstr[0] + "」  を削除してもよろしいですか？", "ユーザ削除画面",
						JOptionPane.YES_NO_OPTION);

				// ユーザ削除画面　OKが押された場合
				if (ret == JOptionPane.OK_OPTION) {

					// H2データベースを切断
					H2.Disconnect();

					// ユーザのH2DBのファイルを取得
					File mv = new File(main.path + "\\bin\\" + user.Userstr[0]
							+ ".mv.db");
					File trace = new File(main.path + "\\bin\\"
							+ user.Userstr[0] + ".trace.db");

					// アカウント情報　ファイルパス
					Account = new File(main.path + "\\bin\\" + "account.txt");

					// mv　・　traceファイルを削除
					mv.delete();
					trace.delete();

					// アカウント情報を配列に格納
					String str[] = new String[50];
					try {
						str = main.User.ReadAccount();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}

					// 対象ユーザのアカウント情報を削除
					try {
						DeleteAccount(str);
					} catch (IOException e) {
						e.printStackTrace();
					}

					// ユーザ管理画面を非表示
					setVisible(false);

					// ユーザ認証画面を表示
					main.User.passwatext.setText(null);
					main.User.textField.setText(null);
					main.User.textField.requestFocus();
					main.User.Okflg = false;
					main.User.setVisible(true);

				}
			}
		});
		User_DeleteB.setBounds(0, 125, 152, 103);
		contentPane.add(User_DeleteB);

		pass_changeB = new JButton(
				"");
		pass_changeB.setContentAreaFilled(false);
		pass_changeB.setIcon(new ImageIcon(main.path + "\\ユーザ管理画面\\rect4773.png"));
		pass_changeB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// ユーザ管理画面を非表示
				setVisible(false);

				// メイン画面非表示
				main.frame.setVisible(false);

				// パスワード変更画面
				Password_Question passquestion = new Password_Question();
				passquestion.setVisible(true);

				// アカウント情報を配列に格納
				String str[] = new String[10];
				try {
					str = main.User.ReadAccount();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

				if (passquestion.flg == true) {
					try {

						ChangePassword(str);
						H2.Disconnect();

						main.frame.setVisible(false);
						main.User.textField.setText("");
						main.User.passwatext.setText("");
						main.User.setVisible(true);

					} catch (IOException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
				}

			}
		});
		pass_changeB.setBounds(184, 125, 146, 103);
		contentPane.add(pass_changeB);

		LogoutB = new JButton("");
		LogoutB.setIcon(new ImageIcon(main.path + "\\ユーザ管理画面\\rect440.png"));
		LogoutB.setContentAreaFilled(false);
		LogoutB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// 一度H2データベースを切断
				H2.Disconnect();

				
				main.User.textField.requestFocus();
				main.frame.setVisible(false);
				main.User.setVisible(true); // ユーザ認証 画面表示
				main.User.setAlwaysOnTop(true);// 　一番手前に表示
				main.User.setLocationRelativeTo(null);// Windowsの真ん中に表示

				// 自分自身の非表示
				setVisible(false);

			}
		});
		LogoutB.setBounds(98, 252, 177, 46);
		contentPane.add(LogoutB);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Java\\kadai\\workspace\\Group6-13\\\u30E6\u30FC\u30B6\u7BA1\u7406\u753B\u9762\uFF12\\\u30E6\u30FC\u30B6\u80CC\u666F.png"));
		lblNewLabel.setBounds(0, -275, 523, 864);
		contentPane.add(lblNewLabel);

		User_management.User_DeleteB.setIcon(new ImageIcon(main.path + "\\ユーザ管理画面２\\ID削除.png"));
		User_management.pass_changeB.setIcon(new ImageIcon(main.path + "\\ユーザ管理画面２\\パス変更.png"));
		User_management.LogoutB.setIcon(new ImageIcon(main.path + "\\ユーザ管理画面２\\ログアウト２.png"));
		User_management.lblNewLabel.setIcon(new ImageIcon(main.path + "\\ユーザ管理画面２\\ユーザ背景.png"));
		
	}

	// 対象のユーザを削除する　メソッド
	private void DeleteAccount(String[] str) throws IOException {

		// 対象ユーザ情報を 変数　に格納
		String info = user.encryptStr(user.Userstr[0]) + ":"
				+ user.encryptStr(user.Userstr[1]);

		// ユーザ情報を探索
		for (int i = 0; i < User_cnt ; i++) {
			// 対象ユーザを検知
			if (str[i].equals(info)) {
				str[i] = "%"; // 対象ユーザの所　「%」　を格納
			}
		}

		// 記述用変数に アカウント情報を代入
		BufferedWriter writer = new BufferedWriter(new FileWriter(Account));

		// 配列の内容を アカウント情報のファイルに格納
		for (int i = 0; i < User_cnt ; i++) {
			writer.write(str[i]);
			writer.newLine();
		}

		// 「&」 を最後を格納
		writer.write("&");
		writer.close();

	}

	// 対象のユーザのパスワード変更　メソッド
	private void ChangePassword(String[] str) throws IOException {

		// 対象ユーザ情報を 変数　に格納
		String preinfo = user.encryptStr(user.Userstr[0]) + ":"
				+ user.encryptStr(user.Userstr[1]);
		String info = user.encryptStr(user.Userstr[0]) + ":"
				+ user.encryptStr(Password_Question.Cpassword);

		// ユーザ情報を探索
		for (int i = 0; i < User_cnt; i++) {
			// 対象ユーザを検知
			if (str[i].equals(preinfo)) {
				str[i] = info; // 対象ユーザの所を　「新しいパスワード」　を格納
			}
		}
		System.out.println(Account);
		// 記述用変数に アカウント情報を代入
		BufferedWriter writer = new BufferedWriter(new FileWriter(Account));

		// 配列の内容を アカウント情報のファイルに格納
		for (int i = 0; i < User_cnt; i++) {
			writer.write(str[i]);
			writer.newLine();
		}

		// 「&」 を最後を格納
		writer.write("&");
		writer.close();

		H2.passChange(Password_Question.Cpassword);

	}

}
