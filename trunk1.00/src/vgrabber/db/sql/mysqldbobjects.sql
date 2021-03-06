-- phpMyAdmin SQL Dump
-- version 2.8.1
-- http://www.phpmyadmin.net
-- 
-- Host: localhost
-- Generation Time: Mar 06, 2007 at 08:17 PM
-- Server version: 5.0.22
-- PHP Version: 5.2.0-dev
-- 
-- Database: `imobil5`
-- 

-- --------------------------------------------------------

-- 
-- Table structure for table `anunt`
-- 

CREATE TABLE `anunt` (
  `id` int(11) NOT NULL auto_increment,
  `editie_id` int(11) NOT NULL,
  `categorie_id` int(11) NOT NULL,
  `regiune_id` int(11) default NULL,
  `anunt` text NOT NULL,
  `data` datetime NOT NULL,
  `interested` bit(1) NOT NULL default '\0',
  PRIMARY KEY  (`id`),
  KEY `categorie_id` (`categorie_id`),
  KEY `editie_id` (`editie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=14936 ;

-- --------------------------------------------------------

-- 
-- Table structure for table `categ`
-- 

CREATE TABLE `categ` (
  `id` int(11) NOT NULL,
  `parent_id` int(11) default NULL,
  `nume` varchar(100) NOT NULL,
  `toupdate` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

-- 
-- Table structure for table `categorie`
-- 

CREATE TABLE `categorie` (
  `id` int(11) NOT NULL,
  `parent_id` int(11) default NULL,
  `nume` varchar(100) NOT NULL,
  `toupdate` bit(1) NOT NULL default '\0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

-- 
-- Table structure for table `categorie1`
-- 

CREATE TABLE `categorie1` (
  `id` int(11) NOT NULL,
  `parent_id` int(11) default NULL,
  `nume` varchar(100) NOT NULL,
  `toupdate` bit(1) NOT NULL default '\0',
  PRIMARY KEY  (`id`),
  KEY `parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

-- 
-- Table structure for table `contact`
-- 

CREATE TABLE `contact` (
  `id` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `note` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

-- 
-- Table structure for table `contact_anunt`
-- 

CREATE TABLE `contact_anunt` (
  `id` int(11) NOT NULL auto_increment,
  `contact_id` varchar(50) NOT NULL,
  `anunt_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `anunt_id` (`anunt_id`),
  KEY `contact_id` (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=18928 ;

-- --------------------------------------------------------

-- 
-- Table structure for table `editie`
-- 

CREATE TABLE `editie` (
  `id` int(11) NOT NULL,
  `data` date default NULL,
  `note` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

-- 
-- Table structure for table `regiune`
-- 

CREATE TABLE `regiune` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

-- 
-- Table structure for table `regiune_filtru`
-- 

CREATE TABLE `regiune_filtru` (
  `id` int(11) NOT NULL auto_increment,
  `regiune_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- 
-- Constraints for dumped tables
-- 

-- 
-- Constraints for table `anunt`
-- 
ALTER TABLE `anunt`
  ADD CONSTRAINT `FK_anunt_editie` FOREIGN KEY (`editie_id`) REFERENCES `editie` (`id`),
  ADD CONSTRAINT `FK_anunt_Categorie` FOREIGN KEY (`categorie_id`) REFERENCES `categorie1` (`id`);

-- 
-- Constraints for table `categorie1`
-- 
ALTER TABLE `categorie1`
  ADD CONSTRAINT `FK_Categorie_Categorie` FOREIGN KEY (`parent_id`) REFERENCES `categorie1` (`id`);

-- 
-- Constraints for table `contact_anunt`
-- 
ALTER TABLE `contact_anunt`
  ADD CONSTRAINT `FK_contact_anunt_contact` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`id`),
  ADD CONSTRAINT `FK_contact_anunt_anunt` FOREIGN KEY (`anunt_id`) REFERENCES `anunt` (`id`);
