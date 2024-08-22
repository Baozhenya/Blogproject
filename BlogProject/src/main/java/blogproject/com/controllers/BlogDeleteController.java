package blogproject.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import blogproject.com.models.entity.Account;
import blogproject.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogDeleteController {
	@Autowired
	private BlogService blogService;

	@Autowired
	private HttpSession session;

	@PostMapping("/blog/delete")
	public String blogDelete(Long blogId) {
		// セッションからユーザーの情報をaccountに格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
		// もしaccount==null、ログイン画面に戻る
		if (account == null) {
			return "redirect:/account/login";
		} else {
			//もしdeleteBlog結果はtrue
			//記事の一覧ページにリダイレクト
			//そうでない場合編集画面にリダイレクト
			if(blogService.deleteBlog(blogId)) {
				return "redirect:/blog/list";
			}else {
				return "redirect:/blog/edit"+blogId;
			}
		}
	}

}
