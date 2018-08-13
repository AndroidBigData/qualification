package com.zjwam.qualification.bean;

import java.util.List;

public class MineMsgBean {
    private int count;
    private List<Notice> notice;

    public int getCount() {
        return count;
    }

    public List<Notice> getNotice() {
        return notice;
    }

    public class Notice{
        private String name,title,addtime,content,type;
        private int is_read;
        private long id;

        public String getName() {
            return name;
        }

        public String getTitle() {
            return title;
        }

        public String getAddtime() {
            return addtime;
        }

        public String getContent() {
            return content;
        }

        public String getType() {
            return type;
        }

        public void setIs_read(int is_read) {
            this.is_read = is_read;
        }

        public int getIs_read() {
            return is_read;
        }

        public long getId() {
            return id;
        }
    }
}
