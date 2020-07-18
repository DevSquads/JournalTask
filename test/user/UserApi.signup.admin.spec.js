const chai = require('chai');
const { expect } = chai;
require('mocha');
const request = require('supertest');
const app = require('../../app');

const userData = {
  name: 'admin',
  email: 'admin@admin.com',
  password: 'Abdalah1',

};


describe('testing  user signup admin', () => {
  it('signup admin ok', () => {
    return request('localhost:3000/api')
      .post('/user/signup/admin')
      .send(userData)
      .then((response) => {
        expect(response.status).to.equal(201);
        expect(response.body).to.have.property('token');
        expect(response.body).to.have.property('userData');

      });
  });
});