package com.app.JuberEats.controller;

import com.app.JuberEats.primitives.Result;
import com.app.JuberEats.primitives.ResultT;
import com.app.JuberEats.request.product.CreateProductRequest;
import com.app.JuberEats.response.product.GetProductResponse;
import com.app.JuberEats.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api")
@Tag(name = "Product", description = "Product API endpoints")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Operation(summary = "Get product", description = "Endpoint to get product")
    @GetMapping("/v1/public/product/{productId}")
    public ResponseEntity<ResultT<GetProductResponse>> getById(@PathVariable Long productId){
        GetProductResponse response = productService.getById(productId);

        return new ResponseEntity<>(new ResultT<>(response), HttpStatus.OK);
    }

    @Operation(summary = "Create product", description = "Endpoint to create product")
    @PostMapping("/admin/product")
    public ResponseEntity<Result> createProduct(@RequestBody CreateProductRequest request){
        productService.createProduct(request);

        return new ResponseEntity<>(Result.Success(), HttpStatus.OK);
    }
}
