<#assign base=springMacroRequestContext.getContextUrl("")>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<title>PDF预览</title>
</head>
<body>
<script type="text/javascript">

function openScanFile(title,token,bizNo,archivesNo,url){
 	 //var pdfUrl ="http://127.0.0.1:8080/jeecg-boot/generic/web/viewer.html?file="+encodeURIComponent("http://127.0.0.1:8080/jeecg-boot/test/jeecgDemo/getPdfUrl?title="+encodeURI(title));
 	 //var pdfUrl ="${base}/generic/web/viewer.html?file="+encodeURIComponent("http://localhost:8181/jeecg-boot/sys/common/static/[合同-扫描]天津中环领先小尺寸抛光片扩产厂房改造及配套项目EPC总承包合同-28158772元-霍铧德.pdf");
    var pdfUrl ="${base}/generic/web/viewer.html?file="+encodeURIComponent(url);
	 var vm=window.open(pdfUrl);
}

 window.addEventListener('message', function(event) {
     var data = event.data;
     var title = data.title;
	 var token = data.token;
	 var url = data.url;
     openScanFile(title,token,'','',url);
 }, false);
 
</script>
</body>
</html>
