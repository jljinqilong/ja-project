package com.champlink.staff.service.baseInfo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.champlink.common.domain.staff.baseInfo.ContactEmergency;
import com.champlink.staff.dao.baseInfo.ContactEmergencyMapper;

/**
 * 
 * 教育经历
 * 
 * @author natyu
 * @date 2018年7月5日 上午10:24:57
 *
 */
@Service
public class ContactEmergencyService {

    @Autowired
    private ContactEmergencyMapper contactEmergencyMapper;

    /**
     * 添加
     * 
     * @author natyu
     * @date 2018年7月5日 上午10:12:23
     * @param stfContactEmergency
     */
    public boolean addContactEmergency(ContactEmergency contactEmergency) {
        Long rowId = contactEmergency.getRowId();
        if (StringUtils.isEmpty(rowId)) {
            if (contactEmergencyMapper.insertContactEmergency(contactEmergency) > 0) {
                return true;
            }
        } else {
            if (contactEmergencyMapper.updateContactEmergency(contactEmergency) > 0) {
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
    public boolean delContactEmergencyListByStaffId(Long staffId) {
        if (contactEmergencyMapper.delContactEmergencyListByStaffId(staffId) > 0) {
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
    public List<ContactEmergency> getContactEmergencyList(Long staffId) {
        return contactEmergencyMapper.queryContactEmergencyList(staffId);
    }

    public ContactEmergency getByRowId(Long rowId) {
        return contactEmergencyMapper.getByRowId(rowId);
    }
    
    public int delAllStaffId(Long staffId) {
        return 	contactEmergencyMapper.delAllStaffId(staffId);
    }
}
