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
	</div><!-- href="JavaScript:void(0)" -->
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"> <a  @click="ahref()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加订单</a>
	    <el-select popper-class = "optionsContent" default-first-option filterable v-model="customer.cname" @change="chickvalue($event)" placeholder="请选择客户">
	    	
	      <el-option v-for="(g,i) in customer_cname" :key="i" :label="g.cname" :value="g.cname"></el-option>
	      
	    </el-select>
	</span> <!-- <span class="r">共有订单：<strong>{{ordernum}}</strong> 条</span> --> </div>
	<div class="mt-20">
	<el-row>
          <el-col :span="24">
              <el-table ref="multipleTable" @selection-change="selection" :data="goods" border>
                  <el-table-column type="selection" width="55" ></el-table-column>
                  <el-table-column type="index" ></el-table-column> 
                  <!-- <el-table-column label="ID" prop="gid"></el-table-column> -->
                  <el-table-column label="产品名" prop="gname" ></el-table-column>
                  <el-table-column label="头像">
					　　<template slot-scope="scope">
					　　　　<img :src="scope.row.glogo" width="30" height="30" class="head_pic"/>
					　　</template>
				  </el-table-column>
                  <el-table-column label="零售价" prop="gprice"></el-table-column>
                  <el-table-column label="仓库数量" prop="gcount"></el-table-column>
                  <el-table-column label="操作购买数量" >
                  	<template slot-scope="scope">
                  		<i @click="changenum(1,scope)" style="margin-left:30px; color:red; " class="el-icon-plus"></i>
		                 <span style="margin-left:30px;">{{inde[scope.$index]}}</span>
		                 <i @click="changenum(0,scope)" style="margin-left:30px; color:red; " class="el-icon-minus"></i>
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
		ogremark:'',
		order_goods:{
			uid:'',
			gid:'',
			ogcount:'1',
		},
		customer_cname:[],
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
		},
		ordergoodslist:[],
		inde:[],
		ordernum:'0',
		shopnum:'1',
		file:'',
		fileList:[],
		actionUrl:'https://jsonplaceholder.typicode.com/posts/',
		gidgoodslist:[],//存放多选时的所有数据
		glist:[],//存放多选的所有的goodsid
		goods:[],//存放分页查到的数据
		content:'',//条件查询的内容
		goodsinfo:{
            gid:'',
            gname:'',
            glogo:'',
            gcount:'',
            goriginal:'',
            gprice:'',
            ptid:''
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
		selection:function(val){
			
				this.ordergoodslist.length = 0;
	        	var i = 0,j=0;
	        	for(i;i<val.length;i++)
	        	{
	        		this.order_goods.uid = this.customer.cid;
	        		this.order_goods.gid = val[i].gid;
	        		
	        		for(j;j<this.goods.length;j++)
	        		{
	        			if(this.goods[j].gid==val[i].gid)
	        			{
	        				break;
	        			}
	        		}
	        		
	        		this.order_goods.ogcount = this.inde[j].toString();
	        		this.ordergoodslist[i]=JSON.parse(JSON.stringify(this.order_goods));
	        		j = 0;
	        	} 
			
			
        },
		changenum:function(i,scope)
		{
			if(i==1)
			{
				this.inde[scope.$index] = this.inde[scope.$index] + 1;
			}else
			{
				if(this.inde[scope.$index]>1)
				{
					this.inde[scope.$index] = this.inde[scope.$index] - 1;
				}
			}
			this.inde.splice(-1,0);
			
			if(this.ordergoodslist.length>0)
			{
				var i = 0,j=0;
	        	for(i;i<this.ordergoodslist.length;i++)
	        	{
	        		for(j;j<this.goods.length;j++)
	        		{
	        			
	        			if(this.goods[j].gid==this.ordergoodslist[i].gid)
	        			{
	        				this.ordergoodslist[i].ogcount = this.inde[j].toString();
	        				break;
	        			}
	        		}
	        		j = 0;
	        	}
			}
			
		},
		ahref:function(){
			if(this.ordergoodslist.length==0)
			{
				alert("请选择商品！");
			}else
			{
				if(this.ordergoodslist[0].uid=="")
				{
					alert("请选择客户！");
				}else
				{
					var _this = this;
					console.log(_this.ordergoodslist);
		        	$.ajax({
		        	      type: "POST",
		        	      url: "/order/addordergoods",
		        	      contentType:'application/json',
		        	      data:JSON.stringify(_this.ordergoodslist),
		        	      dataType: "json",
		        	      success: function (response) {
		        	      }
		        	  });
				}
				
			}
			
			
			/* if(this.customer.cid!="")
			{
				var _this = this;
				console.log(_this.customer.cid);
				window.location.href="order/allorder?cid="+_this.customer.cid;
			}else
			{
				alert("请选择客户！");
			} */
		},
		chickvalue:function(e){
			
			for(var i = 0;i < this.customer_cname.length;i++)
			{
				if(this.customer_cname[i].cname==this.customer.cname){
					this.customer.cid = this.customer_cname[i].cid;
					break;
				}
			}
			if(this.ordergoodslist.length>0)
			{
				var j = 0;
				for(j;j<this.ordergoodslist.length;j++)
        		{
        			this.ordergoodslist[j].uid = this.customer.cid;
        			
        		}
			}
		},
		//修改框添加图片触发事件
		changefile:function(file, fileList){
			this.file = file;
			this.goodsinfo.glogo = file.url;
		},
		
		
		
		//查询所有的仓库编号
		selectAlltid:function(){
			var _this = this;
        	$.ajax({
        	      type: "GET",
        	      traditional: true,
        	      url: "/goods/selecttid",
        	      data: null,
        	      dataType: "json",
        	      success: function (response) {
        	    	  _this.goodstid = response;
        	      }
        	  });
		},
		
		
		//添加goods后上传到后台
		addupdate:function(){
			
		},
	    //模糊查询  搜索框触发的事件
		selectIf:function(){
			console.log(this.content);
			this.selectAllGoods();
		},
		
		//多选框 发生改变时触发的事件，将所选的goods信息传入gidgoodslist
        
        
        //点击修改信息时触发的事件
        updategoods:function(scope)
        {
        	if(this.customer.cname!="")
       		{
        		this.updateDialogState = true;
                this.goodsinfo = scope.row;
                this.selectAlltid();
       		}else{
       			alert("请选择客户信息！");
       		}
            
        },
        
        //点击确认修改，触发的修改事件
        update:function(){
        	console.log(this.customer.cid);
        	
                this.order_goods.gid = this.goodsinfo.gid;
                this.order_goods.ogcount = this.shopnum;
                this.order_goods.ogremark = this.ogremark;
                this.order_goods.uid = this.customer.cid;
                this.updateDialogState = false;
                console.log(this.order_goods);
                if(this.order_goods.ogcount > this.goodsinfo.gcount)
               	{
               		alert("仓库数量不足！");
               	}else
           		{
               		var _this = this;
               		$.ajax({
              	      type: "POST",
              	      traditional: true,
              	      url: "/goods/addorder",
              	      data: _this.order_goods,
              	      dataType: "json",
              	      success: function (response) {
              	    	_this.selectAllOrderById();
              	    	_this.selectAllGoods();
              	    	let key;
                        for(key in _this.order_goods){
                        	_this.order_goods[key]  = ''
                        }
                        _this.shopnum="1";
                        _this.ogremark="";
              	      }
              	  });
           		}
       		
            
		    
		    
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
        selectAllGoods:function(){
        	var _this = this;
    		$.ajax({
                type: "GET",
                url: "/goods/goodslist1",
                data: {pn:_this.currentPage,
                	content:_this.content},
                dataType: "json",
                success: function (response) {
                    _this.goods = response.list;
                    _this.total = response.total;
                    _this.pageSize = response.pageSize;
                    
                    var i = 0;
                    for(i;i<response.list.length;i++){
                    	_this.inde.push(1);
                    }
                }
            });
        },
        selectAllOrderById:function(){
        	var _this = this;
    		$.ajax({
                type: "GET",
                url: "/goods/selectAllOrderById",
                data: null,
                dataType: "json",
                success: function (response) {
                    _this.ordernum = response;
                }
            });
        },
        selectAllKehuById:function(){
        	var _this = this;
    		$.ajax({
                type: "GET",
                url: "/goods/selectAllKehuById",
                data: null,
                dataType: "json",
                success: function (response) {
                    _this.customer_cname = response;
                }
            });
        }

	},
	
	//钩子函数，直接调用分页查询
	created:function(){
		this.selectAllGoods();
		this.selectAllOrderById();
		this.selectAllKehuById();
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
