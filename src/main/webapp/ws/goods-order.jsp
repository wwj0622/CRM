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
	
	<input type="hidden" id="cids" value='${cid }'>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" @click="datadel(0)" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 添加订单并支付</a></span> <span class="r">共有数据：<strong>{{total}}</strong> 条</span> </div>
	<div class="mt-20">
	<el-row>
          <el-col :span="24">
              <el-table ref="multipleTable" @selection-change="selection" :data="order_goods" border>
                  <el-table-column type="selection" width="55" ></el-table-column>
                  <!-- <el-table-column type="index" ></el-table-column> -->
                  <el-table-column label="商品ID" prop="gid"></el-table-column>
                  <el-table-column label="产品名" prop="goods.gname"></el-table-column>
                  <el-table-column label="商品图像">
					　　<template slot-scope="scope">　
					　　　　<img :src="scope.row.goods.glogo" width="30" height="30" class="head_pic"/>
					　	</template> 
				  </el-table-column>
                  <el-table-column label="零售价" prop="goods.gprice"></el-table-column>
                  <el-table-column label="购买数量" prop="ogcount">
                  	
                  </el-table-column>
                  
                  <el-table-column label="数量操作">
                      <template slot-scope="scope">
                      <el-button size="mini" style="margin-right:0px" type="danger" @click="increase(1,scope)">+</el-button>
                          <el-button size="mini" type="danger" @click="increase(0,scope)">-</el-button>
                          
                      </template>
                  </el-table-column>
              </el-table>
          </el-col>
      </el-row>
      <el-row style="margin-top:10px">
      <el-col :span="24">
        <el-pagination :current-page.sync="currentPage" @current-change="selectByPn" background :page-size="pageSize" :total="total" layout="prev,pager,next" ></el-pagination>
      </el-col>
    </el-row> 
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
		cid:'',
		file:'',
		fileList:[],
		actionUrl:'https://jsonplaceholder.typicode.com/posts/',
		ordergoodslist:[],//存放多选时的所有数据
		glist:[],//存放多选的所有的goodsid
		goods:[],//存放分页查到的数据
		content:'',//条件查询的内容
		order_goods:[],
		orderGoods:{
            gid:'',
            oid:'',
            ogcount:'',
            ogremark:'',
            uid:'',
            goods:{
            	gid:'',
            	gname:'',
                glogo:'',
                gcount:'',
                goriginal:'',
                gprice:'',
                ptid:''
            }
        },
        order:{
        	oid:'',
        	cid:'',
        	oremark:'',
        	osum:'',
        	ostate:'1'
        },
        goodstid:[],
        pageSize:8,
        total:0,
        currentPage:1,
        updateDialogState:false,
        addDialogState:false,
        po:'left',
        wd:'80'
	},
	methods:{
		
		increase:function(i,scope){
			var istrue = true;
			if(i==1)
			{
				scope.row.ogcount = parseInt(scope.row.ogcount)+1;
				
			}
			else
			{
				scope.row.ogcount = parseInt(scope.row.ogcount)-1;
				if(scope.row.ogcount == 0)
				{
					istrue = false;
					var _this = this;
		        	$.ajax({
		        	      type: "POST",
		        	      traditional: true,
		        	      url: "/order/delordergoods",
		        	      data: {oid:scope.row.oid},
		        	      dataType: "json",
		        	      success: function (response) {
		        	    	  _this.selectAllGoodsOrderById();
		        	      }
		        	  });
				}
			}
			if(istrue)
			{
				var _this = this;
	        	$.ajax({
	        	      type: "POST",
	        	      traditional: true,
	        	      url: "/order/updateordergoodsbycount",
	        	      data: {
	        	    	  ogcount:scope.row.ogcount,
	        	    	  oid:scope.row.oid
	        	      },
	        	      dataType: "json",
	        	      success: function (response) {
	        	    	  _this.selectAllGoodsOrderById();
	        	      }
	        	  });
			}
			
		},
	    //模糊查询  搜索框触发的事件
		selectIf:function(){
			console.log(this.content);
			this.selectAllGoods();
		},
		
		//多选框 发生改变时触发的事件，将所选的goods信息传入gidgoodslist
        selection:function(val){
        	this.ordergoodslist = val;
        },
        
        //删除多条goods信息时的事件
        datadel:function(i){
        	
        	if(i==0){
        		if(this.ordergoodslist.length==0)
       			{
        			alert("请选择要购买的商品！");
       			}else{
       				this.glist.length = 0;
            		for(var j = 0;j<this.ordergoodslist.length;j++){
                		this.glist.push(this.ordergoodslist[j].oid);
                	}
            		console.log(this.glist);
            		//this.delgoodsByGid();
            		//先添加到ordergoods表
            		//再删除order表
            		_this = this;
            		$.ajax({
  	        	      type: "POST",
  	        	      traditional: true,
  	        	      url: "/order/buyordergoods",
  	        	      data: {list:_this.glist,
  	        	    	  cid:_this.cid},
  	        	      dataType: "json",
  	        	      success: function (response) {
  	        	    	  _this.selectAllGoodsOrderById();
  	        	      }
  	        	  });
       			}
        	}
        },
        
        
        //点击修改信息时触发的事件
        updategoods:function(scope)
        {
            this.updateDialogState = true;
            this.goodsinfo = scope.row;
            this.selectAlltid();
        },
        
        
        //dialog取消时的事件
        clos:function(){
            this.updateDialogState = false;
            this.addDialogState = false;
        },
        
        //点击页码发生的查询事件
        selectByPn:function()
        {
            console.log(this.currentPage);
            this.selectAllGoods();
        },
        
        //查询所有的goods信息，并分页
        selectAllGoodsOrderById:function(){
        	var _this = this;
    		$.ajax({
                type: "GET",
                url: "/order/allorderbyid",
                data: {
                	cid:_this.cid,
                	pn:_this.currentPage,
                	content:_this.content},
                dataType: "json",
                success: function (response) {
                    _this.total = response.total;
                    _this.pageSize = response.pageSize;
                    _this.order_goods = response.list;
                }
            });
        }

	},
	
	//钩子函数，直接调用分页查询
	created:function(){
		this.cid = $("#cids").val();
		this.selectAllGoodsOrderById();
		
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
