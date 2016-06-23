package com.jd.o2o.waybill.delivery;

import com.jd.jim.cli.Cluster;
import com.jd.o2o.combination.api.jmq.CancelCombinationMq;
import com.jd.o2o.commons.domain.context.AppContext;
import com.jd.o2o.commons.domain.response.ServiceResponse;
import com.jd.o2o.commons.utils.json.JsonUtils;
import me.code41.seed.api.dto.request.DeliveryEndRequestDTO;
import me.code41.seed.api.dto.request.ReturnStationRquestDTO;
import me.code41.seed.api.dto.settlement.SettlementDTO;
import com.jd.o2o.express.waybill.api.jmq.DeliveryOrderCreateMQ;
import com.jd.o2o.express.waybill.api.services.DeliveryService;
import com.jd.o2o.express.waybill.common.jimdb.JimDBUtil;
import com.jd.o2o.express.waybill.dao.DeliveryOrderDao;
import me.code41.seed.service.service.combinationcancel.CombinationCancelService;
import me.code41.seed.service.service.deliveryend.DeliveryEndService;
import com.jd.o2o.express.waybill.domain.entity.DeliveryOrderEntity;
import com.jd.o2o.lp.domain.enumuration.DeliveryOrderStatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * Created by weizheng on 16/3/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-config.xml"})
public class DeliveryServiceTest {
    @Resource
    private SearchSettlementServiceFacade searchSettlementServiceFacade;
    @Resource
    private DeliveryOrderDao deliveryOrderDao;
    @Resource
    private DeliveryEndService deliveryEndService;
    @Resource
    private JimDBUtil jimDBUtil;

    @Resource
    private DeliveryServiceFacade deliveryServiceFacade;
    @Resource
    private CombinationCancelService combinationCancelService;

    @Resource
    private Cluster jimClient;
    @Test
    public void testDeliveryEndSuccess() {
//        String json = "{\"deliveryOrderId\":1310352,\"taskId\":633634,\"deliveryManId\":1020343,\"longitude\":121.360321,\"latitude\":31.220681,\"clientMode\":\"9.2\",\"clientUniqueNo\":\"a397df46def4811dadd0c5d26ac8619cb28e7550\",\"downloadChannel\":\"AppStore\",\"ipAddress\":\"10.8.152.71\",\"isMark\":\"false\",\"distance\":905.5835294707836,\"apiVersion\":\"1.1\"} ";
        String json = "{\"deliveryOrderId\":1310365,\"taskId\":633701,\"deliveryManId\":1000635,\"longitude\":116.505707,\"latitude\":39.793291,\"clientMode\":\"9.2\",\"clientUniqueNo\":\"51945057051055ac612ff8defe9847eba41e074b\",\"downloadChannel\":\"AppStore\",\"ipAddress\":\"10.13.180.82\",\"isMark\":\"false\",\"distance\":52.34981047572597,\"apiVersion\":\"1.1\"}";
        DeliveryEndRequestDTO requestDTO = JsonUtils.fromJson(json,DeliveryEndRequestDTO.class);

        deliveryEndService.deliveryEndSuccess(new AppContext("test"), requestDTO,"weizheng");

//        System.out.println("\n" +
//                "  (\\____/)\n" +
//                "  / @__@ \\    \n" +
//                " (  (oo)  )   \n" +
//                "  `-.~~.-'\n" +
//                "   /    \\             \n" +
//                " @/      \\_          \n" +
//                "(/ /    \\ \\)\n" +
//                " WW`----'WW"+ JsonUtils.toJson(response));
    }


    @Test
    public void testJim() {
        String deliveryOrderNo = "ON000000731216";
//        jimClient.del("ON000000731905");
        jimDBUtil.setDeliveryStatus2JimDB("ON000000731850", DeliveryOrderStatusEnum.ORDER_RECEIVE_SUC.getCode()+"");
        jimDBUtil.setDeliveryStatus2JimDB("ON000000731846", DeliveryOrderStatusEnum.ORDER_RECEIVE_SUC.getCode()+"");
        jimDBUtil.setDeliveryStatus2JimDB("ON000000731835", DeliveryOrderStatusEnum.ORDER_RECEIVE_SUC.getCode()+"");
        jimDBUtil.setDeliveryStatus2JimDB("ON000000731824", DeliveryOrderStatusEnum.ORDER_RECEIVE_SUC.getCode()+"");

    }


    @Test
    public void testStationSignInStatusReturn(){
        ReturnStationRquestDTO returnStationRquestDTO = new ReturnStationRquestDTO();
        returnStationRquestDTO.setSrcOrderNo("20150826");
        returnStationRquestDTO.setSourceSysId("9");
        returnStationRquestDTO.setSignInStatus(0);
        returnStationRquestDTO.setStationId(11);
        returnStationRquestDTO.setSignInStatusDes("ceshi");
        returnStationRquestDTO.setStationName("ceshizhandian");
        deliveryServiceFacade.stationSignInStatusReturn(returnStationRquestDTO);
    }

    @Test
    public void testcombi(){
        CancelCombinationMq mq = JsonUtils.fromJson("{\"combinedId\":1000582,\"cancelTime\":\"2016-04-07 00:00:06\",\"freight\":0,\"cancelOrderNoList\":[\"ON000000746631\",\"ON000000746642\"],\"remainOrderNoList\":[],\"combinedDetailResponseDTOList\":[]}",CancelCombinationMq.class);
        combinationCancelService.combinationCancelProcess(mq);
    }



    private DeliveryOrderEntity getDeliveryOrderEntityByNo(String deliveryOrderNo){
        DeliveryOrderEntity deliveryOrderEntity = null;
        if(null != deliveryOrderNo) {
            DeliveryOrderCreateMQ deliveryOrderMQ = jimDBUtil.getDeliveryOrder(deliveryOrderNo);
            if(null != deliveryOrderMQ) {
                deliveryOrderEntity = new DeliveryOrderEntity();
                BeanUtils.copyProperties(deliveryOrderMQ.getDeliveryOrder(), deliveryOrderEntity);
            }
            //如果缓存中没查到，则从数据库查
            if(null == deliveryOrderEntity) {
                deliveryOrderEntity = deliveryOrderDao.getDeliveryOrderByDeliveryOrderNo(deliveryOrderNo);
            }
        }
        Assert.notNull(deliveryOrderEntity, "根据deliveryOrderNo从数据库查询deliveryOrderEntity返回为空，deliveryOrderNo:" + deliveryOrderNo);
        return deliveryOrderEntity;
    }

    private DeliveryOrderEntity getDeliveryOrderEntityById(Long deliveryOrderId){
        DeliveryOrderEntity deliveryOrderEntity = null;
        if(null != deliveryOrderId) {
            String deliveryOrderNo = jimDBUtil.getDeliveryNoByDeliveryOrderId(deliveryOrderId.toString());
            if(null != deliveryOrderNo) {
                return this.getDeliveryOrderEntityByNo(deliveryOrderNo);
            }
            deliveryOrderEntity = deliveryOrderDao.getDeliveryOrderById(deliveryOrderId);
        }
        Assert.notNull(deliveryOrderEntity,"根据id从数据库查询deliveryOrderEntity返回为空，id:" + deliveryOrderId);
        return deliveryOrderEntity;
    }
}
