-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Aug 24, 2020 at 12:18 AM
-- Server version: 8.0.20
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ElectronicWallet`
--

-- --------------------------------------------------------

--
-- Table structure for table `Transaction`
--

CREATE TABLE `Transaction` (
  `Id` int NOT NULL,
  `SenderId` int NOT NULL,
  `ReceiverId` int NOT NULL,
  `Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Remarks` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `Amount` double NOT NULL,
  `TransactionType` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE `User` (
  `Id` int NOT NULL,
  `UserName` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `PhoneNumber` varchar(30) DEFAULT NULL,
  `Address` text,
  `Balance` double DEFAULT NULL,
  `Gender` varchar(20) DEFAULT NULL,
  `Status` varchar(30) DEFAULT NULL,
  `UserType` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`UserName`, `Password`, `Name`, `PhoneNumber`, `Address`, `Balance`, `Gender`, `Status`, `UserType`) VALUES
('admin', 'admin', 'Admin', '01712345678', 'some address', NULL, 'Male', 'Active', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `UserRequest`
--

CREATE TABLE `UserRequest` (
  `Id` int NOT NULL,
  `UserId` int NOT NULL,
  `RequestAmount` double NOT NULL,
  `RequestDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `RequestStatus` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `RequestType` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Transaction`
--
ALTER TABLE `Transaction`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `UserRequest`
--
ALTER TABLE `UserRequest`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Transaction`
--
ALTER TABLE `Transaction`
  MODIFY `Id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT for table `User`
--
ALTER TABLE `User`
  MODIFY `Id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `UserRequest`
--
ALTER TABLE `UserRequest`
  MODIFY `Id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
