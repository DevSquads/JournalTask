const chai = require('chai');
const { expect } = chai;
require('mocha');
const request = require('supertest');
const app = require('../../app');

const userData = {
  email: 'a@a.com',
  password: 'Abdalah1911'
};

describe.skip('testing  user login ', () => {

  it('login ok', () => {
    return request('localhost:3000/api')
      .post('/user/login')
      .send(userData)
      .then((response) => {
        console.log(response);
        expect(response.status).to.equal(200);
        expect(response.body).to.have.property('token');
        expect(response.body).to.have.property('userData');

      });
  });

  it('login required email', () => {
    return request('localhost:3000/api')
      .post('/user/login')
      .send({
        password: '123'
      })
      .then((response) => {
        expect(response.status).to.equal(400);
        expect(response.body).to.have.property('message').to.equal('email is required');
      });
  });
  it('login required password', () => {
    return request('localhost:3000/api')
      .post('/user/login')
      .send({
        email: 'a@a.com'
      })
      .then((response) => {
        expect(response.status).to.equal(400);
        expect(response.body).to.have.property('message').to.equal('password is required');
      });
  });

  it('login validation schema valid email ', () => {
    return request('localhost:3000/api')
      .post('/user/login')
      .send({
        email: 'a.com',
        password: '123'
      })
      .then((response) => {
        expect(response.status).to.equal(400);
        expect(response.body).to.have.property('message').to.equal('email must be a valid email');
      });
  });

  it('login Invalid username or password ', () => {
    return request('localhost:3000/api')
      .post('/user/login')
      .send({
        email: 'a@a.com',
        password: '123'
      })
      .then((response) => {
        expect(response.status).to.equal(400);
        expect(response.body).to.have.property('message').to.equal('Invalid username or password');
      });
  });

});