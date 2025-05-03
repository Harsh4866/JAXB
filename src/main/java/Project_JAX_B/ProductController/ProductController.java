package Project_JAX_B.ProductController;

import Project_JAX_B.ProductEntity.Product;
import Project_JAX_B.ProductService.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<Product> saveAll(@RequestBody Product product) {
        Product product1 = productService.addAll(product);
        return ResponseEntity.ok(product1);

    }

    @GetMapping
   public ResponseEntity< List<Product>> getAll() {
        List<Product> allProducts = productService.getAllProducts();
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/product/{id}")
    ResponseEntity<Product> getById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
@DeleteMapping("/{id}")
    ResponseEntity<Void> deleteBYId(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> getById1(@PathVariable Long id, @RequestBody Product product) {
        Product updateProduct = productService.updateById(id, product);
        return updateProduct != null ? ResponseEntity.ok(updateProduct) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/Xml")
    ResponseEntity<String> getAndConvertXml(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Product.class);
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                StringWriter writer = new StringWriter();
                marshaller.marshal(product.get(), writer);
                return ResponseEntity.ok(writer.toString());
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body("Error converting to XML");
            }
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
