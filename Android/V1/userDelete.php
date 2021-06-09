<?php

			include_once (dirname(__FILE__).'\Ressources\DB_operation.php');

			$db = new db_operation();

			/*$response['status'] = false;
		    $response['error'] = true;
		    $response['message'] = "Bug initialisation";*/

			if($_SERVER["REQUEST_METHOD"] == "POST")
			{

				if (isset($_POST["Nom"])) 
				{

			        $resultPass = $db->delete($_POST['Nom']);
			        if($resultPass)
			        {
			        	$response['message'] ="Membre supprimer ";
			        }else{
			        	$response['message'] ="Echec de la surpression ";
			        }

			      //echo json_encode($response);
	    		}
			}

			echo json_encode($response);
?>