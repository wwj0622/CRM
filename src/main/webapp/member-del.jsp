<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<base href="<%=basepath %>" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
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
<script src="./js/axios.min.js"></script>
<script src="./js/vue.js"></script>
<title>删除的用户</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户中心 <span class="c-gray en">&gt;</span> 删除的用户<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container" id="app">
	<div class="text-c"> 日期范围：
		<input type="text"  autocomplete="off" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;">
		-
		<input type="text"  autocomplete="off" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;">
		<input type="text"  autocomplete="off" v-model="udate.name"  class="input-text" style="width:250px" placeholder="输入会员名称、电话、邮箱" id="" name="">
		<button type="button" @click="allUser" class="btn btn-success radius" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> </span> <span class="r">共有数据：<strong>88</strong> 条</span> </div>
	<div class="mt-20"  >
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				<th width="80">ID</th>
				<th width="100">用户名</th>
				<th width="40">性别</th>
				<th width="90">手机</th>
				<th width="80">邮箱</th>
				<th width="80">地址</th>
				<th width="130">加入时间</th>
				<th width="">操作时间</th>
				<th width="70">状态</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<tr class="text-c"  v-for="(u,i) in user">
				<td><input type="checkbox" value="" name=""></td>
				<td>{{u.id}}</td>
				<td><u style="cursor:pointer" class="text-primary" onclick="member_show('张山','member-show.html','10001','360','400')">{{u.usercode}}</u></td>
				<td>男</td>
				<td>{{u.phone}}</td>
				<td>admin@mail.com</td>
				<td class="text-l">{{u.idcard}}</td>
				<td>{{u.jointime}}</td>
				<td>{{u.operationtime}}</td>
				<td class="td-status"><span class="label label-danger radius">{{u.locked == 3?'已删除': '未删除' }}</span></td>
				<td class="td-manage"><a style="text-decoration:none" href="javascript:;" @click="member_huanyuan(this,u.id)" title="还原"><i class="Hui-iconfont">&#xe66b;</i></a> <a title="删除" href="javascript:;" @click="member_del(this,u.id)" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
			</tr>
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
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript">


/*用户-还原*/
function member_huanyuan(obj,id){
	layer.confirm('确认要还原吗？',function(index){
		
		$(obj).remove();
		layer.msg('已还原!',{icon: 6,time:1000});
	});
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

<script>
new Vue({
	  el:'#app',
	  data () {
		  return {
			  user:[],
			  udate:{
				  start:'',
				  end:'',
				  name:''
			     }
		  }
	  },
	  methods: {
   	selectUser(){
		  this_a=this;
			axios.get("/sduser",null)
			   .then(res => {
				   console.log(res);
				 this_a.user = res.data;
			   })
			   .catch(err => {
				   console.error(err); 
			   })
		},
		member_huanyuan(obj,id){
			 this_a=this;
		    layer.confirm('确认要还原吗？',function(index){
			axios.post("/cancel",{id:id})
			.then(res => {
				console.log(res);
				layer.msg('已还原!',{icon: 6,time:1000});
				 this_a.selectUser();
			})
			.catch(err => {
				console.error(err); 
			})
		      });
	        },
	    	allUser(){
				this_a=this;
				 this_a.udate.start=document.getElementById('datemin').value;
			     this_a.udate.end=document.getElementById('datemax').value;
		          axios.post("/selectDeleteAllUser",this_a.udate)
		          .then(res => {
		            console.log(res);
		             this_a.user = res.data;
		          })
		          .catch(err => {
		            console.error(err); 
		          })
				
			},
			member_del(obj,id){
				this_a=this;
				layer.confirm('确认要删除吗？',function(index){
					$.ajax({
						type: 'POST',
						url: '/deleteUser',
						data: {id:id},
						dataType: 'json',
						success: function(data){
							$(obj).parents("tr").remove();
							layer.msg('已删除!',{icon:1,time:1000});
							this_a.selectUser();
						},
						error:function(data) {
							console.log(data.msg);
						},
					});		
				});
			}
			
		  
	  },
	  created(){
		this.selectUser();
	  }

 })

</script>

</body>
</html>
