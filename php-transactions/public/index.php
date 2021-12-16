<?php

declare(strict_types = 1);

$root = dirname(__DIR__) . DIRECTORY_SEPARATOR;

define('APP_PATH', $root . 'app' . DIRECTORY_SEPARATOR);
define('FILES_PATH', $root . 'transaction_files' . DIRECTORY_SEPARATOR);
define('VIEWS_PATH', $root . 'views' . DIRECTORY_SEPARATOR);

/* YOUR CODE (Instructions in README.md) */

require APP_PATH . "App.php";
require APP_PATH . "helpers.php";

// Read and process file
// change file path and transaction handler for different file format
$files = getTransactionFiles(FILES_PATH);

// Store data in memory (in an array)
$transactions = [];
foreach ($files as $file) {
    $transactions = array_merge($transactions, getTransactions($file, "extractTransaction"));
}

// Calculate the total income, total expense & net total (total income - total expense)
$totals = calculateTotals($transactions);

require VIEWS_PATH . "transactions.php";