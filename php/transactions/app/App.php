<?php

declare(strict_types = 1);

// Your Code

// get files (only file name)
function getTransactionFiles(string $dirPath): array
{
    $files = [];
    foreach(scandir($dirPath) as $file) {
        if(is_dir($file)) continue;
        $files[] = $dirPath . $file;
    }
    return $files;
}

// get transaction data from file
// change to ?callable $transactionHandler = null
// to define transaction handler for different file formats
function getTransactions(string $fileName, ?callable $transactionHandler = null): array
{
    if(!file_exists($fileName)) trigger_error("File does not exist", E_USER_ERROR);

    $file = fopen($fileName, "r");
    fgetcsv($file); // discard the header row
    $transactions = [];
    while (($transaction = fgetcsv($file)) !== false) {
        if($transactionHandler !== null) {
            $transactions[] = $transactionHandler($transaction);
        }
    }
    return $transactions;
}

// extract transaction
function extractTransaction(array $transactionRow): array
{
    // assumed that files are in the same format
    // extract in certain order
    [$date, $checkNumber, $description, $amount] = $transactionRow;
    $amount = (float) str_replace(["$", ","], "", $amount) ;
    return [
        "date" => $date,
        "checkNumber" => $checkNumber,
        "description" => $description,
        "amount" => $amount
    ];
}

function calculateTotals(array $transactions): array
{
    $totals = [
        "netTotal" => 0,
        "totalIncome" => 0,
        "totalExpense" => 0
    ];
    foreach($transactions as $transaction) {
        $totals["netTotal"] += $transaction["amount"];
        if($transaction["amount"] >= 0) {
            $totals["totalIncome"] += $transaction["amount"];
        } else {
            $totals["totalExpense"] += $transaction["amount"];
        }
    }
    return $totals;
}
