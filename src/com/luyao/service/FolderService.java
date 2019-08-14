package com.luyao.service;

import com.luyao.dao.FileDao;
import com.luyao.dao.FolderDao;
import com.luyao.pojo.Filetbl;
import com.luyao.pojo.Folder;
import com.luyao.pojo.Netuser;

import java.sql.Date;
import java.util.List;

public class FolderService {

    private FolderDao folderDao;
    private FileDao fileDao;

    public void save(Netuser user, String foldername, Integer parentid) {
        Folder folder = new Folder();
        folder.setFoldername(foldername);
        folder.setCreateTime(new Date(System.currentTimeMillis()));
        folder.setUsername(user.getUsername());
        folder.setParentid(parentid);
        folderDao.save(folder);
    }



    public List<Folder> seleByIdandName(Integer parentid,String username) {
      List<Folder> folder = folderDao.seleByIdandName(parentid,username);
      return folder;
    }

    public Folder seleById(Integer parentid) {
        Folder folder = folderDao.seleById(parentid);
        return folder;
    }

    public List<Filetbl> seleFieName(String username) {
        List list = fileDao.seleFieName(username);
        return list;
    }

    public FolderDao getFolderDao() {
        return folderDao;
    }

    public void setFolderDao(FolderDao folderDao) {
        this.folderDao = folderDao;
    }

    public FileDao getFileDao() {
        return fileDao;
    }

    public void setFileDao(FileDao fileDao) {
        this.fileDao = fileDao;
    }


}
