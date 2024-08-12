package cn.langya;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final List<String> texts = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5000 ; i++) {
            String apiText = WebUtils.get("https://international.v1.hitokoto.cn/?encode=text");
            System.out.println(apiText);
            add(apiText);
            // 1000ms = 1s
            Thread.sleep(100);
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(String.format("采集到 %s 条数据",texts.size()));
        }
        writeListToFile(texts,"C:\\Users\\Administrator\\IdeaProjects\\GetNetEaseAPIText\\result.txt");
    }

    public static void add(String text) {
        if (text == null || texts.contains(text)) return;
        texts.add(text);
    }

    public static void writeListToFile(List<String> list, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : list) {
                writer.write(line);
                // don't need newLine
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}