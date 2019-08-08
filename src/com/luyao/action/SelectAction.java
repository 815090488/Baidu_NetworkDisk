package com.luyao.action;

import com.luyao.dao.FileDao;
import com.luyao.dao.SelectDao;
import com.luyao.pojo.Filetbl;
import com.luyao.pojo.Netuser;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SelectAction extends BaseAction {
    private SelectDao selectDao;
    private HttpServletRequest requestSession = ServletActionContext.getRequest();
    private HttpServletResponse response = ServletActionContext.getResponse();
    /**
     * 文件搜索
     */
    public String selectFile(){
//        selectFile
        String selectFile = requestSession.getParameter("selectFile");
        System.out.println(selectFile+"------selectFile-----------  SelectAction-----");
        Netuser user = (Netuser) session.get("user");
        String username = user.getUsername();
        List<Filetbl> filetblList = selectDao.selectfile(selectFile,username);
        System.out.println(filetblList+"list-------------selectList       --SelectAction----------------");
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
}
