package com.zjwam.qualification.bean;

import java.util.List;

public class VideoCommentBean {
    private int count;
    private List<Comment> comment;

    public int getCount() {
        return count;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public class Comment{
        private int star;
        private String nickname,pic,addtime,content;

        public int getStar() {
            return star;
        }

        public String getNickname() {
            return nickname;
        }

        public String getPic() {
            return pic;
        }

        public String getAddtime() {
            return addtime;
        }

        public String getContent() {
            return content;
        }
    }
}
