package com.example.liuqimin.lifary;

/**
 * Created by liuqi on 2015-08-04.
 */
public class BlogPost {
    private String author;
    private String title;
    public BlogPost (){
        // empty default constructor, necessary for Firebase to be able to deserialize blog posts
    }

    public BlogPost (String a, String t){
        author = a;
        title = t;
    }

    public String getAuthor() {
        return author;
    }
    public String getTitle() {
        return title;
    }
}