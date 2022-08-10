const express = require('express');

const adminController = require('../controllers/admin');
const productController = require('../controllers/productController');

const router = express.Router();

// GET /admin/users
router.get('/users', adminController.getUsers);

router.post('/add-product', productController.postAddProduct);

module.exports = router;
