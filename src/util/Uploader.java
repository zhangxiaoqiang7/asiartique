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
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
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
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			int cnt=1;
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
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
