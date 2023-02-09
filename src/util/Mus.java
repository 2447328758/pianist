package util;

import pianist.Tone;

import java.util.ArrayList;

public class Mus {
    public char[] name;
    public int[] level;
    public double[] time;

    private static final char[] mapper = {'0','c','d','e','f','g','a','b'};

    public Mus(int[][] sigle_mus, int chushu, boolean opposite){
        this(sigle_mus[0],sigle_mus[1],sigle_mus[2],chushu,opposite);
    }
    public Mus(int[] name,int[] level, int[] time, int chushu, boolean opposite){
        if (!(name.length==level.length&&level.length==time.length))
            throw new RuntimeException("name:%d level:%d time:%d".formatted(name.length,level.length,time.length));

        this.name= new char[name.length];

        for (int i=0;i<name.length;i++){
            this.name[i]=mapper[name[i]];
        }


        if (!opposite)
            this.level = level;
        else{//如果须要取反，则取反;
            this.level=new int[level.length];
            for (int i = 0 ;i<level.length; i++)
                this.level[i]=-level[i];
        }

        this.time = new double[time.length];
        for (int i=0;i<time.length;i++){
            this.time[i]=(double) time[i]/chushu;
        }
    }
    public ArrayList<Tone> generateTones(){
        ArrayList<Tone> tones = new ArrayList<>();
        int i=0;
        while (i<name.length){
            tones.add(new Tone(name[i],level[i],time[i]));
            i++;
        }
        return tones;
    }
}
