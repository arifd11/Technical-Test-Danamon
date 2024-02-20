-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Feb 20, 2024 at 04:36 PM
-- Server version: 8.0.24
-- PHP Version: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `technical_test`
--

-- --------------------------------------------------------

--
-- Table structure for table `keys`
--

CREATE TABLE `keys` (
  `id` char(36) NOT NULL,
  `api_key` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `keys`
--

INSERT INTO `keys` (`id`, `api_key`) VALUES
('bcc8eee9-cdae-11ee-8bf6-6045bd56ef3b', 'technicalTestArif2024');

-- --------------------------------------------------------

--
-- Table structure for table `t_user`
--

CREATE TABLE `t_user` (
  `id` int NOT NULL,
  `username` varchar(100) NOT NULL,
  `email` varchar(125) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('admin','normal') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `t_user`
--

INSERT INTO `t_user` (`id`, `username`, `email`, `name`, `password`, `role`, `created_at`, `updated_at`) VALUES
(1, 'arif', 'arif@gmail.com', 'Arif', '$2y$10$FPvatDi8ACubXSM9OQ9A5OOfesj2qgoJn20.X4jCElcMMCk1ORAUu', 'admin', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(2, 'demo', 'demo@gmail.com', 'Demo Account', '$2y$10$fRh0DX7qLzY7.qRWGCtxkuwoWC4u7BZkRqYDTI84jXZfTIUU1aq3q', 'normal', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(3, 'test', 'test@gmail.com', 'Test', '$2y$12$F0j/KPn0JxY.YgW1cRCFH.MF2vk95OWY4FW.txvxZEbF3LF3vWkc2', 'normal', '2024-02-17 17:08:17', '2024-02-17 17:08:17'),
(4, 'test2', 'test2@gmail.com', 'Test 2', '$2y$12$qadxAgakYZt/Z0ocAJEJ..rtSc4DAMbXo5ilfM69w8mGfWupKALh.', 'normal', '2024-02-17 21:34:19', '2024-02-17 21:34:19'),
(5, 'test1', 'test1@gmail.com', 'test1', '$2y$12$ztYbtissHxOKft8OB2Tyhupebe.U1X9VZDosTI89emfqmC649AaKO', 'normal', '2024-02-17 21:58:42', '2024-02-17 21:58:42'),
(6, 'test3', 'test3@gmail.com', 'Test 3', '$2y$12$/7BZ7PC9Fl6ZDHQtcghWs.0GZdP7WDh1ngKOokfWQFKs07A9a8jf.', 'normal', '2024-02-19 04:09:24', '2024-02-19 04:09:24'),
(7, 'test4', 'test4@gmail.com', 'Test 4', '$2y$12$tc7U0Ca2asqIKme1pgaxZuZrlUVV84eITFi3G8zpyLPx49HAzdupO', 'normal', '2024-02-19 04:09:31', '2024-02-19 04:09:31');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `keys`
--
ALTER TABLE `keys`
  ADD KEY `id` (`id`);

--
-- Indexes for table `t_user`
--
ALTER TABLE `t_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `t_user`
--
ALTER TABLE `t_user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
