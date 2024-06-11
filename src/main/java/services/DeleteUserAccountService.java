package services;

import java.sql.Connection;
import java.sql.SQLException;

import database.ConnectionFactory;
import models.Account.AccountDAO;
import models.Account.AccountEntity;
import models.User.UserDAO;

public class DeleteUserAccountService {
	//Declara um objeto ConnectionFactory para criar conexões com o banco de dados://
	ConnectionFactory connection;

	//Construtor da classe, que inicializa o objeto ConnectionFactory://
	public DeleteUserAccountService() {
		this.connection = new ConnectionFactory();
	}

	//Declaração do método execute, que não retorna nada (void) e aceita um parâmetro accountId do tipo int://
	public void execute(int accountId) {	
		//Cria uma conexão com o banco de dados usando o ConnectionFactory://
		Connection dbConnection = this.connection.getConnection();
		//Cria um objeto AccountDAO usando a conexão de banco de dados://
		AccountDAO accountDAO = new AccountDAO(dbConnection);

		//Inicia um bloco de código que tenta executar operações que podem lançar exceções://
		try {
			//Usa o accountDAO para buscar a conta a ser deletada pelo accountId://
			AccountEntity accountToBeDeleted = accountDAO.getAccountById(accountId);
			//Chama o método deleteAccount do accountDAO para deletar a conta encontrada.//
			accountDAO.deleteAccount(accountToBeDeleted.getId());

			//O bloco finally é sempre executado, independentemente se a execução normal ou uma exceção foi lançada no bloco try.//
		} finally {
	        try {
			//Verifica se a conexão dbConnection não é nula e está aberta, e então a fecha para liberar os recursos://
	            if (dbConnection != null && !dbConnection.isClosed()) {
	                dbConnection.close();
	            }
			//Captura qualquer SQLException que pode ocorrer ao fechar a conexão://
	        } catch (SQLException e) {
			//Lança uma nova RuntimeException com uma mensagem de erro e a exceção original (e), se ocorrer um problema ao fechar a conexão://
	            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", e);
	        }
	    }
	}
}
