<%@page import="br.edu.ifnmg.taskmanager.model.Tarefa"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="br.edu.ifnmg.taskmanager.model.Dao"%>
<%@page import="br.edu.ifnmg.taskmanager.model.TarefaDao"%>




<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
	
	<%
		TarefaDao tarDao = new TarefaDao();
		Tarefa taref =  new Tarefa();
		
		String codigo = (String)request.getAttribute("id");
		
		taref.setId(Long.parseLong(codigo));
		out.println("codigo: " + codigo);
		
		//tarDao.excluir(taref);
		
		tarDao.excluirId(Long.parseLong(codigo));
	%>
	<h1>Excluido com sucesso!</h1>
	<!-- <a href="Controller?acao=excluir&id=${tarefa.id}" class="btn btn-danger btn-sm"></a> -->
	
	
		<!-- TarefaDao tDao = new TarefaDao();
		 tDao.excluir(${tarefa});-->
		
	
</body>
</html>