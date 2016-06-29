package me.code41.seed.web.controller;

import com.alibaba.fastjson.JSON;
import me.code41.seed.api.dto.DemoDTO;
import me.code41.seed.api.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 */
@Controller
@RequestMapping(value = {"/"})
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Resource
    DemoService demoService;

    @RequestMapping(value = "/")
    @ResponseBody
    public Object hello(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> result = new HashMap<String, String>();
        result.put("result", "hello");
        return result;
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public Object test() {
        DemoDTO demoDTO = demoService.demoMethod();
        logger.info("IndexController.test=>{}", JSON.toJSON(demoDTO));
        return demoDTO;
    }
}
