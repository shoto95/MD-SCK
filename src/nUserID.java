import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import java.awt.Component;
import java.io.File;
import java.awt.Color;

public class nUserID extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	// OK�E�L�����Z������@�ϐ�
	private int hantei = 0;

	static JLabel label;
	static JButton okButton;
	static JButton cancelButton;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public nUserID() {
		setTitle("\u78BA\u8A8D\u753B\u9762");
		setModalityType(ModalityType.APPLICATION_MODAL);
		// ��Ԏ�O�ɕ\��
		setAlwaysOnTop(true);
		setBounds(100, 100, 430, 156);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		hantei = 0;

		
		// path �́@�J�����g�f�B���N�g���擾
				String path = new File("user.dir").getAbsoluteFile().getParent();
		{
			okButton = new JButton("");
			okButton.setIcon(new ImageIcon("C:\\Java\\kadai\\workspace\\\u30B0\u30EB\u30FC\u30D7\\ID\u4F5C\u6210\uFF12\\OK.png"));
			okButton.setAlignmentY(Component.TOP_ALIGNMENT);
			okButton.setContentAreaFilled(false);
//			okButton.setIcon(new ImageIcon(main.path + "\\ID�쐬\\text4251.png"));
			nUserID.okButton.setIcon(new ImageIcon(path + "\\ID�쐬�Q\\OK.png"));
			{
				lblNewLabel = new JLabel("\u65B0\u3057\u3044ID\u3092\u4F5C\u6210\u3057\u307E\u3059\u304B\uFF1F\uFF1F");
				lblNewLabel.setForeground(Color.YELLOW);
				lblNewLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 22));
				lblNewLabel.setBounds(77, 22, 429, 55);
				contentPanel.add(lblNewLabel);
			}
			okButton.setBounds(268, 86, 71, 31);
			contentPanel.add(okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					// ���C����ʕ\��
					main.frame.setVisible(true);

					hantei = 1;

					// �������g�̔�\��
					setVisible(false);
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			cancelButton = new JButton("");
			cancelButton.setIcon(new ImageIcon("C:\\Java\\kadai\\workspace\\\u30B0\u30EB\u30FC\u30D7\\ID\u4F5C\u6210\uFF12\\\u30AD\u30E3\u30F3\u30BB\u30EB.png"));
			cancelButton.setContentAreaFilled(false);
//			cancelButton.setIcon(new ImageIcon(main.path + "\\ID�쐬\\rect4239.png"));
			nUserID.cancelButton.setIcon(new ImageIcon(path + "\\ID�쐬�Q\\�L�����Z��.png"));
			cancelButton.setBounds(337, 86, 77, 31);
			contentPanel.add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					// �L�����Z�������ꂽ
					hantei = 2;
					// �������g�̔�\��
					setVisible(false);

				}
			});
			cancelButton.setActionCommand("Cancel");
		}
		{
			label = new JLabel("");
//			label.setIcon(new ImageIcon(main.path + "\\ID�쐬\\text4257.png"));
			nUserID.label.setIcon(new ImageIcon(path + "\\ID�쐬�Q\\ID�쐬�w�i.png"));
			label.setBounds(-34, -42, 506, 186);
			contentPanel.add(label);
		}
		
		
		
		
		
	}

	// OK�E�L�����Z������@��Ԃ����\�b�h
	public int  getflg() {
		return hantei;
	}
}
