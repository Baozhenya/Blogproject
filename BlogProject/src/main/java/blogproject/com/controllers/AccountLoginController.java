package blogproject.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	public String getAccountLoginPage(Model model) {
		//普段errorメッセージを表示させないように
		model.addAttribute("error", false);
		return "login.html";
	}
	
	//ログイン処理
	@PostMapping("/account/login/process")
	public ModelAndView accountLoginProcess(@RequestParam String accountEmail, @RequestParam String password, ModelAndView mav) {
		//loginCheckメソッドを呼び出し
		Account account= accountService.loginCheck(accountEmail, password);
		if(account == null) {
			//メールとパスワードが正しくなかった場合、error内容を表示させる
			mav.addObject("error",true);
			mav.setViewName("login.html");
			return mav;
		}else {
			session.setAttribute("loginAccountInfo", account);
			mav.addObject("accountEmail",accountEmail);
			mav.addObject("password",password);
			mav.setViewName("redirect:/blog/list");
			return mav;
		}
	}

}
