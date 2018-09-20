<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Insert title here</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ace.min.css" id="main-ace-style" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ace-fonts.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/ace.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/ace-extra.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/useropinion.js"></script>

</head>
<body>
	<jsp:include page="../include/header.jsp"></jsp:include>
	<div class="main-container" id="main-container">
		<jsp:include page="../include/left.jsp"></jsp:include>
		<div class="main-content">
			<div class="page-content">
				<div id="main_tag" class="easyui-tabs">
					<div title="3u主页">
						<center>
							<h2>欢迎访问WEB运营后台</h2>
						</center>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../include/footer.jsp"></jsp:include>
	</div>

	<div id="win" class="easyui-window" title="添加" closed="true" style="width:600px;height:400px;">
	</div>
</body>
</html>