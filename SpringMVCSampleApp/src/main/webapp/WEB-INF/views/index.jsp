<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Anmeldeformular</title>
<style>
.error {
	color: #ff0000;
	font-style: italic; 
	font-weight: bold;
}
</style> 

 <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        $(document).ready(function() {
            $("#age").focusout(function() {
            	$('label[id*="ageMessage"]').text('');
            	var No1 = document.getElementById('age').value;
            	
            	  $.ajax({
            	        url: "http://localhost:8080/SpringMVCSampleApp/rest/age/" +  No1
            	    }).then(function(data) {
            	       $("#ageMessage").text(data);
            	    });
            	
            	
            });
        });
    </script>
</head>
<body>

  <c:choose>
    <c:when test="${login}">
	<springForm:form method="POST" commandName="loginUser"
		action="/SpringMVCSampleApp/new">
		${ageText}
		<table>
			<tr>
				<td>Name:</td>
				<td><springForm:input path="name" /></td>
				<td><springForm:errors path="name" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Surname</td>
				<td><springForm:input path="surname" /></td>
				<td><springForm:errors path="surname" cssClass="error" /></td>
			</tr>
					<tr>
			<td>Age</td>
				<td><springForm:input path="age" id="age" /></td>
				<td><label id="ageMessage"></label></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Anmelden!"></td>
			</tr>
		</table>

	</springForm:form>
	  </c:when>
        <c:otherwise>
              message : ${success}
    <br/>
    <br/>
           
         </c:otherwise>
     </c:choose>
  Go back to <a href="<c:url value='/show/users' />">List of All Users</a>
</body>
</html>
