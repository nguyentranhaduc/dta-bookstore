const Product = require('../models/productModel');

exports.getProducts = (req, res, next) => {
  // lấy tất cả product
  Product.find()
    .then((products) => {
      // trả về một mảng tất cả các product document
      res.status(200).json(products);
    })
    .catch((err) => {
      console.log(err);
    });
};

exports.getProductByCategory = (req, res, next) => {
  // lấy thể loại sách từ url
  const theLoaiSach = req.params.loaisach;

  console.log(theLoaiSach);

  // lấy tất cả product
  Product.find({ type: theLoaiSach })
    .then((products) => {
      // trả về một mảng tất cả các product document
      res.status(200).json(products);
    })
    .catch((err) => {
      console.log(err);
    });
};

exports.postAddProduct = (req, res, next) => {
  // lấy các giá trị của sản phẩm từ request
  const title = req.body.title;
  const price = req.body.price;
  const type = req.body.type;
  const description = req.body.description;
  const imagesUrl = req.body.imagesUrl;
  const detail = req.body.detail;
  const banchay = req.body.banchay;
  const sanphammoi = req.body.sanphammoi;

  // tạo một product mới với các giá tị lấy được ở trên
  const product = new Product({
    title: title,
    price: price,
    type: type,
    description: description,
    imagesUrl: imagesUrl,
    detail: detail,
    banchay: banchay,
    sanphammoi: sanphammoi,
  });

  // lưu sản phẩm vào database
  return product
    .save()
    .then((result) => {
      console.log('Thêm sản phẩm ' + result.title + ' thành công!');
      res
        .status(200)
        .json({ status: 'Thêm sản phẩm ' + result.title + ' thành công!' });
    })
    .catch((err) => {
      console.log(err);
    });
};

exports.getSachBanChay = (req, res, next) => {
  Product.find({ banchay: true })
    .then((products) => {
      res.status(200).json(products);
    })
    .catch((err) => {
      console.log(err);
    });
};

exports.getSachMoi = (req, res, next) => {
  Product.find({ sanphammoi: true })
    .then((products) => {
      res.status(200).json(products);
    })
    .catch((err) => {
      console.log(err);
    });
};

exports.postTimSachTheoTen = (req, res, next) => {
  console.log('Tiến hành tìm kiếm sách theo tên.');

  // lấy từ khóa cần tìm kiếm từ request
  const tuKhoa = req.body.tuKhoa;

  Product.find({ title: { $regex: tuKhoa, $options: tuKhoa } })
    // .limit(20)
    .then((results) => {
      res.status(200).json(results);
    })
    .catch((err) => {
      console.log(err);
    });
};
