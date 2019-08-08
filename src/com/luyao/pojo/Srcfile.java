package com.luyao.pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
public class Srcfile {
    private String filecode;
    private String filepath;
    private Timestamp uptime;
    private String author;


    @Id
    @Column(name = "filecode", nullable = false, length = 32)
    public String getFilecode() {
        return filecode;
    }

    public void setFilecode(String filecode) {
        this.filecode = filecode;
    }

    @Basic
    @Column(name = "filepath", nullable = true, length = 150)
    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @Basic
    @Column(name = "uptime", nullable = true)
    public Timestamp getUptime() {
        return uptime;
    }

    public void setUptime(Timestamp uptime) {
        this.uptime = uptime;
    }

    @Basic
    @Column(name = "author", nullable = true, length = 50)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Srcfile srcfile = (Srcfile) o;
        return Objects.equals(filecode, srcfile.filecode) &&
                Objects.equals(filepath, srcfile.filepath) &&
                Objects.equals(uptime, srcfile.uptime) &&
                Objects.equals(author, srcfile.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filecode, filepath, uptime, author);
    }
}
