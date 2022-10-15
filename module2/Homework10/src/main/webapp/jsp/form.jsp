<%@ page language="java" contentType="text/html;charset=utf-8"%>

<html>
<body>
<h2>FIll the form</h2>
<form action="/homepage/information.do" method="GET">
  <label for="uname">Name:</label><br>
  <input type="text" id="uname" name="uname"><br>
  <label for="number">Telephone number:</label><br>
  <input type="text" id="number" name="number"><br>
  <label for="emale">Email:</label><br>
  <input type="text" id="mail" name="mail"><br><br>
  <input type="submit" value="Submit">
</form>
</body>
</html>