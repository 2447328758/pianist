package util;


import java.io.FileWriter;
import java.io.IOException;

public class test {
    int[] lnames={
            1,5,2,5,4,3,6,4,5,1,4,//22
            4,5,1,7,6,3,4,5,1,5,4,1,//24
            5,2,1,5,1,4,5,2,1,7,6,3,//24
            5,2,1,5,1,5,4,1,5,2,1,7,6,3,3,7,//32
            4,5,1,7,6,3,3,7,4,1,5,2,5,5,5,//30
            1,5,1,5,1,5,1,5,4,1,4,1,4,1,4,1,2,6,2,6,2,6,2,6,5,2,5,2,5,2,5,2,//64
            3,7,3,7,3,7,3,7,6,3,6,3,5,3,5,3,4,1,4,4,2,4,5,2,5,2,5,2,5,2,//60
            1,5,1,5,1,5,1,5,4,1,4,1,4,1,4,1,2,6,2,6,2,6,2,6,5,2,5,2,5,2,5,2,//64
            3,7,3,6,4,4,5,1,5,1,5,1,//24
            4,5,1,2,1,//10
    };
    int[] llevels={
            1,1,1,1,1,1,1,1,2,1,2,//22
            2,2,1,2,2,2,2,2,1,1,2,1,//24
            2,1,1,1,1,2,2,1,1,2,2,1,//24
            2,1,1,1,1,1,2,1,2,1,1,2,2,1,2,2,//32
            2,2,1,2,2,1,2,2,2,1,2,1,2,2,3,//30
            1,1,0,1,0,1,0,1,2,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,2,1,1,1,1,1,1,1,//64
            2,2,1,2,1,2,1,2,2,1,1,1,2,1,1,1,2,1,1,2,1,1,2,1,1,1,2,1,1,1,//60
            1,1,0,1,0,1,0,1,2,1,1,1,1,1,1,1,2,2,0,1,0,1,0,1,2,1,1,1,1,1,1,1,//64
            2,2,1,2,2,2,2,1,1,0,1,0,//24
            2,2,2,2,2,//10
    };
    int[] ltimes = {
            8,8,8,4,4,8,8,8,8,8,8,//22
            4,4,4,4,8,8,4,4,4,4,8,8,//24
            4,4,4,4,8,8,8,8,4,4,4,4,//24
            4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,//32
            4,4,4,4,4,4,4,4,4,4,4,4,4,4,8,//30
            2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,//64
            2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,4,2,2,4,2,2,2,2,2,2,2,2,//60
            2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,//64
            2,2,12,8,8,8,8,2,2,2,2,8,//24
            8,8,16,16,16,//10
    };

    int[] rnames = new int[]{
            3,5,2,1,3,4,6,3,2,6,5,3,2,1,3,4,5,3,2,3,5,6,3,2,1,//50

            2,2,2,5,3,1,3,5,6,3,2,1,2,1,2,5,3,3,2,1,7,1,1,7,6,5,3,//54

            6,5,5,4,5,3,3,2,1,7,1,1,7,6,5,1,6,1,5,3,5,6,3,2,1,//50

            2,2,2,5,3,1,3,5,6,3,2,1,2,1,2,5,3,3,2,1,7,1,1,7,6,5,3,//52 少了一个(已修正 54)

            6,5,5,4,5,3,3,2,1,7,1,1,7,6,5,1,6,1,7,6,6,7,//44

            3,5,2,1,2,3,6,1,2,3,4,6,4,3,2,1,7,1,2,5,//40

            5,7,2,5,7,1,1,2,3,3,2,1,6,6,5,1,2,//34

            3,5,2,1,2,3,6,1,2,3,4,6,4,3,2,1,7,1,2,5,//40

            7,1,2,5,3,3,6,1,1,1,3,6,2,1,7,1,3,6,//36

            2,1,7,1,3,5,2,1,3,4,6,3,2,6,3,//30
    };

    int[] rlevels = new int[]{
            0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,//50
            0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0,1,0,0,0,0,//56 多了一个(已修正 54)
            0,0,0,0,0,0,0,0,0,-1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,//50
            0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0,1,0,0,0,0,//54
            0,0,0,0,0,0,0,0,0,-1,0,1,0,0,0,0,0,0,0,-1,-1,-1,//44
            0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,//40
            0,0,1,1,0,1,1,1,1,1,1,1,0,0,0,0,0,//34
            0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,//40
            0,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,0,//36
            1,1,0,1,-1,-1,0,0,-1,-1,-1,0,0,-1,-1,//30
    };

    int[] rtimes = new int[]{
            2,2,2,8,2,2,2,2,8,2,2,2,2,6,2,4,2,2,8,4,2,4,2,2,2,//50
            2,1,2,2,7,2,4,2,4,2,2,2,2,1,2,2,7,1,1,2,1,2,3,2,1,4,1,//54
            2,1,2,1,1,7,1,1,2,1,2,3,2,1,3,1,7,1,9,4,2,4,2,2,2,//50
            2,1,2,2,7,2,4,2,4,2,2,2,2,1,2,2,7,1,1,2,1,2,3,2,1,4,1,//54
            2,1,2,1,1,7,1,1,2,1,2,3,2,1,3,1,7,1,9,4,4,8,//44
            2,2,2,4,2,2,6,4,4,4,2,2,2,4,2,2,6,4,4,4,//40
            2,2,2,6,2,10,2,2,2,6,2,4,4,4,6,4,4,//34
            2,2,2,4,2,2,6,4,4,4,2,2,2,4,2,2,6,4,4,4,//40
            2,2,2,4,2,2,4,2,2,6,2,6,2,4,4,14,2,6,//36
            2,4,4,2,2,2,2,8,2,2,2,2,6,4,16,//30
    };

    public void test() throws IOException {
        for (int i = 0;i<llevels.length;i++)
            llevels[i]=-llevels[i];
        Mus rmus = new Mus(rnames,rlevels,rtimes,4,false);
        Mus lmus = new Mus(lnames,llevels,ltimes,4,true);
        Mus[] others = new Mus[]{lmus};
        MusFileMaker musMaker = new MusFileMaker(rmus,others);
        FileWriter fw = new FileWriter("不将就左右手2.mus");
        fw.write(musMaker.outMus());
        fw.flush();
    }
    public static void main(String[] args) throws IOException {
        new test().test();
    }
}
