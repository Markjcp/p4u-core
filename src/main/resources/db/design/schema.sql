SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `p4u` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `p4u` ;

-- -----------------------------------------------------
-- Table `p4u`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `p4u`.`user` (
  `user_id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_email` VARCHAR(45) NOT NULL,
  `user_password` VARCHAR(45) NOT NULL,
  `user_facebook` VARCHAR(45) NULL,
  `user_address` VARCHAR(45) NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `p4u`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `p4u`;
INSERT INTO `p4u`.`user` (`user_id`, `user_email`, `user_password`, `user_facebook`, `user_address`) VALUES (1, 'mnforlenza@gmail.com', 'password', 'mforlenza', '190:190');

COMMIT;

