package com.lm;

import org.junit.jupiter.api.Test;


public class CartTest {
//    //写出所有CartService中的方法的测试用例
//    // 1. 测试添加商品到购物车
//    @Test
//    public void testAddProductToCart() {
//        // Arrange
//        CartService cartService = new CartService();
//        Product product = new Product(1L, "Test Product", 100.0);
//        int quantity = 2;
//
//        // Act
//        cartService.addProductToCart(product, quantity);
//
//        // Assert
//        Cart cart = cartService.getCart();
//        assertEquals(1, cart.getItems().size());
//        assertEquals(200.0, cart.getTotalPrice(), 0.01);
//    }
//    // 2. 测试从购物车中移除商品
//    @Test
//    public void testRemoveProductFromCart() {
//        // Arrange
//        CartService cartService = new CartService();
//        Product product = new Product(1L, "Test Product", 100.0);
//        int quantity = 2;
//        cartService.addProductToCart(product, quantity);
//
//        // Act
//        cartService.removeProductFromCart(product);
//
//        // Assert
//        Cart cart = cartService.getCart();
//        assertEquals(0, cart.getItems().size());
//        assertEquals(0.0, cart.getTotalPrice(), 0.01);
//    }
//    // 3. 测试更新购物车中商品的数量
//    @Test
//    public void testUpdateProductQuantityInCart() {
//        // Arrange
//        CartService cartService = new CartService();
//        Product product = new Product(1L, "Test Product", 100.0);
//        int initialQuantity = 2;
//        cartService.addProductToCart(product, initialQuantity);
//
//        // Act
//        int newQuantity = 3;
//        cartService.updateProductQuantityInCart(product, newQuantity);
//
//        // Assert
//        Cart cart = cartService.getCart();
//        assertEquals(1, cart.getItems().size());
//        assertEquals(300.0, cart.getTotalPrice(), 0.01);
//    }
//    // 4. 测试清空购物车
//    @Test
//    public void testClearCart() {
//        // Arrange
//        CartService cartService = new CartService();
//        Product product1 = new Product(1L, "Test Product 1", 100.0);
//        Product product2 = new Product(2L, "Test Product 2", 200.0);
//        cartService.addProductToCart(product1, 2);
//        cartService.addProductToCart(product2, 1);
//
//        // Act
//        cartService.clearCart();
//
//        // Assert
//        Cart cart = cartService.getCart();
//        assertEquals(0, cart.getItems().size());
//        assertEquals(0.0, cart.getTotalPrice(), 0.01);
//    }
//
//    // 5. 测试获取购物车中的商品列表
//    @Test
//    public void testGetCartItems() {
//        // Arrange
//        CartService cartService = new CartService();
//        Product product1 = new Product(1L, "Test Product 1", 100.0);
//        Product product2 = new Product(2L, "Test Product 2", 200.0);
//        cartService.addProductToCart(product1, 2);
//        cartService.addProductToCart(product2, 1);
//
//        // Act
//        List<CartItem> items = cartService.getCartItems();
//
//        // Assert
//        assertEquals(2, items.size());
//        assertEquals(200.0, items.get(0).getTotalPrice(), 0.01);
//        assertEquals(200.0, items.get(1).getTotalPrice(), 0.01);
//    }
//    // 6. 测试获取购物车总价
//    @Test
//    public void testGetCartTotalPrice() {
//        // Arrange
//        CartService cartService = new CartService();
//        Product product1 = new Product(1L, "Test Product 1", 100.0);
//        Product product2 = new Product(2L, "Test Product 2", 200.0);
//        cartService.addProductToCart(product1, 2);
//        cartService.addProductToCart(product2, 1);
//
//        // Act
//        double totalPrice = cartService.getCartTotalPrice();
//
//        // Assert
//        assertEquals(400.0, totalPrice, 0.01);
//    }
//    // 7. 测试获取购物车中的商品数量
//    @Test
//    public void testGetCartItemCount() {
//        // Arrange
//        CartService cartService = new CartService();
//        Product product1 = new Product(1L, "Test Product 1", 100.0);
//        Product product2 = new Product(2L, "Test Product 2", 200.0);
//        cartService.addProductToCart(product1, 2);
//        cartService.addProductToCart(product2, 1);
//
//        // Act
//        int itemCount = cartService.getCartItemCount();
//
//        // Assert
//        assertEquals(3, itemCount);
//    }
//    // 8. 测试检查购物车是否为空
//    @Test
//    public void testIsCartEmpty() {
//        // Arrange
//        CartService cartService = new CartService();
//
//        // Act
//        boolean isEmpty = cartService.isCartEmpty();
//
//        // Assert
//        assertTrue(isEmpty);
//
//        // Add a product to the cart
//        Product product = new Product(1L, "Test Product", 100.0);
//        cartService.addProductToCart(product, 2);
//
//        // Check if the cart is empty again
//        isEmpty = cartService.isCartEmpty();
//        assertFalse(isEmpty);
//    }
//    // 9. 测试获取购物车的详细信息
//    @Test
//    public void testGetCartDetails() {
//        // Arrange
//        CartService cartService = new CartService();
//        Product product1 = new Product(1L, "Test Product 1", 100.0);
//        Product product2 = new Product(2L, "Test Product 2", 200.0);
//        cartService.addProductToCart(product1, 2);
//        cartService.addProductToCart(product2, 1);
//
//        // Act
//        CartDetails details = cartService.getCartDetails();
//
//        // Assert
//        assertEquals(2, details.getItems().size());
//        assertEquals(400.0, details.getTotalPrice(), 0.01);
//    }
//    // 10. 测试获取购物车的总商品数量
//    @Test
//    public void testGetCartTotalItemCount() {
//        // Arrange
//        CartService cartService = new CartService();
//        Product product1 = new Product(1L, "Test Product 1", 100.0);
//        Product product2 = new Product(2L, "Test Product 2", 200.0);
//        cartService.addProductToCart(product1, 2);
//        cartService.addProductToCart(product2, 1);
//
//        // Act
//        int totalItemCount = cartService.getCartTotalItemCount();
//
//        // Assert
//        assertEquals(3, totalItemCount);
//    }
//    // 11. 测试获取购物车的商品数量
//    @Test
//    public void testGetCartProductCount() {
//        // Arrange
//        CartService cartService = new CartService();
//        Product product1 = new Product(1L, "Test Product 1", 100.0);
//        Product product2 = new Product(2L, "Test Product 2", 200.0);
//        cartService.addProductToCart(product1, 2);
//        cartService.addProductToCart(product2, 1);
//
//        // Act
//        int productCount = cartService.getCartProductCount();
//
//        // Assert
//        assertEquals(2, productCount);
//    }
//    // 12. 测试获取购物车的商品总价
//    @Test
//    public void testGetCartTotalProductPrice() {
//        // Arrange
//        CartService cartService = new CartService();
//        Product product1 = new Product(1L, "Test Product 1", 100.0);
//        Product product2 = new Product(2L, "Test Product 2", 200.0);
//        cartService.addProductToCart(product1, 2);
//        cartService.addProductToCart(product2, 1);
//
//        // Act
//        double totalProductPrice = cartService.getCartTotalProductPrice();
//
//        // Assert
//        assertEquals(400.0, totalProductPrice, 0.01);
//    }
//    // 13. 测试获取购物车的商品列表
//    @Test
//    public void testGetCartProductList() {
//        // Arrange
//        CartService cartService = new CartService();
//        Product product1 = new Product(1L, "Test Product 1", 100.0);
//        Product product2 = new Product(2L, "Test Product 2", 200.0);
//        cartService.addProductToCart(product1, 2);
//        cartService.addProductToCart(product2, 1);
//
//        // Act
//        List<Product> productList = cartService.getCartProductList();
//
//        // Assert
//        assertEquals(2, productList.size());
//        assertTrue(productList.contains(product1));
//        assertTrue(productList.contains(product2));
//    }
//    // 14. 测试获取购物车的商品数量
//    @Test
//    public void testGetCartItemCount() {
//        // Arrange
//        CartService cartService = new CartService();
//        Product product1 = new Product(1L, "Test Product 1", 100.0);
//        Product product2 = new Product(2L, "Test Product 2", 200.0);
//        cartService.addProductToCart(product1, 2);
//        cartService.addProductToCart(product2, 1);
//
//        // Act
//        int itemCount = cartService.getCartItemCount();
//
//        // Assert
//        assertEquals(3, itemCount);
//    }
//    // 15. 测试获取购物车的商品总价
//    @Test
//    public void testGetCartTotalPrice() {
//        // Arrange
//        CartService cartService = new CartService();
//        Product product1 = new Product(1L, "Test Product 1", 100.0);
//        Product product2 = new Product(2L, "Test Product 2", 200.0);
//        cartService.addProductToCart(product1, 2);
//        cartService.addProductToCart(product2, 1);
//
//        // Act
//        double totalPrice = cartService.getCartTotalPrice();
//
//        // Assert
//        assertEquals(400.0, totalPrice, 0.01);
//    }



}
