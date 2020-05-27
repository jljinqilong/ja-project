package com.champlink.staff.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.champlink.common.domain.staff.baseInfo.AdjustmentWork;
import com.champlink.common.domain.staff.baseInfo.BaseInfo;
import com.champlink.staff.dao.baseInfo.AdjustmentWorkMapper;
import com.champlink.staff.dao.baseInfo.BaseInfoMapper;
import com.champlink.staff.service.call.SystemService;

@Component
public class AdjustmentWorkTimingService {

    @Autowired
    private BaseInfoMapper baseInfoMapper;

    @Autowired
    private AdjustmentWorkMapper adjustmentWorkMapper;

    @Autowired
    private SystemService systemService;

    //  每分钟启动
    //    @Scheduled(cron = "0 0/1 * * * ?")
    //    public void timerToNow() {
    //        System.err.println("\r\n\r\n" + "now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\r\n\r\n");
    //    }

    /**
     * 定时更新所有已到期借调外派信息恢复
     * 
     * @param
     * @return
     */
    @Scheduled(cron = "0 01 00 * * ?")
    public void updateAdjustmentWork() {
        List<AdjustmentWork> allList = adjustmentWorkMapper.allList();
        Date date = new Date();
        System.err.println("========================" + date);
        for (AdjustmentWork a : allList) {
            if (a.getRealEndTime() != null) {
                if (date.before(a.getRealEndTime())) {
                    BaseInfo baseInfo = new BaseInfo();
                    baseInfo.setStaffNo(a.getOriginalStaffNo());
                    baseInfo.setRowId(a.getStaffId());
                    baseInfoMapper.updateBaseInfo(baseInfo);
                    systemService.updateUserName(a.getNewStaffNo(), a.getOriginalStaffNo());
                    adjustmentWorkMapper.updateStatus(a.getRowId());
                }
            } else {
                if (a.getChangeType().equals("TEMPORARILY")) {
                    if (date.before(a.getTemporarilyEndDate())) {
                        BaseInfo baseInfo = new BaseInfo();
                        baseInfo.setStaffNo(a.getOriginalStaffNo());
                        baseInfo.setRowId(a.getStaffId());
                        baseInfoMapper.updateBaseInfo(baseInfo);
                        systemService.updateUserName(a.getNewStaffNo(), a.getOriginalStaffNo());
                        adjustmentWorkMapper.updateStatus(a.getRowId());
                    }
                }
                if (a.getChangeType().equals("EXPATRIATE")) {
                    if (date.before(a.getExpatriateEndDate())) {
                        BaseInfo baseInfo = new BaseInfo();
                        baseInfo.setStaffNo(a.getOriginalStaffNo());
                        baseInfo.setRowId(a.getStaffId());
                        baseInfoMapper.updateBaseInfo(baseInfo);
                        systemService.updateUserName(a.getNewStaffNo(), a.getOriginalStaffNo());
                        adjustmentWorkMapper.updateStatus(a.getRowId());
                    }
                }
            }
        }
    }

}
