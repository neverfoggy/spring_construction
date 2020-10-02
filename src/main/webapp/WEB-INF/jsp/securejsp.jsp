<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
</head>
<body>
<h3>Welcome, Enter The Employee Details</h3>
<%--@elvariable id="command" type="com.kursach.model.MasterDTO"--%>
<form:form method="POST"
           action="/add" modelAttribute="command">
    <form:select path="id">
        <form:option value="NONE" label="-----Select-----"/>
        <jsp:useBean id="depts" scope="request" type="com.kursach.model.DAODept"/>
        <form:options items="${depts}" itemValue="${depts.getId()}" itemLabel="${depts.getName()}"/>
    </form:select>
    <input type="submit" />
</form:form>