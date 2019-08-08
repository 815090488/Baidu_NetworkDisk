package com.luyao.pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
import java.util.Set;

@Entity
public class Filesort {
    private int sortid;
    private String sortname;
    private String remark;


    @Id
    @Column(name = "sortid", nullable = false)
    public int getSortid() {
        return sortid;
    }

    public void setSortid(int sortid) {
        this.sortid = sortid;
    }

    @Basic
    @Column(name = "sortname", nullable = true, length = 30)
    public String getSortname() {
        return sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 100)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filesort filesort = (Filesort) o;
        return sortid == filesort.sortid &&
                Objects.equals(sortname, filesort.sortname) &&
                Objects.equals(remark, filesort.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sortid, sortname, remark);
    }
}
