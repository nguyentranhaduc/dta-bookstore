package com.example.dtabookstore.data

import com.example.dtabookstore.R
import com.example.dtabookstore.model.Book

object DataSourceBook {
    val books: List<Book> = listOf(
        Book(
            "1",
            listOf(R.drawable.book_taichinhcanhan, R.drawable.book_taichinhcanhan_sau),
            "Sách Tài Chính Cá Nhân Cho Người Việt Nam - Tặng Khóa học Online về Tài chính",
            "Doanh nhân Lâm Minh Chánh hiện là Chủ tịch trường đào tạo Quản trị Kinh doanh BizUni, Đồng Sáng lập Group QTvKN là cộng đồng doanh nhân hàng đầu VN. Anh Chánh có 17 năm kinh nghiệp làm quản lý cấp cao các tập đoàn nước ngoài, và 11 năm khởi nghiệp. Anh từng đảm trách các vị trí Tổng Giám đốc, Phó TGĐ, GĐ các DN về BHNT, Chứng khoán, Sàn vàng, Đầu tư. Anh tốt nghiệp MBA hạng ưu tại UTS, và là một chuyên gia có thực tiễn nhiều năm về tài chính và đầu tư.",
            200000,
            mapOf<String, String>(
                "nha_xuat_ban" to "Nhà Xuất Bản Phụ Nữ",
                "ngay_xuat_ban" to "2020-04-10",
                "so_trang" to "376",
                "cong_ty_phat_hanh" to "BIZBOOKS"
            ),
            "Sách kinh tế",
            true,
            true
        ),
        Book(
            "2",
            listOf(R.drawable.book_cangdoclapcangcaoquy),
            "Càng độc lập càng cao quý",
            "Này cô gái, bất luận cuộc đời mang đến cho ta bao nhiêu chông gai thử thách, xin đừng tự coi nhẹ chính mình, đừng buông bỏ bản thân và phụ thuộc vào người khác. Hãy luôn giữ vững niềm tin rằng, số phận của ta nằm trong chính ta, không nằm trong tay ông Trời. Độc lập về cuộc sống, tư tưởng và kinh tế giúp chúng ta vĩnh viễn không mất đi quyền lựa chọn trong tay mình. Không ai thay chúng ta chịu trách nhiệm về cuộc đời mình. Thế nên, dù đang hạnh phúc đi nữa, trên đường đời cũng không tránh khỏi những tháng ngày phải độc bước tiến lên. Càng độc lập càng cao quý – Hãy nhớ lấy, cô gái nhé!",
            57600,
            mapOf<String, String>(
                "nha_xuat_ban" to "Nhà Xuất Bản Phụ Nữ",
                "ngay_xuat_ban" to "2019-06-02",
                "so_trang" to "368",
                "cong_ty_phat_hanh" to "1980 Books"
            ),
            "Sách kỹ năng sống",
            true,
            true
        ),
        Book(
            "3",
            listOf(R.drawable.book_caycamngotcuatoi),
            "Cây Cam Ngọt Của Tôi",
            "Hãy làm quen với Zezé, cậu bé tinh nghịch siêu hạng đồng thời cũng đáng yêu bậc nhất, với ước mơ lớn lên trở thành nhà thơ cổ thắt nơ bướm. Chẳng phải ai cũng công nhận khoản “đáng yêu” kia đâu nhé. Bởi vì, ở cái xóm ngoại ô nghèo ấy, nỗi khắc khổ bủa vây đã che mờ mắt người ta trước trái tim thiện lương cùng trí tưởng tượng tuyệt vời của cậu bé con năm tuổi.",
            80000,
            mapOf<String, String>(
                "nha_xuat_ban" to "Nhà Xuất Bản Hội Nhà Văn",
                "ngay_xuat_ban" to "",
                "so_trang" to "244",
                "cong_ty_phat_hanh" to "Nhã Nam"
            ),
            "Sách văn học",
            true,
            false
        ),
        Book(
            "4",
            listOf(R.drawable.book_hieuhetvetamlyhoc),
            "How Psychology Works - Hiểu Hết Về Tâm Lý Học",
            "Tìm hiểu về các vấn đề tâm trí của con người luôn đầy sức hấp dẫn và lôi cuốn, vì vậy mà tâm lý học ra đời, hình thành và phát triển rất nhiều các học thuyết và trường phái. Cuốn sách này dẫn dắt bạn đọc qua hành trình tìm hiểu các học thuyết và trường phái đó, về cách các nhà tâm lý diễn giải hành xử và tâm trí con người. Tại sao chúng ta có những hành vi, suy nghĩ và cảm xúc như vậy, chúng diễn ra và kết thúc như thế nào, chúng ảnh hưởng lâu dài, gián đoạn hay ngắn ngủỉ đến đời sống của chúng ta ra sao, làm thế nào để chúng ta thoát khỏi những tác động tiêu cực của chúng?",
            210200,
            mapOf<String, String>(
                "nha_xuat_ban" to "Nhà Xuất Bản Thế Giới",
                "ngay_xuat_ban" to "2020-11-01",
                "so_trang" to "247",
                "cong_ty_phat_hanh" to "Nhã Nam"
            ),
            "Sách kỹ năng sống",
            true,
            false
        ),
        Book(
            "5",
            listOf(R.drawable.book_khoinghiepkinhdoanhonline),
            "Khởi Nghiệp Kinh Doanh Online - Bán Hàng Hiệu Quả Trên Facebook",
            "Quyển sách căn bản cho người mới bắt đầu, đơn giản, dễ hiểu và dành riêng cho thị trường Việt Nam.",
            190000,
            mapOf<String, String>(
                "nha_xuat_ban" to "Nhà Xuất Bản Đà Nẵng",
                "ngay_xuat_ban" to "2021-06-01",
                "so_trang" to "300",
                "cong_ty_phat_hanh" to "Công ty TNHH Truyền Thông Giver"
            ),
            "Sách kinh tế",
            true,
            false
        ),
        Book(
            "6",
            listOf(R.drawable.book_renluyentuduyphanbien, R.drawable.book_renluyentuduyphanbien_sau),
            "Rèn Luyện Tư Duy Phản Biện",
            "Việc lắng nghe những ý kiến của người khác cũng có thể giúp bạn nhận ra rằng phạm vi tri thức của bạn không phải là vô hạn. Không ai có thể biết hết tất cả mọi thứ. Nhưng với việc chia sẻ và đánh giá phê bình kiến thức, chúng ta có thể mở rộng tâm trí. Nếu điều này khiến bạn cảm thấy không thoải mái, không sao cả. Trên thực tế, bước ra ngoài vùng an toàn là một điều quan trọng để mở rộng niềm tin và suy nghĩ của bạn. Tư duy phản biện không phải là chỉ biết vài thứ, và chắc chắn không phải việc xác nhận những điều bạn đã biết. Thay vào đó, nó xoay quanh việc tìm kiếm sự thật – và biến chúng trở thành thứ bạn biết.",
            55200,
            mapOf<String, String>(
                "nha_xuat_ban" to "Nhà Xuất Bản Phụ Nữ",
                "ngay_xuat_ban" to "2019-12-01",
                "so_trang" to "204",
                "cong_ty_phat_hanh" to "1980 Books"
            ),
            "Sách kỹ năng sống",
            true,
            false
        ),
        Book(
            "7",
            listOf(R.drawable.book_thaydoituduylanhdao, R.drawable.book_thaydoituduylanhdao_2, R.drawable.book_thaydoituduylanhdao_3),
            "Leader Mindset - Thay Đổi Tư Duy Lãnh Đạo - LOAN VĂN SƠN",
            "Nội dung cuốn sách được trình bày theo kết cấu Vấn đề - Giải pháp. Ở phần nêu vấn đề, tác giả đã trình bày một cách tổng quan đến chi tiết những “điểm mù” trong tư duy lãnh đạo. Từ vấn đề cho đến giải pháp đều được lồng ghép trong những câu chuyện từ chất liệu của cuộc sống. Có thể nói, Leader Mindset – Thay đổi tư duy lãnh đạo đã tái hiện một cách sinh động và chân thực cuộc đời của những nhà lãnh đạo.",
            300000,
            mapOf<String, String>(
                "nha_xuat_ban" to "Nhà Xuất Bản Thanh Niên",
                "ngay_xuat_ban" to "2021-12-10",
                "so_trang" to "358",
                "cong_ty_phat_hanh" to "CÔNG TY CỔ PHẦN TGS GROUP"
            ),
            "Sách kinh tế",
            false,
            true
        ),
        Book(
            "8",
            listOf(R.drawable.book_yeutrongtinhthuc, R.drawable.book_yeutrongtinhthuc_sau),
            "Yêu Trong Tỉnh Thức - Từ Bạn Đời Đến Bạn Đạo Tập 1",
            "Khi bạn thật sự yêu một ai đó, cái tôi của bạn sẽ tự động biến mất dần vì người đó. Vậy nên khi chúng ta yêu nhau, kết hôn mà cãi cọ nhau, tranh giành đúng sai, mâu thuẫn về những chuyện không đâu thì hãy tự hỏi xem liệu ta có đang yêu đối phương thật sự hay không? Chúng ta có đang tỉnh thức hay không?",
            185000,
            mapOf<String, String>(
                "nha_xuat_ban" to "Nhà Xuất Bản Hồng Đức",
                "ngay_xuat_ban" to "2021-06-30",
                "so_trang" to "300",
                "cong_ty_phat_hanh" to "BIZBOOKS"
            ),
            "Sách văn học",
            false,
            true
        ),
        Book(
            "9",
            listOf(R.drawable.book_thongketrongkinhtevakinhdoanh),
            "Thống Kê Trong Kinh Tế Và Kinh Doanh - Statistics For Business And Economics",
            "Nói về cơ hội khi thành thạo về kĩ năng thống kê phân tích dữ liệu. Hãy dùng toàn bộ kiến thức mà bạn học hỏi, nghiên cứu đọc sách để ra quyết định. Hãy suy nghĩ, tìm tòi về thứ mà bạn cho là có giá trị trong tương lai và đầu tư công sức vào đó. Dù đúng hay sai, nhưng nhất định bạn phải đặt cửa vào thứ gì đó theo bạn nó sẽ thành công trong tương lai. Đó là đỉnh cao của thống kê và khoa học phân tích dữ liệu.",
            419000,
            mapOf<String, String>(
                "nha_xuat_ban" to "Nhà Xuất Bản Kinh Tế TPHCM",
                "ngay_xuat_ban" to "2018-03-25",
                "so_trang" to "889",
                "cong_ty_phat_hanh" to "Nhà Xuất Bản Kinh Tế TPHCM"
            ),
            "Sách kinh tế",
            false,
            true
        ),

    )



    //  Hàm tìm danh sách sách mới nhất
    fun findBookMoiNhat(): List<Book> {
        val listBook: MutableList<Book> = mutableListOf()

        books.forEach {
            if (it.sach_moi) {
                listBook.add(it)
            }
        }

        return listBook
    }



    //  Hàm tìm danh sách sách bán chạy
    fun findBookBanChay(): List<Book> {
        val listBook: MutableList<Book> = mutableListOf()

        books.forEach {
            if (it.ban_chay) {
                listBook.add(it)
            }
        }

        return listBook
    }



    // Hàm lấy danh sách sách theo thể loại
    fun findSpecificCategoryBooks(inputCategory: String): List<Book> {
        val listBook: MutableList<Book> = mutableListOf()

        books.forEach {
            if (it.book_category == inputCategory) {
                listBook.add(it)
            }
        }

        return listBook
    }



    // Hàm tìm sách theo id trong dataSource
    fun findABook(inputBookId: String): Book? {
        DataSourceBook.books.forEach {
            if (it.book_id == inputBookId) {
                return it
            }
        }

        return null
    }
}