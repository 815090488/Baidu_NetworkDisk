package com.luyao.pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Folder {
    private int folderid;
    private String foldername;
    private Integer parentid;
    private Date createTime;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "folderid=" + folderid +
                ", foldername='" + foldername + '\'' +
                ", parentid=" + parentid +
                ", createTime=" + createTime +
                '}';
    }

    @Id
    @Column(name = "folderid", nullable = false)
    public int getFolderid() {
        return folderid;
    }

    public void setFolderid(int folderid) {
        this.folderid = folderid;
    }

    @Basic
    @Column(name = "foldername", nullable = true, length = 100)
    public String getFoldername() {
        return foldername;
    }

    public void setFoldername(String foldername) {
        this.foldername = foldername;
    }

    @Basic
    @Column(name = "parentid", nullable = true)
    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    @Basic
    @Column(name = "createTime", nullable = true)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Folder folder = (Folder) o;
        return folderid == folder.folderid &&
                Objects.equals(foldername, folder.foldername) &&
                Objects.equals(parentid, folder.parentid) &&
                Objects.equals(createTime, folder.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(folderid, foldername, parentid, createTime);
    }
}
