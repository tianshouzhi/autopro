package ${package};

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

/**
 * 生成验证码图片的工具类
 * @author tianshouzhi@126.com
 * @createTime 2014-6-12 上午12:59:46
 * @version v1
 * @description  说明，这个工具类是本人参考http://www.cnblogs.com/dongliyang/archive/2012/08/24/2654431.html这个博客来写的
 * 首先非常感谢该博客的作者，看了他的代码和思路，知道自己还有很长的路要走。有关编写验证码的思路，请参考以上博客
 * 
 * 本文对其中的一些代码做了一些小小的改进，保证了程序向后的兼容性：
 * 即替换了JPEGImageEncoder 类和 JPEGCodec类，这两个API属于SUN公司未公开的API，经过本人的测试，在JDK1.6中这两个API是存在的，在JDK1.7中已经取消
  * 所以使用这两个API是无法保证程序向后兼容
 * 
 * 还有一处是，原文在点击图片获取新的验证码是，使用是alt="看不清，换一张"，应该使用的是title="看不清，换一张"
 * 
 *  //最后，在原文的基础上，给出一个非常重要的提示：、如果项目中控制了权限，那么必须对请求验证码的action放行，否则无法获取到验证码
 * 
 */
public class BufferedImageGenarator {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(BufferedImageGenarator.class);

	 /**
	    * 生成验证码图片
	    * @param securityCode   验证码字符
	    * @return  BufferedImage  图片
	    */
	   public static BufferedImage createImage(String securityCode){
	       
	       //验证码长度
	       int codeLength=securityCode.length();
	       //字体大小
	       int fSize = 16;
	       int fWidth = fSize + 1;
	       //图片宽度
	       int width = codeLength * fWidth + 6 ;
	       //图片高度
	       int height = fSize * 2 + 1;
	       
	       //图片
	       BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	       Graphics g=image.createGraphics();
	       
	       //设置背景色
	      
	       g.setColor(Color.WHITE);
	       
	       //填充背景
	       g.fillRect(0, 0, width, height);
	       
	       //设置边框颜色
	       g.setColor(Color.LIGHT_GRAY);
	       //边框字体样式
	       g.setFont(new Font("Arial", Font.BOLD, height - 2));
	       //绘制边框
	       g.drawRect(0, 0, width - 1, height -1);
	       
	       
	       //绘制噪点
	       Random rand = new Random();
	       //设置噪点颜色
	       g.setColor(Color.LIGHT_GRAY);
	       for(int i = 0;i < codeLength * 6;i++){
	           int x = rand.nextInt(width);
	           int y = rand.nextInt(height);
	           //绘制1*1大小的矩形
	           g.drawRect(x, y, 1, 1);
	       }
	       
	       //绘制验证码
	       int codeY = height - 10;  
	       //设置字体颜色和样式
	       g.setColor(new Color(19,148,246));
	       g.setFont(new Font("Georgia", Font.BOLD, fSize));
	       for(int i = 0; i < codeLength;i++){
	           g.drawString(String.valueOf(securityCode.charAt(i)), i * 16 + 5, codeY);
	       }
	       //关闭资源
	       g.dispose();
	       
	       return image;
	   }
	   
	   /**
	    * 返回验证码图片的流格式
	    * @param securityCode  验证码
	    * @return ByteArrayInputStream 图片流
	    */
	   public static ByteArrayInputStream getImageAsInputStream(String securityCode){
	       
	       BufferedImage image = createImage(securityCode);
	       return convertImageToStream(image);
	   }
	   
	   /**
	    * 将BufferedImage转换成ByteArrayInputStream
	    * @param image  图片
	    * @return ByteArrayInputStream 流
	    */
	   private static ByteArrayInputStream convertImageToStream(BufferedImage image){
	      
	       ByteArrayInputStream inputStream;
		try {
			   ByteArrayOutputStream bos = new ByteArrayOutputStream();
			   ImageIO.write(image, "jpg", bos);
			   byte[] bts = bos.toByteArray();
			   inputStream = new ByteArrayInputStream(bts);
		} catch (IOException e) {
			logger.error("创建验证码图片失败", e);
			throw new RuntimeException(e);
		}
	      
	       return inputStream;
	   }
}
