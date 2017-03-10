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

	// �f�X�N�g�b�v�̃f�B���N�g���p�X�@�i�[�z��
	public String[][] tabledata = new String[20][2];

	// ���� �z��
	public String[] columnNames = { "�f�X�N�g�b�v", "�p�X" };
	public static String USERHOME = System.getProperty("user.home");
	private JLabel use;

	makefolder Mfolder;

	private String DName = null; // ���ݎg�p���̃f�X�N�g�b�v�̖��O��ۑ�
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

		System.out.println("�f�o�b�N" + USERHOME);

		// SCK�t�H���_�쐬
		String SCK_f = USERHOME + "\\SCK";
		SCK_D = new File(SCK_f);
		SCK_D.mkdir();

		// �v���C�x�[�g�p�t�H���_����
		PrivatePath = USERHOME + "\\SCK\\Private";
		Private_D = new File(PrivatePath);
		Private_D.mkdir();

		// �d���p�t�H���_����
		String WorkPath = USERHOME + "\\SCK\\Work";
		Work_D = new File(WorkPath);
		Work_D.mkdir();

		// MakeFolder�p�t�H���_����
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

		// �ŐV�̏����������
		saishin();

		// ���p
		PrivateB = new JButton("");
		PrivateB.setContentAreaFilled(false);
		PrivateB.setIcon(new ImageIcon(main.path
				+ "\\�f�X�N�g�b�v�؂�ւ����\\rect4737-7.png"));
		PrivateB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {

					int btn = arg0.getButton();

					if (btn == MouseEvent.BUTTON1) {

						WorkB.setEnabled(false);
						CallB.setEnabled(false);

						Mfolder = new makefolder(PrivatePath);
						setDesktopName("���p");

						Thread.sleep(2000);

						JOptionPane.showMessageDialog(null, "�ړ������������܂����B");

						WorkB.setEnabled(true);
						CallB.setEnabled(true);

					} else if (btn == MouseEvent.BUTTON3) {
						System.out.println(PrivatePath);
						Runtime rt = Runtime.getRuntime();
						String OFile = "explorer " + PrivatePath;
						rt.exec(OFile);
					}

				} catch (IOException | InterruptedException e) {
					// TODO �����������ꂽ catch �u���b�N
					e.printStackTrace();

				}

			}
		});

		PrivateB.setFont(new Font("MS UI Gothic", Font.PLAIN, 23));
		PrivateB.setBounds(12, 50, 175, 67);
		contentPane.add(PrivateB);

		// �d���p
		WorkB = new JButton("");
		WorkB.setIcon(new ImageIcon(main.path + "\\�f�X�N�g�b�v�؂�ւ����\\rect4558.png"));
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
						setDesktopName("�d���p");

						Thread.sleep(2000);

						JOptionPane.showMessageDialog(null, "�ړ������������܂����B");

						PrivateB.setEnabled(true);
						CallB.setEnabled(true);

					} else if (btn == MouseEvent.BUTTON3) {
						System.out.println(WorkPath);
						Runtime rt = Runtime.getRuntime();
						String OFile = "explorer " + WorkPath;
						rt.exec(OFile);
					}

				} catch (IOException | InterruptedException e) {
					// TODO �����������ꂽ catch �u���b�N
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

		// �Ăяo��
		CallB = new JButton("");
		CallB.setContentAreaFilled(false);
		CallB.setIcon(new ImageIcon(main.path + "\\�f�X�N�g�b�v�؂�ւ����\\rect4564.png"));
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
						JOptionPane.showMessageDialog(null, "�ړ������������܂����B");
						PrivateB.setEnabled(true);
						WorkB.setEnabled(true);

					}
				} catch (InterruptedException e) {
					// TODO �����������ꂽ catch �u���b�N
					e.printStackTrace();
				}
			}
		});

		CallB.setFont(new Font("MS UI Gothic", Font.PLAIN, 23));
		CallB.setBounds(12, 248, 175, 67);
		contentPane.add(CallB);

		// �쐬
		MakeB = new JButton("");
		MakeB.setContentAreaFilled(false);
		MakeB.setIcon(new ImageIcon(main.path + "\\�f�X�N�g�b�v�؂�ւ����\\rect4534.png"));
		MakeB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// �v���W�F�N�g�t�H���_�̃p�X����
				String mpath = EXPATH + "\\" + TextBox.getText();
				System.out.print(mpath);
				File NFile = new File(mpath);
				NFile.mkdir();

				// �ŐV���̍X�V
				saishin();
				try {

					BufferedReader br = new BufferedReader(
							new FileReader(NFile));
					String Primal = br.readLine();

					use.setText(""/* �\�����������݂̃f�X�N�g�b�v�� */);

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
				+ "\\�f�X�N�g�b�v�؂�ւ����\\rect4542.png"));
		DeleteB.setContentAreaFilled(false);
		DeleteB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// �m�F��ʂ�\��
				JFrame kakunin = new JFrame();

				// ��������̂����f����ϐ�
				int option = JOptionPane.showConfirmDialog(kakunin,
						"�{���ɑI�����ꂽ���̂��폜���Ă�낵���ł��傤���B", "�m�F���",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				
				if (option == 0) {

					// �N���b�N���ꂽ�Z���̓��e���擾
					String Rpass = table.getValueAt(table.getSelectedRow(), 1)
							.toString();

					// �N���b�N���ꂽ�s�̃f�B���N�g�����폜
					File RrFile = new File(Rpass);
					delete(RrFile);

					// �ŐV�̏��ɍX�V
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

		PrivateB.setIcon(new ImageIcon(main.path + "\\�f�X�N�g�b�v�؂�ւ���ʂQ\\���p.png"));
		WorkB.setIcon(new ImageIcon(main.path + "\\�f�X�N�g�b�v�؂�ւ���ʂQ\\�d���p.png"));
		CallB.setIcon(new ImageIcon(main.path + "\\�f�X�N�g�b�v�؂�ւ���ʂQ\\�Ăяo��.png"));
		MakeB.setIcon(new ImageIcon(main.path + "\\�f�X�N�g�b�v�؂�ւ���ʂQ\\�쐬.png"));
		DeleteB.setIcon(new ImageIcon(main.path + "\\�f�X�N�g�b�v�؂�ւ���ʂQ\\�폜.png"));
		lblNewLabel.setIcon(new ImageIcon(main.path
				+ "\\�f�X�N�g�b�v�؂�ւ���ʂQ\\�f�X�N�g�b�v�w�i.png"));

	}

	public void DeskTop_first() {

		String Path_t = USERHOME + "\\SCK\\Path.txt";
		Path_f = new File(Path_t);

		if (!Path_f.exists()) {
			JFrame sirase = new JFrame();
			int btn = JOptionPane.showConfirmDialog(sirase,
					"���݂̃f�X�N�g�b�v�̏�Ԃ��u���p�v�ɐݒ肵�܂��B", "���m�点", JOptionPane.OK_OPTION,
					JOptionPane.INFORMATION_MESSAGE);

			if (btn != 1) {
				Mfolder = new makefolder(WorkPath);
				use.setText("�d���p");

				try {
					Thread.sleep(2000);
					Mfolder = new makefolder(WorkPath);
				} catch (InterruptedException e) {
					// TODO �����������ꂽ catch �u���b�N
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "�ړ������������܂����B");

			}

		} else {

			File txt = new File(System.getProperty("user.home")
					+ "\\SCK\\path.txt");
			// �v���C�x�[�g���
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(txt));
			} catch (FileNotFoundException e) {
				// TODO �����������ꂽ catch �u���b�N
				e.printStackTrace();
			}
			try {
				Primal = br.readLine();
			} catch (IOException e) {
				// TODO �����������ꂽ catch �u���b�N
				e.printStackTrace();
			}
			try {
				br.close();
			} catch (IOException e) {
				// TODO �����������ꂽ catch �u���b�N
				e.printStackTrace();
			}

			if (Primal.equals(PrivatePath)) {
				use.setText("���p");
			} else if (Primal.equals(WorkPath)) {
				use.setText("�d���p");
			} else {
				// use.setText("�d���p");
			}

		}
	}

	// �e�[�u���̍ŐV�����X�V
	public void saishin() {
		// tabledata�z���������
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < tabledata.length; j++) {
				tabledata[j][i] = "";
			}
		}

		// MakeFolder���̃f�B���N�g���T��
		File dir1 = new File(EXPATH);
		File[] exfiles = dir1.listFiles();
		// tabledata�z��ɁAMakeFolder���̃f�B���N�g��������
		for (int i = 0; i < exfiles.length; i++) {
			tabledata[i][1] = exfiles[i].getPath();
			tabledata[i][0] = exfiles[i].getName();
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(208, 50, 604, 265);
		contentPane.add(scrollPane);
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane.setRowHeaderView(scrollPane_1);

		// �ŐV�̏��� JTable�ɑ��
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

					table = (JTable) arg0.getSource();// �I�����ꂽ����table�ɑ��
					int[] index = table.getSelectedRows();

					FilePass = (String) table.getValueAt(
							table.getSelectedRow(), 1); // �t�@�C�����擾
					System.out.println(FilePass);

				} else if (btn == MouseEvent.BUTTON3) {

					// �I�����ꂽ�Z���́Anull��
					if (!(isEmpty(FilePass))) {

						// �Ώۂ̃t�@�C�����N���ϐ�
						Runtime rt = Runtime.getRuntime();
						EXE = new String("explorer " + FilePass); // �N������EXE�ɑ��

						try {
							rt.exec(EXE); // �w�肳�ꂽ�t�@�C�����R�R�ŋN��
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}

			}
		});
		scrollPane.setViewportView(table);

		// ���x�N���b�N���Ă��A�ҏW��Ԃ�s��
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
	 * �w�肳�ꂽ String �� null �܂��͋󕶎��񂩂ǂ�����Ԃ��܂��B
	 *
	 * @param value
	 *            �`�F�b�N���� String
	 * @return null �܂��͋󕶎��񂩂ǂ����Bnull �܂��͋󕶎���Ȃ� true �A����ȊO�Ȃ� false �B
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
