package com.zjwam.qualification.bean;

import java.util.List;

public class MineCommentBean {
    private int count;
    private List<Comment> comment;

    public int getCount() {
        return count;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public class Comment{
        private String content,addtime,name;
        private long id;
        private int star;

        public String getContent() {
            return content;
        }

        public String getAddtime() {
            return addtime;
        }

        public String getName() {
            return name;
        }

        public long getId() {
            return id;
        }

        public int getStar() {
            return star;
        }
    }
}
