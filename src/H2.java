import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;

import com.mysql.jdbc.PreparedStatement;

public class H2 {
	public static Connection con = null;
	static Statement stm;

	// SQL���@�i�[�ϐ��@
	private static String sql = null;
	static String ID;
	static String PASSWARD;
	private String COMAND;
	static boolean flg;
	static int option;

	// ���s�t�@�C�����@�ϐ�
	public static String EXE;

	
	// �e�[�u���p�@�z��
		public Object[][] str = { {new ImageIcon(""), "", "", "", "" }, { new ImageIcon(""), "", "", "", "" },
				{new ImageIcon(""), "", "", "", "" }, { new ImageIcon(""), "", "", "", "" },
				{new ImageIcon(""), "", "", "", "" }, { new ImageIcon(""), "", "", "", "" },
				{new ImageIcon(""), "", "", "", "" }, { new ImageIcon(""), "", "", "", "" },
				{new ImageIcon(""), "", "", "", "" }, { new ImageIcon(""), "", "", "", "" },
				{new ImageIcon(""), "", "", "", "" }, { new ImageIcon(""), "", "", "", "" },
				{new ImageIcon(""), "", "", "", "" }, { new ImageIcon(""), "", "", "", "" },
				{new ImageIcon(""), "", "", "", "" }, { new ImageIcon(""), "", "", "", "" },
				{new ImageIcon(""), "", "", "", "" }, { new ImageIcon(""), "", "", "", "" },
				{new ImageIcon(""), "", "", "", "" }, { new ImageIcon(""), "", "", "", "" },

		};

	

	// �t�@�C����
	private static String NAME = null;
	// �t�@�C���p�X
	private static String PASS = null;
	// �t�@�C��ID
	private String FILEID = null;
	// �A�C�R���p�X
	private String PICTURE = null;

	public H2(String id, String passward) {

		try {
			// ���͂��ꂽ�A�J�E���g������
			ID = id;
			System.out.println(ID);
			PASSWARD = passward;

			// JDBC�h���C�o�̃��[�h
			Class.forName("org.h2.Driver").newInstance();

			// H2�ɐڑ�
			con = DriverManager.getConnection("jdbc:h2:" + main.path
					+ "\\bin\\" + ID, ID, PASSWARD);
			stm = con.createStatement();
			System.out.println("H2�ɐڑ��ł��܂����B");
			flg = true;

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			System.out.println("JDBC�h���C�o�̃��[�h�Ɏ��s���܂����B");
		} catch (SQLException e) {
			System.out.println("H2�ɐڑ��ł��܂���ł����B");
		} finally {
		}
	}

	public H2() {
	}

	// while�ŉ񂵂čs���ڂ𐔂���
	public static void select_key(int keysum, int keycount) {

		try {
			// �X�e�[�g�����g����
			stm = con.createStatement();
			// SELECT ��
			sql = "SELECT * FROM " + "A" + ID + " WHERE KEYCODE=" + keysum
					+ " AND KEYCOUNT=" + keycount;
			ResultSet rs = stm.executeQuery(sql);

			
			Runtime rt = Runtime.getRuntime();
			while (rs.next()) {
				String filepath = rs.getString(3);
				System.out.println("�f�o�b�O�Ffilepath=" + filepath);
				
				
				Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
				if (filepath == "") {	//�t�@�C���p�X���󔒂Ȃ�΁A�t�@�C�����ɂ��镶������N���b�v�{�[�h�ɃR�s�[����
					clip = ClipboardCopy(rs.getString(2));
					
				} else {
					int keycode = rs.getInt(5);
					int keycnt = rs.getInt(6);
					System.out.println("�擾���� -> " + keycode);
					System.out.println("�擾���� -> " + keycnt);
					EXE = null;
					EXE = new String("explorer " + filepath);

					try {
						rt.exec(EXE);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("H2�ɐڑ��ł��܂���ł����B�S");
		}
	}

	// H2�f�[�^�x�[�X�̐ڑ���ؒf�@���\�b�h
	public static void Disconnect() {
		try {
			// ���݂̃f�[�^�x�[�X��ؒf
			con.close();

			// �e�[�u���X�V�񐔁@������
			main.cnt = true;

			// �{�^���t���[���@�E�@���C����ʁ@��\��
			list.Bframe.setVisible(false);
			main.frame.setVisible(false);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ���o�^ID �e�[�u���쐬����@���\�b�h
	public static void Create() {

		try {
			// �X�e�[�g�����g����
			stm = con.createStatement();

			// ���o�^ID �e�[�u���쐬
			 sql = "CREATE TABLE "
					 + "A" 
					+  ID 
					+ " (fileid int auto_increment,"
					+ "filename VARCHAR(10000),filepass VARCHAR(255),picture VARCHAR(255), keycode INT, keycount INT,comand VARCHAR(255))";
			System.out.println(sql);
			stm.executeUpdate(sql);
			System.out.println("create�����I�I");

		} catch (SQLException e) {
			System.out.println("H2�ɐڑ��ł��܂���ł����B2");
			e.printStackTrace();
		} finally {
		}

	}

	// �h���b�O���ꂽ�t�@�C������H2�f�[�^�x�[�X�ɑ}������@���\�b�h
	public void insert(String name, String pass, String picture, int code,
			int keycount, String comand) {
		try {
			// �X�e�[�g�����g����
			stm = con.createStatement();

			// �t�@�C�����E�t�@�C���p�X�E�A�C�R���̃p�X�@���
			NAME = name.replace("'", "''");
			PASS = pass.replace("'", "''");;
			PICTURE = picture.replace("'", "''");;

			
			// INSERT INTO ��
			sql = "INSERT INTO " + "A" + ID
					+ " (filename,filepass,picture,KEYCODE,KEYCOUNT,comand)"
					+ "VALUES(" + "'" + NAME + "','" + PASS + "','" + PICTURE
					+ "'," + code + "," + keycount + "," + comand + ")";
			System.out.println(sql);
			stm.executeUpdate(sql);
			System.out.println("Insert�����I�I");

		} catch (SQLException e) {
			System.out.println("H2�ɐڑ��ł��܂���ł����B3");
		} finally {
		}
	}

	// ��������f�[�^�x�[�X�ɓo�^����
	public void insert_String(String name, int code, int keycount, String comand) {
		try {
			// �X�e�[�g�����g����
			// stm = con.createStatement();
			String Empty = main.path + "\\empty.png";
			// INSERT INTO ��
			sql = "INSERT INTO " + "A" + ID
					+ " (filename,filepass,picture,KEYCODE,KEYCOUNT,comand)"
					+ "VALUES(" + "'" + name + "'," + "''" + ",'" + Empty
					+ "'," + code + "," + keycount + "," + comand + ")";
			// sql = "INSERT INTO "+ ID +
			// "(filename,filepass,picture,KEYCODE)VALUES(" + "'" + NAME +"'"+
			// "," + "'" + PASS +"'"+ "," + "'" + PICTURE +"'" + "," + "'" +
			// code +"'" + ")";

			System.out.println(sql);

			stm.executeUpdate(sql);
			System.out.println("Insert�����I�I");

		} catch (SQLException e) {
			System.out.println("H2�ɐڑ��ł��܂���ł����B3");
		} finally {
		}

	}

	 /**
	  * MySQL�@���@H2 �ւ̓o�^�����C���|�[�g
	  * @param rs MySQL�iSELECT��)�̌��ʂ��擾
	  */
	public void My_inport(ResultSet rs) {

		try {

			// MySQL�̓o�^�����P�s���擾
			while (rs.next()) {

				sql = "INSERT INTO " + "A" + ID + " VALUES(" + rs.getInt("fileid")
						+ ",'" + rs.getString("filename") + "','"
						+ rs.getString("filepass") + "','"
						+ rs.getString("picture") + "',"
						+ rs.getString("keycode") + ","
						+ rs.getString("keycount") + ",'"
						+ rs.getString("comand") + "')";

				stm.executeUpdate(sql);

			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
		}

	}

	// �I�����ꂽ�t�@�C�����N������@���\�b�h
	public static void Fileboot(int row) {

		try {
			// Select���̌��ʂ���
			ResultSet rs = Select();

			// �Ώۂ̃t�@�C�����N���ϐ�
			Runtime rt = Runtime.getRuntime();

			// �I�����ꂽ�t�@�C���̃C���f�b�N�X�ԍ����w��
			if (rs.absolute(row + 1)) {
				PASS = rs.getString(3); // �t�@�C���̃p�X���擾
				if( PASS == "" ){
					ClipboardCopy(rs.getString(2));
				} else {
					EXE = new String("explorer " + PASS); // �N������EXE�ɑ��

					try {
						rt.exec(EXE); // �w�肳�ꂽ�t�@�C�����R�R�ŋN��
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		} catch (SQLException e) {
			System.out.println("H2�ɐڑ��ł��܂���ł����B5");
		} finally {

		}
	}

	// �e�[�u���̏���Ԃ� ���\�b�h
	public Object[][] Listdisplay() {

		try {
			// Select���̌��ʂ���
			ResultSet rs = Select();

			// �z��̔Ԓn �ϐ�
			int count = 0;

			// ���߂ă��O�C�����ꂽ
			boolean flg = false;

			// DB�̃e�[�u���̏���S�ēǂݍ��ނ܂�
			while (rs.next()) {

				flg = true;

				// �t�@�C�����E�t�@�C���p�X�E�A�C�R���̃p�X�@������
				FILEID = String.valueOf(rs.getInt(1));
				NAME = rs.getString(2);
				PASS = rs.getString(3);
				PICTURE = rs.getString(4);
				COMAND = rs.getString(7);

				String tmp = main.path + "/bin/" + NAME + ".png";

				// �e�[�u���p�z��֑��
				str[count] = new Object[] { new ImageIcon(BigIcon(tmp)),
						FILEID, NAME, PASS, COMAND };

				// �Ԓn+1
				count++;

				// �f�o�b�O�p
				System.out.println("�擾���� -> " + FILEID + "  " + NAME + "     "
						+ PASS + "     " + COMAND);
			}

			if (flg == false) {
				for (int i = 0; i < 20; i++) {
					str[i] = new Object[] { new ImageIcon(""), "", "", "", "" };
				}
			}
			
			
		} catch (SQLException e) {
			System.out.println("H2�ɐڑ��ł��܂���ł����B6");
		} finally {

		}
		return str;
	}

	/**
	 * �w�肳�ꂽ Image�@�^ �̃A�C�R���摜��Ԃ��B
	 *
	 * @param icon
	 *            �Ώۂ̃A�C�R���摜
	 * @return bigImg �Ώۂ̃A�C�R���摜�T�C�Y�@�~�@2 �傫���@
	 */
	private Image BigIcon(String icon) {
		// �I�����ꂽ�A�C�R���̃p�X����
		ImageIcon icon1 = new ImageIcon(icon);

		MediaTracker tracker = new MediaTracker(main.list);

		// getScaledInstance�ő傫����ύX���܂��B
		Image bigImg = icon1.getImage().getScaledInstance(
				(int) (icon1.getIconWidth() * 2.0), -1, Image.SCALE_SMOOTH);

		// MediaTracker�ŏ����̏I����҂��܂��B
		tracker.addImage(bigImg, 2);
		return bigImg;
	}

	//
	public static ResultSet Select() {
		ResultSet rs = null;
		try {
			stm = con.createStatement();
			sql = "select * from " + "A" + ID  + " order by fileid";
			rs = stm.executeQuery(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return rs;
	}
	
	public int getKeyCount( int fileid ){
		ResultSet rs = null;
		int keycount = 0;
		try {
			sql = "SELECT keycount from A" + ID + " WHERE fileid=" + fileid;
			rs = stm.executeQuery(sql);
			rs.next();
			
			keycount = rs.getInt("keycount");				

		} catch (SQLException e) {
			System.out.println("�`�`�`�`�`�`H2�N���X��getKeyCount���\�b�h�ŃG���[�����`�`�`�`�`�`");
			e.printStackTrace();
		}
	
			return keycount;
	}

	// �I�����ꂽ�t�@�C�����폜����@���\�b�h
	public void Delete(int[] row, int count) {

		// ��xDB�̏����擾
		ResultSet rs = Select();

		try {

			// �X�e�[�g�����g����
			stm = con.createStatement();

			// �t�@�C��ID���i�[����ϐ�
			String fileid = null;

			// �t�@�C��ID�̊i�[�z��@�J�E���g
			int i = 0;

			// �폜������܂�
			while (i < count) {

				if (rs.absolute((row[i]) + 1)) {
					fileid = String.valueOf(rs.getInt(1)); // �t�@�C��ID ���擾
				}

				// �Ώۃt�@�C��ID���폜���� SQL��
				sql = "DELETE FROM " + "A" + ID + " WHERE fileid='" + fileid + "'";
				stm.executeUpdate(sql);

				i++; // �J�E���g + 1
			}

			System.out.println("Delete�����I�I");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("H2�ɐڑ��ł��܂���ł����B7");
		} finally {

		}

	}

	// �I�����ꂽ�t�@�C�����폜����@���\�b�h
	public static void Delete2(String pass) {

		// ��xDB�̏����擾
		ResultSet rs = Select();

		try {

			// �X�e�[�g�����g����
			stm = con.createStatement();

			// �t�@�C��ID���i�[����ϐ�
			String fileid = null;

			// �t�@�C��ID�̊i�[�z��@�J�E���g
			int i = 0;

			// �Ώۃt�@�C��ID���폜���� SQL��
			sql = "DELETE FROM " + "A" + ID + " WHERE filepass='" + pass + "'";
			stm.executeUpdate(sql);

			i++; // �J�E���g + 1

			System.out.println("Delete�����I�I");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("H2�ɐڑ��ł��܂���ł����B7");
		} finally {

		}

	}

	// �V���[�g�J�b�g�L�[��ύX����
	public void update_key(int keycode, int keycount, int fileid, String comand) {
		try {
			// UPDATE ��
			sql = "UPDATE " + "A" + ID + " SET KEYCODE=" + keycode + ", KEYCOUNT="
					+ keycount + ", comand=" + comand + " WHERE fileid="
					+ fileid;
			stm.executeUpdate(sql);

			System.out.println("UPDATE�����I�I");

		} catch (SQLException e) {
			System.out.println("UPDATE�ł��܂���ł����B");
		} finally {
		}
	}

	// �������ύX�i�X�V�j����
	public void update_String(int keycode, int keycount, int fileid,
			String comand, String str) {
		try {
			// UPDATE ��
			sql = "UPDATE " + "A" + ID + " SET KEYCODE=" + keycode + ", KEYCOUNT="
					+ keycount + ", filename=" + "'" + str + "'" + ", comand="
					+ comand + " WHERE fileid=" + fileid;
			stm.executeUpdate(sql);

			System.out.println("UPDATE�����I�I");

		} catch (SQLException e) {
			System.out.println("UPDATE�ł��܂���ł����B");
		} finally {
		}
	}

	public static void passChange(String password) {
		sql = "ALTER USER " + "A" + ID + " SET PASSWORD '" + password + "'";

		try {
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}
	}

	/**
	 * �@�t�@�C���ꗗ����t�@�C�������݂��邩
	 *
	 * @param files
	 *            �e�f�B���N�g�����̃t�@�C���ꗗ
	 */
	private static void CheakFileList(File[] files) {
		// �t�@�C�������@�t���O
		flg = false;

		// �f�B���N�g��������
		for (int i = 0; i < files.length; i++) {

			// 1�̃t�@�C���p�X���擾
			File file = files[i];
			String tmp = String.valueOf(file);

			// �f�B���N�g�����Ƀt�@�C�������݂��邩
			if (PASS.equals(tmp)) {
				flg = true;
			}
		}

		// �t�@�C�������݂��Ȃ������ꍇ
		if (flg == false) {

			// �t�@�C�������݂��Ȃ������̂ŁA��������
			H2.Delete2(PASS); // ���̃t�@�C�������폜

			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "�u" + NAME
					+ "�v�@\n�t�@�C���ړ� ���� �폜���ꂽ�̂ŁA�f�[�^�x�[�X����폜���܂����B");

			main.list.getlatest(); // �ŐV�̏����X�V

		}

	}

	/**
	 * �o�^���ꂽ�t�@�C�����i�[�悪�����Ă��邩�m�F����
	 */
//	public static void Cheak() {
//
//		try {
//			// Select���̌��ʂ���
//			ResultSet rs = Select();
//
//			// �t�@�C���T���@�I�u�W�F�N�g����
//			//fileSearch search = new fileSearch();
//
//			// DB�̃e�[�u���̏���S�ēǂݍ��ނ܂�
//			while (rs.next()) {
//
//				// �t�@�C�����E�t�@�C���p�X�E�A�C�R���̃p�X�@������
//				NAME = rs.getString(2);
//				PASS = rs.getString(3);
//				System.out.println(PASS);
//
//				if (!list.isEmpty(PASS)) {
//
//					int index = PASS.lastIndexOf("\\"); // �t�@�C���p�X����e�f�B���N�g�����擾����ۂ�index
//					String oya = PASS.substring(0, index);// �t�@�C���̐e�f�B���N�g�����擾
//
//					// �t�H���_�ł͂Ȃ���
//					if (!getSuffix(NAME).equals(NAME)) {
//
//						// �e�f�B���N�g������t�@�C����T��
//						File[] files = search.listFiles(oya, "*."
//								+ getSuffix(NAME));
//						CheakFileList(files);
//						search.clear();
//					} else {
//
//						// �e�f�B���N�g������t�H���_��T��
//						File[] files = search.listFiles(oya, NAME,
//								search.TYPE_DIR, true, 0);
//						CheakFileList(files);
//						search.clear();
//					}
//
//				} else {
//				}
//			}
//
//		} catch (SQLException e) {
//			System.out.println("H2�ɐڑ��ł��܂���ł����B6");
//		} finally {
//
//		}
//
//	}

	/**
	 * �w�肳�ꂽ String�@�^ �̊g���q��Ԃ��B
	 *
	 * @param fileName
	 *            �`�F�b�N���� �t�@�C����
	 * @return fileName .�ȍ~ �̊g���q��Ԃ��B
	 */
	public static String getSuffix(String fileName) {
		if (fileName == null)
			return null;
		int point = fileName.lastIndexOf(".");
		if (point != -1) {
			return fileName.substring(point + 1);
		}
		return fileName;
	}

	//�����Ă�������filename�̒l���N���b�v�{�[�h�ɃR�s�[���郁�\�b�h
	private static Clipboard ClipboardCopy(String filename){
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection selection = new StringSelection(filename);
		clipboard.setContents(selection, selection);
		return clipboard;
	}
}