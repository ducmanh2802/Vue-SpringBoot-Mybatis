package com.hzhang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzhang.pojo.Book;
import com.hzhang.service.BookService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/5/12 17:07
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/findAll/{currentPage}/{pageSize}")
    PageInfo<Book> findAll(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize){
        PageHelper.startPage(currentPage, pageSize);
        PageInfo<Book> pageInfo = new PageInfo<>(bookService.findAll());
        return pageInfo;
    }

    @PostMapping("/save")
    String saveBook(@RequestBody Book book){
        try {
            bookService.saveBook(book);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @PutMapping("/update")
    String updateBook(@RequestBody Book book){
        try {
            bookService.updateBook(book);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @DeleteMapping("/delete/{id}")
    String deleteBook(@PathVariable("id") int id){
        try {
            bookService.deleteBook(id);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
}
