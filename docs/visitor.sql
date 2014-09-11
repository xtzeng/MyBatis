/*创建Visitor*/
CREATE TABLE Visitor
(
    Id INT(11) NOT NULL AUTO_INCREMENT,
    Name VARCHAR(1000) NOT NULL,
    Email VARCHAR(1000) NOT NULL,
    Status INT NOT NULL DEFAULT 1,
    CreateTime DateTime,
    PRIMARY KEY(Id)
)
/*创建网站表*/
CREATE TABLE Website
(
    Id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(1000) NOT NULL,
    VisitorId INT REFERENCES Visitor(Id),
    Status INT NOT NULL DEFAULT 1,
    CreateTime DateTime
)

/*创建频道表*/
CREATE TABLE Channel
(
    Id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(1000) NOT NULL,
    WebsiteId INT REFERENCES Website(Id),
    Status INT NOT NULL DEFAULT 1,
    CreateTime DateTime
)