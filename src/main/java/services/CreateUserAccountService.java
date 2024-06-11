package services;

import java.sql.Connection;
import java.sql.SQLException;

//Classe responsavel por criar e fornecer conexões com o banco de dados.//
import database.ConnectionFactory;
import exceptions.UserAlreadyExistsExepction;
//Classe responsável por interagir com a tabela de contas "Account" no banco de dados. Encapsula toda a lógica de acesso a dados e fornece métodos para realizar operações CRUD (create, read, update, delete) na tabela de contas.//
import models.Account.AccountDAO;
import models.Account.AccountEntity;
import models.User.UserDAO;
import models.User.UserEntity;

public class CreateUserAccountService {
	private ConnectionFactory connection;
	private GetUserByEmailService getUserByEmailService;
	private GetAccountByUsernameService getAccountByUsername;

	public CreateUserAccountService() {
		//Este objeto é responsável por fornecer conexões com o banco de dados://
		this.connection = new ConnectionFactory();
		//Este objeto é responsável por buscar o usuário pelo e-mail://
		this.getUserByEmailService = new GetUserByEmailService();
		//Este objeto é responsável por buscar a conta pelo nome de usuário://
		this.getAccountByUsername = new GetAccountByUsernameService();
	}
	//Declara um método público chamado execute que não retorna nenhum valor (void) e recebe dois parâmetros: um do tipo UserEntity chamado user e outro do tipo AccountEntity chamado account://
	public void execute(UserEntity user, AccountEntity account) {
		//Está obtendo uma conexão com o banco de dados usando uma instância de ConnectionFactory que está armazenada no atributo connection da classe atual. O resultado dessa operação é armazenado na variável dbConnection://
	    Connection dbConnection = this.connection.getConnection();
		//Está inicializando uma instância de AccountDAO, que nos permite interagir com o banco de dados para realizar operações relacionadas às contas, utilizando a conexão dbConnection://
	    AccountDAO accountDAO = new AccountDAO(dbConnection);
		//Está inicializando uma instância de UserDAO, que nos permite interagir com o banco de dados para realizar operações relacionadas aos usuários, utilizando a conexão dbConnection://
	    UserDAO userDAO = new UserDAO(dbConnection);
	    
	    try {
		//Procura o usuário pelo e-mail e salva a informação na váriavel userOnDatabase://
	        UserEntity userOnDatabase = getUserByEmailService.execute(user.getEmail());
		//Procura a conta pelo nome de usuário e salva a informação na váriavel accountOnDatabase://
	        AccountEntity accountOnDatabase = getAccountByUsername.execute(account.getUsername());
	        
	        if (userOnDatabase == null && accountOnDatabase == null) {
                UserEntity newUser = userDAO.createUser(user);
                
                account.setUserId(newUser.getId());
                
                accountDAO.createAccount(account);
            } else if (userOnDatabase != null && accountOnDatabase == null) {
		//Estamos definindo o ID do usuário existente (userOnDatabase.getId()) no objeto user, para garantir que estamos atualizando o usuário correto no banco de dados://
            	user.setId(userOnDatabase.getId());
            	//Estamos chamando um método chamado updateUser do objeto userDAO para atualizar as informações do usuário existente no banco de dados, utilizando as informações fornecidas no objeto user://
            	userDAO.updateUser(user);
            	//Estamos definindo o ID do usuário existente (userOnDatabase.getId()) no objeto account, para garantir que a conta criada esteja associada a esse usuário://
                account.setUserId(userOnDatabase.getId());
                //Estamos chamando um método por nome createAccount do objeto accountDAO para criar uma nova conta no banco de dados, utilizando as informações fornecidas no objeto account://
                accountDAO.createAccount(account);
            } else {
                throw new UserAlreadyExistsExepction("Email ou nome de usuário já registrado.");
            }
	        
	    } finally {
	        try {
			//Verificamos se a conexão com o banco de dados não é nula e se não está fechada. Se a conexão não for nula e estiver aberta, então a fechamos://
	            if (dbConnection != null && !dbConnection.isClosed()) {
			    //Chamamos o método close() na conexão com o banco de dados para fechá-la://
	                dbConnection.close();
	            }
			//Se ocorrer algum erro ao tentar fechar a conexão, capturamos essa exceção do tipo SQLException://
	        } catch (SQLException e) {
			//Esta exceção contém uma mensagem de erro informando que ocorreu um problema ao fechar a conexão com o banco de dados, e também inclui a exceção original (e) que foi capturada.//
	            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", e);
	        }
	    }
	}

}
