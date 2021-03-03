package com.intkr.saas.module.screen.common;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.requestcontext.buffered.BufferedRequestContext;
import com.alibaba.citrus.util.StringUtil;
import com.intkr.saas.util.RequestUtil;

/**
 * 
 * @author Beiden
 * @date 2017-1-25 下午2:37:29
 * @version 1.0
 */
public class BarCode {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private BufferedRequestContext brc;

	public void execute() throws Exception {
		try {
			if (StringUtil.isBlank(RequestUtil.getParam(request, "code"))) {
				return;
			}
			String code = RequestUtil.getParam(request, "code");
			// 为了节省内存，关闭buffering。
			brc.setBuffering(false);

			// 只要设置了正确的content type，你就可以输出任何文本或二进制的内容：
			// HTML、JSON、JavaScript、JPG、PDF、EXCEL等。
			response.setContentType("image/jpeg");

			OutputStream out = response.getOutputStream();
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "No-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpeg");
			generate(code, response.getOutputStream());
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * 生成到流
	 * 
	 * @param msg
	 * @param ous
	 */
	public static void generate(String msg, OutputStream ous) {
		if (msg == null || "".equals(msg) || ous == null) {
			return;
		}
		Code128Bean bean = new Code128Bean();
		// 精细度
		final int dpi = 300;
		// module宽度
		final double moduleWidth = UnitConv.in2mm(4.0f / dpi);
		// 配置对象
		bean.setModuleWidth(moduleWidth);
		// bean.setWideFactor(3);
		bean.doQuietZone(false);
		String format = "image/png";
		try {
			// 输出到流
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
			// 生成条形码
			bean.generateBarcode(canvas, msg);
			// 结束绘制
			canvas.finish();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
