-- Product
create table Product (
    idProduct number primary key,
    name varchar2(50) not null,
    url varchar2(300)
);

CREATE SEQUENCE Product_seq INCREMENT BY 1 START WITH 1 MINVALUE 1;

-- Category
create table Category (
    idCategory number primary key,
    name varchar2(50) not null,
    description varchar2(100)
);

CREATE SEQUENCE Category_seq INCREMENT BY 1 START WITH 1 MINVALUE 1;

-- Product_Category
create table Product_Category (
    FK_idProduct number not null,
    FK_idCategory number not null,
    primary key (FK_idProduct, FK_idCategory),
    foreign key (FK_idProduct) references Product(idProduct),
    foreign key (FK_idCategory) references Category(idCategory)
);

-- User
create table ClientUser (
    idClientUser number primary key,
    name varchar2(50) not null
);

CREATE SEQUENCE ClientUser_seq INCREMENT BY 1 START WITH 1 MINVALUE 1;

-- Purchase
create table Purchase (
    idPurchase number primary key,
    datePurchase date not null,
    FK_idClientUser number not null,
    foreign key (FK_idClientUser) references ClientUser(idClientUser)
);

CREATE SEQUENCE Purchase_seq INCREMENT BY 1 START WITH 1 MINVALUE 1;

-- Purchase_Product
create table Purchase_Product (
    idPurchaseProduct number primary key,
    FK_idPurchase number not null,
    FK_idProduct number not null,
    foreign key (FK_idPurchase) references Purchase(idPurchase),
    foreign key (FK_idProduct) references Product(idProduct)
);

CREATE SEQUENCE Purchase_Product_seq INCREMENT BY 1 START WITH 1 MINVALUE 1;