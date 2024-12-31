package com.matzalal.web.controller;

import com.matzalal.web.entity.Rest;
import com.matzalal.web.entity.RestView;
import com.matzalal.web.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService service;

    @RequestMapping("result")
    public String list(Model model,
                       @RequestParam(required = false) String query

                      ){
        List<Rest> list = service.getViewList(query);
        System.out.println(query);
        System.out.println(list);
        model.addAttribute("list", list);
        return "/search/search_result";
    }
}
