package blogproject.com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blogproject.com.models.entity.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {

	// 保存処理と更新処理insert、update
	Account save(Account account);

	// SELECT * FROM account WHERE account_email = ?
	// 管理者の登録処理をするときに、同じメールアドレスで登録させない
	Account findByAccountEmail(String accountEmail);

	// SELECT * FROM account WHERE account_email And password=?
	// ログイン処理
	Account findByAccountEmailAndPassword(String accountEmail, String password);
}
