<?php

			//CE FICHIER EST UN WEB SERVICE

			//include 'DB_Connect.php';
			include_once (dirname(__FILE__).'\Ressources\DB_operation.php');

			//$testVar = new AndroidRepert();
			//$DB = $testVar->Connect();
			$db = new db_operation();


			//echo dirname(__FILE__);

			/*if (isset($_GET["Nom"]) && isset($_GET["Password"]) && isset($_GET["mail"]))
			{
				$db->createUser($_GET['Nom'], $_GET['Password'], $_GET['mail']);
			}*/



			/*if (isset($_GET["NomLog"]) && isset($_GET["PasswordLog"]))
				{
					if($db->UserLogin($_GET['NomLog'], $_GET['PasswordLog']))
				{
					echo "Utilisateur trouver";
				}else{
					echo "Utilisateur NON trouver";
				}
			}

			if (isset($_GET["NomLog"]))
			{
				$db->GetUserByUsername($_GET['NomLog']);
			}*/


			if($_SERVER["REQUEST_METHOD"] == "POST")
			{
				if (isset($_POST["Nom"]) && isset($_POST["Password"]) && isset($_POST["mail"]) && isset($_POST["local"]) && isset($_POST["ddn"])) 
				{
			        $create = $db->createUser($_POST['Nom'],$_POST['Password'],$_POST['mail'],$_POST['local'],$_POST['ddn']);
			        switch($create) {
			            case 0:
			                $response = array(
			                    'status' => false,
			                    'error' => true,
			                    'message' => 'Ce nom d\'utilisateur ou cette adresse mail sont deja utilises'
			                );
			                break;
			            case 1:
			                $response = array(
			                    'status' => true,
			                    'error' => false,
			                    'message' => 'L\'utilisateur a ete cree'
			                );
			                break;
			            case 2:
			                $response = array(
			                    'status' => false,
			                    'error' => true,
			                    'message' => 'Erreur d\'insertion, veuillez reesayer.'
			                );
			                break;
			        }

			        echo json_encode($response);
	    		}
			}
			

			

		/*if(isset($reponse)) 
		{
		    if($reponse['error'] == true) {
		        echo('
		            <div style="color:#FF0000;">'
		                .$reponse['message'].
		            '</div>
		        ');
		    } else {
		        echo('
		            <div style="color:#00FF00;">'
		                .$reponse['message'].
		            '</div>
		        ');
		    }
		}*/

		?>