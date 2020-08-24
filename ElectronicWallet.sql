-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Aug 23, 2020 at 05:29 PM
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

--
-- Dumping data for table `Transaction`
--

INSERT INTO `Transaction` (`Id`, `SenderId`, `ReceiverId`, `Date`, `Remarks`, `Amount`, `TransactionType`) VALUES
(1, 1, 2, '2020-08-20 04:11:58', 'First Payment', 1000, 'Transfer'),
(2, 2, 1, '2020-08-20 04:31:35', 'payment to shanto', 5100, 'Transfer'),
(3, 11, 1, '2020-08-20 04:35:33', 'to shanto from rahman', 20, 'Transfer'),
(4, 1, 2, '2020-08-20 16:29:53', 'jnk', 0.01, 'Transfer'),
(5, 1, 2, '2020-08-21 06:02:20', 'Due', 500, 'Transfer'),
(6, 17, 2, '2020-08-22 18:14:42', 'Balanced Recharged approved by Admin.', 300, 'Recharge'),
(7, 17, 1, '2020-08-22 18:15:47', 'Balanced Recharged approved by Admin.', 5000, 'Recharge'),
(8, 17, 1, '2020-08-22 18:16:34', 'Balanced Recharged approved by Admin.', 2000, 'Recharge'),
(9, 1, 2, '2020-08-22 18:23:33', 'Payment Due', 2000, 'Transfer'),
(10, 2, 1, '2020-08-22 18:26:14', 'Gift', 10000, 'Transfer'),
(11, 2, 1, '2020-08-22 18:26:57', 'Gift2', 5000, 'Transfer'),
(12, 2, 1, '2020-08-22 18:28:00', 'Due', 200, 'Transfer'),
(13, 1, 2, '2020-08-22 18:34:48', 'mobile bill', 19.99, 'Transfer'),
(14, 1, 2, '2020-08-23 05:11:22', 'second payment', 1000, 'Transfer'),
(15, 17, 20, '2020-08-23 16:29:38', 'Balanced Recharged approved by Admin.', 10000, 'Recharge'),
(16, 20, 1, '2020-08-23 16:35:33', 'Eid Salami', 10000, 'Transfer');

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

INSERT INTO `User` (`Id`, `UserName`, `Password`, `Name`, `PhoneNumber`, `Address`, `Balance`, `Gender`, `Status`, `UserType`) VALUES
(1, 'shanto', '123', 'Shanto Siddiq', '01799752044', 'Keranigonj,Dhaka-1310.', 68000, 'Male', 'Active', 'User'),
(2, 'farhad', '123', 'Hossain Md. Farhad', '01777086265', 'dont know right now', 436020, 'Male', 'Active', 'User'),
(10, 'mobinur', '123', 'Mobinur Rahman', '01793453123', 'kearanigonj, Dhaka, Bangladesh.\n', 100, 'Male', 'Active', 'User'),
(11, 'rahman', '1234', 'rahman', '01290328123', 'adsfgsagsdgasdfasdf', 80, 'Male', 'Active', 'User'),
(13, 'salam', '123', 'Salam', '123123123', 'ascafvfqwe', 100, 'Male', 'Active', 'User'),
(17, 'admin', 'admin', 'Admin', '01712345678', 'some address', NULL, 'Male', 'Active', 'Admin'),
(18, 'jb', 'jb123', 'Jobbar', '01924466947', 'Gulshan', 100, 'Male', 'Active', 'User'),
(20, 'kivran', '1234', 'Kivran', '01111111111', 'asdhbasjdasdasd', 100, 'Male', 'Active', 'User');

-- --------------------------------------------------------

--
-- Table structure for table `UserBalanceRecharge`
--

CREATE TABLE `UserBalanceRecharge` (
  `Id` int NOT NULL,
  `UserId` int NOT NULL,
  `RechargeAmount` double NOT NULL,
  `RechargeDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `RechargeStatus` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `UserBalanceRecharge`
--

INSERT INTO `UserBalanceRecharge` (`Id`, `UserId`, `RechargeAmount`, `RechargeDate`, `RechargeStatus`) VALUES
(4, 2, 1500, '2020-08-22 01:09:41', 'Pending'),
(6, 2, 300, '2020-08-22 12:32:58', 'Success'),
(7, 1, 5000, '2020-08-22 14:02:02', 'Success'),
(8, 1, 2000, '2020-08-22 14:02:10', 'Success'),
(9, 1, 300, '2020-08-22 15:36:56', 'Pending'),
(10, 1, 500, '2020-08-22 15:37:01', 'Pending'),
(11, 1, 600, '2020-08-22 15:37:04', 'Pending'),
(12, 20, 10000, '2020-08-23 16:29:05', 'Success');

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
-- Indexes for table `UserBalanceRecharge`
--
ALTER TABLE `UserBalanceRecharge`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Transaction`
--
ALTER TABLE `Transaction`
  MODIFY `Id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `User`
--
ALTER TABLE `User`
  MODIFY `Id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `UserBalanceRecharge`
--
ALTER TABLE `UserBalanceRecharge`
  MODIFY `Id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
