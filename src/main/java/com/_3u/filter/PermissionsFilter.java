package com._3u.filter;


import com._3u.config.*;
import com._3u.utils.Constants;
import com._3u.utils.CookieUtils;
import com._3u.utils.RemoteAdressUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PermissionsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        // 得到页面的访问路径
        String path = req.getServletPath();
        if (path == null) {// 如果没有路径，跳到登录页面
            res.sendRedirect(Config.LOGIN_URL);
            return;
        }
        if (path.contains("/user_saveOrUpdate.action")
                || path.contains("/user_login.action")
                || path.contains("/user_logout.action")
                || path.contains("/user_index.action")
                || path.contains("/app_sort.action")
                || path.contains("/app_sync.action")
                || path.contains("/app_updateCache.action")
                || path.contains("controller.jsp")
                || path.contains("/otherTool_itunes.action")
                || path.contains("/otherTool_updateItunes.action")) {// 不过滤的页面
            chain.doFilter(request, response);
            return;
        }
        String ticket = CookieUtils.getCookieValue(req, Constants.COOKIE_TICKET);
        ticket = "12345698778965412336985214712365";
        Set<String> funcHrefSet = null;
        if (ticket != null && ticket.length() == 32) {
            /*String json = HTMLUtils.postURLConnection(Config.TICKET_URL, "system=7&ticket=" + ticket);*/
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
                    "{\"id\":355,\"sonFunctions\":[{\"id\":356,\"js\":\"addVersions\",\"name\":\"产品中心\",\"pid\":355,\"url\":\"version_list.action\"}," +
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
            JSONObject jsonObject = new JSONObject(json);
            String ip = jsonObject.getString("ip");
            String nowIp = RemoteAdressUtils.getIpValue(req);
            // if (ip.split(",").length == 1 && !ip.equals(nowIp)) {
            if (!ip.contains(nowIp)) {
                // 重新设定ticket缓存值
                //HTMLUtils.postURLConnection(Config.TICKET_RESET_URL, "ip=" + nowIp + "&ticket=" + ticket);
            } /*else if (!ip.contains(nowIp)) {
				res.sendRedirect(Config.LOGIN_URL);
				return;
			}*/
            //System.out.println(jsonObject.toString());
            JSONArray sonFunctions = jsonObject.getJSONArray("sonFunctions");
            funcHrefSet = new HashSet<String>();
            for (int i = 0; i < sonFunctions.length(); i++) {
                JSONObject func = sonFunctions.getJSONObject(i);
                JSONArray sonFuncs = func.getJSONArray("sonFunctions");
                for (int j = 0; j < sonFuncs.length(); j++) {
                    JSONObject sonFunc = sonFuncs.getJSONObject(j);
                    funcHrefSet.add(sonFunc.getString("url"));
                }
            }
        } else {
            res.sendRedirect(Config.LOGIN_URL);
            return;
        }
        // 从session中取值
        try {
            // 没有功能权限返回登录页面
            if (funcHrefSet == null) {
                res.sendRedirect(Config.LOGIN_URL);
                return;
            }
            // 截取路径的值，路径格式统一为/*/*_*_*,所以截取最后一个“/”到最后一个“_”符号之间的字符
            int begin = path.lastIndexOf("/") + 1;
            int end = path.lastIndexOf("_");
            if (end < 1) {
                end = path.indexOf(".");
            }
            if (begin < end) {
                path = path.substring(begin, end);
            }
            for (Iterator iterator = funcHrefSet.iterator(); iterator.hasNext();) {
                String href = (String) iterator.next();
                //if (href != null && href.startsWith(path)) {// 有该路径的权限，直接返回
                chain.doFilter(request, response);
                return;
                //}
            }
            // 没有权限，全部跳到登录页面,可以做其他处理
            res.sendRedirect(Config.LOGIN_URL);
            // chain.doFilter(request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect(Config.LOGIN_URL);
            return;
        }
    }

    @Override
    public void destroy() {

    }
}
