package com.champlink.staff.dao.contract;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.contract.Agreement;
import com.github.pagehelper.Page;

@Mapper
public interface AgreementDao {

    int add(Agreement agreement);

    int delById(@Param("rowId") Long rowId);

    int update(Agreement agreement);

    Page<Agreement> pageList(Paginater paginater);

    List<Agreement> getById(@Param("rowId") Long rowId);

    List<Agreement> getAgreementByStaffId(@Param("staffId") Long staffId);

    String getLastAgreementNo();

    List<Agreement> getAgreementByContract(@Param("contractNo") String contractNo);

    void updateRenewStauts(@Param("rowId") Long rowId, @Param("renewStatus") String renewStatus);

    List<Agreement> getByAgreementNo(@Param("agreementNo") String agreementNo);

    List<Agreement> allList();

    List<Agreement> allListByState();

    void updateAgreementState(Agreement agreement);

    void updateAgreementStateByStaffId(Agreement agreement);

    void updateAgreementStateByContractNo(Agreement agreement);

}