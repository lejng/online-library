CREATE SCHEMA `online_library` ;
USE online_library;
-- Table: users
CREATE TABLE users (
  id    INT   NOT NULL AUTO_INCREMENT PRIMARY KEY,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  role VARCHAR(25)NOT NULL DEFAULT 'ROLE_USER',
  name VARCHAR(50) NOT NULL,
  surname VARCHAR(50) NOT NULL,
  imageUrl VARCHAR(500) NULL
);

CREATE TABLE `genres` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `genre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `books` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `genre_id` INT NOT NULL,
  `author` VARCHAR(255) NULL,
  `description` VARCHAR(500) NULL,
  `contentUrl` VARCHAR(500) NULL,
  `imageUrl` VARCHAR(500) NULL,
  PRIMARY KEY (`id`),
  INDEX `genre_idx` (`genre_id` ASC),
  CONSTRAINT `genre`
  FOREIGN KEY (`genre_id`)
  REFERENCES `genres` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` VARCHAR(500) NOT NULL,
  `book_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `book_idx` (`book_id` ASC),
  INDEX `user_idx` (`user_id` ASC),
  CONSTRAINT `book`
  FOREIGN KEY (`book_id`)
  REFERENCES `books` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user`
  FOREIGN KEY (`user_id`)
  REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `rating` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rating` INT NULL,
  `user_id` INT NULL,
  `book_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_idx` (`user_id` ASC),
  INDEX `book_idx` (`book_id` ASC),
  CONSTRAINT `user_k`
  FOREIGN KEY (`user_id`)
  REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `book_k`
  FOREIGN KEY (`book_id`)
  REFERENCES `books` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

insert into genres values(1,'History');
insert into genres values(2,'Horror');
insert into genres values(3,'Fantasy');
insert into genres values(4,'Travel');
