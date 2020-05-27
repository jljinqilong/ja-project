package com.champlink.org.controller.position;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.org.position.RefGradeRank;
import com.champlink.org.service.position.RefGradeRankService;

@RestController
@RequestMapping("/refgraderank")
public class RefGradeRankController extends BaseController {

    @Autowired
    private RefGradeRankService refGradeRankService;

    @RequestMapping(value = "/getRankType", produces = "text/json;charset=UTF-8")
    public String getByRankType() {
        List<RefGradeRank> list = refGradeRankService.getByRankType();
        return getSuccessJson(list);
    }

}
