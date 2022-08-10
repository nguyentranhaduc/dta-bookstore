const jwt = require('jsonwebtoken');

module.exports = (req, res, next) => {
  console.log('Đang xác thực danh tính request!');

  // lấy header "Authorization" và lưu vào biến authHeader
  const authHeader = req.get('Authorization');
  // nếu header 'Authorization' không có giá trị.
  if (!authHeader) {
    // khởi tạo đối tượng Error
    const error = new Error('Not authenticated.');
    // set statusCode là 401
    error.statusCode = 401;
    throw error;
  }

  // nếu header 'Authorization' có giá trị:

  // lấy header "Authorization", cắt bởi khoảng cách và lấy phần token phía sau
  // gán vào biến hằng token.
  const token = authHeader.split(' ')[1];

  let decodedToken; // khai báo một biến let để chứa token sau khi giải mã

  try {
    decodedToken = jwt.verify(token, 'conbocuoi123');
  } catch (err) {
    // nếu thất bại
    err.statusCode = 500;
    throw err;
  }

  if (!decodedToken) {
    const error = new Error('Not authenticated.');
    error.statusCode = 401;
    throw error;
  }
  // Nếu token hợp lệ thì gắn token đã giải mã vào request
  req.userId = decodedToken.userId;

  next();
};
