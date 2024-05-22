package com.oop.rest;

import java.util.List;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oop.entity.Product;
import com.oop.service.ProductService;

@Component
public class ProductRest extends RouteBuilder {

	@Autowired
	ProductService service;

	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet");
		//For post operation
		rest("/insert")
		.post()
		.to("direct:product");
		from("direct:product")
		//.log("body contains:${body}")
		.process(exchange->{
			String str= exchange.getIn().getBody(String.class);
			ObjectMapper mapper = new ObjectMapper();
			Product product = mapper.readValue(str, Product.class);
			service.saveProduct(product);
			//System.out.println(product);
			exchange.getIn().setBody(str);
		})
		.log("body contains :${body}");
		
		
		
		//For get all operation
		
		rest("/getAll") 
		  .get("/")
		  .to("direct:products");
			from("direct:products")
			.process(exchange->{
						
				List<Product> allProduct = service.getAllProduct();
				exchange.getIn().setBody(allProduct.toString());
			})
		  .log("body contains :${body}");
	 
		//For delete by Id operation
		  rest("/delete") 
		  .delete("{id}").to("bean:productService?method=delete(${header.id})");
		  
		  //For get by Id operation
		  rest("/product") 
		  .get("{id}")
		  .to("bean:productService?method=getById(${header.id})");
		  
	 
}
	}
