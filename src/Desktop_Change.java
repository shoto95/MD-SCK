import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Toolkit;


public class Desktop_Change extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField TextBox;
	static JButton WorkB;
	static JButton MakeB;
	static JButton CallB;
	static JButton PrivateB;
	private JTable table;
	private String EXPATH;

	// デスクトップのディレクトリパス　格納配列
	public String[][] tabledata = new String[20][2];

	// 項目 配列
	public String[] columnNames = { "デスクトップ", "パス" };
	public static String USERHOME = System.getProperty("user.home");
	private JLabel use;

	makefolder Mfolder;

	private String DName = null; // 現在使用中のデスクトップの名前を保存
	private File SCK_D;
	private File Private_D;
	private File Work_D;
	private File Extra_D;
	private File Path_f;
	private String Primal;
	static String PrivatePath;
	static String WorkPath = USERHOME + "\\SCK\\Work";
	static JLabel lblNewLabel;
	static JButton DeleteB;

	public Desktop_Change() {
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						"C:\\Java\\kadai\\workspace\\\u30B0\u30EB\u30FC\u30D7\\\u30A2\u30A4\u30B3\u30F32.png"));
		setTitle("\u30C7\u30B9\u30AF\u30C8\u30C3\u30D7\u5207\u308A\u66FF\u3048\u753B\u9762");

		System.setProperty("system_property", "user.home");

		System.out.println("デバック" + USERHOME);

		// SCKフォルダ作成
		String SCK_f = USERHOME + "\\SCK";
		SCK_D = new File(SCK_f);
		SCK_D.mkdir();

		// プライベート用フォルダ生成
		PrivatePath = USERHOME + "\\SCK\\Private";
		Private_D = new File(PrivatePath);
		Private_D.mkdir();

		// 仕事用フォルダ生成
		String WorkPath = USERHOME + "\\SCK\\Work";
		Work_D = new File(WorkPath);
		Work_D.mkdir();

		// MakeFolder用フォルダ生成
		EXPATH = USERHOME + "\\SCK\\Extra";
		Extra_D = new File(EXPATH);
		Extra_D.mkdir();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 840, 578);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 最新の情報を取り入れる
		saishin();

		// 私用
		PrivateB = new JButton("");
		PrivateB.setContentAreaFilled(false);
		PrivateB.setIcon(new ImageIcon(main.path
				+ "\\デスクトップ切り替え画面\\rect4737-7.png"));
		PrivateB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {

					int btn = arg0.getButton();

					if (btn == MouseEvent.BUTTON1) {

						WorkB.setEnabled(false);
						CallB.setEnabled(false);

						Mfolder = new makefolder(PrivatePath);
						setDesktopName("私用");

						Thread.sleep(2000);

						JOptionPane.showMessageDialog(null, "移動完了いたしました。");

						WorkB.setEnabled(true);
						CallB.setEnabled(true);

					} else if (btn == MouseEvent.BUTTON3) {
						System.out.println(PrivatePath);
						Runtime rt = Runtime.getRuntime();
						String OFile = "explorer " + PrivatePath;
						rt.exec(OFile);
					}

				} catch (IOException | InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();

				}

			}
		});

		PrivateB.setFont(new Font("MS UI Gothic", Font.PLAIN, 23));
		PrivateB.setBounds(12, 50, 175, 67);
		contentPane.add(PrivateB);

		// 仕事用
		WorkB = new JButton("");
		WorkB.setIcon(new ImageIcon(main.path + "\\デスクトップ切り替え画面\\rect4558.png"));
		WorkB.setContentAreaFilled(false);
		WorkB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {

					int btn = arg0.getButton();

					if (btn == MouseEvent.BUTTON1) {

						PrivateB.setEnabled(false);
						CallB.setEnabled(false);

						Mfolder = new makefolder(WorkPath);
						setDesktopName("仕事用");

						Thread.sleep(2000);

						JOptionPane.showMessageDialog(null, "移動完了いたしました。");

						PrivateB.setEnabled(true);
						CallB.setEnabled(true);

					} else if (btn == MouseEvent.BUTTON3) {
						System.out.println(WorkPath);
						Runtime rt = Runtime.getRuntime();
						String OFile = "explorer " + WorkPath;
						rt.exec(OFile);
					}

				} catch (IOException | InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();

				}
			}
		});
		WorkB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg1) {

			}
		});
		WorkB.setFont(new Font("MS UI Gothic", Font.PLAIN, 23));
		WorkB.setBounds(12, 144, 175, 67);
		contentPane.add(WorkB);

		// 呼び出し
		CallB = new JButton("");
		CallB.setContentAreaFilled(false);
		CallB.setIcon(new ImageIcon(main.path + "\\デスクトップ切り替え画面\\rect4564.png"));
		CallB.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent arg0) {

				int btn = arg0.getButton();

				try {

					if (btn == 1) {

						PrivateB.setEnabled(false);
						WorkB.setEnabled(false);
						new makefolder(table.getValueAt(table.getSelectedRow(),
								1).toString());

						Thread.sleep(2000);

						String Name = (String) table.getValueAt(
								table.getSelectedRow(), 0);
						setDesktopName(Name);
						JOptionPane.showMessageDialog(null, "移動完了いたしました。");
						PrivateB.setEnabled(true);
						WorkB.setEnabled(true);

					}
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		});

		CallB.setFont(new Font("MS UI Gothic", Font.PLAIN, 23));
		CallB.setBounds(12, 248, 175, 67);
		contentPane.add(CallB);

		// 作成
		MakeB = new JButton("");
		MakeB.setContentAreaFilled(false);
		MakeB.setIcon(new ImageIcon(main.path + "\\デスクトップ切り替え画面\\rect4534.png"));
		MakeB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// プロジェクトフォルダのパスを代入
				String mpath = EXPATH + "\\" + TextBox.getText();
				System.out.print(mpath);
				File NFile = new File(mpath);
				NFile.mkdir();

				// 最新情報の更新
				saishin();
				try {

					BufferedReader br = new BufferedReader(
							new FileReader(NFile));
					String Primal = br.readLine();

					use.setText(""/* 表示させい現在のデスクトップ名 */);

				} catch (IOException e) {
					System.out.println(e);
				}

			}
		});
		MakeB.setFont(new Font("MS UI Gothic", Font.PLAIN, 23));
		MakeB.setBounds(474, 429, 168, 67);
		contentPane.add(MakeB);

		TextBox = new JTextField();
		TextBox.setBounds(232, 429, 204, 67);
		contentPane.add(TextBox);
		TextBox.setColumns(10);

		DeleteB = new JButton("");
		DeleteB.setIcon(new ImageIcon(main.path
				+ "\\デスクトップ切り替え画面\\rect4542.png"));
		DeleteB.setContentAreaFilled(false);
		DeleteB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// 確認画面を表示
				JFrame kakunin = new JFrame();

				// 消去するのか判断する変数
				int option = JOptionPane.showConfirmDialog(kakunin,
						"本当に選択されたものを削除してよろしいでしょうか。", "確認画面",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				
				if (option == 0) {

					// クリックされたセルの内容を取得
					String Rpass = table.getValueAt(table.getSelectedRow(), 1)
							.toString();

					// クリックされた行のディレクトリを削除
					File RrFile = new File(Rpass);
					delete(RrFile);

					// 最新の情報に更新
					saishin();

				}
			}
		});
		DeleteB.setFont(new Font("MS UI Gothic", Font.PLAIN, 23));
		DeleteB.setBounds(644, 429, 168, 67);
		contentPane.add(DeleteB);

		use = new JLabel("");
		use.setFont(new Font("MS UI Gothic", Font.BOLD, 29));
		use.setForeground(Color.YELLOW);
		use.setBounds(60, 445, 127, 51);
		contentPane.add(use);

		lblNewLabel = new JLabel("");
		lblNewLabel
				.setIcon(new ImageIcon(
						"C:\\Java\\kadai\\workspace\\\u30B0\u30EB\u30FC\u30D7\\\u30C7\u30B9\u30AF\u30C8\u30C3\u30D7\u5207\u308A\u66FF\u3048\u753B\u9762\\\u30C7\u30B9\u30AF\u30C8\u30C3\u30D7\u80CC\u666F.png"));
		lblNewLabel.setBounds(-33, -75, 897, 614);
		contentPane.add(lblNewLabel);

		PrivateB.setIcon(new ImageIcon(main.path + "\\デスクトップ切り替え画面２\\私用.png"));
		WorkB.setIcon(new ImageIcon(main.path + "\\デスクトップ切り替え画面２\\仕事用.png"));
		CallB.setIcon(new ImageIcon(main.path + "\\デスクトップ切り替え画面２\\呼び出し.png"));
		MakeB.setIcon(new ImageIcon(main.path + "\\デスクトップ切り替え画面２\\作成.png"));
		DeleteB.setIcon(new ImageIcon(main.path + "\\デスクトップ切り替え画面２\\削除.png"));
		lblNewLabel.setIcon(new ImageIcon(main.path
				+ "\\デスクトップ切り替え画面２\\デスクトップ背景.png"));

	}

	public void DeskTop_first() {

		String Path_t = USERHOME + "\\SCK\\Path.txt";
		Path_f = new File(Path_t);

		if (!Path_f.exists()) {
			JFrame sirase = new JFrame();
			int btn = JOptionPane.showConfirmDialog(sirase,
					"現在のデスクトップの状態を「私用」に設定します。", "お知らせ", JOptionPane.OK_OPTION,
					JOptionPane.INFORMATION_MESSAGE);

			if (btn != 1) {
				Mfolder = new makefolder(WorkPath);
				use.setText("仕事用");

				try {
					Thread.sleep(2000);
					Mfolder = new makefolder(WorkPath);
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "移動完了いたしました。");

			}

		} else {

			File txt = new File(System.getProperty("user.home")
					+ "\\SCK\\path.txt");
			// プライベートよぶ
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(txt));
			} catch (FileNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			try {
				Primal = br.readLine();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			try {
				br.close();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			if (Primal.equals(PrivatePath)) {
				use.setText("私用");
			} else if (Primal.equals(WorkPath)) {
				use.setText("仕事用");
			} else {
				// use.setText("仕事用");
			}

		}
	}

	// テーブルの最新情報を更新
	public void saishin() {
		// tabledata配列を初期化
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < tabledata.length; j++) {
				tabledata[j][i] = "";
			}
		}

		// MakeFolder内のディレクトリ探索
		File dir1 = new File(EXPATH);
		File[] exfiles = dir1.listFiles();
		// tabledata配列に、MakeFolder内のディレクトリ情報を代入
		for (int i = 0; i < exfiles.length; i++) {
			tabledata[i][1] = exfiles[i].getPath();
			tabledata[i][0] = exfiles[i].getName();
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(208, 50, 604, 265);
		contentPane.add(scrollPane);
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane.setRowHeaderView(scrollPane_1);

		// 最新の情報を JTableに代入
		table = new JTable(tabledata, columnNames);
		table.addMouseListener(new MouseAdapter() {
			private String value;
			private String FileName;
			private String FilePass;
			private String EXE;

			@Override
			public void mouseClicked(MouseEvent arg0) {
				int btn = arg0.getButton();

				if (btn == MouseEvent.BUTTON1) {

					table = (JTable) arg0.getSource();// 選択された情報をtableに代入
					int[] index = table.getSelectedRows();

					FilePass = (String) table.getValueAt(
							table.getSelectedRow(), 1); // ファイル名取得
					System.out.println(FilePass);

				} else if (btn == MouseEvent.BUTTON3) {

					// 選択されたセルは、nullか
					if (!(isEmpty(FilePass))) {

						// 対象のファイルを起動変数
						Runtime rt = Runtime.getRuntime();
						EXE = new String("explorer " + FilePass); // 起動文をEXEに代入

						try {
							rt.exec(EXE); // 指定されたファイルをココで起動
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}

			}
		});
		scrollPane.setViewportView(table);

		// 何度クリックしても、編集状態を不可
		table.setDefaultEditor(Object.class, null);
	}

	static private void delete(File f) {
		if (f.exists() == false) {
			return;
		}

		if (f.isFile()) {
			f.delete();
		}

		if (f.isDirectory()) {
			File[] files = f.listFiles();
			for (int i = 0; i < files.length; i++) {
				delete(files[i]);
			}
			f.delete();
		}
	}

	/**
	 * 指定された String が null または空文字列かどうかを返します。
	 *
	 * @param value
	 *            チェックする String
	 * @return null または空文字列かどうか。null または空文字列なら true 、それ以外なら false 。
	 */
	public static boolean isEmpty(String value) {

		if (value == null || value.length() == 0)
			return true;
		else
			return false;
	}

	//
	public void setDesktopName(String Name) {
		use.setText(Name);
	}
}
