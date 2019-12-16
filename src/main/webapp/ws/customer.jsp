<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<base href="<%=basepath %>" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<script src="/js/axios.min.js"></script>
<script src="/ws/js/jquery.min.js"></script>
<script src="/ws/js/vue.js"></script>
<!-- 引入样式 -->
<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
<!-- import Vue before Element -->
<script src="https://unpkg.com/vue/dist/vue.js"></script>
<!-- 引入组件库 -->
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
<title>用户管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户中心 <span class="c-gray en">&gt;</span> 用户管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div id="app" class="page-container">
	<div class="text-c"> 日期范围：
		<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;">
		<input type="text" class="input-text" style="width:250px" placeholder="输入会员名称、电话、邮箱" v-model="content"  id="" name="">
		<button type="submit" class="btn btn-success radius" @click="selectIf()" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" @click="datadel(0)" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a href="javascript:;" @click="addgoodsfunction()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加商品</a></span> <span class="r">共有数据：<strong>1</strong> 条</span> </div>
	<div class="mt-20">
		<el-form :model="customer"   ref="customer"  label-width="100px"  class="demo-ruleForm">
		  <el-form-item label="名称" prop="cname">
		    <el-input type="text" style="width:700px;" v-model="customer.cname" autocomplete="off"></el-input>
		  </el-form-item>
		  <el-form-item label="银行账户" prop="caccount">
		    <el-input type="text" style="width:700px;" v-model="customer.caccount" autocomplete="off"></el-input>
		  </el-form-item>
		  <el-form-item label="电子邮件" prop="cemail">
		    <el-input type="email" v-model="customer.cemail" style="width:700px;"></el-input>
		  </el-form-item>
		  <el-form-item label="电话号码" prop="cphone">
		    <el-input type="text" style="width:700px;" v-model="customer.cphone" autocomplete="off"></el-input>
		  </el-form-item>
		  <el-form-item label="详细地址" prop="caddress">
		    <el-input type="textarea" style="width:700px;" v-model="customer.caddress" autocomplete="off"></el-input>
		  </el-form-item>
		  <el-form-item label="备注信息" prop="bremark">
		    <el-input type="textarea" style="width:700px;" v-model="customer.bremark" autocomplete="off"></el-input>
		  </el-form-item>
		  <el-form-item label="联系人编号" prop="smid">
		    <el-input readonly type="text" style="width:700px;" v-model="customer.smid " autocomplete="off"></el-input>
		  </el-form-item>
		  <el-form-item>
		    <el-button type="primary" @click="submitForm('customer')">修改</el-button>
		    <el-button @click="resetForm('customer')">重置</el-button>
		  </el-form-item>
		</el-form>
    </el-form>
	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->


<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
var v = new Vue({
	el:"#app",
	data:{
		tt:'111',
		content:'',
		customer:{
			cid:'',
			cname:'',
			cproterty:'',
			cstate:'',
			caccount:'',
			cemail:'',
			cphone:'',
			caddress:'',
			bremark:'',
			smid:''
		}
	},
	methods: {
	      submitForm:function(formName) {
	    	  let key;
	    	  var istrue = false;
              for(key in this.customer){
              	if(this.customer[key]  == ''){
              		istrue = true;
              	}
              }
              if(istrue)
           	  {
           	  	alert("请将信息填写完整！");
           	  }else{
           		var _this = this;
	        	$.ajax({
	        	      type: "POST",
	        	      url: "/customer/updatecustomer",
	        	      data: _this.customer,
	        	      dataType: "json",
	        	      success: function (response) {
	        	    	  _this.selectCustomerByNowId();
	        	      }
	        	  });
	        	alert("修改成功！");
           	  }
	    	  
	        
	      },
	      resetForm:function(formName) {
	        this.$refs[formName].resetFields();
	      },
	      selectCustomerByNowId:function(){
		    	var _this = this;
	        	$.ajax({
	        	      type: "GET",
	        	      traditional: true,
	        	      url: "/customer/customerMessage",
	        	      data: null,
	        	      dataType: "json",
	        	      success: function (response) {
	        	    	  _this.customer = response;
	        	      }
	        	  });
	      }
	    },
	//钩子函数，直接调用分页查询
	created:function(){
		this.selectCustomerByNowId();
	}
});
</script>
<script type="text/javascript">

$(function(){
	$('.table-sort').dataTable({
		"aaSorting": [[ 1, "desc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  {"orderable":false,"aTargets":[0,8,9]}// 制定列不参与排序
		]
	});
	
});
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*用户-查看*/
function member_show(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*用户-停用*/
function member_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_start(this,id)" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
				$(obj).remove();
				layer.msg('已停用!',{icon: 5,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}

/*用户-启用*/
function member_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_stop(this,id)" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
				$(obj).remove();
				layer.msg('已启用!',{icon: 6,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});
	});
}
/*用户-编辑*/
function member_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*密码-修改*/
function change_password(title,url,id,w,h){
	layer_show(title,url,w,h);	
}
/*用户-删除*/
function member_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}
</script> 
</body>
</html>
