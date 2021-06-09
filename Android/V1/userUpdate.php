<?php

			include_once (dirname(__FILE__).'\Ressources\DB_operation.php');

			$db = new db_operation();

			$resultPass = false;
			$resultMail = false;
			$resultLocal = false;
			$resultDate = false;

			$response['message'] = "";

			if($_SERVER["REQUEST_METHOD"] == "POST")
			{
				if (isset($_POST["Nom"]) && isset($_POST["Password"]) && !empty($_POST["Password"])) 
				{
			        $resultPass = $db->updatePass($_POST['Nom'],$_POST['Password']);
			        if($resultPass)
			        {
			        	$response['message'] ="Mdp mis à jour ";
			        }else{
			        	$response['message'] ="Echec de mis à jour ";
			        }

			       // echo json_encode($response);
	    		}

	    		if (isset($_POST["Nom"]) && isset($_POST["mail"]) && !empty($_POST["mail"])) 
				{
			        $resultMail = $db->updateMail($_POST['Nom'],$_POST['mail']);
			        if($resultMail)
			        {
			        	$response['message'] ="Mail mis à jour ";
			        }else{
			        	$response['message'] ="Echec de mis à jour ";
			        }

			       // echo json_encode($response);
	    		}

	    		if (isset($_POST["Nom"]) && isset($_POST["local"]) && !empty($_POST["local"])) 
				{
			        $resultLocal = $db->updateLocal($_POST['Nom'],$_POST['local']);
			       if($resultLocal)
			        {
			        	$response['message'] ="Localite mise à jour ";
			        }else{
			        	$response['message'] ="Echec de mis à jour ";
			        }

			       // echo json_encode($response);
	    		}

	    		if (isset($_POST["Nom"]) && isset($_POST["ddn"]) && !empty($_POST["ddn"])) 
				{
			        $resultDate = $db->updateDate($_POST['Nom'],$_POST['ddn']);
			        if($resultDate)
			        {
			        	$response['message'] ="Date de naissance mise à jour ";
			        }else{
			        	$response['message'] ="Echec de mis à jour ";
			        }

			       // echo json_encode($response);
	    		}

	    		$response['error'] =!($resultPass&&$resultMail&&$resultLocal&&$resultDate);
	    		$response['status'] =($resultPass||$resultMail||$resultLocal||$resultDate);
			}else{

		                $response = array(
			                    'status' => true,
			                    'error' => false,
			                    'message' => 'L\'utilisateur a ete modifier'
			                );
			}

			echo json_encode($response);
			


		?>