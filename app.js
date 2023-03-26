const express = require('express')
const app = express()
const port = 3000
const { PrismaClient } = require('@prisma/client')
const prisma = new PrismaClient()
const bodyParser = require('body-parser')
const bcrypt = require('bcrypt')
const jwt = require('jsonwebtoken')
const { verifyJwt } = require('./middleware')

const PRIVATEKEY = "laksdlaknkcqjweco"

app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())

app.post('/students', verifyJwt, async (req, res) => {
  const { name, email, birth_date } = req.body
  const student = await prisma.student.create({
    data: {
      name,
      email,
      birth_date: new Date(birth_date),
      user_id: req.user.id
    }
  })

  return res.json(student)
})

app.get('/students', verifyJwt, async (req, res) => {
  const students = await prisma.student.findMany({
    where: {
      user_id: req.user.id,
    },
    include: {
      class: true
    }
  })
  return res.json(students)
})

app.get('/students/:id', async (req, res) => {
  const id = Number(req.params.id)
  const student = await prisma.student.findFirst({
    where: {
      id
    },
    include: {
      class: true
    }
  })

  return res.json(student)
})

app.get('/class/:id', async (req, res) => {
  const id = Number(req.params.id)
  const student_class = await prisma.class.findFirst({
    where: {
      id
    },
    include: {
      students: true
    }
  })

  return res.json(student_class)
})

app.post('/user/register', async (req, res) => {
  const {name, email, password} = req.body

  try {
    const hashed_password = await bcrypt.hash(password, 10)
    const user = await prisma.user.create({
      data: {
        name,
        email,
        password: hashed_password
      }
    })
  
    return res.send("Register Success")
  } catch (e) {
    console.error(e)
    return res.status(500).send("Internal Server Error")
  }
})

app.post('/user/login', async (req, res) => {
  const { email, password } = req.body

  try {
    const user = await prisma.user.findFirst({
      where: {
        email
      }
    })

    if (!user) {
      return res.status(404).send("User Not Found")
    }

    if (!await bcrypt.compare(password, user.password)) {
      return res.status(401).send("Invalid Password")
    }

    const payload = {
      id: user.id,
      name: user.name,
      email: user.email
    }

    const token = jwt.sign(payload, PRIVATEKEY, {
      expiresIn: '3d'
    })

    return res.send(token)
  } catch (e) {
    console.error(e)
    return res.status(500).send("Internal Server Error")
  }
})

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})