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
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" @click="datadel(0)" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a href="javascript:;" onclick="member_add('添加用户','member-add.html','','510')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加用户</a></span> <span class="r">共有数据：<strong>88</strong> 条</span> </div>
	<div class="mt-20">
	<el-row>
          <el-col :span="24">
              <el-table ref="multipleTable" @selection-change="selection" :data="goods" border>
                  <el-table-column type="selection" width="55" ></el-table-column>
                  <!-- <el-table-column type="index" ></el-table-column> -->
                  <el-table-column label="ID" prop="gid"></el-table-column>
                  <el-table-column label="产品名" prop="gname"></el-table-column>
                  <el-table-column label="头像">
					　<img :src="glogo" width="30" height="30"  />
				  </el-table-column>
                  <el-table-column label="成本价" prop="goriginal"></el-table-column>
                  <el-table-column label="零售价" prop="gprice"></el-table-column>
                  <el-table-column label="仓库数量" prop="gcount"></el-table-column>
                  <el-table-column label="仓库编号" prop="ptid"></el-table-column>
                  
                  <el-table-column label="操作">
                      <template slot-scope="scope">
                          <el-button size="mini" @click="updategoods(scope)">修改</el-button>
                          <el-dialog title="修改商品信息" :visible.sync="updateDialogState">
                              <el-form :model="goodsinfo" :label-position="po" :label-width="wd">
                                  <el-form-item label="ID">
                                      <el-input readonly v-model="goodsinfo.gid"></el-input>
                                  </el-form-item>
                                  <el-form-item label="产品名">
                                    <el-input v-model="goodsinfo.gname" ></el-input>
                                  </el-form-item>
                                  <el-form-item label="图片" >
                                    <img :src="goodsinfo.glogo" width="30" height="30"  />
                                    
                                    <el-upload
									  class="upload-demo"
									  :action="actionUrl"
									  :before-upload="beforeAvatarUpload"
									  :on-change="changefile"
									  :file-list="fileList"
									  :auto-upload="false"
  									  :show-file-list="false"
									  list-type="picture">
									  <el-button size="small" type="primary">点击上传</el-button>
									  <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过3M</div>
									</el-upload>
                                  </el-form-item>
                                  <el-form-item label="成本价" >
                                    <el-input v-model="goodsinfo.goriginal" ></el-input>
                                  </el-form-item>
                                  <el-form-item label="零售价" >
                                    <el-input v-model="goodsinfo.gprice" ></el-input>
                                  </el-form-item>
                                  <el-form-item label="仓库数量" >
                                    <el-input v-model="goodsinfo.gcount" ></el-input>
                                  </el-form-item>
                                  <el-form-item label="仓库编号" >
                                    <el-input v-model="goodsinfo.ptid" ></el-input>
                                  </el-form-item>
                                  <!-- <el-form-item label="生日">
                                    <el-date-picker v-model="stuinfo.birthday" ></el-date-picker>
                                  </el-form-item> -->
                              </el-form>
                              <div slot="footer" class="dialog-footer">
                                  <el-button type="primary" @click="clos()">取消</el-button>
                                  <el-button type="success" @click="update()">确认</el-button>
                              </div>
                          </el-dialog>
                          <el-button size="mini" type="danger" @click="datadel(1)">删除</el-button>
                      </template>
                  </el-table-column>
              </el-table>
          </el-col>
      </el-row>
      <el-row style="margin-top:10px">
      <el-col :span="24">
        <el-pagination :current-page.sync="currentPage" @current-change="selectByPn" background page-size="1" :total="total" layout="prev,pager,next" ></el-pagination>
      </el-col>
    </el-row> 
	 <!-- <table  class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				<th width="80">ID</th>
				<th width="100">产品名</th>
				<th width="120">图片</th>
				<th width="90">成本价</th>
				<th width="150">零售价</th>
				<th width="">仓库数量</th>
				<th width="130">仓库编号</th>
				<th width="100">操作</th>
			</tr>
		</thead >
			<tbody v-for="(g,i) in goods">
				<tr class="text-c">
					<td><input type="checkbox" :value="g.gid" name=""></td>
					<td>{{g.gid}}</td>
					<td><u style="cursor:pointer" class="text-primary" >{{g.gname}}</u></td>
					<td><img width="30px" height="30px" :src="g.glogo"/></td>
					<td>{{g.goriginal}}</td>
					<td>{{g.gprice}}</td>
					<td >{{g.gcount}}</td>
					<td>{{g.ptid}}</td>
					<td class="td-manage"><a style="text-decoration:none" onClick="member_stop(this,'10001')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a> <a title="编辑" href="javascript:;" onclick="member_edit('编辑','member-add.html','4','','510')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="change_password('修改密码','change-password.html','10001','600','270')" href="javascript:;" title="修改密码"><i class="Hui-iconfont">&#xe63f;</i></a> <a title="删除" href="javascript:;" onclick="member_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
				</tr>
			</tbody>
	</table>  -->
	<%-- <table>
		<tr>
    	 <td colspan="4">
    	 	<a href="goods/goodslist" >首页</a>
    	 	
    	 	<c:if test="${pageinfo.hasPreviousPage }">
    	 		<a href="goods/goodslist?pn=${pageinfo.prePage }" >上一页</a>
    	 	</c:if>
    	 	<c:if test="${pageinfo.hasNextPage }">
    	 		<a href="goods/goodslist?pn=${pageinfo.nextPage }" >下一页</a>
    	 	</c:if>
    	 	
    	 	<a href="goods/goodslist?pn=${pageinfo.pages }" >尾页</a>
    	 </td>
    </tr>
	</table> --%>
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
		file:'',
		fileList:[],
		uploadData:{
			dataType:this.goodsinfo,
			oldFilePath:""
		},
		actionUrl:'https://jsonplaceholder.typicode.com/posts/',
		gidgoodslist:[],//存放多选时的所有数据
		glist:[],//存放多选的所有的goodsid
		goods:[],
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
        total:1000,
        currentPage:1,
        updateDialogState:false,
        po:'left',
        wd:'80'
	},
	methods:{
		changefile:function(file, fileList){
			console.log("触发事件");
			this.file = file;
			this.goodsinfo.glogo = file.url;
		   /*  var reader = new FileReader();
		    reader.readAsDataURL(file.raw);
		    reader.onload = function(e){
		        console.log(this.result);//图片的base64数据
		    } */ 
		},
		beforeAvatarUpload:function(file){
			const isLt10M = file.size / 1024 / 1024 < 10; // 限制小于10M
	        return isLt10M;
		},
		/*  handleRemove:function(file, fileList) {
	        console.log(file, fileList);
	      },
	      handlePreview:function(file) {
	        console.log(file);
	      },  */
		selectIf:function(){
			console.log(this.content);
			this.selectAllGoods();
		},
		updatestu:function(scope)
        {
			this.file = null;
            this.updateDialogState = true;
            console.log("scope.row:"+scope.row);
            this.stuinfo = scope.row;
        },
        selection:function(val){
        	
        	this.gidgoodslist = val;
        	
        },
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
        		
        	}else if(i==1){
        		if(this.gidgoodslist.length>1)
        		{
        			alert("不能同时删除多条！");
        		}else if(this.gidgoodslist.length==1){
        			this.glist.length = 0;
        			this.glist.push(this.gidgoodslist[0].gid);
        			this.delgoodsByGid();
        		}else{
        			alert("请选择要删除的信息！");
        		}
        	}
        	
        	
        },
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
        	    	  /* location.href='/goods/goodslist1'; 
        	    	  window.parent.location.reload();*/
        	    	  _this.selectAllGoods();
        	      }
        	  });
        },
        delstu:function(scope){
            console.log(scope);
        },
        updategoods:function(scope)
        {
            this.updateDialogState = true;
            console.log("scope.row:");
            this.goodsinfo = scope.row;
            console.log(this.goodsinfo);
        },
        update:function(){
        	
            this.updateDialogState = false;
            
            //this.goodsinfo.glogo = this.file;
            //console.log(this.goodsinfo);
            var formData = new FormData();
		    //updategoods.append("goodsinfo",this.goodsinfo);
		    console.log("goodsinfo");
		    console.log(this.goodsinfo);
		    formData.append("file",this.file.raw);
		    formData.append("gid",this.goodsinfo.gid);
		    formData.append("gname",this.goodsinfo.gname);
		    formData.append("glogo",this.goodsinfo.glogo);
		    formData.append("gcount",this.goodsinfo.gcount);
		    formData.append("goriginal",this.goodsinfo.goriginal);
		    formData.append("gprice",this.goodsinfo.gprice);
		    formData.append("ptid",this.goodsinfo.ptid);
		    /* let config = {
		    		'ContentType':'application/x-www-form-urlencoded'
		    } ; */
		    var _this = this;
		    var configs = 'multipart/form-data;';
		    const instance=axios.create({
		          withCredentials: true
		         }) 
		    
		    instance.post("/goods/update", formData, configs).then(function(result) {
                            console.log(result);
                            _this.file = '';
                            _this.selectAllGoods();
            })
		    
        },
        clos:function(){
            this.updateDialogState = false;
        },
        selectByPn:function()
        {
            console.log(this.currentPage);
            this.selectAllGoods();
        },
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
                    _this.total = response.pages;
                }
            });
        }

	},
	created:function(){
		this.selectAllGoods();
		
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
