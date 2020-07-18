const chai = require('chai');
const { expect } = chai;
require('mocha');
const request = require('supertest');
const app = require('../../app');

let articleId = "5f10d707c70dfe12181db6ca"

let token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZjEwZGUwMTBlZjVjODA1OTBmNWQ5ZDMiLCJpYXQiOjE1OTUxMTI3NDYsImV4cCI6MTU5NTExNjM0Nn0.HB3JABBJN5Zn1jTzF2xTTO4qOruRjplv9ZzLMTjhupw"

describe('testing edit article', () => {

  it('edit article ok', () => {
    return request('localhost:3000/api')
      .put(`/article/edit-article/${articleId}`)
      .set({ authorization: token })
      .send({
        title: 'artedit',
        description: 'des1'
      })
      .then((response) => {
        expect(response.status).to.equal(200);
        expect(response.body).to.have.property('message').to.equal('Article edited successfully');
        expect(response.body).to.have.property('editedArticle');

      });
  });

  it('edit article Invalid article Id mongoose.Types.ObjectId.isValid', () => {
    return request('localhost:3000/api')
      .put(`/article/edit-article/124354465565`)
      .set({ authorization: token })
      .send({
        title: 'artt',
        description: 'des1'
      })
      .then((response) => {
        expect(response.status).to.equal(400);
        expect(response.body).to.have.property('message').to.equal('Invalid Article');
      });
  });

  it('edit article Invalid article not found', () => {
    return request('localhost:3000/api')
      .put(`/article/edit-article/5f00f489cbc80937b0e7d318`)
      .set({ authorization: token })
      .send({
        title: 'artefeee',
        description: 'des1'
      })
      .then((response) => {
        expect(response.status).to.equal(400);
        expect(response.body).to.have.property('message').to.equal('Invalid Article');
      });
  });

  //required validation like add article
});