							 /* PHẦN MỀM QUẢN LÝ SIÊU THỊ ĐIỆN MÁY
                             Giảng viên hướng dẫn: TS. Phạm Doãn Tĩnh
			                 Nhóm thực hiện:       Nguyễn Đình Đức
                                                   Phạm Ngọc Đan
                                                   Trần Tiến Đạt
                                                   Đinh Hoàng PHú
                                                   Phan Văn Thịnh */
drop database if exists QuanLySieuThiDienMay;
-- xoa database neu ton tai

create database if not exists QuanLySieuThiDienMay CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci';
-- tao database sử dụng charset là UTF-8 và Collate là utf8_unicode_ci để khi nhập tiếng Việt không bị lỗi font.

use QuanLySieuThiDienMay;

-- tao bang bang nhan vien
create table if not exists NhanVien(
	MaNV varchar(20) not null,
    TenNV varchar(50) not null,
    ChucVu varchar(50) not null,
    NgaySinh date not null,
    GioiTinh varchar(5) not null,
	DiaChi varchar(255) not null,
	SDT varchar(12) not null,
    Email varchar(50) not null,
    GhiChu varchar(255),
    AnhNV varchar(255),
    primary key (MaNV), 	-- tao va dat ten cho khoa chinh
    unique (SDT),	 	-- unique khac primary key la vd 1 nhan vien chi co 1 sdt va 1 mail duy nhat
	unique (Email)	-- se khong the co hai nhan vien cung sdt va mail
);

-- ALTER TABLE ten bang ADD CONSTRAINT pk_user PRIMARY KEY (ten cot)
-- ALTER TABLE ten bang ADD UNIQUE(ten cot);
-- hoac su dung cach tao bang xong moi tao khoa chinh va tao unique

-- tao bang khach hang
create table if not exists KhachHang(
	MaKH varchar(20) not null,
    TenKH varchar(100) not null,
    DiaChi varchar(255),
	SDT varchar(12) not null,
    Email varchar(45) not null,
    TongTien double not null,
    LoaiKhachHang varchar(45) not null,
    GhiChu varchar(255),
    primary key (MaKH, SDT)
);

-- tao bang Nha cung cap
create table if not exists NhaCungCap(
    MaNCC varchar(20) not null,
    TenNCC varchar(255) not null,
    DiaChi varchar(255) not null,
    SDT varchar(12) not null,
    Email varchar(50) not null,
    GhiChu varchar(255),
    primary key (MaNCC)
);

-- tao bang hang hoa
create table if not exists HangHoa(
    MaHH varchar(20) not null,
	TenHH varchar(255) not null,
    NSanXuat varchar(255) not null,
    MaNCC varchar(20) not null,
    GiaNhap double not null,
    GiaBan double not null,
    Anh_path varchar(255) not null,
    primary key (MaHH),
    foreign key (MaNCC) references nhacungcap(MaNCC)
);

-- tao bang tai khoan
create table if not exists TaiKhoan(
	Username varchar(20) not null,
    MatKhau varchar(20) not null,
    MaNV varchar(20) not null,
	primary key (Username),
    foreign key (MaNV) references nhanvien(MaNV)
);

-- tao bang hoa don ban hang
create table if not exists HoaDonBanHang(
    MaHD varchar(20) not null,
	Ngay date not null,
    MaNV varchar(20) not null,
    MaKH varchar(20) not null,
    HTThanhToan varchar(50) not null,
    TongTien double not null,
    primary key (MaHD),
    foreign key (MaNV) references nhanvien(MaNV),
    foreign key (MAKH) references khachhang(MaKH)
);

-- tao bang hoa don nhap hang
create table if not exists HoaDonNhapHang(
    MaHD varchar(20) not null,
	Ngay date not null,
	MaNV varchar(20) not null,
    MaNCC varchar(20) not null,
    TongTien double not null,
    primary key (MaHD),
    foreign key (MaNV) references nhanvien(MaNV),
    foreign key (MaNCC) references nhacungcap(MaNCC)
);

-- tao bang chi tiet hoa don ban hang
create table if not exists ChiTietHoaDonBanHang(
    MaHD varchar(20) not null,
    MaHH varchar(20) not null,
    SoLuong int(10) not null,
    ThanhTien double not null,
    primary key (MaHD, MaHH),
    foreign key (MaHD) references hoadonbanhang(MaHD),
    foreign key (MaHH) references hanghoa(MaHH)
);
create table if not exists ChiTietHoaDonNhapHang(
    MaHD varchar(20) not null,
    MaHH varchar(20) not null,
    SoLuong int(10) not null,
    ThanhTien double not null,
    primary key (MaHD, MaHH),
    foreign key (MaHD) references hoadonnhaphang(MaHD),
    foreign key (MaHH) references hanghoa(MaHH)
);

