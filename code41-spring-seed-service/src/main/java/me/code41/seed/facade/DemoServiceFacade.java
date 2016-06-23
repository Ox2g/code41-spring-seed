package me.code41.seed.facade;

import me.code41.seed.api.dto.DemoDTO;
import me.code41.seed.api.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * Created by code41 on 2016/6/23.
 */
@Service
public class DemoServiceFacade implements DemoService {
    /**
     * Demo Method
     *
     * @return
     */
    @Override
    public DemoDTO demoMethod() {
        return null;
    }
}
