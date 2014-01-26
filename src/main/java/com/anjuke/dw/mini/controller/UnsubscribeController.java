package com.anjuke.dw.mini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.anjuke.dw.mini.dao.ReportDao;
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

}
