package me.code41.seed.web.controller;

import com.jd.o2o.lp.annotation.SmartRequestParam;
import com.jd.o2o.lp.apk.domain.DeliveryManInfoSession;
import com.jd.o2o.lp.domain.JsonResponse;
import com.jd.o2o.lp.domain.SmartRequiredParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 */
@Controller
@RequestMapping(value = {"/"})
public class DeliveryController {
    private static final Logger Logger = LoggerFactory.getLogger(DeliveryController.class);

    @RequestMapping(value = "/")
    @ResponseBody
    public JsonResponse deliveryFetchSuccess(@SmartRequestParam(value = HashMap.class) SmartRequiredParams<HashMap<String, Object>> requestParams, DeliveryManInfoSession deliveryManInfoSession) {
        return null;
    }
}
