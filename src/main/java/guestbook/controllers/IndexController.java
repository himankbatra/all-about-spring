package guestbook.controllers;

import guestbook.model.GuestbookEntry;
import guestbook.services.GuestbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private GuestbookService guestbookService;

    @GetMapping(path = "/")
    public  String index(Model model)
    {
        List<GuestbookEntry> entries = guestbookService.findAll();
        model.addAttribute("guestBookEntries",entries);
        return "index";
    }

}
