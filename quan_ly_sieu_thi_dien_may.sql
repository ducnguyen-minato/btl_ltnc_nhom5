drop database if exists quan_ly_sieu_thi_dien_may;
-- xoa database neu ton tai

create database if not exists quan_ly_sieu_thi_dien_may CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci';
-- tao database sử dụng charset là UTF-8 và Collate là utf8_unicode_ci để khi nhập tiếng Việt không bị lỗi font.

use quan_ly_sieu_thi_dien_may;

-- tao bang bang nhan vien
create table if not exists nhanvien(
	manhanvien varchar(20) not null,
    tennhanvien varchar(255) not null,
    chucvu varchar(20) not null,
	ngaysinh varchar(12) not null,
    gioitinh varchar(10) not null,
    diachi varchar(255),
    sodienthoai varchar(50) not null,
    email varchar(50) not null,
    ghichu varchar(255),
    primary key (manhanvien), 	-- tao va dat ten cho khoa chinh
    unique (sodienthoai),	 	-- unique khac primary key la vd 1 nhan vien chi co 1 sdt va 1 mail duy nhat
	unique (email)	-- se khong the co hai nhan vien cung sdt va mail
);

-- ALTER TABLE ten bang ADD CONSTRAINT pk_user PRIMARY KEY (ten cot)
-- ALTER TABLE ten bang ADD UNIQUE(ten cot);
-- hoac su dung cach tao bang xong moi tao khoa chinh va tao unique

-- tao bang khach hang
create table if not exists khachhang(
	makhachhang varchar(45) not null,
    tenkhachhang varchar(255) not null,
	diachi varchar(12) not null,
    sodienthoai varchar(10) not null,
    email varchar(255) not null,
    tongtien double not null,
    loaikhachhang varchar(255),
    primary key (makhachhang)
);

-- tao bang Nha cung cap
create table if not exists nhacungcap(
    manhacungcap varchar(45)  not null,
    tenhacungcap varchar(255) not null,
    diachi varchar(255) not null,
    sodienthoai varchar(12) not null,
    email varchar(50) not null,
    ghichu varchar(255),
    primary key (manhacungcap)
);

-- tao bang tai khoan
create table if not exists TaiKhoan(
	Username varchar(20) not null,
    MatKhau varchar(20) not null,
    MaNV varchar(20) not null,
	primary key (Username),
    foreign key (MaNV) references nhanvien(manhanvien)
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
    foreign key (MaNCC) references nhacungcap(manhacungcap)
);


-- tao bang hoa don ban hang
create table if not exists HoaDonBanHang(
    MaHD varchar(20) not null,
	Ngay varchar(20) not null,
    MANVBanHang varchar(255) not null,
    MAKH varchar(255) not null,
    HTThanhToan varchar(50) not null,
    TongTien int(255) not null,
    primary key (MaHD),
    foreign key (MaNVBanHang) references nhanvien(manhanvien),
    foreign key (MAKH) references khachhang(makhachhang)
);

-- tao bang hoa don nhap hang
create table if not exists HoaDonNhapHang(
    MaHD varchar(20) not null,
	Ngay varchar(20) not null,
	MANVNhanHang varchar(255) not null,
    MaNCC varchar(255) not null,
    TenNVGiaoHang varchar(255) not null,
    TongTien int(255) not null,
    primary key (MaHD),
    foreign key (MaNVNhanHang) references nhanvien(manhanvien),
    foreign key (MaNCC) references nhacungcap(manhacungcap)
);

-- tao bang chi tiet hoa don ban hang
create table if not exists ChiTietHoaDonBanHang(
    MaHD varchar(20) not null,
    MaHH varchar(20) not null,
    SoLuong int(10) not null,
    ThanhTien varchar(255) not null,
    primary key (MaHD, MaHH),
    foreign key (MaHD) references hoadonbanhang(MaHD)
);
create table if not exists ChiTietHoaDonNhapHang(
    MaHD varchar(20) not null,
    MaHH varchar(20) not null,
    SoLuong int(10) not null,
    ThanhTien varchar(255) not null,
    primary key (MaHD, MaHH),
    foreign key (MaHD) references hoadonnhaphang(MaHD)
);



