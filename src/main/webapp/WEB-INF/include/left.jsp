<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(document).ready(function(){
        $(".hsub").parent().children(":first").addClass("open");
        $(".hsub").parent().children(":first").children("ul").attr("style", "display: block;");
        //功能菜单选中样式
        $(".submenu").children("li").click(function(){
            var className = $(this).attr("class");
            if(className==''){
                //清除所有选中样式
                $(".submenu").attr("style", "display: none;");
                $(".hsub").removeClass("active");
                $(".hsub").removeClass("open");
                $(".hsub").find("li").removeClass("active");

                $(this).parent().attr("style", "display: block;");
                $(this).parent().parent().parent().attr("style", "display: block;");
                $(this).parent().parent().addClass("open");
                $(this).parent().parent().parent().parent().addClass("open");

                $(this).addClass("active");
                $(this).parent().parent().addClass("active");
                $(this).parent().parent().parent().parent().addClass("active");
            }
        });

        $(".hsub").click(function(){
            var className = $(this).attr("class");
            if(className == "hsub"){
                className = $(this).parent().attr("class");
                if(className.indexOf("submenu") == -1){
                    $(".submenu").attr("style", "display: none;");
                    $(".hsub").removeClass("open");
                }else{
                    $(this).siblings().removeClass("open");
                    $(this).siblings().children(".submenu").attr("style", "display: none;");
                }
            }
        });
    });
</script>
<div id="sidebar" class="sidebar responsive">
    <div class="sidebar-shortcuts" id="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
            <button class="btn btn-success">
                <i class="ace-icon fa fa-signal"></i>
            </button>
            <button class="btn btn-info">
                <i class="ace-icon fa fa-pencil"></i>
            </button>
            <button class="btn btn-warning">
                <i class="ace-icon fa fa-users"></i>
            </button>

            <button class="btn btn-danger">
                <i class="ace-icon fa fa-cogs"></i>
            </button>
        </div>

        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span>
            <span class="btn btn-info"></span>
            <span class="btn btn-warning"></span>
            <span class="btn btn-danger"></span>
        </div>
    </div>

    <ul class="nav nav-list">
        <%-- <li class="<s:if test="#func.id == #pId">active hsub open</s:if>">--%>
        <c:forEach items="${functions}" var="item">
           <li class="active hsub open">
               <a href="#" class="dropdown-toggle">
                   <span class="menu-text"> ${item.name}</span>
                   <b class="arrow fa fa-angle-down"></b>
               </a>
               <b class="arrow"></b>
               <ul class="submenu">
                   <c:forEach var="it" items="${item.sonFunctions}">
                       <li class="">
                           <a href="javascript:;" onclick="${it.js}('${it.name}')" style="text-decoration: none;">
                               <i class="menu-icon fa fa-caret-right"></i>
                                   ${it.name}
                           </a>
                           <b class="arrow"></b>
                       </li>
                   </c:forEach>
               </ul>
           </li>
        </c:forEach>
    </ul>

    <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
        <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
    </div>
</div>