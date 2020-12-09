# Journal Blog

Journal Blog that manages to publish articles by authors, the project aims to have articles that apprroved and managed by admins.


# Stack 

- Language: Ruby 2.7.0
- Web Framework: Rails 5.2.4.4
- Storage: Sqllite3
- OS: ubuntu 20.04

# Framework Installation
 
 This project requires ruby on rails installed and functional inside an ubuntu machine to do that follow these steps:- 

 1 - Install RVM for ruby environment from [here][https://github.com/rvm/ubuntu_rvm]

 
 2 - Install rails 5.2.4.4


    gem install rails -v 5.2.4.4


 3 - Install bundler 


    gem install bundler -v 2.1.2

# Project installation 

 1 - Install required dependencies


    bundle install

 2 - Setup database


    rake db:setup


 3 - Migrate your database


    rake db:migrate


4 - Seed your admin/user to your database


    rake db:seed


5 - you will find an admin with email `samir@gmail.com` and password `mostafa96` use this account to approve and delete articles

# Start/Stop Application

* To start application 


    ```
       rails s
    ```


* To stop application 


    ```
       CTRL + C
    ```

# Run Tests

* To run all tests 


    ```
       rspec
    ```



* To run certain test 


    ```
       rspec spec/features/<FILE NAME>.rb
    ```
