package package_models;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import package_api.SuperUsersData;
import package_api.BaseUsersData;
import package_api.MainSpecData;
import package_api.ProductData;
import package_entities.BaseUsers;
import package_entities.MainSpec;
import package_entities.Product;
import package_search.SearchMainSpec;
import package_search.SearchProduct;
import package_search.SearchUser;
import package_specificationsEnums.AccessesNumbers;
import package_specificationsEnums.AddCode;
import package_specificationsEnums.ProductStatus;
import package_specificationsEnums.UsersRoles;


public class CreationModels {
	@PersistenceContext(unitName = "springHibernate")
	EntityManager em;
	private MainSpec findMainSpec(SearchMainSpec search) {
		//return em.find(MainSpec .class, search);
		@SuppressWarnings("unchecked")
		List<MainSpec> allMainSpec = em.createNativeQuery("SELECT*FROM mainspec", MainSpec.class).getResultList();
		for (int cnt = 0; cnt<allMainSpec.size(); cnt++){
			if ((allMainSpec.get(cnt).getUniqueTitleInside().equals(search.getUniqueTitleInside())==true)&&
								 (allMainSpec.get(cnt).getProductTitle().equals(search.getTitle())==true)){
				return allMainSpec.get(cnt);				
			}
		}
		return null;
	}
	private Product findProduct(SearchProduct search) {
		//return em.find(Product.class, search);
		@SuppressWarnings("unchecked")
		List<Product> allProducts = em.createNativeQuery("SELECT*FROM products", Product.class).getResultList();
		for(int cnt = 0; cnt<allProducts.size(); cnt++){
			if (allProducts.get(cnt).getUniqueTitleInside().equals(search.getUniqueTitleInside())==true){
				return allProducts.get(cnt);
			}
		}
		return null;
	}
	private BaseUsers findUser(SearchUser search) {
		//return em.find(BaseUsers .class, search);
		@SuppressWarnings("unchecked")
		List<BaseUsers> allBaseUsers = em.createNativeQuery("SELECT*FROM baseusers", BaseUsers.class).getResultList();
		for (int cnt = 0; cnt<allBaseUsers.size(); cnt++){
			if ((allBaseUsers.get(cnt).getUserName().equals(search.getUserName())==true)&&
						(allBaseUsers.get(cnt).getPass().equals(search.getPass())==true)){
				return allBaseUsers.get(cnt);
			}
		}
		return null;
	}
	
	@Transactional	
	public AddCode addNewProduct(ProductData productData){
		SearchUser searchUser = new SearchUser(productData.getUserName(),productData.getUserPass());
		if((findUser(searchUser)==null)){
			return AddCode.CAN_NOT_EXECUTE;
		}
		if (findUser(searchUser).getAccessRights(AccessesNumbers.CREATE_PRODUCT.ordinal())==false){
			return AddCode.USER_HAS_NOT_ACCESS;
		}
		SearchProduct searchProd = new SearchProduct(productData.getUniqueTitleInside());
		if((findProduct(searchProd)!=null)){
			return AddCode.ALREADY_EXISTS;
		}
		em.persist(new Product(productData.getUniqueTitleInside(),
				productData.getStatus(), productData.getRealCostPrice(),productData.getRealWorkingHours(), 
				LocalDate.parse(productData.getCreateDate()),LocalDate.parse(productData.getChangeDate()), productData.getGroups(), 
				productData.getQuantityInStock(), productData.getQuantityInOrder(),productData.getTargetCostPrice(), 
				productData.getTargetWorkingHours()));
		return AddCode.OK;
														
	}
	
	@Transactional
	public ProductData getProduct (SearchProduct searchProduct){
		SearchProduct searchProd = new SearchProduct(searchProduct.getUniqueTitleInside());
		Product  product = em.find(Product.class, searchProd);
		if (product ==  null){
			ProductData productData = new ProductData();
			return productData;
		}
		//*** for change product status when get product if status was changed ***********************************
		@SuppressWarnings("unchecked")
		List<MainSpec> allMainSpec = em.createNativeQuery("SELECT*FROM mainspec", MainSpec.class).getResultList();
		List<MainSpec> productsMainSpec = new ArrayList<MainSpec>();
		for (int cnt = 0; cnt<allMainSpec.size(); cnt++){
			if (allMainSpec.get(cnt).getProductTitle().equals(product.getUniqueTitleInside())==true){
				productsMainSpec.add(allMainSpec.get(cnt));
			}
		}
		if ((productsMainSpec != null)&&(productsMainSpec.size()>0)){
			ProductStatus comp = null;
			for (int cntStatus = 0; cntStatus<3; cntStatus++){
				ProductStatus tmp = null;
				switch(cntStatus){
				case 0:
					tmp = ProductStatus.ACTIV;
					break;
				case 1:
					tmp = ProductStatus.IN_DEVELOPMENT;
					break;
				case 2:
					tmp = ProductStatus.OBSOLETED;
					break;
				}
				for (int cnt = 0; cnt<productsMainSpec.size(); cnt++){
					if (productsMainSpec.get(cnt).getStatus()==tmp){
						comp = tmp;
						break;
					}
				}
				if (comp != null)break;
			}
			if (comp !=product.getStatus()){
				em.merge(new Product(product.getUniqueTitleInside(), comp, product.getRealCostPrice(), product.getRealWorkingHours(),
						product.getCreateDate(), product.getChangeDate(), product.getGroups(), product.getQuantityInStock(),
						product.getQuantityInOrder(), product.getTargetCostPrice(), product.getTargetWorkingHours()));
				product.setStatus(comp); 
			}
		}
		//***************************************************************************************************************************
		/*ProductData productData =*/return new ProductData(product.getUniqueTitleInside(), product.getStatus(), 
												product.getRealCostPrice(), product.getRealWorkingHours(),
												product.getCreateDate().toString(), product.getChangeDate().toString(),
												product.getGroups(), product.getQuantityInStock(), product.getQuantityInOrder(),
												product.getTargetCostPrice(), product.getTargetWorkingHours(), null, null);
		//return productData;
	}
	
	@Transactional
	public List<ProductData> getProductsTable(){
		@SuppressWarnings("unchecked")
		List<Product> allProducts = em.createNativeQuery("SELECT*FROM products", Product.class).getResultList();
		List<ProductData> results = new ArrayList<>();
		for(Product product:allProducts){
			results.add(new ProductData(product.getUniqueTitleInside(),product.getStatus(),product.getRealCostPrice(), 
					product.getRealWorkingHours(),product.getCreateDate().toString(),product.getChangeDate().toString(),
					product.getGroups(),product.getQuantityInStock(),product.getQuantityInOrder(),product.getTargetCostPrice(),
					product.getTargetWorkingHours(), null, null));
		}
		return results;
	}
	
	@Transactional
	public AddCode addNewMainSpec(MainSpecData mainSpecData){
		SearchMainSpec searchMainSpec = new SearchMainSpec(mainSpecData.getUniqueTitleInside(), mainSpecData.getProductTitle());
		if((findMainSpec(searchMainSpec)!=null)){
			return AddCode.ALREADY_EXISTS;
		}
		MainSpec mainSpec = new MainSpec(mainSpecData.getUniqueTitleInside(), mainSpecData.getProductTitle(),
				mainSpecData.getStatus(), mainSpecData.getRealCostPrice(), mainSpecData.getRealWorkingHours(), 
				LocalDate.parse(mainSpecData.getCreateDate()), LocalDate.parse(mainSpecData.getChangeDate()));
		em.persist(mainSpec);
		return AddCode.OK;
	}
	
	@Transactional
	public List<MainSpecData> getMainSpecTable(SearchProduct searchProduct){
		@SuppressWarnings("unchecked")
		List<MainSpec> allMainSpec = em.createNativeQuery("SELECT*FROM mainspec", MainSpec.class).getResultList();
		List<MainSpecData> result = new ArrayList<>(); 
		for (int cnt=0; cnt<allMainSpec.size(); cnt++){
			if (allMainSpec.get(cnt).getProductTitle().equals(searchProduct.getUniqueTitleInside())==true){
				result.add(new MainSpecData(allMainSpec.get(cnt).getUniqueTitleInside(),
						allMainSpec.get(cnt).getProductTitle(),allMainSpec.get(cnt).getStatus(),
						allMainSpec.get(cnt).getRealCostPrice(), allMainSpec.get(cnt).getRealWorkingHours(),
						allMainSpec.get(cnt).getCreateDate().toString(), allMainSpec.get(cnt).getChangeDate().toString()));
			}
		}
		return result;
	}
	
	/*@Transactional
	public AddCode changeMainSpec(){
		
	}*/
	
	@Transactional
	public AddCode addUser(BaseUsersData baseUserData){
		@SuppressWarnings("unchecked")
		List<BaseUsers> allBaseUsers = em.createNativeQuery("SELECT*FROM baseusers", BaseUsers.class).getResultList();
		for(int cnt = 0; cnt<allBaseUsers.size(); cnt++){
			if (allBaseUsers.get(cnt).getRole().equals(UsersRoles.SUPER_USER)==true){
				if (allBaseUsers.get(cnt).getUserName().equals(baseUserData.getSuperUserName())==false){
					return AddCode.CAN_NOT_EXECUTE;
				}
				break;
			}
		}
		SearchUser searchUser = new SearchUser(baseUserData.getUserName(),baseUserData.getPass());
		if((findUser(searchUser)!=null)){
			return AddCode.ALREADY_EXISTS;
		}
		em.persist(new BaseUsers(baseUserData.getUserName(), baseUserData.getPass(), baseUserData.getAccessRights(),
				baseUserData.getRole(), LocalDate.parse(baseUserData.getCreateTime()), LocalDate.parse(baseUserData.getChangeTime())));
		return AddCode.OK;
	}
	
	@Transactional
	public AddCode addSuperUser(SuperUsersData baseUserData){
		@SuppressWarnings("unchecked")
		List<BaseUsers> allBaseUsers = em.createNativeQuery("SELECT*FROM baseusers", BaseUsers.class).getResultList();
		SearchUser searchUser = new SearchUser(baseUserData.getUserName(),baseUserData.getPass());
		if((findUser(searchUser)!=null)){
			return AddCode.ALREADY_EXISTS;
		}
		if (baseUserData.getRole().equals(UsersRoles.SUPER_USER)==false){
			return AddCode.INVALIDE_PARAMETER;
		}
		for (int cnt=0; cnt<allBaseUsers.size(); cnt++){
			if (allBaseUsers.get(cnt).getRole().equals(baseUserData.getRole())==true){
				return AddCode.SUPER_USER_EXIST;
			}
		}
		em.persist(new BaseUsers(baseUserData.getUserName(), baseUserData.getPass(), 0xFFFFFFFF,baseUserData.getRole(), 
						LocalDate.parse(baseUserData.getCreateTime()), LocalDate.parse(baseUserData.getChangeTime())));
		return AddCode.OK;
	}

		
	@Transactional
	public AddCode connectUser(BaseUsersData baseUserData){
		
		SearchUser searchUser = new SearchUser(baseUserData.getUserName(),baseUserData.getPass());
		if((findUser(searchUser)==null)){
			return AddCode.INVALIDE_PARAMETER;
		}		
		return AddCode.OK;
	}
	
	@Transactional
	public AddCode disconnectUser(BaseUsersData baseUserData){
		SearchUser searchUser = new SearchUser(baseUserData.getUserName(),baseUserData.getPass());
		if((findUser(searchUser)==null)){
			return AddCode.INVALIDE_PARAMETER;
		}
		return AddCode.OK;
	}
	
	@Transactional
	public AddCode changeUserAccessOrRole(BaseUsersData baseUserData){
		@SuppressWarnings("unchecked")
		List<BaseUsers> allBaseUsers = em.createNativeQuery("SELECT*FROM baseusers", BaseUsers.class).getResultList();
		for(int cnt = 0; cnt<allBaseUsers.size(); cnt++){
			if (allBaseUsers.get(cnt).getRole().equals(UsersRoles.SUPER_USER)==true){
				if (allBaseUsers.get(cnt).getUserName().equals(baseUserData.getSuperUserName())==false){
					return AddCode.CAN_NOT_EXECUTE;
				}
				break;
			}
		}
		SearchUser searchUser = new SearchUser(baseUserData.getUserName(),baseUserData.getPass());
		if((findUser(searchUser)==null)){
			return AddCode.NO_OBJECT;
		}
		if (baseUserData.getChangeTime()==null){
			return AddCode.INVALIDE_PARAMETER;
		}
		em.merge(new BaseUsers(baseUserData.getUserName(), baseUserData.getPass(), baseUserData.getAccessRights(),
				baseUserData.getRole()==null?findUser(searchUser).getRole():baseUserData.getRole(), findUser(searchUser).getCreateTime(),
				LocalDate.parse(baseUserData.getChangeTime())));
		return AddCode.OK;
	}
	
	@Transactional
	public AddCode keepAlive(BaseUsersData baseUserData){
		SearchUser searchUser = new SearchUser(baseUserData.getUserName(),baseUserData.getPass());
		if((findUser(searchUser)==null)){
			return AddCode.NO_OBJECT;
		}
		return AddCode.OK;
	}
}
