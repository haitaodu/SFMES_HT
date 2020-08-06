package com.smartflow.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.smartflow.model.CustomerInEditInitializeModel;
import com.smartflow.model.SupplierInEditInitializeModel;
import com.smartflow.model.SupplierListDTO;
import com.smartflow.model.SupplierModel;
import com.smartflow.service.SupplierInterfaceService;
import com.smartflow.util.CustomerReadData;
import com.smartflow.util.ReadDataUtil;
import com.smartflow.util.SupplierReadData;
@Controller
@RequestMapping("/api/Supplier")
public class SupplierInformationController extends BaseController{
	private static Logger logger = Logger.getLogger(SupplierInformationController.class);
	@Autowired
	SupplierInterfaceService supplierService;

	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/Post",method=RequestMethod.POST)
	public @ResponseBody Object addDataForsupplier(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		//解析request为Json格式

		Map<String, Object> json = new HashMap<String, Object>();
		JSONObject jsonObject = ReadDataUtil.paramData(request);
		String suppliercode = jsonObject.get("SupplierCode") == null ? "" : String.valueOf(jsonObject.get("SupplierCode"));
		String name=jsonObject.get("Name")==null?"":String.valueOf(jsonObject.get("Name"));
		String country=jsonObject.get("Country")==null?"":String.valueOf(jsonObject.get("Country"));
		String duns=jsonObject.get("DUNS")==null?"":String.valueOf(jsonObject.get("DUNS"));
		String province=jsonObject.get("Province")==null?"":String.valueOf(jsonObject.get("Province"));
		String address=jsonObject.get("Address")==null?"":String.valueOf(jsonObject.get("Address"));
		String postcode=jsonObject.get("PostCode")==null?"":String.valueOf(jsonObject.get("PostCode"));
		String fax=jsonObject.get("Fax")==null?"":String.valueOf(jsonObject.get("Fax"));
		String contactperson=jsonObject.get("ContactPerson")==null?"":String.valueOf(jsonObject.get("ContactPerson"));
		String email=jsonObject.get("Email")==null?"":String.valueOf(jsonObject.get("Email"));
		String telphone=jsonObject.get("Telphone")==null?"":String.valueOf(jsonObject.get("Telphone"));
		String mobilephone=jsonObject.get("MobilePhone")==null?"":String.valueOf(jsonObject.get("MobilePhone"));
		//String creatorid=jsonObject.get("CreatorId")==null?"":String.valueOf(jsonObject.get("CreatorId"));
		String  state=jsonObject.get("State")==null?"":String.valueOf(jsonObject.get("State"));
		Integer creatorId = jsonObject.getInteger("CreatorId");
		int State=Integer.parseInt(state);
		//int creatorId=Integer.parseInt(creatorid);
		//String creationdatetime=jsonObject.get("CreationDateTime")==null?"":String.valueOf(jsonObject.get("CreationDateTime"));
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//Date creationDateTime=sdf.parse(creationdatetime);
		SupplierModel  supplier=new SupplierModel();

		supplier.setAdress(address);
		supplier.setCode(suppliercode);
		supplier.setContactPerson(contactperson);
		supplier.setCountry(country);
		//supplier.setCreationDateTime(creationDateTime);
		//supplier.setCreatorId(creatorId);
		supplier.setState(State);
		supplier.setEmail(email);
		supplier.setFax(fax);
		supplier.setMobilePhone(mobilephone);
		supplier.setName(name);
		supplier.setPostCode(postcode);
		supplier.setProvince(province);
		supplier.setTelphone(telphone);
		supplier.setDUNS(duns);
		supplier.setCreationDateTime(new Date());
		supplier.setCreatorId(creatorId);
		supplier.setEditeDateTime(new Date());
		supplier.setEditorId(creatorId);
		if(suppliercode.length()>=40)
	{


		json= this.setJson(103, "该供应商编号过长无法注册", -1);
		return json;

	}
		if(supplierService.isRegisterSupplierNumber(suppliercode))
		{


			json= this.setJson(103, "该供应商编号已注册", -1);
			return json;

		}
		if (supplierService.getDataForCheckUnique(suppliercode, name)==0) {
			supplierService.addData(supplier);
		}
		else {
			json= this.setJson(200, "你插入的数据已存在", -1);
			return json;
		}

		try {
			json= this.setJson(200, "添加成功", 0);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}

	//测试用例
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public @ResponseBody String hello() {
		System.out.println("hello world");
		return "hello";
	}
	//根据ID找到顾客信息
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/GetTById/{Id}",method=RequestMethod.GET)
	public @ResponseBody Object getDataById(@PathVariable Integer Id)

	{
		Map<String , Object> json=new HashMap<String,Object>();
		Map<String, Object> map=new HashMap<String,Object>();
		SupplierReadData supplierReadData=supplierService.getDetailsById(Id);
		if (supplierReadData.getSupplierName()==null) {
			json=this.setJson(101, "无法找到您需要的数据", 0);
			return json;

		}
		map.put("Id", supplierReadData.getSupplierId());
		map.put("SupplierCode", supplierReadData.getSupplierCode());
		map.put("Name", supplierReadData.getSupplierName());
		map.put("BUNS",supplierReadData.getDUNS());
		map.put("Country",supplierReadData.getSupplierCountry());
		map.put("Province", supplierReadData.getSupplierProvince());
		map.put("Address", supplierReadData.getSupplierAddress());
		map.put("PostCode", supplierReadData.getSupplierPostCode());
		map.put("Fax", supplierReadData.getSupplierFax());
		map.put("ContactPerson", supplierReadData.getSupplierContactPerson());
		map.put("Email", supplierReadData.getSupplierEmail());
		map.put("Telphone", supplierReadData.getSupplierTelphone());
		//map.put("MobilePhone", supplierReadData.());
		map.put("MobilePhone", supplierReadData.getSupplierMobilePhone());
		map.put("Editor", supplierReadData.getSupplierEditorName());
		map.put("EditorDateTime", supplierReadData.getSupplierEditeDateTime());
		map.put("Creator", supplierReadData.getSupplierCreatorName());
		map.put("CreationDateTime", supplierReadData.getSupplierCreationDateTime());
		String state = null;
		if (supplierReadData.getSupplierState()==1) {
			state = "激活";
		}else if (supplierReadData.getSupplierState()==0) {
			state = "未激活";
		}else{
			state = "已删除";
		}
		map.put("State", state);

		try {
			json= this.setJson(200, "查询成功", map);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;

	}
	//根据Id找到需要编辑的初始化数据
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/GetEditInitialize/{Id}",method=RequestMethod.GET)
	public  @ResponseBody Object getEditInitialize(@PathVariable Integer Id)
	{
		Map<String , Object> json=new HashMap<String,Object>();
		SupplierInEditInitializeModel model = supplierService.getEditInitializeData(Id);
		if (model==null) {
			json=this.setJson(102, "您需要的数据暂且没有", 0);
			return json;			
		}
		SupplierReadData supplierReadData=supplierService.getDetailsById(Id);
		Map<String, Object> supplierData=new HashMap<String,Object>();
		supplierData.put("Id",model.getId() );
		supplierData.put("SupplierCode", model.getCode());
		supplierData.put("Name", model.getName());
		supplierData.put("DUNS", model.getDuns());
		supplierData.put("Country", model.getCountry());
		supplierData.put("Province", model.getProvince());
		supplierData.put("Address", model.getAdress());
		supplierData.put("PostCode", model.getPostcode());
		supplierData.put("Fax", model.getFax());
		supplierData.put("ContactPerson", model.getContactperson());
		supplierData.put("Email", model.getEmal());
		supplierData.put("Telphone", model.getTelphone());
		supplierData.put("MobilePhone", model.getMobilephone());

		supplierData.put("EditDateTime", model.getEditdatetime());
		supplierData.put("State", model.getState());
		if (model.getEditor()!=null) {
			supplierData.put("Editor", supplierReadData.getSupplierEditorName());

		}
		else
		{
			supplierData.put("Editor", null);
		}
		try {
			json= this.setJson(200, "编辑初始化成功", supplierData);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}
	//根据区域Id删除相应的信息
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/Delete",method=RequestMethod.POST)
	public  @ResponseBody Object deleteData(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Map<String , Object> json=new HashMap<String,Object>();
		/*
		if (supplierService.getDataById(Id)==null) {
			json=this.setJson(202, "你所删除的数据不存在", 0);
			return json;
		}
		supplierService.delData(Id);
		做假删除操作
		 */
		JSONObject jsonObject = ReadDataUtil.paramData(request);
		@SuppressWarnings("unchecked")
		List<Integer> list=(List<Integer>)  jsonObject.get("List");
		
		if (list.size()==0) {

			json=this.setJson(204, "未选择删除数据", 1);
			return(json);
		}
		for (Integer Id : list) {
		if (supplierService.getDataById(Id)==null) {
			json=this.setJson(202, "所删除数据为空", 0);
			return(json);
		}
		else if (supplierService.getDataById(Id).getState()==-1) {
			json=this.setJson(203, "该数据已删除", -1);
			return(json);
		}
		supplierService.delData(Id);
		}
		try {
			json= this.setJson(200, "删除成功", 0);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;

	}
	//在编辑信息后更改数据库的数据
	@SuppressWarnings("unused")
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/Put",method=RequestMethod.PUT)
	public @ResponseBody Object updateData(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		System.out.println("2222222222222222");
		Map<String , Object> json=new HashMap<String,Object>();
		JSONObject jsonObject = ReadDataUtil.paramData(request);
		String id=jsonObject.get("Id")==null?"":String.valueOf(jsonObject.get("Id"));
		String suppliercode = jsonObject.get("SupplierCode") == null ? "" : String.valueOf(jsonObject.get("SupplierCode"));
		String name=jsonObject.get("Name")==null?"":String.valueOf(jsonObject.get("Name"));
		String country=jsonObject.get("Country")==null?"":String.valueOf(jsonObject.get("Country"));
		String duns=jsonObject.get("DUNS")==null?"":String.valueOf(jsonObject.get("DUNS"));
		String province=jsonObject.get("Province")==null?"":String.valueOf(jsonObject.get("Province"));
		String address=jsonObject.get("Address")==null?"":String.valueOf(jsonObject.get("Address"));
		String postcode=jsonObject.get("PostCode")==null?"":String.valueOf(jsonObject.get("PostCode"));
		String fax=jsonObject.get("Fax")==null?"":String.valueOf(jsonObject.get("Fax"));
		String contactperson=jsonObject.get("ContactPerson")==null?"":String.valueOf(jsonObject.get("ContactPerson"));
		String email=jsonObject.get("Email")==null?"":String.valueOf(jsonObject.get("Email"));
		String telphone=jsonObject.get("Telphone")==null?"":String.valueOf(jsonObject.get("Telphone"));
		String mobilephone=jsonObject.get("MobilePhone")==null?"":String.valueOf(jsonObject.get("MobilePhone"));
		//临时改成固定值10
		//String editorid=jsonObject.get("EditorId")==null?"":String.valueOf(jsonObject.get("EditorId"));
		Date  editDateTime=new Date();
		Integer state=Integer.parseInt(jsonObject.get("State")==null?"":String.valueOf(jsonObject.get("State")));

		Integer idInteger=Integer.parseInt(id);
		SupplierModel  supplier=supplierService.getDataById(idInteger);
		if(!state.equals(""))
		{
			supplier.setState(state);
		}
		Integer editorId = jsonObject.getInteger("EditorId");
		supplier.setAdress(address);
		supplier.setCode(suppliercode);
		supplier.setContactPerson(contactperson);
		supplier.setCountry(country);
		supplier.setEditeDateTime(editDateTime);
		supplier.setEditorId(editorId);
		supplier.setEmail(email);
		supplier.setFax(fax);
		supplier.setMobilePhone(mobilephone);
		supplier.setName(name);
		supplier.setPostCode(postcode);
		supplier.setProvince(province);
		supplier.setTelphone(telphone);
		supplier.setDUNS(duns);


		supplier.setId(idInteger);
		if(suppliercode.length()>=40)
		{


			json= this.setJson(103, "该供应商编号过长无法注册", -1);
			return json;

		}
		if(supplierService.isRegisterSupplierNumber(suppliercode))
		{


			json= this.setJson(103, "该供应商编号已注册", -1);
			return json;

		}
		if(supplierService.getDataById(idInteger)==null)
		{
			json=this.setJson(201, "所修改数据不存在", 1);
			return json;
		}
		supplierService.upDateData(supplier);

		try {
			json= this.setJson(200, "修改成功", 0);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/GetTByCondition",method=RequestMethod.POST)
	public @ResponseBody Object readPageData(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		JSONObject jsonObject=ReadDataUtil.paramData(request);
		Map<String, Object> map=new HashMap<>();
		int pageSize=jsonObject.getIntValue("PageSize");
		int pageNumber=jsonObject.getIntValue("PageIndex");
		String supplierCode;
		String supplierName;
		if (jsonObject.get("SupplierCode")==null) {
			supplierCode="";	
		}
		else {
			supplierCode=jsonObject.get("SupplierCode").toString();

		}
		if (jsonObject.get("SupplierName")==null) {
			supplierName="";
		}
		else {
			supplierName=jsonObject.get("SupplierName").toString();
		}

		Map<String, Object> json=new HashMap<String,Object>();
		if (pageNumber==0) {
			map.put("RowCount", 0);
			map.put("Tdto", new ArrayList<>());
			/*
			map.put("PageSize", pageSize);
			map.put("PageIndex", pageNumber);
			 */
			json= this.setJson(200, "无数据", map);
			return(json);
		}
		List<SupplierListDTO> pageData= supplierService.getPageData(pageNumber, pageSize,supplierCode,supplierName);
		map.put("RowCount", supplierService.getRowCount(supplierCode,supplierName));
		map.put("Tdto", pageData);
		if (pageData.isEmpty()==true) {
			json= this.setJson(200, "您搜索的数据不存在", map);
			return json;
		}
		try {
			json= this.setJson(200, "查询成功", map);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;

	}

}


