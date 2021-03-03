package com.intkr.saas.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2011-10-27 上午09:59:17
 * @version 1.0
 */
public class GetImgUtil {

	public static Element getImg(String content) {
		Document doc = Jsoup.parse(content);
		Elements images = doc.select("img");
		Element image = images.get(0);
		image.attr("width", "160");
		image.attr("height", "110");
		return image;
	}

	public static void resize(InputStream is, OutputStream os, int newWidth, String format) {
		try {
			BufferedImage prevImage = ImageIO.read(is);
			double width = prevImage.getWidth();
			double height = prevImage.getHeight();
			double percent = newWidth / width;
			int newWithd = (int) (width * percent);
			int newHeight = (int) (height * percent);
			BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);
			Graphics graphics = image.createGraphics();
			graphics.drawImage(prevImage, 0, 0, newWithd, newHeight, null);
			ImageIO.write(image, format, os);
			os.flush();
			is.close();
			os.close();
		} catch (IOException e) {
			throw new RuntimeException("", e);
		}
	}

	public static void resize(String srcFile, String descFile, int newWidth) {
		try {
			InputStream is = new FileInputStream(new File(srcFile));
			OutputStream os = new FileOutputStream(new File(descFile));
			resize(is, os, newWidth, "jpg");
		} catch (FileNotFoundException e) {
			throw new RuntimeException("", e);
		}
	}

	public static void main(String[] args) {
		File file = new File("F:\\workspace\\IntKr\\c-fw\\src\\main\\webapp\\IK\\templates\\screen\\themes");
		File[] themes = file.listFiles();
		for (File theme : themes) {
			String srcImage = theme.getAbsolutePath() + "\\images\\cover.png";
			String descImage = theme.getAbsolutePath() + "\\images\\cover-sm.jpg";
			File srcImg = new File(srcImage);
			if (!srcImg.exists()) {
				continue;
			}
			File descImg = new File(descImage);
			if (descImg.exists()) {
				continue;
			}
			resize(srcImage, descImage, 400);
		}
	}

}
