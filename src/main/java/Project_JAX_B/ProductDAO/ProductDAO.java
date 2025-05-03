package Project_JAX_B.ProductDAO;

import Project_JAX_B.ProductEntity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product,Long> {
}
