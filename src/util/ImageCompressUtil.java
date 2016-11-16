package util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageCompressUtil {
	/**
	 * ֱ��ָ��ѹ����Ŀ�ߣ�
	 * (�ȱ���ԭ�ļ�����ѹ�����ϴ�)
	 * Ҽ����Ŀ�����ڶ�ά��ѹ��
	 * @param oldFile Ҫ����ѹ�����ļ�ȫ·��
	 * @param width ѹ����Ŀ��
	 * @param height ѹ����ĸ߶�
	 * @param quality ѹ������
	 * @param smallIcon �ļ�����СС��׺(ע�⣬���ļ���׺����),��ѹ���ļ�����yasuo.jpg,��ѹ�����ļ�����yasuo(+smallIcon).jpg
	 * @return ����ѹ������ļ���ȫ·��
	 */
	public static String zipImageFile(String oldFile, int width, int height,
			float quality, String smallIcon) {
		if (oldFile == null) {
			return null;
		}
		String newImage = null;
		try {
			/**�Է������ϵ���ʱ�ļ����д��� */
			Image srcFile = ImageIO.read(new File(oldFile));
			/** ��,���趨 */
			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(srcFile, 0, 0, width, height, null);
			String filePrex = oldFile.substring(0, oldFile.indexOf('.'));
			/** ѹ������ļ��� */
			newImage = filePrex + smallIcon	+ oldFile.substring(filePrex.length());
			/** ѹ��֮����ʱ���λ�� */
			FileOutputStream out = new FileOutputStream(newImage);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
			/** ѹ������ */
			jep.setQuality(quality, true);
			encoder.encode(tag, jep);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newImage;
	}

	/**
	 * �����ļ�����������ʱ·��(�����ļ��ϴ�)
	 * @param fileName
	 * @param is
	 * @return �ļ�ȫ·��
	 */
	public static String writeFile(String fileName, InputStream is) {
		if (fileName == null || fileName.trim().length() == 0) {
			return null;
		}
		try {
			/** ���ȱ��浽��ʱ�ļ� */
			FileOutputStream fos = new FileOutputStream(fileName);
			byte[] readBytes = new byte[512];// �����С
			int readed = 0;
			while ((readed = is.read(readBytes)) > 0) {
				fos.write(readBytes, 0, readed);
			}
			fos.close();
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	/**
	 * �ȱ���ѹ���㷨�� 
	 * �㷨˼�룺����ѹ��������ѹ������ѹ��ԭͼ������һ��ͼƬЧ����ӽ�ԭͼ������ͼ
	 * @param srcURL ԭͼ��ַ
	 * @param deskURL ����ͼ��ַ
	 * @param comBase ѹ������
	 * @param scale ѹ������(��/��)����  һ����1��
	 * ��scale>=1,����ͼheight=comBase,width��ԭͼ��߱���;��scale<1,����ͼwidth=comBase,height��ԭͼ��߱���
	 * @throws Exception
	 * @author shenbin
	 * @createTime 2014-12-16
	 * @lastModifyTime 2014-12-16
	 */
	public static void saveMinPhoto(String srcURL, String deskURL, double comBase,
			double scale) throws Exception {
		File srcFile = new java.io.File(srcURL);
		Image src = ImageIO.read(srcFile);
		int srcHeight = src.getHeight(null);
		int srcWidth = src.getWidth(null);
		int deskHeight = 0;// ����ͼ��
		int deskWidth = 0;// ����ͼ��
		double srcScale = (double) srcHeight / srcWidth;
		/**����ͼ����㷨*/
		if ((double) srcHeight > comBase || (double) srcWidth > comBase) {
			if (srcScale >= scale || 1 / srcScale > scale) {
				if (srcScale >= scale) {
					deskHeight = (int) comBase;
					deskWidth = srcWidth * deskHeight / srcHeight;
				} else {
					deskWidth = (int) comBase;
					deskHeight = srcHeight * deskWidth / srcWidth;
				}
			} else {
				if ((double) srcHeight > comBase) {
					deskHeight = (int) comBase;
					deskWidth = srcWidth * deskHeight / srcHeight;
				} else {
					deskWidth = (int) comBase;
					deskHeight = srcHeight * deskWidth / srcWidth;
				}
			}
		} else {
			deskHeight = srcHeight;
			deskWidth = srcWidth;
		}
		BufferedImage tag = new BufferedImage(deskWidth, deskHeight, BufferedImage.TYPE_3BYTE_BGR);
		tag.getGraphics().drawImage(src, 0, 0, deskWidth, deskHeight, null); //������С���ͼ
		FileOutputStream deskImage = new FileOutputStream(deskURL); //������ļ���
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(deskImage);
		encoder.encode(tag); //��JPEG����
		deskImage.close();
	}

	public static void main(String args[]) throws Exception {
		//ImageCompressUtil.zipImageFile("f:/25.jpg", 1280, 1280, 1f, "x2");
		ImageCompressUtil.saveMinPhoto("D:\\apache-tomcat-7.0.59\\webapps\\artcenter\\WEB-INF\\images\\fineart\\1478670688384_1_.jpg", "D:\\apache-tomcat-7.0.59\\webapps\\artcenter\\WEB-INF\\images\\fineart\\1478670688384_1.jpg", 1000, 0.9d);
	}
}
