package com.luyao.action;

import com.luyao.dao.SelectDao;
import com.luyao.pojo.Filetbl;
import com.luyao.pojo.Netuser;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SelectAction extends BaseAction {
    private SelectDao selectDao;
    private Integer folderid;
    private HttpServletRequest requestSession = ServletActionContext.getRequest();


    /**
     * 文件夹删除
     */
    public String deleteFold(){
        Integer fileid = Integer.parseInt(requestSession.getParameter("folderid"));
        folderid = Integer.parseInt(requestSession.getParameter("parentid"));
        selectDao.deleteFold(fileid);
        return SUCCESS;
    }


    /**
     * 文件删除
     */
    public String deleteFile(){
        Integer fileid = Integer.parseInt(requestSession.getParameter("fileid"));
        folderid = Integer.parseInt(requestSession.getParameter("parentid"));
        selectDao.deletFile(fileid);
        return SUCCESS;
    }

    /**
     * 文件搜索
     */
    public String selectFile(){
//        selectFile
        String selectFile = requestSession.getParameter("selectFile");
        Netuser user = (Netuser) session.get("user");
        String username = user.getUsername();
        List<Filetbl> filetblList = selectDao.selectfile(selectFile,username);
        request.put("filetbls",filetblList);
        return SUCCESS;
    }

    /**
     * 根据文件类型查找
     * @return
     */
    public String selectAction(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String fileType = request.getParameter("fileType");
        System.out.println(fileType);
        Netuser user = (Netuser) session.get("user");
        String username = user.getUsername();
        //分类
        List fileClassify  = selectDao.seleImage(fileType,username);
        this.request.put("filetbls",fileClassify);
        return SUCCESS;
    }

    public SelectDao getSelectDao() {
        return selectDao;
    }

    public void setSelectDao(SelectDao selectDao) {
        this.selectDao = selectDao;
    }

    public Integer getFolderid() {
        return folderid;
    }

    public void setFolderid(Integer folderid) {
        this.folderid = folderid;
    }
}
