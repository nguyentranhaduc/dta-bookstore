const Notification = require('../models/notificationModel');

exports.postGetNotification = (req, res, next) => {
  console.log('Tiến hành lấy danh sách thông báo của user.');

  // lấy userId từ request
  const userId = req.body.userId;

  console.log(userId);

  Notification.find({ userId: userId })
    .limit(10)
    .then((notifications) => {
      res.status(200).json(notifications);
    })
    .catch((err) => {
      console.log(err);
    });
};
