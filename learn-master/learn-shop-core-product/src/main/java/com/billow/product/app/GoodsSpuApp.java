package com.billow.product.app;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * spu表 前端控制器
 * </p>
 *
 * @author billow
 * @version v1.0
 * @since 2019-11-27
 */
@Api(tags = {"GoodsSpuApp"}, value = "spu表")
@RestController
@RequestMapping("/goodsSpuApp")
public class GoodsSpuApp {

    private Logger logger = LoggerFactory.getLogger(getClass());

//    @Autowired
//    private GoodsSpuService goodsSpuService;
//    @Autowired
//    private AliPayScanService aliPayScanService;
//
//
//    @ApiOperation(value = "根据id查询spu表数据")
//    @GetMapping(value = "/getById/{id}")
//    public GoodsSpuVo getById(@PathVariable("id") String id) {
////        try {
////            AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
////            model.setOutTradeNo(UUID.randomUUID().toString());
////            model.setSubject("我就买一个商品");
////            model.setTotalAmount("0.01");
//////            model.setTimeoutExpress("90m");
////            String qrCode = aliPayScanService.tradePrecreate(model);
////            System.out.println("=====================");
////            System.out.println(qrCode);
////            QrGenUtil.zxingCodeCreate(qrCode, "C:/Users/Administrator/Desktop/b.jpg", 350, null);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        return null;
//        GoodsSpuPo po = goodsSpuService.getById(id);
//        return ConvertUtils.convert(po, GoodsSpuVo.class);
//    }
}
