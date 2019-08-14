package com.luyao.action;

import com.alibaba.fastjson.JSON;
import com.luyao.dao.FileDao;
import com.luyao.pojo.Filetbl;
import com.luyao.pojo.Folder;
import com.luyao.pojo.Netuser;
import com.luyao.service.FolderService;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class FolderAction extends BaseAction implements ModelDriven<Folder> {

    private Folder folder = new Folder();
    private FolderService folderService;
    private HttpServletResponse response = ServletActionContext.getResponse();
    private HttpServletRequest requestSession = ServletActionContext.getRequest();

    /**
     * 创建文件夹
     *
     * @return
     */
    public void createFolder() {
        Netuser user = (Netuser) session.get("user");
        folderService.save(user, folder.getFoldername(), folder.getFolderid());
        String string = JSON.toJSONString("true");
        response.setContentType("text/html;charset=UTF-8");
        try {
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查找文件夹
     */
    public String seleFolderByName() {
        Netuser user = (Netuser) session.get("user");
        List<Folder> folderList = folderService.seleByIdandName(folder.getParentid(), user.getUsername());
        System.out.println(folderList + "LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");

        Folder folder2 = folderService.seleById(folder.getParentid());
        List<Filetbl> filetbls = folderService.seleFieName(folder.getParentid(),user.getUsername());

        request.put("folders", folderList);
        request.put("folder", folder2);
        request.put("filetbls", filetbls);
        return SUCCESS;
    }

    public FolderService getFolderService() {
        return folderService;
    }

    public void setFolderService(FolderService folderService) {
        this.folderService = folderService;
    }

    @Override
    public Folder getModel() {
        return folder;
    }
}
