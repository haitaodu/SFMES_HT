package com.smartflow.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.smartflow.model.CustomerListDto;
import com.smartflow.model.CustomerModel;
import com.smartflow.service.CustomerInterfaceService;
import com.smartflow.util.CustomerReadData;
import com.smartflow.util.ReadDataUtil;
@Controller
@RequestMapping("/api/Customer")
public class CustomerInformationController extends BaseController{
	private static Logger logger = Logger.getLogger(CustomerInformationController.class);
	@Autowired
	CustomerInterfaceService customerService;
	@SuppressWarnings("unused")
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/Post",method=RequestMethod.POST)
	public @ResponseBody Object addDataForCustomer(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String, Object> json = new HashMap<String, Object>();
		//解析request为Json格式
		JSONObject jsonObject = ReadDataUtil.paramData(request);
		String customercode = jsonObject.get("CustomerCode") == null ? "" : String.valueOf(jsonObject.get("CustomerCode"));
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
		//按正常情况来讲，应当CreatorId是不能判为空的。但数据库的设计这个字段是可以为空暂且按Integer类型定义
		//测试时应该注意有CreatorId为空的测试案例
		//Integer creatorId=Integer.parseInt(creatorid);
		//String creationdatetime=jsonObject.get("CreationDateTime")==null?"":String.valueOf(jsonObject.get("CreationDateTime"));
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//Date creationDateTime=sdf.parse(creationdatetime);
		Integer state=Integer.parseInt(jsonObject.get("State")==null?"":String.valueOf(jsonObject.get("State")));
		Integer creatorId = jsonObject.getInteger("CreatorId");
		CustomerModel  customer=new CustomerModel();
		customer.setCustomerAdress(address);
		customer.setCustomerCode(customercode);
		customer.setCustomerContactPerson(contactperson);
		customer.setCustomerCountry(country);
		//customer.setCustomerCreationDateTime(creationDateTime);
		//customer.setCustomerCreatorId(creatorId);
		customer.setCustomerEmail(email);
		customer.setCustomerFax(fax);
		customer.setCustomerMobilePhone(mobilephone);
		customer.setCustomerName(name);
		customer.setCustomerPostCode(postcode);
		customer.setCustomerProvince(province);
		customer.setCustomerTelphone(telphone);
		customer.setDUNS(duns);
		customer.setCustomerState(state);
		customer.setCustomerCreationDateTime(new Date());
		customer.setCustomerCreatorId(creatorId);
		customer.setCustomerEditeDateTime(new Date());
		customer.setCustomerEditorId(creatorId);
		if(customercode.length()>=40)
		{


			json= this.setJson(103, "该客户编号过长无法注册", -1);
			return json;

		}
		if(customerService.isRegisterCustomerNumber(customercode))
		{


				json= this.setJson(103, "该客户编号已被注册", -1);
				return json;

		}

		if(customerService.isregisterdnus(duns))
		{


			json= this.setJson(103, "该邓氏码已被注册", -1);
			return json;

		}
		if (customerService.getDataForCheckUnique(customercode, name)==0) {
			customerService.addData(customer);
		}
		else {

			json= this.setJson(103, "你所插入的数据已存在", -1);
			return json;
		}

		try {
			json= this.setJson(200, "新增成功", 0);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}

	//测试用例
	@CrossOrigin(origins = "*",maxAge = 3600)
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
		CustomerReadData customerReadData=customerService.getDetailsById(Id);
		if (customerReadData.getCustomerName()==null) {
			json=this.setJson(101, "您所请求的数据为空", 0);
			return json;
		}
		map.put("Id", customerReadData.getCustomerId());
		map.put("CustomerCode", customerReadData.getCustomerCode());
		map.put("Name", customerReadData.getCustomerName());
		map.put("DUNS", customerReadData.getDUNS());
		map.put("Country", customerReadData.getCustomerCountry());
		map.put("Address", customerReadData.getCustomerAdress());
		map.put("PostCode", customerReadData.getCustomerPostCode());
		map.put("Fax", customerReadData.getCustomerFax());
		map.put("ContactPerson", customerReadData.getCustomerContactPerson());
		map.put("Email",customerReadData.getCustomerEmail() );
		map.put("Telphone", customerReadData.getCustomerTelphone());
		map.put("MobilePhone", customerReadData.getCustomerMobilePhone());
		map.put("Editor",customerReadData.getCustomerEditorName());
		map.put("EditDateTime", customerReadData.getCustomerEditeDateTime());
        map.put("Province", customerReadData.getCustomerProvince());
        map.put("Creator", customerReadData.getCustomerCreatorName());
        map.put("CreationDateTime", customerReadData.getCustomerCreationDateTime());
		String state = null;
		if (customerReadData.getCustomerState()==1) {
			state = "激活";
		}else if (customerReadData.getCustomerState()==0) {
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
	public  @ResponseBody Object getEditInitialize( @PathVariable Integer  Id)
	{
		Map<String , Object> json=new HashMap<String,Object>();
		CustomerInEditInitializeModel model = customerService.getEditInitializeData(Id);
		if (model==null) {
			json=this.setJson(102, "您需要的数据暂且没有", 0);
			return json;

		}
		CustomerReadData customerReadData=customerService.getDetailsById(Id);
		Map<String, Object> customerData=new HashMap<String,Object>();
		customerData.put("Id",model.getId() );
		customerData.put("CustomerCode", model.getCode());
		customerData.put("Name", model.getName());
		customerData.put("DUNS", model.getDuns());
		customerData.put("Country", model.getCountry());
		customerData.put("Province", model.getProvince());
		customerData.put("Address", model.getAdress());
		customerData.put("PostCode", model.getPostcode());
		customerData.put("Fax", model.getFax());
		customerData.put("ContactPerson", model.getContactperson());
		customerData.put("Email", model.getEmail());
		customerData.put("Telphone", model.getTelphone());
		customerData.put("MobilePhone", model.getMobilephone());

		customerData.put("EditDateTime", model.getEditdatetime());
		customerData.put("State", model.getState());
		if (model.getEditorId()!=null) {
			customerData.put("Editor", customerReadData.getCustomerEditorName());

		}
		else
		{
			customerData.put("Editor", null);
		}
		try {
			json= this.setJson(200, "编辑初始化成功", customerData);
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
	public  @ResponseBody Object deleteData(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String , Object> json=new HashMap<String,Object>();
		/*
		if (customerService.getDataById(Id)==null) {
			json=this.setJson(202, "所删除数据为空", 0);
			return(json);
		}
		customerService.delData(Id);
		做假删除操作
		 */
		JSONObject jsonObject = ReadDataUtil.paramData(request);
	    @SuppressWarnings("unchecked")
		List<Integer> list=(List<Integer>)  jsonObject.get("List");
	    
	    
	   
	    System.out.println(list.size());
	    if (list.size()==0) {
	    	
	    	json=this.setJson(204, "未选择删除数据", 1);
			return(json);
		}
	    for (Integer Id : list) {
	    	if (customerService.getDataById(Id)==null) {
				json=this.setJson(202, "所删除数据为空", 1);
				return(json);
			}
			else if (customerService.getDataById(Id).getCustomerState()==-1) {
				json=this.setJson(203, "该数据已删除", -1);
				return(json);
			}
			customerService.delData(Id);
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
		Map<String , Object> json=new HashMap<String,Object>();
		JSONObject jsonObject = ReadDataUtil.paramData(request);
		String id=jsonObject.get("Id")==null?"":String.valueOf(jsonObject.get("Id"));
		String customercode = jsonObject.get("CustomerCode") == null ? "" : String.valueOf(jsonObject.get("CustomerCode"));
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
//		String editorid=jsonObject.get("EditorId")==null?"":String.valueOf(jsonObject.get("EditorId"));
		String editdatetime=jsonObject.get("EditDateTime")==null?"":String.valueOf(jsonObject.get("EditDateTime"));
		Integer state=Integer.parseInt(jsonObject.get("State")==null?"":String.valueOf(jsonObject.get("State")));
		Integer editorId = jsonObject.getInteger("EditorId");
		if(customercode.length()>=40)
		{


			json= this.setJson(103, "该客户编号过长无法注册", -1);
			return json;

		}
		if(customerService.isRegisterCustomerNumber(customercode))
		{


			json= this.setJson(103, "该客户编号已被注册", -1);
			return json;

		}

		if(customerService.isregisterdnus(duns))
		{


			json= this.setJson(103, "该邓氏码已被注册", -1);
			return json;

		}
		Integer idInteger=Integer.parseInt(id);
		
		CustomerModel  customer=customerService.getDataById(idInteger);
		customer.setCustomerAdress(address);
		customer.setCustomerCode(customercode);
		customer.setCustomerContactPerson(contactperson);
		customer.setCustomerCountry(country);
		customer.setCustomerEditeDateTime(new Date());
		if(!state.equals(""))
		{
		customer.setCustomerState(state);
		}
		
		//此操作为更改操作，按理说EditorId不应为空，但是数据库的允许为空，所以暂且设为Integer型
		customer.setCustomerEditorId(editorId);
		customer.setCustomerEmail(email);
		customer.setCustomerFax(fax);
		customer.setCustomerMobilePhone(mobilephone);
		customer.setCustomerName(name);
		customer.setCustomerPostCode(postcode);
		customer.setCustomerProvince(province);
		customer.setCustomerTelphone(telphone);
		customer.setDUNS(duns);
	
		//customer.setCustomerCreationDateTime(customerService.getDataById(idInteger).getCustomerCreationDateTime());
		//customer.setCustomerCreatorId(customerService.getDataById(idInteger).getCustomerCreatorId());
		
			customerService.upDateData(customer);
	
		try {
			json= this.setJson(200, "修改成功", 1);
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
		Map<String, Object> json=new HashMap<String,Object>();
		Map<String, Object> map=new HashMap<String,Object>();
		
		int pageSize=jsonObject.getIntValue("PageSize");
		int pageNumber=jsonObject.getIntValue("PageIndex");
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
		String customerCode;
		String customerName;
		if (jsonObject.get("CustomerCode")==null) {
		customerCode="";	
		}
		else {
			 customerCode=jsonObject.get("CustomerCode").toString();
			
		}
		if (jsonObject.get("CustomerName")==null) {
			customerName="";
		}
		else {
			 customerName=jsonObject.get("CustomerName").toString();
		}

	
		List<CustomerListDto>pageData= customerService.getPageData(pageNumber, pageSize,customerCode,customerName);
		
		map.put("RowCount", customerService.getRowCount(customerCode,customerName));
		map.put("Tdto", pageData);
		/*
		map.put("PageSize", pageSize);
		map.put("PageIndex", pageNumber);
       */		
		try {
			json= this.setJson(200, "查询成功", map);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;

	}
	//以下代码为测试前端给出的数据转化为Model型数据的测试案例
	/*
	@RequestMapping(value="/GetTestForAdd",method=RequestMethod.POST)
	public @ResponseBody Object addDataForTest(@RequestBody CustomerModel customerModel)
	{
		customerService.addData(customerModel);
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			json= this.setJson(200, "Success", 0);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}
	 */
}


