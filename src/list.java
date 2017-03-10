import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class list extends JFrame implements DropTargetListener {
	private static final long serialVersionUID = 1L;
	// ファイルID ・ファイル名・ファイルパスを格納する配列
	public Object[][] tabledata = { { new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" } };

	// 項目 配列
	private String[] columnNames = { "アイコン", "ファイルID", "ファイル名/文字列", "ファイルパス","コマンド" };
	JTable Table;
	static JTable Table2;
	JScrollPane scroll;
	private DefaultTableModel tableModel;
	// H2 接続変数
	public Connection con = null;
	// 選択されたファイル名
	private String value = null;
	// ファイル選択変数
	private JFileChooser chooser;
	// アイコンパス 変数
	private ImageIcon icon1 = null;
	public static H2 h2;
	public static JCheckBox KeyCheckBox;
	private static boolean DialogFlag = false; // キー登録・変更のダイアログが表示中はキー判定は行わないためのフラグ
	// キー設定ダイアログ
	private KeyDialog KDlg = new KeyDialog(this);
	// 文字列ダイアログ
	private StringDialog SDlg = new StringDialog(this);
	// コマンド保存変数　
	private String comand1 = "";
	private String comand2 = "";
	public static Button_frame Bframe;
	// 選択された行のfileidを保存するための変数
	private int IndexID;
	// 選択された行のfilenameとfilepathを保存するための変数
	private String FileName = null;
	private String FilePath = null;
	int count = 0;
	int[] index;
	static JButton RegistB;
	static JButton ChangeB;
	static JButton StartB;
	static JButton BackupB;
	static JButton ImportB;
	static JLabel lblNewLabel;
	class MyTableModel extends DefaultTableModel {
		MyTableModel(Object[][] objects, String[] columnNames) {
			super(objects, columnNames);
		}
		public Class getColumnClass(int col) {
			return getValueAt(0, col).getClass();
		}
	}

	public list() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			// 選択されたファイルの情報を格納
			chooser = new JFileChooser();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// getContentPane
		setTitle("\u4E00\u89A7\u8868");
		this.setSize(new Dimension(731, 511));
		this.setLocationRelativeTo(null);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		getContentPane().setLayout(null);

		// 入力されたアカウント情報をログイン
		h2 = new H2(user.Userstr[0].toString(), user.Userstr[1].toString());
		SDlg.setH2(h2);
		KDlg.setH2(h2);
		
		// デバッグ用
		System.out.println(user.fast);
		
		// ログインしたことのないIDを入力された場合
		if (user.fast == true) {
			H2.Create(); // 対象IDのデーブルを作成
		}

		Bframe = new Button_frame(h2.Listdisplay()); // ボタン起動フレーム　生成
		Bframe.setBounds(760, 0, 490, 200);
		Bframe.setlatest(h2.Listdisplay()); // 情報を更新
		Bframe.setVisible(true); // ボタン起動フレーム　表示		
		latest(); // 最新の情報　更新

		Table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*
				 * クリックされた値を取得　　1:左クリック 3 右クリック
				 */
				int btn = arg0.getButton();
				// 左クリックされたか
				if (btn == MouseEvent.BUTTON1) {
					Table2 = Table = (JTable) arg0.getSource();// 選択された情報をtableに代入
					int[] index = Table.getSelectedRows();
					value = (String) tabledata[index[0]][1];
					FileName = (String) Table.getValueAt(Table.getSelectedRow(), 1);
					// 選択されたセルは、nullか
					if (!(isEmpty(value))) {
//						System.out.println("*デバッグ*：value=" + value);
						IndexID = Integer.parseInt(value);
					} else {
						IndexID = 0;
						latest();
					}
					// 右クリック　（削除処理）　
				} else if (btn == MouseEvent.BUTTON3) {
					Table2 = Table = (JTable) arg0.getSource(); // 選択された情報をtableに代入
					// 選択された個数　変数
					int count = Table.getSelectedRowCount();
					// 選択されたインデックス番号 配列
					int[] index = Table.getSelectedRows();
					boolean blankflg = false;
					for (int i = 0; i < index.length; i++) {
						if (!tabledata[index[i]][1].equals("")) {
							blankflg = true;
						}
					}
					if (blankflg == true) {
						// 確認画面を表示
						JFrame kakunin = new JFrame();
						// 消去するのか判断する変数
						int option = JOptionPane.showConfirmDialog(kakunin,"本当に選択されたものを削除してよろしいでしょうか。", "確認画面",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
						// 確認画面のOKされた場合
						if (option == 0) {
							h2.Delete(index, count); // 削除処理
						}
						// 選択された数が0ではない場合
						while (count != 0) {
							tabledata[index[count - 1]] = new Object[] {new ImageIcon(""), "", "", "", "" };
							for (int i = count; !(tabledata[i + 1].equals(null))&& i < 10; i++) { // 行を前に詰める処理
								tabledata[i] = tabledata[i + 1]; // クリアされた行に挿入
								tabledata[i + 1] = new Object[] {new ImageIcon(""), "", "", "", "" }; // 挿入した行をクリア
								Table.clearSelection(); // 選択状態をクリア
							}
							count--; // 選択された個数 -1をする
						}
					}
					latest(); // 最新の情報に更新
				}
			}
		});
		StartB = new JButton("");
		StartB.setBackground(UIManager.getColor("Button.background"));
		StartB.setOpaque(false);
		StartB.setContentAreaFilled(false);
		StartB.setIcon(new ImageIcon(main.path + "\\一覧表\\path7651.png"));
		StartB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				h2.Fileboot(Table.getSelectedRow()); // 選択されたファイルを起動
			}
		});
		StartB.setBounds(9, 22, 139, 47);
		getContentPane().add(StartB);
		RegistB = new JButton("");
		RegistB.setBackground(Color.BLACK);
		RegistB.setOpaque(false);
		RegistB.setContentAreaFilled(false);
		RegistB.setIcon(new ImageIcon(main.path + "\\一覧表\\path7652.png"));
		RegistB.addActionListener(new ActionListener() {
			// 文字列登録の処理
			public void actionPerformed(ActionEvent arg0) {
				DialogFlag = true;
				SDlg.setTextField("");
				SDlg.setDialog();
				SDlg.setInformation(false);
				SDlg.setVisible(true);
				if (SDlg.getOkFlag()) {
					h2.insert_String(SDlg.getTextField(), SDlg.getKCode(),SDlg.getKCnt(), SDlg.getKName());
					latest();
				}
				DialogFlag = false;
			}
		});
		RegistB.setBounds(9, 79, 139, 37);
		getContentPane().add(RegistB);
		ChangeB = new JButton("");
		ChangeB.setContentAreaFilled(false);
		ChangeB.addActionListener(new ActionListener() {
			// 文字列変更処理
			public void actionPerformed(ActionEvent arg0) {
				// 空白の行を選択されたとき
				if (IndexID == 0) {
					JOptionPane.showConfirmDialog(null, "選択された行にはデータが存在しません。","データなし", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
				} else if (tabledata[Table.getSelectedRow()][3] != "") { // ファイルパスの場所が空白じゃなかった場合
					// ただのキー変更のみのダイアログを表示
					DialogFlag = true;
					//KDlg.setFileName(FileName);
					KDlg.setDialog(Table.getSelectedRow(), IndexID, FileName);
					KDlg.setVisible(true);
					if (KDlg.getOCFlag()) {
						h2.update_key(KDlg.getKeyCode(), KDlg.getKeyCnt(),IndexID, KDlg.getKeyName());
						latest();
					}
					DialogFlag = false;
				} else {
					// 文字列用変更ダイアログの表示
					DialogFlag = true;
					//SDlg.setTextField(FileName);
					//ここに変更する行の登録されているキーの情報をダイアログにセットするメソッドを書く（なのでStringDialogにメソッドを作成する）
					SDlg.setDialog( Table.getSelectedRow(), IndexID, FileName );
					SDlg.setInformation(true, IndexID);
					SDlg.setVisible(true);
					if (SDlg.getOCFlag()) {
						h2.update_String(SDlg.getKCode(), SDlg.getKCnt(),IndexID, SDlg.getKName(), SDlg.getTextField());
						latest();
					}
					DialogFlag = false;
				}
			}
		});
		ChangeB.setBounds(9, 126, 139, 37);
		getContentPane().add(ChangeB);

		ImportB = new JButton("");
		ImportB.setContentAreaFilled(false);
		ImportB.setOpaque(false);
		ImportB.setIcon(new ImageIcon(main.path + "\\一覧表\\path765.png"));
		ImportB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MySQL mysql = new MySQL();
				h2.My_inport(mysql.My_import(H2.ID, H2.PASSWARD));
				latest();
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "「" + H2.ID + "」　様のインポートを完了いたしました。");
			}
		});
		ImportB.setBounds(9, 173, 139, 47);
		getContentPane().add(ImportB);

		BackupB = new JButton("");
		BackupB.setContentAreaFilled(false);
		BackupB.setIcon(new ImageIcon(main.path + "\\一覧表\\path76523.png"));
		BackupB.setBackground(UIManager.getColor("Button.background"));
		BackupB.addActionListener(new ActionListener() {
			//バックアップの処理
			public void actionPerformed(ActionEvent arg0) {
//				JOptionPane.showConfirmDialog(null, "バックアップ中...",
//						"バックアップ", JOptionPane.DEFAULT_OPTION,
//						JOptionPane.ERROR_MESSAGE);
//				
//				MySQL mysql = new MySQL(h2.ID, h2.PASSWARD);
//				mysql.My_backup(h2.ID, h2.PASSWARD,h2.Select());
//				JFrame frame = new JFrame();
//
//				JOptionPane.showMessageDialog(frame, "「" + h2.ID
//						+ "」　様のバックアップを完了いたしました。");

				///////////////////////////////////////////////////////
//				final JDialog d = new JDialog();
//				JLabel DispLabel = new JLabel("<html>データベースに登録中です<br>※登録完了には少し時間がかかる場合があります</br></html>");
//				d.add(DispLabel);
//				d.pack();
//				d.setSize(300, 200);
//				d.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
//				d.setModalityType(ModalityType.APPLICATION_MODAL);
//				d.setLocationRelativeTo(null);
//				d.setVisible(true);
//				new Thread(new Runnable() {
//					public void run() {
//						//...時間の掛かる処理をここに記述...
//						System.out.println("Thread処理");
//						StartB.setEnabled(false);
//						RegistB.setEnabled(false);
//						ChangeB.setEnabled(false);
//						ImportB.setEnabled(false);
//						BackupB.setEnabled(false);
//						DialogFlag = true;
//						try {
//							Thread.sleep(5000);
//						} catch (InterruptedException e) {
//							// TODO 自動生成された catch ブロック
//							e.printStackTrace();
//						}
//						MySQL mysql = new MySQL(h2.ID, h2.PASSWARD);
//						mysql.My_backup(h2.ID, h2.PASSWARD,h2.Select());
//						d.setVisible(false);
//						d.setModalityType(ModalityType.APPLICATION_MODAL);
//						DispLabel.setText("データベースへの登録が完了しました");
//						d.setVisible(true);
//						
//						JOptionPane.showMessageDialog(null, "データベースへの登録が完了しました");
//						
//						
//						
//						StartB.setEnabled(true);
//						RegistB.setEnabled(true);
//						ChangeB.setEnabled(true);
//						ImportB.setEnabled(true);
//						BackupB.setEnabled(true);
//						DialogFlag = false;
//						SwingUtilities.invokeLater(new Runnable() {
//							public void run() {
//								//d.setVisible(false);
//								d.dispose();
//							}
//						});
//					}
//				}).start();

				
				///////////////////////////////////////////////////////

			
			//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
			
				final JDialog d = new JDialog((JFrame)null, true);
				final JProgressBar pb = new JProgressBar();
				
				//pb.setValue(0);
				d.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				//d.setSize(300, 200);
				//d.setSize(300, 200);
				//pb.setSize(300, 200);
				
				pb.setIndeterminate(true);
				pb.setStringPainted(true);
				
				d.add(pb);
				d.pack();
				d.setSize(300, 100);
				d.setLocationRelativeTo(null);

				d.addWindowListener(new WindowAdapter() {
					@Override
					public void windowOpened(WindowEvent e) {
						new Thread(new Runnable() {
							public void run() {
//								SwingUtilities.invokeLater(new Runnable() {
//									public void run() {
//										d.setTitle("データベースサーバに接続中");
//										pb.setString("しばらくお待ちください...");
//									}
//								});
								//...時間の掛かる読み込み処理をここに記述...
								
								
								SwingUtilities.invokeLater(new Runnable() {
									public void run() {
										d.setTitle("データベースサーバに接続中");
										pb.setString("しばらくお待ちください...");
									}
  								});
								//...時間の掛かる登録処理をここに記述...
								System.out.println("Thread処理");
								StartB.setEnabled(false);
								RegistB.setEnabled(false);
								ChangeB.setEnabled(false);
								ImportB.setEnabled(false);
								BackupB.setEnabled(false);
								DialogFlag = true;

								MySQL mysql = new MySQL(h2.ID, h2.PASSWARD);
								//System.out.println("半分");
								if( mysql.getSuccessFlag()/*上のコンストラクタの結果うまくいかなかった場合、下の処理は行わず「失敗しました」と表示する*/ ){
									mysql.My_backup(h2.ID, h2.PASSWARD, h2.Select());
								}
								//System.out.println("終わり");
								//完璧に処理が完了したところの場所へ書く↓
								//JOptionPane.showMessageDialog(null, "データベースへの登録が完了しました");
								
								
								
								StartB.setEnabled(true);
								RegistB.setEnabled(true);
								ChangeB.setEnabled(true);
								ImportB.setEnabled(true);
								BackupB.setEnabled(true);
								DialogFlag = false;

								SwingUtilities.invokeLater(new Runnable() {
									public void run() {
										d.setVisible(false);
										if( mysql.getSuccessFlag() ){
											JOptionPane.showMessageDialog(null, "データベースへの登録が完了しました");
										} else {
											JOptionPane.showMessageDialog(null, "データベースへの登録に失敗しました");
										}
										d.dispose();
									}
								});
							}
						}).start();
					}
				});
				
				d.setVisible(true);

			
			
			
			
			
			
			//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
			
			
			
			
			}
		});
		BackupB.setBounds(9, 230, 142, 37);
		getContentPane().add(BackupB);
		KeyCheckBox = new JCheckBox("\u30B7\u30E7\u30FC\u30C8\u30AB\u30C3\u30C8\u30AD\u30FC\u3092\u4F7F\u7528\u3057\u306A\u3044");
		KeyCheckBox.setForeground(Color.YELLOW);
		KeyCheckBox.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		KeyCheckBox.setOpaque(false);
		KeyCheckBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				main.KeyCheckBox.setSelected(KeyCheckBox.isSelected());
			}
		});
		KeyCheckBox.setBounds(0, 420, 215, 21);
		getContentPane().add(KeyCheckBox);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Java\\kadai\\workspace\\\u30B0\u30EB\u30FC\u30D7\\\u4E00\u89A7\u8868\\\u4E00\u89A7\u8868\u80CC\u666F.png"));
		lblNewLabel.setBounds(-77, -123, 1098, 789);
		getContentPane().add(lblNewLabel);
		list.StartB.setIcon(new ImageIcon(main.path + "\\一覧表２\\起動.png"));
		list.RegistB.setIcon(new ImageIcon(main.path + "\\一覧表２\\文字列登録.png"));
		list.ChangeB.setIcon(new ImageIcon(main.path + "\\一覧表２\\変更.png"));
		list.ImportB.setIcon(new ImageIcon(main.path + "\\一覧表２\\インポート.png"));
		list.BackupB.setIcon(new ImageIcon(main.path + "\\一覧表２\\バックアップ.png"));
		// list.CloseB.setIcon(new ImageIcon(main.path + "\\一覧表２\\閉じる３.png"));
		list.lblNewLabel.setIcon(new ImageIcon(main.path + "\\一覧表２\\一覧表背景.png"));
	}

	private void setAutoResizeMode(int autoResizeOff) {}

	/**
	 * dsadas
	 */
	public void latest() {
		tableModel = new MyTableModel(h2.Listdisplay(),columnNames);
		// 一度起動されているか
		if (main.cnt) {
		} else {
			Table2.setModel(tableModel); // テーブルに画像情報をセット
			Table2.removeColumn(Table2.getColumn("ファイルパス")); // ファイルパス項目を非表示
			Table2.removeColumn(Table2.getColumn("ファイルID")); // ファイルパス項目を非表示
			Bframe.setlatest(h2.Listdisplay()); // 情報を更新
		}	

		Table = new JTable(tableModel); // テーブルを生成
		tabledata = h2.Listdisplay(); // 最新の情報を　テーブルに
		Table.removeColumn(Table.getColumn("ファイルパス")); // ファイルパス項目を非表示
		Table.removeColumn(Table.getColumn("ファイルID")); // ファイルパス項目を非表示
		Table.setRowHeight(50); // セルの高さを 50 にセット
		
		scroll = new JScrollPane(Table);
		scroll.setBounds(179, 3, 536, 438);
		getContentPane().add(scroll);

		Table.setRowSelectionAllowed(true);
		Table.getTableHeader().setResizingAllowed(false);
		Table.setDefaultEditor(Object.class, null);
		Table.setAutoCreateRowSorter(false);
		if(main.cnt){
			Table2=Table;
			main.cnt = false;
		}
		new DropTarget(Table, DnDConstants.ACTION_COPY_OR_MOVE, this);
	}

	@Override
	public void dragEnter(DropTargetDragEvent arg0) {}

	@Override
	public void dragExit(DropTargetEvent arg0) {}

	@Override
	public void dragOver(DropTargetDragEvent arg0) {}

	@Override
	public void drop(DropTargetDropEvent dtde) {
		dtde.acceptDrop(DnDConstants.ACTION_MOVE);
	//	latest();
		try {
			// ドラッグされたファイル情報を取得
			Transferable tran = dtde.getTransferable();
			// ファイルリストにドラッグされたファイルを格納
			List<?> fileList = (List<?>) tran.getTransferData(DataFlavor.javaFileListFlavor);
			Iterator<?> iterator = fileList.iterator();
			while (iterator.hasNext()) {
				// ドラッグされたファイルを一つだけ取得
				File file = (File) iterator.next();
				// ファイル情報変数　初期化
				WindowsShortcut ws = null;
				String filePath = null;
				String fileName = null;
				try {
					// ファイル名を格納
					String kaku = file.getName();
					// ファイル拡張子を取得
					kaku = getSuffix(kaku);
					// 拡張子が 「lnk」 の場合
					if (kaku.equals("lnk")) {
						ws = new WindowsShortcut(file); // ファイルのリンク先のファイルを取得
						filePath = ws.getRealFilename(); // リンク先のファイルパス文字列
						int index = filePath.lastIndexOf("\\"); // ファイルパスからファイル名を取得する際のindex
						fileName = filePath.substring(index + 1);// ファイル名を取得
					} else {
						filePath = file.getAbsolutePath(); // ファイルパスを取得
						fileName = file.getName(); // ファイル名を取得
					}
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				// キー設定ダイアログの設定と表示
				DialogFlag = true;
				KDlg.setFileName(fileName);
				KDlg.setVisible(true);
				DialogFlag = false;
				// DBへデータ挿入
				ImageIcon icon = (ImageIcon) chooser.getIcon(file);
				String ext = main.path + "\\bin\\" + fileName + ".png";
				// 画像ファイル保存
				ImageIO.write((BufferedImage) icon.getImage(), "png", new File(ext));
				// ファイル情報をH2DBに挿入
				if (KDlg.getOCFlag()) {
					h2.insert(fileName, filePath, ext, KDlg.getKeyCode(),KDlg.getKeyCnt(), KDlg.getKeyName());
				}
			}
			dtde.dropComplete(true); // ドロップ処理が完了
			latest(); // 最新の情報に更新
		} catch (Exception e) {
			e.printStackTrace();
			dtde.dropComplete(false);
		}
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent arg0) {}

	/**
	 * 指定された String の拡張子を返す。
	 *
	 * @param value
	 *            チェックする ファイルのパス
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
	public void getlatest() {
		index = Table.getSelectedRows();
		int j = index.length;
		while (j != 0) {
			tabledata[index[j - 1]] = new Object[] { "", "", "", "", "" }; // ファイル情報をクリア
			for (int i = j; !(tabledata[i + 1].equals(null)) && i < 20; i++) { // 行を前に詰める処理
				tabledata[i] = tabledata[i + 1]; // クリアされた行に挿入
				tabledata[i + 1] = new Object[] { "", "", "", "", "" }; // 挿入した行をクリア
				Table.clearSelection(); // 選択状態をクリア
			}
			j--; // 選択された個数 -1をする
		}
	}

	// 現在チェックが入っているかどうかを取得する
	public static boolean getKeyCheckBox() {
		return KeyCheckBox.isSelected();
	}

	// チェックが入っているならはずし、入っていないなら入れる
	public static void setKeyCheckBox() {
		KeyCheckBox.setSelected(!getKeyCheckBox());
	}

	public static boolean getDialogFlag() {
		return DialogFlag;
	}

	public Object[][] getTableData() {
		return tabledata;
	}

}