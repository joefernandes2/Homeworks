<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<style>
table, th, td {
  border:1px solid black;
}
</style>
<h1>Info user</h1>
<table style="width:100%">
  <tr>
    <th>Name</th>
    <th>Telephone number</th>
    <th>Email</th>
  </tr>
  <tr>
    <td><c:out value="${user.name}"/></td>
    <td><c:out value="${user.number}"/></td>
    <td><c:out value="${user.email}"/></td>
  </tr>
</table>
</html>