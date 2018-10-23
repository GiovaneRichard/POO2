package br.edu.ifnmg.taskmanager.model;

import java.util.Date;

/* 
 * DESCRIÇÃO DO COMANDO SQL
 * 
 CREATE TABLE tarefa(
 	id bigint(20) NOT NULL AUTO_INCREMENT,
 	descricao varchar(255) NOT NULL,
 	criaçcao datetime DEFAULT CURRENT_TIMESTAMP,
 	prioridade tinyint(4) DEFAULT '1',
 	progresso tinyint(4) DEFAULT '0',
 	concluida tinyint(1) DEFAULT '0',
 	conclusao DATETIME DEFAULT NULL,
 	PRIMARY KEY(id)
 	
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */

public class Tarefa {

	private Long id;
	private String descricao;
	private Date criacao;

	private Byte prioridade;

	private Short progresso;
	private Boolean concluida;
	private Date conclusao;

	public Tarefa(Long id, String descricao, Date criacao, Byte prioridade, Short progresso, Boolean concluida,
			Date conclusao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.criacao = criacao;
		this.prioridade = prioridade;
		this.progresso = progresso;
		this.concluida = concluida;
		this.conclusao = conclusao;
	}
	
	public Tarefa() {};

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getCriacao() {
		return criacao;
	}

	public void setCriacao(Date criacao) {
		this.criacao = criacao;
	}

	public Byte getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Byte prioridade) {
		this.prioridade = prioridade;
	}

	public Short getProgresso() {
		return progresso;
	}

	public void setProgresso(Short progresso) {
		this.progresso = progresso;
	}

	public Boolean getConcluida() {
		return concluida;
	}

	public void setConcluida(Boolean concluida) {
		this.concluida = concluida;
	}

	public Date getConclusao() {
		return conclusao;
	}

	public void setConclusao(Date conclusao) {
		this.conclusao = conclusao;
	}

}
