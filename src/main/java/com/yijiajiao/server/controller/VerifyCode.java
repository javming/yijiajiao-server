package com.yijiajiao.server.controller;

import com.yijiajiao.server.util.RedisUtil;
import com.yijiajiao.server.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.ws.rs.*;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

@Slf4j
@Path("/get")
public class VerifyCode {

	private static final int width = 70;
	private static final int height = 40;
	private static final String verify = "verify-code:";

	@GET
	@Path("/verify/image.json")
	@Produces("image/jpeg")
	public Response getImageNew() throws Exception{
		Map<String, Response> map = new HashMap<>();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = image.createGraphics();
		Font font = new Font("Atlantic Inline", Font.BOLD, 30);
		g.setColor(getRandomColor(200, 250));
		g.fillRect(0, 0, width, height);
		g.setFont(font);
		Random random = new Random();
		String code = "";
		for (int i = 0; i < 4; i++) {
			String str = "";
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			if ("char".equalsIgnoreCase(charOrNum)) {
				str = String.valueOf((char) (97 + random.nextInt(26)));
				code += str;
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				str = String.valueOf(random.nextInt(10));
				code += str;
			}
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(str, 13 * i + 6, 30);
		}
		log.info("code === {}", code );
		g.dispose();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageIO.write(image, "JPEG", output);
		final byte[] imgData = output.toByteArray();
		final InputStream bigInputStream = new ByteArrayInputStream(imgData);
		Response.ResponseBuilder builder = Response.ok(bigInputStream);
		builder.header("imgheader", code);
		map.put(code, builder.cacheControl(getCacheControl(true)).build());
		return map.remove(code);
	}

	@GET
	@Path("/verify/code/image.json")
	@Produces("image/jpeg")
	public Response getImageNew(@QueryParam("tel") String tel) throws Exception{
		if (StringUtil.isEmpty(tel) || tel.length()!=11){
			return null;
		}
		Map<String, Response> map = new HashMap<>();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = image.createGraphics();
		Font font = new Font("Atlantic Inline", Font.BOLD, 30);
		g.setColor(getRandomColor(200, 250));
		g.fillRect(0, 0, width, height);
		g.setFont(font);
		Random random = new Random();
		String code = "";
		for (int i = 0; i < 4; i++) {
			String str = "";
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			if ("char".equalsIgnoreCase(charOrNum)) {
				str = String.valueOf((char) (97 + random.nextInt(26)));
				code += str;
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				str = String.valueOf(random.nextInt(10));
				code += str;
			}
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(str, 13 * i + 6, 30);
		}
		g.dispose();
		RedisUtil.putRedis(verify+tel,code,60);
		log.info("图片验证码存放redis "+RedisUtil.ttl(verify+tel)+"s");
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageIO.write(image, "JPEG", output);
		final byte[] imgData = output.toByteArray();
		final InputStream bigInputStream = new ByteArrayInputStream(imgData);
		Response.ResponseBuilder builder = Response.ok(bigInputStream);
		builder.header("imgheader", code);
		map.put(code, builder.cacheControl(getCacheControl(true)).build());
		return map.remove(code);
	}
	
	private static CacheControl getCacheControl(boolean falg) {
		CacheControl cc = new CacheControl();
		cc.setMaxAge(60);
		cc.setNoCache(falg);
		return cc;
	}
	
	private static Color getRandomColor(int fc, int bc) throws Exception {
		Random random = new Random();
		if (fc > 255)
			fc = 200;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	
}
