package ru.rustem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import ru.rustem.converter.ProductToProductDtoConverter;
import ru.rustem.dto.ProductDto;
import ru.rustem.model.Product;
import ru.rustem.model.UserRequest;
import ru.rustem.service.CategoryService;
import ru.rustem.service.ProductService;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PriceController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView("price");
        return modelAndView;
    }
    @PostMapping("/price")
    public ResponseEntity<List<ProductDto>> updateHosting(@RequestBody UserRequest userRequest, HttpServletResponse response) {
        System.out.println(userRequest);
        List<ProductDto> productDtos = null;
        if ((userRequest.getCategory().equals("")) && (userRequest.getProduct().equals("")) && (userRequest.getMin_price() == null) && (userRequest.getMax_price() == null)){
            return new ResponseEntity<List<ProductDto>>(productDtos, (MultiValueMap<String, String>) response, HttpStatus.BAD_REQUEST);
        } else {
            List<Product> products = productService.findAll();
            List<Product> products1 = productService.findByPriceGreaterThanEqual(300);
            List<Product> products2 = productService.findByPriceLessThanEqual(300);
            List<Product> products3 = productService.findByNameStartingWithIgnoreCase("hP");
            if (products != null) {
                productDtos = products.stream()
                        .map(ProductToProductDtoConverter::productToProductDtoConverter).collect(Collectors.toList());
                System.out.println(productDtos);
                return new ResponseEntity<List<ProductDto>>(productDtos, HttpStatus.OK);
            }
            return new ResponseEntity<List<ProductDto>>(productDtos, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
