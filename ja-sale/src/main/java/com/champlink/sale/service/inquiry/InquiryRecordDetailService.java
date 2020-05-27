package com.champlink.sale.service.inquiry;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.inquiry.InquiryRecordDetail;
import com.champlink.common.domain.staff.baseInfo.BaseInfo;
import com.champlink.common.form.sale.inquiry.InquiryRecordDetailForm;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.util.method.MethodUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.sale.dao.inquiry.InquiryRecordDetailDao;
import com.champlink.sale.service.call.OrgService;
import com.champlink.sale.service.call.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * 多条询价明细对应一条询价记录
 * @Author created by barrett in 18-8-9 下午2:30
 */
@Service
public class InquiryRecordDetailService {

    private static Logger logger = LoggerFactory.getLogger(InquiryRecordDetailService.class);

    @Autowired
    private InquiryRecordDetailDao inquiryRecordDetailDao;
    @Autowired
    private OrgService orgService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private InquiryRecordService inquiryRecordService;

    /**
     * 分页查询
     * @Author created by barrett in 18-8-7 下午5:13
     * @Param
     * @return
     */
    public PageListVO<InquiryRecordDetail> pageList(InquiryRecordDetailForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO<InquiryRecordDetail> pageListVO = PageListVO.newInstance(inquiryRecordDetailDao.pageList(paginater));

        return pageListVO;
    }

    /**
     * 添加询价记录
     * @Author created by barrett in 18-8-6 下午6:04
     */
    public boolean add(InquiryRecordDetail record) {
        record.setDelFlag(0);
        record.setCreatedBy(inquiryRecordService.getLoginId());
        if (inquiryRecordDetailDao.insert(record) > 0) {
            return true;
        }
        return false;
    }


    /**
     * 更新
     * @Author created by barrett in 18-8-6 下午9:06
     */
    public boolean update(InquiryRecordDetail record) {
        record.setLastUpdateBy(inquiryRecordService.getLoginId());
        if (inquiryRecordDetailDao.update(record) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 查询询价信息
     * @Author created by barrett in 18-8-7 上午9:28
     * @Param
     * @return
     */
    public List<InquiryRecordDetail> getInquiryRecord(Long rowId) {
        List<InquiryRecordDetail> list = inquiryRecordDetailDao.selectInquiryRecordDetailByRowId(rowId);

        for (InquiryRecordDetail inquiryRecordDetail : list) {
            try {
                //查询用户名称
                String userInfo = staffService.getUserInfo(inquiryRecordDetail.getCreatedBy());
                BaseInfo user = (BaseInfo) MethodUtil.getObject(BaseInfo.class.getName(), userInfo);
                inquiryRecordDetail.setCreateName(user.getStaffName());
            }catch (Exception e){
                AppException.create(100004);
            }
        }

        return list;
    }

    /**
     * （逻辑删除）询价记录
     * @Author created by barrett in 18-8-7 下午4:44
     * @Param
     * @return
     */
    @Transactional
    public boolean deleteByIds(String ids) {
        if (org.apache.commons.lang.StringUtils.isNotBlank(ids)) {
            String[] idArr = ids.split(",");
            inquiryRecordDetailDao.updateDelFlag(idArr,inquiryRecordService.getLoginId());
        }
        return false;
    }
}
