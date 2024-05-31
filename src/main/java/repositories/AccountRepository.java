package repositories;

import models.Account.AccountEntity;

public interface AccountRepository {
	AccountEntity getAccountByUsername(String username);
	
	AccountEntity getAccountById(int id);
	
	void createAccount(AccountEntity account);
	
	void deleteAccount(int id);
	
	void updateAccount(AccountEntity account);
}
