package demo;

import application.MemoryUserApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Emoji {

    private ArrayList<MyMessage> MyGetMessages(String input) throws IOException {

        // 创建一个用于匹配方括号内字符的正则表达式模式
        Pattern pattern = Pattern.compile("\\[([^\\[\\]]+)\\]");
        // 将模式应用到输入字符串上，创建一个标准匹配器
        Matcher matcher = pattern.matcher(input);

        // 创建一个用于存储匹配结果的 ArrayList
        ArrayList<MyMessage> matches = new ArrayList<>();
        // 定义字符串索引变量，初始值为 0
        int lastIndex = 0;
        // 指定表情图片存储路径
        String dizhi = "D:/86155/QQ/表情包/静态/";

        // 使用循环查找输入字符串中的所有匹配项
        while (matcher.find()) {
            // 如果当前匹配项起始索引大于上一个匹配项结束索引，则说明当前匹配项与上一个匹配项之间存在普通文本，
            // 将其封装为一个 MyMessage 对象并添加到 matches 中
            if (matcher.start() > lastIndex) {
                matches.add(new MyMessage(0, (input.substring(lastIndex, matcher.start())).getBytes()));
            }

            // 从当前匹配项中提取表情名
            String FileName = matcher.group(1);
            // 如果找到表情图片，则读取相应文件的内容作为字节数组，并封装为 MyMessage 对象添加到 matches 中
            if (FilePan(FileName)) {
                File file = new File(dizhi + FileName);
                System.out.println(file);
                matches.add(new MyMessage(1, Files.readAllBytes(file.toPath())));
            }
            // 如果未找到对应的表情图片，则说明当前匹配项为普通文本，封装为 MyMessage 对象添加到 matches 中
            else {
                matches.add(new MyMessage(0, input.substring(matcher.start(), matcher.end()).getBytes()));
            }

            // 更新 lastIndex，将其设置为当前匹配项的结束索引，以便找到下一个匹配项时确定之前的普通文本部分
            lastIndex = matcher.end();
        }

        // 添加最后一段普通文本到 matches 中
        if (lastIndex < input.length()) {
            matches.add(new MyMessage(0, input.substring(lastIndex).getBytes()));
        }

        // 遍历 matches，输出其中的每个 MyMessage 对象
        for (int i = 0; i < matches.size(); i++) {
            MyMessage match = matches.get(i);
            String segment = new String(match.getBytes());
            int shu = match.getLeixing();
        }

        // 返回所有匹配项和普通文本的 MyMessage 对象列表
        return matches;
    }
    public static boolean FilePan(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}