package com.luyao.service;

import com.luyao.dao.FolderDao;
import com.luyao.pojo.Folder;
import com.luyao.pojo.Netuser;

import java.sql.Date;

public class FolderService {

    private FolderDao folderDao;

    public void save(Netuser user, String foldername) {

        System.out.println("FolderService     Foldername======================="+foldername);
        Folder folder = new Folder();
        folder.setFoldername(foldername);
        folder.setCreateTime(new Date(System.currentTimeMillis()));
        folder.setUsername(user.getUsername());
        folderDao.save(folder);
    }

    public FolderDao getFolderDao() {
        return folderDao;
    }

    public void setFolderDao(FolderDao folderDao) {
        this.folderDao = folderDao;
    }
}
