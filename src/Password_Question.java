import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalityType;

public class Password_Question extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	public static String Cpassword;
	public boolean flg = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Password_Question dialog = new Password_Question();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Password_Question() {
		setTitle("\u30D1\u30B9\u30EF\u30FC\u30C9\u5909\u66F4\u753B\u9762");
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel label = new JLabel(
				"\u65B0\u3057\u3044\u30D1\u30B9\u30EF\u30FC\u30C9\uFF1A");
		label.setBounds(58, 68, 200, 50);
		contentPanel.add(label);
		{
			JLabel label_1 = new JLabel(
					"\u65B0\u3057\u3044\u30D1\u30B9\u30EF\u30FC\u30C9\uFF08\u78BA\u8A8D\u7528\uFF09\uFF1A");
			label_1.setBounds(10, 128, 200, 50);
			contentPanel.add(label_1);
		}

		textField = new JTextField();
		textField.setBounds(143, 74, 200, 40);
		contentPanel.add(textField);
		textField.setColumns(10);
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(143, 134, 200, 40);
			contentPanel.add(textField_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String pass = textField.getText();
						String pass2 = textField_1.getText();

						// 両方空白を入力されていないか
						if (!list.isEmpty(pass) && !list.isEmpty(pass2)) {
							// 確認用とパスワードが一致しているか
							if (pass.equals(pass2)) {
								Cpassword = textField.getText(); // 新しいパスワード格納
								flg = true; // 両方一致している
								setVisible(false); // パスワード変更画面を非表示
							} else {

								JDialog not = new JDialog();
								JOptionPane.showMessageDialog(not,
										"入力された値が一致しません。");
								// 入力部分を消す
								textField.setText("");
								textField_1.setText("");
							}

						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						main.frame.setVisible(true);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
