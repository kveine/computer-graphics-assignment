package models;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import util.TextureDistributor;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;

public class Ship extends TransformGroup{
	//makes a spaceship that is parked on the ground
	public Ship(){
		
		Transform3D sphereTransform = new Transform3D();
		Vector3f sphereVector = new Vector3f(3.2f, -0.5f, -10f);
		sphereTransform.setTranslation(sphereVector);
		
		//sets texture
		Appearance ap = TextureDistributor.LoadAppearance("metallicTexture.jpg");
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		addChild(new Sphere(0.36f, primflags,300, ap));
		
		//rotates
		Quat4f myQuat4f = new Quat4f(0, -30, 0, 90);
        sphereTransform.setRotation(myQuat4f);
        setTransform(sphereTransform);
		
        BranchGroup sphereBG = new BranchGroup();
		addChild(sphereBG);
		//adds next model in branch to the sphere BranchGroup
		sphereBG.addChild(cylinder());

	}
	
	private TransformGroup glass(){
		TransformGroup glassTG = new TransformGroup();
		Transform3D glassTransform = new Transform3D();
		glassTransform.setScale(new Vector3d(1.5, 2, 1.5));
		Vector3f glassVector = new Vector3f(0f, 0.4f, 0f);
		glassTransform.setTranslation(glassVector);
		glassTG.setTransform(glassTransform);
		//sets texture
		Appearance ap = TextureDistributor.LoadAppearance("glassTexture.jpg");
		//makes texture transparent
		TransparencyAttributes ta = new TransparencyAttributes();
		ta.setTransparencyMode(ta.NICEST);
		ta.setTransparency (0.5f);
		ap.setTransparencyAttributes(ta);
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		glassTG.addChild(new Sphere(0.35f, primflags,300, ap));
		
		return glassTG;
		
	}
	
	private TransformGroup cylinder(){
		TransformGroup cylinderTG = new TransformGroup();
		Transform3D cylinderTransform = new Transform3D();
		Vector3f cylinderVector = new Vector3f(0f, 0.1f, 0);
		cylinderTransform.setTranslation(cylinderVector);
		cylinderTG.setTransform(cylinderTransform);
		//sets texture
		Appearance ap = TextureDistributor.LoadAppearance("shipTexture2.jpg");
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		cylinderTG.addChild(new Cylinder(0.8f, 0.5f, primflags, ap));
		BranchGroup cylinderBG = new BranchGroup();
		cylinderTG.addChild(cylinderBG);
		//adds next model in branch to the cylinder BranchGroup
		cylinderBG.addChild(glass());
		cylinderBG.addChild(leg(0.6f, -0.5f, 0f, true, false));
		cylinderBG.addChild(leg(-0.6f, -0.5f, 0f, false, false));
		cylinderBG.addChild(leg(0f, -0.5f, 0.6f, true, true));
		cylinderBG.addChild(leg(0f, -0.5f, -0.6f, false, true));
		return cylinderTG;
	}
	
	private TransformGroup leg(float x, float y, float z, boolean right, boolean backOrFront){
		TransformGroup legTG = new TransformGroup();
		Transform3D legTransform = new Transform3D();
		Vector3f legVector = new Vector3f(x, y, z);
		legTransform.setTranslation(legVector);
		legTG.addChild(new Cylinder(0.03f, 0.6f));
		
		//uses if and boolean values to decide which rotation to use
		if(right && !backOrFront){
	    	Quat4f myQuat4f = new Quat4f(0, 0, 20, 90);
	        legTransform.setRotation(myQuat4f);
	    } 
	    else if(!right && !backOrFront){
	    	Quat4f myQuat4f = new Quat4f(0, 0, -20, 90);
	        legTransform.setRotation(myQuat4f);
	    }
	    else if(right && backOrFront){
	    	Quat4f myQuat4f = new Quat4f(-20, 0, 0, 90);
	        legTransform.setRotation(myQuat4f);
	    }
	    else if(!right && backOrFront){
	    	Quat4f myQuat4f = new Quat4f(20, 0, 0, 90);
	        legTransform.setRotation(myQuat4f);
	    }
		
        legTG.setTransform(legTransform);
        BranchGroup legBG = new BranchGroup();
        legTG.addChild(legBG);
        //adds next model in branch to the leg BranchGroup
        legBG.addChild(foot(0f, -0.3f, 0f));
        
		return legTG;

	}
	
	private TransformGroup foot(float x, float y, float z){
		TransformGroup footTG = new TransformGroup();
		Transform3D footTransform = new Transform3D();
		Vector3f footVector = new Vector3f(x, y, z);
		footTransform.setTranslation(footVector);
		footTG.setTransform(footTransform);
		//sets texture
		Appearance ap = TextureDistributor.LoadAppearance("shoeTexture.jpg");
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		footTG.addChild(new Sphere(0.08f, primflags, 300, ap));
		
		return footTG;
	}
}
