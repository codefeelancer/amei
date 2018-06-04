package abr.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import abr.bean.Webpage;
import abr.dao.WebpageDao;
import abr.service.MailSenderService;

/**
 * @author Y.H.
 * @create 2017-11-21 21:34
 **/
@Controller
public class FrontController {

	@Autowired
	private WebpageDao webpageDao;

	@Autowired
	private MailSenderService mailSenderService;

	private Logger logger = LoggerFactory.getLogger(FrontController.class);

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String toContact(Model model) {
		logger.info("Accessing  /contact");
		model.addAttribute("otherpageurl", "cn/contact");
		return "/contact";
	}

	@RequestMapping(value = "/cn/contact", method = RequestMethod.GET)
	public String toCnContact(Model model) {
		logger.info("Accessing  /cn/contact");
		model.addAttribute("otherpageurl", "contact");
		return "/cn_contact";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String toIndex(Model model) {
		logger.info("Accessing  /");
		return "/index";
	}

	@RequestMapping(value = "/cn/", method = RequestMethod.GET)
	public String toCnIndex(Model model) {
		logger.info("Accessing  /cn/");
		return "/cn_index";
	}

	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public String toPage(Model model, @PathVariable String page) {
		logger.info("Accessing this path: " + page);
		Webpage webpage = webpageDao.queryPageByPath(page);
		String html = null;
		String title = null;
		if (webpage == null) {
			html = "";
			title = "";
		} else {
			html = webpage.getHtml();
			title = webpage.getTitle();
		}
		model.addAttribute("html", html);
		model.addAttribute("title", title);
		model.addAttribute("otherpageurl", "cn/" + page);
		model.addAttribute("lang", "en");
		return "/page";
	}

	@RequestMapping(value = "/cn/{page}", method = RequestMethod.GET)
	public String toCnPage(Model model, @PathVariable String page) {
		logger.info("Accessing this path: cn/" + page);
		Webpage webpage = webpageDao.queryPageByPath("cn/" + page);
		String html = null;
		String title = null;
		if (webpage == null) {
			html = "";
			title = "";
		} else {
			html = webpage.getHtml();
			title = webpage.getTitle();
		}
		model.addAttribute("html", html);
		model.addAttribute("title", title);
		model.addAttribute("otherpageurl", page);
		model.addAttribute("lang", "cn");
		return "/page";
	}

	@RequestMapping(value = "/cn/search", method = RequestMethod.GET)
	public String toCnSearch(@RequestParam String keyword, Model model) {
		logger.info("search the keyword: " + keyword);
		String html = generateResult(keyword, "cn");
		model.addAttribute("html", html);
		model.addAttribute("title", "Search result for " + keyword);
		model.addAttribute("otherpageurl", "/search?keyword=" + URLEncoder.encode(keyword));
		model.addAttribute("lang", "cn");
		return "/page";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String toSearch(@RequestParam String keyword, Model model) {
		logger.info("search the keyword: " + keyword);
		String html = generateResult(keyword, "en");
		model.addAttribute("html", html);
		model.addAttribute("title", "Search result for " + keyword);
		model.addAttribute("otherpageurl", "cn/search?keyword=" + URLEncoder.encode(keyword));
		model.addAttribute("lang", "en");
		return "/page";
	}

	private String generateResult(String keyword, String lang) {
		List<Webpage> list = null;
		int resultCount = 0;
		StringBuilder htmlBuilder = new StringBuilder("");
		if (keyword != null && !"".equals(keyword.trim())) {
			/**
			 * 建议过滤出所有以下字符(防止注入漏洞)： [1] |（竖线符号） [2] & （& 符号） [3];（分号） [4] $（美元符号） [5]
			 * %（百分比符号） [6] @（at 符号） [7] '（单引号） [8] "（引号） [9] \'（反斜杠转义单引号） [10] \"（反斜杠转义引号）
			 * [11] <>（尖括号） [12] ()（括号） [13] +（加号） [14] CR（回车符，ASCII 0x0d） [15] LF（换行，ASCII
			 * 0x0a） [16] ,（逗号） [17] \（反斜杠）
			 */
			keyword = keyword.replaceAll("\\|", "");
			keyword = keyword.replaceAll("\\&", "");
			keyword = keyword.replaceAll(";", "");
			keyword = keyword.replaceAll("\\$", "");
			keyword = keyword.replaceAll("@", "");
			keyword = keyword.replaceAll("'", "");
			keyword = keyword.replaceAll("\"", "");
			keyword = keyword.replaceAll("\\'", "");
			keyword = keyword.replaceAll("<", "");
			keyword = keyword.replaceAll("\\(", "");
			keyword = keyword.replaceAll("\\)", "");
			keyword = keyword.replaceAll("\\+", "");
			keyword = keyword.replaceAll("\r|\\r", "");
			keyword = keyword.replaceAll("\n|\\n", "");
			keyword = keyword.replaceAll(",", "");
			keyword = keyword.replaceAll("\\\\", "");
			list = webpageDao.queryPagesByKeyword(keyword);
			if ("en".equals(lang)) {
				htmlBuilder.append("<h1>Search Result of \"" + keyword + "\"</h1>");
			} else {
				htmlBuilder.append("<h1>\"" + keyword + "\"的搜索结果</h1>");
			}
			for (Webpage webpage : list) {
				String text = Jsoup.parse(webpage.getHtml()).text();
				if (keyword != null && text.toUpperCase().contains(keyword.toUpperCase())) {
					int substrLength = text.length() > 240 ? 240 : text.length();
					htmlBuilder.append("<div class=\"search-result-item\"><h4><a href=\"");
					htmlBuilder.append(webpage.getPath() + "\">");
					htmlBuilder.append(webpage.getTitle());
					htmlBuilder.append("</a></h4><p>" + text.substring(0, substrLength));
					htmlBuilder.append("......</p></div>");
					resultCount++;
				}
			}
			if (resultCount == 0) {
				if ("en".equals(lang)) {
					htmlBuilder.append(
							"<div style=\"background-color:#f0f0f0;text-align:center;width:100%;padding:60px 0;height:150px;\">No Result!</div>");
				} else {
					htmlBuilder.append(
							"<div style=\"background-color:#f0f0f0;text-align:center;width:100%;padding:60px 0;height:150px;\">无</div>");

				}
			}
		}
		return htmlBuilder.toString();
	}

	static final int FAIL = -1;
	static final int FREQUECE_OVER = 0;
	static final int SCCUSSE = 1;

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sendemail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map sendEmail(@RequestParam String email, @RequestParam String name, @RequestParam String firstname,
			@RequestParam String titel, @RequestParam String company, @RequestParam String zipcode,
			@RequestParam String address, @RequestParam String city, @RequestParam String content,
			HttpSession session) {
		logger.info("begin to send email:");
		Integer n_sendemail = (Integer) session.getAttribute("n_sendemail");
		Map result = new HashMap<>();
		if (n_sendemail == null || n_sendemail <= 5) {
			try {
				mailSenderService.setSubject("测试邮件");
				mailSenderService.setTemplateName("mail.vm");// 设置的邮件模板
				Map<String, String> model = new HashMap<>();
				model.put("email", email);
				model.put("name", name);
				model.put("firstname", firstname);
				model.put("titel", titel);
				model.put("company", company);
				model.put("zipcode", zipcode);
				model.put("address", address);
				model.put("city", city);
				model.put("content", content);
				mailSenderService.sendWithTemplate(model, "trecy-1@163.com");
				logger.info(email + " send an email!");
				result.put("code", SCCUSSE);
			} catch (Exception e) {
				e.printStackTrace();
				logger.info(email+" fail to send email!");
				result.put("code", FAIL);
			}
			if (n_sendemail == null) {
				session.setAttribute("n_sendemail", 1);
			} else {
				session.setAttribute("n_sendemail", ++n_sendemail);
			}
		} else {
			logger.info("Cannot send an email!too frequency!");
			result.put("code", FREQUECE_OVER);
		}
		return result;
	}

}
