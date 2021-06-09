<?php
  
//Appelle de la constante.

  /**************************************************\
   *BUT     : 
   *ENTREE  :
   *SORTIE  :
  \**************************************************/
include 'Constantes_AndroidData.php';

class AndroidRepert
{    
  function __construct(){}

  private $conn; //Objet de connexion.

  function Connect() {
    $dsn="mysql:host=".SERVERNAME.";dbname=".DBNAME.";charset=UTF8";
    $this->conn = new mysqli(SERVERNAME, USERNAME, PASSWORD, DBNAME);
    //Retourne mysqli_connect_errno()
    if(mysqli_connect_errno()) {
        //echo("Problème de connexion : Erreur " . mysqli_connect_err());
    } else {
        //echo('Connexion reussie');
    }
    return $this->conn;
}
}
 
?>