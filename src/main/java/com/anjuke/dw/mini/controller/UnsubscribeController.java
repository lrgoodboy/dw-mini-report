package com.anjuke.dw.mini.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UnsubscribeController {

    @RequestMapping("/unsubscribe")
    public String index(ModelMap model,
            @RequestParam("reportId") int reportId) {

        model.addAttribute("reportId", reportId);
        return "unsubscribe";
    }

}
