package Project_JAX_B.ProductService;

import Project_JAX_B.ProductDAO.ProductDAO;
import Project_JAX_B.ProductEntity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductDAO productService;

   public Product addAll(Product product){
        return productService.save(product);
    }

    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    public Optional<Product> getProductById(Long id){
       return productService.findById(id);
    }

   public Product updateById(Long id, Product product) {
        return productService.findById(id).map(product1 -> {
            product1.setName(product.getName());
            product1.setPrice(product.getPrice());
            product1.setDate(product.getDate());
            return productService.save(product1);
        }).orElse(null);

    }
        public void deleteById(Long id){
         productService.deleteById(id);

        }
}
