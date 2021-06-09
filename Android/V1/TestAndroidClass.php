<!DOCTYPE html> <!-- Ceci est un modèle de base, penser à faire une copie avant de faire des modifications -->
<html>
	<head>
		<meta charset="utf-8"/>
		<link rel="icon" href="">
		<script type="text/javascript" src="">
			
		</script>
		<title>
			TestAndroidCLASS PHP
		</title>
	</head>

	<body>

		<h1>Connection BDD Android</h1>

		<form name="form" action="#" method="get" id="form">
			<fieldset>
				<p><input type="texte" name="Nom" placeholder="Nom" required="" maxlength="42" id="Nom"></p>
				<br>
				<p><input type="texte" name="Password" placeholder="Password" required="" maxlength="25" id="Password"></p>
				<br>
				<p><input type="texte" name="mail" placeholder="mail" required="" maxlength="30" id="mail"></p>
				<br>
				<br>	
				<p><input type="submit" name="Valider" value="Valider" onclick='//soumettre();' action="#" method="get" ></p>
			</fieldset>
			<br>
			<br>
		</form>
		<form name="formLog" action="#" method="get" id="formLog">
			<fieldset>
				<p><input type="texte" name="NomLog" placeholder="NomLog" maxlength="42" id="NomLog"></p>
				<br>
				<p><input type="texte" name="PasswordLog" placeholder="PasswordLog" maxlength="25" id="PasswordLog"></p>
				<br>	
				<p><input type="submit" name="ValiderLog" value="ValiderLog" onclick='//soumettre();' action="#" method="get" ></p>
			</fieldset>
		</form>

		<?php
			//include 'DB_Connect.php';
			include_once (dirname(__FILE__).'\Ressources\DB_operation.php');


			//$testVar = new AndroidRepert();
			//$DB = $testVar->Connect();
			$db = new db_operation();


			echo dirname(__FILE__);

			if (isset($_GET["Nom"]) && isset($_GET["Password"]) && isset($_GET["mail"]))
			{
				$db->createUser($_GET['Nom'], $_GET['Password'], $_GET['mail']);
			}



			if (isset($_GET["NomLog"]) && isset($_GET["PasswordLog"]))
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
			}

			/*if(isset($_GET['Valider'])) 
			{
		        $create = $db->createUser($_GET['Nom'],$_GET['Password'],$_GET['mail']);
		        switch($create) {
		            case 0:
		                $reponse = array(
		                    'status' => false,
		                    'error' => true,
		                    'message' => 'Ce nom d\'utilisateur ou cette adresse mail sont deja utilises'
		                );
		                break;
		            case 1:
		                $reponse = array(
		                    'status' => true,
		                    'error' => false,
		                    'message' => 'L\'utilisateur a ete cree'
		                );
		                break;
		            case 2:
		                $reponse = array(
		                    'status' => false,
		                    'error' => true,
		                    'message' => 'Erreur d\'insertion, veuillez reesayer.'
		                );
		                break;
		        }
    		}*/

			

		if(isset($reponse)) 
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
		}

		?>

		<script type="text/javascript">
			function soumettre()
			{
					var nom=document.getElementById("Nom").value;
					var password=document.getElementById("Password").value;
					var mail=document.getElementById("mail").value;

					console.log(nom);
					console.log(prenom);
					console.log(password);

					if(nom=="")
					{
						alert("Donnez votre nom");
					}

					if(password=="")
					{
						alert("Donnez un mot de passe");
					}

					if(mail=="")
					{
						alert("Donnez un Email");
					}

					if((nom!="") && (prenom!="") && (mail!=""))
					{
						alert("Formulaire valide");
	                    alert("isOk");
	                    document.getElementById("form").submit();

	                    //createUser(nom, password, mail);

					}
			}
		</script>

		<footer>
			<br>
			<p>Nous somme le :</p>
			<?php
				echo date('d/m/Y h:i:s'); /*fonction date permettant d'afficher la date (si on veut rafraichir autom : 'setInterval'*/
			?>
		</footer>

	</body>
</html>