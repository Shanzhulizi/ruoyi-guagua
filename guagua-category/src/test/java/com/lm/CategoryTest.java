package com.lm;

public class CategoryTest {

    //给出category模块的测试用例
    // 1. 测试CategoryService的addCategory方法
    // 2. 测试CategoryService的getCategory方法
    // 3. 测试CategoryService的deleteCategory方法
    // 4. 测试CategoryService的updateCategory方法
    // 5. 测试CategoryService的getAllCategories方法
    // 6. 测试CategoryService的getCategoriesByParentId方法

//    @Test
//    public void testAddCategory() {
//        // Arrange
//        CategoryService categoryService = new CategoryService();
//        Category category = new Category(1L, "Test Category", null);
//
//        // Act
//        categoryService.addCategory(category);
//
//        // Assert
//        Category fetchedCategory = categoryService.getCategory(1L);
//        assertEquals("Test Category", fetchedCategory.getName());
//    }
//    @Test
//    public void testGetCategory() {
//        // Arrange
//        CategoryService categoryService = new CategoryService();
//        Category category = new Category(1L, "Test Category", null);
//        categoryService.addCategory(category);
//
//        // Act
//        Category fetchedCategory = categoryService.getCategory(1L);
//
//        // Assert
//        assertEquals("Test Category", fetchedCategory.getName());
//    }
//    @Test
//    public void testDeleteCategory() {
//        // Arrange
//        CategoryService categoryService = new CategoryService();
//        Category category = new Category(1L, "Test Category", null);
//        categoryService.addCategory(category);
//
//        // Act
//        categoryService.deleteCategory(1L);
//
//        // Assert
//        Category fetchedCategory = categoryService.getCategory(1L);
//        assertNull(fetchedCategory);
//    }
//    @Test
//    public void testUpdateCategory() {
//        // Arrange
//        CategoryService categoryService = new CategoryService();
//        Category category = new Category(1L, "Test Category", null);
//        categoryService.addCategory(category);
//
//        // Act
//        category.setName("Updated Category");
//        categoryService.updateCategory(category);
//
//        // Assert
//        Category fetchedCategory = categoryService.getCategory(1L);
//        assertEquals("Updated Category", fetchedCategory.getName());
//    }
//    @Test
//    public void testGetAllCategories() {
//        // Arrange
//        CategoryService categoryService = new CategoryService();
//        Category category1 = new Category(1L, "Category 1", null);
//        Category category2 = new Category(2L, "Category 2", null);
//        categoryService.addCategory(category1);
//        categoryService.addCategory(category2);
//
//        // Act
//        List<Category> categories = categoryService.getAllCategories();
//
//        // Assert
//        assertEquals(2, categories.size());
//    }
//    @Test
//    public void testGetCategoriesByParentId() {
//        // Arrange
//        CategoryService categoryService = new CategoryService();
//        Category parentCategory = new Category(1L, "Parent Category", null);
//        categoryService.addCategory(parentCategory);
//
//        Category childCategory1 = new Category(2L, "Child Category 1", 1L);
//        Category childCategory2 = new Category(3L, "Child Category 2", 1L);
//        categoryService.addCategory(childCategory1);
//        categoryService.addCategory(childCategory2);
//
//        // Act
//        List<Category> childCategories = categoryService.getCategoriesByParentId(1L);
//
//        // Assert
//        assertEquals(2, childCategories.size());
//    }


}
