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
		
		//中央に表示
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
					//登録ボタン
					public void actionPerformed(ActionEvent arg0) {
						int KeyIndex = KeyComboBox.getSelectedIndex();
						KCode = KeyCd[KeyIndex];
						KName = KeyName[KeyIndex];
						KName2 = "";
						KCnt = 1;

						//System.out.println("デバッグ：OkFlag=" + OkFlag);
						//System.out.println("デバッグ：CancelFlag=" + CancelFlag);
						//System.out.println("デバッグ：OCFlag=" + OCFlag);
						
						//全て左側のキーに合わせる
						if( ShiftCheckBox.isSelected() ){
							KCode *= 42;   //LS=42 , RS=54
							KName2 += "Shift + ";
							KCnt++;
						}
						if( AltCheckBox.isSelected() ){
							KCode *= 56;   //LAlt=56 , RAlt=ない
							KName2 += "Alt + ";
							KCnt++;
						}
						
						KName = KName2 + KName;
						OkFlag = true;
						CancelFlag = false;
						//System.out.println("デバッグ：OkFlag=" + OkFlag);
						//System.out.println("デバッグ：CancelFlag=" + CancelFlag);
						//System.out.println("デバッグ：OCFlag=" + OCFlag);

						System.out.println("変更前：TextField=" + TextField.getText());
						System.out.println("変更前：長さ=" + TextField.getText().length());
						
						
						// 登録する文字列の長さをTextLengthに代入
						int TextLength = TextField.getText().length();

						if ( TextLength > 0 && TextLength <= 10000 ) {
							// 同じキーが設定されているかのをここで判定をする---------------------------------------
							Object[][] tabledata = list.getTableData();
							boolean Flag = false;
							int TableLength = tabledata.length;

							
							// 同じキーで登録されてないか確かめる
							for( int i = 0 ; i < TableLength ; i++ ){
//								System.out.println("##############+ ID != tabledata:" + i + ID != (String)tabledata[i][1]);
//								System.out.println("$$$$ID=" + ID);
//								System.out.println("$$$$ta=" + (String)tabledata[i][1]);
//								System.out.println("$$$$ta=" + tabledata[Row][3]);

								//登録されているコマンドが等しく　かつ　IDが等しくなく（選択されている行は対象外のため）　かつ　文字列のみを対象とする（ファイルパスが空白のところ）
								if( KName.equals((String)tabledata[i][4]) && !(ID.equals((String)tabledata[i][1])) && tabledata[i][3] == ""/*起動用か文字列か判定*/ ){
									System.out.println("-----------------------------------------------------");
									Flag = true;	//同じのが見つかったためフラグを立てる
									break;		//ループを抜ける
								}
							}
							

							if( Flag ){//登録失敗ダイアログ表示
								JOptionPane.showConfirmDialog(null,
										"他の登録されている文字列で同じショートカットキーが使われています。", "ショートカットキーの重複登録不可",
										JOptionPane.DEFAULT_OPTION,
										JOptionPane.ERROR_MESSAGE);
							} else {//登録処理
								KName = "'" + KName + "'";
								TextField.setText(TextField.getText().replace("'","''"));
								System.out.println("変更後：TextField=" + TextField.getText());
								System.out.println("変更後：長さ=" + TextField.getText().length());								
								setVisible(false);
							}
							
						} else {
							if( TextLength == 0 ){
								JOptionPane.showConfirmDialog(null,
										"最低でも1文字以上は入力してください。", "文字数不足",
										JOptionPane.DEFAULT_OPTION,
										JOptionPane.ERROR_MESSAGE);
							} else {
								JOptionPane.showConfirmDialog(null,
										"登録する文字列の長さがオーバーしています。", "文字数オーバー",
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
					//キー設定せず登録ボタン
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
		
		JLabel Label = new JLabel("\u767B\u9332\u3057\u305F\u3044\u6587\u5B57\u5217 \uFF0810000\u6587\u5B57\u4EE5\u5185\uFF09");
		Label.setBounds(12, 10, 286, 13);
		contentPanel.add(Label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 27, 290, 30);
		contentPanel.add(scrollPane);
		
		TextField = new JTextArea();
		TextField.addCaretListener(new CaretListener() {
			//JTextAreaキャレットの処理
			public void caretUpdate(CaretEvent arg0) {
				Label.setText("登録したい文字列 （10000文字以内）             " + TextField.getText().length() + "文字");
			}
		});
		TextField.addFocusListener(new FocusAdapter() {
			@Override
			//JTextAreaにフォーカスが当たった時の処理
			public void focusGained(FocusEvent arg0) {
				scrollPane.setSize(290, 65);
				TextField.setSize(290, 65);
			}
			@Override
			//JTextAreaのフォーカスが外れた時の処理
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
//			System.out.println("終了イベント");
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
	
	//完了ボタンが押されたかどうかを判定するフラグを取得
	public boolean getOkFlag(){
		boolean Flag;
		
		Flag = OkFlag;
		OkFlag = false;
		CancelFlag = false;
		return Flag;
	}
	
	//設定しないボタンが押されたかどうかを判定するフラグを取得
	public boolean getCancelFlag(){
		boolean Flag;
		
		Flag = CancelFlag;
		OkFlag = false;
		CancelFlag = false;
		return Flag;
	}
	
	//テキストフィールドにある文字列を取得する
	public String getTextField(){
		return TextField.getText();
	}
	
	//テキストフィールドに引数strをセットする
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
	
	//設定しないボタンのEnableをセットする
	public void setInformation(boolean Set){
		setInformation(Set, 0);
	}
	
	//オーバーロード
	public void setInformation(boolean Set, int IndexID){
		ID = String.valueOf(IndexID);
		NoSettingButton.setEnabled(Set);
	}
	
	//Dialogに情報をセット（登録時）
	public void setDialog(){
		ShiftCheckBox.setSelected(false);
		AltCheckBox.setSelected(false);
		KeyComboBox.setSelectedIndex(0);
	}
	
	//Dialogに情報をセット（変更時）
	public void setDialog( int SelectedRow, int IndexID , String FileName){
		Object[][] tabledata = list.getTableData();
		String Key = (String)tabledata[SelectedRow][4];
		int index;
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
				AltCheckBox.setSelected(false);
			}
			else if( keycount == 3 ){
				//すべてにチェックを入れる
				ShiftCheckBox.setSelected(true);
				AltCheckBox.setSelected(true);
			}
			else{
				//ShiftなのかAltなのかの判定
				
				//登録した文字列を空白の位置で分ける処理を書く
				index = Key.indexOf( " " );
				str = Key.substring( 0, index );
				//System.out.println("~~~確認：str=" + str + "@");

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
			System.out.println("~~~確認：str=" + str + "@");
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

