package ebaza.codejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ebaza.codejava.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
