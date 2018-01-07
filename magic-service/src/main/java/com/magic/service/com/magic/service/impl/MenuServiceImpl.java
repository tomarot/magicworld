package com.magic.service.com.magic.service.impl;

import com.magic.dao.MenuDao;
import com.magic.entity.Menu;
import com.magic.service.MenuService;
import com.magic.utils.PageBean;
import com.magic.utils.ResultVo;
import com.magic.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: T5S
 * Date: 17-5-18
 * Time: 下午9:15
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    @Transactional(readOnly = false,propagation= Propagation.REQUIRED)
    public ResultVo<Menu> addMenu(Menu menu) {
        ResultVo<Menu> resultVo = new ResultVo<Menu>();
        menu.setId(UUIDUtils.getUUID());
        menu.setCreateTime(new Date());
        resultVo.setData(menu);
        menuDao.saveMenu(menu);
       // menu.setId("123123132");

       // menuDao.saveMenu(menu);
        resultVo.setState(ResultVo.SUCCESS);
        resultVo.setMessage(ResultVo.SUCCESS_MESSAGE);
        return resultVo;
    }

    @Override
    @Transactional(readOnly = false,propagation= Propagation.REQUIRED)
    public ResultVo<Menu> updateMenu(Menu menu) {
        ResultVo<Menu> resultVo = new ResultVo<Menu>();
        resultVo.setData(menu);
        menuDao.updateMenu(menu);
        resultVo.setState(ResultVo.SUCCESS);
        resultVo.setMessage(ResultVo.SUCCESS_MESSAGE);
        return resultVo;
    }

    @Override
    @Transactional(readOnly = false,propagation= Propagation.REQUIRED)
    public ResultVo<Menu> deleteMenuByUpdate(String menuIds) {
        return null;
    }

    @Override
    @Transactional(readOnly = false,propagation= Propagation.REQUIRED)
    public ResultVo<Menu> deleteMenuByDelete(String[] menuIds) {

        ResultVo<Menu> resultVo = new ResultVo<Menu>();
        String ids = "";
        for(String id:menuIds){
             ids += "'"+id +"',";
        }
        ids = ids.substring(0,ids.length()-1);
        resultVo.setData(menuDao.deleteMenu(ids));
        resultVo.setState(ResultVo.SUCCESS);
        resultVo.setMessage(ResultVo.SUCCESS_MESSAGE);
        return resultVo;
    }

    @Override
    public List<Menu> getChildMenus(String parentId) {
        return menuDao.getChildMenus(parentId);
    }

    @Override
    public List<Menu> queryMenus(Menu menu) {
        return menuDao.queryMenus(menu);
    }

    @Override
    public PageBean<Menu> queryMenus(Menu menu, PageBean pageBean) {
        PageBean<Menu> resultPageBean = new PageBean<Menu>();
        List<Menu> resultList = menuDao.queryMenus(menu,pageBean);
        int count = menuDao.queryMenusCount(menu);
        resultPageBean.setRows(resultList);
        resultPageBean.setTotal(count);
        resultPageBean.setCode(0);
        return resultPageBean;
    }

    @Override
    public Menu getMenuInfo(String id) {
        return menuDao.getMenuInfo(id);
    }

    @Override
    public Map<String, Object> getOnePageInfo(String id) {
        Map<String,Object> map = new HashMap<String,Object>();
        Menu menu = this.getMenuInfo(id);
        List<Menu> list = this.getChildMenus(id);
        map.put("menu",menu);
        map.put("childMenu",list);
        return map;
    }

    @Override
    public ResultVo<Menu> getInitMenusData(String userId) {
        ResultVo<Menu> resultVo = new ResultVo<Menu>(ResultVo.SUCCESS,ResultVo.SUCCESS_MESSAGE);
        List<Menu> list = getMenusListForLevel(1);  //获取一级菜单
        resultVo.setDataList(list);
        for(Menu menu:list){
            buildMenusData(menu);
        }
        return resultVo;
    }

    /**
     * 递归构建菜单数据机构
     * @param menu
     */
    public void buildMenusData(Menu menu){
        List<Menu> childList = getChildMenus(menu.getId());  //获取子集菜单
        if(childList!=null&&childList.size()>0){
            menu.setChildList(childList);
            for(Menu childMenu:childList){
                buildMenusData(childMenu);
            }
        }
    }

    @Override
    public List<Menu> getMenusListForLevel(int level) {
        Menu queryMenu = new Menu();
        queryMenu.setLevel(level);
        queryMenu.setState(1+"");
        List<Menu> list = queryMenus(queryMenu);
        return list;
    }

    @Override
    @Transactional
    public ResultVo<Menu> testTransMenuPage() {
        Menu menu = new Menu();
        menu.setId(UUIDUtils.getUUID());
        menu.setName("test1");
        //menuDao.saveMenu(menu);
        addMenu(menu);
        menu.setId(UUIDUtils.getUUID());
        menu.setName("错误数据测试错误数据(事务)");
       // menuDao.saveMenu(menu);
        addMenu(menu);
        return null;
    }

}
