package com.champlink.system.dao;

import org.apache.ibatis.annotations.Mapper;
import com.champlink.common.form.file.UploadFileForm;

@Mapper
public interface UploadFileMapper {

    void addUploadFile(UploadFileForm form);

    void deleteFile(String name);

    UploadFileForm getFileInfo(String name);

}
