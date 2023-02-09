package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileAnalyze {
    public static void main(String[] args) throws IOException {
        AnalyzeDiff("不将就左右手.mus", "不将就左右手2.mus");
    }

    private static void AnalyzeDiff(String src,String dst) throws IOException {
        FileReader src_fr = new FileReader(src);
        FileReader dst_fr = new FileReader(dst);
        BufferedReader src_br=new BufferedReader(src_fr);
        BufferedReader dst_br=new BufferedReader(dst_fr);
        String temp1,temp2;
        int lineId=0;
        while((temp1=src_br.readLine())!=null && (temp2=dst_br.readLine())!=null){
            if (!temp1.replace(" ","").equals(temp2.replace(" ",""))){
                System.out.printf("in lint %d:not matches%n", ++lineId);
                System.out.printf("%s\t:%s%n", src,temp1);
                System.out.printf("%s\t:%s%n", dst,temp2);
                System.out.println("\n\n");
            }else
                System.out.println(++lineId);
        }
    }
}
