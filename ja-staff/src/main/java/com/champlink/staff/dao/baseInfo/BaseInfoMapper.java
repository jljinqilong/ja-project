package com.champlink.staff.dao.baseInfo;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.BaseSelect;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.baseInfo.BaseInfo;
import com.champlink.common.form.org.org.QueryCountBaseInfoByDeptId;
import com.champlink.common.form.staff.baseInfo.SearchBaseInfoForm;
import com.github.pagehelper.Page;

@Mapper
public interface BaseInfoMapper {

    public Integer insertBaseInfo(BaseInfo baseInfo);

    public Integer insertBaseInfoList(@Param("list") List<BaseInfo> list);

    public BaseInfo getBaseInfoByStaffId(@Param("staffId") Long staffId, @Param("delFlag") Integer delFlag);

    public Integer logicDelBaseInfoByStaffId(@Param("staffId") Long staffId, @Param("delFlag") Integer delFlag);

    public Integer updateBaseInfo(BaseInfo baseInfo);

    public Page<BaseInfo> queryBaseInfoList(Paginater pager);

    public List<BaseInfo> queryBaseInfoForParams(SearchBaseInfoForm form);

    public List<BaseInfo> queryExportExcelInfo(SearchBaseInfoForm form);

    public Integer queryBaseInfo(@Param("deptId") Long deptId, @Param("isOnJobId") Long isOnJobId);

    public Integer checkStaffNo(@Param("staffNo") String staffNo, @Param("delFlag") Integer delFlag);

    public Integer checkIdentityNo(@Param("identityNo") String identityNo, @Param("delFlag") Integer delFlag);

    public Integer checkIdentityNoByJob(@Param("identityNo") String identityNo, @Param("delFlag") Integer delFlag, @Param("jobStatus") Long jobStatus, @Param("rowId") Long rowId);

    public Integer checkMobileByJob(@Param("mobile") String mobile, @Param("delFlag") Integer delFlag, @Param("jobStatus") Long jobStatus, @Param("rowId") Long rowId);

    public Integer checkEmailByJob(@Param("email") String email, @Param("delFlag") Integer delFlag, @Param("jobStatus") Long jobStatus, @Param("rowId") Long rowId);

    public Integer update(BaseInfo baseInfo);

    public Integer updateLeaveOffice(@Param("ids") String[] ids, @Param("isBlacklist") Long isBlacklist, @Param("leaveDate") Date leaveDate, @Param("isOnJob") Long isOnJob);

    public List<BaseInfo> getBaseInfoByIds(@Param("list") List<Long> staffIds);

    public int delDept_ids(@Param("list") List<Long> dept_Ids);

    public int orgMerge(@Param("sourceId") Long sourceId, @Param("targetId") Long targetId);

    public int queryByDeptId(Long deptId);

    public int getCountByIdNo(@Param("idNo") String idNo);

    public BaseInfo getBaseInfoByStaffNo(@Param("staffNo") String staffNo, @Param("delFlag") Integer delFlag);

    public Integer queryCountBaseInfoByPositionId(@Param("positionId") Long positionId);

    public Integer checkHasLeave(@Param("ids") String[] ids, @Param("isOnJobId") Long isOnJobId);

    public List<QueryCountBaseInfoByDeptId> queryCountBaseInfoByDeptId(@Param("isOnJobId") Long isOnJobId);

    BaseInfo getBaseInfo(String userName);

    List<BaseSelect> getSalePersonList();

    BaseInfo getUserNameJobNumberById(Long id);
    
    public int queryByBaseId(@Param("baseId") Long baseId);

    List<Long> getStaffIdByOrgIds(@Param("orgIds") List<Long> orgIds);
    
    public List<String> getStaffNoBystaffIds(@Param("ids") List<Long> ids);
}