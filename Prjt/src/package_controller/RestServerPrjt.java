package package_controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import package_api.SuperUsersData;
import package_api.BaseUsersData;
import package_api.MainSpecData;
import package_api.ProductData;
import package_models.CreationModels;
import package_search.SearchProduct;
import package_specificationsEnums.AddCode;

@RestController
@SpringBootApplication
@ImportResource({"classpath:beans_orm.xml"})
public class RestServerPrjt {
	private static final String CREATE_PRODUCT_POST = "/create/product/post";//for create one product record
	private static final String CREATE_MAINSPEC_POST = "/create/mainspec/post";//for create one product main spec record
	private static final String GET_PRODUCT_POST = "/get/product/post";//for get one product record
	private static final String GET_PRODUCTS_TABLE_POST = "/get/products/table/post";//for get all products records
	private static final String GET_PRODUCT_MAINSPEC_TABLE_POST = "/get/product/mainspec/table/post";//for get all main spec records of product
	private static final String CREATE_USER = "/create/user/post";
	private static final String CREATE_SUPER_USER = "/create/super/user/post";
	private static final String CONNECT_USER = "/connect/user/post";
	private static final String DISCONNECT_USER = "/disconnect/user/post";
	private static final String CHANGE_ROLE_ACCESS_USER = "/change/role/access/user/post";
	
	
	@Autowired
	CreationModels models;
	
	@RequestMapping(value=CREATE_PRODUCT_POST, method=RequestMethod.POST)
	public AddCode addProduct(@RequestBody ProductData productData){
		return models.addNewProduct(productData);	
	}
	
	@RequestMapping(value=GET_PRODUCT_POST, method=RequestMethod.POST)
	public ProductData getProduct(@RequestBody SearchProduct searchProduct){
		return models.getProduct(searchProduct);
	}
	
	@RequestMapping(value=GET_PRODUCTS_TABLE_POST, method=RequestMethod.POST)
	public List<ProductData> getProductsTable(){
		return models.getProductsTable();
	}
	
	@RequestMapping(value=GET_PRODUCT_MAINSPEC_TABLE_POST, method=RequestMethod.POST)
	public List<MainSpecData> getProductMainSpecTable(@RequestBody SearchProduct searchProduct){
		return models.getMainSpecTable(searchProduct);
	}
	
	@RequestMapping(value=CREATE_MAINSPEC_POST, method=RequestMethod.POST)
	public AddCode addMainSpec(@RequestBody MainSpecData mainSpecData){
		return models.addNewMainSpec(mainSpecData);	
	}
	
	@RequestMapping(value=CREATE_USER, method=RequestMethod.POST)
	public AddCode addUser(@RequestBody BaseUsersData baseUsersData){
		return models.addUser(baseUsersData);	
	}
	
	@RequestMapping(value=CREATE_SUPER_USER, method=RequestMethod.POST)
	public AddCode addSuperUser(@RequestBody SuperUsersData baseUsersData){
		return models.addSuperUser(baseUsersData);	
	}
	
	@RequestMapping(value=CONNECT_USER, method=RequestMethod.POST)
	public AddCode connectUser(@RequestBody BaseUsersData baseUsersData){
		return models.connectUser(baseUsersData);	
	}
	
	@RequestMapping(value=DISCONNECT_USER, method=RequestMethod.POST)
	public AddCode disconnectUser(@RequestBody BaseUsersData baseUsersData){
		return models.disconnectUser(baseUsersData);	
	}
	
	@RequestMapping(value=CHANGE_ROLE_ACCESS_USER, method=RequestMethod.POST)
	public AddCode changeRoleAccessUser(@RequestBody BaseUsersData baseUsersData){
		return models.changeUserAccessOrRole(baseUsersData);	
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RestServerPrjt.class, args);//for rest requests
		
		final Timer checkConnectionByTime = new Timer();//for create timer  
		
		//*** For  requests into server without REST
		AbstractApplicationContext ctx= new FileSystemXmlApplicationContext("beans.xml");
		CreationModels creationModels=ctx.getBean(CreationModels.class);
		//*******************************************************************************
				
		checkConnectionByTime.schedule(new TimerTask() {
            @Override
            public void run() {
            }
        }, 60000, 60000);
	}

}
