package com.champlink.sale.controller.area;

import com.alibaba.fastjson.JSONObject;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.sale.area.Area;
import com.champlink.common.domain.sale.area.AreaDetail;
import com.champlink.common.domain.system.Code;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.area.AreaForm;
import com.champlink.common.form.area.ImportAreaForm;
import com.champlink.common.service.call.StaffServiceFacade;
import com.champlink.common.util.cache.RedisService;
import com.champlink.common.util.excel.ImportExcelUtils;
import com.champlink.common.util.file.FileClient;
import com.champlink.common.util.method.MethodUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.sale.service.area.AreaService;
import com.champlink.sale.service.call.SaleCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/area")
@RestController
public class AreaController extends BaseController {

    @Autowired
    private AreaService areaService;
    @Autowired
    private SaleCommonService saleCommonService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private StaffServiceFacade staffServiceFacade;
    /**
     * jsl 2018-07-30
     * 区域查询
     */
    @RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
    public String searchAreaList(AreaForm form) {
        PageListVO<Area> areaList = areaService.searchAreaList(form);
        //查询所有字典项
        String allCode = saleCommonService.getAllCode();
        for(Area area : areaList.getList()){
            //区域的地区集合
            List<AreaDetail> detailList = area.getAreaDetailList();
            if(detailList.size()>0) {
                if (allCode != null) {
                    JSONObject parseObject = JSONObject.parseObject(allCode);
                    if ((Integer) parseObject.get("code") == 200) {
                        List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                        detailList = super.translateIdToName(detailList, allCodeList, new String[]{"continentId,rowId,name",
                                "countryId,rowId,name",
                                "zCurrencyId,rowId,name",
                                "fCurrencyId,rowId,name"});
                    }
                }
                area.setAreaDetailList(detailList);
                //拼接国家名称
                String countryNames = "";
                for (AreaDetail areaDetail : detailList) {
                    countryNames += (String) areaDetail.getTransNames().get("countryId_name") + ",";
                }
                area.setCountryNames(countryNames.substring(0, countryNames.length() - 1));
            }
        }
     return getSuccessJson(areaList);
    }

    /**
     * jsl 2018-08-03
     * 增加区域
     * @return
     */
    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    public String addRegion(@RequestBody Area area){
        area.setCreatedBy(RequestContext.get().getStaffId());
        if (areaService.addArea(area)){
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 查询区域信息
     * @param rowId
     * @return
     */
    @RequestMapping(value = "/get/{id}", produces = "application/json;charset=UTF-8")
    public String getById(@PathVariable("id") Long rowId) {
        Area area = areaService.getById(rowId);
        return getSuccessJson(area);
    }


    /**
     * 修改
     * @param area
     * @return
     */
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public String update(@RequestBody Area area) {
        if (areaService.update(area)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 删除
     * @param rowId
     * @return
     */
    @PostMapping(value = "/del/{id}", produces = "application/json;charset=UTF-8")
    public String del(@PathVariable("id") Long rowId) {
        areaService.delById(rowId);
        return getSuccessJson();
    }


    /**
     * @Author jsl
     * @Date 查询所有的区域 2018/8/8
     * @Description
     **/
    @GetMapping(value = "/getAllArea", produces = "application/json;charset=UTF-8")
    public String getAllArea(){
        List<Area> areaList = areaService.getAllArea();
        return getSuccessJson(areaList);
    }


    /**
     * @Author jsl
     * @Date 区域明细查询 2018/8/16
     * @Description
     **/
    @RequestMapping(value = "/detailList", produces = "text/json;charset=UTF-8")
    public String searchDetailList(AreaForm form) {
        PageListVO<AreaDetail> pageListVO = areaService.searchAreaDetailList(form);
        List<AreaDetail> areaDetailList = pageListVO.getList();
        //查询所有字典项
        String allCode = saleCommonService.getAllCode();

        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                areaDetailList = super.translateIdToName(areaDetailList, allCodeList, new String[]{
                        "continentId,rowId,name",
                        "countryId,rowId,name",
                        "zCurrencyId,rowId,name"});
            }
            pageListVO.setList(areaDetailList);
        }

        return getSuccessJson(pageListVO);
    }


    /**
     *  导入区域信息
     * @param request
     * @param response
     */
    @PostMapping(value = "/importExcel", produces = "application/json;charset=UTF-8")
    public String importExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //1.构建Excel接受的实体类
        List<ImportAreaForm> list = new ArrayList<ImportAreaForm>();

        //2.将Excel转成JavaBean ImportAreaForm
        ImportExcelUtils.export(request, ImportAreaForm.class, list);


        //获取当前登陆人的信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);
        String baseInfo = staffServiceFacade.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);
//          Long createdBy =118L;
        areaService.importExcelArea(createdBy,list);
        return getSuccessJson();
    }


    /**
     * @Author jsl
     * @Date 19:06 2018/8/21
     * @Description    下载模板
     **/
    @RequestMapping(value = "/download", produces = "application/json;charset=UTF-8")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filePath = request.getParameter("filePath").split("&")[0];
        FileClient.downloadFile(filePath, response);
    }


}

