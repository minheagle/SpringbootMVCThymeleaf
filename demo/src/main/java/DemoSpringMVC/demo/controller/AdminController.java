package DemoSpringMVC.demo.controller;

import DemoSpringMVC.demo.domain.product.CreateProduct;
import DemoSpringMVC.demo.service.category.ICategoryService;
import DemoSpringMVC.demo.service.file.IFileService;
import DemoSpringMVC.demo.service.product.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IFileService fileService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IProductService productService;

    @GetMapping("")
    public String adminPage(){
        return "admin/home";
    }

    @GetMapping("/products")
    public String productManagement(Model model){
        model.addAttribute("products", productService.getAll());
        return "admin/product/product-list";
    }

    @GetMapping("/products/create")
    public String createProductForm(Model model){
        model.addAttribute("categoryList", categoryService.getAllLeaf());
        model.addAttribute("newProduct", new CreateProduct());
        return "admin/product/create-product";
    }

    @PostMapping("/products/create")
    public String createProduct(@ModelAttribute("newProduct") @Valid CreateProduct newProduct ,
                                BindingResult bindingResult,
                                @RequestParam("category") Long categoryId ,
                                Model model,
                                RedirectAttributes redirect){
        if(bindingResult.hasErrors()){
            return "admin/product/create-product";
        }
        try{
            productService.create(newProduct, categoryId);
            return "redirect:/admin/products";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "admin/product/create-product";
        }
    }

    @GetMapping("/categories")
    public String listCategoryPage(){
        return "admin/category/category-list";
    }
}
