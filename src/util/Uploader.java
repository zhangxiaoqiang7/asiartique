package util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class Uploader{
	public boolean loadSingleFile(HttpServletRequest request,
			HttpServletResponse response, String basename,String suffix) {
		// ����һ��ͨ�õĶಿ�ֽ�����
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// �ж� request �Ƿ����ļ��ϴ�,���ಿ������
		if (multipartResolver.isMultipart(request)) {
			// ת���ɶಿ��request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// ȡ��request�е������ļ���
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// ȡ���ϴ��ļ�
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// ȡ�õ�ǰ�ϴ��ļ����ļ�����
					 //String filename = file.getOriginalFilename();
					if(file.getOriginalFilename().trim()=="") continue;
					 System.out.println(basename);
					 File localFile=null;
					if(suffix.equals(".jpg")||suffix.equals(".png"))  localFile = new File(basename+"_"+suffix);
					else localFile= new File(basename+suffix);
					try {
						file.transferTo(localFile);
						if(suffix.equals(".jpg")||suffix.equals(".png")) ImageCompressUtil.saveMinPhoto(basename+"_"+suffix, basename+suffix, 1000, 0.9d);
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean loadMultipleFiles(HttpServletRequest request,
			HttpServletResponse response, String basename, String suffix) {
		System.out.println(basename);
		// ����һ��ͨ�õĶಿ�ֽ�����
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// �ж� request �Ƿ����ļ��ϴ�,���ಿ������
		if (multipartResolver.isMultipart(request)) {
			// ת���ɶಿ��request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// ȡ��request�е������ļ���
			Iterator<String> iter = multiRequest.getFileNames();
			int cnt=1;
			while (iter.hasNext()) {
				// ȡ���ϴ��ļ�
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// ȡ�õ�ǰ�ϴ��ļ����ļ�����
					// String filename = file.getOriginalFilename();
					if(file.getOriginalFilename().trim()=="") continue;
					File localFile = new File(basename+"_"+cnt+"_"+suffix);
					try {
						file.transferTo(localFile);
						ImageCompressUtil.saveMinPhoto(basename+"_"+cnt+"_"+suffix, basename+"_"+cnt+suffix, 1000, 0.9d);
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
				}
				cnt++;
			}
		}
		return true;
	}
}
