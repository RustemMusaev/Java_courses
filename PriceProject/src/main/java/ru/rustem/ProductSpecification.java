package ru.rustem;

import org.springframework.data.jpa.domain.Specification;
import ru.rustem.dto.ProductDto;
import ru.rustem.model.Product;
import ru.rustem.model.UserRequest;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class ProductSpecification implements Specification<Product> {
    private UserRequest userRequest;

    public ProductSpecification(UserRequest userRequest) {
        this.userRequest = userRequest;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        if (!userRequest.getMin_price().equals(null)) {
            return builder.greaterThanOrEqualTo(
                    root.getModel().getName() get(price), userRequest.getMin_price());
        }
        else if (userRequest.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
                    root.<String> get(userRequest.getKey()), userRequest.getValue().toString());
        }
        else if (userRequest.getOperation().equalsIgnoreCase(":")) {
            if (root.get(userRequest.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.<String>get(userRequest.getKey()), "%" + userRequest.getValue() + "%");
            } else {
                return builder.equal(root.get(userRequest.getKey()), userRequest.getValue());
            }
        }

        return null;
    }
}
