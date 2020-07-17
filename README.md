# JournalTask

# Stack Used
- nodeJS
- MongoDB

# How To Run App
- install nodeJS and mongoDB
- go to directory JournalTask/backend
- npm install
- write this command in terminal "node app.js"
- make sure "Successfully connected to the database" is printed after you run

# URLs to test for Requirements
- List of articles sorted by most popular authors (who has the highest number of published articles).
    Using GET request: http://localhost:80/api/article/listArticlesSortedByMostPopularAuthors

- Create article.
    Using POST request: http://localhost:80/api/article/createArticle
    With BODY inputs: title, description, authorName

- Delete article. (accessed by Admin who is Samir)
    Using DELETE request: http://localhost:80/api/admin/deleteArticle
    With BODY inputs: title


- Approve article. (accessed by Admin who is Samir)
    Using PATCH request: http://localhost:80/api/admin/approveArticle
    With BODY inputs: title   

- When an author view all articles, they see their own articles first. 
    Using GET request: http://localhost:80/api/author/viewAllArticles/:authorName
        ex: http://localhost:80/api/author/viewAllArticles/engy
