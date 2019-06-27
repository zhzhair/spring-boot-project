package com.github.zhzhair.propreader.util;

import org.apache.commons.codec.Charsets;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class CensorWordReaderUtil {
    /**
     * 将敏感词放到Set集合中
     * @param path 敏感词文件路径
     * @param wordCount 敏感词个数
     * @return 敏感词集合
     */
    public static Set<String> toSet(String path,Integer wordCount){
        Set<String> set = new HashSet<>(wordCount);//假设有3000个敏感词，初始化容量，避免扩容
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new ClassPathResource(path).getInputStream(), Charsets.UTF_8));
            String line;

            while((line = reader.readLine()) != null) {
                set.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return set;
    }

    /**
     * 从文本中查询是否有铭感词
     * @param text 查询文本
     * @param set 敏感词的集合
     * @return 是否有敏感词
     */
    public static boolean hasCensorWord(String text, Set<String> set){
        for (String string : set) {
            if(text.contains(string)) return true;
        }
        return false;
    }

    /**
     * 从文本中查询铭感词
     * @param text 查询文本
     * @param keyWordSet 敏感词的集合
     * @return 敏感词的集合
     */
    public static Set<String> getCensorWord(String text, Set<String> keyWordSet){
        Set<String> set = new HashSet<>();
        for (String string : keyWordSet) {
            if(text.contains(string)){
                set.add(string);
            }
        }
        return set;
    }

    /**
     * 从文本中查询铭感词
     * @param text 查询文本
     * @param keyWordSet 敏感词的集合
     * @return 敏感词的集合
     */
    public static String getCensorWordText(String text, Set<String> keyWordSet){
        if(!hasCensorWord(text,keyWordSet)) return text;
        Set<String> set = getCensorWord(text,keyWordSet);
        String returnStr = text;
        for (String string : set) {
            returnStr = returnStr.replace(string,getStars(string.length()));
        }
        return returnStr;
    }

    /**
     * 将每隔字符替换成*
     * @param length 长度
     * @return *...
     */
    private static String getStars(int length){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append("*");
        }
        return stringBuilder.toString();
    }
}
