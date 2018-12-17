package com.dawnchau.webclass.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "hobby", schema = "webclass", catalog = "")
public class HobbyEntity {
    private int id;
    private String hobby;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "hobby")
    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HobbyEntity that = (HobbyEntity) o;
        return id == that.id &&
                Objects.equals(hobby, that.hobby);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, hobby);
    }
}
