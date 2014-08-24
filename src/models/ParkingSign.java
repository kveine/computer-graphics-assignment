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

public class ParkingSign extends TransformGroup{
	//makes a parking sign which is placed in the right back
	public ParkingSign(){
		
		Transform3D cylinderTransform = new Transform3D();
	    Vector3f cylinderVector = new Vector3f(3.3f, -0.3f, -12f);
	    cylinderTransform.setTranslation(cylinderVector);
	    //sets texture 
	    Appearance ap = TextureDistributor.LoadAppearance("metallicTexture.jpg");
	    int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
	    
	    //rotate
	    Quat4f myQuat4f = new Quat4f(0, -20, 0, 90);
        cylinderTransform.setRotation(myQuat4f);
	    addChild(new Cylinder(0.03f, 1.8f, primflags, ap));
	    setTransform(cylinderTransform);
	    
	    BranchGroup cylinderBG = new BranchGroup();
	    addChild(cylinderBG);
	    //adds next model in branch to cylinder BranchGroup
	    cylinderBG.addChild(signBox());
	}
	
	private TransformGroup signBox(){
		TransformGroup signBoxTG = new TransformGroup();
		Transform3D signBoxTransform = new Transform3D();
		Vector3f signBoxVector = new Vector3f(0f, 0.8f, 0f);
		signBoxTransform.setTranslation(signBoxVector);
		signBoxTG.setTransform(signBoxTransform);
		//sets texture
		Appearance ap = TextureDistributor.LoadAppearance("shoeTexture.jpg");
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		Appearance sign = TextureDistributor.LoadAppearance("parkingSignTexture.jpg");
		Box signBox = new Box(0.2f, 0.3f, 0.05f, primflags, ap);
		//sets different texture on front and back of box
		signBox.getShape(Box.FRONT).setAppearance(sign);
		signBox.getShape(Box.BACK).setAppearance(sign);
		signBoxTG.addChild(signBox);
		
		return signBoxTG;
	}

}
