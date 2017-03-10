import java.util.concurrent.*;
import java.util.logging.*;

import org.jnativehook.*;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 * JNativeHook�̃��M���O��}������B
 *
 * main�ň�x�ݒ肵�Ă����̂��Ƃ�JNativeHook���ŃZ�b�g�������ۂ��̂ŕʃX���b�h��2�b�҂��ăZ�b�g�B
 */

public class Key implements NativeKeyListener {
	private int KeySum = 1;			//������Ă���L�[�R�[�h�̍��v�l��ۑ�
	private int LastKey = 0;		//���݉����Ă���L�[�R�[�h�̕ۑ�
	private int KeyCount = 0;		//���݉�����Ă���L�[�̐����J�E���g����
//	private TimerDialog TD = new TimerDialog();
//	private Thread thread = new Thread(TD);
//	private int KeyCode = 0;		//�o�^����L�[�R�[�h��ۑ�
//	private int KeyCnt = 0;			//�o�^����L�[�̐���ۑ�
//	private String KeyName;
//	private boolean OCFlag = false;
	
	public Key(){
		// �t�b�N���ĂȂ�������t�b�N
        if (!GlobalScreen.isNativeHookRegistered()) {
            try {
                GlobalScreen.registerNativeHook();
            } catch (NativeHookException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

        // ���O��}�����ăn���h����o�^
        suppressLogger();
        GlobalScreen.addNativeKeyListener(this);
        //GlobalScreen.getInstance().addNativeKeyListener(new Main());

	}
	
	
	public void KeyEnable(){
		// �t�b�N���ĂȂ�������t�b�N
        if (!GlobalScreen.isNativeHookRegistered()) {
            try {
                GlobalScreen.registerNativeHook();
            } catch (NativeHookException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

        // ���O��}�����ăn���h����o�^
        suppressLogger();
        GlobalScreen.addNativeKeyListener(this);
        //GlobalScreen.getInstance().addNativeKeyListener(new Main());

	}
	
    // �L�[�������ꂽ
    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
    //�u �V���[�g�J�b�g�L�[���g�p���Ȃ� �v�Ƀ`�F�b�N�������Ă���Ώ������s��Ȃ�	
    	if( LastKey != e.getKeyCode() ){
    		if(e.getKeyCode() == 1){
    			LastKey = 1;
    			KeyCount++;
        		list.setKeyCheckBox();		//Esc�L�[�������ꂽ��KeyCheckBox�̒l��؂�ւ���
        		//TD.setOnOff(!list.getKeyCheckBox());
        		//Thread thread = new Thread(TD);
        		//thread.start();
    		}
    		else if(!list.getKeyCheckBox() && !list.getDialogFlag()){
    			//System.out.println("KeyPre!!!");
//    			if( LastKey != e.getKeyCode() ){
    				KeyCount++;					//���݉�����Ă���L�[�̐���1���₷
    				LastKey = e.getKeyCode();	//�Ō�ɉ������L�[�R�[�h�̍X�V
    			
    				//Shift,Ctrl,Alt�L�[�̉E����������Ă�����A������������Ă���悤�ɐ؂�ւ���
    				if(NativeKeyEvent.VC_SHIFT_R == LastKey){
    					LastKey = NativeKeyEvent.VC_SHIFT_L;
    				}
    				else if(NativeKeyEvent.VC_CONTROL_R == LastKey){
    					LastKey = NativeKeyEvent.VC_CONTROL_L;
    				}
    				else if(NativeKeyEvent.VC_ALT_R == LastKey){
    					LastKey = NativeKeyEvent.VC_ALT_L;
    				}

    				KeySum *= LastKey;			//�����Ă���L�[�R�[�h�̍��v�l���X�V

    				//System.out.println("������܂���");
    				//KeyCount++;
    				//System.out.println("KeyPressed:" + NativeKeyEvent.getKeyText(e.getKeyCode()) + "�������ꂽ");
    				//System.out.println("KeyCode=" + e.getKeyCode());

    				System.out.println("PreLastKey=" + LastKey);	//�f�o�b�O
    				System.out.println("PreKeySum=" + KeySum);		//�f�o�b�O
    				H2.select_key(KeySum, KeyCount);				//H2�ɃA�N�Z�X���āA�������̂��������(�S��)�Ăяo��
//    			}
    		}
    	}
    }

    // �L�[�������ꂽ
    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
    //�u �V���[�g�J�b�g�L�[���g�p���Ȃ� �v�Ƀ`�F�b�N�������Ă���Ώ������s��Ȃ�	
//    	if(!list.getKeyCheckBox() && !list.getDialogFlag()){
    		KeyCount=0;			//���݉�����Ă���L�[�̐���1���炷
    		//	System.out.println("������܂���");
    		//	System.out.println("KeyReleased:" + NativeKeyEvent.getKeyText(e.getKeyCode()) + "�������ꂽ");	//e.paramString()
    		//	KeySum /= e.getKeyCode();
    		LastKey = 0;
    		//	System.out.println("ReLastKey=" + LastKey);		//�f�o�b�O
    		//	System.out.println("ReKeySum=" + KeySum);		//�f�o�b�O
    		
    			//�������񋭐��I�ɗ�������KeySum��1�ɂ���
    				KeySum = 1;
//    	}
    }

    // �L�[���^�C�v����
    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        //System.out.println("KeyTyped:" + NativeKeyEvent.getKeyText(e.getKeyCode()) + "�������ꂽ");
    }

    
    public void suppressLogger() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1, r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });

        executorService.schedule(() -> {
            final Logger jNativeHookLogger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            if (jNativeHookLogger.getLevel() != Level.WARNING) {
                synchronized (jNativeHookLogger) {
                    jNativeHookLogger.setLevel(Level.WARNING);
                }
            }
        }, 2, TimeUnit.SECONDS);
    }

}
