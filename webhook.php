<?php
//Parse error: syntax error, unexpected '}', expecting end of file in /membri/botsottomarino/api/example.php on line 95
$inizio = 12;
$fine  = 23;
$token = "763191121:AAHtYs_DNdnL-R0DITBtbU61msE0vhAG4kA";
$website= "https://api.telegram.org/bot".$token;
$servername = "localhost";
$username = "botsottomarino";
$password = "password";
$dbname = "my_botsottomarino";
$articoli = array();
//while ($row = mysqli_array($table, mysqli_


//https://stackoverflow.com/questions/7491768/get-rows-from-mysql-table-to-php-arrays


$messaggio= file_get_contents("php://input");

$arr = utf8_encode($arr);
$arr = json_decode($messaggio,true);
$testo = $arr["message"]["text"]; 
$chatID = $arr["message"]["chat"]["id"];

//
    $tastiera = ("&reply_markup={\"keyboard\":[[\"LEGGI\"],[\"Developer\"]]}");//={\'keyboard\'".":[[\"LEGGI\"],[\"Developer\"]]"."}";

//TASTIERA
if(strcmp($testo, '/start') == 0){

$tastiera2 = json_encode(array( 
								"keyboard" => array(
                                					array("Leggi")),
                                "resize_keyboard" => TRUE) );
$tastiera3 = urlencode($tastiera2);

$start = "Benvenuto nel bot, premere l'unico tasto per ricevere thesubmarine. Riavviare il bot in caso di sbatti";
$url2 = $GLOBALS[website]."/sendMessage?chat_id=".$chatID."&text=".urlencode($start)."&reply_markup=".$tastiera2;
	file_get_contents($url2);
    }
    


//AGGIORNA 
//DELETE FROM `articoli` WHERE 1
if(strcmp($testo, '/update') == 0)   {

// Legge API aws
$API= file_get_contents("https://p2pzmrtj84.execute-api.us-east-1.amazonaws.com/prod/helloworld");
//$temp = utf8_encode($temp);
$temp = json_decode($API, TRUE);
$body = "body";
//print_r($temp);

$articoli_lungo = substr($API, $inizio, 0-$fine);
//print_r("articoli lungo".$articoli_lungo);

$articoli = explode("¿º", $articoli_lungo);
//print_r ($articoli[0]."articolo 0");
//$articoli = str_replace(['\\\u2014'],["-"], $articoli);
//$articoli = str_replace(['\\\u2019','\\\u201c', '\\\u201d'],["'"], $articoli);
/*
$f = count($articoli);
for ($index = 0; $index <= $f; $index++) {
print_r($articoli[$index]."index=".$index."\n");
}*/ // stampa tutti gli articoli

//print_r($articoli[0]);
//print_r("articoli lungo".$articoli_lungo);
//$text =$articoli[0];

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
$sqlD ="DELETE FROM `articoli` WHERE 1";
$clean = $conn->query($sqlD);



$f = count($articoli);
$sql="";
for ($index = 0; $index <= $f; $index++) {
 $art  = substr($articoli[$index], 2);
 
 if(0==0){
 	//$result=$conn->query($sql);
    $res=mysqli_query($conn,$sql);
    //////////
    if($res){
		$url = $GLOBALS[website]."/sendMessage?chat_id=".$chatID."&text=".urlencode("riuscito")."&parseMode=HTML";
		file_get_contents($url);
    }
 	else{
    $culo= mysqli_error($conn);
 //$last = substr($sql, (strlen($sql)-30));
 		$url = $GLOBALS[website]."/sendMessage?chat_id=".$chatID."&text=".urlencode($culo."ERRORE")."&parseMode=HTML";
		file_get_contents($url);
 	}
    //////////
    
    $sql="";
 }
 $sql =$sql."INSERT INTO `articoli`(`Testo`, `id`) VALUES ("."'".$art."'".",".$index.");";
}
    //sleep(3);
$url = $GLOBALS[website]."/sendMessage?chat_id=".$chatID."&text=".urlencode("Fine")."&parseMode=HTML";
		file_get_contents($url);

 //articoli contiene array di tutti gli articoli

//$articoli = array(); // make a new array to hold all your data
////////////////////////////////////////////////////////////////////////
}


//INVIA 
if((strcmp($testo, '/start') !== 0) && (strcmp($testo, '/update') !== 0)){
 //RECUPERA ARTICOLI DA DATABASE


$welcome = "Questa è Hello, World!, la nostra rassegna mattiniera di attualità, cultura e internet. Tutte le mattine, un pugno di link da leggere, vedere e ascoltare.";

$url = $GLOBALS[website]."/sendMessage?chat_id=".$chatID."&text=".urlencode($welcome)."&parseMode=HTML";
	file_get_contents($url);

$conn = new mysqli($servername, $username, $password, $dbname);
$sql2 ="SELECT `Testo` FROM `articoli` WHERE 1";
$result = $conn->query($sql2);
$i = 0;
while($row = $result->fetch_array(MYSQLI_ASSOC))
{
$articoli[$i] =  $row['Testo'];
$i++;


}

sendMessage($chatID, $articoli);

}

function sendMessage($chatID, $message){
$finish = count($message);
for ($index = 0; $index <= $finish; ++$index) {
//$completa  = substr($message[$index], 2);

$temporanea = str_replace(['\\u2014'],["-"], $message[$index]);
$completo = str_replace(['\\u2019','\\u201c', '\\u201d'],["'"], $temporanea);
$url = $GLOBALS[website]."/sendMessage?chat_id=".$chatID."&text=".urlencode($completo)."&parseMode=HTML";
	file_get_contents($url);
    sleep(3);
}
}
function resultToArray($result) {
    $rows = array();
    while($row = $result->fetch_assoc()) {
        $rows[] = $row;
    }
    return $rows;
}






//https://api.telegram.org/bot763191121:AAHtYs_DNdnL-R0DITBtbU61msE0vhAG4kA
//https://api.telegram.org/bot763191121:AAHtYs_DNdnL-R0DITBtbU61msE0vhAG4kA/
//setWebHook?url=https://botsottomarino.altervista.org/example.php
?>
