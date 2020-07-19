# Manage Article

You can :

  + registration (email - password)
  + login 
  + AUth provided by JWT
  + create a article (title-description)
  + get user article
  + Only admin can approve/delete articles.
  + When an author view all articles, they see their own articles first

### Tech

Article uses a number of open source projects to work properly:

* [node.js] - evented I/O for the backend
* [Express] - fast node.js network app framework 
* [MongoDb] - NoSql DataBase , the most popular database for modern apps

And of course Article itself is open source with a [public repository][git-repo-url] on GitHub.

### Installation

Article requires [Node.js](https://nodejs.org/) v8+ to run.

Install the dependencies and devDependencies and start the server.

``` sh
$ git clone https://github.com/abdalahshaban/JournalTask.git
$ cd JournalTask
$ npm install -d
$ npm run start or node app.js
```

for run test 

``` sh
$ cd JournalTask
$ npm install 
$ npm run test
```

   [git-repo-url]: <https://github.com/abdalahshaban/JournalTask.git>
   [node.js]: <http://nodejs.org>
   [express]: <http://expressjs.com>
   [mongoDb]:<https://www.mongodb.com/>
