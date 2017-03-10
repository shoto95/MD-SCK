import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class MySQL {
	public static Connection con = null;
	private Connection con1 = null;
	static Statement stm;
	private String sql;
	private ResultSet rs;
	private boolean SuccessFlag = false;

	/**
	 * �Ώۃ��[�U�̃��O�C��������@���\�b�h
	 * 
	 * @param ID
	 *            �Ώۃ��[�U��ID
	 * @param Password
	 *            �Ώۃ��[�U�̃p�X���[�h
	 */
	public MySQL(String ID, String Password) {
		try {
			con = null;
			stm = null;
			rs = null;
			SuccessFlag = false;
			System.out.println("MySQL�FID=" + ID);
			System.out.println("MySQL�FPassword=" + Password);

			System.out.println("MySQL�R���X�g���N�^--------------");
			
			// JDBC�h���C�o�̃��[�h
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("�h���C�o�̃��[�h����");

			// MySQL�ɐڑ�
			con1 = DriverManager.getConnection("jdbc:mysql://192.168.100.103:49523/root", "naka", "123");
			
			  con = DriverManager.getConnection("jdbc:mysql://192.168.100.103:49523/" + ID, "naka", "123");
			  System.out.println("my con�ւ̑��");


//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
//					+ ID, ID, Password);
//			System.out.println("MySQL�ɐڑ��ł��܂����B");

			// MySQL�p�̃X�e�[�g�����g�𐶐�
			stm = con.createStatement();
			System.out.println("my stm�̐���");

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			System.out.println("JDBC�h���C�o�̃��[�h�Ɏ��s���܂����B");
		} catch (SQLException e) { // �Ώۃ��[�U�̃f�[�^�x�[�X����x���쐬����Ă��Ȃ��ꍇ
			System.out.println("catch---");
			
			//con1��null�Ȃ炻���ŃG���[���N������
			if( con1 == null ){
				return;
			}
			
/////////////////////////////////////////////////////////////////���߂č��i�K��MySQL�𐶐�����create�����...�I�I			
			My_create(ID, Password); // ���[�U��DB�E�e�[�u�����쐬
		} finally {
//
//			// �e�[�u�������݂��Ă����̂ŁA�����e�[�u�����폜
//			try {
//				System.out.println("MySQL_finally--------------");
//				
//				// �f�[�^�x�[�X��I��
//				sql = "USE " + ID;
//				System.out.println("�f�[�^�x�[�X�̑I����");
//				stm.execute(sql);
//				System.out.println("���s");
//
//
//				// �Ώۃ��[�U�̃e�[�u�����폜
//				sql = "DROP TABLE " + encryptStr(Password);
//				stm.execute(sql);
//
//				// �V���Ƀe�[�u�����쐬
//				sql = "CREATE TABLE "
//						+ encryptStr(Password)
//						+ " (fileid int ,filename VARCHAR(10000),filepass VARCHAR(255),picture VARCHAR(255), keycode INT, keycount INT,comand VARCHAR(255))";
//				stm.executeUpdate(sql);
//
//			} catch (SQLException e) {
//				// TODO �����������ꂽ catch �u���b�N
//				e.printStackTrace();
//			}

		}

	}

	public MySQL() {
	}

	/**
	 * MySQL�ɑΏۃ��[�U��p�́uDB�v�Ɓu�e�[�u���v���쐬����@���\�b�h
	 * 
	 * @param ID
	 *            �Ώۃ��[�U��ID
	 * @param Password
	 *            �@�Ώۃ��[�U�̃p�X���[�h
	 */
	private void My_create(String ID, String Password) {

		try {
			System.out.println("My_create--------------");
			
			// JDBC�h���C�o�̃��[�h
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("cre �h���C�o�̃��[�h");
			
			// �Ǘ��҂Ƃ��āA�T�[�o�����O�C��
			
			  con = DriverManager.getConnection(
			  "jdbc:mysql://192.168.100.103:49523/root", "naka", "123");
			  System.out.println("cre con�ւ̑��");

//			con = DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/bas", "PHONE1", "PENGUIN");

			System.out.println("MySQL�ɐڑ��ł��܂����B");

			// MySQL�p�̃X�e�[�g�����g�𐶐�
			stm = con.createStatement();
			System.out.println("cre stm����");
			
			// ���[�U�p�̃f�[�^�x�[�X���쐬
			sql = "CREATE DATABASE " +  ID;
			System.out.println("SQL���쐬");
			
			stm.execute(sql);
			System.out.println("create database���s");

			// �쐬�����f�[�^�x�[�X���g�p
			sql = "USE " + ID;
			stm.execute(sql);
			System.out.println("use���s");

			// �Ώۂ̃��[�U�̃e�[�u�����쐬
			sql = "CREATE TABLE "
					+ encryptStr(Password)
					+ " (fileid int ,filename VARCHAR(10000),filepass VARCHAR(255),picture VARCHAR(255), keycode INT, keycount INT,comand VARCHAR(255))";
			stm.executeUpdate(sql);
			System.out.println("table create���s");

//			// ���߂ăo�b�N�A�b�v�����̂ŁA���[�U��ǉ�
//			sql = "GRANT SELECT,INSERT,DELETE ON " + ID + "." + encryptStr(Password) + " TO "
//					+ ID + "@192.168.100.103 IDENTIFIED BY '" + Password + "'";
//			stm.execute(sql);
//			System.out.println("user create���s");

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			System.out.println("JDBC�h���C�o�̃��[�h�Ɏ��s���܂����B");
		} catch (SQLException e) {
			System.out.println(e);

			// �e�[�u�������݂��Ă����̂ŁA�����e�[�u�����폜
			try {
				System.out.println("MySQL_finally--------------");
				
				// �f�[�^�x�[�X��I��
				sql = "USE " + ID;
				System.out.println("�f�[�^�x�[�X�̑I����");
				stm.execute(sql);
				System.out.println("���s");


				// �Ώۃ��[�U�̃e�[�u�����폜
				sql = "DROP TABLE " + encryptStr(Password);
				stm.execute(sql);

				// �V���Ƀe�[�u�����쐬
				sql = "CREATE TABLE "
						+ encryptStr(Password)
						+ " (fileid int ,filename VARCHAR(10000),filepass VARCHAR(255),picture VARCHAR(255), keycode INT, keycount INT,comand VARCHAR(255))";
				stm.executeUpdate(sql);

			} catch (SQLException e1) {
				// TODO �����������ꂽ catch �u���b�N
				e1.printStackTrace();
			}

		} finally {
		}

	}

	/**
	 * H2�̓o�^����MySQL�Ƀo�b�N�A�b�v���郁�\�b�h
	 * 
	 * @param ID
	 *            �g�p���̃��[�UID
	 * @param Mys
	 *            H2�̏����擾���Ă���ϐ�
	 */
	public void My_backup(String ID,String Password ,ResultSet Mys) {

		try {
			System.out.println("My_backup������---------");
			// MySQL�Ƀo�b�N�A�b�v��
			sql = "INSERT INTO " + encryptStr(Password) + " VALUES(?,?,?,?,?,?,?)";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);

			// H2�̓o�^�����P�s���擾
			while (Mys.next()) {
				ps.setInt(1, Mys.getInt("fileid"));
				ps.setString(2, Mys.getString("filename"));
				ps.setString(3, Mys.getString("filepass"));
				ps.setString(4, Mys.getString("picture"));
				ps.setString(5, Mys.getString("keycode"));
				ps.setString(6, Mys.getString("keycount"));
				ps.setString(7, Mys.getString("comand"));

				// �o�b�`�ɂP�s���L��
				ps.addBatch();
			}
			System.out.println("while�I���");
			// �擾�����S�Ă̏���MySQL��
			ps.executeBatch();
			System.out.println("�����I��");
			SuccessFlag = true;
			//JOptionPane.showMessageDialog(null, "�f�[�^�x�[�X�ւ̓o�^���������܂���");

		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(null, "�f�[�^�x�[�X�ւ̓o�^�Ɏ��s���܂���");
			System.out.println(e);
		} finally {
			System.out.println("finally������");
			if (con != null) {
				System.out.println("if�˓��I");
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println("MySQL�̃N���[�Y�Ɏ��s���܂����B");
				}
			}
			System.out.println("finally�I���");
		}
		return;
	}

	public ResultSet My_import(String ID, String Password) {

		try {
			
			// JDBC�h���C�o�̃��[�h
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			

			// MySQL�ɐڑ�
			
//			  con =
//			  DriverManager.getConnection("jdbc:mysql://10.16.226.212:49523/" +
//			  ID, ID, Password);

			con = DriverManager.getConnection("jdbc:mysql://192.168.100.103:49523/root", "naka", "123");


			System.out.println("MySQL�ɐڑ��ł��܂����B");
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
			//		+ ID, ID, Password);
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bas", "PHONE1", "PENGUIN" );
			
			
			System.out.println("MySQL�ɐڑ��ł��܂����B");
			
			// MySQL�p�̃X�e�[�g�����g�𐶐�
			stm = con.createStatement();
			
			// �쐬�����f�[�^�x�[�X���g�p
			sql = "USE " + ID;
			stm.execute(sql);
			

			
			sql = "select * from " + encryptStr(Password) + " order by fileid";
			rs = stm.executeQuery(sql);
			

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			System.out.println("JDBC�h���C�o�̃��[�h�Ɏ��s���܂����B");
		} catch (SQLException e) { // �Ώۃ��[�U�̃f�[�^�x�[�X����x���쐬����Ă��Ȃ��ꍇ
			//My_create(ID, Password); // ���[�U��DB�E�e�[�u�����쐬
		} finally {
		
		}
		
		return rs;
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

	public boolean getSuccessFlag(){
		return SuccessFlag;
	}
	
	
}