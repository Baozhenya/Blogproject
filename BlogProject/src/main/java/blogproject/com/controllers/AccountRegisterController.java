package blogproject.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blogproject.com.services.AccountService;

@Controller
public class AccountRegisterController {

	@Autowired
	private AccountService accountService;

	// 登録画面の表示
	@GetMapping("/account/register")
	public String getAccountRegisterPage() {
		return "register.html";
	}

	// 登録処理
	@PostMapping("/account/register/process")
	public String accountRegisterProcess(@RequestParam String accountName, @RequestParam String accountEmail,
			@RequestParam String password) {
		// 登録成功だったらログイン画面に進む
		// じゃないと、登録画面に戻る
		if (accountService.createAccount(accountName, accountEmail, password)) {
			return "login.html";
		} else {
			return "register.html";
		}
	}
}
