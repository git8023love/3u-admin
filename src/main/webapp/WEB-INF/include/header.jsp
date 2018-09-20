<%@page import="org.json.JSONArray"%>
<%@page import="com._3u.domain.User"%>
<%@page import="com._3u.utils.*"%>
<%@page import="com._3u.config.*"%>
<%
JSONArray mainFunctions = (JSONArray)request.getAttribute("mainFunctions");
System.out.println(mainFunctions.toString());
%>
<div id="navbar" class="navbar navbar-default">
    <div class="navbar-container" id="navbar-container">
        <button id="menu-toggler" class="navbar-toggle menu-toggler pull-left" type="button">
            <span class="sr-only"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small>
                    <i class="fa fa-leaf"></i>
                    3uTools
                </small>
            </a>
        </div>

        <style>
            .button_t { margin-right:10px; float:left; }
            .button_t a { float:left; display:inline-block; height:45px; line-height:45px; font-size:12px; color:#FFF; }
            .button_t a:hover { text-decoration:none; }
            .button_t .name { padding:0px 5px 0px 20px; }
            .button_t .bmenu { width:23px; background: url("${pageContext.request.contextPath}/image/b_3.png") center center no-repeat; }
            .button_t.on .name { background-color:#2a6e9f; }
            .button_t.on .bmenu { background-color:#246496; }
        </style>

        <div class="navbar-header pull-left">
            <div style="float: left;">
                <%if (mainFunctions != null && mainFunctions.length() > 0) { %>
                <div style="text-align: right; font-size: 20px; font-weight:bold; margin-top: 0px;margin-right:200px;">
                    <% for (int i = 0; i < mainFunctions.length(); i++) { %>
                    <div class="button_t <% if (mainFunctions.getJSONObject(i).getInt("functionId") == 7) { %>on<% } %>">
                        <a class="name" href="<%=mainFunctions.getJSONObject(i).getString("homePage") %>?ticket=${ ticket }"><%=mainFunctions.getJSONObject(i).getString("name") %></a>
                        <a class="bmenu" href="<%=mainFunctions.getJSONObject(i).getString("homePage") %>?ticket=${ ticket }" target="_blank">&nbsp;</a>
                    </div>
                    <% } %>
                </div>
                <% } %>
            </div>
        </div>
        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <div style="float: right;">
                <ul class="nav ace-nav">
                    <li class="light-blue">
                        <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                            <img class="nav-user-photo" src="assets/avatars/user.jpg"/>
                            <span class="user-info">
								<small>欢迎,</small>
								<%=((User)session.getAttribute(Constants.SESSION_USER)).getRealName() %>
							</span>

                            <i class="ace-icon fa fa-caret-down"></i>
                        </a>

                        <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                            <li class="divider"></li>
                            <li>
                                <a href="<%=Config.LOGOUT_URL %>?ticket=${ ticket }">
                                    <i class="ace-icon fa fa-power-off"></i>
                                    退出
                                </a>
                            </li>
                        </ul>
                    </li>

                </ul>
            </div>
        </div>
    </div>
</div>