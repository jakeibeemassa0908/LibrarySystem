<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<c:if test="${sessionScope.user==null && sessionScope.admin==null}">
	<c:redirect url=""></c:redirect>
</c:if>