package com.magic.service.com.magic.service.impl;

import com.magic.common.WebConstants;
import com.magic.dao.SharesGameRecordMapper;
import com.magic.dao.SharesHistoryDataDao;
import com.magic.dao.SharesHistoryDataMapper;
import com.magic.entity.SharesGameRecord;
import com.magic.entity.SharesHistoryData;
import com.magic.service.SharesHistoryDataService;
import com.magic.utils.DateUtil;
import com.magic.utils.PageBean;
import com.magic.common.ResultVo;
import com.magic.utils.UUIDUtils;
import com.magic.vo.KChartDataVo;
import com.magic.vo.SharesGameRecordVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: T5S
 * Date: 2018/1/14
 * Time: 15:52
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class SharesHistoryDataServiceImpl implements SharesHistoryDataService{
    @Autowired
    private SharesHistoryDataDao sharesHistoryDataDao;
    @Autowired
    private SharesHistoryDataMapper sharesHistoryDataMapper;
    @Autowired
    private SharesGameRecordMapper sharesGameRecordMapper;
    @Override
    public ResultVo impSharesHistoryData(MultipartFile file) {
        try {
//            FileInputStream fileIn = new FileInputStream("C:/Users/T5S/Desktop/rhlk.xls");
//            FileInputStream fileIn = new FileInputStream(file);
//            FileInputStream fileIn = new FileInputStream(file);
            InputStream fileInputStream = file.getInputStream();
            List<SharesHistoryData> sharesHistoryDataList = analysisExeclData(fileInputStream);
            for(SharesHistoryData sharesHistoryData:sharesHistoryDataList){
                sharesHistoryData.setCreateTime(new Date());
//                sharesHistoryDataDao.saveSharesHistoryData(sharesHistoryData);
                sharesHistoryDataMapper.insertSelective(sharesHistoryData);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SharesHistoryData> analysisExeclData(InputStream fileInputStream) {
        List<SharesHistoryData> arrayList = new ArrayList<SharesHistoryData>();
        try {

            //根据指定的文件输入流导入Excel从而产生Workbook对象
            Workbook wb0 = new HSSFWorkbook(fileInputStream);
            //获取Excel文档中的第一个表单
            Sheet sht0 = wb0.getSheetAt(0);
            //对Sheet中的每一行进行迭代
            for (Row r : sht0) {
                //如果当前行的行号（从0开始）未达到2（第三行）则从新循环
                if (r.getRowNum() < 1) {
                    continue;
                }
                //将单元格数据类型全部转换成Cell.CELL_TYPE_STRING类型
                for(int i=0;i<13;i++){
                    if(r.getCell(i)!=null){
                        r.getCell(i).setCellType(CellType.STRING);
                    }
                }
                //创建实体类
                SharesHistoryData sharesData = new SharesHistoryData();
                //取出当前行第1个单元格数据，并封装在info实体stuName属性上
                sharesData.setId(UUIDUtils.getUUID());
                sharesData.setSharesId(r.getCell(0).getStringCellValue());
                sharesData.setFrequency(r.getCell(1).getStringCellValue());
                sharesData.setDataTime(DateUtil.string2SqlDate(r.getCell(2).getStringCellValue().split(",")[0]));
                sharesData.setStartPrice(Double.valueOf(r.getCell(3).getStringCellValue()));
                sharesData.setHighPrice(Double.valueOf(r.getCell(4).getStringCellValue()));
                sharesData.setLowPrice(Double.valueOf(r.getCell(5).getStringCellValue()));
                sharesData.setEndPrice(Double.valueOf(r.getCell(6).getStringCellValue()));
                sharesData.setUpVal(r.getCell(7).getStringCellValue());
                sharesData.setAmplitude(r.getCell(8).getStringCellValue());
                sharesData.setCountNum(r.getCell(9).getStringCellValue());
                sharesData.setCountMoney(r.getCell(10).getStringCellValue());
                sharesData.setChangedHands(r.getCell(11).getStringCellValue());
                sharesData.setVolamount(Integer.valueOf(r.getCell(12).getStringCellValue()));
                arrayList.add(sharesData);
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return arrayList;
    }

    @Override
    public PageBean<SharesHistoryData> querySharesHistoryData(SharesHistoryData sharesHistoryData) {
//        List<SharesHistoryData> resultList = sharesHistoryDataDao.querySharesHistoryData(sharesHistoryData,pageBean);
        List<SharesHistoryData> resultList = sharesHistoryDataMapper.selectBySelective(sharesHistoryData);
        int count = sharesHistoryDataMapper.selectCountBySelective(sharesHistoryData);
//        int count = sharesHistoryDataDao.querySharesHistoryDataCount(sharesHistoryData);
        PageBean<SharesHistoryData> resultPageBean = new PageBean<SharesHistoryData>();
        resultPageBean.setRows(resultList);
        resultPageBean.setTotal(count);
        resultPageBean.setResultCode(0);
        return resultPageBean;
    }

    @Override
    public KChartDataVo queryKChartData(SharesHistoryData sharesHistoryData) {
        KChartDataVo kChartDataVo = new KChartDataVo();
        List<SharesHistoryData> resultList = sharesHistoryDataMapper.selectForNoPageBySelective(sharesHistoryData);
        kChartDataVo.setDataList(resultList);
        return kChartDataVo;
    }

    @Override
    @Transactional
    public KChartDataVo queryGameKChartData(String gameCode,String next) {
        SharesGameRecord selectGameRecord = new SharesGameRecord();
        //读取游戏进度
        SharesGameRecordVo selectGameRecordVo = new SharesGameRecordVo();
        selectGameRecordVo.setGamecode(gameCode);
        List<SharesGameRecord> list = sharesGameRecordMapper.selectBySelective(selectGameRecordVo);
        if(list!=null&&list.size()>0){
            selectGameRecord = list.get(0);
        }
        //获得数据开始位置
        int startIndex = sharesHistoryDataMapper.getDataStartIndex(selectGameRecord.getStartrecordid());
        //偏移值
        int offsetNum = selectGameRecord.getOffsetnum().intValue();
        if("y".equals(next)){
            offsetNum++;
            selectGameRecord.setOffsetnum(new Long(offsetNum));
            sharesGameRecordMapper.updateByPrimaryKey(selectGameRecord);
        }
        SharesHistoryData sharesHistoryData = new SharesHistoryData();
        sharesHistoryData.setkChartStartIndex(startIndex);
        sharesHistoryData.setLimit(offsetNum + WebConstants.GP.DATALENGTH);
        sharesHistoryData.setSharesCode(selectGameRecord.getGamecode());
        sharesHistoryData.setFrequency(selectGameRecord.getFrequency());
        return queryKChartData(sharesHistoryData);
    }

    public static void main(String[] args) {
//        SharesHistoryDataServiceImpl s = new SharesHistoryDataServiceImpl();
//        s.impSharesHistoryData(new File(""));
    }
}
