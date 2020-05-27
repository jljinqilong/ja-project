package com.champlink.org.controller.position;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.org.position.RefPositionGrade;
import com.champlink.org.service.position.RefPositionGradeService;

@RestController
@RequestMapping("/refpositiongrade")
public class RefPositionGradeController extends BaseController {

    @Autowired
    private RefPositionGradeService refPositionGradeService;

    @RequestMapping(value = "/getGradeType", produces = "text/json;charset=UTF-8")
    public String getByGradeType() {
        List<RefPositionGrade> list = refPositionGradeService.getByGradeType();
        return getSuccessJson(list);
    }

}
