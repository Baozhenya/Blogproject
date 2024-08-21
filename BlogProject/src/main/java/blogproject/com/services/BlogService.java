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
	
	//記事一覧をチェック
	//もしaccountId==null 戻り値としてnull
	//findALL内容をコントローラークラスに渡す
	public List<Blog>selectAllBlogList(Long accountId){
		if(accountId == null) {
			return null;
		}else {
			return blogDao.findAll();
		}
	}

}
