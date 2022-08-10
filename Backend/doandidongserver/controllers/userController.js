const nodemailer = require('nodemailer');
const sendgridTransport = require('nodemailer-sendgrid-transport');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');

const User = require('../models/userModel');
const Product = require('../models/productModel');

// chuẩn bị sendgrid
const transporter = nodemailer.createTransport(
  sendgridTransport({
    auth: {
      api_key:
        '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',
    },
  })
);

exports.postDangNhap = (req, res, next) => {
  console.log('User tiến hành đăng nhập =====');
  // lấy các thuộc tính từ request
  const emailInput = req.body.email;
  const passwordInput = req.body.password;

  let loadedUser;

  console.log(emailInput);
  console.log(passwordInput);

  // tìm user trong database
  User.findOne({ email: emailInput })
    .then((user) => {
      // tìm thành công

      // nếu không tìm thấy user có email đó
      if (!user) {
        res.status(200).json({ status: 'Email hoặc mật khẩu không đúng!' });
      } else {
        // nếu tìm thấy user có email đó

        loadedUser = user;
        return bcrypt.compare(passwordInput, user.password);
      }
    })
    // kết quả so sánh mật khẩu
    .then((isEqual) => {
      if (!isEqual) {
        // nếu kết quả so sánh mật khẩu là không đúng
        res.status(200).json({ status: 'Email hoặc mật khẩu không đúng!' });
      } else {
        // kết quả so sánh mật khẩu là đúng

        // tạo token
        // sign giá trị email và id của user đó trong database
        const token = jwt.sign(
          {
            email: emailInput,
            userId: loadedUser._id.toString(),
          },
          'conbocuoi123', // secret key
          { expiresIn: '1h' } // set thời gian cho token vừa tọa
        );

        // trả respond về cùng token và id của user đó
        res
          .status(200) // 200 OK
          .json({ token: token, userId: loadedUser._id.toString() });
      }
    })
    // quá trình tìm xảy ra lỗi
    .catch((err) => {
      console.log(err);
    });
};

exports.postDangKy = (req, res, next) => {
  console.log(req.body);

  // lấy các trường dữ liệu trong request
  const fullname = req.body.fullname;
  const email = req.body.email;
  const phone = req.body.phone;
  const password = req.body.password;

  User.findOne({ email: email }).then((userDoc) => {
    if (userDoc) {
      console.log('Email này đã tồn tại!');
      return res.status(200).json({
        status: 'Email này đã được sử dụng, mời bạn nhập email khác!',
      });
    } else {
      bcrypt
        .hash(password, 12)
        .then((hashedPassword) => {
          // tạo object User mới từ các trường lấy được từ request
          const user = new User({
            fullname: fullname,
            email: email,
            phone: phone,
            password: hashedPassword,
            // cart: [],
          });
          // lưu vào database
          return user.save();
        })
        // nếu lưu vào database thành công
        .then((result) => {
          console.log('Tạo tài khoản thành công!');
          res
            .status(200)
            .json({ status: 'Tạo tài khoản thành công, mời bạn đăng nhập!' });

          transporter
            .sendMail({
              to: result.email,
              from: 'haduc159@gmail.com',
              subject: 'DTA-Bookstore - Đăng ký tài khoản thành công',
              html:
                '<h1>Chúc mừng, bạn đã đăng ký tài khoản thành công tại <b>DTA-Bookstore</b></h1><br><div><p>Tài khoản của bạn là: ' +
                result.email +
                '</p></div>',
            })
            .then((result) => {
              console.log('Gửi mail thành công!');
            });
        })
        .catch((err) => {
          console.log(err);
          res.status(400).json({ status: 'Tạo tài khoản thất bại!' });
        });
    }
  });
};

exports.postGetUserInformation = (req, res, next) => {
  console.log('Lấy thông tin user.');

  // lấy userId từ request
  const userId = req.body.userId;
  console.log('User Id: ' + userId);

  User.findOne({ _id: userId })
    .then((user) => {
      // trả về thông tin của user
      res.status(200).json(user);
    })
    .catch((err) => {
      console.log(err);
    });
};

exports.postUpdateUserInformation = (req, res, next) => {
  console.log(req.body);

  // lấy các thông tin cần cập nhật từ request
  const userId = req.body.userId;

  const fullname = req.body.fullname;
  const email = req.body.email;
  const phone = req.body.phone;
  const address = req.body.address;

  User.updateOne(
    { _id: userId },
    {
      $set: {
        fullname: fullname,
        email: email,
        phone: phone,
        address: address,
      },
    }
  )
    .then((result) => {
      console.log(result);
      res.status(200).json({ status: 'Cập nhật thông tin user thành công!' });
      console.log('Cập nhật thông tin user thành công!');
    })
    .catch((err) => {
      console.log(err);
    });
};

exports.getProducts = (req, res, next) => {
  // lấy tất cả product
  Product.find()
    .then((products) => {
      // trả về tất cả product document
      res.status(200).json(products);
    })
    .catch((err) => {
      console.log(err);
    });
};
