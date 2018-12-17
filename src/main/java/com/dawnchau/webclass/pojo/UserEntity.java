package com.dawnchau.webclass.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "webclass", catalog = "")
public class UserEntity {
    private int id;
    private int role;
    private byte disabled;
    private String account;
    private String password;
    private String email;

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
    @Column(name = "role")
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Basic
    @Column(name = "disabled")
    public byte getDisabled() {
        return disabled;
    }

    public void setDisabled(byte disabled) {
        this.disabled = disabled;
    }

    @Basic
    @Column(name = "account")
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id &&
                role == that.role &&
                disabled == that.disabled &&
                Objects.equals(account, that.account) &&
                Objects.equals(password, that.password) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, role, disabled, account, password, email);
    }
}
