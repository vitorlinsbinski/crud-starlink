package repositories;

import models.Account.AccountEntity;

public interface AccountRepository {
	AccountEntity getAccountByUsername(String username);
	
	void createAccount(AccountEntity account);
	
	void deleteAccount(int id);
}
