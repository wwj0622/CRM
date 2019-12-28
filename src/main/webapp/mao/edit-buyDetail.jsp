<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<base href="<%=basepath %>" />
	<meta charset="utf-8">
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
	<script src="../js/axios.min.js"></script>
	<title>添加采购单 - H-ui.admin v3.1</title>
	<meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
	<meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">

</head>
<body>
	<from>
	<div  id="app">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">原材料名称：</label>
			<div class="formControls col-xs-8 col-sm-9"> 
				<span class="select-box">
					<select v-model="buyDetail.partsId"  class="select" >				
                           <option v-for="(s,i) in parts" :value="s.pid">{{s.pname}}</option>
					</select>
				</span>
			</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>采购数量：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="hidden" id="box" value="${param.bdid }" >
				<input type="text" class="input-text" v-model="buyDetail.bdcount" placeholder="" id="" name="mobile">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>采购价格：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" v-model="buyDetail.bdprice" name="email" id="email">
			</div>
		</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">是否入库：</label>
			<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select v-model="buyDetail.bdstate" class="select" size="1" name="city">
					<option :value="0" selected>未入库</option>
					<option :value="1">已入库</option>
				</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<button type="button" @click="updateBy" class="btn btn-primary radius" >提交</button>
			</div>
		</div>
		</div>
	</from>
	
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

<script src="mao/vue.js" ></script>
<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>


<script type="text/javascript">
var v =  new Vue({
	el:'#app',
	data:{
		parts:[],
		buyDetail:{}
	},
	methods:{
		selectSupplier:function(){
			var _thiss = this;
			$.ajax({
	            type: "GET",
	            url: "/buy/getInfo",
	            data: null,
	            dataType: "json",
	            success: function (response) {
	            	_thiss.parts = response.data; 
	            	consloe.log(_thiss.parts);
	            },
	        }); 
	        
			//this.supplier = [{sname:'111'}];
	        
	        
		},
		a:function(){
			var _this = this;
	        var bdid = document.getElementById("box").value;
	        $.ajax({
	            type: "GET",
	            url: "/buy/getBuyDetailByBdid",
	            data: {bdid:bdid},
	            dataType: "json",
	            success: function (response) {
	            	_this.buyDetail = response.data;
	            },
	        });
		},
		updateBy:function(){
			var _this = this;
			_this.buyDetail.parts={};
	        $.ajax({
	            type: "POST",
	            url: "/buy/updateBuyDetail",
	            data: _this.buyDetail,
	            dataType: "json",
	            success: function (response) {
	            	layer.close(layer.index);
	            	window.parent.location.reload();
	            },
	        });
		}
	},
	created:function(){ 
       this.a();
       this.selectSupplier();

     
       
	}
	
});
	

</script>	

</body>
</html>





