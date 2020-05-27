package com.champlink.sale.service.call;

import com.champlink.common.domain.system.Code;
import com.champlink.common.domain.system.ModuleLog;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SaleCommonFallBackService implements SaleCommonService{
    @Override
    public String getAllCode() {
        return null;
    }

    @Override
    public String getByTpye(String typeCode) {
        return null;
    }

    @Override
    public String add(ModuleLog moduleLog) {
        return null;
    }

    @Override
    public String generate(String code, String num) {
        return null;
    }

    @Override
    public String batchAdd(List<String> list) {
        return null;
    }

    @Override
    public String getByCodeAndName(String typeCode, String name) {
        return null;
    }
}
