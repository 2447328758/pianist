package pianist;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class Finger {
    public static int bpm = 72;
    static double beat =  60000.0d/ bpm;
    public Robot robot;
    static HashMap<String, Integer> keyMap;
    static {
        loadConf();
    }

    public Finger(){
        try {
            robot=new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setBpm(int bmp){
        Finger.bpm =bmp;
        Finger.beat=60000.0d/Finger.bpm;
    }



    public static void loadConf(){
        //加载键盘设置
        keyMap=new HashMap<>();

        keyMap.put("g-3",KeyEvent.VK_COMMA);

        keyMap.put("c-2",KeyEvent.VK_Z);
        keyMap.put("d-2",KeyEvent.VK_X);
        keyMap.put("e-2",KeyEvent.VK_C);
        keyMap.put("f-2",KeyEvent.VK_V);
        keyMap.put("g-2",KeyEvent.VK_B);
        keyMap.put("a-2",KeyEvent.VK_N);
        keyMap.put("b-2",KeyEvent.VK_M);


        keyMap.put("c-1",KeyEvent.VK_A);
        keyMap.put("d-1",KeyEvent.VK_S);
        keyMap.put("e-1",KeyEvent.VK_D);
        keyMap.put("f-1",KeyEvent.VK_F);
        keyMap.put("g-1",KeyEvent.VK_G);
        keyMap.put("a-1",KeyEvent.VK_H);
        keyMap.put("b-1",KeyEvent.VK_J);


        keyMap.put("c0",KeyEvent.VK_Q);
        keyMap.put("d0",KeyEvent.VK_W);
        keyMap.put("e0",KeyEvent.VK_E);
        keyMap.put("f0",KeyEvent.VK_R);
        keyMap.put("g0",KeyEvent.VK_T);
        keyMap.put("a0",KeyEvent.VK_Y);
        keyMap.put("b0",KeyEvent.VK_U);


        keyMap.put("c1",KeyEvent.VK_1);
        keyMap.put("d1",KeyEvent.VK_2);
        keyMap.put("e1",KeyEvent.VK_3);
        keyMap.put("f1",KeyEvent.VK_4);
        keyMap.put("g1",KeyEvent.VK_5);
        keyMap.put("a1",KeyEvent.VK_6);
        keyMap.put("b1",KeyEvent.VK_7);

        keyMap.put("c2",KeyEvent.VK_8);
        keyMap.put("d2",KeyEvent.VK_9);
        keyMap.put("e2",KeyEvent.VK_0);
        keyMap.put("f2",KeyEvent.VK_MINUS);
        keyMap.put("g2",KeyEvent.VK_EQUALS);
        keyMap.put("a2",KeyEvent.VK_BACK_QUOTE);
        keyMap.put("b2",KeyEvent.VK_DELETE);

    }

    public void Press(Tone t) throws InterruptedException {
        System.out.println("%c%d".formatted(t.name,t.level));
        if (t.name=='0'){
            Thread.sleep((int)(t.time*beat));
            return;
        }
        int keyCode = keyMap.get("%c%d".formatted(t.name,t.level));
        robot.keyPress(keyCode);
        robot.delay((int)(t.time*beat));
        robot.keyRelease(keyCode);
    }

}
