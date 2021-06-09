<!DOCTYPE html> <!-- Ceci est un modèle de base, penser à faire une copie avant de faire des modifications -->
<html>
	<head>
		<meta charset="utf-8"/>
		<link rel="icon" href="">
		<script type="text/javascript" src="">
			
		</script>
		<title>
			TestAndroid PHP
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
				<p><input type="submit" name="Valider" value="Valider" onclick='//soumettre();' action="#" method="get" ></p>
			</fieldset>
		</form>

		<?php
			include 'DB_Connect.php';

			$testVar = new AndroidRepert();
			$DB = $testVar->Connect();

			echo dirname(__FILE__);

			//createUser($_GET['Nom'], $_GET['Password'], $_GET['mail'], $testVar->Connect(););

			function createUser($username, $password, $email, $connDB)
			{

				try
		        {
		            //$stmt = $testVar->prepare("INSERT INTO USERS (Username, Password, Email) VALUES (?, ?, ?)");
		            $stmt = $connDB->prepare("INSERT INTO USERS (Username, Password, Email) VALUES (?, ?, ?)");
		        }
		        catch (Exception $e)
		        {
		              echo "Erreur préparation : ".$e->getMessage();
		           }

		        $stmt->bind_param("sss", $username, $password, $email);

		       /* if(userExist($username, $email))
		        {
		        	$stmt->execute();
		        	echo "JaaJ";
		        	return 1;
		        }else{
		        	if()
		        }*/


		        if($stmt->execute())
		        {
		        	echo "JaaJ";
		        	return 1;
		        }else{
		        	echo "Ne-Nein";
		        	return 2;
		        }
			}

			if (isset($_GET["Nom"]) && isset($_GET["Password"]) && isset($_GET["mail"]))
			{
				createUser($_GET['Nom'], $_GET['Password'], $_GET['mail'], $DB);
			}

			//createUser($_GET['Nom'], $_GET['Password'], $_GET['mail'], $DB);

			if (isset($_GET["NomLog"]) && isset($_GET["PasswordLog"]))
				{
					if(UserLogin($_GET['NomLog'], $_GET['PasswordLog'], $DB))
				{
					echo "Utilisateur trouver";
				}else{
					echo "Utilisateur NON trouver";
				}
			}

			/*if(UserLogin($_GET['NomLog'], $_GET['PasswordLog'], $DB))
			{
				echo "Utilisateur trouver";
			}else{
				echo "Utilisateur NON trouver";
			}*/

			if (isset($_GET["NomLog"]))
			{
				GetUserByUsername($_GET['NomLog'], $DB);
			}

			//GetUserByUsername($_GET['NomLog'], $DB);

			
			function UserLogin($username, $pass, $DB)
			{
			    $req = "SELECT * FROM USERS where Username = ? AND Password = ?;";
			    $password = $pass;

			    //Envoie de la requête à la base
			    try
			    {
			        $stmt = $DB->prepare($req);

			        $stmt->bind_param("ss", $username, $password);

			        $stmt->execute();
			        $stmt->store_result();
			        return $stmt->num_rows() > 0;

			    }
			    catch(PDOException $error)
			    {
			        echo "<script>console.log('".$error->getMessage()."')</script>";
			        exit();
			    }
			}

			function GetUserByUsername($username, $DB)
			{
			    $req = "SELECT * FROM USERS where Username = ?;";

			    //Envoie de la requête à la base
			    try
			    {
			        $stmt = $DB->prepare($req);

			        $stmt->bind_param("s", $username);

			        $stmt->execute();
			        $array = $stmt->get_result()->fetch_assoc();

			        echo "<br>";
			        foreach ($array as $key => $value)
			        {
			            echo $key." : ";
			            echo $value."<br>";
			        }

			        return 1;
			    }
			    catch(PDOException $error)
			    {
			        echo "<script>console.log('".$error->getMessage()."')</script>";
			        exit();
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