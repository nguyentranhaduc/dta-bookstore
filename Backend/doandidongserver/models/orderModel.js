const User = require('./userModel');

const mongoose = require('mongoose');

const Schema = mongoose.Schema;

const orderSchema = new Schema({
  // id của user đặt hàng
  userId: {
    type: Schema.Types.ObjectId,
    ref: 'User',
    required: true,
  },

  // trạng thái của đơn hàng
  status: {
    type: String,
    default: 'choxacnhandonhang',
  },

  // mảng chứa danh sách các sản phẩm
  products: [
    {
      // id của sản phẩm
      productId: {
        type: Schema.Types.ObjectId,
        ref: 'Product',
        required: true,
      },
      // số lượng
      quantity: {
        type: Number,
        required: true,
      },
    },
  ],

  total: {
    type: Number,
    default: 0,
  },

  date: {
    type: Date,
    default: Date.now,
  },
});

module.exports = mongoose.model('Order', orderSchema);
