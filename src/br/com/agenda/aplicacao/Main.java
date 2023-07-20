package br.com.agenda.aplicacao;

import java.util.Date;

import br.com.agenda.dao.ContatoDao;
import br.com.agenda.model.Contato;

public class Main {

	public static void main(String[] args) {

		ContatoDao contatoDao = new ContatoDao();

		Contato contato2 = new Contato();
		contato2.setNome("Maria Gabriela");
		contato2.setIdade(35);
		contato2.setDataCadastro(new Date());

		contatoDao.save(contato2);

		Contato contato = new Contato();
		contato.setNome("maria gabriela dias orlando");
		contato.setDataCadastro(new Date());

		contatoDao.update(contato);
		
		//contatoDao.deleteByID(2);

		for (Contato c : contatoDao.getContatos()) {
			System.out.println("Contato:" + c.getNome());
		}
	}

}
