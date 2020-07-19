# JournalTask



# Project Description and Structure

The Application consists of 5 main pages:

- Login page.             => It is the Landing page of the application that ensure the authorization of users before entering the application core.
- Articles page           => This page contains only the published articles .
- Add Article page      => In this page users can add their article and wait for approval to be published.
- Dashboard page     => This page contains all articles and it’s status (published or unpublished) ordered by the signed user first then,
			         the most popular artist ( who has the highest number of published articles)..
5- Error page              => This page will be loaded automatically if user navigate to a wrong url .


The project is built using the following technologies:

1- Exppress.js  - node.js  Framework -  (back-end) 
2- Nuxt.js - javascript framework - (front-end)
3- Firebase (database)

Project files consists of two main folders 

1- journal-system (front-end)
2- server (back-end)

# Installation and Running

After cloning project files:

1- Navigate to  “ journal-system ” folder 

- To install dependencies, run command =>  npm install
- To start application, run command =>  npm run dev
- Application will be running on localhost:3000

2- Navigate to  “ server ” folder  (navigate in new terminal)

- To install dependencies, run command =>  npm install
- To start application, run command =>  nodemon app.js
- Application will be running on localhost:8080

# Users Data 

1- Samir (Admin)

- Email: samir@gmail.com
- Password: samir1234

2- Ali (User)

- Email: ali@gmail.com
- Password: ali1234

3- Menna (User)

- Email: menna@gmail.com
- Password: menna1234

# Test Cases Scenarios 

1- Sign in with ( Admin Account ) 

- you will see all pages available to navigate.
- you will see your articles sorted firstly in dashboard
- you will have the access to publish / delete articles from database in the dashboard page.

2- Sign in with ( User Account ) 

- you will see all pages available to navigate.
- you will see your articles sorted firstly in dashboard
- you will NOT have the access to publish / delete articles from database in the dashboard page. (ACTIONS COLUMNS WILL BE HIDDEN)

=======================================================================================

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
- Edit README to include the reqired steps to run your application.

# Stack
- Choosing the tech stack is up to you.

# Nice to have
- Tests.
- Organized and well written git commit history.
- E2E tests.

# Task Deadline
- Monday Morning 20/07/2020.
