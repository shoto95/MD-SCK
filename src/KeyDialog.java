import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JLabel;


public class KeyDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private boolean OkFlag = false;
	private boolean CancelFlag = false;
	private boolean OCFlag = false;
	private JComboBox<String> KeyComboBox;
	private int KCode;
	private int KCnt;
	private String KName;
	private String KName2;

	
	private list list;
	private H2 h2;
	private JLabel FileNameLabel;
	
	private String[] KeyName = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
								 "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
								 "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "@", "[", ";",
								 ":", "]", ",", ".", "?", "-", "^", "￥", "↑", "→", "←", "↓", "Enterキー",
								 "Spaceキー", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12"
							   };
	
	private int[] KeyCd = {
							 30, 48, 46, 32, 18, 33, 34, 35,    23,    36,    37,    38, 50,
							 49, 24, 25, 16, 19, 31, 20, 22,    47,    17,    45,    21, 44,
							 11,  2,  3,  4,  5,  6,  7,  8,     9,    10,    41,    26, 13,
							 39, 27, 51, 52, 53, 12, 40, 43, 57416, 57421, 57419, 57424, 28,
							 57, 59, 60, 61, 62, 63, 64, 65,    66,    67,    68,    87, 88
						  };
	private JCheckBox ShiftCheckBox;
	private JCheckBox CtrlCheckBox;
	private JCheckBox AltCheckBox;

	public KeyDialog(list lt){
		this();
		list = lt;
	}
	
	/**
	 * Create the dialog.
	 */
	public KeyDialog() {
		setTitle("\u30B7\u30E7\u30FC\u30C8\u30AB\u30C3\u30C8\u30AD\u30FC\u767B\u9332");
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 360, 170);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		//中央に表示
		setLocationRelativeTo(null);
		
		ShiftCheckBox = new JCheckBox("Shift");
		ShiftCheckBox.setBounds(8, 17, 103, 21);
		contentPanel.add(ShiftCheckBox);
		
		CtrlCheckBox = new JCheckBox("Ctrl");
		CtrlCheckBox.setBounds(8, 40, 103, 21);
		contentPanel.add(CtrlCheckBox);
		
		AltCheckBox = new JCheckBox("Alt");
		AltCheckBox.setBounds(8, 63, 103, 21);
		contentPanel.add(AltCheckBox);
		
		KeyComboBox = new JComboBox<String>();
		KeyComboBox.setBounds(119, 18, 179, 19);
		contentPanel.add(KeyComboBox);
		KName = null;
		KName = null;
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton OkButton = new JButton("\u767B\u9332");
				OkButton.addActionListener(new ActionListener() {
					//完了ボタン
					public void actionPerformed(ActionEvent arg0) {
						int KeyIndex = KeyComboBox.getSelectedIndex();
						KCode = KeyCd[KeyIndex];
						KName = KeyName[KeyIndex];
						KName2="";
						KCnt = 1;
						
						//全て左側のキーに合わせる
						if( ShiftCheckBox.isSelected() ){
							KCode *= 42;   //LS=42 , RS=54
							KName2 += "Shift + ";
							KCnt++;
						}
						if( CtrlCheckBox.isSelected() ){
							KCode *= 29;   //LC=29 , RC=3613
							KName2 += "Ctrl + ";
							KCnt++;
						}
						if( AltCheckBox.isSelected() ){
							KCode *= 56;   //LAlt=56 , RAlt=ない
							KName2 += "Alt + ";
							KCnt++;
						}
						
						KName = "'" + KName2 + KName + "'";
						OkFlag = true;
						CancelFlag = false;
						setVisible(false);
					}

				});
				OkButton.setActionCommand("OK");
				buttonPane.add(OkButton);
				getRootPane().setDefaultButton(OkButton);
			}
			{
				JButton NoSettingButton = new JButton("\u30AD\u30FC\u8A2D\u5B9A\u305B\u305A\u767B\u9332");
				NoSettingButton.addActionListener(new ActionListener() {
					//設定しないボタン
					public void actionPerformed(ActionEvent e) {
						OkFlag = false;
						CancelFlag = true;
						KCode = 0;
						KCnt = 0;
						KName = "''";
						KName2 = "";
						setVisible(false);
					}
				});
				NoSettingButton.setActionCommand("Cancel");
				buttonPane.add(NoSettingButton);
			}
			
			JButton CancelButton = new JButton("\u30AD\u30E3\u30F3\u30BB\u30EB");
			CancelButton.addActionListener(new ActionListener() {
				//キャンセルボタン
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
		
		FileNameLabel = new JLabel("\u30AD\u30FC\u3092\u8A2D\u5B9A\u3059\u308B\u30D5\u30A1\u30A4\u30EB\u540D");
		FileNameLabel.setBounds(119, 67, 179, 13);
		contentPanel.add(FileNameLabel);
		

	}
	
	public void setFileName(String FileName) {
		FileNameLabel.setText(FileName);
	}

	
	public int getKeyCode(){
		return KCode;
	}
	
	public int getKeyCnt(){
		return KCnt;
	}
	
	public String getKeyName(){
		return KName;
	}

	//完了ボタンが押されたかどうかを判定するフラグを取得
//	public boolean getOkFlag(){
//		boolean Flag;
//		
//		Flag = OkFlag;
//		OkFlag = false;
//		return Flag;
//	}
	
	//設定しないボタンが押されたかどうかを判定するフラグを取得
//	public boolean getCancelFlag(){
//		boolean Flag;
//		
//		Flag = CancelFlag;
//		CancelFlag = false;
//		return Flag;
//	}
	
	//完了ボタン 又は 設定しないボタンのどちらかが押された場合TRUEを返す（キャンセル 又は 閉じる を押すとFALSEを返す）
	public boolean getOCFlag(){
		boolean Flag;
		
		Flag = OkFlag || CancelFlag;
		OkFlag = false;
		CancelFlag = false;
		return Flag;
	}

	//Dialogに情報をセット（変更時）
	public void setDialog( int SelectedRow, int IndexID , String FileName){
		Object[][] tabledata = list.getTableData();
		String Key = (String)tabledata[SelectedRow][4];
		int index1;
		int index2;
		String str;
		
		int keycount;
		
		keycount = h2.getKeyCount(IndexID);
		
		
		//先に押されたキーの数を見る（３なら無条件で二つともチェック、逆に１つなら無条件でチェックなし、２つの場合のみifの判定処理をさせる）
		
		
		if( keycount == 0 ){
			System.out.println("データ取得失敗");
		} else {
			if( keycount == 1 ){
				//すべてのチェックをはずす
				ShiftCheckBox.setSelected(false);
				CtrlCheckBox.setSelected(false);
				AltCheckBox.setSelected(false);
			}
			else if( keycount == 4 ){
				//すべてにチェックを入れる
				ShiftCheckBox.setSelected(true);
				CtrlCheckBox.setSelected(true);
				AltCheckBox.setSelected(true);
			}
			else{
				//ShiftなのかCtrlなのかAltなのかの判定
				
				//登録した文字列を空白の位置で分ける処理を書く
				index1 = Key.indexOf( " " );
				str = Key.substring( 0, index1 );
				index1 = index1 + 3;
				//System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				//System.out.println("~~~確認1：str=" + str + "@");

				if( "Shift".equals( str ) ){
					System.out.println("Shift_true");
					ShiftCheckBox.setSelected(true);
					//成功したときにindexの更新
					index2 = Key.indexOf(" ", index1);
					str = Key.substring( index1, index2 );
					index1 = index2 + 3;
					//System.out.println("~~~確認2：str=" + str + "@");
				} else {
					System.out.println("Shift_false");
					ShiftCheckBox.setSelected(false);
				}

				
				
				if( "Ctrl".equals( str ) ){
					System.out.println("Ctrl_true");
					CtrlCheckBox.setSelected(true);
					index2 = Key.indexOf(" ", index1);
					str = Key.substring( index1, index2 );
					index1 = index2 + 3;
					//System.out.println("~~~確認2：str=" + str + "@");
				} else {
					System.out.println("Ctrl_false");
					CtrlCheckBox.setSelected(false);
				}


				if( "Alt".equals( str ) ){
					System.out.println("Alt_true");
					AltCheckBox.setSelected(true);
				} else {
					System.out.println("Alt_false");
					AltCheckBox.setSelected(false);
				}
			}
			System.out.println("Alph");
			index1 = Key.lastIndexOf( " " );
			str = Key.substring( index1 + 1, Key.length() );
			//System.out.println("~~~確認：str=" + str + "@");
			int count;
			
			for( count = 0 ; !(str.equals(KeyName[count])) ; count++ );
			
			KeyComboBox.setSelectedIndex(count);

			setFileName(FileName);
		}
	}

	public void setH2(H2 H2){
		h2 = H2;
	}
}
