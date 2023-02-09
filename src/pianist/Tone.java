package pianist;

import java.util.ArrayList;

public class Tone {
    enum Name{
        ZERO,C,D,E,F,G,A,B
    }
    public static Tone Standard_Tone;
    static {
        Standard_Tone=new Tone('0',0,2);
    }
    public char name;
    public int level;
    public double time;
    public ArrayList<Tone> otherTone;
    private Finger finger;
    public Tone(){
        finger=new Finger();
    }
    public Tone(char name, int level, double time) {
        this();
        this.name = name;
        this.level = level;
        this.time = time;
    }

    public void addTone(Tone tone){
        if (otherTone ==null)
            otherTone =new ArrayList<>();
        otherTone.add(tone);
    }

    public void voice() throws InterruptedException {
        Thread thread = null;
        if (otherTone !=null){
            thread = new Thread(()->{
                for (Tone t : otherTone) {
                    try {
                       t.voice();
                    } catch (InterruptedException e) {
                        System.out.println("\n\n\n\n");
                        throw new RuntimeException(e);
                    }
                }
            });

        }
        if (thread!=null)
            //thread.start();
            Thread.startVirtualThread(thread);//开启协程
        finger.Press(this);
    }

    @Override
    public String toString() {
        return "%c%d %f\n".formatted(name,level,time);
    }
}
