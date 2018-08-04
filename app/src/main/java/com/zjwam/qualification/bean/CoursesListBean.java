package com.zjwam.qualification.bean;

import java.util.List;

public class CoursesListBean {
    private int count;
    private List<classList> class_list;

    public int getCount() {
        return count;
    }

    public List<classList> getClassList() {
        return class_list;
    }

    public class classList{
        private long id;
        private String name,img,ratio,intro,type;
        private int snum,num;

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getImg() {
            return img;
        }

        public String getRatio() {
            return ratio;
        }

        public String getIntro() {
            return intro;
        }

        public String getType() {
            return type;
        }

        public int getSnum() {
            return snum;
        }

        public int getNum() {
            return num;
        }
    }
}
