package com.champlink.staff.service.baseInfo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.champlink.common.domain.staff.baseInfo.RelationshipSocial;
import com.champlink.staff.dao.baseInfo.RelationshipSocialMapper;

/**
 * 
 * @author natyu
 * @date 2018年7月19日 上午11:05:04
 *
 */
@Service
public class RelationshipSocialService {

    @Autowired
    private RelationshipSocialMapper relationshipSocialMapper;

    /**
     * 添加
     * 
     * @author natyu
     * @date 2018年7月5日 上午10:12:23
     * @param stfRelationshipInner
     */
    public boolean addRelationshipSocial(RelationshipSocial relationshipSocial) {
        Long rowId = relationshipSocial.getRowId();
        if (StringUtils.isEmpty(rowId)) {
            if (relationshipSocialMapper.insertRelationshipSocial(relationshipSocial) > 0) {
                return true;
            }
        } else {
            if (relationshipSocialMapper.updateRelationshipSocial(relationshipSocial) > 0) {
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
    public boolean delRelationshipSocialListByStaffId(Long staffId) {
        if (relationshipSocialMapper.delRelationshipSocialListByStaffId(staffId) > 0) {
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
    public List<RelationshipSocial> getRelationshipSocialList(Long staffId) {
        return relationshipSocialMapper.queryRelationshipSocialList(staffId);
    }

    public RelationshipSocial getByRowId(Long rowId) {
        return relationshipSocialMapper.getByRowId(rowId);
    }

    public int delAllByStaffId(Long staffId){
    	return relationshipSocialMapper.delAllByStaffId(staffId);
    }
    
}
