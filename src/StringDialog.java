import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.util.Collections;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class StringDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private boolean OkFlag = false;
	private boolean CancelFlag = false;
	private JComboBox<String> KeyComboBox;
	private int KCode;
	private int KCnt;
	private String KName = null;
	private String KName2 = null;
	private String ID;

	
	private String[] KeyName = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
								 "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
								 "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "@", "[", ";",
								 ":", "]", ",", ".", "?", "-", "^", "��", "��", "��", "��", "��", "Enter�L�[",
								 "Space�L�[", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12"
							   };
	
	private int[] KeyCd = {
							 30, 48, 46, 32, 18, 33, 34, 35,    23,    36,    37,    38, 50,
							 49, 24, 25, 16, 19, 31, 20, 22,    47,    17,    45,    21, 44,
							 11,  2,  3,  4,  5,  6,  7,  8,     9,    10,    41,    26, 13,
							 39, 27, 51, 52, 53, 12, 40, 43, 57416, 57421, 57419, 57424, 28,
							 57, 59, 60, 61, 62, 63, 64, 65,    66,    67,    68,    87, 88
						  };
	private JTextArea TextField;
	private String TextStr = null;
	private list list;
	private H2 h2;
	private JButton NoSettingButton;
	private JCheckBox ShiftCheckBox;
	private JCheckBox AltCheckBox;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public StringDialog(list lt){
		this();
		list = lt;
	}
	public StringDialog() {
		setTitle("\u30B7\u30E7\u30FC\u30C8\u30AB\u30C3\u30C8\u30AD\u30FC\u767B\u9332");
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 335, 235);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		//�����ɕ\��
		setLocationRelativeTo(null);

		ShiftCheckBox = new JCheckBox("Shift");
		ShiftCheckBox.setBounds(8, 92, 103, 21);
		contentPanel.add(ShiftCheckBox);
		
		AltCheckBox = new JCheckBox("Alt");
		AltCheckBox.setBounds(8, 115, 103, 21);
		contentPanel.add(AltCheckBox);
		
		KeyComboBox = new JComboBox<String>();
		KeyComboBox.setBounds(119, 93, 179, 19);
		contentPanel.add(KeyComboBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton OkButton = new JButton("\u767B\u9332");
				OkButton.addActionListener(new ActionListener() {
					//�o�^�{�^��
					public void actionPerformed(ActionEvent arg0) {
						int KeyIndex = KeyComboBox.getSelectedIndex();
						KCode = KeyCd[KeyIndex];
						KName = KeyName[KeyIndex];
						KName2 = "";
						KCnt = 1;

						//System.out.println("�f�o�b�O�FOkFlag=" + OkFlag);
						//System.out.println("�f�o�b�O�FCancelFlag=" + CancelFlag);
						//System.out.println("�f�o�b�O�FOCFlag=" + OCFlag);
						
						//�S�č����̃L�[�ɍ��킹��
						if( ShiftCheckBox.isSelected() ){
							KCode *= 42;   //LS=42 , RS=54
							KName2 += "Shift + ";
							KCnt++;
						}
						if( AltCheckBox.isSelected() ){
							KCode *= 56;   //LAlt=56 , RAlt=�Ȃ�
							KName2 += "Alt + ";
							KCnt++;
						}
						
						KName = KName2 + KName;
						OkFlag = true;
						CancelFlag = false;
						//System.out.println("�f�o�b�O�FOkFlag=" + OkFlag);
						//System.out.println("�f�o�b�O�FCancelFlag=" + CancelFlag);
						//System.out.println("�f�o�b�O�FOCFlag=" + OCFlag);

						System.out.println("�ύX�O�FTextField=" + TextField.getText());
						System.out.println("�ύX�O�F����=" + TextField.getText().length());
						
						
						// �o�^���镶����̒�����TextLength�ɑ��
						int TextLength = TextField.getText().length();

						if ( TextLength > 0 && TextLength <= 10000 ) {
							// �����L�[���ݒ肳��Ă��邩�̂������Ŕ��������---------------------------------------
							Object[][] tabledata = list.getTableData();
							boolean Flag = false;
							int TableLength = tabledata.length;

							
							// �����L�[�œo�^����ĂȂ����m���߂�
							for( int i = 0 ; i < TableLength ; i++ ){
//								System.out.println("##############+ ID != tabledata:" + i + ID != (String)tabledata[i][1]);
//								System.out.println("$$$$ID=" + ID);
//								System.out.println("$$$$ta=" + (String)tabledata[i][1]);
//								System.out.println("$$$$ta=" + tabledata[Row][3]);

								//�o�^����Ă���R�}���h���������@���@ID���������Ȃ��i�I������Ă���s�͑ΏۊO�̂��߁j�@���@������݂̂�ΏۂƂ���i�t�@�C���p�X���󔒂̂Ƃ���j
								if( KName.equals((String)tabledata[i][4]) && !(ID.equals((String)tabledata[i][1])) && tabledata[i][3] == ""/*�N���p�������񂩔���*/ ){
									System.out.println("-----------------------------------------------------");
									Flag = true;	//�����̂������������߃t���O�𗧂Ă�
									break;		//���[�v�𔲂���
								}
							}
							

							if( Flag ){//�o�^���s�_�C�A���O�\��
								JOptionPane.showConfirmDialog(null,
										"���̓o�^����Ă��镶����œ����V���[�g�J�b�g�L�[���g���Ă��܂��B", "�V���[�g�J�b�g�L�[�̏d���o�^�s��",
										JOptionPane.DEFAULT_OPTION,
										JOptionPane.ERROR_MESSAGE);
							} else {//�o�^����
								KName = "'" + KName + "'";
								TextField.setText(TextField.getText().replace("'","''"));
								System.out.println("�ύX��FTextField=" + TextField.getText());
								System.out.println("�ύX��F����=" + TextField.getText().length());								
								setVisible(false);
							}
							
						} else {
							if( TextLength == 0 ){
								JOptionPane.showConfirmDialog(null,
										"�Œ�ł�1�����ȏ�͓��͂��Ă��������B", "�������s��",
										JOptionPane.DEFAULT_OPTION,
										JOptionPane.ERROR_MESSAGE);
							} else {
								JOptionPane.showConfirmDialog(null,
										"�o�^���镶����̒������I�[�o�[���Ă��܂��B", "�������I�[�o�[",
										JOptionPane.DEFAULT_OPTION,
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				});
				OkButton.setActionCommand("OK");
				buttonPane.add(OkButton);
				getRootPane().setDefaultButton(OkButton);
			}
			{
				NoSettingButton = new JButton("\u30AD\u30FC\u8A2D\u5B9A\u305B\u305A\u767B\u9332");
				NoSettingButton.addActionListener(new ActionListener() {
					//�L�[�ݒ肹���o�^�{�^��
					public void actionPerformed(ActionEvent e) {
						OkFlag = false;
						CancelFlag = true;
						KName = "''";
						KName2 = "";
						KCode = 0;
						KCnt = 0;
						TextField.setText(TextStr);
						setVisible(false);
					}
				});
				NoSettingButton.setActionCommand("Cancel");
				buttonPane.add(NoSettingButton);
			}
			
			JButton CancelButton = new JButton("\u30AD\u30E3\u30F3\u30BB\u30EB");
			CancelButton.addActionListener(new ActionListener() {
				//�L�����Z���{�^��
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
				}
			});
			buttonPane.add(CancelButton);
		}
		
		for(String s:KeyName){
			KeyComboBox.addItem(s);
		}
		KeyComboBox.setSelectedIndex(0);
		
		JLabel Label = new JLabel("\u767B\u9332\u3057\u305F\u3044\u6587\u5B57\u5217 \uFF0810000\u6587\u5B57\u4EE5\u5185\uFF09");
		Label.setBounds(12, 10, 286, 13);
		contentPanel.add(Label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 27, 290, 30);
		contentPanel.add(scrollPane);
		
		TextField = new JTextArea();
		TextField.addCaretListener(new CaretListener() {
			//JTextArea�L�����b�g�̏���
			public void caretUpdate(CaretEvent arg0) {
				Label.setText("�o�^������������ �i10000�����ȓ��j             " + TextField.getText().length() + "����");
			}
		});
		TextField.addFocusListener(new FocusAdapter() {
			@Override
			//JTextArea�Ƀt�H�[�J�X�������������̏���
			public void focusGained(FocusEvent arg0) {
				scrollPane.setSize(290, 65);
				TextField.setSize(290, 65);
			}
			@Override
			//JTextArea�̃t�H�[�J�X���O�ꂽ���̏���
			public void focusLost(FocusEvent arg0) {
				scrollPane.setSize(290, 30);
				TextField.setSize(290, 30);
			}
		});
		scrollPane.setViewportView(TextField);
		TextField.setColumns(10);
		
		JLabel Label2 = new JLabel("\u30B7\u30E7\u30FC\u30C8\u30AB\u30C3\u30C8\u30AD\u30FC\u306E\u8A2D\u5B9A");
		Label2.setToolTipText("ggg");
		Label2.setBounds(12, 67, 115, 13);
		contentPanel.add(Label2);
		

	}
	
	
//	protected void processWindowEvent(WindowEvent e) {
//		if (e.getID() == WindowEvent.WINDOW_CLOSING ) {
//			System.out.println("�I���C�x���g");
//	    }
//	}
	
	
	public int getKCode(){
		return KCode;
	}
	
	public int getKCnt(){
		return KCnt;
	}
	
	public String getKName(){
		return KName;
	}
	
	//�����{�^���������ꂽ���ǂ����𔻒肷��t���O���擾
	public boolean getOkFlag(){
		boolean Flag;
		
		Flag = OkFlag;
		OkFlag = false;
		CancelFlag = false;
		return Flag;
	}
	
	//�ݒ肵�Ȃ��{�^���������ꂽ���ǂ����𔻒肷��t���O���擾
	public boolean getCancelFlag(){
		boolean Flag;
		
		Flag = CancelFlag;
		OkFlag = false;
		CancelFlag = false;
		return Flag;
	}
	
	//�e�L�X�g�t�B�[���h�ɂ��镶������擾����
	public String getTextField(){
		return TextField.getText();
	}
	
	//�e�L�X�g�t�B�[���h�Ɉ���str���Z�b�g����
	public void setTextField(String str){
		TextField.setText(str);
		TextStr = str;
	}
	
	public boolean getOCFlag(){
		boolean Flag;
		
		Flag = OkFlag || CancelFlag;
		OkFlag = false;
		CancelFlag = false;
		return Flag;
	}
	
	//�ݒ肵�Ȃ��{�^����Enable���Z�b�g����
	public void setInformation(boolean Set){
		setInformation(Set, 0);
	}
	
	//�I�[�o�[���[�h
	public void setInformation(boolean Set, int IndexID){
		ID = String.valueOf(IndexID);
		NoSettingButton.setEnabled(Set);
	}
	
	//Dialog�ɏ����Z�b�g�i�o�^���j
	public void setDialog(){
		ShiftCheckBox.setSelected(false);
		AltCheckBox.setSelected(false);
		KeyComboBox.setSelectedIndex(0);
	}
	
	//Dialog�ɏ����Z�b�g�i�ύX���j
	public void setDialog( int SelectedRow, int IndexID , String FileName){
		Object[][] tabledata = list.getTableData();
		String Key = (String)tabledata[SelectedRow][4];
		int index;
		String str;
		
		int keycount;
		
		keycount = h2.getKeyCount(IndexID);
		
		
		//��ɉ����ꂽ�L�[�̐�������i�R�Ȃ疳�����œ�Ƃ��`�F�b�N�A�t�ɂP�Ȃ疳�����Ń`�F�b�N�Ȃ��A�Q�̏ꍇ�̂�if�̔��菈����������j
		
		
		if( keycount == 0 ){
			System.out.println("�f�[�^�擾���s");
		} else {
			if( keycount == 1 ){
				//���ׂẴ`�F�b�N���͂���
				ShiftCheckBox.setSelected(false);
				AltCheckBox.setSelected(false);
			}
			else if( keycount == 3 ){
				//���ׂĂɃ`�F�b�N������
				ShiftCheckBox.setSelected(true);
				AltCheckBox.setSelected(true);
			}
			else{
				//Shift�Ȃ̂�Alt�Ȃ̂��̔���
				
				//�o�^������������󔒂̈ʒu�ŕ����鏈��������
				index = Key.indexOf( " " );
				str = Key.substring( 0, index );
				//System.out.println("~~~�m�F�Fstr=" + str + "@");

				if( "Shift".equals( str ) ){
					ShiftCheckBox.setSelected(true);
					AltCheckBox.setSelected(false);
				} else {
					ShiftCheckBox.setSelected(false);
					AltCheckBox.setSelected(true);
				}				
			}
			
			index = Key.lastIndexOf( " " );
			str = Key.substring( index + 1, Key.length() );
			System.out.println("~~~�m�F�Fstr=" + str + "@");
			int count;
			
			for( count = 0 ; !(str.equals(KeyName[count])) ; count++ );
			
			KeyComboBox.setSelectedIndex(count);

			
			setTextField(FileName);
		}
	}
	
	
	public void setH2(H2 H2){
		h2 = H2;
	}
}

