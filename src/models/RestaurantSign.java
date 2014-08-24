package models;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import util.TextureDistributor;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;

public class RestaurantSign extends TransformGroup{
	//makes a sign for the restaurant, with the restaurants logo
	public RestaurantSign(){
		
		Transform3D cylinderTransform = new Transform3D();
	    Vector3f cylinderVector = new Vector3f(-2.1f, 0f, -4f);
	    cylinderTransform.setTranslation(cylinderVector);
	    
	    //sets texture
	    Appearance ap = TextureDistributor.LoadAppearance("metallicTexture.jpg");
	    int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
	    
	    //rotates
	    Quat4f myQuat4f = new Quat4f(0, 20, 0, 90);
        cylinderTransform.setRotation(myQuat4f);
	   
        addChild(new Cylinder(0.05f, 2.3f, primflags, ap));
	    setTransform(cylinderTransform);
	    
	    BranchGroup cylinderBG = new BranchGroup();
	    addChild(cylinderBG);
	    //adds next structure in Branch to the cylinder BranchGroup
	    cylinderBG.addChild(box());
	    
	}
	
	private TransformGroup box(){
		TransformGroup boxTG = new TransformGroup();
		Transform3D boxTransform = new Transform3D();
		Vector3f boxVector = new Vector3f(0f, 1.2f, 0f);
		boxTransform.setTranslation(boxVector);
		boxTG.setTransform(boxTransform);
		//sets texture
		Appearance ap = TextureDistributor.LoadAppearance("boxTexture.jpg");
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		Box signBox = new Box(0.5f, 0.3f, 0.2f, primflags, ap);
		Appearance logo = TextureDistributor.LoadAppearance("themilkyways2.png");
		//sets different texture in front and back of box. This is the diner logo
		signBox.getShape(Box.FRONT).setAppearance(logo);
		signBox.getShape(Box.BACK).setAppearance(logo);
		boxTG.addChild(signBox);
		
		return boxTG;
		
	}
	
}
