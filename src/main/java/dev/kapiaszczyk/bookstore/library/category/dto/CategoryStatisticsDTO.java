package dev.kapiaszczyk.bookstore.library.category.dto;

public class CategoryStatisticsDTO {
    private Long id;
    private String name;
    private Long bookCount;

    public CategoryStatisticsDTO(String name, Long bookCount) {
        this.name = name;
        this.bookCount = bookCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBookCount() {
        return bookCount;
    }

    public void setBookCount(Long bookCount) {
        this.bookCount = bookCount;
    }
}
