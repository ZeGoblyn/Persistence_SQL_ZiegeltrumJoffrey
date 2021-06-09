<?php

			//CE FICHIER EST UN WEB SERVICE

			//include '../DB_Connect.php';
			include_once (dirname(__FILE__).'\Ressources\DB_operation.php');

			//$testVar = new AndroidRepert();
			//$DB = $testVar->Connect();
			$db = new db_operation();


			echo dirname(__FILE__);

			if ($_SERVER["REQUEST_METHOD"]== "GET")
			{
				if (isset($_GET["NomLog"]))
				{
					if($db->GetUserByUsername($_GET['NomLog']))
					{
						$response = array(
			                    'status' => true,
			                    'error' => false,
			                    'message' => 'L\'utilisateur n\'existe pas');
					} else{
						$response = array(
			                    'status' => true,
			                    'error' => false,
			                    'message' => 'L\'utilisateur existe');
					Echo json_encode($response);
					}
				}
			}

			



		

		?>