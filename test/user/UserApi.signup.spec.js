const chai = require('chai');
const { expect } = chai;
require('mocha');
const request = require('supertest');
const app = require('../../app');
const { before } = require('mocha');

const userData = {
    name: 'abdalah',
    email: 'a@a.com',
    password: 'Abdalah1',

};


describe('testing  user signup', () => {
    it('signup ok', () => {
        return request('localhost:3000/api')
            .post('/user/signup')
            .send(userData)
            .then((response) => {
                expect(response.status).to.equal(201);
                expect(response.body).to.have.property('token');
                expect(response.body).to.have.property('userData');

            });
    });

    it('signup required name', () => {
        return request('localhost:3000/api')
            .post('/user/signup')
            .send({
                email: 'h@h.com',
                password: 'Abdalah1',
            })
            .then((response) => {
                expect(response.status).to.equal(400);
                expect(response.body).to.have.property('message').to.equal('name is required');
            });
    });
    it('signup required email', () => {
        return request('localhost:3000/api')
            .post('/user/signup')
            .send({
                name: 'abdalah',
                password: 'Abdalah1',
            })
            .then((response) => {
                expect(response.status).to.equal(400);
                expect(response.body).to.have.property('message').to.equal('email is required');
            });
    });
    it('signup required password', () => {
        return request('localhost:3000/api')
            .post('/user/signup')
            .send({
                name: 'abdalah',
                email: 'b@b.com'
            })
            .then((response) => {
                expect(response.status).to.equal(400);
                expect(response.body).to.have.property('message').to.equal('password is required');
            });
    });

    it('sigup validation schema valid name len', () => {
        return request('localhost:3000/api')
            .post('/user/signup')
            .send({
                name: 'a',
                email: 'a@a.com',
                password: '123'
            })
            .then((response) => {
                expect(response.status).to.equal(400);
                expect(response.body).to.have.property('message').to.equal('name length must be between 2~20 characters and consists of letters only');
            });
    });
    it('sigup validation schema valid email ', () => {
        return request('localhost:3000/api')
            .post('/user/signup')
            .send({
                name: 'abdalah',
                email: 'www',
                password: '123'
            })
            .then((response) => {
                expect(response.status).to.equal(400);
                expect(response.body).to.have.property('message').to.equal('email must be a valid email');
            });
    });

    it('sigup validation schema valid password ', () => {
        return request('localhost:3000/api')
            .post('/user/signup')
            .send({
                name: 'abdalah',
                email: 'a@a.com',
                password: '123'
            })
            .then((response) => {
                expect(response.status).to.equal(400);
                expect(response.body).to.have.property('message').to.equal('Password must be at least a minimum of 8 characters long with 1 small letter, 1 capital letter , 1 number');
            });
    });

    it('signup Email already exists', () => {
        before
        return request('localhost:3000/api')
            .post('/user/signup')
            .send({
                name: 'abdalah',
                email: 'a@a.com',
                password: 'Abdalah1'
            })
            .then((response) => {
                expect(response.status).to.equal(409);
                expect(response.body).to.have.property('message').to.equal('Email already exists')

            });
    });

});