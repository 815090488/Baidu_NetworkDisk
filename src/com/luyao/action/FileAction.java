package com.luyao.action;

import com.luyao.dao.FileDao;
import com.luyao.pojo.Filesort;
import com.luyao.pojo.Filetbl;
import com.luyao.pojo.Netuser;
import com.luyao.pojo.Srcfile;
import com.luyao.util.Config;
import com.luyao.util.FileUtil;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.List;


public class FileAction extends BaseAction {

    //判断登录状态
    //判断会员类型 上传大小
    //判断所剩容量
    //多文件上传
    //文件上传成功后，入库
    // 入库：文件入库   用户文件记录入库   File[] upload
    //usertype 1:普通用户 2：普通会员 3、超级会员

    private File[] file;
    private String[] fileFileName;
    private String[] stortname = new String[10];
    private FileDao fileDao;
    private String username;
    private String filename;
    private String fileMd5;

    private static final long USERFILESIZE = 1024 * 1024 * 10;//普通用户
    private static final long REGULARMEMBERS = 1024 * 1024 * 20;//普通会员

    /**
     * 文件上传
     * @return
     */
    public String upload() {
        Netuser loginuser = (Netuser) session.get("user");
        if (loginuser != null) {
            Integer usertype = loginuser.getUsertype();
            long fileLength = FileUtil.calSize(file);
            Long capacity = (loginuser.getCapacity()) * 1024 * 1024;
            username = loginuser.getUsername();
            if (capacity - fileLength > 0) {
                //普通用户
                if (usertype == 1) {
                    if (fileLength > USERFILESIZE) {
                        throw new RuntimeException("文件超出上传范围，请充会员");
                    } else {
                        if (capacity > 0) {
                            fileUpload();
                            uploadManager(fileLength, capacity);
                        } else {
                            throw new RuntimeException("空间不足");
                        }

                    }
                } else if (usertype == 2) {
                    //普通会员
                    if (fileLength > REGULARMEMBERS) {
                        throw new RuntimeException("文件超出上传范围，请充超级会员");
                    } else {
                        fileUpload();
                        uploadManager(fileLength, capacity);
                    }
                } else if (usertype == 3) {
                    fileUpload();
                    uploadManager(fileLength, capacity);
                }
                return SUCCESS;
            } else {
                throw new RuntimeException("内存剩余空间不足");
            }
        } else {
            request.put("message", "未登录");
            return INPUT;
        }

    }

    /**
     * 上传逻辑处理，将新的用户放入到session
     * @param fileLength
     * @param capacity
     */
    private void uploadManager(long fileLength, Long capacity) {
        Long CApa = (capacity - fileLength) / (1024 * 1024);
        fileDao.UpdateCapacity(CApa, username);
        Netuser netuser = fileDao.seleUser(username);
        session.put("user", netuser);
    }

    /**
     * 文件上传
     */
    private String fileUpload() {
        File dir = new File(Config.saveDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            for (int i = 0; i < file.length; i++) {
                File saveFile = new File(Config.saveDir, FileUtil.getUnqiueByName(fileFileName[i]));


                //filepath截取文件名字
                String absolutePath = saveFile.getAbsolutePath();
                String substring = absolutePath.substring(absolutePath.lastIndexOf("\\") + 1);
                //文件类型
                String fileType = fileFileName[i].substring(fileFileName[i].lastIndexOf(".") + 1);


                if (fileType.equals("docx") || fileType.equals("doc") || fileType.equals("md")
                        || fileType.equals("txt") || fileType.equals("pdf")|| fileType.equals("ppd")) {
                    stortname[i] = "document";
                } else if (fileType.equals("avi") || fileType.equals("mp4")) {
                    stortname[i] = "video";
                } else if (fileType.equals("jpg") || fileType.equals("png")) {
                    stortname[i] = "images";
                } else if (fileType.equals("mp3") || fileType.equals("qmc3")) {
                    stortname[i] = "music";
                } else {
                    stortname[i] = "other";
                }


                fileMd5 = FileUtil.getMD5(file[i]);
                Srcfile result = fileDao.fileSeleMd5(fileMd5);
                Filesort fileSort = fileDao.seleFileSort(fileType);
                if (result != null) {
                    System.out.println("实现秒传");

                    Filetbl filetb = new Filetbl();
                    filetb.setUsername(username);
                    filetb.setFilename(fileFileName[i]);
                    filetb.setUploadDate(new Timestamp(System.currentTimeMillis()));
                    filetb.setFilepath(substring);
                    filetb.setSortname(stortname[i]);
                    filetb.setSrcmd5(fileMd5);
                    filetb.setSortid(fileSort.getSortid());
                    fileDao.saveFile(null, null, filetb);
                } else {

                    FileUtils.copyFile(file[i], saveFile);
                    //入库
                    Srcfile srcfile = new Srcfile();
                    srcfile.setFilecode(fileMd5);
                    srcfile.setFilepath(substring);
                    srcfile.setUptime(new Timestamp(System.currentTimeMillis()));
                    srcfile.setAuthor(username);


                    Filetbl filetb = new Filetbl();
                    filetb.setUsername(username);
                    filetb.setFilename(fileFileName[i]);
                    filetb.setUploadDate(new Timestamp(System.currentTimeMillis()));
                    filetb.setFilepath(substring);
                    filetb.setSortname(stortname[i]);
                    filetb.setSrcmd5(fileMd5);


                    if (fileSort == null) {
                        Filesort filesort1 = new Filesort();
                        filesort1.setSortname(stortname[i]);
                        filesort1.setRemark(fileType);

                        filetb.setSrcfileBySrcmd5(srcfile);
                        fileDao.saveFile(filesort1, srcfile, filetb);
                    } else {
                        filetb.setSortid(fileSort.getSortid());
                        filetb.setSrcfileBySrcmd5(srcfile);
                        fileDao.saveFile(null, srcfile, filetb);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }


    /**
     * 根据用户名显示所用文件
     * @return
     */
    public String fileList() {
        Netuser user = (Netuser) session.get("user");
        List filetbls = fileDao.seleFieName(user.getUsername());
        request.put("filetbls", filetbls);
        return SUCCESS;
    }

    public String down() {
        return SUCCESS;
    }

    public InputStream getMyInput() {
        File file = new File(Config.saveDir, filename);
        if (file.exists() == false) {
            throw new RuntimeException("文件不存在！");
        }
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    public File[] getFile() {
        return file;
    }

    public void setFile(File[] file) {
        this.file = file;
    }

    public String[] getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String[] fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String[] getStortname() {
        return stortname;
    }

    public void setStortname(String[] stortname) {
        this.stortname = stortname;
    }

    public FileDao getFileDao() {
        return fileDao;
    }

    public void setFileDao(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFilename() {
        try {
            filename = URLEncoder.encode(filename, "utf-8");
            System.out.println(">>>>====" + filename);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }
}
