package com.champlink.staff.dao.baseInfo;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.staff.baseInfo.Address;

@Mapper
public interface AddressMapper {

    public Integer insertAddress(Address address);

    public Integer updateAddress(Address address);

    public Integer delAddressListByStaffId(@Param("rowId") Long rowId);

    public List<Address> queryAddressList(@Param("staffId") Long staffId);

    public Address getByRowId(@Param("rowId") Long rowId);
    
    public int delAllByStaffId(@Param("staffId") Long staffId);
    
}