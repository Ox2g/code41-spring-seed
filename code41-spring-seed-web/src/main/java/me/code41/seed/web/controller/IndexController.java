package me.code41.seed.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 */
@Controller
@RequestMapping(value = {"/"})
public class IndexController {
    private static final Logger Logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/")
    @ResponseBody
    public Object deliveryFetchSuccess(HttpServletRequest request, HttpServletResponse response) {
        return "hello";
    }
}
