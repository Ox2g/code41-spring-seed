package com.jd.o2o.waybill.delivery;

import me.code41.seed.api.dto.request.DeliveryFetchRequestDTO;
import me.code41.seed.service.service.fetch.DeliveryFetchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by zhangjiwei on 16/3/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-config.xml"})
public class FetchServiceTest {
    @Resource
    DeliveryFetchService deliveryFetchService;

    @Test
    public void testFetchSuccess() {
        DeliveryFetchRequestDTO fetchRequestDTO = new DeliveryFetchRequestDTO();
//        fetchRequestDTO.setDeliveryManId(1377L);
//        fetchRequestDTO.setBarCode("OE000000567431");
        fetchRequestDTO.setDeliveryManId(103L);
        fetchRequestDTO.setBarCode("201603231638");
        deliveryFetchService.fetchSuccess(fetchRequestDTO, "test");
    }

    @Test
    public void testFetchFail() {
        DeliveryFetchRequestDTO fetchRequestDTO = new DeliveryFetchRequestDTO();
//        fetchRequestDTO.setDeliveryManId(1377L);
//        fetchRequestDTO.setDeliveryOrderId(4L);
        fetchRequestDTO.setDeliveryManId(103L);
        fetchRequestDTO.setDeliveryOrderId(1305940L);
        fetchRequestDTO.setFailReason("GTF_5");
        deliveryFetchService.fetchFail(fetchRequestDTO, "test");
    }

    @Test
    public void testFetchFailToSuccess() {
        DeliveryFetchRequestDTO fetchRequestDTO = new DeliveryFetchRequestDTO();
        fetchRequestDTO.setDeliveryManId(103L);
        fetchRequestDTO.setBarCode("20122275578");
        deliveryFetchService.fetchFailToSuccess(fetchRequestDTO, "test");
    }
}
