const chai = require('chai');
const { expect } = chai;
require('mocha');
const request = require('supertest');
const app = require('../../app');

const articleData = {
  title: 'art1',
  description: 'description 1',
};
//Token was Expired
let tokenExpired = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZjAwZjQ2OWNiYzgwOTM3YjBlN2QzMTEiLCJpYXQiOjE1OTM4OTgwOTEsImV4cCI6MTU5MzkwMTY5MX0.zO-LjapA33IpikGIqDE5ifgRettp4Mpj6goIx5ilM40';

let token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZjEwZGUwMTBlZjVjODA1OTBmNWQ5ZDMiLCJpYXQiOjE1OTUxMTI3NDYsImV4cCI6MTU5NTExNjM0Nn0.HB3JABBJN5Zn1jTzF2xTTO4qOruRjplv9ZzLMTjhupw"

describe('testing add article', () => {

  it('add article ok', () => {
    return request('localhost:3000/api')
      .post('/article/add-article')
      .set({ authorization: token })
      .send(articleData)
      .then((response) => {
        // console.log(response);
        expect(response.status).to.equal(200);
        expect(response.body).to.have.property('message').to.equal('Article add Successfully');
        expect(response.body).to.have.property('article');

      });
  });

  it('add article Token was Expired', () => {
    return request('localhost:3000/api')
      .post('/article/add-article')
      .set({ authorization: tokenExpired })
      .send(articleData)
      .then((response) => {
        expect(response.status).to.equal(401);
        expect(response.body).to.have.property('message').to.equal('Token Not Valid. Please login again');
        expect(response.body).to.have.property('token').to.equal(null);

      });
  });

  it('add article removed token from header Unauthorized request ', () => {
    return request('localhost:3000/api')
      .post('/article/add-article')
      .send(articleData)
      .then((response) => {
        expect(response.status).to.equal(401);
        expect(response.body).to.have.property('message').to.equal('Unauthorized request');

      });
  });

  it('add article title required ', () => {
    return request('localhost:3000/api')
      .post('/article/add-article')
      .set({ authorization: token })
      .send({
        description: 'description 1',
      })
      .then((response) => {
        expect(response.status).to.equal(400);
        expect(response.body).to.have.property('message').to.equal('title is required');

      });
  });

  it('add article description required ', () => {
    return request('localhost:3000/api')
      .post('/article/add-article')
      .set({ authorization: token })
      .send({
        title: 'art22',
      })
      .then((response) => {
        expect(response.status).to.equal(400);
        expect(response.body).to.have.property('message').to.equal('description is required');

      });
  });

});