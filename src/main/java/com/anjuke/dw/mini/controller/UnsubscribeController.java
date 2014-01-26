package com.anjuke.dw.mini.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.anjuke.dw.mini.dao.ReportDao;
import com.anjuke.dw.mini.model.AnjukeUser;
import com.anjuke.dw.mini.model.Report;


@Controller
@SessionAttributes("currentUser")
public class UnsubscribeController {

    @Autowired
    private ReportDao reportDao;

    @RequestMapping("/unsubscribe")
    public String index(ModelMap model,
            @RequestParam("reportId") int reportId) {

        Report report = reportDao.findById(reportId);
        if (report == null) {
            model.addAttribute("error", "报表ID不存在");
            return "unsubscribeError";
        }

        model.addAttribute("report", report);
        return "unsubscribe";
    }

    @RequestMapping("/unsubscribe/confirm")
    public String confirm(ModelMap model,
            @RequestParam("reportId") int reportId) {

        Report report = reportDao.findById(reportId);
        if (report == null) {
            model.addAttribute("error", "报表ID不存在");
            return "unsubscribeError";
        }

        AnjukeUser user = (AnjukeUser) model.get("currentUser");
        List<String> receivers = new ArrayList<String>();
        boolean exists = false;
        for (String receiver : report.getReceivers().split("[\\r\\n]+")) {
            receiver = receiver.trim();
            if (receiver.isEmpty()) {
                continue;
            }
            if (receiver.equals(user.getEmail())) {
                exists = true;
            } else {
                receivers.add(receiver);
            }
        }

        if (!exists) {
            model.addAttribute("error", "您没有订阅该报表，或该报表是发送给邮件组的，您无法退订。");
            return "unsubscribeError";
        }

        reportDao.updateRecievers(reportId, StringUtils.join(receivers, "\n"));
        return "unsubscribeSuccess";
    }

}
