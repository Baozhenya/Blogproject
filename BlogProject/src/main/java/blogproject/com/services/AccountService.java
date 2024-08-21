package blogproject.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blogproject.com.models.dao.AccountDao;
import blogproject.com.models.entity.Account;

@Service
public class AccountService {
	@Autowired
	private AccountDao accountDao;

	// 登録処理
	public boolean createAccount(String accountName, String accountEmail, String password) {
		if (accountDao.findByAccountEmail(accountEmail) == null) {
			accountDao.save(new Account(accountName, accountEmail, password));
			return true;
		} else {
			return false;
		}
	}
	
	//ログイン処理
	public Account loginCheck(String accountEmail, String password) {
		Account account = accountDao.findByAccountEmailAndPassword(accountEmail, password);
		if(account == null) {
			return null;
		}else {
			return account;
		}
	}
}
