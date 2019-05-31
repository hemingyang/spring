<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${list}
	<script type="text/javascript">
		var xmlHttpReq;

		function createXmlHttpRequest() {

			if (window.XMLHttpRequest) {
				xmlHttpReq = new XMLHttpRequest();//非IE浏览器

			} else {
				xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");//IE浏览器
			}
		}

		function checkUser() {

			var username = document.getElementById("user").value;

			if (username == "")

			{
				alert("用户名必须填写！");

				return false;
			}
			createXmlHttpRequest();

			xmlHttpReq.onreadystatechange = handle;
			var url = "check?username=" + username;

			xmlHttpReq.open("get", url, true);

			xmlHttpReq.send(null);

		}

		function handle()

		{

			//准备状态为4

			if (xmlHttpReq.readyState == 4) {
				if(xmlHttpReq.staus=200){
					var res =xmlHttpReq.responseText;
					var result=document.getElemenById("result");
					result.innerHTML="SSS";
				}

			}
		}
		
	
	</script>
</body>
</html>