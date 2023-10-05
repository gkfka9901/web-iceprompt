package com.study.web.controller;

import com.study.web.entity.Web;
import com.study.web.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Controller
public class WebController {

    @Autowired
    private WebService webService;


    @GetMapping("/")
    public String webIndex(Model model,
                           @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                           @RequestParam(required = false) String searchKeyword) {

        Page<Web> list = null;

        if (searchKeyword == null) {
            list = webService.webList(pageable);
        } else {
            list = webService.webSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);


        return "index";
    }
    @GetMapping("/web/write") //localhost:8080/web/write
    public String webWriteForm() {

        return "webwrite";
    }

    @PostMapping("/web/writepro")
    public String webWritepro(Web web, Model model) {    //Web web은 webWritepro() 메소드의 매개변수로, Web 클래스 타입의 객체(=인스턴스).

        webService.write(web);  //Autowired를 통해 주입된 webService

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/web/list");

        return "writemessage";
    }

    @GetMapping("/web/list")
    public String webList(Model model,
                          @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                          @RequestParam(required = false) String searchKeyword) {

        Page<Web> list = null;

        if (searchKeyword == null) {
            list = webService.webList(pageable);
        } else {
            list = webService.webSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "weblist";
    }


    @GetMapping("/web/view")
    public String webView(Model model, Integer id) {
        model.addAttribute("web", webService.webview(id));
        return "webview";
    }


    @GetMapping("/web/delete")
    public String webDelete(Integer id) {

        webService.webDelete(id);

        return "redirect:/web/list";
    }

    @GetMapping("/web/modify/{id}")
    public String webModify(@PathVariable("id") Integer id,
                            Model model) {

        model.addAttribute("web", webService.webview(id));

        return "webmodify";
    }

    @PostMapping("/web/update/{id}")
    public String webUpdate(@PathVariable("id") Integer id, Web web) throws Exception{

        Web webTemp = webService.webview(id);
        webTemp.setTitle(web.getTitle());
        webTemp.setContent1(web.getContent1());
        webTemp.setContent2(web.getContent2());
        webTemp.setContent3(web.getContent3());
        webTemp.setContent4(web.getContent4());
        webTemp.setContent5(web.getContent5());

        webService.write(webTemp);

        return "redirect:/web/list";
    }

    @GetMapping("/web/account")
    public String webAccount() {
        return "account";
    }

}
