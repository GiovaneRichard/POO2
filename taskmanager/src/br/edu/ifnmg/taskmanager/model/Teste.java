package br.edu.ifnmg.taskmanager.model;

import java.awt.print.Printable;

import br.edu.ifnmg.taskmanager.controller.Banco;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Banco.getConexao();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		String dt = "19/10/2018 17:28:00";
		
		 String dia = dt.substring(0,2);
         String mes = dt.substring(3,5);
         String ano = dt.substring(6, 10);
         String hora = dt.substring(10,13);
         String min = dt.substring(13,16);
         String seg = dt.substring(16);
         String dataBanco = ano + "-" +  mes + "-" + dia + " " + hora + min + seg;
         
         System.out.println(dataBanco);
		
	}

}
