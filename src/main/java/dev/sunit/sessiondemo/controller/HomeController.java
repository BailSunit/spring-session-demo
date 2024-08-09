package dev.sunit.sessiondemo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import org.springframework.jdbc.core.JdbcTemplate;

@RestController
@RequestMapping(value="/api/v1")
public class HomeController {

    private JdbcTemplate pgTemplate;
    private final String HOME_VIEW_COUNT = "HOME_VIEW_COUNT";
    @GetMapping("/")
    public String home(Principal principal, HttpSession session) {
        incrementCount(session, HOME_VIEW_COUNT);
        return "Hello " + principal.getName();
    }

    @GetMapping("/count")
    public String count(HttpSession session) {
        return "HOME_VIEW_COUNT : " + session.getAttribute(HOME_VIEW_COUNT);
    }
    private void incrementCount(HttpSession session, String attr) {
        var homeViewCount = session.getAttribute(attr)==null ? 0 : (Integer) session.getAttribute(attr);
        session.setAttribute(attr, homeViewCount+=1);
    }

    public String createTable() {
        String s = "CREATE TABLE testing (ID numeric, NAME varchar20)";
        pgTemplate.query(s);
    }
}
