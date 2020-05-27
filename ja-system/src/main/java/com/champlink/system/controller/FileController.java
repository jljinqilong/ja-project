package com.champlink.system.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.champlink.common.controller.BaseController;
import com.champlink.common.form.file.UploadFileForm;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.util.file.FastDFSClient;
import com.champlink.system.dao.UploadFileMapper;

/**
 * 文件上传下载
 * 
 * @author natyu
 * @date 2018年7月14日 下午4:15:38
 *
 */
@Controller
@RequestMapping("/file")
public class FileController extends BaseController {

    @Autowired
    private UploadFileMapper uploadFileMapper;

    /**
     * 
     * 多文件上传
     * 
     * @author sensenfeng
     * @date Sep 4, 2014 3:11:28 PM
     * @param myfiles
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/upload/multi", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("files") MultipartFile[] myFiles) throws Exception {

        if (myFiles.length > 8) {
            return getFailJson("最多批量上传8个文件");
        }
        List<String> fileNameList = new ArrayList<String>();
        for (MultipartFile myFile : myFiles) {
            if (myFile.isEmpty()) {
                return getFailJson("文件上传失败");
            }
            // 生成唯一的文件名
            String newFileName = UUID.randomUUID().toString().replaceAll("-", "");

            // 截取文件扩展名
            String originalFilename = myFile.getOriginalFilename();
            String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
            String path = FastDFSClient.uploadFile(myFile.getInputStream(), originalFilename);
            System.out.println("path:" + path);
            UploadFileForm form = new UploadFileForm();
            form.setNewName(path);
            form.setOriginName(myFile.getOriginalFilename());
            uploadFileMapper.addUploadFile(form);

            // logger.info("文件上传成功：文件长度=" + myFile.getSize() + ",文件原名=" +
            // myFile.getOriginalFilename() + ",新文件名=" + path);

            fileNameList.add(path);
        }
        return getSuccessJson(fileNameList);
    }

    /**
     * 
     * 单文件上传
     * 
     * @author sensenfeng
     * @date Sep 4, 2014 3:11:28 PM
     * @param myFile
     * @return 存储后的文件名
     * @throws Exception
     */
    @RequestMapping(value = "/upload/single", method = RequestMethod.POST)
    @ResponseBody
    public String singleUpload(@RequestParam("file") MultipartFile myFile) throws Exception {
        if (myFile.isEmpty()) {
            return getFailJson("文件上传失败");
        }
        String originalFilename = myFile.getOriginalFilename();
        String path = FastDFSClient.uploadFile(myFile.getInputStream(), originalFilename);
        UploadFileForm form = new UploadFileForm();
        form.setNewName(path);
        form.setOriginName(myFile.getOriginalFilename());
        uploadFileMapper.addUploadFile(form);
        return getSuccessJson(path);
    }

    /**
     * 
     * 删除文件
     * 
     * @author sensenfeng
     * @date Sep 9, 2014 3:24:53 PM
     * @param filePath 文件路径
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delFile", method = RequestMethod.DELETE)
    @ResponseBody
    public String delUploadFile(String filePath) throws Exception {
        if (StringUtils.isEmpty(filePath)) {
            return getFailJson("文件名称不能为空");
        } else {
            filePath = new String(filePath.getBytes("ISO8859-1"), "UTF-8");
        }
        // FastDFSClient.deleteFile();
        // delete文件历史表
        uploadFileMapper.deleteFile(filePath.split("/")[1]);
        // 直接根据物理地址全等删除
        uploadFileMapper.deleteFile(filePath);
        // logger.info("文件删除成功，文件名：" + filePath);
        return getSuccessJson();
    }

    /**
     * 
     * 文件下载
     * 
     * @author sensenfeng
     * @date Sep 5, 2014 11:06:12 AM
     * @param filePath 文件路径
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ModelAndView download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String filePath = request.getParameter("filePath");
        if (StringUtils.isEmpty(filePath)) {
            AppException.create(10005); // 文件路径不能为空
        } else {
            filePath = new String(filePath.getBytes("ISO8859-1"), "UTF-8");
        }
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        String fileName = null;

        try {

            if (filePath.startsWith("group")) {// fastdfs 起始
                Map<String, String> metaData = FastDFSClient.getFileMetadata(filePath);
                fileName = metaData.get(FastDFSClient.METADATA_FILENAME);
            } else if (filePath.contains(",")) {
                String[] strArr = filePath.split(",");
                fileName = strArr[1] + "." + strArr[0].split("\\.")[1];
                filePath = strArr[0];
            } else if (filePath.contains("buffer")) {
                fileName = uploadFileMapper.getFileInfo(filePath.split("/")[1]).getOriginName();
                fileName = URLEncoder.encode(fileName, "utf-8");
            } else {
                // 截取文件名
                fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
            }

            bis = new BufferedInputStream(FastDFSClient.getInputStream(filePath));

            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));

            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            AppException.create(10004); // 文件不存在
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
        return null;
    }

}
