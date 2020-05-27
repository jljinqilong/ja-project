package com.champlink.common.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletResponse;

public class FileClient {

    /**
     * 获取文件流
     * 
     * @param filePath
     * @return
     * @throws Exception
     */
    public static InputStream getInputStream(String filePath) throws Exception {
        return FileClient.class.getClassLoader().getResourceAsStream("excelTemplate/" + filePath);
    }

    /**
     * 下载文件
     * 
     * @param filePath
     * @param response
     * @throws Exception
     */
    public static void downloadFile(String filePath, HttpServletResponse response) throws Exception {
        response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length()), "UTF-8"));
        response.setContentType("application/x-msdownload;");
        try (BufferedInputStream bis = new BufferedInputStream(getInputStream(filePath)); BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())) {

            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
