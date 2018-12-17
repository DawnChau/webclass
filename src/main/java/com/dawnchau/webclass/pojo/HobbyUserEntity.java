package com.dawnchau.webclass.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "hobby_user", schema = "webclass", catalog = "")
public class HobbyUserEntity {
    private int id;
    private int userid;
    private int hobbyid;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)  // 主键自增
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userid")
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "hobbyid")
    public int getHobbyid() {
        return hobbyid;
    }

    public void setHobbyid(int hobbyid) {
        this.hobbyid = hobbyid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HobbyUserEntity that = (HobbyUserEntity) o;
        return id == that.id &&
                userid == that.userid &&
                hobbyid == that.hobbyid;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userid, hobbyid);
    }
}
