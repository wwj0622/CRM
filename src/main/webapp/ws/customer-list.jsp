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
<script src="/element-ui/lib/index.js"></script>
<link type="text/css" rel="styleSheet"  href="/element-ui/lib/theme-chalk/index.css" />
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
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"> <a href="javascript:;" @click="addgoodsfunction()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加客户</a></span> <span class="r">共有数据：<strong>{{total}}</strong> 条</span> </div>
	<div class="mt-20">
	<el-row>
          <el-col :span="24">
              <el-table ref="multipleTable" @selection-change="selection" :data="customerlist" border>
                  <!-- <el-table-column type="selection" width="55" ></el-table-column> -->
                  <el-table-column type="index" label="序号"></el-table-column>
                  <el-table-column label="ID" prop="cid"></el-table-column>
                  <el-table-column label="客户名称" prop="cname"></el-table-column>
                  <el-table-column label="银行账号" prop="caccount"></el-table-column>
                  <el-table-column label="电子邮件" prop="cemail"></el-table-column>
                  <el-table-column label="电话号码" prop="cphone"></el-table-column>
                  <el-table-column label="地址" prop="caddress"></el-table-column>
                  <el-table-column label="备注信息" prop="bremark"></el-table-column>
                  <el-table-column label="操作">
                      <template slot-scope="scope">
                          <el-button size="mini" @click="updategoods(scope)">修改</el-button>
                          <el-dialog title="修改客户信息" :visible.sync="updateDialogState">
                              <el-form :model="customer" :label-position="po" :label-width="wd">
                                  <el-form-item label="客户名称">
                                    <el-input v-model="customer.cname" ></el-input>
                                  </el-form-item>
                                  <el-form-item label="银行账号" >
                                    <el-input v-model="customer.caccount" ></el-input>
                                  </el-form-item>
                                  <el-form-item label="电子邮件" >
                                    <el-input v-model="customer.cemail" ></el-input>
                                  </el-form-item>
                                  <el-form-item label="电话号码" >
                                    <el-input v-model="customer.cphone" ></el-input>
                                  </el-form-item>
                                  <el-form-item label="地址" >
                                    <el-input type="textarea" v-model="customer.caddress" ></el-input>
                                  </el-form-item>
                                  <el-form-item label="备注信息" >
                                    <el-input type="textarea" v-model="customer.bremark" ></el-input>
                                  </el-form-item>
                              </el-form>
                              <div slot="footer" class="dialog-footer">
                                  <el-button type="primary" @click="clos()">取消</el-button>
                                  <el-button type="success" @click="update()">确认</el-button>
                              </div>
                          </el-dialog>
                          <el-button size="mini" type="danger" @click="datadel1(1,scope)">删除</el-button>
                      </template>
                  </el-table-column>
              </el-table>
          </el-col>
          <el-dialog title="添加客户信息" :visible.sync="addDialogState">
             <el-form :model="addcustomer" :label-position="po" :label-width="wd">
                 <el-form-item label="客户名称">
                   <el-input v-model="addcustomer.cname" ></el-input>
                 </el-form-item>
                 <el-form-item label="银行账号" >
                   <el-input v-model="addcustomer.caccount" ></el-input>
                 </el-form-item>
                 <el-form-item label="电子邮件" >
                   <el-input v-model="addcustomer.cemail" ></el-input>
                 </el-form-item>
                 <el-form-item label="电话号码" >
                   <el-input v-model="addcustomer.cphone" ></el-input>
                 </el-form-item>
                 <el-form-item label="地址" >
                   <el-input type="textarea" v-model="addcustomer.caddress" ></el-input>
                 </el-form-item>
                 <el-form-item label="备注信息" >
                   <el-input type="textarea" v-model="addcustomer.bremark" ></el-input>
                 </el-form-item>
             </el-form>
             <div slot="footer" class="dialog-footer">
                 <el-button type="primary" @click="clos()">取消</el-button>
                 <el-button type="success" @click="addupdate()">确认</el-button>
             </div>
         </el-dialog>
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
		addcustomer:{
			cname:'',
			caccount:'',
			cemail:'',
			cphone:'',
			caddress:'',
			bremark:'',
		},
		customerlist:[],
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
        addgoods:{
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
		//修改框添加图片触发事件
		changefile:function(file, fileList){
			this.file = file;
			this.goodsinfo.glogo = file.url;
		},
		
		//点击添加后触发的事件
		addgoodsfunction:function(){
			this.addDialogState = true;
			this.selectAlltid();
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
		
		//添加goods框，改变图片触发事件
		addgoodsfile:function(file){
			this.file = file;
			this.addgoods.glogo = file.url;
		},
		
		//添加goods后上传到后台
		addupdate:function(){
			
			var istrue = false;
			for(var key in this.addcustomer){
              	if(this.addcustomer[key]  == ''){
              		istrue = true;
              	}
              }
			if(istrue){
				alert("请将信息补充完整！");
			}else{
				this.addDialogState = false;
				var _this = this;
	        	$.ajax({
	        	      type: "GET",
	        	      traditional: true,
	        	      url: "/customer/addCustomer",
	        	      data: _this.addcustomer,
	        	      dataType: "json",
	        	      success: function (response) {
	        	    	  for(var key in _this.addcustomer){
	        	              	_this.addcustomer[key]  = '';
	        	              }
	        	    	  this.allCustomerBySmid();
	        	      }
	        	  });
			}
			
		},
		
		//上传文件之前的钩子,设置文件上传的大小为10M
		beforeAvatarUpload:function(file){
			const isLt10M = file.size / 1024 / 1024 < 10; // 限制小于10M
	        return isLt10M;
		},
	    //模糊查询  搜索框触发的事件
		selectIf:function(){
			console.log(this.content);
			this.selectAllGoods();
		},
		
		//多选框 发生改变时触发的事件，将所选的goods信息传入gidgoodslist
        selection:function(val){
        	this.gidgoodslist = val;
        },
        
        //删除多条goods信息时的事件
        datadel:function(i){
        	if(i==0){
        		if(this.gidgoodslist.length==0)
       			{
        			alert("请选择要删除的信息！");
       			}else{
       				this.glist.length = 0;
            		for(var j = 0;j<this.gidgoodslist.length;j++){
                		this.glist.push(this.gidgoodslist[j].gid);
                	}
            		this.delgoodsByGid();
       			}
        	}
        },
        
        //删除单条goods信息触发的事件，并进行一系列的逻辑判断
        datadel1:function(i,scope){
        	if(i==1){
        		var _this = this;
	        	$.ajax({
	        	      type: "GET",
	        	      traditional: true,
	        	      url: "/customer/delCustomer",
	        	      data: {cid:scope.row.cid},
	        	      dataType: "json",
	        	      success: function (response) {
	        	    	  
	        	    	  _this.allCustomerBySmid();
	        	      }
	        	  });
        	}
        },
        
        //删除goods信息的方法
        delgoodsByGid:function(){
        	var _this = this;
        	$.ajax({
        	      type: "POST",
        	      traditional: true,
        	      url: "/goods/del",
        	      data: {
        	    	  
        	    	  gidlist:_this.glist,
        	    	  pn:_this.currentPage
        	    	  },
        	      dataType: "json",
        	      success: function (response) {
        	    	  _this.selectAllGoods();
        	      }
        	  });
        },
        
        //点击修改信息时触发的事件
        updategoods:function(scope)
        {
        	console.log(scope.row);
            this.updateDialogState = true;
            this.customer = scope.row;
        },
        
        //点击确认修改，触发的修改事件
        update:function(){
            this.updateDialogState = false;
            var _this = this;
            $.ajax({
                type: "POST",
                url: "/customer/updateCustomer",
                data: _this.customer,
                dataType: "json",
                success: function (response) {
                	_this.allCustomerBySmid();   
                }
            });
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
                }
            });
        },
        allCustomerBySmid:function(){
        	var _this = this;
    		$.ajax({
                type: "GET",
                url: "/customer/allCustomerBySmid",
                data: null,
                dataType: "json",
                success: function (response) {
                	_this.customerlist = response;
                }
            });
        }

	},
	
	//钩子函数，直接调用分页查询
	created:function(){
		this.allCustomerBySmid();
		
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
