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

	// ID�E�p�X���[�h���͕ϐ�
	public JPasswordField passwatext = new JPasswordField(25);
	public JTextField textField;

	// ID�E�p�X���[�h�i�[�z��
	public static String Userstr[] = new String[2];

	// �A�J�E���g���t�@�C���@�쐬�m�F�@�t���O
	public boolean flg = true;

	// ID���m�@�t���O
	private boolean IDflg = false;

	// �p�X���[�h���m�@�t���O
	private boolean Passflg = false;

	// ���O�C���\�m�F�@�t���O
	public boolean Okflg = false;

	// ���߂đΏۂ�ID�̃��O�C���m�F�@�t���O
	static boolean fast = false;

	// ���O�C�����͉�
	private int count = 1;

	// �A�J�E���g���̃t�@�C�����e�@�i�[�z��
	private String str[] = new String[51];

	// �A�J�E���g���t�@�C���p�X�@�i�[�ϐ�
	public static File newfile;

	// ������C���f�b�N�X�ԍ��@�i�[�ϐ�
	private int index = 0;

	// ���O�C��������
	public static int logincnt = 0;

	// �A�J�E���g�o�^��
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

	private MouseEvent start;// WindowDrugMove�N���X���Ŏg���������ɐݒu���Ȃ��Ɠ����Ȃ�
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
		contentPanel.addMouseListener(new WindowDrugMove()); // �}�E�X���ŏ��ɒ͂񂾎�
		contentPanel.addMouseMotionListener(new WindowDrugMove()); // �}�E�X���h���b�O������

		addWindowListener(new WindowAdapter() {
			@Override
			// �~�{�^���������ꂽ�Ƃ�
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}

		});

		setTitle("\u30E6\u30FC\u30B6\u8A8D\u8A3C");
		setBounds(100, 100, 351, 406);

//		contentPanel.setBorder(new LineBorder(Color.CYAN, 1, false));
//		contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		// ��ʂ̉�������
		setUndecorated(true);

//		setOpacity(0.5f);
		logincnt++;

		

		confirmB = new JButton("");
		//������
		confirmB.setContentAreaFilled(false);
		
		
		confirmB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				passwatext.setEchoChar((char) 0);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				passwatext.setEchoChar('��');
			}
		});
		confirmB.setBounds(291, 207, 41, 27);
		confirmB.setIcon(new ImageIcon(main.path + "\\���O�C�����\\path7651.png"));
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
		passwatext.setText("�p�X���[�h");
		confirmB.setVisible(false);

		passwatext.addFocusListener(new FocusAdapter() {

			// �@passwatext �ɃL�����b�g������ꍇ
			@Override
			public void focusGained(FocusEvent arg0) {
				// �p�X���[�h���͕������󗓂łȂ��ꍇ
				if ("�p�X���[�h".equals(passwatext.getText())
						&& INACTIVE.equals(passwatext.getForeground())) {
					confirmB.setVisible(true);
					passwatext.setText("");
					Color ok = Color.BLACK;
					passwatext.setForeground(ok);
					passwatext.setEchoChar('��');
					passwatext
							.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
				}
			}

			// �@passwatext �ɃL�����b�g���Ȃ��ꍇ
			@Override
			public void focusLost(FocusEvent arg0) {

				// passwatext ���󗓂ł���ꍇ
				if ("".equals(passwatext.getText().trim())) {
					confirm.setVisible(false);
					passwatext
							.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
					passwatext.setForeground(INACTIVE);
					passwatext.setEchoChar((char) 0);
					passwatext.setText("�p�X���[�h");

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

			// �L�����b�g���I�����ꂽ�Ƃ�
			@Override
			public void focusGained(FocusEvent arg0) {

				// ���͂��ꂽ��
				if ("ID".equals(textField.getText())
						&& INACTIVE.equals(textField.getForeground())) {
					textField.setText("");
					Color ok = Color.BLACK;
					textField.setForeground(ok);
					textField.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
				}
			}

			@Override
			// �L�����b�g���I������Ȃ���
			public void focusLost(FocusEvent arg0) {

				// textfield���󗓂ł���ꍇ
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
		okB.setIcon(new ImageIcon(main.path + "\\���O�C�����\\rect4247.png"));
		okB.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				// �eOk�t���O��������
				IDflg = false;
				Passflg = false;
				Okflg = false;

				// �A�J�E���g���t�@�C���쐬
				filecreate();

				// �A�J�E���g�����@�z��Ɋi�[
				Userstr[0] = textField.getText();// ���[�UID
				Userstr[1] = passwatext.getText();// �p�X���[�h

				try {

					// �����󔒂łȂ���
					if (!(Userstr[0].equals("ID"))
							&& !(Userstr[1].equals("�p�X���[�h"))
							&& !(Userstr[1].equals(""))
							&& (!(Userstr[0].equals("")))) {

						if (flg == false) { // ���ɍ쐬�ς݂̏ꍇ

							str = ReadAccount();

							@SuppressWarnings("resource")
							BufferedReader br = new BufferedReader(
									new FileReader(newfile));
							int i = 0;
							String tmp;

							// ���[�U�t�@�C���擾
							while (!(tmp = br.readLine()).equals("&")) {
								str[i] = tmp;
								i++;
							}

							// ID �E�@�p�X���[�h�@�n�b�V���ϊ�
							String UserID = encryptStr(Userstr[0]);
							String Userpasswd = encryptStr(Userstr[1]);

							// ID�T��
							int Index = IDsearch(UserID);

							// ID�������ꂽ��
							if (IDflg == true) {
								PASSWARDsearch(Userpasswd, Index); // ID�����ρ@�p�X���[�h�T��

								// �p�X���[�h�������ꂽ��
								if (Passflg == true) {
									Okflg = true;

								} else { // �p�X���[�h�����ł��Ȃ������ꍇ

									// �A�J�E���g���ƈ�v���Ȃ����� ���m�点
									NotUser();

									// �A�J�E���g���͉�ʂ�OK�N���b�N���ꂽ�ꍇ
									if (notu.getflg() == 1) {

										// 3�� �A�J�E���g�����͂��ꂽ�ꍇ
										if (count == 3) {
											System.exit(0); // �v���O�������I��

										} else { // 1,2��A�J�E���g��񂪐������Ȃ���
											// ID�@�E�@�p�X���[�h���͕����@�N���A
											reset();
											textField.requestFocus();
											count++;
										}
									}
								}

							} else { // ��x�A�J�E���g��񂪓o�^����Ă���ꍇ

								// �V�����A�J�E���g����͂��ꂽ�ꍇ
								nUserID();

								// �V�K�쐬��ʂ� OK�{�^�����N���b�N���ꂽ�ꍇ
								if (use.getflg() == 1) {
									// �A�J�E���g���ǉ�
									fast = false;
									Acwriter();

								} else {
									System.out.println("2");
									reset();
									textField.requestFocus();
									setVisible(true);
								}
							}

						} else { // ���߂ċN�����ꂽ�ꍇ

							// �V�����A�J�E���g����͂��ꂽ�ꍇ
							nUserID();

							// �V�K�쐬��ʂ� OK�{�^�����N���b�N���ꂽ�ꍇ
							if (use.getflg() == 1) {
								// ����t�@�C���쐬
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

				// �V�K�쐬�@���́@�������A�J�E���g����͂��ꂽ�ꍇ
				if (Okflg == true) {
					reset();
					setVisible(false); // ���[�U�F�؉�ʁ@��\��
					list = new list(); // �ꗗ�\ ��ʐ���
					main.label.setText( Userstr[0]); // ���C����ʁ@ID�\��
					main.frame.setVisible(true); // ���C����ʁ@�\��

					if (!KeyFlag) { // KeyFlag�@���@false�ł����
						Key key = new Key();
						KeyFlag = true;
					}

				}

			}
		});
		getRootPane().setDefaultButton(okB);

		CloseB = new JButton("");
		CloseB.setContentAreaFilled(false);
		CloseB.setIcon(new ImageIcon(main.path + "\\���O�C�����\\rect4237.png"));
		CloseB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		CloseB.setBounds(186, 274, 121, 58);
		contentPanel.add(CloseB);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(main.path + "\\���O�C�����\\rect440.png"));
		lblNewLabel_1.setBounds(28, 18, 335, 95);
		contentPanel.add(lblNewLabel_1);

//		JLabel lblNewLabel_1 = new JLabel("New label");
//		lblNewLabel_1.setIcon(new ImageIcon(main.path + "���O�C�����\\rect4247.png"));
//		lblNewLabel_1.setBounds(25, 35, 436, 386);
//		contentPanel.add(lblNewLabel_1);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, -200, 894, 800);
		contentPanel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("C:\\Java\\kadai\\workspace\\\u30B0\u30EB\u30FC\u30D7\\\u30ED\u30B0\u30A4\u30F3\u753B\u9762\\\u30ED\u30B0\u30A4\u30F3\u80CC\u666F.png"));
		
		user.confirmB.setIcon(new ImageIcon(main.path + "\\���O�C����ʂQ\\�m�F.png"));
		user.okB.setIcon(new ImageIcon(main.path + "\\���O�C����ʂQ\\���O�C��.png"));
		user.CloseB.setIcon(new ImageIcon(main.path + "\\���O�C����ʂQ\\����Q.png"));
		user.lblNewLabel.setIcon(new ImageIcon(main.path + "\\���O�C����ʂQ\\���O�C���w�i.png"));
		user.lblNewLabel_1.setIcon(new ImageIcon(main.path + "\\���O�C����ʂQ\\�A�C�R���T�u.png"));
		
	}

	// �A�J�E���g���t�@�C�����쐬�@���\�b�h
	public void filecreate() {

		// �A�J�E���g���̃t�@�C���ǂݍ���
		newfile = new File(main.path + "\\bin\\" + "account.txt");

		try {

			// �t�@�C�����쐬����Ă��Ȃ���
			if (newfile.createNewFile()) {
				System.out.println("�t�@�C���쐬����");
				flg = true;

			} else {
				System.out.println("�t�@�C���쐬�ς�");
				flg = false;
			}

		} catch (IOException e) {
			System.out.println("��O���������܂����B");
			System.out.println(e);
		}

	}

	@SuppressWarnings("resource")
	public String[] ReadAccount() throws FileNotFoundException {

		BufferedReader br = new BufferedReader(new FileReader(newfile));
		int i = 0;
		String tmp;

		// ���[�U�t�@�C���擾
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

	// ID��T�����郁�\�b�h
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

	// �p�X���[�h��T�����郁�\�b�h
	public void PASSWARDsearch(String Userpasswd, int i) {

		index = str[i].indexOf(":");
		if (str[i].substring(index + 1).equals(Userpasswd)) {
			Passflg = true;
		}
	}

	// ��������͂�m�点��@���\�b�h
	public void NotUser() {
		this.setAlwaysOnTop(false);
		notu = new NotUser();
		notu.setAlwaysOnTop(true);
		notu.setLocationRelativeTo(null);
		notu.setVisible(true);

	}

	// �V�K�A�J�E���g����邩�m�点��@���\�b�h
	public void nUserID() {
		this.setAlwaysOnTop(false);
//		use = new nUserID();
		use.setAlwaysOnTop(true);
		use.setLocationRelativeTo(null);
		use.setVisible(true);

	}

	// �ǉ��A�J�E���g��o�^����@���\�b�h
	public void Acwriter() throws IOException {

		writer = new BufferedWriter(new FileWriter(newfile));
		int j;

		for (j = 0; j < Ac_count; j++) {

			// �A�J�E���g��񂪂��łɓ����Ă���ꍇ
			if (str[j].equals("%") == false)
				;

			// �u%�v �ł���Ƃ��댟�m
			else if (str[j].equals("%") && fast == false) {
				// �@�u%�v ���@�A�J�E���g���Ɠ���ւ�
				str[j] = encryptStr(Userstr[0]) + ":" + encryptStr(Userstr[1]);
				fast = true;
			}
		}

		// �V����ID����������
		str[j] = encryptStr(Userstr[0]) + ":" + encryptStr(Userstr[1]);

		// �z��̓��e���A�J�E���g���̃t�@�C���ɋL��
		for (j = 0; j < Ac_count; j++) {
			writer.write(str[j]);
			writer.newLine();
		}

		// �@�Ō�́@���@���L��
		writer.write("&");
		writer.close();

		// ���O�C���\�t���O�� OK ����
		Okflg = true;

	}

	// ���߂ăA�J�E���g�����L������@���\�b�h
	public void Fastaccount(boolean flg) throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(newfile));

		if (flg == true) {
			// �A�J�E���g�����t�@�C���ɋL��
			writer.write(encryptStr(Userstr[0]) + ":" + encryptStr(Userstr[1]));
			Okflg = true;
		} else {
			writer.write("%");
			Okflg = false;
		}
		// ���߂ă��O�C�����ꂽ���Ƃ�ۑ�
		fast = true;

		for (int i = 0; i < Ac_count; i++) {

			// �A�J�E���g���̌�ɉ��s
			if (i == 0) {
				writer.newLine();
			}

			// ���o�^�������u%�v �L��
			writer.write("%");
			writer.newLine();
		}

		writer.write("&");
		writer.close();

	}

	public static String encryptStr(String text) {
		// �ϐ�������
		MessageDigest md = null;
		StringBuffer buffer = new StringBuffer();

		try {
			// ���b�Z�[�W�_�C�W�F�X�g�C���X�^���X�擾
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// ��O�������A�G���[���b�Z�[�W�o��
			System.out.println("�w�肳�ꂽ�Í����A���S���Y��������܂���");
		}

		// ���b�Z�[�W�_�C�W�F�X�g�X�V
		md.update(text.getBytes());

		// �n�b�V���l���i�[
		byte[] valueArray = md.digest();

		// �n�b�V���l�̔z������[�v
		for (int i = 0; i < valueArray.length; i++) {
			// �l�̕����𔽓]�����A16�i���ɕϊ�
			String tmpStr = Integer.toHexString(valueArray[i] & 0xff);

			if (tmpStr.length() == 1) {
				// �l���ꌅ�������ꍇ�A�擪��0��ǉ����A�o�b�t�@�ɒǉ�
				buffer.append('0').append(tmpStr);
			} else {
				// ���̑��̏ꍇ�A�o�b�t�@�ɒǉ�
				buffer.append(tmpStr);
			}
		}

		// ���������n�b�V���v�Z�l��ԋp
		return buffer.toString();
	}

//	// ������̒������l�݂̂�����
//	static boolean isNum(String number) {
//		try {
//
//			Integer.parseInt(number); // ���l�ɕϊ��ł��邩
//			return true; // �ϊ��ł����̂Ł@true
//		} catch (NumberFormatException e) {
//			return false; // ���l�ȊO�ɂ��������܂܂�Ă���@false
//		}
//	}

	public void reset() {
		// ID���͕����@������
		textField.setText("");
		Color ok = Color.BLACK;
		textField.setForeground(ok);
		textField.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));

		// �p�X���[�h���͕����@������
		passwatext.setText("�p�X���[�h");
		passwatext.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		passwatext.setForeground(INACTIVE);
		passwatext.setEchoChar((char) 0);
		confirmB.setVisible(false);

		// �eOK�t���O������
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
	 * @author �E�B���h�E���h���b�O����ۂɁAx�E�������X�V����N���X
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
		}// �ŏ��ɒ͂񂾃|�C���g���L��

		public void mouseReleased(MouseEvent me) {
		}
	}
}
