package com.champlink.staff.service.baseInfo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.champlink.common.domain.staff.baseInfo.RelationshipInner;
import com.champlink.staff.dao.baseInfo.RelationshipInnerMapper;

/**
 * 
 * @author natyu
 * @date 2018年7月19日 上午11:05:00
 *
 */
@Service
public class RelationshipInnerService {

    @Autowired
    private RelationshipInnerMapper relationshipInnerMapper;

    /**
     * 添加
     * 
     * @author natyu
     * @date 2018年7月5日 上午10:12:23
     * @param stfRelationshipInner
     */
    public boolean addRelationshipInner(RelationshipInner relationshipInner) {
        Long rowId = relationshipInner.getRowId();
        if (StringUtils.isEmpty(rowId)) {
            if (relationshipInnerMapper.insertRelationshipInner(relationshipInner) > 0) {
                return true;
            }
        } else {
            if (relationshipInnerMapper.updateRelationshipInner(relationshipInner) > 0) {
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
    public boolean delRelationshipInnerListByStaffId(Long staffId) {
        if (relationshipInnerMapper.delRelationshipInnerListByStaffId(staffId) > 0) {
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
    public List<RelationshipInner> getRelationshipInnerList(Long staffId) {
        return relationshipInnerMapper.queryRelationshipInnerList(staffId);
    }

    public RelationshipInner getByRowId(Long rowId) {
        return relationshipInnerMapper.getByRowId(rowId);
    }
    
    public int delAllByStaffId(Long staffId) {
    	return relationshipInnerMapper.delAllByStaffId(staffId);
    }

}
