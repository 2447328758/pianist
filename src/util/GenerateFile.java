package util;

import pianist.Tone;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateFile {
    static char[] map = {'c','d','e','f','g','a','b'};
    public static void main(String[] args) throws IOException {
        //FileWriter fw = new FileWriter("不将就右手.mus");
//        FileWriter fw = new FileWriter("不将就右手_2.mus");
//        Generate(fw);
//        fw.flush();
//        fw.close();
//        System.out.println((int)'a');
        addRightHand();
    }

    private static void Generate(FileWriter fw) throws IOException {
        char name;
        int level;
        double time;
        int[] names = new int[]{
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

        int[] levels = new int[]{
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

        int[] times = new int[]{
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




        System.out.println(names.length+" "+levels.length);
//        for (int i=0;i<names.length;i++){
//            fw.write(" %c %d %f\r\n".formatted(map[names[i]-1],levels[i],(double)times[i]/4));
//        }
        int total = 0;
        int i;
        for (i=0;i<names.length;i++){
            if (levels[i]==-1 && levels[i+1]==-1 && levels[i+2]==-1)
                break;
            total+=times[i];
        }
        fw.write(" 0 0 %f\r\n".formatted((double)total/4));
        System.out.println(i);
        i+=3;
        total=0;
        while(i<names.length-1){
            total+=times[i];
            i++;
        }
        fw.write(" 0 0 %f\r\n".formatted((double)total/4));
    }

    private static void addRightHand() throws IOException {
        String path="不将就右手_2.mus";
        ArrayList<Tone> tones=new ArrayList<>();
        int[] names={
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
        int[] levels={
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
        int[] times = {
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

        System.out.println("%d %d %d".formatted(names.length,levels.length,times.length));

        for (int i=0;i<names.length;i++){
            tones.add(new Tone(map[names[i]-1],-levels[i],(double)times[i]/4));
        }

        System.out.printf("伴奏数据初始化成功，数量：%d%n", tones.size());


        Pattern p = Pattern.compile("([a-g]) ([-0-3]{1,2}) (\\d+\\.?\\d*?);");
        Matcher matcher = p.matcher("");

        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter("不将就左右手.mus");


        //key 表示该行开始的时间
        //value 表示该行的内容
        ArrayList<Double> key=new ArrayList<>();
        ArrayList<String> value=new ArrayList<>();

        double time = 0;

        Tone nextTone;
        nextTone=tones.get(0);
        double needTime = 0;
        String lastLine=null;
        double lastTotal=0;
        System.out.println("total:\tneedTime:");
        /*while (br.read()!=-1){
            String line=br.readLine();
            if (lastLine==null)
                lastLine=line;
            matcher.reset(line);

            System.out.println("%f\t%f".formatted(total,needTime));

            if (total >= needTime){
                if (total==needTime)
                    fw.write(" "+line.concat(":%c %d %f\r\n".formatted(nextTone.name,nextTone.level,nextTone.time)));
                else if (total>needTime){
                    fw.write(" %s :0 0 %f; %c %d %f\r\n".formatted(lastLine,,nextTone.name,nextTone.level,nextTone.time));
                }
                total=0;
                needTime=nextTone.time;
                tones.remove(nextTone);
                nextTone = tones.get(0);
            }else {
                fw.write(line+"\r\n");
            }

            if (matcher.find()){
                time = Double.parseDouble(matcher.group(3));
                total+=time;
            }
            lastLine=line;
        }*/

        double total=0;
        String line;
        while(br.read()!=-1){
            line=br.readLine();
            matcher.reset(line);
            key.add(total);
            value.add(line);
            if (matcher.find())
                total+=Double.parseDouble(matcher.group(3));
        }

        time=0;

        Tone t1,t2;
        //Iterator<Map.Entry<Double,String>> entries = record.entrySet().iterator();
        Iterator<Tone> toneIterator = tones.iterator();
        //Map.Entry<Double,String> lastEntry=entries.next();
        Map.Entry<Double,String> entry;
        String outLine;
        double nextDelay = 0;
        int pointer=1,lastPointer=0;
        double k;
        String v;

        while (pointer<key.size()){
            k=key.get(pointer);
            v=value.get(pointer);
            outLine=value.get(lastPointer).concat(" :");
            while(toneIterator.hasNext()){
                t1=toneIterator.next();
                time+=t1.time;
                /**
                 * 如果时间已经超过该 tone 结束的时间
                 */
                if (time >= k){
                    //如果 时间延迟 不为 0,就在前面延迟 nextDelay 时间;
                    if (nextDelay!=0){
                        outLine = outLine.concat(" 0 0 %f;".formatted(nextDelay));
                        nextDelay=0;
                    }

                    /**
                     * 延迟后 加上需要添加的 tone
                     */
                    outLine = outLine.concat(" %c %d %f;\r\n".formatted(t1.name,t1.level,t1.time));
                    /**
                     * 设置 Delay ，如果为 0 即不需要 时延
                     */
                    nextDelay=time-k;

                    /**
                     * 如果时延超过 下一个 tone 所需要的时间，则 （pointer 后移 && outLine 加上跳过的 tone 数据） until Delay < 下一个 tone 所需要的时间
                     */
                    while(pointer+1<key.size() && nextDelay>=key.get(pointer+1)-key.get(pointer)){
                        nextDelay-=key.get(pointer+1)-key.get(pointer);
                        outLine = outLine.concat(value.get(pointer)+"\r\n");
                        pointer++;
                    }

//                    pointer=pointer-1;
                    break;

                }else {
                    /**
                     * 如果时间没有超过 该 tone 结束的时间
                     */
                    //如果 时间延迟 不为 0,就在前面延迟 nextDelay 时间;
                    if (nextDelay!=0){
                        outLine = outLine.concat(" 0 0 %f;".formatted(nextDelay));
                        nextDelay=0;
                    }
                    /**
                     * 在 outLine 后加上 tone 的 数据
                     */
                    outLine = outLine.concat(" %c %d %f;".formatted(t1.name,t1.level,t1.time));
                }
            }
            /**
             * 把 outLine 写入的文件
             */
            fw.write(outLine);
            //todo 分隔符
            //fw.write("\r\n");
            //设置 lastPointer = pointer;
            lastPointer=pointer;
            pointer++;
        }


        fw.flush();
        fw.close();
        br.close();
        fr.close();

        if (tones.isEmpty())
            System.out.println("全部添加");
        else
            System.out.printf("未完全添加，剩余：%d%n", tones.size());
    }

    private static Tone createTone(String exp){
        Pattern p = Pattern.compile("([a-g]) ([-0-3]{1,2}) (\\d+\\.?\\d*?);");
        Matcher matcher = p.matcher("");

        char name;
        int level;
        double time;

        String[] tone_elem = exp.split(":");
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
