const joi = require('@hapi/joi');

const articleSchema = {

    title: joi.string()
        .required()
        .lowercase(),

    description: joi.string()
        .required()
        .lowercase(),
};

module.exports = {
    articleSchema: joi.object(articleSchema),
};