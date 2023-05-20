package dev.kapiaszczyk.bookstore.library.credit;

public class CreditDTO {

    private Long id;
    private Long authorId;
    private Long bookId;

    public CreditDTO() {
    }

    public CreditDTO(Long id, Long authorId, Long bookId) {
        this.id = id;
        this.authorId = authorId;
        this.bookId = bookId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

}
