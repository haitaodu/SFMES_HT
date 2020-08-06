package com.smartflow.service;

import com.smartflow.dao.JPHDao;
import com.smartflow.dao.MaterialDao;
import com.smartflow.dto.*;
import com.smartflow.model.JPH;
import com.smartflow.model.Location;
import com.smartflow.model.User;
import com.smartflow.util.ParseFieldToMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class JPHServiceImpl implements JPHService {
    @Autowired
    JPHDao jphDao;
    @Autowired
    MaterialDao materialDao;
    //拼接方法拼接成为MaterialNumber(MaterialDescriptor)这样的形式
    ParseFieldToMapUtil parseFieldToMapUtil = new ParseFieldToMapUtil();
    @Autowired
    HibernateTemplate hibernateTemplate;
    @Override
    public Integer getTotalCountJPHListByCondition(GetJPHListConditionInputDTO getJPHListConditionInputDTO) {
        return jphDao.getTotalCountJPHListByCondition(getJPHListConditionInputDTO);
    }

    @Override
    public List<GetJPHListOutputDTO> getJPHListByCondition(GetJPHListConditionInputDTO getJPHListConditionInputDTO) {
        List<JPH> jphList = jphDao.getJPHListByCondition(getJPHListConditionInputDTO);
        List<GetJPHListOutputDTO> getJPHListOutputDTOList=new ArrayList<>();
        if(jphList != null){
            for (JPH jph:jphList) {
                GetJPHListOutputDTO getJPHListOutputDTO = new GetJPHListOutputDTO();
                getJPHListOutputDTO.setId(jph.getId());
                getJPHListOutputDTO.setLocation(parseFieldToMapUtil.parseFiledToString(jph.getLocation().getLocationNumber(), jph.getLocation().getDescription()));
                getJPHListOutputDTO.setMaterial(parseFieldToMapUtil.parseFiledToString(jph.getMaterial().getMaterialNumber(), jph.getMaterial().getDescription()));
                getJPHListOutputDTO.setCreator(jph.getCreator().getUserName());
                getJPHListOutputDTO.setCreationDateTime(jph.getCreationDateTime());
                getJPHListOutputDTO.setEditDateTime(jph.getEditDateTime());
                getJPHListOutputDTO.setJPH(jph.getJPH());//JPH表示做一个件用多少秒
                if(jph.getJPH().compareTo(BigDecimal.ZERO) != 0) {//判断JPH不为0
                    BigDecimal eightHoursProduction = new BigDecimal(60 * 60 * 8);//8个小时换算成秒
                    BigDecimal tenHoursProduction = new BigDecimal(60 * 60 * 10);//10个小时换算成秒
                    BigDecimal twelveHoursProduction = new BigDecimal(60 * 60 * 12);//12个小时换算成秒
                    eightHoursProduction = eightHoursProduction.divide(jph.getJPH(), 0, BigDecimal.ROUND_DOWN);//8小时产量
                    tenHoursProduction = tenHoursProduction.divide(jph.getJPH(), 0, BigDecimal.ROUND_DOWN);//10小时产量
                    twelveHoursProduction = twelveHoursProduction.divide(jph.getJPH(), 0, BigDecimal.ROUND_DOWN);//12小时产量
                    getJPHListOutputDTO.setEightHoursProduction(eightHoursProduction.intValue());
                    getJPHListOutputDTO.setTenHoursProduction(tenHoursProduction.intValue());
                    getJPHListOutputDTO.setTwelveHoursProduction(twelveHoursProduction.intValue());
                }else{
                    getJPHListOutputDTO.setEightHoursProduction(0);
                    getJPHListOutputDTO.setTenHoursProduction(0);
                    getJPHListOutputDTO.setTwelveHoursProduction(0);
                }
                getJPHListOutputDTO.setEditor(jph.getEditor().getUserName());
                getJPHListOutputDTO.setName(jph.getMaterial().getDescription());
                String state=null;
                if (jph.getState()==0)
                {
                    state="未激活";
                }
                if (jph.getState()==1)
                {
                    state="已激活";
                }
              else
                {
                    state="已删除";
                }
                getJPHListOutputDTO.setState(state);
                getJPHListOutputDTOList.add(getJPHListOutputDTO);
            }
        }
        return getJPHListOutputDTOList;
    }

    @Override
    public void addJPH(AddJPHInputDTO addJPHInputDTO) throws  NullPointerException {
      JPH  jph=new JPH();
      jph.setCreationDateTime(new Date());
      User user=hibernateTemplate.get(User.class,addJPHInputDTO.getCreatorId());
      jph.setCreator(user);
      jph.setEditDateTime(new Date());
      jph.setEditor(user);
      jph.setJPH(addJPHInputDTO.getJPH());
      jph.setLocation(hibernateTemplate.get(Location.class,addJPHInputDTO.getLocationId()));
      jph.setMaterial(materialDao.getMaterialByNumber(addJPHInputDTO.getMaterialNumber()));
      jph.setState(addJPHInputDTO.getState());
        jphDao.addJPH(jph);

    }

    @Transactional
    @Override
    public void updateJPH(EditJPHInputDTO editJPHInputDTO) throws  NullPointerException{
        JPH  jph=hibernateTemplate.get(JPH.class,editJPHInputDTO.getId());
        jph.setCreationDateTime(new Date());
        User user=hibernateTemplate.get(User.class,editJPHInputDTO.getEditorId());
        jph.setCreator(user);
        jph.setEditDateTime(new Date());
        jph.setEditor(user);
        jph.setJPH(editJPHInputDTO.getJPH());
        jph.setState(editJPHInputDTO.getState());
        jph.setId(editJPHInputDTO.getId());
        jphDao.updateJPH(jph);
    }

    @Override
    public JPH getJPHById(Integer jphId) {
    return  hibernateTemplate.get(JPH.class,jphId);
    }

    @Override
    @Transactional
    public void deleteJPH(Integer Id) {
    JPH  jph=hibernateTemplate.get(JPH.class,Id);
    jph.setState(-1);
     hibernateTemplate.update(jph);
    }

    @Override
    public JPHDetailOutPut getJPHDetailOutPutById(Integer Id) throws  NullPointerException{
        JPHDetailOutPut jphDetailOutPut=new JPHDetailOutPut();
        JPH jph=jphDao.getJPHById(Id);
        jphDetailOutPut.setCreationDateTime(jph.getCreationDateTime());
        jphDetailOutPut.setCreator(jph.getCreator().getUserName());
        jphDetailOutPut.setEditDateTime(jph.getEditDateTime());
        jphDetailOutPut.setId(jph.getId());
        jphDetailOutPut.setEditor(jph.getEditor().getUserName());
        jphDetailOutPut.setJPH(jph.getJPH());
        jphDetailOutPut.setLocationNumber(parseFieldToMapUtil.parseFiledToString(jph.getLocation().getLocationNumber(),
                jph.getLocation().getDescription()));
        jphDetailOutPut.setMaterialNumber(parseFieldToMapUtil.parseFiledToString(jph.getMaterial().getMaterialNumber(),
                jph.getMaterial().getDescription()));
        String state=null;
        if (jph.getState()==0)
        {
            state="未激活";
        }
        if (jph.getState()==1)
        {
            state="已激活";
        }
        else
        {
            state="已删除";
        }
        jphDetailOutPut.setState(state);
        return  jphDetailOutPut;
    }

    @Override
    public JPHUpdateOutPut getJPHUpdateOutPutDTP(Integer Id) {
        JPHUpdateOutPut jphUpdateOutPut=new JPHUpdateOutPut();
        JPH jph=hibernateTemplate.get(JPH.class,Id);
        jphUpdateOutPut.setJPH(jph.getJPH());
        List<Map<String,Object>> LocationList=materialDao.getLocationNumberAndId();
        jphUpdateOutPut.setLocationList(LocationList);
        jphUpdateOutPut.setMaterialNumber(jph.getMaterial().getMaterialNumber());
        jphUpdateOutPut.setLoactionId(jph.getLocation().getId().toString());
        jphUpdateOutPut.setState(jph.getState());
        jphUpdateOutPut.setId(jph.getId());
        return jphUpdateOutPut;
    }
}
