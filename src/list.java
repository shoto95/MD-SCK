import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class list extends JFrame implements DropTargetListener {
	private static final long serialVersionUID = 1L;
	// �t�@�C��ID �E�t�@�C�����E�t�@�C���p�X���i�[����z��
	public Object[][] tabledata = { { new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" },
			{ new ImageIcon(""), "", "", "", "" } };

	// ���� �z��
	private String[] columnNames = { "�A�C�R��", "�t�@�C��ID", "�t�@�C����/������", "�t�@�C���p�X","�R�}���h" };
	JTable Table;
	static JTable Table2;
	JScrollPane scroll;
	private DefaultTableModel tableModel;
	// H2 �ڑ��ϐ�
	public Connection con = null;
	// �I�����ꂽ�t�@�C����
	private String value = null;
	// �t�@�C���I��ϐ�
	private JFileChooser chooser;
	// �A�C�R���p�X �ϐ�
	private ImageIcon icon1 = null;
	public static H2 h2;
	public static JCheckBox KeyCheckBox;
	private static boolean DialogFlag = false; // �L�[�o�^�E�ύX�̃_�C�A���O���\�����̓L�[����͍s��Ȃ����߂̃t���O
	// �L�[�ݒ�_�C�A���O
	private KeyDialog KDlg = new KeyDialog(this);
	// ������_�C�A���O
	private StringDialog SDlg = new StringDialog(this);
	// �R�}���h�ۑ��ϐ��@
	private String comand1 = "";
	private String comand2 = "";
	public static Button_frame Bframe;
	// �I�����ꂽ�s��fileid��ۑ����邽�߂̕ϐ�
	private int IndexID;
	// �I�����ꂽ�s��filename��filepath��ۑ����邽�߂̕ϐ�
	private String FileName = null;
	private String FilePath = null;
	int count = 0;
	int[] index;
	static JButton RegistB;
	static JButton ChangeB;
	static JButton StartB;
	static JButton BackupB;
	static JButton ImportB;
	static JLabel lblNewLabel;
	class MyTableModel extends DefaultTableModel {
		MyTableModel(Object[][] objects, String[] columnNames) {
			super(objects, columnNames);
		}
		public Class getColumnClass(int col) {
			return getValueAt(0, col).getClass();
		}
	}

	public list() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			// �I�����ꂽ�t�@�C���̏����i�[
			chooser = new JFileChooser();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// getContentPane
		setTitle("\u4E00\u89A7\u8868");
		this.setSize(new Dimension(731, 511));
		this.setLocationRelativeTo(null);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		getContentPane().setLayout(null);

		// ���͂��ꂽ�A�J�E���g�������O�C��
		h2 = new H2(user.Userstr[0].toString(), user.Userstr[1].toString());
		SDlg.setH2(h2);
		KDlg.setH2(h2);
		
		// �f�o�b�O�p
		System.out.println(user.fast);
		
		// ���O�C���������Ƃ̂Ȃ�ID����͂��ꂽ�ꍇ
		if (user.fast == true) {
			H2.Create(); // �Ώ�ID�̃f�[�u�����쐬
		}

		Bframe = new Button_frame(h2.Listdisplay()); // �{�^���N���t���[���@����
		Bframe.setBounds(760, 0, 490, 200);
		Bframe.setlatest(h2.Listdisplay()); // �����X�V
		Bframe.setVisible(true); // �{�^���N���t���[���@�\��		
		latest(); // �ŐV�̏��@�X�V

		Table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*
				 * �N���b�N���ꂽ�l���擾�@�@1:���N���b�N 3 �E�N���b�N
				 */
				int btn = arg0.getButton();
				// ���N���b�N���ꂽ��
				if (btn == MouseEvent.BUTTON1) {
					Table2 = Table = (JTable) arg0.getSource();// �I�����ꂽ����table�ɑ��
					int[] index = Table.getSelectedRows();
					value = (String) tabledata[index[0]][1];
					FileName = (String) Table.getValueAt(Table.getSelectedRow(), 1);
					// �I�����ꂽ�Z���́Anull��
					if (!(isEmpty(value))) {
//						System.out.println("*�f�o�b�O*�Fvalue=" + value);
						IndexID = Integer.parseInt(value);
					} else {
						IndexID = 0;
						latest();
					}
					// �E�N���b�N�@�i�폜�����j�@
				} else if (btn == MouseEvent.BUTTON3) {
					Table2 = Table = (JTable) arg0.getSource(); // �I�����ꂽ����table�ɑ��
					// �I�����ꂽ���@�ϐ�
					int count = Table.getSelectedRowCount();
					// �I�����ꂽ�C���f�b�N�X�ԍ� �z��
					int[] index = Table.getSelectedRows();
					boolean blankflg = false;
					for (int i = 0; i < index.length; i++) {
						if (!tabledata[index[i]][1].equals("")) {
							blankflg = true;
						}
					}
					if (blankflg == true) {
						// �m�F��ʂ�\��
						JFrame kakunin = new JFrame();
						// ��������̂����f����ϐ�
						int option = JOptionPane.showConfirmDialog(kakunin,"�{���ɑI�����ꂽ���̂��폜���Ă�낵���ł��傤���B", "�m�F���",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
						// �m�F��ʂ�OK���ꂽ�ꍇ
						if (option == 0) {
							h2.Delete(index, count); // �폜����
						}
						// �I�����ꂽ����0�ł͂Ȃ��ꍇ
						while (count != 0) {
							tabledata[index[count - 1]] = new Object[] {new ImageIcon(""), "", "", "", "" };
							for (int i = count; !(tabledata[i + 1].equals(null))&& i < 10; i++) { // �s��O�ɋl�߂鏈��
								tabledata[i] = tabledata[i + 1]; // �N���A���ꂽ�s�ɑ}��
								tabledata[i + 1] = new Object[] {new ImageIcon(""), "", "", "", "" }; // �}�������s���N���A
								Table.clearSelection(); // �I����Ԃ��N���A
							}
							count--; // �I�����ꂽ�� -1������
						}
					}
					latest(); // �ŐV�̏��ɍX�V
				}
			}
		});
		StartB = new JButton("");
		StartB.setBackground(UIManager.getColor("Button.background"));
		StartB.setOpaque(false);
		StartB.setContentAreaFilled(false);
		StartB.setIcon(new ImageIcon(main.path + "\\�ꗗ�\\\path7651.png"));
		StartB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				h2.Fileboot(Table.getSelectedRow()); // �I�����ꂽ�t�@�C�����N��
			}
		});
		StartB.setBounds(9, 22, 139, 47);
		getContentPane().add(StartB);
		RegistB = new JButton("");
		RegistB.setBackground(Color.BLACK);
		RegistB.setOpaque(false);
		RegistB.setContentAreaFilled(false);
		RegistB.setIcon(new ImageIcon(main.path + "\\�ꗗ�\\\path7652.png"));
		RegistB.addActionListener(new ActionListener() {
			// ������o�^�̏���
			public void actionPerformed(ActionEvent arg0) {
				DialogFlag = true;
				SDlg.setTextField("");
				SDlg.setDialog();
				SDlg.setInformation(false);
				SDlg.setVisible(true);
				if (SDlg.getOkFlag()) {
					h2.insert_String(SDlg.getTextField(), SDlg.getKCode(),SDlg.getKCnt(), SDlg.getKName());
					latest();
				}
				DialogFlag = false;
			}
		});
		RegistB.setBounds(9, 79, 139, 37);
		getContentPane().add(RegistB);
		ChangeB = new JButton("");
		ChangeB.setContentAreaFilled(false);
		ChangeB.addActionListener(new ActionListener() {
			// ������ύX����
			public void actionPerformed(ActionEvent arg0) {
				// �󔒂̍s��I�����ꂽ�Ƃ�
				if (IndexID == 0) {
					JOptionPane.showConfirmDialog(null, "�I�����ꂽ�s�ɂ̓f�[�^�����݂��܂���B","�f�[�^�Ȃ�", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
				} else if (tabledata[Table.getSelectedRow()][3] != "") { // �t�@�C���p�X�̏ꏊ���󔒂���Ȃ������ꍇ
					// �����̃L�[�ύX�݂̂̃_�C�A���O��\��
					DialogFlag = true;
					//KDlg.setFileName(FileName);
					KDlg.setDialog(Table.getSelectedRow(), IndexID, FileName);
					KDlg.setVisible(true);
					if (KDlg.getOCFlag()) {
						h2.update_key(KDlg.getKeyCode(), KDlg.getKeyCnt(),IndexID, KDlg.getKeyName());
						latest();
					}
					DialogFlag = false;
				} else {
					// ������p�ύX�_�C�A���O�̕\��
					DialogFlag = true;
					//SDlg.setTextField(FileName);
					//�����ɕύX����s�̓o�^����Ă���L�[�̏����_�C�A���O�ɃZ�b�g���郁�\�b�h�������i�Ȃ̂�StringDialog�Ƀ��\�b�h���쐬����j
					SDlg.setDialog( Table.getSelectedRow(), IndexID, FileName );
					SDlg.setInformation(true, IndexID);
					SDlg.setVisible(true);
					if (SDlg.getOCFlag()) {
						h2.update_String(SDlg.getKCode(), SDlg.getKCnt(),IndexID, SDlg.getKName(), SDlg.getTextField());
						latest();
					}
					DialogFlag = false;
				}
			}
		});
		ChangeB.setBounds(9, 126, 139, 37);
		getContentPane().add(ChangeB);

		ImportB = new JButton("");
		ImportB.setContentAreaFilled(false);
		ImportB.setOpaque(false);
		ImportB.setIcon(new ImageIcon(main.path + "\\�ꗗ�\\\path765.png"));
		ImportB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MySQL mysql = new MySQL();
				h2.My_inport(mysql.My_import(H2.ID, H2.PASSWARD));
				latest();
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "�u" + H2.ID + "�v�@�l�̃C���|�[�g�������������܂����B");
			}
		});
		ImportB.setBounds(9, 173, 139, 47);
		getContentPane().add(ImportB);

		BackupB = new JButton("");
		BackupB.setContentAreaFilled(false);
		BackupB.setIcon(new ImageIcon(main.path + "\\�ꗗ�\\\path76523.png"));
		BackupB.setBackground(UIManager.getColor("Button.background"));
		BackupB.addActionListener(new ActionListener() {
			//�o�b�N�A�b�v�̏���
			public void actionPerformed(ActionEvent arg0) {
//				JOptionPane.showConfirmDialog(null, "�o�b�N�A�b�v��...",
//						"�o�b�N�A�b�v", JOptionPane.DEFAULT_OPTION,
//						JOptionPane.ERROR_MESSAGE);
//				
//				MySQL mysql = new MySQL(h2.ID, h2.PASSWARD);
//				mysql.My_backup(h2.ID, h2.PASSWARD,h2.Select());
//				JFrame frame = new JFrame();
//
//				JOptionPane.showMessageDialog(frame, "�u" + h2.ID
//						+ "�v�@�l�̃o�b�N�A�b�v�������������܂����B");

				///////////////////////////////////////////////////////
//				final JDialog d = new JDialog();
//				JLabel DispLabel = new JLabel("<html>�f�[�^�x�[�X�ɓo�^���ł�<br>���o�^�����ɂ͏������Ԃ�������ꍇ������܂�</br></html>");
//				d.add(DispLabel);
//				d.pack();
//				d.setSize(300, 200);
//				d.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
//				d.setModalityType(ModalityType.APPLICATION_MODAL);
//				d.setLocationRelativeTo(null);
//				d.setVisible(true);
//				new Thread(new Runnable() {
//					public void run() {
//						//...���Ԃ̊|���鏈���������ɋL�q...
//						System.out.println("Thread����");
//						StartB.setEnabled(false);
//						RegistB.setEnabled(false);
//						ChangeB.setEnabled(false);
//						ImportB.setEnabled(false);
//						BackupB.setEnabled(false);
//						DialogFlag = true;
//						try {
//							Thread.sleep(5000);
//						} catch (InterruptedException e) {
//							// TODO �����������ꂽ catch �u���b�N
//							e.printStackTrace();
//						}
//						MySQL mysql = new MySQL(h2.ID, h2.PASSWARD);
//						mysql.My_backup(h2.ID, h2.PASSWARD,h2.Select());
//						d.setVisible(false);
//						d.setModalityType(ModalityType.APPLICATION_MODAL);
//						DispLabel.setText("�f�[�^�x�[�X�ւ̓o�^���������܂���");
//						d.setVisible(true);
//						
//						JOptionPane.showMessageDialog(null, "�f�[�^�x�[�X�ւ̓o�^���������܂���");
//						
//						
//						
//						StartB.setEnabled(true);
//						RegistB.setEnabled(true);
//						ChangeB.setEnabled(true);
//						ImportB.setEnabled(true);
//						BackupB.setEnabled(true);
//						DialogFlag = false;
//						SwingUtilities.invokeLater(new Runnable() {
//							public void run() {
//								//d.setVisible(false);
//								d.dispose();
//							}
//						});
//					}
//				}).start();

				
				///////////////////////////////////////////////////////

			
			//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
			
				final JDialog d = new JDialog((JFrame)null, true);
				final JProgressBar pb = new JProgressBar();
				
				//pb.setValue(0);
				d.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				//d.setSize(300, 200);
				//d.setSize(300, 200);
				//pb.setSize(300, 200);
				
				pb.setIndeterminate(true);
				pb.setStringPainted(true);
				
				d.add(pb);
				d.pack();
				d.setSize(300, 100);
				d.setLocationRelativeTo(null);

				d.addWindowListener(new WindowAdapter() {
					@Override
					public void windowOpened(WindowEvent e) {
						new Thread(new Runnable() {
							public void run() {
//								SwingUtilities.invokeLater(new Runnable() {
//									public void run() {
//										d.setTitle("�f�[�^�x�[�X�T�[�o�ɐڑ���");
//										pb.setString("���΂炭���҂���������...");
//									}
//								});
								//...���Ԃ̊|����ǂݍ��ݏ����������ɋL�q...
								
								
								SwingUtilities.invokeLater(new Runnable() {
									public void run() {
										d.setTitle("�f�[�^�x�[�X�T�[�o�ɐڑ���");
										pb.setString("���΂炭���҂���������...");
									}
  								});
								//...���Ԃ̊|����o�^�����������ɋL�q...
								System.out.println("Thread����");
								StartB.setEnabled(false);
								RegistB.setEnabled(false);
								ChangeB.setEnabled(false);
								ImportB.setEnabled(false);
								BackupB.setEnabled(false);
								DialogFlag = true;

								MySQL mysql = new MySQL(h2.ID, h2.PASSWARD);
								//System.out.println("����");
								if( mysql.getSuccessFlag()/*��̃R���X�g���N�^�̌��ʂ��܂������Ȃ������ꍇ�A���̏����͍s�킸�u���s���܂����v�ƕ\������*/ ){
									mysql.My_backup(h2.ID, h2.PASSWARD, h2.Select());
								}
								//System.out.println("�I���");
								//�����ɏ��������������Ƃ���̏ꏊ�֏�����
								//JOptionPane.showMessageDialog(null, "�f�[�^�x�[�X�ւ̓o�^���������܂���");
								
								
								
								StartB.setEnabled(true);
								RegistB.setEnabled(true);
								ChangeB.setEnabled(true);
								ImportB.setEnabled(true);
								BackupB.setEnabled(true);
								DialogFlag = false;

								SwingUtilities.invokeLater(new Runnable() {
									public void run() {
										d.setVisible(false);
										if( mysql.getSuccessFlag() ){
											JOptionPane.showMessageDialog(null, "�f�[�^�x�[�X�ւ̓o�^���������܂���");
										} else {
											JOptionPane.showMessageDialog(null, "�f�[�^�x�[�X�ւ̓o�^�Ɏ��s���܂���");
										}
										d.dispose();
									}
								});
							}
						}).start();
					}
				});
				
				d.setVisible(true);

			
			
			
			
			
			
			//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
			
			
			
			
			}
		});
		BackupB.setBounds(9, 230, 142, 37);
		getContentPane().add(BackupB);
		KeyCheckBox = new JCheckBox("\u30B7\u30E7\u30FC\u30C8\u30AB\u30C3\u30C8\u30AD\u30FC\u3092\u4F7F\u7528\u3057\u306A\u3044");
		KeyCheckBox.setForeground(Color.YELLOW);
		KeyCheckBox.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		KeyCheckBox.setOpaque(false);
		KeyCheckBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				main.KeyCheckBox.setSelected(KeyCheckBox.isSelected());
			}
		});
		KeyCheckBox.setBounds(0, 420, 215, 21);
		getContentPane().add(KeyCheckBox);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Java\\kadai\\workspace\\\u30B0\u30EB\u30FC\u30D7\\\u4E00\u89A7\u8868\\\u4E00\u89A7\u8868\u80CC\u666F.png"));
		lblNewLabel.setBounds(-77, -123, 1098, 789);
		getContentPane().add(lblNewLabel);
		list.StartB.setIcon(new ImageIcon(main.path + "\\�ꗗ�\�Q\\�N��.png"));
		list.RegistB.setIcon(new ImageIcon(main.path + "\\�ꗗ�\�Q\\������o�^.png"));
		list.ChangeB.setIcon(new ImageIcon(main.path + "\\�ꗗ�\�Q\\�ύX.png"));
		list.ImportB.setIcon(new ImageIcon(main.path + "\\�ꗗ�\�Q\\�C���|�[�g.png"));
		list.BackupB.setIcon(new ImageIcon(main.path + "\\�ꗗ�\�Q\\�o�b�N�A�b�v.png"));
		// list.CloseB.setIcon(new ImageIcon(main.path + "\\�ꗗ�\�Q\\����R.png"));
		list.lblNewLabel.setIcon(new ImageIcon(main.path + "\\�ꗗ�\�Q\\�ꗗ�\�w�i.png"));
	}

	private void setAutoResizeMode(int autoResizeOff) {}

	/**
	 * dsadas
	 */
	public void latest() {
		tableModel = new MyTableModel(h2.Listdisplay(),columnNames);
		// ��x�N������Ă��邩
		if (main.cnt) {
		} else {
			Table2.setModel(tableModel); // �e�[�u���ɉ摜�����Z�b�g
			Table2.removeColumn(Table2.getColumn("�t�@�C���p�X")); // �t�@�C���p�X���ڂ��\��
			Table2.removeColumn(Table2.getColumn("�t�@�C��ID")); // �t�@�C���p�X���ڂ��\��
			Bframe.setlatest(h2.Listdisplay()); // �����X�V
		}	

		Table = new JTable(tableModel); // �e�[�u���𐶐�
		tabledata = h2.Listdisplay(); // �ŐV�̏����@�e�[�u����
		Table.removeColumn(Table.getColumn("�t�@�C���p�X")); // �t�@�C���p�X���ڂ��\��
		Table.removeColumn(Table.getColumn("�t�@�C��ID")); // �t�@�C���p�X���ڂ��\��
		Table.setRowHeight(50); // �Z���̍����� 50 �ɃZ�b�g
		
		scroll = new JScrollPane(Table);
		scroll.setBounds(179, 3, 536, 438);
		getContentPane().add(scroll);

		Table.setRowSelectionAllowed(true);
		Table.getTableHeader().setResizingAllowed(false);
		Table.setDefaultEditor(Object.class, null);
		Table.setAutoCreateRowSorter(false);
		if(main.cnt){
			Table2=Table;
			main.cnt = false;
		}
		new DropTarget(Table, DnDConstants.ACTION_COPY_OR_MOVE, this);
	}

	@Override
	public void dragEnter(DropTargetDragEvent arg0) {}

	@Override
	public void dragExit(DropTargetEvent arg0) {}

	@Override
	public void dragOver(DropTargetDragEvent arg0) {}

	@Override
	public void drop(DropTargetDropEvent dtde) {
		dtde.acceptDrop(DnDConstants.ACTION_MOVE);
	//	latest();
		try {
			// �h���b�O���ꂽ�t�@�C�������擾
			Transferable tran = dtde.getTransferable();
			// �t�@�C�����X�g�Ƀh���b�O���ꂽ�t�@�C�����i�[
			List<?> fileList = (List<?>) tran.getTransferData(DataFlavor.javaFileListFlavor);
			Iterator<?> iterator = fileList.iterator();
			while (iterator.hasNext()) {
				// �h���b�O���ꂽ�t�@�C����������擾
				File file = (File) iterator.next();
				// �t�@�C�����ϐ��@������
				WindowsShortcut ws = null;
				String filePath = null;
				String fileName = null;
				try {
					// �t�@�C�������i�[
					String kaku = file.getName();
					// �t�@�C���g���q���擾
					kaku = getSuffix(kaku);
					// �g���q�� �ulnk�v �̏ꍇ
					if (kaku.equals("lnk")) {
						ws = new WindowsShortcut(file); // �t�@�C���̃����N��̃t�@�C�����擾
						filePath = ws.getRealFilename(); // �����N��̃t�@�C���p�X������
						int index = filePath.lastIndexOf("\\"); // �t�@�C���p�X����t�@�C�������擾����ۂ�index
						fileName = filePath.substring(index + 1);// �t�@�C�������擾
					} else {
						filePath = file.getAbsolutePath(); // �t�@�C���p�X���擾
						fileName = file.getName(); // �t�@�C�������擾
					}
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				// �L�[�ݒ�_�C�A���O�̐ݒ�ƕ\��
				DialogFlag = true;
				KDlg.setFileName(fileName);
				KDlg.setVisible(true);
				DialogFlag = false;
				// DB�փf�[�^�}��
				ImageIcon icon = (ImageIcon) chooser.getIcon(file);
				String ext = main.path + "\\bin\\" + fileName + ".png";
				// �摜�t�@�C���ۑ�
				ImageIO.write((BufferedImage) icon.getImage(), "png", new File(ext));
				// �t�@�C������H2DB�ɑ}��
				if (KDlg.getOCFlag()) {
					h2.insert(fileName, filePath, ext, KDlg.getKeyCode(),KDlg.getKeyCnt(), KDlg.getKeyName());
				}
			}
			dtde.dropComplete(true); // �h���b�v����������
			latest(); // �ŐV�̏��ɍX�V
		} catch (Exception e) {
			e.printStackTrace();
			dtde.dropComplete(false);
		}
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent arg0) {}

	/**
	 * �w�肳�ꂽ String �̊g���q��Ԃ��B
	 *
	 * @param value
	 *            �`�F�b�N���� �t�@�C���̃p�X
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

	/**
	 * �w�肳�ꂽ String �� null �܂��͋󕶎��񂩂ǂ�����Ԃ��܂��B
	 *
	 * @param value
	 *            �`�F�b�N���� String
	 * @return null �܂��͋󕶎��񂩂ǂ����Bnull �܂��͋󕶎���Ȃ� true �A����ȊO�Ȃ� false �B
	 */
	public static boolean isEmpty(String value) {
		if (value == null || value.length() == 0)
			return true;
		else
			return false;
	}
	public void getlatest() {
		index = Table.getSelectedRows();
		int j = index.length;
		while (j != 0) {
			tabledata[index[j - 1]] = new Object[] { "", "", "", "", "" }; // �t�@�C�������N���A
			for (int i = j; !(tabledata[i + 1].equals(null)) && i < 20; i++) { // �s��O�ɋl�߂鏈��
				tabledata[i] = tabledata[i + 1]; // �N���A���ꂽ�s�ɑ}��
				tabledata[i + 1] = new Object[] { "", "", "", "", "" }; // �}�������s���N���A
				Table.clearSelection(); // �I����Ԃ��N���A
			}
			j--; // �I�����ꂽ�� -1������
		}
	}

	// ���݃`�F�b�N�������Ă��邩�ǂ������擾����
	public static boolean getKeyCheckBox() {
		return KeyCheckBox.isSelected();
	}

	// �`�F�b�N�������Ă���Ȃ�͂����A�����Ă��Ȃ��Ȃ�����
	public static void setKeyCheckBox() {
		KeyCheckBox.setSelected(!getKeyCheckBox());
	}

	public static boolean getDialogFlag() {
		return DialogFlag;
	}

	public Object[][] getTableData() {
		return tabledata;
	}

}