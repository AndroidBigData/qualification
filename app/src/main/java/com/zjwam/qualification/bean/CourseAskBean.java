package com.zjwam.qualification.bean;

import java.util.List;

public class CourseAskBean {
    private int count;
    private List<Answer> answer;

    public int getCount() {
        return count;
    }

    public List<Answer> getAnswer() {
        return answer;
    }

    public class Answer{
        private String nickname,pic,addtime,content,name,vname,sub_new;
        private long id;
        private int count;

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

        public String getName() {
            return name;
        }

        public String getVname() {
            return vname;
        }

        public String getSub_new() {
            return sub_new;
        }

        public long getId() {
            return id;
        }

        public int getCount() {
            return count;
        }
    }
}
