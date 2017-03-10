import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Button_frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private H2 h2 = new H2();
	JButton[] Jstr = new JButton[20];
	static JButton button;
	static JButton button_1;
	static JButton button_2;
	static JButton button_3;
	static JButton button_4;
	static JButton button_5;
	static JButton button_6;
	static JButton button_7;
	static JButton button_8;
	static JButton button_9;
	static JButton button_10;
	static JButton button_11;
	static JButton button_12;
	static JButton button_13;
	static JButton button_14;
	static JButton button_15;
	static JButton button_16;
	static JButton button_17;
	static JButton button_18;
	static JButton button_19;
	static JButton closeB;
	static JLabel lblNewLabel;
	
	private MouseEvent start;// WindowDrugMoveクラス内で使うがここに設置しないと動かない

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Button_frame(Object[][] objects) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 503, 148);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.addMouseListener(new WindowDrugMove()); // マウスを最初に掴んだ時
		contentPane.addMouseMotionListener(new WindowDrugMove()); // マウスをドラッグした時
		contentPane.setLayout(null);
		
		// 画面の縁を消す
				setUndecorated(true);

		button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (H2.flg == true) {
					h2.Fileboot(0);
				}

			}
		});

		Jstr[0] = button;
		button.setMargin(new Insets(2, 2, 2, 2));
		button.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		button.setAlignmentY(1.0f);
		button.setBounds(12, 10, 33, 45);
		contentPane.add(button);

		button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (H2.flg == true) {
					h2.Fileboot(1);
				}
			}
		});
		Jstr[1] = button_1;
		button_1.setMargin(new Insets(2, 2, 2, 2));
		button_1.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		button_1.setAlignmentY(1.0f);
		button_1.setBounds(57, 10, 33, 45);
		contentPane.add(button_1);

		button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (H2.flg == true) {
					h2.Fileboot(2);
				}

			}
		});
		Jstr[2] = button_2;
		button_2.setMargin(new Insets(2, 2, 2, 2));
		button_2.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		button_2.setAlignmentY(1.0f);
		button_2.setBounds(106, 10, 33, 45);
		contentPane.add(button_2);

		button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (H2.flg == true)
					h2.Fileboot(3);
			}
		});
		Jstr[3] = button_3;
		button_3.setMargin(new Insets(2, 2, 2, 2));
		button_3.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		button_3.setAlignmentY(1.0f);
		button_3.setBounds(158, 10, 33, 45);
		contentPane.add(button_3);

		button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (H2.flg == true)
					h2.Fileboot(4);
			}
		});
		Jstr[4] = button_4;
		button_4.setMargin(new Insets(2, 2, 2, 2));
		button_4.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		button_4.setAlignmentY(1.0f);
		button_4.setBounds(213, 10, 33, 45);
		contentPane.add(button_4);

		button_5 = new JButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (h2.flg == true)
					h2.Fileboot(5);
			}
		});
		Jstr[5] = button_5;
		button_5.setMargin(new Insets(2, 2, 2, 2));
		button_5.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		button_5.setAlignmentY(1.0f);
		button_5.setBounds(258, 10, 33, 45);
		contentPane.add(button_5);

		button_6 = new JButton("");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (h2.flg == true)
					h2.Fileboot(6);
			}
		});
		Jstr[6] = button_6;
		button_6.setMargin(new Insets(2, 2, 2, 2));
		button_6.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		button_6.setAlignmentY(1.0f);
		button_6.setBounds(303, 10, 33, 45);
		contentPane.add(button_6);

		button_7 = new JButton("");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (h2.flg == true)
					h2.Fileboot(7);
			}
		});
		Jstr[7] = button_7;
		button_7.setMargin(new Insets(2, 2, 2, 2));
		button_7.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		button_7.setAlignmentY(1.0f);
		button_7.setBounds(362, 10, 33, 45);
		contentPane.add(button_7);

		button_8 = new JButton("");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (h2.flg == true)
					h2.Fileboot(8);
			}
		});
		Jstr[8] = button_8;
		button_8.setMargin(new Insets(2, 2, 2, 2));
		button_8.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		button_8.setAlignmentY(1.0f);
		button_8.setBounds(407, 10, 33, 45);
		contentPane.add(button_8);

		button_9 = new JButton("");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (h2.flg == true)
					h2.Fileboot(9);
			}
		});
		Jstr[9] = button_9;
		button_9.setMargin(new Insets(2, 2, 2, 2));
		button_9.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		button_9.setAlignmentY(1.0f);
		button_9.setBounds(459, 10, 33, 45);
		contentPane.add(button_9);

		button_10 = new JButton("");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (h2.flg == true)
					h2.Fileboot(10);
			}
		});
		Jstr[10] = button_10;
		button_10.setMargin(new Insets(2, 2, 2, 2));
		button_10.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		button_10.setAlignmentY(1.0f);
		button_10.setBounds(12, 72, 33, 45);
		contentPane.add(button_10);

		button_11 = new JButton("");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (H2.flg == true)
					h2.Fileboot(11);
			}
		});
		Jstr[11] = button_11;
		button_11.setMargin(new Insets(2, 2, 2, 2));
		button_11.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		button_11.setAlignmentY(1.0f);
		button_11.setBounds(55, 72, 33, 45);
		contentPane.add(button_11);

		button_12 = new JButton("");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (h2.flg == true)
					h2.Fileboot(12);
			}
		});
		Jstr[12] = button_12;
		button_12.setMargin(new Insets(2, 2, 2, 2));
		button_12.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		button_12.setAlignmentY(1.0f);
		button_12.setBounds(106, 72, 33, 45);
		contentPane.add(button_12);

		button_13 = new JButton("");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (h2.flg == true)
					h2.Fileboot(13);
			}
		});
		Jstr[13] = button_13;
		button_13.setMargin(new Insets(2, 2, 2, 2));
		button_13.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		button_13.setAlignmentY(1.0f);
		button_13.setBounds(158, 72, 31, 45);
		contentPane.add(button_13);

		button_14 = new JButton("");
		button_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (h2.flg == true)
					h2.Fileboot(14);
			}
		});
		Jstr[14] = button_14;
		button_14.setMargin(new Insets(2, 2, 2, 2));
		button_14.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		button_14.setAlignmentY(1.0f);
		button_14.setBounds(213, 72, 33, 45);
		contentPane.add(button_14);

		button_15 = new JButton("");
		button_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (h2.flg == true)
					h2.Fileboot(15);
			}
		});
		Jstr[15] = button_15;
		button_15.setMargin(new Insets(2, 2, 2, 2));
		button_15.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		button_15.setAlignmentY(1.0f);
		button_15.setBounds(258, 72, 33, 45);
		contentPane.add(button_15);

		button_16 = new JButton("");
		button_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (h2.flg == true)
					h2.Fileboot(16);
			}
		});
		Jstr[16] = button_16;
		button_16.setMargin(new Insets(2, 2, 2, 2));
		button_16.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		button_16.setAlignmentY(1.0f);
		button_16.setBounds(313, 72, 33, 45);
		contentPane.add(button_16);

		button_17 = new JButton("");
		button_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (h2.flg == true)
					h2.Fileboot(17);
			}
		});
		Jstr[17] = button_17;
		button_17.setMargin(new Insets(2, 2, 2, 2));
		button_17.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		button_17.setAlignmentY(1.0f);
		button_17.setBounds(362, 72, 33, 45);
		contentPane.add(button_17);

		button_18 = new JButton("");
		button_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (h2.flg == true)
					h2.Fileboot(18);
			}
		});
		Jstr[18] = button_18;
		button_18.setMargin(new Insets(2, 2, 2, 2));
		button_18.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		button_18.setAlignmentY(1.0f);
		button_18.setBounds(407, 72, 33, 45);
		contentPane.add(button_18);

		button_19 = new JButton("");
		button_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (h2.flg == true)
					h2.Fileboot(19);
			}
		});
		Jstr[19] = button_19;
		button_19.setMargin(new Insets(2, 2, 2, 2));
		button_19.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		button_19.setAlignmentY(1.0f);
		button_19.setBounds(462, 72, 30, 45);
		contentPane.add(button_19);

		closeB = new JButton("");
		closeB.setContentAreaFilled(false);
		closeB.setIcon(new ImageIcon("C:\\Java\\kadai\\workspace\\\u30B0\u30EB\u30FC\u30D7\\\u30DC\u30BF\u30F3\u753B\u9762\\\u9589\u3058\u308B.png"));
		closeB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		closeB.setBounds(407, 143, 66, 25);
		contentPane.add(closeB);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Java\\kadai\\workspace\\Group6-13\\\u30DC\u30BF\u30F3\u753B\u9762\uFF12\\\u30DC\u30BF\u30F3\u80CC\u666F.png"));
		lblNewLabel.setBounds(-19, -77, 668, 291);
		contentPane.add(lblNewLabel);

		Button_frame.closeB.setIcon(new ImageIcon(main.path + "\\ボタン画面２\\閉じる.png"));
		Button_frame.lblNewLabel.setIcon(new ImageIcon(main.path + "\\メイン画面２\\メイン背景.png"));
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new MyWindowListener(this)); // ウィンドウの情報を取得
	}

	public void setlatest(Object[][] objects) {

		for (int i = 0; i < 20; i++) {

			// 登録情報がある場合
			if (!isEmpty(objects[i][1])) {

				Object tmp = objects[i][0];
				String Stmp = (String) objects[i][3];
				System.out.println(tmp);

				if (Stmp.equals("")) { // 文字列の場合
					Jstr[i].setIcon(null);
					Jstr[i].setText("文");
				} else { // ファイルの場合
					Jstr[i].setIcon((ImageIcon) tmp);
				}

				//Jstr[i].setContentAreaFilled(false);
				Jstr[i].setVisible(true);

			} else { // 登録情報がない
				Jstr[i].setText("");
				// Jstr[i].setEnabled(false);
				Jstr[i].setVisible(false);
			}

		}
	}

	/**
	 * 指定された String が null または空文字列かどうかを返します。
	 *
	 * @param objects
	 *            チェックする String
	 * @return null または空文字列かどうか。null または空文字列なら true 、それ以外なら false 。
	 */
	public static boolean isEmpty(Object objects) {
		String tmp = (String) objects;
		if (tmp == null || tmp.length() == 0)
			return true;
		else
			return false;
	}

	/* タスクトレイ判断用 */
	class MyWindowListener extends WindowAdapter {

		private Button_frame main = null;

		public MyWindowListener(Button_frame initializer) {
			main = initializer;
		}

		//
		public void windowIconified(WindowEvent e) {
			// main.setVisible(false);
		}

		// アプリが閉じられた時
		public void windowClosing(WindowEvent e) {
			setVisible(false);
			// main.setIconImage(null);
			// System.exit(0);
		}
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
			Window window = Button_frame.this;
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
