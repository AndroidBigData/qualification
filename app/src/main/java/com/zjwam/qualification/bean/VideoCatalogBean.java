package com.zjwam.qualification.bean;

import java.util.List;

public class VideoCatalogBean {
    private int buy;
    private List<Catalog> video;

    public int getBuy() {
        return buy;
    }

    public List<Catalog> getVideo() {
        return video;
    }

    public class Catalog{
        private String vname,address;
        private long id;

        public String getVname() {
            return vname;
        }

        public String getAddress() {
            return address;
        }

        public long getId() {
            return id;
        }
    }
}
