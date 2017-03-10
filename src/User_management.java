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
		User_DeleteB.setIcon(new ImageIcon(main.path + "\\���[�U�Ǘ����\\text7209.png"));
		User_DeleteB.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// ���[�U�폜��ʁ@ �_�C�A���O��\��
				JFrame frame = new JFrame();
				int ret = JOptionPane.showConfirmDialog(frame, "����   �u"
						+ user.Userstr[0] + "�v  ���폜���Ă���낵���ł����H", "���[�U�폜���",
						JOptionPane.YES_NO_OPTION);

				// ���[�U�폜��ʁ@OK�������ꂽ�ꍇ
				if (ret == JOptionPane.OK_OPTION) {

					// H2�f�[�^�x�[�X��ؒf
					H2.Disconnect();

					// ���[�U��H2DB�̃t�@�C�����擾
					File mv = new File(main.path + "\\bin\\" + user.Userstr[0]
							+ ".mv.db");
					File trace = new File(main.path + "\\bin\\"
							+ user.Userstr[0] + ".trace.db");

					// �A�J�E���g���@�t�@�C���p�X
					Account = new File(main.path + "\\bin\\" + "account.txt");

					// mv�@�E�@trace�t�@�C�����폜
					mv.delete();
					trace.delete();

					// �A�J�E���g����z��Ɋi�[
					String str[] = new String[50];
					try {
						str = main.User.ReadAccount();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}

					// �Ώۃ��[�U�̃A�J�E���g�����폜
					try {
						DeleteAccount(str);
					} catch (IOException e) {
						e.printStackTrace();
					}

					// ���[�U�Ǘ���ʂ��\��
					setVisible(false);

					// ���[�U�F�؉�ʂ�\��
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
		pass_changeB.setIcon(new ImageIcon(main.path + "\\���[�U�Ǘ����\\rect4773.png"));
		pass_changeB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// ���[�U�Ǘ���ʂ��\��
				setVisible(false);

				// ���C����ʔ�\��
				main.frame.setVisible(false);

				// �p�X���[�h�ύX���
				Password_Question passquestion = new Password_Question();
				passquestion.setVisible(true);

				// �A�J�E���g����z��Ɋi�[
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
						// TODO �����������ꂽ catch �u���b�N
						e.printStackTrace();
					}
				}

			}
		});
		pass_changeB.setBounds(184, 125, 146, 103);
		contentPane.add(pass_changeB);

		LogoutB = new JButton("");
		LogoutB.setIcon(new ImageIcon(main.path + "\\���[�U�Ǘ����\\rect440.png"));
		LogoutB.setContentAreaFilled(false);
		LogoutB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// ��xH2�f�[�^�x�[�X��ؒf
				H2.Disconnect();

				
				main.User.textField.requestFocus();
				main.frame.setVisible(false);
				main.User.setVisible(true); // ���[�U�F�� ��ʕ\��
				main.User.setAlwaysOnTop(true);// �@��Ԏ�O�ɕ\��
				main.User.setLocationRelativeTo(null);// Windows�̐^�񒆂ɕ\��

				// �������g�̔�\��
				setVisible(false);

			}
		});
		LogoutB.setBounds(98, 252, 177, 46);
		contentPane.add(LogoutB);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Java\\kadai\\workspace\\Group6-13\\\u30E6\u30FC\u30B6\u7BA1\u7406\u753B\u9762\uFF12\\\u30E6\u30FC\u30B6\u80CC\u666F.png"));
		lblNewLabel.setBounds(0, -275, 523, 864);
		contentPane.add(lblNewLabel);

		User_management.User_DeleteB.setIcon(new ImageIcon(main.path + "\\���[�U�Ǘ���ʂQ\\ID�폜.png"));
		User_management.pass_changeB.setIcon(new ImageIcon(main.path + "\\���[�U�Ǘ���ʂQ\\�p�X�ύX.png"));
		User_management.LogoutB.setIcon(new ImageIcon(main.path + "\\���[�U�Ǘ���ʂQ\\���O�A�E�g�Q.png"));
		User_management.lblNewLabel.setIcon(new ImageIcon(main.path + "\\���[�U�Ǘ���ʂQ\\���[�U�w�i.png"));
		
	}

	// �Ώۂ̃��[�U���폜����@���\�b�h
	private void DeleteAccount(String[] str) throws IOException {

		// �Ώۃ��[�U���� �ϐ��@�Ɋi�[
		String info = user.encryptStr(user.Userstr[0]) + ":"
				+ user.encryptStr(user.Userstr[1]);

		// ���[�U����T��
		for (int i = 0; i < User_cnt ; i++) {
			// �Ώۃ��[�U�����m
			if (str[i].equals(info)) {
				str[i] = "%"; // �Ώۃ��[�U�̏��@�u%�v�@���i�[
			}
		}

		// �L�q�p�ϐ��� �A�J�E���g������
		BufferedWriter writer = new BufferedWriter(new FileWriter(Account));

		// �z��̓��e�� �A�J�E���g���̃t�@�C���Ɋi�[
		for (int i = 0; i < User_cnt ; i++) {
			writer.write(str[i]);
			writer.newLine();
		}

		// �u&�v ���Ō���i�[
		writer.write("&");
		writer.close();

	}

	// �Ώۂ̃��[�U�̃p�X���[�h�ύX�@���\�b�h
	private void ChangePassword(String[] str) throws IOException {

		// �Ώۃ��[�U���� �ϐ��@�Ɋi�[
		String preinfo = user.encryptStr(user.Userstr[0]) + ":"
				+ user.encryptStr(user.Userstr[1]);
		String info = user.encryptStr(user.Userstr[0]) + ":"
				+ user.encryptStr(Password_Question.Cpassword);

		// ���[�U����T��
		for (int i = 0; i < User_cnt; i++) {
			// �Ώۃ��[�U�����m
			if (str[i].equals(preinfo)) {
				str[i] = info; // �Ώۃ��[�U�̏����@�u�V�����p�X���[�h�v�@���i�[
			}
		}
		System.out.println(Account);
		// �L�q�p�ϐ��� �A�J�E���g������
		BufferedWriter writer = new BufferedWriter(new FileWriter(Account));

		// �z��̓��e�� �A�J�E���g���̃t�@�C���Ɋi�[
		for (int i = 0; i < User_cnt; i++) {
			writer.write(str[i]);
			writer.newLine();
		}

		// �u&�v ���Ō���i�[
		writer.write("&");
		writer.close();

		H2.passChange(Password_Question.Cpassword);

	}

}
