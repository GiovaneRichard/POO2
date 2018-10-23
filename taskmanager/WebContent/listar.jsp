<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" type="text/css" href="estilo.css"/>
<title>Insert title here</title>

</head>
<body>
	<h1>Tarefas</h1>
	<!-- Comentário de multiplas linhas
	<ol>
		<c:forEach var="tarefa" items="${requestScope.tarefas}">
			<li style="border-top: solid thin black;">
				<ul class="${tarefa.concluida ? 'done' : ''}">
					<li>${tarefa.id}</li>
					<li>${tarefa.descricao}</li>
					<li><fmt:formatDate type="both" value="${tarefa.criacao}"
							pattern="dd/MM/yyyy HH:mm:ss" /></li>
					<li>${tarefa.prioridade}</li>
					<li>${tarefa.progresso}</li>
					<li>${tarefa.concluida}</li>
					<li>${tarefa.conclusao}</li>
				</ul>
			</li>
		</c:forEach>
	</ol>
	-->
	
	<fieldset>
		<legend><strong>Listando os elementos do Banco</strong></legend><br/>
		<table id="tabela">
			<tr class="${tarefa.concluida ? 'done' : '' }">
				<th>id</th>
				<th>Descricao</th>
				<th>Prioridade</th>
				<th>Progresso</th>
				<th>Concluida</th>
				<th>Conclusao</th>
			</tr>
			
			<c:forEach var="tarefa" items="${requestScope.tarefas}">
			<tr>
				<td>${tarefa.id}</td>
				<td>${tarefa.descricao}</td>
				<td><fmt:formatDate type="both" value="${tarefa.criacao}"
							pattern="dd/MM/yyyy HH:mm:ss" /></th>
				<td>${tarefa.prioridade}</td>
				<td>${tarefa.concluida}</td>
				<td>${tarefa.conclusao}</td>
				<td><a href="Controller?action=alterar&id=${tarefa.id}" class="btn btn-primary btn-sm">Alterar</a>
				<td><a href="Controller?action=excluir&id=${tarefa.id}" class="btn btn-danger btn-sm">Excluir</a>
					<!-- <button class="btn btn-danger btn-sm" data-toggle="modal" data-toggle="modal"></button> -->
				</td>
			</tr>
			</c:forEach>
		</table><br/>
		<a href="salvar.jsp" target="_black">Novo</a>
	</fieldset>
	
	
</body>
</html>