const chai = require('chai');
const { expect } = chai;
require('mocha');
const request = require('supertest');
const app = require('../../app');

let articleId = "5f10d706c70dfe12181db6c9"

let token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZjEwZGUwMTBlZjVjODA1OTBmNWQ5ZDMiLCJpYXQiOjE1OTUxMTI3NDYsImV4cCI6MTU5NTExNjM0Nn0.HB3JABBJN5Zn1jTzF2xTTO4qOruRjplv9ZzLMTjhupw"
let tokenAdmin = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZjEwZDJiMTQwNjg0NDA0MDhhMzQ4OGEiLCJpYXQiOjE1OTUxMTQ2NjIsImV4cCI6MTU5NTIwMTA2Mn0.Z5ubz6p0kJrSh3y6CrQ3W9eL418yJ2kb9SIZJCn3cR0"

describe('testing delete article', () => {

  it('delete article ok', () => {
    return request('localhost:3000/api')
      .delete(`/article/delete-article/${articleId}`)
      .set({ authorization: tokenAdmin })
      .then((response) => {
        expect(response.status).to.equal(200);
        expect(response.body).to.have.property('message').to.equal('article Deleted Successfully');
      });
  });
  it('delete article with token user ', () => {
    return request('localhost:3000/api')
      .delete(`/article/delete-article/${articleId}`)
      .set({ authorization: token })
      .then((response) => {
        expect(response.status).to.equal(400);
        expect(response.body).to.have.property('message').to.equal('you dont have permission for this operation');
      });
  });

  it('delete article Invalid article Id mongoose.Types.ObjectId.isValid', () => {
    return request('localhost:3000/api')
      .delete(`/article/delete-article/124354465565`)
      .set({ authorization: tokenAdmin })
      .then((response) => {
        expect(response.status).to.equal(400);
        expect(response.body).to.have.property('message').to.equal('Invalid Article');
      });
  });

  it('delete article Invalid article not found', () => {
    return request('localhost:3000/api')
      .delete(`/article/delete-article/5f00f489cbc80937b0e7d318`)
      .set({ authorization: tokenAdmin })
      .then((response) => {
        expect(response.status).to.equal(400);
        expect(response.body).to.have.property('message').to.equal('Invalid Article');
      });
  });
});