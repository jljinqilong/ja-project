package com.champlink.org.controller.position;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.org.position.Rank;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.org.position.RankForm;
import com.champlink.common.util.method.MethodUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.org.service.call.StaffService;
import com.champlink.org.service.position.RankService;

@RestController
@RequestMapping("/rank")
public class RankController extends BaseController {

    @Autowired
    private RankService rankService;

    @Autowired
    private StaffService staffService;

    /**
     * 新增
     * 
     * @param form
     * @return
     */
    @RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
    public String add(Rank rank) {
        //设置登陆人信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);

        rank.setCreatedBy(createdBy);
        rank.setCreatedTime(new Date());
        rankService.add(rank);
        return getSuccessJson();
    }

    /**
     * 删除
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id}", produces = "text/json;charset=UTF-8")
    public String del(@PathVariable("id") Long id) {
        rankService.del(id);
        return getSuccessJson();
    }

    /**
     * 修改
     * 
     * @param AddEditOrgForm
     * @return
     */
    @RequestMapping(value = "/update", produces = "text/json;charset=UTF-8")
    public String update(Rank rank) {
        //设置登陆人信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);
        rank.setLastUpdateBy(createdBy);
        rank.setLastUpdateTime(new Date());
        if (rankService.update(rank)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 列表
     * 
     * @param userForm
     * @return
     */
    @RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
    public String pageList(RankForm rankForm) {
        //    	List<Long> staffIdList = RequestContext.get().getRoleAuthority();
        RequestContext requestContext = RequestContext.get();
        List<Long> staffIdList = requestContext.getRoleAuthorityStaffIdList();
        rankForm.setStaffIdList(staffIdList);

        if (rankForm.getRankName() != null) {
            rankForm.setRankName(rankForm.getRankName().trim());
        }
        PageListVO pageListVO = rankService.pageList(rankForm);
        return getSuccessJson(pageListVO);
    }

    @RequestMapping(value = "/get/{id}", produces = "text/json;charset=UTF-8")
    public String findOne(@PathVariable("id") Long id) {
        Rank rank = rankService.findOne(id);
        return getSuccessJson(rank);
    }

    @RequestMapping(value = "/getRankType", produces = "text/json;charset=UTF-8")
    public String getByRankType() {
        List<Rank> list = rankService.getByRankType();
        return getSuccessJson(list);
    }

    /**
     * 根据多个职等/赋值名称，查询出多个值等所对应的多个职级
     * @param positionGrade
     * @return
     */
    @RequestMapping(value = "/getRankByGrade", produces = "text/json;charset=UTF-8")
    public String getRankByGrade(@RequestParam("positionGrade") String positionGrade) { //@RequestParam("positionGrade") 
        String[] split = positionGrade.split(",");
        List<Long> positionGrades = new ArrayList<Long>();
        for (String str : split) {
            positionGrades.add(Long.valueOf(str));
        }
        if (!positionGrades.isEmpty()) {
            List<Rank> ranks = rankService.getRankByGrade(positionGrades);
            return getSuccessJson(ranks);
        }
        return getSuccessJson();
    }

    /**
     * 根据所有的职级
     * @param positionGrade
     * @return
     */
    @RequestMapping(value = "/getAllRank", produces = "text/json;charset=UTF-8")
    public String getAllRank() {
        List<Rank> allRank = rankService.getAllRank();
        return getSuccessJson(allRank);
    }

}
