package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        Blog blog = new Blog();
        // set blog title and content
        blog.setTitle(title);
        blog.setContent(content);
        // blog is created by the current userId
        User user = userRepository1.findById(userId).get();
        List<Blog> blogList = user.getBlogList();
        blogList.add(blog);
        // user set his blog list
        user.setBlogList(blogList);
        blog.setUser(user);
        // save in database
        blogRepository1.save(blog);
        return blog;
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        Blog blog = blogRepository1.findById(blogId).get();
        List<Image> imageList = blog.getImageList();
        imageList.clear();
        blogRepository1.deleteById(blogId);
    }
}
