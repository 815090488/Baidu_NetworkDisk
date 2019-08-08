package com.luyao.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Filetbl {
    private int fileId;
    private String filename;
    private String username;
    private Timestamp uploadDate;
    private String downcode;
    private String filepath;
    private Integer sortid;
    private String sortname;
    private String srcmd5;
    private Netuser netuserByUsername;
    private Filesort filesortBySortid;
    private Srcfile srcfileBySrcmd5;

    @Id
    @Column(name = "fileId", nullable = false)
    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    @Basic
    @Column(name = "filename", nullable = true, length = 50)
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "uploadDate", nullable = true)
    public Timestamp getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Timestamp uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Basic
    @Column(name = "downcode", nullable = true, length = 32)
    public String getDowncode() {
        return downcode;
    }

    public void setDowncode(String downcode) {
        this.downcode = downcode;
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
    @Column(name = "sortid", nullable = true)
    public Integer getSortid() {
        return sortid;
    }

    public void setSortid(Integer sortid) {
        this.sortid = sortid;
    }

    @Basic
    @Column(name = "sortname", nullable = true, length = 100)
    public String getSortname() {
        return sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

    @Basic
    @Column(name = "srcmd5", nullable = false, length = 32)
    public String getSrcmd5() {
        return srcmd5;
    }

    public void setSrcmd5(String srcmd5) {
        this.srcmd5 = srcmd5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filetbl filetbl = (Filetbl) o;
        return fileId == filetbl.fileId &&
                Objects.equals(filename, filetbl.filename) &&
                Objects.equals(username, filetbl.username) &&
                Objects.equals(uploadDate, filetbl.uploadDate) &&
                Objects.equals(downcode, filetbl.downcode) &&
                Objects.equals(filepath, filetbl.filepath) &&
                Objects.equals(sortid, filetbl.sortid) &&
                Objects.equals(sortname, filetbl.sortname) &&
                Objects.equals(srcmd5, filetbl.srcmd5);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileId, filename, username, uploadDate, downcode, filepath, sortid, sortname, srcmd5);
    }

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    public Netuser getNetuserByUsername() {
        return netuserByUsername;
    }

    public void setNetuserByUsername(Netuser netuserByUsername) {
        this.netuserByUsername = netuserByUsername;
    }

    @ManyToOne
    @JoinColumn(name = "sortid", referencedColumnName = "sortid")
    public Filesort getFilesortBySortid() {
        return filesortBySortid;
    }

    public void setFilesortBySortid(Filesort filesortBySortid) {
        this.filesortBySortid = filesortBySortid;
    }

    @ManyToOne
    @JoinColumn(name = "srcmd5", referencedColumnName = "filecode", nullable = false)
    public Srcfile getSrcfileBySrcmd5() {
        return srcfileBySrcmd5;
    }

    public void setSrcfileBySrcmd5(Srcfile srcfileBySrcmd5) {
        this.srcfileBySrcmd5 = srcfileBySrcmd5;
    }
}
