const mongoose = require('mongoose');

const Schema = mongoose.Schema;

const productSchema = new Schema({
  title: {
    type: String,
    required: true,
  },

  price: {
    type: Number,
    required: true,
  },

  type: {
    type: String,
    required: true,
  },

  // link các hình ảnh của sản phẩm
  imagesUrl: [{ type: String, required: true }],

  description: {
    type: String,
    required: true,
  },

  // thông tin chi tiết của sản phẩm
  detail: {
    nhaxuatban: {
      type: String,
    },

    ngayxuatban: {
      type: String,
    },

    sotrang: {
      type: Number,
    },

    congtyphathanh: {
      type: String,
    },
  },

  // là sản phẩm bán chạy
  banchay: {
    type: Boolean,
    required: true,
  },

  // là sản phẩm mới
  sanphammoi: {
    type: Boolean,
    required: true,
  },
});

module.exports = mongoose.model('Product', productSchema);
