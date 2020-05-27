package com.champlink.staff.controller.baseInfo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.staff.baseInfo.ContactEmergency;
import com.champlink.staff.service.baseInfo.ContactEmergencyService;

@RestController
@RequestMapping("/contactEmergency")
public class ContactEmergencyController extends BaseController {

    @Autowired
    private ContactEmergencyService contactEmergencyService;

    @RequestMapping(value = "/addOrEdit")
    public String addContactEmergency(ContactEmergency contactEmergency) {
        if (contactEmergencyService.addContactEmergency(contactEmergency)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    @RequestMapping(value = "/del/{id}")
    public String delContactEmergencyListByStaffId(@PathVariable Long id) {
        if (contactEmergencyService.delContactEmergencyListByStaffId(id)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    @RequestMapping(value = "/list/{id}")
    public String searchContactEmergencyList(@PathVariable Long id) {
        List<ContactEmergency> list = contactEmergencyService.getContactEmergencyList(id);
        return getSuccessJson(list);
    }

    @RequestMapping(value = "/getByRowId/{id}")
    public String getByRowId(@PathVariable Long id) {
        ContactEmergency contactEmergency = contactEmergencyService.getByRowId(id);
        return getSuccessJson(contactEmergency);
    }

}