const nodemailer = require('nodemailer');
const sendgridTransport = require('nodemailer-sendgrid-transport');

const Order = require('../models/orderModel');
const User = require('../models/userModel');
const Notification = require('../models/notificationModel');

// chuẩn bị sendgrid
const transporter = nodemailer.createTransport(
  sendgridTransport({
    auth: {
      api_key:
        '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',
    },
  })
);

exports.postAddOrder = (req, res, next) => {
  // lấy userId
  const userId = req.body.userId;
  const danhSachSanPhamVaSoLuong = req.body.danhSachSanPhamVaSoLuong;
  const total = req.body.total;

  console.log(req.body);

  const donHang = new Order({
    userId: userId,
    products: danhSachSanPhamVaSoLuong,
    total: total,
  });

  donHang
    .save()
    .then((resultDonHang) => {
      res
        .status(200)
        .json({ status: 'Đơn hàng của bạn đã được đặt thành công!' });
      console.log(resultDonHang);

      User.findOne({ _id: userId }).then((user) => {
        transporter
          .sendMail({
            to: user.email,
            from: 'haduc159@gmail.com',
            subject: 'DTA-Bookstore - Đặt hàng thành công',
            html:
              '<h1>Bạn đã đặt hàng thành công</h1><br><div><p>Đơn hàng của bạn là: ' +
              resultDonHang._id +
              '</p></div>',
          })
          .then((resultGuiMail) => {
            console.log('Gửi mail thông báo đặt hàng thành công!');
          });

        // tạo đối tượng thông báo mới dựa trên userId và mã đơn hàng vừa lưu được
        const notification = new Notification({
          userId: userId,
          title: 'Đặt hàng thành công',
          content:
            'Bạn đã đặt hàng thành công. Đơn hàng của bạn có mã là: ' +
            resultDonHang._id +
            ', tổng giá trị đơn hàng trị giá: ' +
            resultDonHang.total +
            ' VNĐ.',
        });

        // lưu thông báo vào database
        notification
          .save()
          .then((result) => {
            console.log('tạo thông báo thành công!');
          })
          .catch((err) => {
            console.log(err);
          });
      });
    })
    .catch((err) => {
      console.log(err);
    });
};

exports.getUserOrders = (req, res, next) => {
  console.log('Tiến hành lấy các đơn hàng của user.');

  // lấy userId từ request
  const userId = req.body.userId;

  Order.find({ userId: userId })
    .then((orders) => {
      res.status(200).json(orders);
    })
    .catch((err) => {
      console.log(err);
    });
};

exports.postGetOrderDetail = (req, res, next) => {
  console.log('Tiến hành lấy thông tin chi tiết đơn hàng.');

  // lấy _id của order từ request
  const orderId = req.body.orderId;

  console.log(req.body);

  Order.findOne({ _id: orderId })
    .then((theOrder) => {
      res.status(200).json(theOrder);
    })
    .catch((err) => {
      console.log(err);
    });
};

exports.postDeleteOrder = (req, res, next) => {
  console.log('Tiến hành xóa đơn hàng.');

  // lấy _id của order từ request
  const orderId = req.body.orderId;

  // lấy userId từ request
  const userId = req.body.userId;

  console.log(req.body);

  Order.deleteOne({ _id: orderId })
    .then((resultDonHang) => {
      res.status(200).json({ status: 'Xóa đơn hàng thành công!' });
      console.log('Xóa đơn hàng thành công!');

      // tạo đối tượng thông báo mới dựa trên userId và mã đơn hàng vừa lưu được
      const notification = new Notification({
        userId: userId,
        title: 'Hủy đơn hàng thành công',
        content: 'Bạn đã hủy thành công đơn hàng có mã là: ' + orderId + ' .',
      });

      // lưu thông báo vào database
      notification
        .save()
        .then((result) => {
          console.log('tạo thông báo thành công!');
        })
        .catch((err) => {
          console.log(err);
        });

      // transporter
      //     .sendMail({
      //       to: user.email,
      //       from: 'haduc159@gmail.com',
      //       subject: 'DTA-Bookstore - Đặt hàng thành công',
      //       html:
      //         '<h1>Bạn đã đặt hàng thành công</h1><br><div><p>Đơn hàng của bạn là: ' +
      //         resultDonHang._id +
      //         '</p></div>',
      //     })
      //     .then((resultGuiMail) => {
      //       console.log('Gửi mail thông báo đặt hàng thành công!');
      //     });
    })
    .catch((err) => {
      console.log(err);
    });
};
