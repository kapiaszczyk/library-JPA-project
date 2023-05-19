package dev.kapiaszczyk.bookstore.library.category;

public class CategoryStatisticsDTO {
    private Long id;
    private String categoryName;
    private Long bookCount;

    public CategoryStatisticsDTO(String categoryName, Long bookCount) {
        this.categoryName = categoryName;
        this.bookCount = bookCount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getBookCount() {
        return bookCount;
    }

    public void setBookCount(Long bookCount) {
        this.bookCount = bookCount;
    }
}
