DROP DATABASE IF EXISTS the_dream_gadget;
CREATE DATABASE IF NOT EXISTS the_dream_gadget;
SHOW DATABASES;
USE the_dream_gadget;



DROP TABLE IF EXISTS Cashier;
CREATE TABLE IF NOT EXISTS Cashier
(
    cid     VARCHAR(10) NOT NULL,
    ename   VARCHAR(45),
    address VARCHAR(75),
    contact VARCHAR(12),
    salary  DECIMAL(6, 2),
    CONSTRAINT PRIMARY KEY (cid)
    );
SHOW TABLES;
DESCRIBE Cashier;


#-------------------------------------------------

DROP TABLE IF EXISTS Supplier;
CREATE TABLE IF NOT EXISTS Supplier
(
    sid VARCHAR(10) not null,
    snic VARCHAR (45),
    sname VARCHAR(45),
    scontact VARCHAR(12),
    saddress VARCHAR (75),
    scompany VARCHAR (45),
    CONSTRAINT PRIMARY KEY (sid)
    );
SHOW TABLES;
DESCRIBE Supplier;


#----------------------------------------

DROP TABLE IF EXISTS Item;
CREATE TABLE IF NOT EXISTS Item
(
    Iid        VARCHAR(10) NOT NULL,
    model        VARCHAR(45),
    imei        VARCHAR(45),
    brand       VARCHAR(25),
    type        VARCHAR(25),
    qty         INT,
    bprice      DOUBLE ,
    tcost       DOUBLE ,
    sprice      DOUBLE ,
    description VARCHAR (45),
    sid  VARCHAR(10),
    billnum    VARCHAR(10),
    bdate    VARCHAR(15),
    CONSTRAINT PRIMARY KEY (Iid)

    );
SHOW TABLES;
DESCRIBE Item

    #-------------------------------------------------------------




    #---------------------------------------------------------------





    #-------------------------------------------------

DROP TABLE IF EXISTS Customer;
CREATE TABLE IF NOT EXISTS Customer
(
    cid     VARCHAR(10) NOT NULL,
    name    VARCHAR(45),
    address VARCHAR(75),
    contact VARCHAR(12),
    CONSTRAINT PRIMARY KEY (cid)
    );
SHOW TABLES;
DESCRIBE Customer;



#-------------------------------------------------





#-------------------------------------------------




#----------------------------------------------------

DROP TABLE IF EXISTS Return_Item;
CREATE TABLE IF NOT EXISTS Return_Item
(
    returnid         VARCHAR(10) NOT NULL,
    customeridR   VARCHAR(15),
    itemcodeR     VARCHAR(15),
    returnreason     VARCHAR(45),
    billingdateR       VARCHAR(15),
    returndate     VARCHAR(25),
    warrantydueR       VARCHAR(25),

    CONSTRAINT PRIMARY KEY (returnid),
    CONSTRAINT FOREIGN KEY (customeridR) REFERENCES Customer (cid) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES;
DESCRIBE Return_Item;


#----------------------------------------------------
DROP TABLE IF EXISTS Billing;
CREATE TABLE IF NOT EXISTS Billing
(
    Bicode VARCHAR (15),
    Bid   VARCHAR(10) NOT NULL,
    Bcid VARCHAR (10),
    Bitype VARCHAR (15),
    Bbrandname VARCHAR (15),
    Bmodel VARCHAR (15),
    Bimei VARCHAR (15),
    Bwarrantydue VARCHAR (15),
    Bdescription VARCHAR (25),
    Bqty INT,
    Bprice DOUBLE,
    Bdate VARCHAR (15),

    CONSTRAINT PRIMARY KEY (Bicode)


    );
SHOW TABLES;
DESCRIBE Billing;







#----------------------------------------------------




DROP TABLE IF EXISTS Repair_Item;
CREATE TABLE IF NOT EXISTS Repair_Item
(
    rid         VARCHAR(10) NOT NULL,
    rcid         VARCHAR(10) NOT NULL,
    imeir         VARCHAR(15),
    brandmodel VARCHAR(25),
    faulttype      VARCHAR(10),
    fault     VARCHAR(25),
    bringdate VARCHAR(25),
    daytogive VARCHAR(25),
    CONSTRAINT PRIMARY KEY (rid),
    CONSTRAINT FOREIGN KEY (rcid) REFERENCES Customer (cid) ON DELETE CASCADE ON UPDATE CASCADE

    );
SHOW TABLES;
DESCRIBE Repair_Item;

DROP TABLE IF EXISTS Orders;
CREATE TABLE IF NOT EXISTS Orders(

    oid VARCHAR (10) NOT NULL,
    ocid VARCHAR (10) NOT NULL,
    odate VARCHAR(15),
    otime  VARCHAR(15),
    cost VARCHAR(15),

    CONSTRAINT PRIMARY KEY (oid),
    CONSTRAINT FOREIGN KEY (ocid) REFERENCES Customer (cid) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES;
DESCRIBE Orders;


DROP TABLE IF EXISTS Item_Reports;
CREATE TABLE IF NOT EXISTS Item_Reports(

    itemcode VARCHAR (10) NOT NULL,
    oid VARCHAR (10) NOT NULL,
    unitprice DOUBLE ,
    itemcount INT  DEFAULT 0,
    total DOUBLE DEFAULT 0.00,
    rdate VARCHAR (15),

    CONSTRAINT PRIMARY KEY (itemcode,oid),
    CONSTRAINT FOREIGN KEY (itemcode) REFERENCES Item(Iid) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (oid) REFERENCES Orders (oid) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES;
DESCRIBE Item_Reports;