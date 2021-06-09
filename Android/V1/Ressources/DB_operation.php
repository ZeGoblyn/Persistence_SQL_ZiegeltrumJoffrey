<?php
  
//Appelle de la constante.

  /**************************************************\
   *BUT     : 
   *ENTREE  :
   *SORTIE  :
  \**************************************************/
//include 'Constantes_AndroidData.php';


include_once ('DB_Connect.php');



class db_operation {

    private $context;

    function __construct() 
    {
        $db = new AndroidRepert();
        $this->context = $db->Connect();
    }

    /*function Connect() {
    $dsn="mysql:host=".SERVERNAME.";dbname=".DBNAME.";charset=UTF8";
    $this->context = new mysqli(SERVERNAME, USERNAME, PASSWORD, DBNAME);
    //Retourne mysqli_connect_errno()
    if(mysqli_connect_errno()) {
        echo("Problème de connexion : Erreur " . mysqli_connect_err());
    } else {
        echo('Connexion reussie');
    }
    return $this->context;
}*/

    function createUser($username, $password, $email, $localite, $ddn)
      {

        try
            {
                $stmt = $this->context->prepare("INSERT INTO USERS (Username, Password, Email, Localite, DateDeNaissance) VALUES (?, ?, ?, ?, ?)");
            }
            catch (Exception $e)
            {
                  echo "Erreur préparation : ".$e->getMessage();
               }

            $stmt->bind_param("sssss", $username, $password, $email, $localite, $ddn);

            if($stmt->execute())
            {
              //echo "JaaJ";
              return 1;
            }else{
              //echo "Ne-Nein";
              return 2;
            }
      }

    function UserLogin($username, $pass)
      {
          $req = "SELECT * FROM USERS where Username = ? AND Password = ?;";
          $password = $pass;

          //Envoie de la requête à la base
          try
          {
              $stmt = $this->context->prepare($req);

              $stmt->bind_param("ss", $username, $password);

              $stmt->execute();
              $stmt->store_result();
              return $stmt->num_rows() > 0;

          }
          catch(PDOException $error)
          {
              //echo "<script>console.log('".$error->getMessage()."')</script>";
              //exit();
          }
      }

      function GetUserByUsername($username)
      {
          $req = "SELECT * FROM USERS where Username = ?;";

          //Envoie de la requête à la base
          try
          {
              $stmt = $this->context->prepare($req);

              $stmt->bind_param("s", $username);

              $stmt->execute();
              $array = $stmt->get_result()->fetch_assoc();

              /*echo "<br>";
              foreach ($array as $key => $value)
              {
                  echo $key." : ";
                  echo $value."<br>";
              }*/

              $stmt->store_result();
              //return $stmt->num_rows()>0;
              return $array;
          }
          catch(PDOException $error)
          {
              //echo "<script>console.log('".$error->getMessage()."')</script>";
              exit();
          }
      }

      function updatePass($username, $password)
      {
       

         try
            {
                $req = "UPDATE USERS SET Password = ? where Username = ?;";
            }
            catch (Exception $e)
            {
                  echo "Erreur préparation : ".$e->getMessage();
               }

            $stmt = $this->context->prepare($req);

            $stmt->bind_param("ss", $password, $username);

            if($stmt->execute())
            {
              //echo "JaaJ";
              return 1;
            }else{
              //echo "Ne-Nein";
              return 0;
            }
      }

      function updateMail($username, $email)
      {
       

         try
            {
                $req = "UPDATE USERS SET Email = ? where Username = ?;";
            }
            catch (Exception $e)
            {
                  echo "Erreur préparation : ".$e->getMessage();
               }
            $stmt = $this->context->prepare($req);

            $stmt->bind_param("ss", $email, $username);

            if($stmt->execute())
            {
              //echo "JaaJ";
              return 1;
            }else{
              //echo "Ne-Nein";
              return 0;
            }
      }

      function updateLocal($username, $local)
      {
       

         try
            {
                $req = "UPDATE USERS SET Localite = ? where Username = ?;";
            }
            catch (Exception $e)
            {
                  echo "Erreur préparation : ".$e->getMessage();
               }
            $stmt = $this->context->prepare($req);

            $stmt->bind_param("ss", $Localite, $username);

            if($stmt->execute())
            {
              //echo "JaaJ";
              return 1;
            }else{
              //echo "Ne-Nein";
              return 0;
            }
      }

      function updateDate($username, $date)
      {
       

         try
            {
                $req = "UPDATE USERS SET DateDeNaissance = ? where Username = ?;";
            }
            catch (Exception $e)
            {
                  echo "Erreur préparation : ".$e->getMessage();
               }
            $stmt = $this->context->prepare($req);

            $stmt->bind_param("ss", $DDn, $username);

            if($stmt->execute())
            {
              //echo "JaaJ";
              return 1;
            }else{
              //echo "Ne-Nein";
              return 0;
            }
      }

      function delete($username)
      {
        try
            {
                $req = "DELETE FROM USERS WHERE Username = ?;";
            }
            catch (Exception $e)
            {
                  echo "Erreur préparation : ".$e->getMessage();
               }
            $stmt = $this->context->prepare($req);

            $stmt->bind_param("s", $username);

            if($stmt->execute())
            {
              //echo "JaaJ";
              return 1;
            }else{
              //echo "Ne-Nein";
              return 0;
            }
      }

      function getUsersByCriteria($username, $mail, $local)
      {
        try
            {
                $req = "SELECT * FROM USERS WHERE (UPPER(Username) LIKE UPPER('%".$username."%')) AND (UPPER(Email) LIKE UPPER('%".$mail."%')) AND (UPPER(Localite) LIKE UPPER('%".$local."%'))";
            }
            catch (Exception $e)
            {
                  echo "Erreur préparation : ".$e->getMessage();
               }

            $stmt = $this->context->prepare($req);

            if($stmt->execute())
            {
              //echo "JaaJ";
              $result = $stmt->get_result();

              $arrayResult = array();

              while ($row = $result->fetch_assoc()) 
              {
                array_push($arrayResult,$row);
              }

              return $arrayResult;;
            }else{
              //echo "Ne-Nein";
              return 0;
            }
      }

}



 /* private $conn; //Objet de connexion.

  function Connect() {
    $dsn="mysql:host=".SERVERNAME.";dbname=".DBNAME.";charset=UTF8";
    $this->conn = new mysqli(SERVERNAME, USERNAME, PASSWORD, DBNAME);
    //Retourne mysqli_connect_errno()
    if(mysqli_connect_errno()) {
        echo("Problème de connexion : Erreur " . mysqli_connect_err());
    } else {
        echo('Connexion reussie');
    }
    return $this->conn;
}
}*/
 
?>