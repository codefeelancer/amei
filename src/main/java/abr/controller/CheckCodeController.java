package abr.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 验证码Controller
 * 
 * @author yh
 * @date 2017年10月25日 上午10:02:10
 */
@Controller
public class CheckCodeController {
	private Logger log = LoggerFactory.getLogger(CheckCodeController.class);

	private static int				WIDTH	= 90;
	private static int				HEIGHT	= 30;

	@RequestMapping(value = "/createCheckCode")
	public void createCheckCode(HttpServletRequest request, HttpServletResponse response) {

		// 设置浏览器不要缓存此图片
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		String rands = this.createRandom();
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();

		// 产生图像
		this.drawBackground(g);
		this.drawRands(g, rands);
		g.dispose();// 结束图像 的绘制 过程， 完成图像

		try {
			response.setContentType("image/png");
			ImageIO.write(image, "jpeg", response.getOutputStream());
			HttpSession session = request.getSession();
			session.setAttribute("checkCode", rands);
		} catch (IOException e) {
			log.error("验证码创建失败！");
			e.printStackTrace();
		}
	}
	

	/**
	 * 创建随机码的四个随机字符串
	 * 
	 * @return
	 * @Date 2017年10月25日 上午10:21:23
	 */
	private String createRandom() {
		String str = "0123456789qwertyuiopasdfghjklzxcvbnm";
		char[] rands = new char[4];
		Random random = new Random();

		for (int i = 0; i < 4; i++) {
			rands[i] = str.charAt(random.nextInt(36));
		}
		return new String(rands);
	}

	/**
	 * 创建图片背景
	 * 
	 * @param g
	 * @Date 2017年10月25日 上午10:22:00
	 */
	private void drawBackground(Graphics g) {
		// 画背景
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// 随机产生 120 个干扰点
		for (int i = 0; i < 120; i++) {
			int x = (int) (Math.random() * WIDTH);
			int y = (int) (Math.random() * HEIGHT);
			int red = (int) (Math.random() * 255);
			int green = (int) (Math.random() * 255);
			int blue = (int) (Math.random() * 255);
			g.setColor(new Color(red, green, blue));
			g.drawOval(x, y, 1, 0);
		}
	}

	/**
	 * 图片上画出随机码
	 * 
	 * @param g
	 * @param rands
	 * @Date 2017年10月25日 上午10:22:55
	 */
	private void drawRands(Graphics g, String rands) {
		g.setColor(Color.BLACK);
		g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 18));

		// 在不同的高度上输出验证码的每个字符
		g.drawString("" + rands.charAt(0), 1, 17);
		g.drawString("" + rands.charAt(1), 16, 15);
		g.drawString("" + rands.charAt(2), 31, 18);
		g.drawString("" + rands.charAt(3), 46, 16);

	}

}
