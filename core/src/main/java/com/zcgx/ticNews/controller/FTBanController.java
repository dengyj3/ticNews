package com.zcgx.ticNews.controller;

import com.zcgx.ticNews.dto.FTBanDTO;
import com.zcgx.ticNews.po.FTBanList;
import com.zcgx.ticNews.service.FTService;
import com.zcgx.ticNews.util.PageList;
import com.zcgx.ticNews.util.Response;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ft")
@CrossOrigin
public class FTBanController {
    private final static Logger logger = LoggerFactory.getLogger(FTBanController.class);
    @Autowired
    FTService ftService;

    @ApiOperation(value = "获取ft", notes = "根据请求获取ft")
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public Response<PageList<FTBanList>> queryFTBanList(@RequestParam(required = true) int pageNo, @RequestParam(required = true) int pageSize){
        try {
            if (pageNo < 1){
                pageNo = 0 * pageSize;
            }else {
                pageNo = (pageNo-1) * pageSize;
            }
            return ftService.queryFTBanList(pageNo, pageSize);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("获取ft列表失败! " + e);
            return Response.error("获取ft列表失败! " + e);
        }
    }
    @ApiOperation(value = "获取ft明细", notes = "根据ID获取ft详情")
    @RequestMapping(value = "/query/{id}", method = RequestMethod.GET)
    public Response<FTBanDTO> queryArticleDetail(@PathVariable(required = true) long id){
        try {
            return ftService.queryFTBanDetail(id);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("获取ft详情失败! " + e);
            return Response.error("获取ft详情失败! " + e);
        }
    }
}
