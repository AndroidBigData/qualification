package com.zjwam.qualification.bean;

import java.util.List;

public class VideoNoteBean {
    private int count;
    private List<Note> note;

    public int getCount() {
        return count;
    }

    public List<Note> getNote() {
        return note;
    }

    public class Note{
        private String nickname,pic,addtime,note;
        private int zan,iszan,ismy;
        private long id;

        public String getNickname() {
            return nickname;
        }

        public String getPic() {
            return pic;
        }

        public String getAddtime() {
            return addtime;
        }

        public String getNote() {
            return note;
        }

        public int getZan() {
            return zan;
        }

        public int getIszan() {
            return iszan;
        }

        public int getIsmy() {
            return ismy;
        }

        public long getId() {
            return id;
        }
    }
}
