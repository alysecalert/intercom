<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script src="static/js/jquery-3.1.1.min.js" type="text/javascript"></script>
<script src="static/js/loading.js" type="text/javascript"></script>
<title>Login</title>
</head>
<script type="text/javascript">
function getUpdatePage()
{	
	ajaxindicatorstart("loading");
	jQuery
		.ajax({
			type : "GET",
			url : "getUpdatePage",
			success : function(data) {
			},
			failure : function(data) {
				ajaxindicatorstop();
				alert("Failed to load data");
			}
		});
}
</script>
<body>
<label for="email">Enter Email:</label>
<input type="text" id="email"/>
<br/>
<button onclick="getUpdatePage()" id="signIn" >Sign-in</button>
</body>
</html>