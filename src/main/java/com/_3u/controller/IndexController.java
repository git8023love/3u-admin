package com._3u.controller;

import com._3u.config.Config;
import com._3u.utils.*;
import com._3u.domain.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class IndexController {

	private JSONArray mainFunctions;

	private List<Map<String, Object>> functions;
	
	@RequestMapping("/index")
	public String index(Model model, @RequestParam("ticket") String ticket, HttpServletRequest request, HttpServletResponse response){
		System.out.println("12345698778965412336985214712365");
		// 查询该用户的功能信息
		try {
			if (ticket != null && ticket.length() == 32) {
				//String json = URLEncodeUtils.decodeURL(HTMLUtils.postURLConnection(Config.TICKET_URL, "system=7&ticket=" + ticket)).trim();
				// id 512
				String json = "{\"functions\":[" +
						"{\"logoutPage\":\"http://192.168.1.80:8080/oa/user_logout.action\",\"userId\":48,\"name\":\"OA管理系统\",\"functionId\":1,\"loginPage\":\"http://192.168.1.80:8080/oa/user_login.action\",\"homePage\":\"http://192.168.1.80:8080/oa/user_index.action\"}," +
						"{\"logoutPage\":null,\"userId\":48,\"name\":\"SDK运营管理系统\",\"functionId\":2,\"loginPage\":null,\"homePage\":\"http://192.168.1.80:8080/user_index.action\"}," +
						"{\"logoutPage\":null,\"userId\":48,\"name\":\"PC端运营管理系统\",\"functionId\":3,\"loginPage\":null,\"homePage\":\"http://192.168.1.80:8080/main/user/index.go\"}," +
						"{\"logoutPage\":null,\"userId\":48,\"name\":\"数据查询系统\",\"functionId\":4,\"loginPage\":null,\"homePage\":\"http://192.168.1.80:8080/user/index.go\"}," +
						"{\"logoutPage\":null,\"userId\":48,\"name\":\"移动端运营管理系统\",\"functionId\":5,\"loginPage\":null,\"homePage\":\"http://192.168.1.80:8080/main/user/index.go\"}," +
						"{\"logoutPage\":null,\"userId\":48,\"name\":\"i4 web端运营后台\",\"functionId\":6,\"loginPage\":null,\"homePage\":\"http://192.168.1.80:8080/user_index.action\"}," +
						"{\"logoutPage\":null,\"userId\":48,\"name\":\"3u web端运营后台\",\"functionId\":7,\"loginPage\":null,\"homePage\":\"http://192.168.1.80:8080/user_index.action\"}," +
						"{\"logoutPage\":null,\"userId\":48,\"name\":\"大数据(外网)\",\"functionId\":8,\"loginPage\":null,\"homePage\":\"http://192.168.1.80:8080/login.html\"}," +
						"{\"logoutPage\":null,\"userId\":48,\"name\":\"合同管理系统\",\"functionId\":10,\"loginPage\":null,\"homePage\":\"http://192.168.1.80:8080/main/user/index.go\"}," +
						"{\"logoutPage\":null,\"userId\":48,\"name\":\"固定资产管理\",\"functionId\":11,\"loginPage\":null,\"homePage\":\"http://192.168.1.80:8080/main/user/index.go\"}]," +
						"\"username\":\"lxl\"," +
						"\"sonFunctions\":[" +
						"{\"id\":355,\"sonFunctions\":[" +
						"{\"id\":356,\"js\":\"addVersions\",\"name\":\"产品中心\",\"pid\":355,\"url\":\"version_list.action\"}," +
						"{\"id\":357,\"js\":\"addOpinions\",\"name\":\"意见反馈\",\"pid\":355,\"url\":\"opinion_list.action\"}," +
						"{\"id\":512,\"js\":\"addUserOpinion\",\"name\":\"用户反馈\",\"pid\":355,\"url\":\"userOpinion_list.action\"}," +
						"{\"id\":364,\"js\":\"addUpdateLogs\",\"name\":\"更新日志\",\"pid\":355,\"url\":\"updatelog_list.action\"}]," +
						"\"js\":\"#\",\"name\":\"首页管理\",\"pid\":0,\"url\":\"#\"}," +
						"{\"id\":365,\"sonFunctions\":[" +
						"{\"id\":442,\"js\":\"addNewsSortListTag\",\"name\":\"分类管理\",\"pid\":365,\"url\":\"newsSort_list.action\"}," +
						"{\"id\":366,\"js\":\"addNewsListTag\",\"name\":\"资讯管理\",\"pid\":365,\"url\":\"news_list.action\"}," +
						"{\"id\":445,\"js\":\"addNewsRecommendListTag\",\"name\":\"推荐文章\",\"pid\":365,\"url\":\"newsRecommend_list.action\"}," +
						"{\"id\":446,\"js\":\"addPcAdvListTag\",\"name\":\"PC端广告图\",\"pid\":365,\"url\":\"pcAdv_list.action\"}]" +
						",\"js\":\"#\",\"name\":\"新闻资讯\",\"pid\":0,\"url\":\"#\"}," +
						"{\"id\":426,\"sonFunctions\":[" +
						"{\"id\":427,\"js\":\"addGroups\",\"name\":\"短地址分组管理\",\"pid\":426,\"url\":\"group_list.action\"}," +
						"{\"id\":428,\"js\":\"addShortUrls\",\"name\":\"短地址管理\",\"pid\":426,\"url\":\"shortUrl_list.action\"}," +
						"{\"id\":429,\"js\":\"addPcVersions\",\"name\":\"版本管理\",\"pid\":426,\"url\":\"pcVersion_list.action\"}," +
						"{\"id\":440,\"js\":\"addPaperListTag\",\"name\":\"壁纸审核\",\"pid\":426,\"url\":\"paper_list.action\"}," +
						"{\"id\":470,\"js\":\"addPaperRecommendListTag\",\"name\":\"壁纸推荐\",\"pid\":426,\"url\":\"paper_recommend.action\"}," +
						"{\"id\":457,\"js\":\"addRingListTag\",\"name\":\"铃声审核\",\"pid\":426,\"url\":\"ring_list.action\"}," +
						"{\"id\":449,\"js\":\"addOtherToolListTag\",\"name\":\"其他工具\",\"pid\":426,\"url\":\"otherTool_list.action\"}]," +
						"\"js\":\"#\",\"name\":\"PC端管理\",\"pid\":0,\"url\":\"#\"}," +
						"{\"id\":447,\"sonFunctions\":[" +
						"{\"id\":448,\"js\":\"addlogFileListTag\",\"name\":\"Log统计\",\"pid\":447,\"url\":\"pcLog_list.action\"}]," +
						"\"js\":\"#\",\"name\":\"PC端统计\",\"pid\":0,\"url\":\"#\"}," +
						"{\"id\":508,\"sonFunctions\":[" +
						"{\"id\":509,\"js\":\"addSpecialPaperListTag\",\"name\":\"壁纸专题\",\"pid\":508,\"url\":\"specialPaper_list.action\"}," +
						"{\"id\":510,\"js\":\"addSpecialRingListTag\",\"name\":\"铃声专题\",\"pid\":508,\"url\":\"specialRing_list.action\"}]," +
						"\"js\":\"\",\"name\":\"专题管理\",\"pid\":0,\"url\":\"#\"}]," +
						"\"userId\":48,\"realName\":\"刘晓龙\",\"ip\":\"183.14.29.71\",\"expiry\":1440}";
				if (StringUtils.isNotEmpty(json)) {
					JSONObject jsonObject = new JSONObject(json);
					String ip = jsonObject.getString("ip");
					String nowIp = RemoteAdressUtils.getIpValue(request);
					// if (ip.split(",").length == 1 && !ip.equals(nowIp)) {
					if (!ip.contains(nowIp)) {
						// 重新设定ticket缓存值
						//HTMLUtils.postURLConnection(Config.TICKET_RESET_URL, "ip=" + nowIp + "&ticket=" + ticket);
					} /*else if (!ip.contains(nowIp)) {
						getResponse().sendRedirect(Config.LOGIN_URL);
						return null;
					}*/
					int userId = jsonObject.getInt("userId");
					String username = jsonObject.getString("username");
					String realName = jsonObject.getString("realName");
					User user = new User();
					user.setId(userId);
					user.setUsername(username);
					user.setRealName(realName);

					mainFunctions = jsonObject.getJSONArray("functions");

					JSONArray sonFunctions = jsonObject.getJSONArray("sonFunctions");

					functions = new ArrayList<Map<String, Object>>();
					Set<String> funcHrefs = new HashSet<String>();

					for (int i = 0; i < sonFunctions.length(); i++) {
						JSONObject func = sonFunctions.getJSONObject(i);
						Map<String, Object> function = new HashMap<String, Object>();
						function.put("id", func.getInt("id"));
						function.put("name", func.getString("name"));
						function.put("url", func.getString("url"));
						function.put("js", func.getString("js"));

						JSONArray sonFuncs = func.getJSONArray("sonFunctions");
						List<Map<String, Object>> sons = new ArrayList<Map<String, Object>>();
						for (int j = 0; j < sonFuncs.length(); j++) {
							JSONObject sonFunc = sonFuncs.getJSONObject(j);
							Map<String, Object> son = new HashMap<String, Object>();
							son.put("id", sonFunc.getInt("id"));
							son.put("name", sonFunc.getString("name"));
							son.put("url", sonFunc.getString("url"));
							son.put("js", sonFunc.getString("js"));
							sons.add(son);
							funcHrefs.add(sonFunc.getString("url"));
						}
						function.put("sonFunctions", sons);
						functions.add(function);
					}
					CookieUtils.setCookieTicket(response, ticket, 30 * 24 * 3600);
					SessionUtils.setSession(request.getSession(), user, mainFunctions, funcHrefs);
					request.setAttribute("mainFunctions", mainFunctions);
					request.setAttribute("functions", functions);
					System.out.println("=============" + new JSONArray(functions));
				} else {
					response.sendRedirect(Config.LOGIN_URL);
					return null;
				}
			} else {
				response.sendRedirect(Config.LOGIN_URL);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				response.sendRedirect(Config.LOGIN_URL);
				return null;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return "index";
	}
}
