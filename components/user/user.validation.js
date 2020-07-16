const joi = require('@hapi/joi');

const signupSchema = {
    name: joi.string()
        .required()
        .trim()
        .lowercase()
        .ruleset
        .pattern(/^[a-zA-Z]+$/)
        .min(2)
        .max(20)
        .rule({ message: ' name length must be between 2~20 characters and consists of letters only' }),
    email: joi.string()
        .required()
        .email()
        .lowercase()
        .message('Invalid email'),

    password: joi.string()
        .required()
        .trim()
        .pattern(/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$/)
        .message('Password must be at least a minimum of 8 characters long with 1 small letter, 1 capital letter , 1 number'),

};

const loginSchema = {
    email: joi.string()
        .email()
        .trim()
        .lowercase()
        .required(),

    password: joi.string()
        .required()
        .trim()
};

module.exports = {
    signup: joi.object(signupSchema),
    login: joi.object(loginSchema),
};