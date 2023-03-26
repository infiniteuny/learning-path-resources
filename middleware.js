const jwt = require('jsonwebtoken')
const PRIVATEKEY = "laksdlaknkcqjweco"

const verifyJwt = async (req, res, next) => {
  let token = req.headers.authorization
  token = token?.replace('Bearer ', '')
  console.log(token)

  if (!token) {
    return res.status(401).send("Unauthorized")
  }

  jwt.verify(token, PRIVATEKEY, {}, function (err, payload) {
    if (err) {
      return res.status(401).send("Unauthorized")
    }

    req.user = payload
    next()
  })
}

module.exports = {
  verifyJwt
}