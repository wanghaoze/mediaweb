package com.jingjusi.mediaweb.common.utils;
import com.coremedia.iso.IsoFile;

import java.io.IOException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

public class VideoUtil {
    public static void fetchPic(File file, String framefile) throws Exception{
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(file);
        ff.start();
        int lenght = ff.getLengthInFrames();

        File targetFile = new File(framefile);
        int i = 0;
        Frame frame = null;
        while (i < lenght) {
            // 过滤前5帧，避免出现全黑的图片，依自己情况而定
            frame = ff.grabFrame();
            if ((i > 5) && (frame.image != null)) {
                break;
            }
            i++;
        }

        String imgSuffix = "jpg";
        if(framefile.indexOf('.') != -1){
            String[] arr = framefile.split("\\.");
            if(arr.length>=2){
                imgSuffix = arr[1];
            }
        }

        Java2DFrameConverter converter =new Java2DFrameConverter();
        BufferedImage srcBi =converter.getBufferedImage(frame);
        int owidth = srcBi.getWidth();
        int oheight = srcBi.getHeight();
        // 对截取的帧进行等比例缩放
        int width = 800;
        int height = (int) (((double) width / owidth) * oheight);
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        bi.getGraphics().drawImage(srcBi.getScaledInstance(width, height, Image.SCALE_SMOOTH),0, 0, null);
        try {
            ImageIO.write(bi, imgSuffix, targetFile);
        }catch (Exception e) {
            e.printStackTrace();
        }
        ff.stop();
    }

    /**
     * 获取视频时长，单位为秒
     * @param file
     * @return 时长（s）
     */
    public static Long getVideoTime(File file){
        Long times = 0L;
        try {
            FFmpegFrameGrabber ff = new FFmpegFrameGrabber(file);
            ff.start();
            times = ff.getLengthInTime()/(1000*1000);
            ff.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(VideoUtil.class);

    public static Map<String, Object> getScreenshot(String filePath) throws Exception{

        System.out.println("截取视频截图开始："+ System.currentTimeMillis());
        System.out.println(filePath);
        Map<String, Object> result = new HashMap<String, Object>();
        FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(filePath);

        // 第一帧图片存储位置
        String targerFilePath = filePath.substring(0, filePath.lastIndexOf("\\"));
        // 视频文件名
        String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
        // 图片名称
        String targetFileName = fileName.substring(0, fileName.lastIndexOf("."));
        System.out.println("视频路径是：" + targerFilePath);
        System.out.println("视频文件名：" + fileName);
        System.out.println("图片名称是：" + targetFileName);

        grabber.start();
        //设置视频截取帧（默认取第一帧）
        Frame frame = grabber.grabImage();
        //视频旋转度
        String rotate = grabber.getVideoMetadata("rotate");
        Java2DFrameConverter converter = new Java2DFrameConverter();
        //绘制图片
        BufferedImage bi = converter.getBufferedImage(frame);
        if (rotate != null) {
            // 旋转图片
            bi = rotate(bi, Integer.parseInt(rotate));
        }
        //图片的类型
        String imageMat = "jpg";
        //图片的完整路径
        String imagePath = targerFilePath + File.separator + targetFileName + "." + imageMat;
        //创建文件
        File output = new File(imagePath);
        ImageIO.write(bi, imageMat, output);

        //拼接Map信息
        result.put("videoWide", bi.getWidth());
        result.put("videoHigh", bi.getHeight());
        long duration = grabber.getLengthInTime() / (1000 * 1000);
        result.put("rotate", StringUtils.isBlank(rotate)? "0" : rotate);
        result.put("format", grabber.getFormat());
        result.put("imgPath", output.getPath());
        System.out.println("视频的宽:" + bi.getWidth());
        System.out.println("视频的高:" + bi.getHeight());
        System.out.println("视频的旋转度：" + rotate);
        System.out.println("视频的格式：" + grabber.getFormat());
        System.out.println("此视频时长（s/秒）：" + duration);
        grabber.stop();
        System.out.println("截取视频截图结束："+ System.currentTimeMillis());
        return result;
    }

    /**
     * @Description: 根据视频旋转度来调整图片
     * @param src
     * @param angel	视频旋转度
     * @return  BufferedImage
     */
    public static BufferedImage rotate(BufferedImage src, int angel) {
        int src_width = src.getWidth(null);
        int src_height = src.getHeight(null);
        int type = src.getColorModel().getTransparency();
        Rectangle rect_des = calcRotatedSize(new Rectangle(new Dimension(src_width, src_height)), angel);
        BufferedImage bi = new BufferedImage(rect_des.width, rect_des.height, type);
        Graphics2D g2 = bi.createGraphics();
        g2.translate((rect_des.width - src_width) / 2, (rect_des.height - src_height) / 2);
        g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);
        g2.drawImage(src, 0, 0, null);
        g2.dispose();
        return bi;
    }


    /**
     * @Description: 计算图片旋转大小
     * @param src
     * @param angel
     * @return  Rectangle
     */
    public static Rectangle calcRotatedSize(Rectangle src, int angel) {
        if (angel >= 90) {
            if (angel / 90 % 2 == 1) {
                int temp = src.height;
                src.height = src.width;
                src.width = temp;
            }
            angel = angel % 90;
        }
        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
        double angel_dalta_width = Math.atan((double) src.height / src.width);
        double angel_dalta_height = Math.atan((double) src.width / src.height);
        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_width));
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_height));
        int des_width = src.width + len_dalta_width * 2;
        int des_height = src.height + len_dalta_height * 2;
        return new java.awt.Rectangle(new Dimension(des_width, des_height));
    }
    /**
     * 获取视频文件的播放长度(mp4、mov格式)
     * @param videoPath
     * @return 单位为毫秒
     */
    public static long getMp4Duration(String videoPath) throws IOException {
        IsoFile isoFile = new IsoFile(videoPath);
        long lengthInSeconds =
                isoFile.getMovieBox().getMovieHeaderBox().getDuration() /
                        isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
        return lengthInSeconds;
    }


    /**
     * 得到语音或视频文件时长,单位秒
     * @param filePath
     * @return
     * @throws IOException
     */
    public static long getDuration(String filePath) throws IOException {
        String format = getVideoFormat(filePath);
        long result = 0;
        if("wav".equals(format)){
            result = AudioUtil.getDuration(filePath).intValue();
        }else if("mp3".equals(format)){
            result = AudioUtil.getMp3Duration(filePath).intValue();
        }else if("m4a".equals(format)) {
            result = VideoUtil.getMp4Duration(filePath);
        }else if("mov".equals(format)){
            result = VideoUtil.getMp4Duration(filePath);
        }else if("mp4".equals(format)){
            result = VideoUtil.getMp4Duration(filePath);
        }

        return result;
    }

    /**
     * 得到语音或视频文件时长,单位秒
     * @param filePath
     * @return
     * @throws IOException
     */
    public static long getDuration(String filePath,String format) throws IOException {
        long result = 0;
        if("wav".equals(format)){
            result = AudioUtil.getDuration(filePath).intValue();
        }else if("mp3".equals(format)){
            result = AudioUtil.getMp3Duration(filePath).intValue();
        }else if("m4a".equals(format)) {
            result = VideoUtil.getMp4Duration(filePath);
        }else if("mov".equals(format)){
            result = VideoUtil.getMp4Duration(filePath);
        }else if("mp4".equals(format)){
            result = VideoUtil.getMp4Duration(filePath);
        }

        return result;
    }


    /**
     * 得到文件格式
     * @param path
     * @return
     */
    public static String getVideoFormat(String path){
        return  path.toLowerCase().substring(path.toLowerCase().lastIndexOf(".") + 1);
    }


}

