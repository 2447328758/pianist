package util;

import pianist.Tone;

import java.util.ArrayList;
/**
 * 使用ArrayList<Tone>生成文件
 */
public class MusFileMaker {

    private ArrayList<Tone> base;
    private ArrayList<Tone>[] others;


    public MusFileMaker(ArrayList<Tone> base, ArrayList<Tone>[] others){
        if (base==null)
            throw new RuntimeException("Null Pointer of base");
        this.base=base;
        this.others=others;
    }
    public MusFileMaker(Mus base, Mus[] others){
        this.base = base.generateTones();
        this.others = new ArrayList[others.length];
        ArrayList<Tone> temp;
        for (int i=0;i< others.length;i++){
            this.others[i]=others[i].generateTones();
        }
    }

    public String outMus(){
        return makeMus(base,others);
    }

    public static String makeMus(ArrayList<Tone> base,ArrayList<Tone>[] tones){
        ArrayList<String> bases = createAppend(null,base);
        if (tones!=null){
            ArrayList<String> temp;
            for(ArrayList<Tone> t : tones){
                if (t==null)
                    continue;//跳过空元素
                temp = createAppend(base,t);
                int i= Math.min(bases.size(), temp.size());
                while (--i>=0){
                    if (!temp.get(i).equals(""))
                        bases.set( i, bases.get(i).concat(" :" + temp.get(i)));
                }
                //把多余的temp中的字符串添加进去 ,createAppend已经保证了 temp.size() <= bases.size();
                for (i= bases.size(); i< temp.size(); i++){
                    bases.add(temp.get(i));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s : bases)
            sb.append("%s\r\n".formatted(s));
        return sb.toString();
    }


    /**
     *
     * @param base
     * @param append
     * @return 需要添加的字符串
     */
    public static ArrayList<String> createAppend(ArrayList<Tone> base,ArrayList<Tone> append){
        ArrayList<String> result = new ArrayList<>();
        Tone tone = null;
        if (base==null)/*生成append的字符串序列*/
        {
            for (Tone t: append)
                result.add(" %c %d %f;".formatted(t.name,t.level,t.time));
        }
        else /*根据base生成append的字符串序列*/
        {
            double time=0,delay=0;
            int bp,ap;
            bp=ap=0;


            while(bp<base.size() && ap < append.size())//当两个都没有遍历完的时候
            {
                if (time >= base.get(bp).time) {
                    delay = time - base.get(bp).time;//设置延时
                    time-=base.get(bp).time;
                    if (bp >= result.size())
                        result.add("");//添加一个空串
                    bp++;//bp指后移
                    if (bp == base.size())//如果base的长度不够了
                        base.add(Tone.Standard_Tone);//向base中添加一个没有声音的空音符;
                }
                else if (time < base.get(bp).time) {
                    //无需操作 继续添加音符,ap++; append 返回 append[ap].time
                    tone = append.get(ap++);
                    time += tone.time;

                    if (delay != 0) {
                        if (result.size() <= bp)
                            result.add(" 0 0 %f;".formatted(delay));//如果bp不存在,,则向后添加;
                        else
                            result.set(bp, result.get(bp) + " 0 0 %f;".formatted(delay));//如果bp已经存在,则重新设置;
                        delay = 0;
                    }
                    if (result.size() <= bp)
                        result.add(" %c %d %f;".formatted(tone.name, tone.level, tone.time));//如果bp不存在,,则向后添加;
                    else
                        result.set(bp, result.get(bp) + " %c %d %f;".formatted(tone.name, tone.level, tone.time));//如果bp已经存在,则重新设置;
                }
            }

            /*
            *   while(bp<base.size() && ap < append.size())//当两个都没有遍历完的时候
            *   {
            *       if time >= base[bp].time:
            *           delay = time - base[bp].time;//设置延时
            *           bp++;//bp指后移
            *           if bp == base.size()://如果base的长度不够了
            *               base.add(Tone.Standard_Tone);//向base中添加一个没有声音的空音符;
            *       else time < base[bp].time
            *           time += append(append,bp,&ap);//无需操作 继续添加音符,ap++; append 返回 append[ap].time
            *   }
            * */
        }


        return result;
    }
}
