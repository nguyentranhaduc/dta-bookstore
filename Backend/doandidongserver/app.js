// các nodejs core module
const path = require('path');

// các module third-party
const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');

const app = express();

// các import tự tạo
const userRoutes = require('./routes/userRoutes');
const adminRoutes = require('./routes/adminRoutes');

// khai báo thư mục static trong project
app.use(express.static(path.join(__dirname, 'public')));

// kết body cho các request
app.use(bodyParser.urlencoded({ extended: false }));

// biến đổi request thành json để làm việc với api
app.use(bodyParser.json());

// các router liên quan đến api

// user routes
app.use('/api', userRoutes);

// các router liên quan đến phần admin
app.use('/admin', adminRoutes);

// Kết nối đến database mongodb
mongoose
  .connect(
    // connection string ở đây
    'mongodb+srv://haduc:haduc123@cluster0.bktuo.mongodb.net/dta-bookstore?retryWrites=true&w=majority'
  )
  .then((result) => {
    // nếu kết nối thành công và trả về result
    console.log('Kết nối database thành công!');
    app.listen(3000);
  })
  .catch((err) => {
    console.log(err);
  });
