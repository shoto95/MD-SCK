import java.util.concurrent.*;
import java.util.logging.*;

import org.jnativehook.*;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 * JNativeHookのロギングを抑制する。
 *
 * mainで一度設定してもそのあとでJNativeHook側でセットされるっぽいので別スレッドで2秒待ってセット。
 */

public class Key implements NativeKeyListener {
	private int KeySum = 1;			//押されているキーコードの合計値を保存
	private int LastKey = 0;		//現在押しているキーコードの保存
	private int KeyCount = 0;		//現在押されているキーの数をカウントする
//	private TimerDialog TD = new TimerDialog();
//	private Thread thread = new Thread(TD);
//	private int KeyCode = 0;		//登録するキーコードを保存
//	private int KeyCnt = 0;			//登録するキーの数を保存
//	private String KeyName;
//	private boolean OCFlag = false;
	
	public Key(){
		// フックしてなかったらフック
        if (!GlobalScreen.isNativeHookRegistered()) {
            try {
                GlobalScreen.registerNativeHook();
            } catch (NativeHookException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

        // ログを抑制してハンドラを登録
        suppressLogger();
        GlobalScreen.addNativeKeyListener(this);
        //GlobalScreen.getInstance().addNativeKeyListener(new Main());

	}
	
	
	public void KeyEnable(){
		// フックしてなかったらフック
        if (!GlobalScreen.isNativeHookRegistered()) {
            try {
                GlobalScreen.registerNativeHook();
            } catch (NativeHookException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

        // ログを抑制してハンドラを登録
        suppressLogger();
        GlobalScreen.addNativeKeyListener(this);
        //GlobalScreen.getInstance().addNativeKeyListener(new Main());

	}
	
    // キーが押された
    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
    //「 ショートカットキーを使用しない 」にチェックが入っていれば処理を行わない	
    	if( LastKey != e.getKeyCode() ){
    		if(e.getKeyCode() == 1){
    			LastKey = 1;
    			KeyCount++;
        		list.setKeyCheckBox();		//Escキーが押されたらKeyCheckBoxの値を切り替える
        		//TD.setOnOff(!list.getKeyCheckBox());
        		//Thread thread = new Thread(TD);
        		//thread.start();
    		}
    		else if(!list.getKeyCheckBox() && !list.getDialogFlag()){
    			//System.out.println("KeyPre!!!");
//    			if( LastKey != e.getKeyCode() ){
    				KeyCount++;					//現在押されているキーの数を1増やす
    				LastKey = e.getKeyCode();	//最後に押したキーコードの更新
    			
    				//Shift,Ctrl,Altキーの右側が押されていたら、左側が押されているように切り替える
    				if(NativeKeyEvent.VC_SHIFT_R == LastKey){
    					LastKey = NativeKeyEvent.VC_SHIFT_L;
    				}
    				else if(NativeKeyEvent.VC_CONTROL_R == LastKey){
    					LastKey = NativeKeyEvent.VC_CONTROL_L;
    				}
    				else if(NativeKeyEvent.VC_ALT_R == LastKey){
    					LastKey = NativeKeyEvent.VC_ALT_L;
    				}

    				KeySum *= LastKey;			//押しているキーコードの合計値を更新

    				//System.out.println("押されました");
    				//KeyCount++;
    				//System.out.println("KeyPressed:" + NativeKeyEvent.getKeyText(e.getKeyCode()) + "が押された");
    				//System.out.println("KeyCode=" + e.getKeyCode());

    				System.out.println("PreLastKey=" + LastKey);	//デバッグ
    				System.out.println("PreKeySum=" + KeySum);		//デバッグ
    				H2.select_key(KeySum, KeyCount);				//H2にアクセスして、同じものが見つかれば(全て)呼び出す
//    			}
    		}
    	}
    }

    // キーが離された
    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
    //「 ショートカットキーを使用しない 」にチェックが入っていれば処理を行わない	
//    	if(!list.getKeyCheckBox() && !list.getDialogFlag()){
    		KeyCount=0;			//現在押されているキーの数を1減らす
    		//	System.out.println("離されました");
    		//	System.out.println("KeyReleased:" + NativeKeyEvent.getKeyText(e.getKeyCode()) + "が離された");	//e.paramString()
    		//	KeySum /= e.getKeyCode();
    		LastKey = 0;
    		//	System.out.println("ReLastKey=" + LastKey);		//デバッグ
    		//	System.out.println("ReKeySum=" + KeySum);		//デバッグ
    		
    			//いったん強制的に離したらKeySumを1にする
    				KeySum = 1;
//    	}
    }

    // キーをタイプした
    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        //System.out.println("KeyTyped:" + NativeKeyEvent.getKeyText(e.getKeyCode()) + "が押された");
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
