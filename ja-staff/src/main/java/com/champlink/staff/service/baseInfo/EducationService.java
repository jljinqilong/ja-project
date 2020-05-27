package com.champlink.staff.service.baseInfo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.champlink.common.domain.staff.baseInfo.Education;
import com.champlink.staff.dao.baseInfo.EducationMapper;

/**
 * 
 * @author natyu
 * @date 2018年7月19日 上午11:04:48
 *
 */
@Service
public class EducationService {

    @Autowired
    private EducationMapper educationMapper;

    /**
     * 添加
     * 
     * @author natyu
     * @date 2018年7月5日 上午10:12:23
     * @param stfEducation
     */
    public boolean addEducation(Education education) {
        Long rowId = education.getRowId();
        if (StringUtils.isEmpty(rowId)) {
            if (educationMapper.insertEducation(education) > 0) {
                return true;
            }
        } else {
            if (educationMapper.updateEducation(education) > 0) {
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
    public boolean delEducationListByStaffId(Long staffId) {
        if (educationMapper.delEducationListByStaffId(staffId) > 0) {
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
    public List<Education> getEducationList(Long staffId) {
        return educationMapper.queryEducationList(staffId);
    }

    public Education getByRowId(Long rowId) {
        return educationMapper.getByRowId(rowId);
    }

    public int delAllByStaffId(Long staffId) {
    	return educationMapper.delAllByStaffId(staffId);
    }
}
