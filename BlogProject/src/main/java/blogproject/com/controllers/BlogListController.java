package blogproject.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import blogproject.com.models.entity.Account;
import blogproject.com.models.entity.Blog;
import blogproject.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogListController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private BlogService blogService;
	
	//記事一覧画面を表示する
	@GetMapping("/blog/list")
	public String getBlogList(Model model) {
		//セッションからログインしている人の情報を取得
		Account account = (Account) session.getAttribute("loginAccountInfo");
		//もしaccount==null,ログイン画面にリダイレクト、
		//じゃないとログインした人の情報を画面に私、記事一覧のhtml表示
		if(account == null) {
			return"redirect:/account/login";
		}else {
			//記事の情報を取得
			List<Blog> blogList = blogService.selectAllBlogList(account.getAccountId());
			model.addAttribute("accountName",account.getAccountName());
			model.addAttribute("blogList",blogList);
			return "blog_list.html";
		}
		
	}
}
