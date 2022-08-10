const express = require('express');

const userController = require('../controllers/userController');
const productController = require('../controllers/productController');
const orderController = require('../controllers/orderController');
const notificationController = require('../controllers/notificationController');
// const isAuth = require('../middelware/is-auth');

const router = express.Router();

// POST /api/dangnhap
// nếu token không chính xác, hành động sẽ bị chặn lại
router.post('/dangnhap', userController.postDangNhap);

// POST /api/dangky
router.post('/dangky', userController.postDangKy);

// GET /api/products/:tên_thể_loại_sách
router.get(
  '/products/theloai/:loaisach',
  productController.getProductByCategory
);

// GET /api/products/sachbanchay
router.get('/products/sachbanchay', productController.getSachBanChay);

// GET /api/products/sachmoi
router.get('/products/sachmoi', productController.getSachMoi);

// GET /api/products
router.get('/products', productController.getProducts);

// POST /api/dathang
router.post('/dathang', orderController.postAddOrder);

// GET /api/getUserInformation
router.post('/getUserInformation', userController.postGetUserInformation);

// POST /api/updateUserInformation
router.post('/updateUserInformation', userController.postUpdateUserInformation);

// POST /api/getUserOrders
router.post('/getUserOrders', orderController.getUserOrders);

// POST /api/getOrderDetail
router.post('/getOrderDetail', orderController.postGetOrderDetail);

// POST /api/deleteOrder
router.post('/deleteOrder', orderController.postDeleteOrder);

// POST /api/timSachTheoTen
router.post('/timSachTheoTen', productController.postTimSachTheoTen);

// POST /api/getNotification
router.post('/getNotification', notificationController.postGetNotification);

module.exports = router;
