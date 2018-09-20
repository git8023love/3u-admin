/**
 * 用户反馈
 * @param title
 * @return
 */
function addUserOpinion(title){
	if ($('#main_tag').tabs('exists', title)) {
		$('#main_tag').tabs('select', title);
	} else {
		$('#main_tag').tabs('add', {
			title : title, 
			closable : true,
			content:'<table>'+
						'<tr>'+
							'<td>意见类型：<input style="width:150px;" class="easyui-combobox" id="uopinion_type" data-options="data:userOpinionTypeSel,valueField:\'id\',textField:\'text\',panelHeight:\'auto\'"/></td>'+
							'<td>状态：<input style="width:150px;" class="easyui-combobox" id="uopinion_status" data-options="data:opinionOperStatusAllSel,valueField:\'id\',textField:\'text\',panelHeight:\'auto\'"/></td>'+
							'<td><a onclick="getUserOpinions()" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>'+
						'</tr>'+
					'</table>'+
					'<div id="userOpinions_datagrid"></div>'
		});
		getUserOpinions();
	}
}


userOpinionTypeSel = [{"id":-1,"text":"全部","selected":true},{"id":1,"text":"产品建议"},{"id":2,"text":"设备链接"},{"id":3,"text":"应用下载"},{"id":4,"text":"刷机越狱"},{"id":5,"text":"备份恢复"},{"id":6,"text":"铃声壁纸"},{"id":7,"text":"照片视频"},{"id":8,"text":"其它问题"}];
opinionOperStatusAllSel = [{"id":-1,"text":"全部","selected":true},{"id":1,"text":"未处理"},{"id":2,"text":"处理中"},{"id":3,"text":"已处理"},{"id":4,"text":"已关闭"}];
opinionOperStatusSel = [{"id":1,"text":"未处理"},{"id":2,"text":"处理中"},{"id":3,"text":"已处理"},{"id":4,"text":"已关闭"}];
opinionPCosSel = [{"id":"10.0","text":"Windows 10"},{"id":"6.3","text":"Windows 8.1"},{"id":"6.2","text":"Windows 8"},{"id":"6.1","text":"Windows 7"},{"id":"6.0","text":"Windows Vista"},{"id":"5.1","text":"Windows XP"},{"id":"5.0","text":"Windows 2000"},{"id":"5.2","text":"Windows XP 64-Bit Edition"}];
                     
                    
function getUserOpinions() {
	 var lastIndex;  
    $('#userOpinions_datagrid').datagrid({  
   		url:'/opinion/list',
           width:$(window).width()-200,
   		   height:$(window).height()-200,
           nowrap: true,  
           autoRowHeight: false,  
           striped: true,  
           collapsible:true,  
           remoteSort: false,  
           pageSize:50,
    	   pageList:[50,60,80,100,120,140,160,180,200],
   		   pagination:true,  
           rownumbers:true, 
           columns:[[  
                {field:'ck',checkbox:true}, 
	             {field:'id',title:'ID',width:50,align:'center'},
	             {field:'type',title:'反馈类型',width:250,align:'center',
	            	 formatter:function(value,row,index){
	                    return opinionType(value);
	                }
	             },
	             {field:'context',title:'反馈内容',width:350,align:'center',fixed:true},
	             {field:'contactWay',title:'联系方式',width:300,align:'center'},
	             {field:'image',title:'图片',width:100,align:'center',
	            	 formatter:function(value, row, index){
	            	 	if(value != '') {
	            	 		return '<span  class="appinfo_stype" onclick="showImage('+index+')">查看</span>';
	            	 	}
		        	 }
	             },
	             {field:'createtime',title:'提交时间',width:150,align:'center',
	            	 formatter:function(value,row,index){
	            	 	if(value){
	            	 		var newTime = new Date(value);
	            	 		value = newTime.getFullYear()+"-"+(newTime.getMonth()+1)+"-"+newTime.getDate()+" "+newTime.getHours()+":"+newTime.getMinutes()+":"+newTime.getSeconds();
	            	 		return value;
	            	 	}
            	 	}
	             },
	             {field:'status',title:'状态',width:100,align:'center',
	            	 formatter:function(value,row,index){
	            	 	return value == 1 ? '<font color="red">未处理</font>' : value == 2 ? '<font color="green">处理中</font>' : value == 3 ? '<font color="#0066CC">已处理</font>' : '<font color="gray">已关闭</font>';
	             	}
	             },
	             {field:'OPRETION',title:'操作',width:200,align:'center',
	            	 formatter:function(value, row, index){
            	 		return '<span  class="appinfo_stype" onclick="viewDetails('+index+')">查看详情</span>&nbsp;&nbsp;'+
		            	 	   ((row.status == 4 || row.status == 3) ? '' : '<span class="appinfo_stype" onclick="opinionOper('+index+')">反馈处理</span>');
		        	 }
	             },
	             {field:'opertime',title:'操作时间',width:150,align:'center',
	            	 formatter:function(value,row,index){
		            	 if(value){
		            	 	var newTime = new Date(value);
			        		value = newTime.getFullYear()+"-"+(newTime.getMonth()+1)+"-"+newTime.getDate()+" "+newTime.getHours()+":"+newTime.getMinutes()+":"+newTime.getSeconds();
			        		return value;
		            	 }
	             	}
	             },
	             {field:'operuser',title:'操作人',width:150,align:'center'},
	             {field:'note',title:'备注',width:350,align:'center'}
           ]],
           toolbar:[{  
        	    id:'uopinion_opt',  
	            text:'批量处理',  
	            iconCls:'icon-remove',  
	            handler:function(){  
       	 			batchOpertionUserOpinions();
	            } 
           }],  
           onBeforeLoad:function(){  
               $(this).datagrid('rejectChanges');  
           },  
           queryParams:{
        	   type: $('#uopinion_type').combobox('getValue'),
           	   status: $('#uopinion_status').combobox('getValue')
		  }
    });
}

function opinionType(value){
	var txt = '', type = value.split(",");
 	for(var j=0; j<type.length; j++){
 		off:
        for(var i=0; i<userOpinionTypeSel.length; i++){
            if(userOpinionTypeSel[i].id == type[j]){
            	txt += userOpinionTypeSel[i].text+",";
            	break off;
            }
        }
 	}
 	return txt.substring(0, txt.length-1);
}

function batchOpertionUserOpinions() {
	var rows = $('#userOpinions_datagrid').datagrid('getSelections'), ids='';
	$.each(rows, function(i,item){
		ids += item.id+","
	});
	ids = ids.substring(0, ids.length-1);
	var content = '<br />'+
				  '	<table id="uopinion_opinionOper" style="width:290px;table-layout: fixed;">'+
				  '		 <tr><td class="title" style="width:25%;text-align: right;">处理ID：</td><td id="uopinion_opt_ids" style="word-wrap: break-word;">'+ids+'</td></tr>'+
				  '		 <tr style="line-height: 40px;"><td class="title" style="width:25%;text-align: right;">状态：</td><td><input id="uopinion_opt_status" class="easyui-combobox" data-options="data:opinionOperStatusSel,valueField:\'id\',textField:\'text\',panelHeight:\'auto\'" style="width:198px"/></td></tr>'+
				  '		 <tr style="line-height: 40px;"><td class="title" style="width:25%;text-align: right;">备注：</td><td><textarea id="uopinion_opt_note" type="text" value="" style="width:200px; height:50px;"></textarea></td></tr>'+
				  '		 <tr style="text-align: center;font-weight: bold;line-height: 40px;"><td colspan="2"><input type="button" id="uopinion_opt_sub_btn" value="提 交"/></td></tr>'+
				  '	</table>';
	$('#win').window({
	width: 305,
	height: 350,
	top:( window.screen.height-800)/2-100,
	left: (window.screen.width-550)/2-120,
	title: '反馈处理',
	content: content
	});
	$('#win').window('open');
	$('#win').find('#uopinion_opt_sub_btn').bind("click", function(e){
		if($.trim($('#uopinion_opt_note').val()) == '') {
			 $.messager.alert('错误提示','<font color="red">备注</font>不能为空!','error');
			return false;
		}
		if($.trim($('#uopinion_opt_status').combobox('getValue')) == '') {
			 $.messager.alert('错误提示','请选择<font color="red">状态</font>!','error');
			return false;
		}
		var status = $('#uopinion_opt_status').combobox('getValue'), note = $('#uopinion_opt_note').val();
		$.post("userOpinion_update.action",{"ids": ids, "userOpinion.status": status, "userOpinion.note": note}, function(data){
            if(data.message == 'ok') {
            	$.messager.alert('提示','成功！','info');
            	 $('#userOpinions_datagrid').datagrid('reload');
            	$('#win').window('close');
            } else {
            	$.messager.alert('提示','<font color="red">失败</font>！','info');
            }
        },"json");
	});
}


function showImage(index) {
	var content='<div class="" id="uopinion_showImage">'+
		'<div class="ft-carousel" id="carousel_1"><ul class="carousel-inner" style="margin: 0 0;"></ul></div></div>';
	$('#win').window({
		width:1015,
		height:768,
		top:(window.screen.height-800)/2-100,
		left:(window.screen.width-550)/2-120,
		title:'查看图片',
		content:content
	});
	$('#win').window('open');
	var row = $('#userOpinions_datagrid').datagrid('getRows')[index];
	var image = JSON.parse(row.image),servernum=row.servermum;
	var url = (servernum==1 ? 'http://47.91.207.47:86/useropinion/' : servernum==2 ? 'http://118.31.185.46:86/useropinion/' : servernum==3 ? 'http://115.29.215.171:86/useropinion/' : 'http://120.26.30.12:86/useropinion/');
	url = "http://47.91.207.47:86/useropinion/";
	//image = ['https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1535437328949&di=572178a3db2a7b901eb54f88ec027f6a&imgtype=0&src=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fitem%2F201404%2F23%2F20140423095926_zeQc4.thumb.600_0.jpeg','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1535437328948&di=27a6995a78f44b71e63234746a0fe633&imgtype=0&src=http%3A%2F%2Fscb.jianbihua.org%2Fimage%2F201508%2F2015083115503357.jpg','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1535445049939&di=b1b849864d3cc6e5c136adacf46be1ec&imgtype=0&src=http%3A%2F%2Fimage14-c.poco.cn%2Fmypoco%2Fmyphoto%2F20130209%2F14%2F64992120201302091404462711628005230_012.jpg%3F2564x1728_120']
	$.each(image, function(i,v){
		var ow,oh,w=1000,h=720;
		//imgReady(v, function(){//test
		imgReady(url+v, function(){
			ow = this.width,oh =this.height;
			var html = '<li class="carousel-item"><img style="';
			if(ow > w && oh > h) {
				var flag = (ow/w) > (oh/h) ? true : false;
				if(flag) {
					html +='width:'+w+'px;'
    			}else{
    				html += 'height:'+h+'px;'
    			}
			} else if(ow > w){
				html += 'width:'+w+'px;'
    		} else if(oh > h){
    			html += 'height:'+h+'px;'
    		}
			html += '" src="'+url+v+'" /></li>'
			//html += '" src="'+v+'" /></li>'//test
			$('.carousel-inner').append(html);
		});
	})		
	setTimeout('$("#carousel_1").FtCarousel()',1000);
}

function viewDetails(index) {
	$('#win').window({
		width:1015,
		height:768,
		top:(window.screen.height-800)/2-100,
		left:(window.screen.width-550)/2-120,
		title:'查看详情',
		content: '<table id="uopinion_viewDetails" style="width:1000px;table-layout: fixed;"></table>'
		});
		$('#win').window('open');
		var row = $('#userOpinions_datagrid').datagrid('getRows')[index];
		if(row.Pcguid != ''){
			$('#uopinion_viewDetails').append('<tr style="border-bottom: 1px solid;"><td style="width:25%;text-align:right;line-height:30px;padding-right:30px;">PC设备标识:</td><td style="word-wrap: break-word;">'+row.pcguid.replace('%7B','{').replace('%7D','}')+'</td></tr>');
		}
		if(row.Pcos != ''){
			$('#uopinion_viewDetails').append('<tr style="border-bottom: 1px solid;"><td style="width:25%;text-align:right;line-height:30px;padding-right:30px;">PC端系统:</td><td style="word-wrap: break-word;">'+opinionPCos(row.pcos)+'('+row.pcos+')</td></tr>');
		}
		if(row.Pcvs != ''){
			$('#uopinion_viewDetails').append('<tr style="border-bottom: 1px solid;"><td style="width:25%;text-align:right;line-height:30px;padding-right:30px;">PC版本号:</td><td style="word-wrap: break-word;">'+row.pcvs+'</td></tr>');
		}
		if(row.Ip != ''){
			$('#uopinion_viewDetails').append('<tr style="border-bottom: 1px solid;"><td style="width:25%;text-align:right;line-height:30px;padding-right:30px;">Ip地址:</td><td style="word-wrap: break-word;">'+row.ip+'</td></tr>');
		}
		if(row.ContactWay != ''){
			$('#uopinion_viewDetails').append('<tr style="border-bottom: 1px solid;"><td style="width:25%;text-align:right;line-height:30px;padding-right:30px;">联系方式:</td><td style="word-wrap: break-word;">'+row.contactWay+'</td></tr>');
		}
		if(row.Type != ''){
			$('#uopinion_viewDetails').append('<tr style="border-bottom: 1px solid;"><td style="width:25%;text-align:right;line-height:30px;padding-right:30px;">反馈类型:</td><td style="word-wrap: break-word;">'+opinionType(row.type)+'</td></tr>');
		}
		if(row.Context != ''){
			$('#uopinion_viewDetails').append('<tr style="border-bottom: 1px solid;"><td style="width:25%;text-align:right;line-height:30px;padding-right:30px;">反馈内容:</td><td style="word-wrap: break-word;">'+row.context+'</td></tr>');
		}
		// if(row.Image != ''){
		// 	var image = JSON.parse(row.image),servernum=row.servernum;
		// 	var url = (servernum==1 ? 'http://120.27.160.46:86/useropinion/' : servernum==2 ? 'http://118.31.185.46:86/useropinion/' : servernum==3 ? 'http://115.29.215.171:86/useropinion/' : 'http://120.26.30.12:86/useropinion/');
		// 	url = "http://47.91.207.47:86/useropinion/";
		// 	var ht= '<tr style="border-bottom: 1px solid;"><td style="width:25%;text-align:right;line-height:30px;padding-right:30px;">反馈图片:</td><td>';
		// 	//var item = [1,6,7]; //test
		// 	//image = ['https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1535437328949&di=572178a3db2a7b901eb54f88ec027f6a&imgtype=0&src=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fitem%2F201404%2F23%2F20140423095926_zeQc4.thumb.600_0.jpeg','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1535437328948&di=27a6995a78f44b71e63234746a0fe633&imgtype=0&src=http%3A%2F%2Fscb.jianbihua.org%2Fimage%2F201508%2F2015083115503357.jpg','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1535445049939&di=b1b849864d3cc6e5c136adacf46be1ec&imgtype=0&src=http%3A%2F%2Fimage14-c.poco.cn%2Fmypoco%2Fmyphoto%2F20130209%2F14%2F64992120201302091404462711628005230_012.jpg%3F2564x1728_120']
		// 	$.each(image, function(i,v){
		// 		var ow,oh,w=200,h=140,html='<img onclick="showImage('+index+')" src="'+url+v+'" style="margin-right: 10px;';
		// 		//var html='<img onclick="showImage('+index+')" src="'+v+'" style="margin-right: 10px;'; //test
		// 		//imgReady('/image/a'+v+'.jpg', function(){ //test
		// 		//imgReady(v, function(){ //test
		// 		imgReady(url+v, function(){
		// 			ow = this.width,oh =this.height;
		// 			if(ow > w && oh > h) {
		// 				var flag = (ow/w) > (oh/h) ? true : false;
		// 				if(flag) {
		// 					html +='width:'+w+'px;'
	    //     			}else{
	    //     				html += 'height:'+h+'px;'
	    //     			}
		// 			} else if(ow > w){
	    // 				html += 'width:'+w+'px;'
	    //     		} else if(oh > h){
	    //     			html += 'height:'+h+'px;'
	    //     		}
		// 		});
		// 		ht+=(html+'"/>');
		// })
		// $('#uopinion_viewDetails').append(ht+'</td></tr>');
	//}
		                                                      
}

function opinionPCos(pcOs){
	var txt = '',os = pcOs.substr(0,pcOs.indexOf(".")+2), osw = pcOs.substr(pcOs.lastIndexOf("_")+1, pcOs.length-1);
	for(var i=0; i<opinionPCosSel.length; i++){
        if(opinionPCosSel[i].id == os){
        	txt += opinionPCosSel[i].text+",";
        	break;
        }
    }
	return txt=='' ? '其他' : txt.substring(0, txt.length-1)+" "+ osw+"位";
	
}

function opinionOper(index) {
	var content = '<br />'+
				  '	<table id="uopinion_opinionOper">'+
				  '		 <tr><td><input type="hidden" id="uopinion_oper_type_id" value="0" /></td></tr>'+
				  '		 <tr style="line-height:30px;"><td class="app_info w100">反馈类型：</td><td><input id="uopinion_oper_type" type="text" value="" disabled style="width:198px"/></td></tr>'+
				  '		 <tr style="line-height:30px;"><td class="app_info w100">反馈内容：</td><td><textarea id="uopinion_oper_context" type="text" value="" disabled style="width:200px; height:80px;"></textarea></td></tr>'+
				  '		 <tr style="line-height:30px;"><td class="app_info w100">状态：</td><td><input id="uopinion_oper_status" class="easyui-combobox" data-options="data:opinionOperStatusSel,valueField:\'id\',textField:\'text\',panelHeight:\'auto\'" style="width:198px"/></td></tr>'+
				  '		 <tr style="line-height:30px;"><td class="app_info w100">备注：</td><td><textarea id="uopinion_oper_note" type="text" value="" style="width:200px; height:50px;"></textarea></td></tr>'+
				  '		 <tr><td style="text-align: center;font-weight: bold; margin-top: 15px;" colspan="2" class="common_submit"><a id="uopinion_oper_sub_btn" class="easyui-linkbutton" data-options="iconCls:\'icon-save\'">保 存</a></td></tr>'+
				  //'		 <tr style="text-align: center;font-weight: bold;line-height: 40px;"><td colspan="2"><input type="button" id="uopinion_oper_sub_btn" value="提 交"/></td></tr>'+
				  '	</table>';
	$('#win').window({
		width: 350,
		height: 350,
		top:( window.screen.height-800)/2-100,
		left: (window.screen.width-550)/2-120,
		title: '反馈处理',
		content: content
	});
	$('#win').window('open');
	var row = $('#userOpinions_datagrid').datagrid('getRows')[index];
	$('#uopinion_oper_type_id').val(row.id);
	$('#uopinion_oper_type').val(opinionType(row.type));
	$('#uopinion_oper_context').val(row.context);
	$('#uopinion_oper_status').combobox('setValue',row.status);
	$('#uopinion_oper_note').val(row.note);
	
	$('#win').find('#uopinion_oper_sub_btn').bind("click", function(e){
		if($.trim($('#uopinion_oper_note').val()) == '') {
			 $.messager.alert('错误提示','<font color="red">备注</font>不能为空!','error');
			return false;
		}
		var id = $('#uopinion_oper_type_id').val(), status = $('#uopinion_oper_status').combobox('getValue'), note = $('#uopinion_oper_note').val();
		$.post("userOpinion_update.action",{"ids": id, "userOpinion.status": status, "userOpinion.note": note}, function(data){
            if(data.message == 'ok') {
            	$.messager.alert('提示','成功！','info');
            	 $('#userOpinions_datagrid').datagrid('reload');
            	$('#win').window('close');
            } else {
            	$.messager.alert('提示','<font color="red">失败</font>！','info');
            }
        },"json");
	});
}
/**
 * 预加载
 */
var imgReady = (function(){
    var list = [],
        intervalId = null;

    // 用来执行队列
    var queue = function(){
        for(var i = 0; i < list.length; i++){
            list[i].end ? list.splice(i--,1) : list[i]();
        }
        !list.length && stop();
    };
    
    // 停止所有定时器队列
    var stop = function(){
        clearInterval(intervalId);
        intervalId = null;
    }
    
    return function(url, ready, error) {
        var onready = {}, 
            width, 
            height, 
            newWidth, 
            newHeight,
            img = new Image();
        img.src = url;

        // 如果图片被缓存，则直接返回缓存数据
        if(img.complete) {
            ready.call(img);
            return;
        }
        width = img.width;
        height = img.height;

        // 加载错误后的事件 
        img.onerror = function () {
            error && error.call(img);
            onready.end = true;
            img = img.onload = img.onerror = null;
        };

        // 图片尺寸就绪
        var onready = function() {
            newWidth = img.width;
            newHeight = img.height;
            if (newWidth !== width || newHeight !== height ||
                // 如果图片已经在其他地方加载可使用面积检测
                newWidth * newHeight > 1024
            ) {
                ready.call(img);
                onready.end = true;
            };
        };
        
        onready();
        
        // 完全加载完毕的事件
        img.onload = function () {
            // onload在定时器时间差范围内可能比onready快
            // 这里进行检查并保证onready优先执行
            !onready.end && onready();
            // IE gif动画会循环执行onload，置空onload即可
            img = img.onload = img.onerror = null;
        };
        
        // 加入队列中定期执行
        if (!onready.end) {
            list.push(onready);
            // 无论何时只允许出现一个定时器，减少浏览器性能损耗
            if (intervalId === null) {
                intervalId = setInterval(queue, 40);
            };
        };
    }
})();

/**
 * 轮播
 */
!function(t) {
    function i(t, i) {
        this.init(t, i)
    }
    i.prototype = {
        init: function(i, n) {
            this.ele = i,
            this.opts = t.extend({},
            {
                index: 0,
                auto: !0,
                time: 4e3,
                indicators: !0,
                buttons: !0
            },
            n),
            this.index = this.opts.index,
            this.render(),
            this.eventBind()
            //this.opts.auto && this.loop() 关闭自动轮播
        },
        render: function() {
            this.renCas(),
            this.opts.indicators && this.renIns(),
            this.opts.buttons && this.renBtns()
        },
        renCas: function() {
            var t = this.ele.find(".carousel-inner"),
            i = t.find(".carousel-item"),
            n = i.length,
            e = i.eq(n - 1).clone(),
            s = i.eq(0).clone(),
            o = this.ele.width();
            this.index = this.index < 0 || this.index > n - 1 ? 0 : this.index,
            t.width((n + 2) * o).prepend(e).append(s).css("left", (this.index + 1) * -o),
            t.find(".carousel-item").width(o)
        },
        renIns: function() {
            for (var t = this.ele.find(".carousel-item").length - 2, i = '<div class="carousel-indicators1">', n = 0; n < t; n++) i += '<span data-index="' + n + '"></span>';
            i += "</div>",
            this.ele.append(i).find(".carousel-indicators1 span").eq(this.index).addClass("active")
        },
        renBtns: function() {
            this.ele.append('<span class="carousel-btn carousel-prev-btn"></span><span class="carousel-btn carousel-next-btn"></span>')
        },
        animate: function(t) {
            var i = this,
            n = this.ele.find(".carousel-inner"),
            e = this.ele.width(),
            s = n.find(".carousel-item").length;
            this.opts.indicators;
            n.stop(!0, !0).animate({
                left: n.position().left + t
            },
            function() {
                var o = n.position().left;
                t < 0 && o < -e * (s - 2) && n.css("left", -e),
                t > 0 && o > -e && n.css("left", -e * (s - 2)),
                i.index = n.position().left / -e - 1,
                i.opts.buttons && i.showBtn()
            })
        },
        showBtn: function() {
            this.ele.find(".carousel-indicators1 span").removeClass("active").eq(this.index).addClass("active")
        },
        loop: function() {
            var t = this.ele.find(".carousel-next-btn");
            this.timer = setInterval(function() {
                t.trigger("click")
            },
            this.opts.time)
        },
        eventBind: function() {
            var i = this,
            n = this.ele.find(".carousel-prev-btn"),
            e = this.ele.find(".carousel-next-btn"),
            s = this.ele.find(".carousel-indicators1"),
            o = this.ele.width(),
            a = (this.ele.find(".carousel-item").length, this.opts.auto);
            e.on("click",
            function() {
                i.animate( - o)
            }),
            n.on("click",
            function() {
                i.animate(o)
            }),
            s.on("click", "span",
            function() {
                i.animate((t(this).data("index") - i.index) * -o)
            }),
            a && this.ele.hover(function() {
                clearInterval(i.timer)
            },
            function() {
                i.loop()
            })
        }
    },
    t.fn.FtCarousel = function(n) {
        return this.each(function() {
            new i(t(this), n)
        })
    }
} (jQuery);
