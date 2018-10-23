package br.edu.ifnmg.taskmanager.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import javax.xml.bind.ParseConversionEvent;

import br.edu.ifnmg.taskmanager.model.Tarefa;
import br.edu.ifnmg.taskmanager.model.TarefaDao;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TarefaDao tarefadao = new TarefaDao();
		Tarefa tarefa = new Tarefa();
		
		String acao = request.getParameter("action");
		/*
		switch(acao)
		{
			case "listar":
				List<Tarefa> tarefas = new TarefaDao().buscarTodos();
				request.setAttribute("tarefas", tarefas);
				RequestDispatcher destino = request.getRequestDispatcher("listar.jsp");
				destino.forward(request, response);
				break;
		}*/
		
		
		if(acao==null) {
			List<Tarefa> tarefas = new TarefaDao().buscarTodos();
			
			request.setAttribute("tarefas", tarefas);
			request.getRequestDispatcher("listar.jsp").forward(request, response);
			
		}else if(acao.equals("nova") || acao.equals("editar")){
			
			
			if(acao.equals("nova")){
				request.getRequestDispatcher("salvar.jsp").forward(request, response);
				doPost(request, response);
			}
			request.getRequestDispatcher("editar.jsp").forward(request, response);
			
			// recebe o id
			String idEditar = request.getParameter("id");
						
			// busca a túpla no banco pelo id
			Tarefa tarefaBusca = tarefadao.buscarPorId(Long.parseLong(idEditar));
			request.setAttribute("tarefaBusca", tarefaBusca);
			
			
		}else if(acao.equals("excluir")) {
			//request.getRequestDispatcher("excluir.jsp").forward(request, response);
			
			// recebe o id
			String id = request.getParameter("id");
			
			
			request.setAttribute("id", id);
			RequestDispatcher destino = request.getRequestDispatcher("excluir.jsp");
			destino.forward(request, response);
			
			//tarefa.setId(Long.parseLong(id));
			//tarefadao.excluir(tarefa);
			
			// permanecer na mesma página
			//List<Tarefa> tarefasE = new TarefaDao().buscarTodos();
			//request.setAttribute("tarefas", tarefasE);
			//request.getRequestDispatcher("listar.jsp").forward(request, response);
			
			
		}else {
			request.getRequestDispatcher("listar.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String pagina;
			
			// Pegar as informações do formulário
			String descricao = request.getParameter("txtDescricao");
			Byte prioridade = Byte.valueOf(request.getParameter("txtPrioridade"));
			short progresso = Short.valueOf(request.getParameter("txtProgresso"));
			Boolean concluida = Boolean.getBoolean(request.getParameter("txtConcluida"));
			
			SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
			String dt = request.getParameter("txtConclusao");
			
			// Conversão data para salvar no banco 
			String dia = dt.substring(0,2);
	         String mes = dt.substring(3,5);
	         String ano = dt.substring(6, 10);
	         String hora = dt.substring(10,13);
	         String min = dt.substring(13,16);
	         String seg = dt.substring(16);
	         String dataBanco = ano + "-" +  mes + "-" + dia + " " + hora + min + seg;
            
			Date data = dataFormatada.parse(dataBanco);
			
			
			
			// Montando o objeto
			Tarefa tarefa = new Tarefa();
			
			tarefa.setDescricao(descricao);
			tarefa.setPrioridade(prioridade);
			tarefa.setProgresso(progresso);
			tarefa.setConcluida(concluida);
			tarefa.setConclusao(data);
			
			// Gravando no Banco
			TarefaDao dao = new TarefaDao();
			
			dao.salvar(tarefa);
			pagina = "index.jsp"; // ficar na mesma página
			
			// Manda para a pagina destino
			request.getRequestDispatcher(pagina).forward(request, response);
			
			JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
		}
		
		
		
		
		
		
	}

}
