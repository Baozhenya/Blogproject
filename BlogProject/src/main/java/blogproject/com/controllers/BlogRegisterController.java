package blogproject.com.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blogproject.com.models.entity.Account;
import blogproject.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogRegisterController {
	@Autowired
	private BlogService blogService;

	@Autowired
	private HttpSession session;

	// 記事画面の表示
	@GetMapping("/blog/register")
	public String getBlogRegisterPage(Model model) {
		// セッションからユーザーの情報をaccountに格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
		// もしaccount==null、ログイン画面に戻る
		// そうではないと記事登録画面表示
		if (account == null) {
			return "redirect:/account/login";
		} else {
			model.addAttribute("accountName", account.getAccountName());
			return "blog_register.html";
		}
	}

	// 記事登録
	@PostMapping("/blog/register/process")
	public String blogRegisterProcess(@RequestParam String blogTitle, @RequestParam String categoryName,
			@RequestParam MultipartFile blogImage, @RequestParam String article) {
		// セッションからユーザーの情報をaccountに格納
		Account account = (Account) session.getAttribute("loginAccountInfo");

		// もしaccount==null,ログイン画面
		// そうではない場合、画像ファイル名取得
		// 画像アップロード
		// 同じファイルの名前がなかったら保存
		// 記事一覧画面に戻る
		// じゃないと、記事登録画面に戻る

		if (account == null) {
			return "redirect;/account/login";
		} else {
			// ファイル名前の取得
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
					+ blogImage.getOriginalFilename();

			// ファイル保存作業
			try {
				Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/" + fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (blogService.createBlog(blogTitle, categoryName, fileName, article, account.getAccountId())) {
				return "redirect:/blog/list";
			} else {
				return "blog_register.html";
			}
		}
	}

}
