CREATE TABLE tbl_member(
   mid VARCHAR(200) NOT NULL PRIMARY KEY,
   mpw VARCHAR(200) NOT NULL,
   email VARCHAR(100) NOT NULL,
   mname VARCHAR(100) NOT NULL,
   regdate TIMESTAMP DEFAULT NOW(),
   moddate TIMESTAMP DEFAULT NOW()
)
;

INSERT INTO tbl_member (mid, mpw, email, mname)
VALUES ('user00', 'user00', 'user00@aaa.com', '사용자1');

INSERT INTO tbl_member (mid, mpw, email, mname)
VALUES ('user11', 'user11', 'user11@aaa.com', '사용자2');

INSERT INTO tbl_member (mid, mpw, email, mname)
VALUES ('user22', 'user22', 'user22@aaa.com', '사용자3');

INSERT INTO tbl_member (mid, mpw, email, mname)
VALUES ('user33', 'user33', 'user33@aaa.com', '사용자4');

INSERT INTO tbl_member (mid, mpw, email, mname)
VALUES ('user44', 'user44', 'user44@aaa.com', '사용자5');

INSERT INTO tbl_member (mid, mpw, email, mname)
VALUES ('user55', 'user55', 'user55@aaa.com', '사용자6');


SELECT * FROM tbl_member WHERE MID = 'user00' AND mpw = 'user00';