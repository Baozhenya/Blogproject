package blogproject.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blogproject.com.models.dao.BlogDao;
import blogproject.com.models.entity.Blog;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;

	// 記事一覧をチェック
	// もしaccountId==null 戻り値としてnull
	// findALL内容をコントローラークラスに渡す
	public List<Blog> selectAllBlogList(Long accountId) {
		if (accountId == null) {
			return null;
		} else {
			return blogDao.findByAccountId(accountId);
		}
	}

	// 記事新規処理チェック
	// 同じタイトルのブログあるかどうかチェック
	public boolean createBlog(String blogTitle, String categoryName, String blogImage, String article, Long accountId) {
		if (blogDao.findByBlogTitle(blogTitle) == null) {
			blogDao.save(new Blog(blogTitle, categoryName, blogImage, article, accountId));
			return true;
		} else {
			return false;
		}
	}

	// 編集画面を表示する時のチェック
	// もしblogId==null, null
	// じゃない場合findByBlogId情報をコントローラークラスに渡す
	public Blog blogEditCheck(Long blogId) {
		if (blogId == null) {
			return null;
		} else {
			return blogDao.findByBlogId(blogId);
		}
	}

	// 更新処理のチェック
	// もしblogId == nullだったら、更新処理しない
	// false
	// そうではない場合更新処理、コントローラークラスからもらったblogIdを使って、編集する前のデータを取得
	// 変更すべきとろこだけ、セッターを使用してデータ更新

	public boolean blogUpdate(Long blogId, String blogTitle, String categoryName, String blogImage, String article,
			Long accountId) {
		if (blogId == null) {
			return false;
		} else {
			Blog blog = blogDao.findByBlogId(blogId);
			blog.setBlogTitle(blogTitle);
			blog.setCategoryName(categoryName);
			blog.setBlogImage(blogImage);
			blog.setArticle(article);
			blog.setAccountId(accountId);
			blogDao.save(blog);
			return true;
		}
	}

	// 削除処理のチェック
	// もしコントローラーから貰ったblogIdがnullの場合false
	// そうではない場合deleteByBlogIdを使用し記事削除⇒true
	public boolean deleteBlog(Long blogId) {
		if (blogId == null) {
			return false;
		} else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}

}
