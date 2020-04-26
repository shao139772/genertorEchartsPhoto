package com.miaomiao;

import java.io.*;
import java.util.UUID;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: http://www.ubisys.com.cn/</p>
 *
 * @Auther: cw
 * @Date: 2020/1/21 17:35
 */
public class GenertorEchartsPic {


    private static final String JSpath = "D:\\phantomjs-2.1.1-windows\\echarts-convert\\echarts-convert.js";

    public static void main(String[] args) {

        String options = "{\"title\":{\"text\":\"销售图\",\"subtext\":\"销售统计\",\"x\":\"CENTER\"},\"toolbox\": {\"feature\": {\"saveAsImage\": {\"show\": true,}}},\"tooltip\": {\"show\": true},\"legend\": {\"data\":[\"直接访问\",\"邮件营销\",\"联盟广告\",\"视频广告\",\"搜索引擎\"]}, \"series\":[{\"name\":\"访问来源\",\"type\":\"pie\",\"radius\": '55%',\"center\": ['50%', '60%'],\"data\":[{\"value\":335, \"name\":\"直接访问\"},{\"value\":310, \"name\":\"邮件营销\"},{\"value\":234, \"name\":\"联盟广告\"},{\"value\":135, \"name\":\"视频广告\"},{\"value\":1548, \"name\":\"搜索引擎\"}]}]}";
        String picPath = generateEChart(options);

    }

    /*
     * 主程序
     */
    public static String generateEChart(String options) {
        String dataPath = writeFile(options);
        String fileName = UUID.randomUUID().toString() + ".png";
        String path = "D:/" + fileName;
        try {
            File file = new File(path);     //文件路径
            if (!file.exists()) {
                File dir = new File(file.getParent());
                dir.mkdirs();
                file.createNewFile();
            }
            String cmd = "phantomjs " + JSpath + " -infile " + dataPath + " -outfile " + path;//生成命令行
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = input.readLine()) != null) {
            }
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return path;
    }

    /*
     *
     * options生成文件存储
     */
    public static String writeFile(String options) {
        String dataPath = "D:\\" + UUID.randomUUID().toString().substring(0, 8) + ".json";
        try {
            /* option写入文本文件 用于执行命令*/
            File writename = new File(dataPath);
            if (!writename.exists()) {
                File dir = new File(writename.getParent());
                dir.mkdirs();
                writename.createNewFile(); //
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write(options);
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后关闭文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataPath;
    }
}
