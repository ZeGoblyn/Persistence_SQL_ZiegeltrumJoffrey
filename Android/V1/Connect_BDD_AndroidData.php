<?php
define('SERVERNAME',"localhost");
define('USERNAME',"root");
define('PASSWORD',"");
define('DBNAME',"AndroidData");

function Connect()
{
//Technique MySQLi
    try
    {
      $this->$conn = new mysqli(SERVERNAME, USERNAME, PASSWORD, DBNAME); //Retourne mysqli_connect_erno()
      if (mysqli_connect_erno())
      {
        echo "Problème de connexion : Erreur " . mysqli_connect_err();
      }
    }
    catch (Exception $e)
    {
      echo "Échec de connexion : " . $e->getMessage();
    }


    return $this->$conn;
  }
?>