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
    cd JournalTask/backend
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
    $ export FLASK_APP=flaskr
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
    $ flask run
    ```

## Testing the project

- the `test_basic.py` was for test driven development. it's to test the endpoints before adding the authentication decorator

- the working test cases are included in a postman collection `test.postman_collection.json`

- import the collection in postman, run the server and run the collection
