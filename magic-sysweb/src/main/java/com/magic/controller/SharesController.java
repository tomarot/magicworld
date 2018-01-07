package com.magic.controller;

import com.magic.entity.DataCheck;
import com.magic.entity.Expect;
import com.magic.entity.Shares;
import com.magic.service.ExpectService;
import com.magic.service.ShareDataCheckService;
import com.magic.service.SharePortraitService;
import com.magic.service.SharesService;
import com.magic.utils.DateUtil;
import com.magic.utils.PageBean;
import com.magic.utils.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: T5S
 * Date: 17-9-13
 * Time: 下午10:21
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/shares")
public class SharesController {
    @Autowired
    private SharesService sharesService;
    @Autowired
    private ExpectService expectService;
    @Autowired
    private SharePortraitService sharePortraitService;
    @Autowired
    ShareDataCheckService shareDataCheckService;

    @RequestMapping("toSharesManagerPage.do")
    public String sharesManagerPage(){
        return "shares/sharesManager/sharesManager";
    }
    /* 股票 开始*/
    /**
     * 股票数据列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("querySharesList.do")
    @ResponseBody
    public PageBean<Shares> querySharesList(Shares shares,PageBean<Shares> pageBean,HttpServletRequest request, HttpServletResponse response){
        pageBean =  sharesService.querySharesListForPage(shares,pageBean);
        return pageBean;
    }

    /**
     * 查询股票期望数据
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("queryExpectList.do")
    @ResponseBody
    public List<Expect> queryExpectList(HttpServletRequest request, HttpServletResponse response,String sharesCode){
        List<Expect> list = expectService.queryExpectList(sharesCode);
        return list;
    }

    @RequestMapping("addSharesPage.do")
    public String addSharesPage(){
        return "shares/operatorShares";
    }

    @RequestMapping("saveShares.do")
     public ResultVo saveShares(HttpServletRequest request, HttpServletResponse response,Shares shares){
        return null;
    }

    @RequestMapping("updateShares.do")
    public ResultVo updateShares(HttpServletRequest request, HttpServletResponse response,Shares shares){
        return null;
    }

    @RequestMapping("deleteShares.do")
    public ResultVo deleteShares(HttpServletRequest request, HttpServletResponse response,Shares shares){
        return null;
    }
    /* 股票 结束 */
    /* 预期 开始 */
    @RequestMapping("addExpectPage.do")
    public String addExpectPage(){
        return "shares/operatorExpect";
    }
    @RequestMapping("operatorExpect.do")
    @ResponseBody
    public ResultVo saveExpect(HttpServletRequest request, HttpServletResponse response,Expect expect){
        ResultVo resultVo = null;
        if(expect.getId()!=null){
            expectService.updateExpect(expect);
        }else{
            resultVo = expectService.saveExpect(expect);
        }
        return resultVo;
    }

    @RequestMapping("createExpectPrice.do")
    @ResponseBody
    public ResultVo createExpectPrice(HttpServletRequest request, HttpServletResponse response,Expect expect){
        ResultVo resultVo = null;
        resultVo = expectService.createExpectPrice(expect);
        return resultVo;
    }

    /* 预期 结束*/
    /* 股票画像 开始*/
    @RequestMapping("toSharePortraitChartPage.do")
    public String sharePortraitChartPage(HttpServletRequest request, HttpServletResponse response,Model model){
        List<Shares> list = sharesService.querySharesList();
        model.addAttribute("shareList",list);
        return "shares/sharesPortraitChart/sharePortraitChart";
    }
    @RequestMapping("getSharePortrait.do")
    @ResponseBody
    public ResultVo getSharePortrait(HttpServletRequest request, HttpServletResponse response,Integer dataId){
        ResultVo resultVo = null;
        resultVo = sharePortraitService.getSharePortrait(dataId);
        return resultVo;
    }
    /* 股票画像 结束*/
    /* 股票点位分析 开始 */
    @RequestMapping("toShareAnalysisPage.do")      //http://localhost:8080/shares/toShareAnalysisPage.do
    public String shareAnalysisPage(HttpServletRequest request, HttpServletResponse response,Model model){
        List<Shares> list = sharesService.querySharesList();
        model.addAttribute("shareList",list);
        return "shares/share_analysis/shareAnalysisAdd";
    }
    /* 股票点位分析 结束 */
    /* 选点 开始 */

    /**
     * 主界面
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("toDataCheckManagerPage.do")      //http://localhost:8080/shares/toDataCheckManagerPage.do
    public String toDataCheckManagerPage(HttpServletRequest request, HttpServletResponse response,Model model){
        List<Shares> list = sharesService.querySharesList();
        model.addAttribute("shareList",list);
        /*shareDataCheckService.getDataCheck();*/
        return "shares/dataCheck/dataCheckManager";
    }

    /**
     * 选点数据列表子页面
     * @param shareDataId
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("toShareDataCheckPage.do")      //http://localhost:8080/shares/toShareDataCheckPage.do
    public String toShareDataCheckPage(String shareDataId,HttpServletRequest request, HttpServletResponse response,Model model){
       // List<Shares> list = sharesService.querySharesList();
        Shares shares = sharesService.getShares(Integer.valueOf(shareDataId));
        List<DataCheck> list = shareDataCheckService.queryDataCheckList(shares.getCode());
        model.addAttribute("dataCheckList",list);
        /*shareDataCheckService.getDataCheck();*/
        return "shares/dataCheck/dataCheckPage";
    }

    @RequestMapping("toOperationDataCheckPage.do")      //http://localhost:8080/shares/toShareDataCheckPage.do
    public String toOperationDataCheckPage(String sharesCode,HttpServletRequest request, HttpServletResponse response,Model model){
        // List<Shares> list = sharesService.querySharesList();
        //Shares shares = sharesService.getShares(Integer.valueOf(shareDataId));
        //List<DataCheck> list = shareDataCheckService.queryDataCheckList(shares.getCode());
        //model.addAttribute("dataCheckList",list);
        /*shareDataCheckService.getDataCheck();*/
        model.addAttribute("shareCode",sharesCode);
        return "shares/dataCheck/operationDataCheckPage";
    }
    @RequestMapping("saveDataCheck.do")
    public String saveDataCheck(DataCheck dataCheck,HttpServletRequest request, HttpServletResponse response,Model model){
        dataCheck.setCreateTime(new Date());
        shareDataCheckService.addDataCheck(dataCheck);
        return "redirect:/shares/toOperationDataCheckPage.do";
    }
    /* 选点 结束 */
}
