const config = {};
const JWTsecret = 'articles';
config.JWTsecret = process.env.SECRET || JWTsecret;
config.TokenDurationInHours = 24;

config.dbURI = "mongodb+srv://article:article@articlecluster.wjpz7.mongodb.net/article?retryWrites=true&w=majority",



    module.exports = config;