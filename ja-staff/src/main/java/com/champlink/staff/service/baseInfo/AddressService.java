package com.champlink.staff.service.baseInfo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.champlink.common.domain.staff.baseInfo.Address;
import com.champlink.staff.dao.baseInfo.AddressMapper;

/**
 * 通讯信息
 * 
 * @author natyu
 * @date 2018年7月5日 上午10:16:44
 *
 */
@Service
public class AddressService {

    @Autowired
    private AddressMapper addressMapper;

    /**
     * 添加
     * 
     * @author natyu
     * @date 2018年7月5日 上午10:12:23
     * @param stfAddress
     */
    public boolean addAddress(Address address) {
        Long rowId = address.getRowId();
        if (StringUtils.isEmpty(rowId)) {
            if (addressMapper.insertAddress(address) > 0) {
                return true;
            }
        } else {
            if (addressMapper.updateAddress(address) > 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * 物理删除
     * 
     * @author natyu
     * @date 2018年7月5日 上午10:12:14
     * @param staffId
     */
    public boolean delAddressListByStaffId(Long staffId) {
        if (addressMapper.delAddressListByStaffId(staffId) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 查询
     * 
     * @author natyu
     * @date 2018年7月5日 上午10:13:01
     * @param staffId
     * @return
     */
    public List<Address> getAddressList(Long staffId) {
        return addressMapper.queryAddressList(staffId);
    }

    public Address getByRowId(Long rowId) {
        return addressMapper.getByRowId(rowId);
    }
    
    public int delAllByStaffId(Long rowId) {
    	return addressMapper.delAllByStaffId(rowId);
    }

}
