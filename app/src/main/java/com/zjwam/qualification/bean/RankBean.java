package com.zjwam.qualification.bean;

import java.util.List;

public class RankBean {
    private String logo;
    private List<Rank> rank;

    public String getLogo() {
        return logo;
    }

    public List<Rank> getRank() {
        return rank;
    }

    public class Rank{
        private String pic,sign,nickname;
        private int num,type,ranking;

        public String getPic() {
            return pic;
        }

        public String getSign() {
            return sign;
        }

        public String getNickname() {
            return nickname;
        }

        public int getNum() {
            return num;
        }

        public int getType() {
            return type;
        }

        public int getRanking() {
            return ranking;
        }
    }
}
