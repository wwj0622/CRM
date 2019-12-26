<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<base href="<%=basepath %>" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Insert title here</title>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />

<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
<script src="./js/axios.min.js"></script>
<script src="./js/vue.js"></script>
</head>
<body>
<div class="page-container" id="app">
	<form  class="form form-horizontal" id="form-article-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>原材料ID：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" v-model="parts.pid" value="" placeholder="" id="" name="">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">原材料名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" v-model="parts.pname" value="" placeholder="" id="" name="">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>仓库：</label>
			<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select name="" class="select" v-model="parts.ptid">
					<option value="0">请选择仓库名字</option>
                    <option :value="s.stid" v-for="(s,i) in storage" >{{s.stname}}</option>
				</select>
                </span>
            </div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2" >价格：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"  v-model="parts.pprice" value="0" placeholder="" id="" name="">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">数量：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" name="" id="" v-model="parts.pcount" placeholder="" value="" class="input-text">
			</div>
        </div>
   
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2" >商品说明：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" name="" id="" placeholder=""  v-model="parts.premark"  value="" class="input-text">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">图片上传：</label>
			<div class="formControls col-xs-8 col-sm-9" style="position: relative;height: 150px;">
                 <img src="./temp/Thumb/chufang.jpg" id="img" alt="" style="position: absolute;width: 150px; height: 150px; " >
		         <input type="file" @change="genghuan" id="file" style="position: absolute;width: 150px;height: 150px;opacity: 0;" >
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button @click="inserParts" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 保存并提交审核</button>
				<button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
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
<script type="text/javascript" src="lib/webuploader/0.1.5/webuploader.min.js"></script> 
<script type="text/javascript" src="lib/ueditor/1.4.3/ueditor.config.js"></script>
<script type="text/javascript" src="lib/ueditor/1.4.3/ueditor.all.min.js"> </script>
<script type="text/javascript" src="lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>

<script>
  new Vue({
      el:'#app',
      data:{
           parts:{
             pid:'',
             pname:'',
             ptid:'0',
             pcount:'',
             pprice:'',
             premark:'',
             plogo:'',
             pstate:'0'
           },
           storage:[]
      },
      methods: {
        genghuan(){
           var file =event.target.files[0];
           var reader= new FileReader();
           reader.readAsDataURL(file);
           reader.onload=function()
           {
              var result = reader.result;
              event.target
              document.getElementById('img').src=result;
           }
        },
        storagename(){
            this_a=this;
            axios.get("/stock/selectStorage",null)
            .then(res => {
                console.log(res);
                this_a.storage=res.data;
            })
            .catch(err => {
                console.error(err); 
            })

         },
        inserParts(){    
         this_a=this;
         var a= document.getElementById('file');   
         var formData = new FormData;
         formData.append('file',a.files[0]);
         formData.append('pid',this.parts.pid);
         formData.append('pname',this.parts.pname);
         formData.append('pcount',this.parts.pcount);
         formData.append('pprice',this.parts.pprice);
         formData.append('premark',this.parts.premark);
         formData.append('ptid',this.parts.ptid);
         formData.append('pstate',this.parts.pstate);
         var url='/stock/addParts';
         var config='multipart/form-data;';
         axios.post(url,formData,config)
         .then(res => {
             console.log(res);
             console.log(res.data.state);
             if(res.data.state)
             {
                 alert('添加成功');
                 layer.close(layer.index);
              	 window.parent.location.reload();
             }
         })
         .catch(err => {
             console.error(err); 
             
         })
        }

      },
      created () {
          this.storagename();
        

      }
  })

</script>

</body>
</html>
