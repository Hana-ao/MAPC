package org.example.tds.revisionsCC1.cours5;

import java.util.HashMap;
import java.util.Map;

public class Icon {
    /*Énoncé : Gestion des icônes d’application

Imagine une application où plusieurs fenêtres utilisent des icônes (images).
 Les icônes sont définies par un type (ex. : Folder, File, Image), mais elles 
 sont partagées entre les fenêtres pour éviter de recréer des objets.

	1.	Crée une classe Icon avec :
	•	Attribut : type (le type de l’icône, comme Folder, File, Image).

	•	Méthode : display(String window) qui affiche l’icône dans une fenêtre donnée.

	2.	Implémente le Flyweight Pattern :
	•	Utilise une Map pour stocker les icônes uniques par type.
	•	Crée une méthode statique getIcon(String type) pour retourner une instance unique d’icône.

	3.	Teste ton code :
	•	Affiche les mêmes icônes dans plusieurs fenêtres.
	•	Vérifie que les références des icônes sont partagées. */

	public enum TypeIcon {
		Folder, File, Image
	}
private TypeIcon type; //T peut prendre plusieurs valeurs ; sinon on peut utiliser String 

//map statique pour stocker les instances
private static final Map<TypeIcon, Icon> icons = new HashMap<>();

//constructeur privé
private Icon(TypeIcon type){
	this.type = type;
}

//getter static
public static Icon getIcon(TypeIcon type){
	if(type == null){
		throw new IllegalArgumentException("Le type d'icône ne peut pas être null !");
	
	}
	//renvoyer l'instance correspondant à l'icone (le type)
	//faire la lazy loading : crée l'icone si elle n'existe pas
	icons.putIfAbsent(type, new Icon(type));
	return icons.get(type);
}
public void display(String window){
	System.out.println("Dans la fenêtre :"+ window + ":" + type);
}

public static void main(String[] args) {
	Icon file1 = Icon.getIcon(TypeIcon.File);
	Icon file2 = Icon.getIcon(TypeIcon.File);
	if(file1 == file2){
		System.out.println("Les références sont les mêmes, tout fonctionne !");
	}else{
		System.out.println("oups");
	}
}
}


