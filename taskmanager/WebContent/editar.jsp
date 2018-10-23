<%@page import="javax.enterprise.context.RequestScoped"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="br.edu.ifnmg.taskmanager.model.Dao"%>
<%@page import="br.edu.ifnmg.taskmanager.model.TarefaDao"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="estilo.css"/>

<title>Insert title here</title>
</head>
<body>

	<fieldset>
		<legend><strong>Nova Tarefa</strong></legend><br/>	
	<form action="Controller" method="get">
	
		
		
		<c:forEach var="tarefa" items="${requestScope.tarefas}">
		
		
		
		<div class="novaTarefa">
			<div class="row">
				<label>Descricao</label><br/>
				<textarea class="form-control" type="text" name="txtDescricao" ></textarea>
				
				
				<!-- <input class="form-control" type="text" name="txtDescricao"/><br/> -->
			</div>
			<div class="form-group">
				<label>Prioridade</label><br/>
				<input class="form-control" type="text" name="txtPrioridade" value="${tarefa.prioridade}"/><br/>
			</div>
			<div class="form-group">
				<label>Progresso</label><br/>
				<input class="form-control" type="text" name="txtProgresso"/><br/>
			</div>
			<div class="form-group">
				<label>Concluida</label><br/>
				<input class="form-control" type="text" name="txtConcluida"/><br/>
			</div>
			<div class="form-group">
				<label>Conclusão</label><br/>
				<input class="form-control" type="text" name="txtConclusao"/><br/>
			</div>
		</div>
		<!--  <a href="Controller?acao=editar" class="btnSalvar">Salvar</a> -->
		
		</c:forEach>
	</form>
	</fieldset>
</body>
</html>