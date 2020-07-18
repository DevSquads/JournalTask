const chai = require('chai');
const { expect } = chai;
require('mocha');
const request = require('supertest');
const app = require('../../app');

let tokenUser = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZjEwYzFmMWRhMjk4NDIwYjRmZTU4ZjUiLCJpYXQiOjE1OTUxMTQzODEsImV4cCI6MTU5NTIwMDc4MX0.eIU2QVRKykY3-2G99WRQ_NN4Q6zhuh0GfEdBovqnaUg';

let token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZjEwZDJiMTQwNjg0NDA0MDhhMzQ4OGEiLCJpYXQiOjE1OTUxMTQ2NjIsImV4cCI6MTU5NTIwMTA2Mn0.Z5ubz6p0kJrSh3y6CrQ3W9eL418yJ2kb9SIZJCn3cR0"

describe('testing get Articles Sorted By Most Popular Authors Or Author View All Articles', () => {

    it('get Articles Sorted By Most Popular Authors ok', () => {
        return request('localhost:3000/api')
            .get(`/article/getArticlesSortedByMostPopularAuthorsOrAuthorvViewAllArticles`)
            .set({ authorization: token })
            .then((response) => {
                expect(response.status).to.equal(200);
                expect(response.body).to.have.property('message').to.equal('get All Articles Successfully');
                expect(response.body).to.have.property('articles');
            });
    });
    it('When an author view all articles, they see their own articles first ok', () => {
        return request('localhost:3000/api')
            .get(`/article/getArticlesSortedByMostPopularAuthorsOrAuthorvViewAllArticles`)
            .set({ authorization: tokenUser })
            .then((response) => {
                expect(response.status).to.equal(200);
                expect(response.body).to.have.property('message').to.equal('get All Articles Successfully');
                expect(response.body).to.have.property('articles');
            });
    });
});