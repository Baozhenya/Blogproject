package blogproject.com.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blogproject.com.models.entity.Blog;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface BlogDao extends JpaRepository<Blog, Long> {
	// 保存処理と更新処理、insert,update
	Blog save(Blog blog);

	// SELECT * FROM blog
	// 記事一覧を表示させる時に使用
	List<Blog> findAll();

	// SELECT * FROM blog WHERE blog_title=?
	// 同じ名前の記事が登録されないとうに
	Blog findByBlogTitle(String blogTitle);

	// SELECT * FROM blog WHERE blog_id = ?
	// 編集画面を表示する時に使用
	Blog findByBlogId(Long blogId);

	// DELETE FROM blog WHERE blog_id = ?
	// 削除に使用
	void deleteByBlogId(Long blogId);

}
