package com.smartflow.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author ：tao
 * @date ：Created in 2020/10/21 18:05
 * @description：${description}
 * @modified By：
 * @version: version
 */

@Entity
@Table(name = "user_error", schema = "dbo")
public class UserError implements Serializable {
    private int id;
    private int userid;
    private int errorid;
    private Integer departid;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userid", nullable = false)
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "errorid", nullable = false)
    public int getErrorid() {
        return errorid;
    }

    public void setErrorid(int errorid) {
        this.errorid = errorid;
    }

    @Basic
    @Column(name = "departid", nullable = true)
    public Integer getDepartid() {
        return departid;
    }

    public void setDepartid(Integer departid) {
        this.departid = departid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserError userError = (UserError) o;
        return id == userError.id &&
                userid == userError.userid &&
                errorid == userError.errorid &&
                Objects.equals(departid, userError.departid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userid, errorid, departid);
    }

    @Override
    public String toString() {
        return "UserError{" +
                "id=" + id +
                ", userid=" + userid +
                ", errorid=" + errorid +
                ", departid=" + departid +
                '}';
    }
}
