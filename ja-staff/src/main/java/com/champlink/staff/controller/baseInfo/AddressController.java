package com.champlink.staff.controller.baseInfo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.staff.baseInfo.Address;
import com.champlink.staff.service.baseInfo.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController extends BaseController {

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/addOrEdit")
    public String addAddress(Address address) {
        if (addressService.addAddress(address)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    @RequestMapping(value = "/del/{id}")
    public String delAddressListByStaffId(@PathVariable Long id) {
        if (addressService.delAddressListByStaffId(id)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    @RequestMapping(value = "/list/{id}")
    public String searchAddressListByStaffId(@PathVariable Long id) {
        List<Address> list = addressService.getAddressList(id);
        return getSuccessJson(list);
    }

    @RequestMapping(value = "/getByRowId/{id}")
    public String getByRowId(@PathVariable Long id) {
        Address address = addressService.getByRowId(id);
        return getSuccessJson(address);
    }

}