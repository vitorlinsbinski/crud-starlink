package services;

import java.sql.Connection;
import java.sql.SQLException;

import database.ConnectionFactory;
import exceptions.ResourceNotFoundException;
import exceptions.UserCredentialsDoesNotMatch;
import models.Account.AccountDAO;
import models.Account.AccountEntity;
import models.User.UserDAO;
import repositories.AccountRepository;
import utils.Hashing;

public class AuthenticateService {
    //Declara um objeto ConnectionFactory para criar conexões com o banco de dados://
    private ConnectionFactory connection;

    //Construtor da classe, que inicializa o objeto ConnectionFactory://
    public AuthenticateService() {
        this.connection = new ConnectionFactory();
    }

    //Declaração do método execute, que retorna um AccountEntity e aceita dois parâmetros: username (nome de usuário) e password (senha)://
    public AccountEntity execute(String username, String password) {
        //Cria uma conexão com o banco de dados usando o ConnectionFactory://
        Connection dbConnection = this.connection.getConnection();
        
        try {
            //Usa AccountDAO para buscar a conta pelo nome de usuário://
        	AccountEntity account = new AccountDAO(dbConnection).getAccountByUsername(username);

            //Se a conta não for encontrada, lança uma exceção ResourceNotFoundException.://
        	if(account == null) {
        		throw new ResourceNotFoundException("Conta não encontrada.");
        	}
        	//Gera o hash da senha fornecida://
        	String hashedPassword = Hashing.hashPassword(password);

            //Compara a senha hashed fornecida com a senha armazenada na conta://
        	boolean doesPasswordMatch = account.getPassword().equals(hashedPassword);

            //Se as senhas não coincidirem, lança uma exceção UserCredentialsDoesNotMatch://
            if(!doesPasswordMatch) {
                throw new UserCredentialsDoesNotMatch("Credenciais inválidas.");
            }
            //Se as credenciais forem válidas, retorna a conta do usuário://
            return account;

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

