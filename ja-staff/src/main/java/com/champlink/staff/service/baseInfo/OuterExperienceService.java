package com.champlink.staff.service.baseInfo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.champlink.common.domain.staff.baseInfo.OuterExperience;
import com.champlink.staff.dao.baseInfo.OuterExperienceMapper;

/**
 * 
 * @author natyu
 * @date 2018年7月19日 上午11:04:55
 *
 */
@Service
public class OuterExperienceService {

    @Autowired
    private OuterExperienceMapper outerExperienceMapper;

    /**
     * 添加
     * 
     * @author natyu
     * @date 2018年7月5日 上午10:12:23
     * @param stfOuterExperience
     */
    public boolean addOuterExperience(OuterExperience outerExperience) {
        Long rowId = outerExperience.getRowId();
        if (StringUtils.isEmpty(rowId)) {
            if (outerExperienceMapper.insertOuterExperience(outerExperience) > 0) {
                return true;
            }
        } else {
            if (outerExperienceMapper.updateOuterExperience(outerExperience) > 0) {
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
    public boolean delOuterExperienceListByStaffId(Long staffId) {
        if (outerExperienceMapper.delOuterExperienceListByStaffId(staffId) > 0) {
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
    public List<OuterExperience> getOuterExperienceList(Long staffId) {
        return outerExperienceMapper.queryOuterExperienceList(staffId);
    }

    public OuterExperience getByRowId(Long rowId) {
        return outerExperienceMapper.getByRowId(rowId);
    }

    public int delAllByStaffId(Long staffId) {
        return outerExperienceMapper.delAllByStaffId(staffId);
    }
    
}
