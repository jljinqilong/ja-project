package com.champlink.staff.dao.baseInfo;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.staff.baseInfo.AdjustmentWork;
import com.champlink.common.form.staff.baseInfo.ImportAdjustmentWork;
import com.github.pagehelper.Page;

@Mapper
public interface AdjustmentWorkMapper {

    public Integer insertAdjustmentWork(@Param("ids") String[] ids, @Param("work") AdjustmentWork work);

    public Integer updateAdjustmentWork(AdjustmentWork work);

    public Integer delAdjustmentWorkListByStaffId(@Param("rowId") Long rowId);

    public List<AdjustmentWork> queryAdjustmentWorkList(@Param("staffId") Long staffId, @Param("changeType") String changeType);

    public List<AdjustmentWork> getAdjustmentBy(@Param("staffId") Long staffId, @Param("changeType") String changeType);

    public AdjustmentWork getByRowId(@Param("rowId") Long rowId);

    public boolean addStaffMove(AdjustmentWork adjustmentWork);

    public Page<AdjustmentWork> pageList(Paginater paginater);

    public int delAllStaffId(@Param("staffId") Long staffId);

    public List<AdjustmentWork> allList();

    public void updateStatus(@Param("rowId") Long rowId);

    public Integer insertStaffMove(@Param("list") List<ImportAdjustmentWork> list);

}