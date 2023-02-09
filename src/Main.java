import pianist.Finger;
import pianist.Music;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException, AWTException {
        Thread.sleep(3000);
//        Music mus = new Music("不将就左右手2.mus");
//        mus.play();
        Finger.setBpm(72);//96(不将就) 72(不将就)
        Music mus2 = new Music("不将就左右手2.mus");
        mus2.play();
        //Music mus_2 = new Music("不将就左手.mus");
        //Thread rh = new pianist.Hand(mus);
        //Thread lh = new pianist.Hand(mus_2);
        //rh.start();
        //lh.start();
    }
}