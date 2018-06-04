package abr.controller;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 处理文件上传删除相关请求
 * 返回的状态码status:<br>
 * <ul>
 * <li>0 - 成功  </li>
 * <li>1 - 类型错误 </li>
 * <li>2 - 读写错误 </li>
 * <li>3 - 数据库错误 </li>
 * <li>4 - 文件无法删除 </li>
 * </ul>
 * @author yh
 * @date 2017年3月30日 上午10:39:38
 */
@Controller
public class FileUploadController {
	
	private final Logger log = LoggerFactory.getLogger(FileUploadController.class);

	/**
	 * 处理编辑文章中上传的图片
	 * 
	 * @Date 2017年3月30日 上午10:04:21
	 */
	@RequestMapping(path = "/saveimage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> handleAritleImageUpload(@RequestParam("file") Part file, HttpServletRequest request) {
		log.info("Handle uploaded  image in the new article and save that in /upload folder");
		
		Map<String, Object> result = new HashMap<>();
		String realSavePath = request.getServletContext().getRealPath("/upload");
		String header = file.getHeader("content-disposition");
		String newFileName = new Long(new Date().getTime()).toString()+ this.getFileSuffix(header);
		
		// if temp file is not an image
		if (!this.isImg(header)) {
			result.put("status", 1);
			return result;
		}
		
		try {
			file.write(realSavePath + File.separator + newFileName);
			result.put("status",0);
			result.put("url", "upload/" + newFileName);
		} catch (IOException e) {
			log.error("Fail to write image to file.Exception:"+e.getMessage());
			e.printStackTrace();
			result.put("status",2);
		}
		return result;
	}

	/**
	 * 处理首页大图管理上传的图片（缓存版本，后面会被裁剪）
	 * statust只能为success和error，是截图插件cropper的要求
	 * 
	 * @Date 2017年3月30日 上午10:04:21
	 */
	@RequestMapping(path = "/saveImageTemp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> handleCarouselImageUpload(@RequestParam("img") Part file, HttpServletRequest request) {
		log.info("Handle uploaded  image and save that in /upload folder");
		
		Map<String, Object> result = new HashMap<>();
		String savePath = request.getServletContext().getRealPath("/upload");
		String newFileName = new Long(new Date().getTime()).toString();
		String header = file.getHeader("content-disposition");
		
		// if temp file is not an image
		if (!this.isImg(header)) {
			result.put("status", "error");
			result.put("message", "Please upload image, only support png/gif/jpg/jpeg");
			return result;
		}
		//write image to file in designated folder
		try {
			file.write(savePath + File.separator + newFileName + this.getFileSuffix(header));
			BufferedImage img = ImageIO.read(new File(savePath + File.separator + newFileName + this.getFileSuffix(header)));
			result.put("status", "success");
			result.put("url", "upload/" + newFileName + this.getFileSuffix(header));
			result.put("width", img.getWidth());
			result.put("height", img.getHeight());
		} catch (IOException e) {
			log.error("Fail to write image to flie"+e.getMessage());
			e.printStackTrace();
			result.put("status", "error");
			result.put("message", "Fail！IO Error！");
		}
		return result;
	}

	
	/**
	 * 处理首页大图管理中经过裁剪的图片
	 * 
	 * @Date 2017年3月28日 下午2:28:01
	 */
	@RequestMapping(path = "/crop", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> handleCarouselImageCrop(@RequestParam String imgUrl, @RequestParam String imgInitW,
			@RequestParam String imgInitH, @RequestParam String imgW, @RequestParam String imgH,
			@RequestParam String imgY1, @RequestParam String imgX1, @RequestParam String cropW,
			@RequestParam String cropH, HttpServletRequest request) {
		log.info("Handle uploaded  image, cropping and saving that in /upload folder");
		Map<String, Object> result = new HashMap<>();
		String parentPath = request.getServletContext().getRealPath("/upload");
		String savePath = request.getServletContext().getRealPath("/"+imgUrl);
		
		//log
		log.debug("x:" + imgX1 + " y:" + imgY1);
		log.debug("initw:" + imgInitW + " inith:" + imgInitH);
		log.debug("newW:" + imgW + " newh:" + imgH);
		log.debug("boxW:" + cropW + " boxH:" + cropH);
		
		try {
			String suffix = imgUrl.substring(imgUrl.lastIndexOf(".") + 1);
			zoomImage(savePath, convert(imgW), convert(imgH));
			String url = cutImage(parentPath, savePath, suffix, convert(imgX1), convert(imgY1), convert(cropW),
					convert(cropH));
			result.put("status", "success");
			result.put("url", url);
		} catch (Exception e) {
			result.put("status", "error");
			result.put("message", "Fail！IO Error！");
			
			//log
			log.error("Fail to write image to flie.Exception:"+e.getMessage());
			log.error("Original image url is "+imgUrl);
			log.error("The cropped image is saved in  "+savePath);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 判断是否为图片
	 * 
	 * @Date 2017年3月30日 上午11:02:13
	 */
	private boolean isImg(String header){
		List<String> typelist = Arrays.asList("gif", "jpeg", "jpg", "png");
		String suffix = this.getFileSuffix(header).substring(this.getFileSuffix(header).lastIndexOf(".") + 1)
				.toLowerCase();
		return typelist.contains(suffix);
	}
	
	/**
	 * 判断是否为文档
	 * 
	 * @Date 2017年3月30日 上午11:02:13
	 */
	private boolean isDocument(String header){
		List<String> typelist = Arrays.asList("txt", "doc", "docx", "pdf", "ppt", "pptx", "xls", "xlsx");
		String suffix = this.getFileSuffix(header).substring(this.getFileSuffix(header).lastIndexOf(".") + 1)
				.toLowerCase();
		return typelist.contains(suffix);
	}
	
	/**
	 * 裁剪图片，删除缓存图片，并重新存储
	 * 
	 * @Date 2017年3月28日 下午3:22:21
	 */
 	 private String cutImage(String parentPath, String path, String suffix, int x, int y, int w, int h)
			throws Exception {
		Iterator iterator = ImageIO.getImageReadersByFormatName(suffix);
		ImageReader reader = (ImageReader) iterator.next();
		InputStream in = new FileInputStream(path);
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(x, y, w, h);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);

		String newFileName = new Long(new Date().getTime()).toString() + "." + suffix;
		File newFile = new File(parentPath + File.separator + newFileName);
		if(newFile.createNewFile()){
			log.info("Create img in the path:"+ parentPath);
		}
		try {
			ImageIO.write(bi, suffix, newFile);
			File temp = new File(path);
			if (temp.exists()){
				log.info("Delete original temp image");
				temp.delete();
			}
			return "upload/" + newFileName;
		} catch (Exception e) {
			log.error("Fail to write image to flie.Exception:"+e.getMessage());
			e.printStackTrace();
			return " ";
		}
	}

	/**
	 * 缩放图片
	 * 
	 * @param path 被缩放图片的路径
	 * @param w  目标宽度
	 * @param h  目标高度
	 * @throws Exception
	 * @Date 2017年3月28日 下午3:15:09
	 */
	private void zoomImage(String path, int w, int h) throws Exception {
		double wr = 0, hr = 0;
		File file = new File(path);
		BufferedImage bufImg = ImageIO.read(file);
		Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);
		wr = w * 1.0 / bufImg.getWidth();
		hr = h * 1.0 / bufImg.getHeight();
		AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
		Itemp = ato.filter(bufImg, null);
		ImageIO.write((BufferedImage) Itemp, path.substring(path.lastIndexOf(".") + 1), file);

	}

	/**
	 * 从消息头content-disposition的filename中提取后缀名
	 * 
	 * @param header 消息头content-disposition
	 * @return 后缀名 '.filetype'
	 * @Date 2017年3月28日 下午2:38:11
	 */
	private String getFileSuffix(String header) {
		String[] tempArr1 = header.split(";");
		String[] tempArr2 = tempArr1[2].split("=");
		String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
		return fileName.substring(fileName.lastIndexOf("."));
	}
	
	/**
	 * 从消息头content-disposition中提取文件名
	 * 
	 * @param header 消息头content-disposition
	 * @return 后缀名 'filename.filetype'
	 * @Date 2017年3月28日 下午2:38:11
	 */
	private String getFileName(String header) {
		String[] tempArr1 = header.split(";");
		String[] tempArr2 = tempArr1[2].split("=");
		String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
		return fileName;
	}

	/**
	 * 内容为浮点数的字符串，将其转化为整数
	 * 
	 * @param doubleStr 内容为浮点数
	 * @return 如果NaN结果为0；如果字符串不为数字结果为-1
	 * @Date 2017年3月28日 下午2:37:29
	 */
	private int convert(String doubleStr) {
		if ("NaN".equals(doubleStr))
			return 0;
		else {
			try {
				return (int) Double.parseDouble(doubleStr);
			} catch (NumberFormatException e) {
				return -1;
			}
		}
	}

}
