package package_controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import package_api.MainSpecData;
import package_api.ProductData;
import package_models.CreationModels;
import package_search.SearchProduct;
import package_specificationsEnums.ProductStatus;

public class TestBaseFunctionAppl {

	public static void main(String[] args){
		AbstractApplicationContext ctx= new FileSystemXmlApplicationContext("beans.xml");
		CreationModels creationModels=ctx.getBean(CreationModels.class);
		
		
		ProductData testProduct = new ProductData("uniqueTitleInside2",  ProductStatus.IN_DEVELOPMENT, 120,
				13, LocalDate.now().toString(), LocalDate.now().toString(), "Group2", 1200, 30, 120, 12, null, null);
		MainSpecData testMainSpec = new MainSpecData("mainSpecUniqueTitleInside2", "Title2",
				ProductStatus.IN_DEVELOPMENT, 123, 32, LocalDate.now().toString(), LocalDate.now().toString());
		SearchProduct searchProduct = new SearchProduct("ProductRest3");
		creationModels.addNewProduct(testProduct);
		creationModels.addNewMainSpec(testMainSpec);
		List<MainSpecData> list = new ArrayList<>();
		list = creationModels.getMainSpecTable(searchProduct);
		System.out.println(list.toString());
		ctx.close();
	}

}
