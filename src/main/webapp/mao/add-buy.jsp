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
	
	<title>添加采购单 - H-ui.admin v3.1</title>
	<meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
	<meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">

</head>
<body>
	<div id="app">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>交货时间：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="date" class="input-text" v-model="buy.btime"  id="username" name="username">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">付款状态：</label>
			<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select  v-model="buy.bstate" class="select" size="1" name="city">
					<option value="" selected>请选择付款状态</option>
					<option value="1">已付款</option>
					<option value="0">未付款</option>
				</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>交货地点：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" v-model="buy.baddress" placeholder="" id="mobile" name="mobile">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>备注信息：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" v-model="buy.bremark" name="email" id="email">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">供应商：</label>
			<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select v-model="buy.sid" class="select" size="1" name="city">
					<option value="" selected>请选择供应商</option>
					<option v-for='(supplier,i) in supplierList' :value="supplier.sid">{{supplier.sname}}</option>
				</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">原材料：</label>
			<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select v-model="buyDetail.pid" class="select" size="1" name="city" >
					<option  value="" selected>请选择原材料</option>
					<option v-for='(parts,i) in partsList' :value="parts.pid">{{parts.pname}}</option>
				</select>
				</span> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">是否入库：</label>
			<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select v-model="buyDetail.bdstate" class="select" size="1" name="city">
				<option  value="" selected>请选择是否出库</option>
					<option :value="0" selected>未入库</option>
					<option :value="1">已入库</option>
				</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>产品数量：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" v-model="buyDetail.bdcount" placeholder="" id="mobile" name="mobile">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>产品价格：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" v-model="buyDetail.bdprice" placeholder="" id="mobile" name="mobile">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>操作人员：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" v-model="buyDetail.bdman" placeholder="" id="mobile" name="mobile">
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<button type="button" @click="addInfo" class="btn btn-primary radius" >提交</button>
			</div>
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

<script src="mao/vue.js" ></script>
<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
	
<script type="text/javascript">
var v =  new Vue({
	el:'#app',
	data:{
		partsList:[],
		supplierList:[],
		buyDetail:{
			pid:'',
			bdcount:'',
			bdprice:'',
			bdstate:'',
			bdman:''
		},
		buy:{
			bstate:'',
			btime:'',
			baddress:'',
			bremark:'',
			sid:''
		}
	},
	methods:{
		addInfo(){
			this.buy.buyDetail=this.buyDetail;
			var _this = this;
	        $.ajax({
	            type: "POST",
	            url: "/buy/addInfo",
	            data: JSON.stringify(_this.buy),
	            dataType: "json",
	            contentType: "application/json; charset=utf-8",
	            success: function (response) {
	            	console.log(_this.buy);
	            	console.log(_this.buyDetail);
	            	layer.close(layer.index);
	            	window.parent.location.reload();
	            	
	            },
	        });
		}
	},
	created(){  
        var _this = this;
        $.ajax({
            type: "GET",
            url: "/buy/getInfo",
            data: null,
            dataType: "json",
            success: function (response) {
            	_this.supplierList = response.data1;
            	_this.partsList = response.data;
            },
        });
       }
});
</script>
</body>
</html>





