DROP DATABASE IF EXISTS `CupcakeShop`;
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema CupcakeShop
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema CupcakeShop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CupcakeShop` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema CupcakeShop
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema CupcakeShop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CupcakeShop` DEFAULT CHARACTER SET utf8 ;
USE `CupcakeShop` ;

-- -----------------------------------------------------
-- Table `CupcakeShop`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CupcakeShop`.`user` (
  `username` VARCHAR(8) NOT NULL,
  `password` VARCHAR(8) NOT NULL,
  `balance` INT NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `CupcakeShop`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CupcakeShop`.`order` (
  `orderID` INT NOT NULL AUTO_INCREMENT,
  `invoice` VARCHAR(45) NULL,
  `price` INT NOT NULL,
  `status` ENUM('unfinished', 'finished') NULL,
  `orderDate` DATETIME NULL,
  `user` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`orderID`, `user`),
  INDEX `fk_order_user_idx` (`user` ASC),
  CONSTRAINT `fk_order_user`
    FOREIGN KEY (`user`)
    REFERENCES `CupcakeShop`.`user` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `CupcakeShop`.`topping`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CupcakeShop`.`topping` (
  `toppingName` VARCHAR(25) NOT NULL,
  `price` INT(11) NOT NULL,
  PRIMARY KEY (`toppingName`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `CupcakeShop`.`bottom`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CupcakeShop`.`bottom` (
  `bottomName` VARCHAR(25) NOT NULL,
  `price` INT(11) NOT NULL,
  PRIMARY KEY (`bottomName`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `CupcakeShop`.`cupcakeDetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CupcakeShop`.`cupcakeDetails` (
  `orderID` INT NOT NULL,
  `qty` INT NOT NULL,
  `topping` VARCHAR(25) NOT NULL,
  `bottom` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`orderID`, `topping`, `bottom`),
  INDEX `fk_cupcakeDetails_topping1_idx` (`topping` ASC),
  INDEX `fk_cupcakeDetails_bottom1_idx` (`bottom` ASC),
  CONSTRAINT `fk_cupcakeDetails_order1`
    FOREIGN KEY (`orderID`)
    REFERENCES `CupcakeShop`.`order` (`orderID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cupcakeDetails_topping1`
    FOREIGN KEY (`topping`)
    REFERENCES `CupcakeShop`.`topping` (`toppingName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cupcakeDetails_bottom1`
    FOREIGN KEY (`bottom`)
    REFERENCES `CupcakeShop`.`bottom` (`bottomName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

USE `CupcakeShop` ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
