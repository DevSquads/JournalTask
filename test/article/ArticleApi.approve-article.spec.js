const chai = require('chai');
const { expect } = chai;
require('mocha');
const request = require('supertest');
const app = require('../../app');

let articleId = "5f1387bf7ab57f21c0b51f92"

let token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZjEwZGUwMTBlZjVjODA1OTBmNWQ5ZDMiLCJpYXQiOjE1OTUxMTI3NDYsImV4cCI6MTU5NTExNjM0Nn0.HB3JABBJN5Zn1jTzF2xTTO4qOruRjplv9ZzLMTjhupw"
let tokenAdmin = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZjEwZDJiMTQwNjg0NDA0MDhhMzQ4OGEiLCJpYXQiOjE1OTUxMTQ2NjIsImV4cCI6MTU5NTIwMTA2Mn0.Z5ubz6p0kJrSh3y6CrQ3W9eL418yJ2kb9SIZJCn3cR0"

describe('testing approve article', () => {

  it('approve article ok', () => {
    return request('localhost:3000/api')
      .get(`/article/approve-article/${articleId}`)
      .set({ authorization: tokenAdmin })
      .then((response) => {
        expect(response.status).to.equal(200);
        expect(response.body).to.have.property('message').to.equal('Article Approved Successfully');
      });
  });

  it('approve article already approved token user ', () => {
    return request('localhost:3000/api')
      .get(`/article/approve-article/5f10d707c70dfe12181db6ca`)
      .set({ authorization: tokenAdmin })
      .then((response) => {
        expect(response.status).to.equal(400);
        expect(response.body).to.have.property('message').to.equal('Already Approved');
      });
  });
  it('approve article with token user ', () => {
    return request('localhost:3000/api')
      .get(`/article/approve-article/${articleId}`)
      .set({ authorization: token })
      .then((response) => {
        expect(response.status).to.equal(400);
        expect(response.body).to.have.property('message').to.equal('you dont have permission for this operation');
      });
  });

  it('approve article Invalid article Id mongoose.Types.ObjectId.isValid orInvalid article not found', () => {
    return request('localhost:3000/api')
      .get(`/article/approve-article/123`)
      .set({ authorization: tokenAdmin })
      .then((response) => {
        expect(response.status).to.equal(400);
        expect(response.body).to.have.property('message').to.equal('Invalid Article');
      });
  });
});