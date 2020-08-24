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
(16, 20, 1, '2020-08-23 16:35:33', 'Eid Salami', 10000, 'Transfer'),
(17, 17, 1, '2020-08-23 18:39:34', 'Balanced Recharged approved by Admin.', 20, 'Recharge'),
(18, 17, 1, '2020-08-23 18:43:50', 'Balanced Recharged approved by Admin.', 10, 'Recharge'),
(19, 17, 1, '2020-08-23 21:10:24', 'Balanced Recharged approved by Admin.', 50, 'Recharge'),
(27, 20, 1, '2020-08-23 21:59:50', 'Due1', 20, 'Transfer'),
(29, 17, 20, '2020-08-23 23:20:01', 'Balanced Recharged approved by Admin.', 20, 'Recharge'),
(30, 20, 1, '2020-08-23 23:20:51', 'Due2', 20, 'Transfer'),
(31, 20, 17, '2020-08-23 23:47:54', 'Balance Cash Out approved by Admin.', 30, 'CashOut'),
(32, 20, 17, '2020-08-23 23:48:55', 'Balance Cash Out approved by Admin.', 30, 'CashOut'),
(33, 20, 17, '2020-08-23 23:49:06', 'Balance Cash Out approved by Admin.', 20, 'CashOut'),
(34, 17, 20, '2020-08-23 23:49:17', 'Balanced Recharged approved by Admin.', 1, 'Recharge'),
(35, 17, 20, '2020-08-23 23:49:50', 'Balanced Recharged approved by Admin.', 1000, 'Recharge'),
(36, 17, 20, '2020-08-24 00:00:14', 'Balanced Recharged approved by Admin.', 20, 'Recharge'),
(37, 20, 1, '2020-08-24 00:02:04', 'yoo money', 20, 'Transfer');

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
(1, 'shanto', '123', 'Shanto Siddiq', '01799752044', 'Keranigonj,Dhaka-1310.', 68920, 'Male', 'Active', 'User'),
(2, 'farhad', '123', 'Hossain Md. Farhad', '01777086265', 'dont know right now', 436020, 'Male', 'Active', 'User'),
(10, 'mobinur', '123', 'Mobinur Rahman', '01793453123', 'kearanigonj, Dhaka, Bangladesh.\n', 100, 'Male', 'Active', 'User'),
(11, 'rahman', '1234', 'rahman', '01290328123', 'adsfgsagsdgasdfasdf', 80, 'Male', 'Active', 'User'),
(13, 'salam', '123', 'Salam', '123123123', 'ascafvfqwe', 100, 'Male', 'Active', 'User'),
(17, 'admin', 'admin', 'Admin', '01712345678', 'some address', NULL, 'Male', 'Active', 'Admin'),
(18, 'jb', 'jb123', 'Jobbar', '01924466947', 'Gulshan', 100, 'Male', 'Active', 'User'),
(20, 'kivran', '1234', 'Kivran', '01111111111', 'asdhbasjdasdasd', 1001, 'Male', 'Active', 'User');

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
-- Dumping data for table `UserRequest`
--

INSERT INTO `UserRequest` (`Id`, `UserId`, `RequestAmount`, `RequestDate`, `RequestStatus`, `RequestType`) VALUES
(4, 2, 1500, '2020-08-22 01:09:41', 'Pending', 'Recharge'),
(6, 2, 300, '2020-08-22 12:32:58', 'Success', 'Recharge'),
(7, 1, 5000, '2020-08-22 14:02:02', 'Success', 'Recharge'),
(8, 1, 2000, '2020-08-22 14:02:10', 'Success', 'Recharge'),
(9, 1, 300, '2020-08-22 15:36:56', 'Pending', 'Recharge'),
(10, 1, 500, '2020-08-22 15:37:01', 'Pending', 'Recharge'),
(11, 1, 600, '2020-08-22 15:37:04', 'Pending', 'Recharge'),
(12, 20, 10000, '2020-08-23 16:29:05', 'Success', 'Recharge'),
(13, 1, 20, '2020-08-23 18:38:54', 'Success', 'Recharge'),
(14, 1, 10, '2020-08-23 18:43:44', 'Success', 'Recharge'),
(15, 1, 500, '2020-08-23 18:48:49', 'Pending', 'Recharge'),
(16, 1, 50, '2020-08-23 21:10:08', 'Success', 'Recharge'),
(17, 20, 1, '2020-08-23 22:05:19', 'Success', 'Recharge'),
(18, 20, 30, '2020-08-23 22:05:51', 'Success', 'CashOut'),
(19, 20, 30, '2020-08-23 22:44:18', 'Success', 'CashOut'),
(20, 20, 20, '2020-08-23 22:46:23', 'Success', 'CashOut'),
(21, 20, 20, '2020-08-23 23:19:34', 'Success', 'Recharge'),
(22, 20, 1000, '2020-08-23 23:49:42', 'Success', 'Recharge'),
(23, 20, 1001, '2020-08-23 23:59:21', 'Pending', 'CashOut'),
(24, 20, 20, '2020-08-24 00:00:02', 'Success', 'Recharge'),
(25, 1, 920, '2020-08-24 00:15:27', 'Pending', 'CashOut');

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
