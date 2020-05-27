package com.champlink.sale.service.call;

import com.champlink.common.domain.system.Code;
import com.champlink.common.domain.system.ModuleLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient(value = "JA-SYSTEM", fallback = SaleCommonFallBackService.class)
public interface SaleCommonService {


        /**
         * 查询所有字典项
         *
         * @author natyu
         * @date 2018年7月25日 下午2:45:34
         * @return
         */
        @RequestMapping(value = "/code/getAllCode")
        public String getAllCode();

        /**
         * 查询字典列表
         *
         * @param typeCode
         * @return
         */
        @RequestMapping(value = "/code/getByTpye")
        public String getByTpye(@RequestParam("typeCode") String typeCode);

        /**
         * 变更记录
         *
         * @author natyu
         * @date 2018年7月25日 上午11:59:20
         * @param moduleLog
         * @return
         */
        @RequestMapping(value = "/moduleLog/add")
        public String add(@RequestParam("moduleLog") ModuleLog moduleLog);

        /**
         * 生成编码
         *
         * @param code
         * @return
         */
        @RequestMapping(value = "/code/generate/{code}/{num}")
        public String generate(@PathVariable("code") String code, @PathVariable("num") String num);

        /**
         * 批量生成员工
         *
         * @author natyu
         * @date 2018年7月27日 下午4:32:03
         * @param list
         * @return
         */
        @RequestMapping(value = "/user/batchadd")
        public String batchAdd(@RequestParam("list") List<String> list);

        /**
         * @Author jsl
         * @Date 14:43 2018/8/21
         * @Description    根据typecode和name查询
         **/
        @RequestMapping(value = "/code/getId")
        public String getByCodeAndName(@RequestParam("typeCode") String typeCode , @RequestParam("name") String name);
}
