<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<script src="./js/axios.min.js"></script>
<script src="./js/vue.js"></script>
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

<title>新建网站角色 - 管理员管理 - H-ui.admin v3.1</title>
<meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<article class="page-container" id="app">
	<form action="" method="post" class="form form-horizontal" id="form-admin-role-add">
		<input type="hidden" value="${String}"  id="id">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" disabled value="" v-model="p2.name" placeholder="" id="roleName" name="roleName">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">备注：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" disabled value="" v-model="p2.available" placeholder="" id="" name="">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">网站角色：</label>
			<div class="formControls col-xs-8 col-sm-9">

				
				<dl class="permission-list" v-for="(p,i) in p1" >
					<dt>
						<label>
							<input type="checkbox" disabled  @click="dj" :value="p.id"  name="user-Character-1-0-0" :id="'a'+p.id">
							{{p.name}}
						</label>
					</dt>
				  <dd>
						<dl class="cl permission-list2">
						<!-- 	<dt>
								<label class="">
									<input type="checkbox" :value="p.id" name="user-Character-1-0" id="user-Character-1-0">
									{{p.name}}</label>
							</dt> -->
							<dd>
								<label class="" v-for="(pm,i) in p.miss" >
									<input :class="'a'+p.id" type="checkbox"  disabled @click="ddj"  :value="pm.id" name="user-Character-1-0-0" v-model="permission" id="user-Character-1-0-0">
									{{pm.name}}
								</label>
							
							</dd>
						</dl>
					</dd>

				</dl>


			</div>
		</div>
	</form>
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script>
<!-- <script type="text/javascript">
$(function(){
	/* $(".permission-list dt input:checkbox").click(function(){
		$(this).closest("dl").find("dd input:checkbox").prop("checked",$(this).prop("checked"));
	});
	$(".permission-list2 dd input:checkbox").click(function(){
		var l =$(this).parent().parent().find("input:checked").length;
		var l2=$(this).parents(".permission-list").find(".permission-list2 dd").find("input:checked").length;
		if($(this).prop("checked")){
			$(this).closest("dl").find("dt input:checkbox").prop("checked",true);
			$(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked",true);
		}
		else{
			if(l==0){
				$(this).closest("dl").find("dt input:checkbox").prop("checked",false);
			}
			if(l2==0){
				$(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked",false);
			}
		}
	});
	 */
	/* $("#form-admin-role-add").validate({
		rules:{
			roleName:{
				required:true,
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$(form).ajaxSubmit();
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		}
	}); */
});
</script> -->
<script>
  new Vue({
	  el:'#app',
	  data () {
		  return {
			  p1:[],
			  id:'',
			  permission:[],
			  p2:{
				  id:'',
				  name:'',
				  available:''
			  }
			
		  }
	  },
	  methods: {
		  pemis(){
             this_a=this;
			 axios.post("/AllPermis",null)
			 .then(res => {
				 console.log(res);
                    this_a.p1 = res.data;
                    this_a.a();
			 })
			 .catch(err => {
				 console.error(err); 
			 })
		},
		a(){
			this.id=document.getElementById('id').value;
			this_a=this;
			axios.post("/selectUP",{id:this_a.id})
			.then(res => {
				console.log(res);
			    this_a.p2=res.data;
			    this_a.permission=res.data.permission; 
			    this_a.c();
			
			})
			.catch(err => {
				console.error(err); 
			})
		  },
		  c(){
			 var permission = this.permission;
			  for (let index = 0; index < permission.length; index++) {
				  var a=document.getElementsByName('user-Character-1-0-0');
	
                  for (let i = 0; i < a.length; i++){
					   if(a[i].value==permission[index].id)
					   {
						   a[i].checked = true;
					   }
					  
				  }
 			  }
		    },
  	    	dj(){
	    		$(".a"+$(event.target).val()).prop("checked",$(event.target).prop("checked"));
  			  },
  			  ddj(){
  				  
  				var id = $(event.target).attr("class");
 
  				var m=$("#"+id);

  				var k=$("."+id);
  				var state = false;

  			/* 	$.each(k, function (indexInArray, valueOfElement) { 
  					if($(this).prop("checked")){
  						state = true;
  					}
  				}); */
  				
  			
  				for (let i = 0; i < k.length; i++) {
  					if(k[i].checked){
  						state = true;
  						break;
  					}
  				}
  				console.log(state);
  				m.prop('checked',state);
  			  }
		
	  },
	  created () {
		  this.pemis();
		  
		  
	  }


  })

</script>

</body>
</html>
