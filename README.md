# JournalTask

## Dependencies for Running Locally

- python3

- virtual environment

- PostgreSQL

## Starting the Project

the commands associated with the steps are for linux (ubuntu) users

1. [Clone](https://help.github.com/en/articles/cloning-a-repository) the [project repository]() to your machine.

    `git clone <repo url>`

2. create a virtual env for the project

    ```
    $ python3 -m venv venv
    $ source venv/bin/activate    
    ```
3. install the project requierments
    
    ```
    $ pip3 install -r requierments
    ```

4. create a database for the project

    ```
    $ sudo -u postgres -i
    $ createdb <database_name>
    $ exit
    ```
5. upgrade the database schema with the migeration
    
    ```
    flask db upgrade
    ```

6. load a dump data into the database

    ```
    $ sudo -u postgres -i
    $ cd <project directory>/JOURNALTASK/flaskr
    $ psql <database_name> < schema.psql
    ```
7. run the flask server
    
    ```
    $ export FLASK_APP=flaskr
    $ flask run
    ```

---
# Description
- Fork the repo to your Github https://help.github.com/en/articles/fork-a-repo.
- Push your work to your forked repo "user-name/JournalTask".
- Create pull request in the original repo "devsquads/JournalTask"

# Story
Samir the chief editor in “legen- wait for it-dary news” asked the journal owner Yehia to make him an appointment with DevSquads their technical partner to ask them for help.
He wants to make an app that would help him manage the articles that being posted by the journalists, Samir is the only one that can delete articles, and approve the articles to be published, every article has a title, description, and author name.


# Requirements
- List of articles sorted by most popular authors ( who has the highest number of published articles).
- Create article.
- Delete article.
- Only Samir can approve/delete articles.
- When an author view all articles, they see their own articles first. 
- Edit README to include the required steps to run your application.

# Stack
- Choosing the tech stack is up to you.

# Nice to have
- Organized and well written git commit history.
- unit and e2e tests

# Task Deadline
- Tuesday Morning 28/07/2020.
