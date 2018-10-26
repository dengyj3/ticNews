package com.zcgx.ticNews.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zcgx.ticNews.dao.FTBanListDao;
import com.zcgx.ticNews.dao.PFListDao;
import com.zcgx.ticNews.dto.FTBanDTO;
import com.zcgx.ticNews.po.FTBanList;
import com.zcgx.ticNews.po.PFList;
import com.zcgx.ticNews.service.FTService;
import com.zcgx.ticNews.util.HttpBaseUtils;
import com.zcgx.ticNews.util.PageList;
import com.zcgx.ticNews.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("ftService")
public class FTServiceImpl implements FTService {
    private final static Logger logger = LoggerFactory.getLogger(FTServiceImpl.class);
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    FTBanListDao ftBanListDao;
    @Autowired
    PFListDao pfListDao;

    static Map<String, String> headerMap = new HashMap<>();
    static {
        headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
    }

    @Override
    public String findFTBanList() {
        String url = "http://125.35.6.80:8080/ftban/itownet/fwAction.do?method=getBaNewInfoPage";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("on", "true");
//        paramMap.put("page", "1");
        paramMap.put("pageSize", "15");
        paramMap.put("productName", "");
        paramMap.put("conditionType", "1");
        paramMap.put("applyname", "");
        paramMap.put("applysn", "");


//        JSONObject jsonObject = JSON.parseObject(result);
        int pageCount = 105468;//jsonObject.getInteger("pageCount");
//        JSONArray jsonArray = jsonObject.getJSONArray("list");
        pageCount = 2;
        for (int i = 1; i<=pageCount; i++){
            paramMap.put("page", i+"");
            String result = HttpBaseUtils.postRequest(restTemplate, url, headerMap, paramMap);
            System.out.println("ftban list is : " + result);
            JSONObject jsonObject = JSON.parseObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for (int j = 0; j<jsonArray.size(); j++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(j);
                FTBanList ftBanList = new FTBanList();
                String applySn = jsonObject1.getString("applySn");
                if(null != ftBanListDao.findByApplySn(applySn)){
                    logger.error("applySn repeat ...... --->>> " + applySn);
                    continue;
                }
                ftBanList.setApplySn(applySn);
                ftBanList.setApplySnTT(jsonObject1.getString("applySnTT"));
                ftBanList.setApplyEnterAddress(jsonObject1.getString("apply_enter_address"));
                ftBanList.setEnterpriseName(jsonObject1.getString("enterpriseName"));
                ftBanList.setEnterpriseNameTT(jsonObject1.getString("enterpriseNameTT"));
                ftBanList.setIsOff(jsonObject1.getString("is_off"));
                ftBanList.setNewProcessid(jsonObject1.getString("newProcessid"));
                ftBanList.setOffDate(jsonObject1.getString("off_date"));
                ftBanList.setOrgName(jsonObject1.getString("org_name"));
                String processid = jsonObject1.getString("processid");
                ftBanList.setProcessid(processid);
                ftBanList.setProductName(jsonObject1.getString("productName"));
                ftBanList.setProductNameTT(jsonObject1.getString("productNameTT"));
                ftBanList.setProvinceConfirm(jsonObject1.getString("provinceConfirm"));
                ftBanListDao.save(ftBanList);
                findPFList(processid);
            }
        }
        return null;
    }

    @Override
    public String findPFList(String processid) {
        String pfUrl = "http://125.35.6.80:8080/ftban/itownet/fwAction.do?method=getBaInfo&processid=" + processid;
        String pfResult = HttpBaseUtils.postRequest(restTemplate, pfUrl, headerMap, null);
        System.out.println("pf result is : " + pfResult);
        JSONObject jsonObject = JSON.parseObject(pfResult);
        JSONArray jsonArray = jsonObject.getJSONArray("pfList");
        for (int i = 0; i<jsonArray.size(); i++){
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            PFList pfList = new PFList();
            pfList.setCas(jsonObject1.getString("cas"));
            pfList.setCname(jsonObject1.getString("cname"));
            pfList.setPfname(jsonObject1.getString("pfname"));
            pfList.setYlid(jsonObject1.getInteger("ylid"));
            pfList.setProcessid(processid);
            pfListDao.save(pfList);
        }
        return null;
    }

    @Override
    public Response<PageList<FTBanList>> queryFTBanList(int pageNo, int pageSize) throws Exception {
        List<FTBanList> lists = ftBanListDao.findAll(pageNo, pageSize);
        long count = ftBanListDao.count();
        PageList<FTBanList> pageList = new PageList<FTBanList>(pageNo, pageSize, (int)count, lists);
        return Response.ok(pageList);
    }

    @Override
    public Response<FTBanDTO> queryFTBanDetail(long id) throws Exception {
        FTBanList ftBanList = ftBanListDao.findById(id);
        List<PFList> pfLists = pfListDao.findByProcessid(ftBanList.getProcessid());
        FTBanDTO ftBanDTO = new FTBanDTO();
        ftBanDTO.setPfLists(pfLists);
        ftBanDTO.setProcessid(ftBanList.getProcessid());
        ftBanDTO.setEnterpriseName(ftBanList.getEnterpriseName());
        ftBanDTO.setIsOff(ftBanList.getIsOff());
        ftBanDTO.setPrivinceConfirm(ftBanList.getProvinceConfirm());
        ftBanDTO.setProductName(ftBanList.getProductName());
        return Response.ok(ftBanDTO);
    }
}
