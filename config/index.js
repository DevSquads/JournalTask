const config = {};
const JWTsecret = 'articles';
config.JWTsecret = process.env.SECRET || JWTsecret;
config.TokenDurationInHours = 24;

config.dbURI = "mongodb://localhost:27017/articles",



    module.exports = config;