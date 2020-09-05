package com.smartflow.service;

import com.smartflow.dao.*;
import com.smartflow.dto.idmode.ModelEditInitialize;
import com.smartflow.dto.idmode.ModelIdConditionInputDTO;
import com.smartflow.model.IdLayoutEntity;
import com.smartflow.model.IdModelEntity;
import com.smartflow.model.UserModel;
import com.smartflow.util.global.PageUtil;
import com.smartflow.view.idmodel.IdModelDetailView;
import com.smartflow.view.idmodel.IdModelPageView;
import com.smartflow.view.idmodel.IdModelSaveView;
import com.smartflow.view.idmodel.IdModelUpdateView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.awt.util.IdentityArrayList;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author ：tao
 * @date ：Created in 2020/6/11 14:07
 * @description：${description}
 */
@Service
public class IdModelServiceImpl implements  IdModelService{
    private final
    StationDao stationDao;
    private final
    IdModelDao idModelDao;
    private final
    UserDao userDao;
    private final
    IdLayOutDao idLayOutDao;
    private  static String serialcode1="1";
    private  static String serialcode2="01";
    private  static String serialcode3="001";
    private  static String serialcode4="0001";
    private  static String serialcode5="00001";
    private  static String serialcode6="000001";
    private  static String serialcode7="0000001";
    private  static String serialcode8="00000001";
    private  static String serialcode9="000000001";

    @Autowired
    public IdModelServiceImpl(IdModelDao idModelDao,
                              UserDao userDao,IdLayOutDao idLayOutDao,
    StationDao stationDao,BOMHeadDao bomHeadDao,MaterialDao materialDao) {
        this.userDao=userDao;
        this.idModelDao = idModelDao;
        this.idLayOutDao=idLayOutDao;
        this.stationDao=stationDao;
    }


    /**
     * 分页查询业务层，关联表IdLayout,增加判断当前日期是否为生效日期区间中
     * @param modelIdConditionInputDTO 前端传入数据
     * @return 返回分页查询结果
     */
    @Override
    public List<IdModelPageView> getPage(ModelIdConditionInputDTO modelIdConditionInputDTO) {
        List<IdModelPageView> idModelPageViews=new IdentityArrayList<>();
        List<IdModelEntity> idModelEntities=idModelDao.getIdModelPageSearch(modelIdConditionInputDTO);
        for (IdModelEntity idModelEntity:idModelEntities)
        {
               idModelPageViews.add(parseToPageView(idModelEntity));
        }
        return idModelPageViews;
    }

    /**
     * 假删除，业务层面，根据id取出数据，然后将state改为-1
     * 将更改后的数据添加到数据库中
     * @param id id号
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void del(Integer id) {
     IdModelEntity idModelEntity=idModelDao.getEntityById(id);
     idModelEntity.setState(-1);
     idModelDao.save(idModelEntity);
    }

    /**
     * 保存数据
     * @param idModelSaveView 前端视图
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(IdModelSaveView idModelSaveView) {
        IdModelEntity idModelEntity=parsSaveViewToEntity(idModelSaveView);
        idModelDao.save(idModelEntity);
        saveIdLayout(idModelEntity.getId(),idModelSaveView.getSupplierCode(),1);
        saveIdLayout(idModelEntity.getId(),idModelSaveView.getPartCode(),2);
        saveIdLayout(idModelEntity.getId(),idModelSaveView.getSupplierSequenceCode(),3);
        saveIdLayout(idModelEntity.getId(),idModelSaveView.getSupplierSelfCode(),4);
        saveIdLayout(idModelEntity.getId(),
                gengerateCode(idModelSaveView.isTimeStamp(),
                        idModelSaveView.getNumberSufiix()),
                5);
    }

    /**
     *
     * @param idUpdateView 前端的更新视图
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(IdModelUpdateView idUpdateView) {
    IdModelEntity idModelEntity=parsUpdateViewToEntity(idUpdateView);
    idModelDao.update(idModelEntity);
    List<IdLayoutEntity>idLayoutEntities=idModelDao.
            getByIdModelId(idUpdateView.getId());
    for (IdLayoutEntity idLayoutEntity:idLayoutEntities)
    {
      idLayOutDao.del(idLayoutEntity);
    }
        saveIdLayout(idModelEntity.getId(),
                idUpdateView.getSupplierCode(),1);
        saveIdLayout(idModelEntity.getId(),idUpdateView.getPartCode(),2);
        saveIdLayout(idModelEntity.getId(),idUpdateView.getSupplierSequenceCode(),3);
        saveIdLayout(idModelEntity.getId(),idUpdateView.getSupplierSelfCode(),4);
        saveIdLayout(idModelEntity.getId(),
                gengerateCode(idUpdateView.isTimeStamp(),
                        idUpdateView.getNumberSufiix()),5);

    }

    /**
     * 详情页面业务层
     * @param id id
     * @return 返回详情数据
     */
    @Override
    public IdModelDetailView getDetail(int id) {
        IdModelEntity idModelEntity=idModelDao.getEntityById(id);
        return  parseToIdModelDetail(idModelEntity);

    }

    @Override
    public Integer getCount(ModelIdConditionInputDTO modelIdConditionInputDTO) {
        return idModelDao.getTotalEntity(modelIdConditionInputDTO);
    }

    @Override
    public ModelEditInitialize getModelEditInit(int id) {
        IdModelEntity idModelEntity=idModelDao.getEntityById(id);
        ModelEditInitialize modelEditInitialize=new ModelEditInitialize();
        modelEditInitialize.setId(id);
        modelEditInitialize.setModelCode(idModelEntity.getModelCode());
        modelEditInitialize.setModelName(idModelEntity.getModelName());
        List<IdLayoutEntity> idLayoutEntities=idModelDao.getByIdModelId(id);
        modelEditInitialize.setSupplierCode(idLayoutEntities.get(0).getValue());
        modelEditInitialize.setPartCode(idLayoutEntities.get(1).getValue());
        modelEditInitialize.setSupplierSequenceCode(idLayoutEntities.get(2).getValue());
        modelEditInitialize.setSupplierSelfCode(idLayoutEntities.get(3).getValue());
        modelEditInitialize.setVaildFrom(idModelEntity.getValidFrom());
        modelEditInitialize.setVaildTo(idModelEntity.getValidTo());
        modelEditInitialize.setState(Integer.toString(idModelEntity.getState()));
        modelEditInitialize.setNumberSufiix(idModelEntity.getNumberSuffix());
        modelEditInitialize.setTimeStamp(idModelEntity.isTimeStamp());
        modelEditInitialize.setBomhead(idModelEntity.getProductName());
        modelEditInitialize.setStationId(Integer.toString(idModelEntity.getStationId()));
        return modelEditInitialize;
    }

    /**
     * 根据LayOut表中的值拼接成序列号
     * @param idLayoutEntities LayOut表中的值
     * @return 返回拼接好的LayOut
     */
    private  String getValue( List<IdLayoutEntity> idLayoutEntities)
    {
        StringBuilder value=new StringBuilder();
        for (IdLayoutEntity idLayoutEntity:idLayoutEntities)
        {
            value.append(idLayoutEntity.getValue());
        }
        return value.toString();
    }

    /**
     * 将idModelEntity修饰为可供前端是使用的View形式
     * @param idModelEntity idModelEntity
     * @return 返回view
     */
    private  IdModelDetailView parseToIdModelDetail(IdModelEntity idModelEntity)
    {
        IdModelDetailView idModelDetailView=new IdModelDetailView();
        if (idModelEntity!=null) {
            UserModel creator=userDao.getDataById(idModelEntity.getCreatorId());
            UserModel editor=userDao.getDataById(idModelEntity.getEditorId());
            List<IdLayoutEntity> idLayoutEntities=idModelDao.getByIdModelId(idModelEntity.getId());
            idModelDetailView.setEditDateTime(idModelEntity.getEditDateTime());
            idModelDetailView.setCreationDateTime(idModelEntity.getCreationDateTime());
            idModelDetailView.setEditDateTime(idModelEntity.getEditDateTime());
            idModelDetailView.setCreator(creator.getName());
            idModelDetailView.setEditor(editor.getName());
            idModelDetailView.setId(idModelEntity.getId());
            idModelDetailView.setState(PageUtil.paseState(idModelEntity.getState()));
            idModelDetailView.setValidFrom(idModelEntity.getValidFrom());
            idModelDetailView.setValidTo(idModelEntity.getValidTo());
            idModelDetailView.setValue(getValue(idLayoutEntities));
            idModelDetailView.setModelCode(idModelEntity.getModelCode());
            idModelDetailView.setModelName(idModelEntity.getModelName());
            String stationNumber=stationDao.getStationById(idModelEntity.getStationId()).getStationNumber();
            idModelDetailView.setMaterialNumber(idModelEntity.getProductName());
            idModelDetailView.setStationNumber(stationNumber);
            idModelDetailView.setTimeStamp(parseToTrueFalse(idModelEntity.isTimeStamp()));
            idModelDetailView.setNumberSuffix(idModelEntity.getNumberSuffix());
            return idModelDetailView;
        }
    return null;
    }

    /**
     * 判断idModelEntity的数据在不在生效范围内，若不在的化不显示给前端
     * @param idModelEntity idModelEntity数据
     * @return 是否是可生效的数据
     */
    private boolean isOutTime(IdModelEntity idModelEntity)
    {
        Date date=new Date();
        Date fromDate=new Date(idModelEntity.getValidFrom().getTime());
        Date toDate=new Date(idModelEntity.getValidTo().getTime());
        return date.after(fromDate) && date.before(toDate);
    }

    /**
     * 将前端的数据转换为可保存的数据库实体
     * @param idModelSaveView 前端数据
     * @return 返回数据库可保存的实体
     */
    private IdModelEntity parsSaveViewToEntity(IdModelSaveView idModelSaveView)
    {
     IdModelEntity idModelEntity=parsStaticViewToEntity(new IdModelEntity());
     parseDateToTimestamp
             (idModelEntity, idModelSaveView.getVaildFrom(),
                     idModelSaveView.getVaildTo());
     idModelEntity.setCreatorId(idModelSaveView.getCreatorId());
     idModelEntity.setEditorId(idModelSaveView.getCreatorId());
     idModelEntity.setModelCode(idModelSaveView.getModelCode());
     idModelEntity.setModelName(idModelSaveView.getModelName());
     idModelEntity.setState(idModelSaveView.getState());
     idModelEntity.setProductName(idModelSaveView.getBomhead());
     idModelEntity.setStationId(idModelSaveView.getStationId());
     idModelEntity.setTimeStamp(idModelSaveView.isTimeStamp());
     idModelEntity.setNumberSuffix(idModelSaveView.getNumberSufiix());
     return idModelEntity;
    }

    /**
     * 将更新实体的视图转化为可保存的数据库实体
     * @param idModelUpdateView 前端更新的视图
     * @return 返回可保存到数据库实体
     */
    private IdModelEntity parsUpdateViewToEntity(IdModelUpdateView idModelUpdateView)
    {
        IdModelEntity idModelEntity=idModelDao.getEntityById
                (idModelUpdateView.getId());
        parseDateToTimestamp(idModelEntity,
                idModelUpdateView.getVaildFrom(),
                idModelUpdateView.getVaildTo());
        idModelEntity.setEditorId(idModelUpdateView.getEditorId());
        idModelEntity.setModelCode(idModelUpdateView.getModelCode());
        idModelEntity.setModelName(idModelUpdateView.getModelName());
        idModelEntity.setId(idModelUpdateView.getId());
        idModelEntity.setState(idModelUpdateView.getState());
        idModelEntity.setProductName(idModelUpdateView.getBomhead());
        idModelEntity.setStationId(idModelUpdateView.getStationId());
        idModelEntity.setTimeStamp(idModelUpdateView.isTimeStamp());
        idModelEntity.setNumberSuffix(idModelUpdateView.getNumberSufiix());
        return idModelEntity;
    }

    /**
     *将Date转化为Timestamp用于数据库存储
     * @param idModelEntity 实体
     * @param vaildFrom2 生效时间
     * @param vaildTo2 失效时间
     */
    private void parseDateToTimestamp(IdModelEntity idModelEntity, Date vaildFrom2, Date vaildTo2) {
        long vaildFrom= vaildFrom2.getTime();
        long vaildTo= vaildTo2.getTime();
        idModelEntity.setValidFrom(new Timestamp(vaildFrom));
        idModelEntity.setValidTo(new Timestamp(vaildTo));
    }

    /**
     * 把写死的数据写入idModelEntity，这个是更新和保存的通用行为，抽出来单独写个函数
     * @param idModelEntity 实体
     * @return 返回实体
     */
    private IdModelEntity parsStaticViewToEntity(IdModelEntity idModelEntity)
    {
        idModelEntity.setCounterFrom(1);
        idModelEntity.setState(1);
        idModelEntity.setCounterIdFormatType(1);
        idModelEntity.setCounterTo(1);
        idModelEntity.setCounterManagementType(1);
         Date date=new Date();
        idModelEntity.setCreationDateTime(new Timestamp(date.getTime()));
        idModelEntity.setEditDateTime(new Timestamp(date.getTime()));
        idModelEntity.setIncrement(1);
        idModelEntity.setResetCounterType(1);
        idModelEntity.setStationRelated(true);
        return idModelEntity;
    }

    /**
     * 保存IdLayout
     * @param idModelId idModelId
     * @param value 生成的序列号码
     * @param sequence 序列号的排序
     */
    private void  saveIdLayout(int idModelId,String value,int sequence)
    {
        IdLayoutEntity idLayoutEntity=new IdLayoutEntity();
        idLayoutEntity.setAllowSpecialCharacters(true);
        idLayoutEntity.setFillCharacter("#");
        idLayoutEntity.setFillPrefixOrSuffix(false);
        idLayoutEntity.setIdModelId(idModelId);
        idLayoutEntity.setMaxLength(10);
        idLayoutEntity.setMinLength(10);
        idLayoutEntity.setSequence(sequence);
        idLayoutEntity.setType(4);
        idLayoutEntity.setValue(value);
        idLayOutDao.save(idLayoutEntity);
    }

    /**
     * 生成的日期序列号，序列号会在每一天初始化为‘0001’;
     * @return 返回序列号
     */
    private static String gengerateCode(boolean isTimeStamp,int numberSufiix)
    {
       StringBuilder stringBuilder=new StringBuilder();
       if (isTimeStamp)
       {
           Date date=new Date();
           String year=String.format("%tY", date);
           String mon=String .format("%tm", date);
           String day=String .format("%td", date);
           stringBuilder.append(year);
           stringBuilder.append(mon);
           stringBuilder.append(day);
       }
       switch (numberSufiix)
       {
           case 1: stringBuilder.append(serialcode1);
           parseToString(serialcode1,1);
                    break;
           case 2:stringBuilder.append(serialcode2);
               parseToString(serialcode2,2);
                    break;
           case 3:stringBuilder.append(serialcode3);
               parseToString(serialcode3,3);
                    break;
           case 4:stringBuilder.append(serialcode4);
               parseToString(serialcode4,4);
                   break;
           case 5:stringBuilder.append(serialcode5);
               parseToString(serialcode5,5);
                   break;
           case 6:stringBuilder.append(serialcode6);
               parseToString(serialcode6,6);
                   break;
           case 7:stringBuilder.append(serialcode7);
               parseToString(serialcode7,7);
                   break;
           case 8:stringBuilder.append(serialcode8);
               parseToString(serialcode8,8);
                   break;
           case 9:stringBuilder.append(serialcode9);
               parseToString(serialcode9,9);
                  break;
            default:
       }

       return stringBuilder.toString();
    }

    private  IdModelPageView parseToPageView(IdModelEntity idModelEntity)
    {
        IdModelPageView idModelPageView=new IdModelPageView();
        idModelPageView.setId(idModelEntity.getId());
        idModelPageView.setModelCode(idModelEntity.getModelCode());
        idModelPageView.setModelName(idModelEntity.getModelName());
        List<IdLayoutEntity> idLayoutEntities=idModelDao.getByIdModelId(idModelEntity.getId());
        idModelPageView.setSupplierCode(idLayoutEntities.get(0).getValue());
        idModelPageView.setPartCode(idLayoutEntities.get(1).getValue());
        idModelPageView.setSupplierSequenceCode(idLayoutEntities.get(2).getValue());
        idModelPageView.setSupplierSelfCode(idLayoutEntities.get(3).getValue());
        idModelPageView.setVaildFrom(idModelEntity.getValidFrom());
        idModelPageView.setVaildTo(idModelEntity.getValidTo());
        idModelPageView.setState(PageUtil.paseState(idModelEntity.getState()));
        idModelPageView.setMaterialNumber(idModelEntity.getProductName());
        String stationNumber=stationDao.getStationById(idModelEntity.getStationId()).getStationNumber();
        idModelPageView.setStationNumber(stationNumber);
        idModelPageView.setIsTimeStamp(parseToTrueFalse(idModelEntity.isTimeStamp()));
        idModelPageView.setNumberSuffix(idModelEntity.getNumberSuffix());
        return  idModelPageView;
    }

    /**
     * 填充前缀为"0"
     * @param originString 原始数据
     * @param number 填充"0"的个数
     * @return 返回填充后的数据
     */
    private  static String prefixChar(String originString,int number)
    {
        for(int i=0;i<number;i++)
        {
            originString.startsWith("0");
        }
        return  originString;
    }

    /**
     *
     * @param arg 数字字符串
     * @param numberSize 总的打标数字长度
     * @return 返回填充后的打标字符串
     */
    private static String parseToString(String arg,int numberSize)
    {
        int number=Integer.valueOf(arg).intValue();
        number++;
        String numberString=String.valueOf(number);
        int prefixNumber=numberSize-numberString.length();
        return prefixChar(numberString,prefixNumber);
    }


    /**
     * 将boolean类型的转化为“是”或者“否”
     * @param arg boolen参数
     * @return 返回是或者否
     */
    private String  parseToTrueFalse(Boolean arg)
    {
        if (Boolean.TRUE.equals(arg))
        {
            return "是";
        }
        else {
            return "否";
        }
    }
}
