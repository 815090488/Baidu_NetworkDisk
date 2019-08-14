package com.luyao.action;

import com.alibaba.fastjson.JSON;
import com.luyao.pojo.Netuser;
import com.luyao.service.FolderService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class FolderAction extends BaseAction {

    private FolderService folderService;
    private HttpServletResponse response = ServletActionContext.getResponse();
    private HttpServletRequest requestSession = ServletActionContext.getRequest();
    /**
     * 创建文件夹
     * @return
     */

    public void createFolder() {
        String foldername = requestSession.getParameter("foldername");
        Netuser user = (Netuser) session.get("user");
        System.out.println(foldername+"----------------------");
        folderService.save(user,foldername);
        String string = JSON.toJSONString("true");
        response.setContentType("text/html;charset=UTF-8");
        try {
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FolderService getFolderService() {
        return folderService;
    }

    public void setFolderService(FolderService folderService) {
        this.folderService = folderService;
    }
}
