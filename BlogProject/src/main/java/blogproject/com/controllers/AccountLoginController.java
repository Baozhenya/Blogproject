package blogproject.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blogproject.com.models.entity.Account;
import blogproject.com.services.AccountService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AccountLoginController {
	@Autowired
	private AccountService accountService;
	
	//Sessionが使えるように宣言
	@Autowired
	private HttpSession session;
	
	//ログイン画面の表示
	@GetMapping("/account/login")
	public String getAccountLoginPage() {
		return "login.html";
	}
	
	//ログイン処理
	@PostMapping("/account/login/process")
	public String accountLoginProcess(@RequestParam String accountEmail, @RequestParam String password) {
		//loginCheckメソッドを呼び出し
		Account account= accountService.loginCheck(accountEmail, password);
		if(account == null) {
			return "login.html";
		}else {
			session.setAttribute("loginAccountInfo", account);
			return "redirect:/blog/list";
		}
	}

}
