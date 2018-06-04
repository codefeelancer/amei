package abr.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import abr.bean.Webpage;
import abr.dao.WebpageDao;

@Controller
@RequestMapping(value = "/admin/webpageManage")
public class WebpageManageController {

	@Autowired
	private WebpageDao webpageDao;

	private Logger log = LoggerFactory.getLogger(WebpageManageController.class);

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addWebpage(Model model, @RequestParam String title, @RequestParam String path,
			@RequestParam String content, WebRequest request) {
		log.info("create a new webpage");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//去除各种换行符，否则文章页面可能缺少一部分
		content = content.replaceAll("\r\n||\\r\\n|\r|\n|\\n|\\r", "");
		
		boolean isSuccess = webpageDao.addWebpage(title, path, content);
		//根据insert结果，重定向到新页面
		if (isSuccess) {
			return "redirect:view/" + path;
		} else {
			model.addAttribute("message","add-fail");
			return "redirect:/error.jsp";
		}

	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyWebpage(Model model, @RequestParam String title,@RequestParam String path,
			 @RequestParam String content,WebRequest request) {
		log.info("modify article");
		//去除各种换行符，否则文章页面可能缺少一部分
		content = content.replaceAll("\r\n||\\r\\n|\r|\n|\\n|\\r", "");
				
		if (webpageDao.modifyWebpage(title, content, path)) {
			return "redirect:view?path=" + path;
		} else {
			model.addAttribute("message","add-fail");
			return "redirect:/error.jsp";
		}

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String toWebpageList(Model model) {
		model.addAttribute("list", webpageDao.queryList());
		return "/admin-list-webpage";
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String toWebpageDetail(@RequestParam String path, Model model) {
		Webpage webpage =  webpageDao.queryPageByPath(path);
		String html = null;
        String title = null;
        if (webpage == null){
        		html = "";
            title = "";
        }else{
            html = webpage.getHtml();
            title = webpage.getTitle();
        }
        model.addAttribute("html",html);
        model.addAttribute("title",title);
        
		if (path != null) {
			if (path.contains("cn/")) {
		        model.addAttribute("otherpageurl","admin/webpageManage/view?path="+path.substring(path.indexOf("cn/")));
		        model.addAttribute("lang","cn");
			}else {
				model.addAttribute("otherpageurl","admin/webpageManage/view?path=cn/"+path);
		        model.addAttribute("lang","en");
			}
		}else {
	        model.addAttribute("otherpageurl","admin/webpageManage/view?path="+path);
	        model.addAttribute("lang","en");
		}
		return "/admin-view-webpage";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String toWebpageEdit(@RequestParam String path, Model model) {
		model.addAttribute("webpage", webpageDao.queryPageByPath(path));
		return "/admin-edit-webpage";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String toNewWebpage(Model model) {
		return "/admin-add-webpage";
	}
	
}
