package com.zjwam.qualification.bean;

import java.util.List;

public class CourseAnswerBean {
    private int count;
    private List<Answer> answer;

    public int getCount() {
        return count;
    }

    public List<Answer> getAnswer() {
        return answer;
    }

    public class Answer{
        private String nickname,pic,addtime,content,vname,name,sub_pic,sub_addtime,sub_nickname,sub_content;
        private long id,sub_id;
        private int sub_count;

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

        public String getVname() {
            return vname;
        }

        public String getName() {
            return name;
        }

        public String getSub_pic() {
            return sub_pic;
        }

        public String getSub_addtime() {
            return sub_addtime;
        }

        public String getSub_nickname() {
            return sub_nickname;
        }

        public String getSub_content() {
            return sub_content;
        }

        public long getId() {
            return id;
        }

        public long getSub_id() {
            return sub_id;
        }

        public int getSub_count() {
            return sub_count;
        }
    }
}
