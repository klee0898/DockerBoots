package com.mbc.day05.controller;

import com.mbc.day05.domain.Admin;
import com.mbc.day05.domain.Product;
import com.mbc.day05.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    // 필드 주입
    @Autowired
    private ProductService productService;

    // 생성자 주입
//    private final ProductService productService;
//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }

    // 사용자 상품리스트
    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product/product-list";
    }

    // 관리자 상품리스트
    @GetMapping("/admin-products")
    public String listAdminProducts(Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("admin", admin);

        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/dashboard";
    }


//    @GetMapping("/form")
//    public String productForm(Model model) {
//        model.addAttribute("product", new Product());
//        return "product/product-form";
//    }

    // 상품 저장
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }
        productService.saveProduct(product);
        return "redirect:/products/admin-products";
    }

    // 상품 수정
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("admin", admin);
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);

        //return "product/product-form";
        return "admin/dashboard";
    }

    // 상품 삭제
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, HttpSession session, Model model) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("admin", admin);
        productService.deleteProduct(id);
        return "redirect:/products/admin-products";
    }

    // 사용자 상품 상세보기
    @GetMapping("/{id}")
    public String userViewProductDetail(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product/product-detail";
    }
    // 관리자 상품 상세보기
    @GetMapping("/admin/{id}")
    public String viewProductDetail(@PathVariable Long id, Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("admin", admin);
        return "admin/dashboard";
    }

    // 상품 폼추가
    @GetMapping("/form")
    public String productForm(Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("admin", admin);
        model.addAttribute("product", new Product());
//        return "product/product-form";
        return "admin/dashboard";
    }



}
