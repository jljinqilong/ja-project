package com.champlink.staff.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.champlink.common.domain.staff.contract.Agreement;
import com.champlink.common.domain.staff.contract.Contract;
import com.champlink.staff.dao.contract.AgreementDao;
import com.champlink.staff.dao.contract.ContractDao;

@Component
public class ContractTimingService {

    @Autowired
    private ContractDao contractDao;
    @Autowired
    private AgreementDao agreementDao;

    //  每分钟启动
    //    @Scheduled(cron = "0 0/1 * * * ?")
    //    public void timerToNow() {
    //        System.err.println("\r\n\r\n" + "now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\r\n\r\n");
    //    }

    /**
     * 定时更新合同状态
     * 
     * @param
     * @return
     */
    @Scheduled(cron = "0 01 00 * * ?")
    public void updateContractState() {
        List<Contract> allList = contractDao.allListByState();
        Date date = new Date();
        System.err.println("========================" + date);
        for (Contract contract : allList) {
            //判断合同状态
            if (date.before(contract.getContractDateStart())) {
                contract.setContractState("1");
            } else if (date.before(contract.getContractDateEnd()) && date.after(contract.getContractDateStart())) {
                contract.setContractState("2");
            } else if (date.after(contract.getContractDateEnd())) {
                contract.setContractState("3");
            }
            contractDao.updateContractState(contract);
        }
    }

    /**
     * 定时更新协议合同状态
     * 
     * @param
     * @return
     */
    @Scheduled(cron = "0 01 00 * * ?")
    public void updateAgreementState() {
        List<Agreement> allList = agreementDao.allListByState();
        Date date = new Date();
        System.err.println("========================" + date);
        for (Agreement agreement : allList) {
            //判断合同状态
            if (date.before(agreement.getAgreementDateStart())) {
                agreement.setAgreementState("1");
            } else if (date.before(agreement.getAgreementDateEnd()) && date.after(agreement.getAgreementDateStart())) {
                agreement.setAgreementState("2");
            } else if (date.after(agreement.getAgreementDateEnd())) {
                agreement.setAgreementState("3");
            }
            agreementDao.updateAgreementState(agreement);
        }
    }

}
