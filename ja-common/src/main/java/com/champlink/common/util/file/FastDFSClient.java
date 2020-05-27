package com.champlink.common.util.file;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.champlink.common.util.context.ContextUtils;
import org.apache.commons.io.IOUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.champlink.common.util.exception.AppException;

public class FastDFSClient {

    private static Logger logger = LoggerFactory.getLogger(FastDFSClient.class);

    private static final String CONFIG_FILENAME = "fastdfs.conf";

    private static Set<StorageClient1> storageClientSet = new HashSet<StorageClient1>();

    public static String METADATA_FILENAME = "fileName";

    static {
        setConf(10);
    }

    /**
     * 获取文件元数据
     * 
     * @param fileId 文件ID
     * @return
     */
    public static Map<String, String> getFileMetadata(String fileId) {
        StorageClient1 client = getClient();
        try {
            NameValuePair[] metaList = client.get_metadata1(fileId);
            if (metaList != null) {
                HashMap<String, String> map = new HashMap<String, String>();
                for (NameValuePair metaItem : metaList) {
                    map.put(metaItem.getName(), metaItem.getValue());
                }
                return map;
            }
        } catch (Exception e) {
            logger.error("FastdfsClient getFileMetadata error!", e);
            AppException.create(10000); // 异常
        } finally {
            release(client);
        }
        return null;
    }

    /**
     * 上传文件
     * 
     * @param input 文件流
     * @param fileName 文件名
     * @return
     */
    public static String uploadFile(InputStream input, String fileName) {
        Map<String, String> metaList = new HashMap<String, String>();
        metaList.put(METADATA_FILENAME, fileName);
        return uploadFile(input, fileName, metaList);
    }

    /**
     * 上传文件
     * 
     * @param input 文件流
     * @param fileName 文件名
     * @param metaList 文件元数据
     * @return
     */
    public static String uploadFile(InputStream input, String fileName, Map<String, String> metaList) {
        StorageClient1 client = getClient();
        try {
            // 获取文件后缀名
            String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
            byte[] buff = IOUtils.toByteArray(input);
            NameValuePair[] nameValuePairs = null;
            if (metaList != null) {
                nameValuePairs = new NameValuePair[metaList.size()];
                int index = 0;
                for (Iterator<Map.Entry<String, String>> iterator = metaList.entrySet().iterator(); iterator.hasNext();) {
                    Map.Entry<String, String> entry = iterator.next();
                    String name = entry.getKey();
                    String value = entry.getValue();
                    nameValuePairs[index++] = new NameValuePair(name, value);
                }
            }
            return client.upload_file1(buff, ext, nameValuePairs);
        } catch (Exception e) {
            logger.error("FastdfsClient uploadFile error!", e);
            AppException.create(10000); // 异常
        } finally {
            release(client);
        }
        return null;
    }

    /**
     * 删除文件
     * 
     * @param fileId 文件ID
     * @return 删除失败返回-1，否则返回0
     */
    public static int deleteFile(String fileId) {
        StorageClient1 client = getClient();
        try {
            return client.delete_file1(fileId);
        } catch (Exception e) {
            logger.error("FastdfsClient delete error!", e);
            AppException.create(10000); // 异常
        } finally {
            release(client);
        }
        return -1;
    }

    public static InputStream getInputStream(String srcPath) {
        InputStream inputStream = null;
        StorageClient1 client = getClient();
        try {
            byte[] content = client.download_file1(srcPath);
            inputStream = IOUtils.toInputStream(new String(content, "ISO-8859-1"), "ISO-8859-1");
        } catch (Exception e) {
            logger.error("FastdfsClient download error!", e);
            AppException.create(10000); // 异常
        } finally {
            release(client);
        }
        return inputStream;
    }

    private static void release(StorageClient1 client) {
        if (client != null) {
            storageClientSet.add(client);
        }

    }

    private static StorageClient1 getClient() {
        StorageClient1 storageClient1 = null;
        try {
            storageClient1 = storageClientSet.iterator().next();
            storageClientSet.remove(storageClient1);
        } catch (Exception e) {
            if (storageClient1 != null) {
                storageClientSet.add(storageClient1);
            }
            logger.error("error", e);
            AppException.create(10000); // 异常
        }
        return storageClient1;
    }

    /**
     * 
     * @param conf
     */
    public static void setConf(int size) {
        try {
            //            boot 环境对于jar中的ini文件读取有问题，使用编码方式初始化
            //            String classPath = new File(FastDFSClient.class.getResource("/").getFile()).getCanonicalPath();
            //            String fdfsClientConfigFilePath = classPath + File.separator + CONFIG_FILENAME;
            //            System.err.println("fdfsClientConfigFilePath-->" + fdfsClientConfigFilePath);
            //            ClientGlobal.init(fdfsClientConfigFilePath);

            ClientGlobal.initByProperties(ContextUtils.getBean(FastDFSProperties.class).getProperty());
            for (int i = 0; i < size; i++) {
                TrackerClient trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
                TrackerServer trackerServer = trackerClient.getConnection();
                if (trackerServer == null) {
                    throw new IllegalStateException("getConnection return null");
                }
                StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
                if (storageServer == null) {
                    throw new IllegalStateException("getStoreStorage return null");
                }
                StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);
                storageClientSet.add(storageClient1);
            }
        } catch (Exception e) {
            AppException.create(10000); // 异常
            logger.error("error", e);
        }
    }

}
