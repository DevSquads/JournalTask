const Config = require('../config');
const jwt = require('jsonwebtoken');
async function verifyToken(req, res, next) {

    let isRouteExcluded = excludeRoutes.filter(route => { return req.originalUrl.search(route) > -1; })[0];
    if (isRouteExcluded) return next();

    if (!req.headers.authorization) return res.status(401).json({ message: 'Unauthorized request' });

    const token = req.headers.authorization;

    return jwt.verify(token, Config.JWTsecret, (err, decoded) => {
        if (err) {
            if (err.expiredAt < new Date()) {
                return res.status(401).json({
                    message: 'Token was Expired. Pleas login again',
                    token: null
                });
            }
            next();
        }
        req.userData = decoded;
        next();
    });
}

let excludeRoutes = [
    '/user/login',
    '/user/signup',
    '/user/signup/admin'
]

module.exports = verifyToken