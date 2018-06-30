package com.wyl.crm.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageRandServlet extends HttpServlet {

	private static final String CONTENT_TYPE = "image/jpeg";

	// Initialize global variables

	public void init() throws ServletException {

	}

	// Process the HTTP Get request

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws

	ServletException, IOException {

		try {

			response.setHeader("Pragma", "No-cache");

			response.setHeader("Cache-Control", "no-cache");

			response.setDateHeader("Expires", 0);

			int width = 60, height = 20;

			BufferedImage image = new BufferedImage(width, height,

					BufferedImage.TYPE_INT_RGB);

			OutputStream os = response.getOutputStream();

			Graphics g = image.getGraphics();

			Random random = new Random();

			g.setColor(getRandColor(200, 250));

			g.fillRect(0, 0, width, height);

			g.setFont(new Font("Arial", Font.PLAIN, 18));

			g.setColor(getRandColor(160, 200));

			for (int i = 0; i < 155; i++) {

				int x = random.nextInt(width);

				int y = random.nextInt(height);

				int xl = random.nextInt(12);

				int yl = random.nextInt(12);

				g.drawLine(x, y, x + xl, y + yl);

			}

			String sRand = "";

			for (int j = 0; j < 4; j++) {

				String rand = String.valueOf(random.nextInt(10));

				sRand += rand;

				g.setColor(new Color(20 + random.nextInt(110), 20 + random

						.nextInt(110), 20 + random.nextInt(110)));

				g.drawString(rand, 13 * j + 6, 16);

			}

			request.getSession().setAttribute("rand", sRand);

			g.dispose();

			ImageIO.write(image, "JPEG", os);

			os.flush();

			os.close();

			os = null;

			response.flushBuffer();

			// out.clear();

			// out = pageContext.pushBody();

		} catch (IllegalStateException e) {

			e.printStackTrace();

		}

	}

	// Process the HTTP Post request

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws

	ServletException, IOException {

		doGet(request, response);

	}

	// Clean up resources

	public void destroy() {

	}

	public Color getRandColor(int fc, int bc) {

		Random random = new Random();

		if (fc > 255)

			fc = 255;

		if (bc > 255)

			bc = 255;

		int r = fc + random.nextInt(bc - fc);

		int g = fc + random.nextInt(bc - fc);

		int b = fc + random.nextInt(bc - fc);

		return new Color(r, g, b);

	}

}
