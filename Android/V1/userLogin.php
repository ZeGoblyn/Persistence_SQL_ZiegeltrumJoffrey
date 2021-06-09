<?php

			include_once (dirname(__FILE__).'\Ressources\DB_operation.php');

			$db = new db_operation();

			/*$response['status'] = false;
		    $response['error'] = true;
		    $response['message'] = "Bug initialisation";*/

			if($_SERVER["REQUEST_METHOD"] == "GET")
			{

				if (isset($_GET["Nom"]) && isset($_GET["Password"])) 
				{

			        $log = $db->UserLogin($_GET['Nom'],$_GET['Password']);
		            if ($log)
		            {
		            	$array=$db->GetUserByUserName($_GET['Nom']);
		                $response['status'] = true;
		                $response['error'] = false;
		                $response['message'] = "Connexion réussit";
		                $response['Name'] = $array['Username'];
		                $response['Mail'] = $array['Email'];
		            }
		            else
		            {
		                $response['status'] = false;
		                $response['error'] = false;
		                $response['message'] = "Couple nom d'utilisateur mot de passe invalide.";
		            }

			      //echo json_encode($response);
	    		}
			}

			echo json_encode($response);
?>