package com.champlink.staff.dao.baseInfo;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.staff.baseInfo.ContactEmergency;

@Mapper
public interface ContactEmergencyMapper {

    public Integer insertContactEmergency(ContactEmergency contactEmergency);

    public Integer updateContactEmergency(ContactEmergency contactEmergency);

    public Integer delContactEmergencyListByStaffId(@Param("rowId") Long rowId);

    public List<ContactEmergency> queryContactEmergencyList(@Param("staffId") Long staffId);

    public ContactEmergency getByRowId(@Param("rowId") Long rowId);
    
    public int delAllStaffId(@Param("staffId") Long staffId);
}