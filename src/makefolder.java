import java.awt.Label;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;

public class makefolder {

	static boolean Flg = false;
	static boolean Flg2 = false;
	static int cnt = 0;
	static int leng;

	public static void main(String[] args) {
		// �ʂ��
	}

	public makefolder(String Select) {
		File file = new File(System.getProperty("user.home"), "Desktop");
		String Desktop = file.toString();
		
//		File file2 = new File(System.getProperty("user.home"), "Public");
//		String P_Desktop = file2.toString();
		String P_Desktop = "C:\\Users\\Public\\Desktop";
//		P_Desktop = P_Desktop + "\\Desktop";
		System.out.println(P_Desktop);
		
		Flg = true;
		try {
			// path.txt��ǂ�
			File txt = new File(System.getProperty("user.home") + "\\SCK\\path.txt");
			System.out.println(txt);
			
			
			//Private������
			if(!txt.exists()){
				txt.createNewFile();
				FileWriter filewriter = new FileWriter(txt);
				filewriter.write(Desktop_Change.PrivatePath);	
				filewriter.close();
			}
			
			//�v���C�x�[�g���
			BufferedReader br = new BufferedReader(new FileReader(txt));
			String Primal = br.readLine();
			br.close();

			cnt = 0;

			// �t�@�C���ړ��@�@�f�X�N�g�b�v����v���C�x�[�g
			fileMove(Desktop, Primal);
			
			fileMove(P_Desktop,Primal);
			cnt++;
			fileMove(Select, Desktop);

			FileWriter filewriter = new FileWriter(txt);
			// path.txt�ɏ�������
			filewriter.write(Select);
			filewriter.close();

			Flg = false;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void fileMove(String from, String to) throws Exception {
		try {
			File fromdir = new File(from); // �ړ���
			File[] files = fromdir.listFiles(); // �ړ����̃t�@�C���ꗗ
			if (files == null)
				return;
			File todir = new File(to); // �ړ���

			// �t�@�C���ړ�
			for (int i = 0; i < files.length; i++) {

				File moveFile = new File(todir.getPath() + "\\"
						+ files[i].getName());

				files[i].renameTo(moveFile);
			}

		} catch (Exception e) {
			throw e;
		}
	}
}