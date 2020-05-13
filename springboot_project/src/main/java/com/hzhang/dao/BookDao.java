package com.hzhang.dao;

import com.hzhang.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BookDao {
    /**
     * 查询所有
     * @return
     */
    List<Book> findAll();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Book findById(int id);

    /**
     * 保存书籍
     * @param book
     */
    void saveBook(Book book);

    /**
     * 更新书籍信息
     * @param book
     */
    void updateBook(Book book);

    /**
     * 根据id删除书籍
     * @param id
     */
    void deleteBook(int id);
}
