package com.spring.controller.client.nonapi;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.dto.ProductDTO;
import com.spring.model.Cart;
import com.spring.model.ProductModel;
import com.spring.model.UserModel;
import com.spring.service.IProductService;
import com.spring.utils.SessionUtil;

@Controller
public class CartController {
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@RequestMapping(value = {"/xem-gio-hang"}, method = RequestMethod.GET)
	public String getCartPage(Model model, HttpServletRequest request) {
		return "client/cart";
	}
	
	
	@RequestMapping(value = {"/them-vao-gio"})
	  @ResponseBody
	  public Collection<ProductModel> addToCart(Model model, HttpServletRequest request, @RequestParam(value = "id") Long id, @RequestParam(value = "qty", required = false) int qty) {
		ProductDTO dto = productService.findOneById(id);
	 	ProductModel product = modelMapper.map(dto, ProductModel.class);
	 	
	 	Cart cart = (Cart) SessionUtil.getInstance().getValue(request, "Cart");
		
			if(qty != 0) {
				if (cart == null) cart = new Cart();
				if (product != null) {
					cart.put(product, qty);
				}
			}
		SessionUtil.getInstance().putValue(request, "Cart", cart);
		cart.updateTotal();
		return cart.getList();
	  }
	
	@RequestMapping(value = {"/cap-nhat-gio"})
	  @ResponseBody
	  public Collection<ProductModel> updateCart(Model model, HttpServletRequest request, @RequestParam(value = "id") Long id, @RequestParam(value = "qty", required = false) int qty) {
		ProductDTO dto = productService.findOneById(id);
	 	ProductModel product = modelMapper.map(dto, ProductModel.class);
	 	
	 	Cart cart = (Cart) SessionUtil.getInstance().getValue(request, "Cart");
		
	 	if(qty != 0) {
			if (cart == null)
				cart = new Cart();
			if (product != null) {
				cart.getProduct(product.getId()).updateQtyInCart(-cart.getProduct(product.getId()).getQtyInCart());
				
				cart.put(product, qty);
			}
		}
		SessionUtil.getInstance().putValue(request, "Cart", cart);
		cart.updateTotal();
		return cart.getList();
	  }
	
	@RequestMapping(value = {"/xoa-khoi-gio"})
	  @ResponseBody
	  public Collection<ProductModel> remmoveProductInCart(Model model, HttpServletRequest request, @RequestParam(value = "id") Long id) {
		
	 	Cart cart = (Cart) SessionUtil.getInstance().getValue(request, "Cart");
		
	 	if (cart == null)
	 		cart = new Cart();
	 	cart.remove(id);
		SessionUtil.getInstance().putValue(request, "Cart", cart);
		cart.updateTotal();
		return cart.getList();
	  }
	
	@RequestMapping(value = {"/thanh-toan"}, method = RequestMethod.GET)
	  public String getCheckoutPage(Model model, HttpServletRequest request) {
		UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "UserModel");
		if(user == null) return "redirect:/xem-gio-hang";
		return "client/checkout";
	  }
}
