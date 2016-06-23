package com.jd.o2o.waybill.delivery;

import com.jd.o2o.express.waybill.common.jimdb.JimDBUtil;
import me.code41.seed.service.service.orderoperate.OrderOperateService;
import com.jd.o2o.ocs.core.api.domain.josmq.OrderCancelMQ;
import com.jd.o2o.ocs.core.api.domain.josmq.OrderLockMQ;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by zhangjiwei on 16/3/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-config.xml"})
public class OrderOperateServiceTest {
    @Resource
    private OrderOperateService orderOperateService;
    @Resource
    private JimDBUtil jimDBUtil;

    @Test
    public void testOrderCancelProcess() {
        String deliveryOrderNo = "OJ000000567420";
        // 设置运单状态
        jimDBUtil.setDeliveryStatus2JimDB(deliveryOrderNo, String.valueOf(10));
        OrderCancelMQ orderCancelMQ = new OrderCancelMQ();
//        orderCancelMQ.setOcOrderId("1111212205");
        orderCancelMQ.setOcOrderId("111121220");
        orderOperateService.orderCancelProcess(orderCancelMQ);
    }

    @Test
    public void testOrderCancel() {
        orderOperateService.orderCancel("2015102119041516", "9");
    }

    @Test
    public void testOrderLock() {
        OrderLockMQ orderLockMQ = new OrderLockMQ();
        orderLockMQ.setOcOrderId("2015102119041516");
        orderOperateService.orderLock(orderLockMQ);
    }
}
