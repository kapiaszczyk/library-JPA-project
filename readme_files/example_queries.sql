-- SQL queries from the the university project

-- Get unique book titles having "Green" w the title from a given inventory
SELECT DISTINCT book_title, book_id FROM book WHERE book_title LIKE '%Green%';

-- Get addresses, where third letter in the street name is "o" or "a".
SELECT * FROM address WHERE street LIKE '__a%' OR street LIKE '__o%';

-- Get city and city code
SELECT 
       c.city_name AS "City name",
       k.city_code_code AS "City code",
FROM   city c
       JOIN city_code k
       ON ( c.city_id = k.city_city_id )
ORDER BY city_name;

-- Get full address for all library users
SELECT usr.library_user_first_name
        || ' '
        || usr.library_user_last_name
        || ', '
        || adr.street
        || ', '
        || ct.city_name
        || ', '
        || k.city_code_code AS "user address"
FROM   address adr
       JOIN library_user usr
         ON ( usr.library_user_id = adr.library_user_id )
       JOIN city ct
         ON ( ct.city_id = adr.city_id )
       JOIN city_code k
         ON ( ct.city_id = k.city_city_id );

-- Get number of all books in the database
SELECT count(book_id) FROM book;

-- Get all books with the given title
SELECT book_title, COUNT(*)
FROM book
WHERE book_title LIKE '%Catcher%'
GROUP BY book_title;

-- Get book titles and amount of books by isbn
SELECT isbn_number, COUNT(*)
FROM isbn
WHERE book_id IN (SELECT book_id FROM book)
GROUP BY isbn_number;


-- Get all books with the given isbn
SELECT book_title, COUNT(*)
FROM book
WHERE book_id IN(select book_id from isbn where isbn_number = '986532935611') 
GROUP BY book_title;

-- Get all book titles ending with a letter 'e' for a category "Fantasy"
SELECT book_title, COUNT(*)
FROM book
WHERE category_id = (SELECT category_id from category where category_name = 'Fantasy')
GROUP BY book_title
HAVING book_title LIKE '%e';

-- Get all books along with categories
SELECT books.book_title AS "Book Title", cat.category_name AS "Category Name"
FROM   book books
       JOIN category cat
       ON ( cat.category_id = books.category_id )
ORDER BY book_title;

-- Get amount of books for a given author
SELECT COUNT(a.author_id), a.author_first_name, a.author_last_name
FROM author a
JOIN credit k
ON k.author_id = a.author_id
GROUP BY a.author_id, a.author_first_name, a.author_last_name;
