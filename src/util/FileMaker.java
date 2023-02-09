package util;


import pianist.Tone;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 使用数组生成ArrayList<Tone> 最后生成 mus 文件
 */
public class FileMaker {
    private int[][] base=null;
    private boolean base_oppsite;
    private int chushu = 4;
    private int[][][] others;
    private boolean[] others_oppsite;

    public FileMaker(int[][] base, boolean base_oppsite, int[][][] others, boolean[] others_oppsite){
        /*检查是否包含三个必需的元素*/
        if (base.length!=3 || (others != null && others[0] != null && others[0].length != 3))
            throw new RuntimeException("base elem count: %d, others elem count: %d".formatted(base.length,others[0].length));
        this.base=base;
        this.others=others;
        this.base_oppsite=base_oppsite;
        this.others_oppsite=others_oppsite;
    }


    public void generateFile(String path) throws IOException {
        FileWriter fw = new FileWriter(path);
        Mus base = new Mus(this.base,this.chushu,this.base_oppsite);
        ArrayList<Tone> baseTones = base.generateTones();
        ArrayList[] others_arrlist = null;
        int othersPointer=0;
        if (this.others !=null){
            others_arrlist = new ArrayList[this.others.length];
            for (int i = 0; i<others.length; i++){
                if (this.others[i]==null)
                    continue;//如果数组中有空指针则跳过
                others_arrlist[othersPointer++]=new Mus(this.others[i],this.chushu,this.others_oppsite[i]).generateTones();//把空指针放在最后
            }
        }
        String outMus = new MusFileMaker(baseTones,others_arrlist).outMus();
        fw.write(outMus);
        fw.flush();
        fw.close();
    }

}
