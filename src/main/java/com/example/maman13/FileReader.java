package com.example.maman13;

import java.io.*;
import java.util.Random;


public  class FileReader {
    public static String pickWord(){
        Random rand = new Random();
        File file = new File("src/main/resources/com/example/maman13/wordsBank.txt");
        int sup = rand.nextInt(1,7);
        int inf = 0;
        String stringHolder = "";

        try(InputStream in = new FileInputStream(file))
        {
            int content;
            while ((content = in.read()) != -1) {
                if ((char)content == '\n' ){
                    inf++;
                    if (inf == sup){
                        return stringHolder;
                    }
                    else{
                        stringHolder = "";
                        continue;
                    }
                }
                stringHolder += (char)content;

            }
            return stringHolder;
        }catch (IOException e){
            e.printStackTrace();
            return  null;
        }
    }
}
