<?php
$inizio = 12;
$fine  = 23;
$token = "763191121:AAHtYs_DNdnL-R0DITBtbU61msE0vhAG4kA";
$website= "https://api.telegram.org/bot".$token;




$keyboard = array(
"keyboard" => array(array(array(
"text" => "/button"

),
array(
"text" => "contact",
"request_contact" => true // This is OPTIONAL telegram button

),
array(
"text" => "location",
"request_location" => true // This is OPTIONAL telegram button

)

)), 
"one_time_keyboard" => true, // Can be FALSE (hide keyboard after click)
"resize_keyboard" => true // Can be FALSE (vertical resize)
); 

$postfields = array(
'chat_id' => "$chat_id",
'text' => "$reply",
'reply_markup' => json_encode($keyboard)
);



$messaggio= file_get_contents("php://input");
print_r($messaggio);
//preg_match('~\{(?:[^{}]|(?R))*\}~', $whole_html, $json);
//$arr = json_decode($json[0],true);
$arr = utf8_encode($arr);
$arr = json_decode($messaggio,true);
$testo = $arr["message"]["text"]; 
$chatID = $arr["message"]["chat"]["id"];


//
    $tastiera = ("&reply_markup={\"keyboard\":[[\"LEGGI\"],[\"Developer\"]]}");//={\'keyboard\'".":[[\"LEGGI\"],[\"Developer\"]]"."}";

// Legge API aws
$API= file_get_contents("https://p2pzmrtj84.execute-api.us-east-1.amazonaws.com/prod/helloworld");
//$temp = utf8_encode($temp);
$temp = json_decode($API, TRUE);
$body = "body";
//print_r($temp);

$articoli_lungo = substr($API, $inizio, 0-$fine);
//print_r("articoli lungo".$articoli_lungo);

$articoli = explode("¿º", $articoli_lungo);
$articoli = str_replace(['\\\u2014'],["-"], $articoli);
$articoli = str_replace(['\\\u2019','\\\u201c', '\\\u201d'],["'"], $articoli);

print_r($articoli[0]);
//print_r("articoli lungo".$articoli_lungo);
$text =$articoli[0];


 


sendMessage($chatID, $articoli);
$tastiera2 = ('&reply_markup={"keyboard ":[["LEGGI"],["Developer"]]}');
$tastiera = "%7B%22keyboard+%22%3A%5B%5B%22LEGGI%22%5D%2C%5B%22Developer%22%5D%5D%7D";

$url2 = $GLOBALS[website]."/sendMessage?chat_id=".$chatID."&text=zzz"."&reply_markup=".$tastiera;
	file_get_contents($url2);


function sendMessage($chatID, $message){
$finish = count($message);
for ($index = 0; $index <= 1; ++$index) {
$completa  = substr($message[$index], 2);
$url = $GLOBALS[website]."/sendMessage?chat_id=".$chatID."&text=".urlencode($completa)."&parseMode=HTML";
	file_get_contents($url);
    sleep(3);
}



}


//https://api.telegram.org/bot763191121:AAHtYs_DNdnL-R0DITBtbU61msE0vhAG4kA
//https://api.telegram.org/bot763191121:AAHtYs_DNdnL-R0DITBtbU61msE0vhAG4kA/
//setWebHook?url=https://botsottomarino.altervista.org/example.php
?>
