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

	// ���s�t�H���_�̃J�����g�f�B���N�g��
	public static String path;
	private JPanel contentPane;
	public static main frame;
	public static nUserID use;
	public static JLabel label;
	public static user User;
	public static Desktop_Change DeskC;
	// �A�J�E���g��� �t�@�C���p�X
	public static list list;
	private static User_management Ument;

	// �e�[�u���X�V��
	public static boolean cnt = true;
	static JCheckBox KeyCheckBox;
	static JButton DeskB;
	private MouseEvent start;//WindowDrugMove�N���X���Ŏg���������ɐݒu���Ȃ��Ɠ����Ȃ�

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

					frame = new main(); // ���C����ʕ\��
					frame.setVisible(false); // ���C����� �\�������Ȃ�
					frame.setLocationRelativeTo(null); // Windows���S�ɕ\��

					User = new user(); // ���[�U�F�� ��ʕ\��
					User.setVisible(true); // ���C����� �\�������Ȃ�
					User.setLocationRelativeTo(null);// Windows���S�ɕ\��
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
			// windows�p�@��ʂɐݒ�
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e2) {
			e2.printStackTrace();
		}
		
				
		setTitle("\u30E1\u30A4\u30F3\u753B\u9762");
		setBounds(100, 100, 548, 484);
		contentPane = new JPanel();
		contentPane.addMouseListener( new WindowDrugMove() ); //�}�E�X���ŏ��ɒ͂񂾎�
		contentPane.addMouseMotionListener( new WindowDrugMove() ); //�}�E�X���h���b�O������
	
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// path �́@�J�����g�f�B���N�g���擾
		path = new File("user.dir").getAbsoluteFile().getParent();
	
		// �e�[�u���X�V�� + 1
		//cnt++;
		
		lblId = new JLabel("ID:__________");
		lblId.setFont(new Font("MS UI Gothic", Font.BOLD, 35));
		lblId.setBounds(0, 0, 200, 50);
		contentPane.add(lblId);

		label = new JLabel("");
		label.setFont(new Font("MS UI Gothic", Font.BOLD | Font.ITALIC, 35));
		label.setBounds(61, 10, 188, 25);
		contentPane.add(label);

		// �ꗗ�\�{�^��
		Table = new JButton("");
		Table.setIcon(new ImageIcon(path + "\\���C�����\\\\rect4669.png"));
		Table.setContentAreaFilled(false);
		Table.addMouseListener(new MouseAdapter() {
			
			//�{�^���̏�ɃJ�[�\�����悹��ꂽ��
			@Override
			public void mouseEntered(MouseEvent arg0) {
				//�c�[���`�b�v���쐬
				Table.createToolTip();
				//�c�[���`�b�v�ŕ\������镶����
				Table.setToolTipText("�ꗗ�\�{�^��");
			

			}
		});
		Table.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �t�@�C���ꗗ�\��\��
				list = User.getList();
				list.setVisible(true);

				// �������g���\��
				//setVisible(false);

			}
		});
		Table.setFont(new Font("MS UI Gothic", Font.PLAIN, 34));
		Table.setBounds(10, 156, 198, 80);
		contentPane.add(Table);
		
		DeskB = new JButton("");
		DeskB.setIcon(new ImageIcon(path + "\\���C�����\\rect47743.png"));
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
		buttonB.setIcon(new ImageIcon(path + "\\���C�����\\rect4845.png"));
		buttonB.setContentAreaFilled(false);
		buttonB.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {

				// �{�^���t���[����ʕ\��
				list.Bframe.setVisible(true);
			}
		});
		
				UserB = new JButton("");
				UserB.setContentAreaFilled(false);
				UserB.setIcon(new ImageIcon(path + "\\���C�����\\rect4587.png"));
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
				TaskB.setIcon(new ImageIcon(path + "\\���C�����\\rect4847.png"));
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
				LogoutB.setIcon(new ImageIcon(path + "\\���C�����\\rect4849.png"));
				LogoutB.setContentAreaFilled(false);
				LogoutB.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						// ��xH2�f�[�^�x�[�X��ؒf
						H2.Disconnect();

						User.textField.requestFocus();
						User.setVisible(true); // ���[�U�F�� ��ʕ\��
						User.setAlwaysOnTop(true);// ��Ԏ�O�ɕ\��
						User.setLocationRelativeTo(null);// Windows�̐^�񒆂ɕ\��

						// �������g�̔�\��
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
				if(cho.getSelectedIndex() == 0){		//���{��̏ꍇ
					
					Desktop_Change.PrivateB.setIcon(new ImageIcon(path + "\\�f�X�N�g�b�v�؂�ւ���ʂQ\\���p.png"));
					Desktop_Change.WorkB.setIcon(new ImageIcon(path + "\\�f�X�N�g�b�v�؂�ւ���ʂQ\\�d���p.png"));
					Desktop_Change.CallB.setIcon(new ImageIcon(path + "\\�f�X�N�g�b�v�؂�ւ���ʂQ\\�Ăяo��.png"));
					Desktop_Change.MakeB.setIcon(new ImageIcon(path + "\\�f�X�N�g�b�v�؂�ւ���ʂQ\\�쐬.png"));
					Desktop_Change.DeleteB.setIcon(new ImageIcon(path + "\\�f�X�N�g�b�v�؂�ւ���ʂQ\\�폜.png"));
					Desktop_Change.lblNewLabel.setIcon(new ImageIcon(path + "\\�f�X�N�g�b�v�؂�ւ���ʂQ\\�f�X�N�g�b�v�w�i.png"));
					
					Button_frame.closeB.setIcon(new ImageIcon(path + "\\�{�^����ʂQ\\����.png"));
					Button_frame.lblNewLabel.setIcon(new ImageIcon(path + "\\���C����ʂQ\\���C���w�i.png"));
					
					list.StartB.setIcon(new ImageIcon(path + "\\�ꗗ�\�Q\\�N��.png"));
					list.RegistB.setIcon(new ImageIcon(path + "\\�ꗗ�\�Q\\������o�^.png"));
					list.ChangeB.setIcon(new ImageIcon(path + "\\�ꗗ�\�Q\\�ύX.png"));
					list.ImportB.setIcon(new ImageIcon(path + "\\�ꗗ�\�Q\\�C���|�[�g.png"));
					list.BackupB.setIcon(new ImageIcon(path + "\\�ꗗ�\�Q\\�o�b�N�A�b�v.png"));
//					list.CloseB.setIcon(new ImageIcon(path + "\\�ꗗ�\�Q\\����R.png"));
					list.lblNewLabel.setIcon(new ImageIcon(path + "\\�ꗗ�\�Q\\�ꗗ�\�w�i.png"));

					nUserID.okButton.setIcon(new ImageIcon(path + "\\ID�쐬�Q\\OK.png"));
					nUserID.cancelButton.setIcon(new ImageIcon(path + "\\ID�쐬�Q\\�L�����Z��.png"));
					nUserID.label.setIcon(new ImageIcon(path + "\\ID�쐬�Q\\ID�쐬�w�i.png"));
					
					main.Table.setIcon(new ImageIcon(path + "\\���C����ʂQ\\�ꗗ�\.png"));
					main.DeskB.setIcon(new ImageIcon(path + "\\���C����ʂQ\\�f�X�N�g�b�v.png"));
					main.UserB.setIcon(new ImageIcon(path + "\\���C����ʂQ\\���[�U.png"));
					main.buttonB.setIcon(new ImageIcon(path + "\\���C����ʂQ\\�{�^��.png"));
					main.TaskB.setIcon(new ImageIcon(path + "\\���C����ʂQ\\�^�X�N.png"));
					main.LogoutB.setIcon(new ImageIcon(path + "\\���C����ʂQ\\���O�A�E�g.png"));
					main.lblNewLabel.setIcon(new ImageIcon(path + "\\���C����ʂQ\\���C���w�i.png"));
					
					
					User_management.User_DeleteB.setIcon(new ImageIcon(path + "\\���[�U�Ǘ���ʂQ\\ID�폜.png"));
					User_management.pass_changeB.setIcon(new ImageIcon(path + "\\���[�U�Ǘ���ʂQ\\�p�X�ύX.png"));
					User_management.LogoutB.setIcon(new ImageIcon(path + "\\���[�U�Ǘ���ʂQ\\���O�A�E�g�Q.png"));
					User_management.lblNewLabel.setIcon(new ImageIcon(path + "\\���[�U�Ǘ���ʂQ\\���[�U�w�i.png"));
					
					user.confirmB.setIcon(new ImageIcon(path + "\\���O�C����ʂQ\\�m�F.png"));
					user.okB.setIcon(new ImageIcon(path + "\\���O�C����ʂQ\\���O�C��.png"));
					user.CloseB.setIcon(new ImageIcon(path + "\\���O�C����ʂQ\\����Q.png"));
					user.lblNewLabel.setIcon(new ImageIcon(path + "\\���O�C����ʂQ\\���O�C���w�i.png"));
					user.lblNewLabel_1.setIcon(new ImageIcon(path + "\\���O�C����ʂQ\\�A�C�R���T�u.png"));
					
				}else{									//�p��̏ꍇ
					
					Desktop_Change.PrivateB.setIcon(new ImageIcon(path + "\\�f�X�N�g�b�v�؂�ւ����\\���p.png"));
					Desktop_Change.WorkB.setIcon(new ImageIcon(path + "\\�f�X�N�g�b�v�؂�ւ����\\�d���p.png"));
					Desktop_Change.CallB.setIcon(new ImageIcon(path + "\\�f�X�N�g�b�v�؂�ւ����\\�Ăяo��.png"));
					Desktop_Change.MakeB.setIcon(new ImageIcon(path + "\\�f�X�N�g�b�v�؂�ւ����\\�쐬.png"));
					Desktop_Change.DeleteB.setIcon(new ImageIcon(path + "\\�f�X�N�g�b�v�؂�ւ����\\�폜.png"));
					Desktop_Change.lblNewLabel.setIcon(new ImageIcon(path + "\\�f�X�N�g�b�v�؂�ւ����\\�f�X�N�g�b�v�w�i.png"));
					
					Button_frame.closeB.setIcon(new ImageIcon(path + "\\�{�^�����\\����.png"));
					Button_frame.lblNewLabel.setIcon(new ImageIcon(path + "\\���C�����\\���C���w�i.png"));
					
					list.StartB.setIcon(new ImageIcon(path + "\\�ꗗ�\\\�N��.png"));
					list.RegistB.setIcon(new ImageIcon(path + "\\�ꗗ�\\\������o�^.png"));
					list.ChangeB.setIcon(new ImageIcon(path + "\\�ꗗ�\\\�ύX.png"));
					list.ImportB.setIcon(new ImageIcon(path + "\\�ꗗ�\\\�C���|�[�g.png"));
					list.BackupB.setIcon(new ImageIcon(path + "\\�ꗗ�\\\�o�b�N�A�b�v.png"));
//					list.CloseB.setIcon(new ImageIcon(path + "\\�ꗗ�\\\����R.png"));
					list.lblNewLabel.setIcon(new ImageIcon(path + "\\�ꗗ�\�Q\\�ꗗ�\�w�i.png"));
					
					System.out.println("�f�o�b�O:path=" + path);
					nUserID.okButton.setIcon(new ImageIcon(path + "\\ID�쐬\\OK.png"));
					nUserID.cancelButton.setIcon(new ImageIcon(path + "\\ID�쐬\\�L�����Z��.png"));
					nUserID.label.setIcon(new ImageIcon(path + "\\ID�쐬\\ID�쐬�w�i.png"));
					
					main.Table.setIcon(new ImageIcon(path + "\\���C�����\\�ꗗ�\.png"));
					main.DeskB.setIcon(new ImageIcon(path + "\\���C�����\\�f�X�N�g�b�v.png"));
					main.UserB.setIcon(new ImageIcon(path + "\\���C�����\\���[�U.png"));
					main.buttonB.setIcon(new ImageIcon(path + "\\���C�����\\�{�^��.png"));
					main.TaskB.setIcon(new ImageIcon(path + "\\���C�����\\�^�X�N.png"));
					main.LogoutB.setIcon(new ImageIcon(path + "\\���C�����\\���O�A�E�g.png"));
					main.lblNewLabel.setIcon(new ImageIcon(path + "\\���C����ʂQ\\���C���w�i.png"));
					
					User_management.User_DeleteB.setIcon(new ImageIcon(path + "\\���[�U�Ǘ����\\ID�폜.png"));
					User_management.pass_changeB.setIcon(new ImageIcon(path + "\\���[�U�Ǘ����\\�p�X�ύX.png"));
					User_management.LogoutB.setIcon(new ImageIcon(path + "\\���[�U�Ǘ����\\���O�A�E�g�Q.png"));
					User_management.lblNewLabel.setIcon(new ImageIcon(path + "\\���[�U�Ǘ����\\���[�U�w�i.png"));
					
					user.confirmB.setIcon(new ImageIcon(path + "\\���O�C�����\\�m�F.png"));
					user.okB.setIcon(new ImageIcon(path + "\\���O�C�����\\���O�C��.png"));
					user.CloseB.setIcon(new ImageIcon(path + "\\���O�C�����\\����Q.png"));
					user.lblNewLabel.setIcon(new ImageIcon(path + "\\���O�C�����\\���O�C���w�i.png"));
					user.lblNewLabel_1.setIcon(new ImageIcon(path + "\\���O�C�����\\�A�C�R���T�u.png"));
					
					
				}
				
				
			}
		});
		choice.setBounds(286, 10, 210, 19);
		contentPane.add(choice);
		
		choice.add("���{��");
		choice.add("English");
		
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Java\\kadai\\workspace\\\u30B0\u30EB\u30FC\u30D7\\\u30E1\u30A4\u30F3\u753B\u9762\\\u30E1\u30A4\u30F3\u80CC\u666F.png"));
//		lblNewLabel.setIcon(new ImageIcon(path + "\\���C�����\\rect45870.png"));
		lblNewLabel.setBounds(0, -134, 722, 596);
		contentPane.add(lblNewLabel);

		/*
		 * �^�X�N�g���C����@�v���O����
		 */
		addWindowListener(new MyWindowListener(this)); // �E�B���h�E�̏����擾
		SystemTray tray = SystemTray.getSystemTray(); // �V�X�e���g���C�����擾

		// �^�X�N�g���C�ɕ\������A�C�R�����Z�b�g
		TrayIcon icon = new TrayIcon(
				ImageIO.read(new File(path + "\\�^�C�g��3.png")));

		// �|�b�v�A�b�v���j���[
		PopupMenu menu = new PopupMenu();

		// ���C�����
		MenuItem aItem = new MenuItem("���C�����");
		aItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// ���[�U�F�؉�ʁ@�\������Ă��Ȃ���
				if (!User.isShowing()) {
					setAutoRequestFocus(true); // ���C����ʁ@�t�H�[�J�X�����Ă�
					setVisible(true); // ���C����ʁ@�\��
				}

			}
		});

		// �I�����j���[
		MenuItem exitItem = new MenuItem("�I��");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// �^�X�N�g���C�ɃA�C�R�����_�u���N���b�N���ꂽ��
		/*
		 * icon.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { setVisible(true); // ���C����ʂ�\�� } });
		 */

		// ���j���[�Ƀ��j���[�A�C�e����ǉ�
		tray.add(icon); // �^�X�N�g���C�ɃA�C�R�����Z�b�g
		icon.setPopupMenu(menu);
		menu.add(aItem);
		menu.add(exitItem);
		
		main.Table.setIcon(new ImageIcon(path + "\\���C����ʂQ\\�ꗗ�\.png"));
		main.DeskB.setIcon(new ImageIcon(path + "\\���C����ʂQ\\�f�X�N�g�b�v.png"));
		main.UserB.setIcon(new ImageIcon(path + "\\���C����ʂQ\\���[�U.png"));
		main.buttonB.setIcon(new ImageIcon(path + "\\���C����ʂQ\\�{�^��.png"));
		main.TaskB.setIcon(new ImageIcon(path + "\\���C����ʂQ\\�^�X�N.png"));
		main.LogoutB.setIcon(new ImageIcon(path + "\\���C����ʂQ\\���O�A�E�g.png"));
		main.lblNewLabel.setIcon(new ImageIcon(path + "\\���C����ʂQ\\���C���w�i.png"));

	}

	/**
	 * 
	 * @author 
	 * �E�B���h�E���h���b�O����ۂɁAx�E�������X�V����N���X
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
		public void mousePressed(MouseEvent me) { start=me; }//�ŏ��ɒ͂񂾃|�C���g���L��
		public void mouseReleased(MouseEvent me){}
		}
	
	

	public static boolean getKeyCheckBox() {

		return KeyCheckBox.isSelected();
	}
}

/* �^�X�N�g���C���f�p */
class MyWindowListener extends WindowAdapter {

	private main main = null;

	public MyWindowListener(main initializer) {
		main = initializer;
	}

	//
	public void windowIconified(WindowEvent e) {
		main.setVisible(false);
	}

	// �A�v��������ꂽ��
	public void windowClosing(WindowEvent e) {
		main.setIconImage(null);
		System.exit(0);
	}
	
	
	
	
}
