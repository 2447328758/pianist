package pianist;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Music {



    String musFile;
    ArrayList<Tone> music;
    public Music(){
        music=new ArrayList<>();
    }
    public Music(String path) throws IOException {
        this();
        this.musFile = path;
        this.readFile(path);
    }

    public void readFile(String path) throws IOException {
        Pattern p = Pattern.compile("([a-g]) ([-0-3]{1,2}) (\\d+\\.?\\d*?);");
        Matcher matcher = p.matcher("");

        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        Tone tone = null;
        char name;
        int level;
        double time;
        //todo 更改文件的解析过程
        System.out.println("name:\tlevel:\ttime:");
        String line;
        while ((line = br.readLine())!= null){
//            matcher.reset(br.readLine());
//            while (matcher.find()){
//                name = matcher.group(1).charAt(0);
//                level = Integer.parseInt(matcher.group(2));
//                time = Double.parseDouble(matcher.group(3));
//                System.out.println("%c\t%d\t%f".formatted(name,level,time));
//                if (tone == null)
//                    tone = new Tone(name,level,time);
//                else
//                    tone.addTone(new Tone(name,level,time));
//            }
//            music.add(tone);
//            tone=null;
            music.add(createTone(line));
            System.out.println();
        }
        br.close();
        fr.close();
    }
    public void play() throws IOException, AWTException, InterruptedException {
        if (music.isEmpty())
            throw new RuntimeException("没有设置乐谱文件");
        for (Tone t:music
             ) {
            t.voice();
        }
    }


    private static Tone createTone(String exp){
        Pattern p = Pattern.compile("([a-g0]) ([-0-3]{1,2}) (\\d+\\.?\\d*?);");
        Matcher matcher = p.matcher("");

        char name;
        int level;
        double time;

        String[] tone_elem = exp.split(":");
        System.out.println(Arrays.deepToString(tone_elem));
        Tone temp = null,last = null;
        for (int i=tone_elem.length-1; i>=0; i--){
            matcher.reset(tone_elem[i]);
            while (matcher.find()){
                name = matcher.group(1).charAt(0);
                level = Integer.parseInt(matcher.group(2));
                time = Double.parseDouble(matcher.group(3));
                if (temp==null)
                    temp = new Tone(name,level,time);
                else
                    temp.addTone(new Tone(name,level,time));
            }

            if (last!=null)
                temp.addTone(last);
            last = temp;
            temp = null;
        }
        return last;
    }
}
