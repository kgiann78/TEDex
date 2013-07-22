-- phpMyAdmin SQL Dump
-- version 4.0.3
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 05, 2013 at 02:01 PM
-- Server version: 5.5.25
-- PHP Version: 5.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `TEDex`
--
CREATE DATABASE IF NOT EXISTS `TEDex` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `TEDex`;

-- --------------------------------------------------------

--
-- Table structure for table `administration`
--

DROP TABLE IF EXISTS `administration`;
CREATE TABLE IF NOT EXISTS `administration` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `numOfStores` int(11) NOT NULL DEFAULT '0',
  `numOfUsers` int(11) NOT NULL DEFAULT '0',
  `numOfSuppliers` int(11) NOT NULL DEFAULT '0',
  `numOfProducts` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
CREATE TABLE IF NOT EXISTS `permissions` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `permissions`
--

INSERT INTO `permissions` (`id`, `name`, `description`) VALUES
(1, 'administrator', 'administrator'),
(2, 'storage_write', 'storage write permission'),
(3, 'product_write', 'product write permission'),
(4, 'supplier_write', 'supplier write permission'),
(5, 'storage_read', 'storage read permission'),
(6, 'product_read', 'product read permission'),
(7, 'supplier_read', 'supplier read permission');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(35) NOT NULL DEFAULT ' ',
  `quantity` int(11) NOT NULL DEFAULT '0',
  `description` tinytext,
  `code` varchar(11) NOT NULL DEFAULT '0',
  `cost` float NOT NULL DEFAULT '0',
  `price` float NOT NULL DEFAULT '0',
  `storageId` int(11) unsigned NOT NULL DEFAULT '0',
  `supplierId` int(11) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `productInStorage` (`storageId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=310 ;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `name`, `quantity`, `description`, `code`, `cost`, `price`, `storageId`, `supplierId`) VALUES
(1, 'Πριόνι', 150, 'εργαλεία', 'tool1', 90, 99, 1, 1),
(2, 'Τσεκούρι', 150, 'εργαλεία', 'tool2', 200, 210, 1, 1),
(3, 'Σφυρί', 150, 'εργαλεία', 'tool3', 5.9, 7.9, 1, 1),
(4, 'Σκεπάρνι', 150, 'εργαλεία', 'tool4', 9.9, 10.9, 1, 1),
(5, 'Αρνί Ζηλανδίας', 100, 'κρεατικά', 'meat1', 9.9, 11.9, 2, 2),
(6, 'Μόσχος Αργεντινής', 100, 'κρεατικά', 'meat2', 0, 0, 2, 2),
(7, 'Κοτόπουλα Βοκτάς', 150, 'πουλερικά', 'poultry1', 3.9, 5.9, 2, 2),
(307, 'Κοτόπουλα Άρτας', 100, 'null', 'poultry2', 0, 0, 2, 2),
(308, 'kati2', 123, 'kati3', 'kati1', 123, 123, 2, 2),
(309, 'hell', 1000, 'jhekhasdagfasdg', 'herll', 21, 21, 3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `roleHasPermissions`
--

DROP TABLE IF EXISTS `roleHasPermissions`;
CREATE TABLE IF NOT EXISTS `roleHasPermissions` (
  `roleId` int(11) unsigned NOT NULL,
  `permissionId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`permissionId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `roleHasPermissions`
--

INSERT INTO `roleHasPermissions` (`roleId`, `permissionId`) VALUES
(1, 1),
(2, 2),
(5, 2),
(3, 3),
(5, 3),
(4, 4),
(5, 4),
(2, 5),
(5, 5),
(6, 5),
(3, 6),
(5, 6),
(6, 6),
(4, 7),
(5, 7);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL DEFAULT '',
  `description` tinytext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`, `description`) VALUES
(1, 'administrator', 'Ο διαχειριστής του συστήματος. Αυτός ο ρόλος δεν μπορεί να τροποποιηθεί'),
(2, 'Υπεύθυνος αποθηκών', 'Ο ρόλος αυτός έχει πλήρη δικαιώματα στη διαχείριση αποθηκών'),
(3, 'Υπεύθυνος προϊόντων', 'ο διαχειριστής των προϊόντων'),
(4, 'Υπεύθυνος εφοδιασμού', 'ο υπεύθυνος για την διαχείριση των προμηθευτών'),
(5, 'δοκιμαστικός ρολος', 'δοκιμαστικός ρόλος, περιέχει τα πάντα'),
(6, 'δοκιμαστικός ρόλος 2', 'μονο για ανάγνωση αποθηκών και προϊόντων');

-- --------------------------------------------------------

--
-- Table structure for table `storages`
--

DROP TABLE IF EXISTS `storages`;
CREATE TABLE IF NOT EXISTS `storages` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(35) DEFAULT ' ',
  `location` varchar(35) DEFAULT ' ',
  `capacity` int(11) DEFAULT '0',
  `standby` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_name` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `storages`
--

INSERT INTO `storages` (`id`, `name`, `location`, `capacity`, `standby`) VALUES
(1, 'Εργαλεία', 'Σκαραμαγκάς', 500, 1),
(2, 'Ψυκτική', 'Ρέντη', 100, 1),
(3, 'Καυσίμων', 'Σκαραμαγκάς', 200, 1);

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
CREATE TABLE IF NOT EXISTS `suppliers` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(35) DEFAULT ' ',
  `surname` varchar(35) DEFAULT ' ',
  `company` varchar(35) DEFAULT ' ',
  `address` varchar(35) DEFAULT ' ',
  `phone` varchar(35) DEFAULT ' ',
  `email` varchar(35) DEFAULT ' ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`id`, `name`, `surname`, `company`, `address`, `phone`, `email`) VALUES
(1, 'Αλέξανδρος', 'Αντωνόπουλος', '', 'Χειμάρρας 18', '+30-210-569-2975', 'dantoni@gmail.com'),
(2, '', '', 'Alevizos SA', 'Καπνικαρέας 124', '+30-210-904-5987', 'sales@alevizos.gr'),
(3, '', '', 'Hellico', 'Αφιδνών 123', '+30-210-569-2111', 'info@hellico.gr');

-- --------------------------------------------------------

--
-- Table structure for table `userHasRoles`
--

DROP TABLE IF EXISTS `userHasRoles`;
CREATE TABLE IF NOT EXISTS `userHasRoles` (
  `userId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `roleId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`userId`,`roleId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `userHasRoles`
--

INSERT INTO `userHasRoles` (`userId`, `roleId`) VALUES
(1, 1),
(2, 5),
(4, 2),
(4, 5);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL DEFAULT '',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `surname` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `email` varchar(30) NOT NULL,
  `activated` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `name`, `surname`, `email`, `activated`) VALUES
(1, 'admin', 'admin', 'Κωνσταντίνος', 'Γιαννούσης', 'kgiann78@tedex.gr', 1),
(2, 'test', 'test', 'Τεστ', 'Τεστ', 'test@tedex.gr', 0),
(4, 'alex', 'alex', 'Alex', 'Alexopoulos', 'alex@tedex.gr', 0),
(5, 'oloimazi', 'kati', 'εγω', 'εσυ', 'kati', 0);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `productInStorage` FOREIGN KEY (`storageId`) REFERENCES `storages` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
