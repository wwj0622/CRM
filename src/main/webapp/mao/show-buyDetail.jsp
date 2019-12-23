<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
	<base href="<%=basepath %>" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<link rel="Bookmark" href="/favicon.ico" >
	<link rel="Shortcut Icon" href="/favicon.ico" />
	<!--[if lt IE 9]>
	<script type="text/javascript" src="lib/html5shiv.js"></script>
	<script type="text/javascript" src="lib/respond.min.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
	<!--[if IE 6]>
	<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
	<script>DD_belatedPNG.fix('*');</script>
	<![endif]-->
	<!--/meta 作为公共模版分离出去-->
	
	<title>采购单详情- H-ui.admin v3.1</title>
	<meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
	<meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>

	<div class="page-container" id="app">
	<div class="text-c"> 
		<input type="hidden" id="box" value="${param.bid }" >
	</div>
	<h1>采购单详情</h1> <a href="mao/show-buy.jsp">点击返回查看采购单</a>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> 
	
		<span class="l">
			<a href="javascript:;"  @click="delAllSupllier" class="btn btn-danger radius">
				<i class="Hui-iconfont">&#xe6e2;</i> 批量删除
			</a> 
			<a href="javascript:;"  @click="updateAllBuydetail" class="btn btn-success radius">
				<i class="Hui-iconfont"></i> 批量入库
			</a> 
			<a href="javascript:;" onclick="member_add('添加采购单详情','mao/add-buy.jsp','','510')" class="btn btn-primary radius">
				<i class="Hui-iconfont">&#xe600;</i> 添加采购单详情
			</a>
		</span> 
		<span class="r">共有数据：<strong>88</strong> 条</span> 
	</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="25"></th>
					<th >采购订单号</th>
					<th width="100">采购原材料名称</th>
					<th width="100">采购数量</th>
					<th width="100">采购价格</th>
					<th width="50">操作人员</th>
					<th >操作时间</th>
					<th width="100">入库状态</th>
					<th width="50">操作</th>
				</tr>
			</thead>
			<tbody>
			 
			<tr class="text-c" v-for='(buyDetail,i) in buyDetailList'>
					<td><input name=""  v-model="checkedBuy" type="checkbox" :value="buyDetail.bdid"></td>
					<td class="text-l">{{buyDetail.bid}}</td>
					<td>{{buyDetail.parts.pname}}</td>
					<td>{{buyDetail.bdcount}}</td>
					<td>{{buyDetail.bdprice}}</td>
					<td>{{buyDetail.bdman}}</td>
					<td>{{buyDetail.bdupdateTime}}</td>
					<td>{{buyDetail.bdstate==1?"已入库":"未入库"}} <button @click="changeState(buyDetail.pid,buyDetail.bdcount,buyDetail.bdid)">点击入库</button> </td>
					<td class="f-14 product-brand-manage">
						<a style="text-decoration:none" @click="member_edit('编辑采购单详情','mao/edit-buyDetail.jsp?bdid='+buyDetail.bdid,'4','','510')" href="javascript:;" title="编辑">
							<i class="Hui-iconfont">&#xe6df;</i>
						</a> 
						<a style="text-decoration:none" class="ml-5" @click="member_del(this,buyDetail.bdid)" href="javascript:;" title="删除">
							<i class="Hui-iconfont">&#xe6e2;</i>
						</a>
					</td>
				</tr>
			 <tr>
			</tbody>
		</table>
	</div>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本--> 
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-member-add").validate({
		rules:{
			username:{
				required:true,
				minlength:2,
				maxlength:16
			},
			sex:{
				required:true,
			},
			mobile:{
				required:true,
				isMobile:true,
			},
			email:{
				required:true,
				email:true,
			},
			uploadfile:{
				required:true,
			},
			
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			//$(form).ajaxSubmit();
			var index = parent.layer.getFrameIndex(window.name);
			//parent.$('.btn-refresh').click();
			parent.layer.close(index);
		}
	});
});

</script> 


<script src="mao/vue.js" ></script>
<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
	

<script type="text/javascript">


var v =  new Vue({
	el:'#app',
	data:{
		buyDetailList:[],
		checkedBuy:[],
	},
	methods:{
		 member_edit(title,url,id,w,h){
        	 layer_show(title,url,w,h);
         },
        
         member_del(obj,bdid){
         	var _this = this;
    			$.ajax({
    				type: "GET",
    	            url: "/buy/delBuyDetail",
    	            data: {bdid:bdid},
    	            dataType: "json",
    				success: function(data){
    					$(obj).parents("tr").remove();
    					layer.msg('已删除!',{icon:1,time:1000});
    					window.parent.location.reload();
    				},
    				error:function(data) {
    					console.log(data.msg);
    				},
    			});		
          },
          delAllSupllier(){
         	 console.log(this.checkedBuy);
         	 var _this = this;
     			$.ajax({
     				type: "GET",
     	            url: "/buy/delBuyDetail",
     	            data: {bdid:_this.checkedBuy},
     	            dataType: "json",
     	            traditional: true,
     				success: function(data){
     					layer.msg('已删除!',{icon:1,time:1000});
     					window.parent.location.reload();
     				},
     				error:function(data) {
     					console.log(data.msg);
     				},
     			});		
          },
          changeState(pid,bdcount,bdid){
        	  var _this = this;
   			$.ajax({
   	            type: "POST",
   	            url: "/buy/updateState",
   	            data: {bdid:bdid},
   	            dataType: "json",
   	            success: function (response) {
   	            	layer.msg('入库成功!',{icon:1,time:1000});
					window.parent.location.reload();
   	            },
   	        });
          },
          updateAllBuydetail(){
        	  var _this = this;
        	  console.log(_this.checkedBuy);
     			$.ajax({
     	            type: "POST",
     	            url: "/buy/updateState",
     	            data: {bdid:_this.checkedBuy},
     	           	traditional: true,
     	            dataType: "json",
     	            success: function (response) {
     	            	layer.msg('入库成功!',{icon:1,time:1000});
    					window.parent.location.reload();
     	            },
     	        });
          }
	},
	created(){  
        var _this = this;
        var bid = document.getElementById("box").value;
        $.ajax({
            type: "GET",
            url: "/buy/getBuyDetailBybid",
            data: {bid:bid},
            dataType: "json",
            success: function (response) {
            	_this.buyDetailList = response.data;
            },
        });
       }
});
</script> 
</body>
</html>
