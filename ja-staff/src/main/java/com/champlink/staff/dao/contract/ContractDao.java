package com.champlink.staff.dao.contract;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.contract.Contract;
import com.github.pagehelper.Page;

@Mapper
public interface ContractDao {

    int add(Contract contract);

    int delById(@Param("rowId") Long rowId);

    int update(Contract contract);

    Long getVersionNuByStaffId(@Param("staffId") Long staffId);

    Contract getByStaffId(@Param("staffId") Long staffId);

    Page<Contract> pageList(Paginater paginater);

    List<Contract> getById(@Param("rowId") Long rowId);

    String getLastContractNo();

    List<Contract> getContractByStaffId(@Param("staffId") Long staffId);

    void updateRenewStauts(@Param("rowId") Long rowId, @Param("renewStatus") String renewStatus);

    List<Contract> allList();

    List<Contract> allListByState();

    void updateContractState(Contract contract);

    void updateContractStateByStaffId(Contract contract);

}