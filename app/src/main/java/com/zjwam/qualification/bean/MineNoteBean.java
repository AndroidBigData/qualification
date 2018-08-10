package com.zjwam.qualification.bean;

import java.util.List;

public class MineNoteBean {
    private int count;
    private List<Note> note;

    public int getCount() {
        return count;
    }

    public List<Note> getNote() {
        return note;
    }

    public class Note{
        private String note,vtime,addtime,name,vname;
        private long id;

        public String getNote() {
            return note;
        }

        public String getVtime() {
            return vtime;
        }

        public String getAddtime() {
            return addtime;
        }

        public String getName() {
            return name;
        }

        public String getVname() {
            return vname;
        }

        public long getId() {
            return id;
        }
    }
}
