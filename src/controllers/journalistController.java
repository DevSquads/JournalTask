package controllers;

import java.util.Vector;

import models.Article;
import models.Journalist;
import models.Role;
import models.RoleName;

public class journalistController {
	
	public boolean checkPermission(RoleName role, Journalist author) {
		Vector<Role> authorPermissions = author.getPermissions();
		for(int i=0; i< authorPermissions.size(); i++)
			if(authorPermissions.elementAt(i).name == role) 
				return true;
		return false;		
	}
	
	public void addArticle(Journalist author, Article newArticle)
	{
		author.addArticle(newArticle);
	}
	
}
