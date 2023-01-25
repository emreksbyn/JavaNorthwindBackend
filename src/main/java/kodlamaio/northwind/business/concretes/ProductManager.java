package kodlamaio.northwind.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessDataResult;
import kodlamaio.northwind.core.utilities.results.SuccessResult;
import kodlamaio.northwind.dataAccess.abstracts.ProductDao;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

@Service
public class ProductManager implements ProductService {

	private ProductDao productDao;

	// IoC eklendi.
	@Autowired
	public ProductManager(ProductDao productDao) {
		super();
		this.productDao = productDao;
	}

	@Override
	public DataResult<List<Product>> getAll() {
		List<Product> products = this.productDao.findAll();
		return new SuccessDataResult<List<Product>>(products, "Ürünler başarı ile getirildi.");
	}

	@Override
	public Result add(Product product) {
		this.productDao.save(product);
		return new SuccessResult("Ürün eklendi.");
	}

	@Override
	public DataResult<Product> getByProductId(int productId) {
		Product product = this.productDao.getByProductId(productId);
		return new SuccessDataResult<Product>(product, "Ürün başarı ile getirildi.");
	}

	@Override
	public DataResult<Product> getByProductName(String productName) {
		Product product = this.productDao.getByProductName(productName);
		return new SuccessDataResult<Product>(product, "Ürün başarı ile getirildi.");
	}

	@Override
	public DataResult<Product> getByProductNameAndCategoryId(String productName, int categoryId) {
		Product product = this.productDao.getByProductNameAndCategory_CategoryId(productName, categoryId);
		return new SuccessDataResult<Product>(product, "Ürün başarı ile getirildi.");
	}

	@Override
	public DataResult<List<Product>> getByProductNameOrCategoryId(String productName, int categoryId) {
		List<Product> products = this.productDao.getByProductNameOrCategory_CategoryId(productName, categoryId);
		return new SuccessDataResult<List<Product>>(products, "Ürünler başarı ile getirildi.");
	}

	@Override
	public DataResult<List<Product>> getByCategoryIdIn(List<Integer> categories) {
		List<Product> products = this.productDao.getByCategory_CategoryIdIn(categories);
		return new SuccessDataResult<List<Product>>(products, "Ürünler başarı ile getirildi.");
	}

	@Override
	public DataResult<List<Product>> getByProductNameContains(String productName) {
		List<Product> products = this.productDao.getByProductNameContains(productName);
		return new SuccessDataResult<List<Product>>(products, "Ürünler başarı ile getirildi.");
	}

	@Override
	public DataResult<List<Product>> getByProductNameStartsWith(String productName) {
		List<Product> products = this.productDao.getByProductNameStartsWith(productName);
		return new SuccessDataResult<List<Product>>(products, "Ürünler başarı ile getirildi.");
	}

	@Override
	public DataResult<List<Product>> getByNameAndCategory(String productName, int categoryId) {
		List<Product> products = this.productDao.getByNameAndCategory(productName, categoryId);
		return new SuccessDataResult<List<Product>>(products, "Ürünler başarı ile getirildi.");
	}

	@Override
	public DataResult<List<Product>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		List<Product> products = this.productDao.findAll(pageable).getContent();
		return new SuccessDataResult<List<Product>>(products, "Ürünler sayfalandi.");
	}

	@Override
	public DataResult<List<Product>> getAllSorted() {
//		Sort sort = Sort.by(Sort.Order.desc("unitPrice"));
		Sort sort = Sort.by(Sort.Direction.DESC, "productName");
		List<Product> products = this.productDao.findAll(sort);
		return new SuccessDataResult<List<Product>>(products, "Ürünler sıralandı.");
	}

	@Override
	public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails() {
		List<ProductWithCategoryDto> productWithCategoryDtos = this.productDao.getProductWithCategoryDetails();
		return new SuccessDataResult<List<ProductWithCategoryDto>>(productWithCategoryDtos,
				"Ürünler kategorileriyle birlikte listelendi.");
	}
}