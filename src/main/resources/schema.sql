DROP TABLE IF EXISTS parent;

CREATE TABLE parent (
  parent_id INT AUTO_INCREMENT  PRIMARY KEY,
  sender VARCHAR(250) NOT NULL,
  receiver VARCHAR(250) NOT NULL,
  totalAmount INT DEFAULT 0,
  totalPaidAmount INT DEFAULT 0,
  PRIMARY KEY (parent_id)
);

DROP TABLE IF EXISTS child;

CREATE TABLE child (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  pId INT NOT NULL,
  paidAmount INT DEFAULT 0,
  PRIMARY KEY (id),
  CONSTRAINT fk_con_paren FOREIGN KEY (pId) REFERENCES parent (parent_id)
) ;