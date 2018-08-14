package com.zjwam.qualification.bean;

import java.util.List;

public class QADetailsBean {
    private String nickname,pic,addtime,content,name,vname;
    private int count;
    private long id;
    private List<Sub> sub;

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

    public int getCount() {
        return count;
    }

    public long getId() {
        return id;
    }

    public List<Sub> getSub() {
        return sub;
    }

    public String getName() {
        return name;
    }

    public String getVname() {
        return vname;
    }

    public class Sub {
        private String nickname,addtime,content,pic;
        private int zan,iszan;
        private long id;

        public String getNickname() {
            return nickname;
        }

        public String getAddtime() {
            return addtime;
        }

        public String getContent() {
            return content;
        }

        public String getPic() {
            return pic;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }

        public void setIszan(int iszan) {
            this.iszan = iszan;
        }

        public int getZan() {
            return zan;
        }

        public int getIszan() {
            return iszan;
        }

        public long getId() {
            return id;
        }
    }
}
