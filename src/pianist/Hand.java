package pianist;

import java.awt.*;
import java.io.IOException;

public class Hand extends Thread{
    private Music music;
    public Hand(Music music){
        this.music=music;
    }

    @Override
    public void run(){
        try {
            music.play();
        } catch (InterruptedException | AWTException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
