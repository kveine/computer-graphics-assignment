package models;

import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColorInterpolator;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import util.TextureDistributor;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;

public class Restaurant extends TransformGroup{
	//makes a restaurant called the Milky ways
	
	float startColor1;
	float startColor2;
	float startColor3;
	float endColor1;
	float endColor2;
	float endColor3;
	
	public Restaurant(){
		
		Transform3D restaurantTransform = new Transform3D();
		Vector3f restaurantVector = new Vector3f(-2.0f, 0.0f, -9f);
		restaurantTransform.setTranslation(restaurantVector);
		//sets texture
		Appearance ap = TextureDistributor.LoadAppearance("restaurantTexture.jpg");
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		//rotates
		Quat4f myQuat4f = new Quat4f(0, 25, 0, 90);
        restaurantTransform.setRotation(myQuat4f);
        setTransform(restaurantTransform);
        Box restaurante = new Box(1.9f, 1.2f, 1.3f, primflags, ap);
        restaurante.setCollidable(true);
        addChild(restaurante); //1.3f, 0.9f, 1.3f
        
        BranchGroup restaurantBG = new BranchGroup();
        addChild(restaurantBG);
        //adds next models in branch to the restaurant BranchGroup
        restaurantBG.addChild(curtain());
		restaurantBG.addChild(door());
		restaurantBG.addChild(window());
		
	}
	
	private TransformGroup bulb(float x, float y, float z, float startColor1, float startColor2, float startColor3, 
			float endColor1, float endColor2, float endColor3){
		
		this.startColor1 = startColor1;
		this.startColor2 = startColor2;
		this.startColor3 = startColor3;
		this.endColor1 = endColor1;
		this.endColor2 = endColor2;
		this.endColor3 = endColor3;
		
		TransformGroup bulbTG = new TransformGroup();
		bulbTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D bulbTransform = new Transform3D();
		bulbTransform.setScale(new Vector3d(0.5f, 1f, 0.5f));
	    Vector3f bulbVector = new Vector3f(x, y, z);
	 	bulbTransform.setTranslation(bulbVector);
	    bulbTG.setTransform(bulbTransform);
	    
	    Appearance colorAppearance = new Appearance();
	    
	    
	    Material myMat = new Material();
	    myMat.setDiffuseColor(1f, 1f, 1f);
	    myMat.setCapability(Material.ALLOW_COMPONENT_WRITE);
	    colorAppearance.setMaterial(myMat);
	    
	    TransformGroup changeColorTG = changeColor(myMat);
	    
	    changeColorTG.addChild(new Sphere(0.095f, colorAppearance));
	    
	    BranchGroup bulbBG = new BranchGroup();
	    bulbBG.addChild(changeColorTG);
	    bulbTG.addChild(bulbBG);
	    
	    return bulbTG;
	
		
	}
	
	private TransformGroup changeColor(Material myMatToApply){
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 10000.0);
		TransformGroup temp = new TransformGroup();
	 	temp.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    
	 	Color3f startColor = new Color3f(startColor1, startColor2, startColor3);
	 	Color3f endColor = new Color3f(endColor1, endColor2, endColor3);
	 	Alpha colorSetup = new Alpha(-1, Alpha.INCREASING_ENABLE, 0, 0, 400, 0, 100, 0, 0, 0);
		ColorInterpolator colorChange = new ColorInterpolator(colorSetup, myMatToApply, startColor, endColor);
		
		colorChange.setSchedulingBounds(bounds);
		temp.addChild(colorChange);
	
		return temp;
	}
	
	
	private TransformGroup door(){
		TransformGroup doorTG = new TransformGroup();
		Transform3D doorTransform = new Transform3D();
		Vector3f doorVector = new Vector3f(0.3f, -0.1f, 1.3f);
		doorTransform.setTranslation(doorVector);
		doorTG.setTransform(doorTransform);
		//sets texture
		Appearance ap = TextureDistributor.LoadAppearance("doorTexture2.jpg");
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		doorTG.addChild(new Box(0.7f, 1.2f, 0.001f, primflags, ap));
		BranchGroup doorBG = new BranchGroup();
		doorTG.addChild(doorBG);
		//adds next models in branch to the door BranchGroup
		doorBG.addChild(steps(0f, -1.01f, 0.2f, 0.7f, 0.15f, 0.2f));
		doorBG.addChild(steps(0f, -1.05f, 0.6f, 0.7f, 0.1f, 0.2f));
		doorBG.addChild(steps(0f, -1.1f, 1f, 0.7f, 0.05f, 0.2f));
		
		return doorTG;
	}
	
	private TransformGroup steps(float x, float y, float z, float w, float h, float l){
		TransformGroup stepsTG = new TransformGroup();
		Transform3D stepsTransform = new Transform3D();
		Vector3f stepsVector = new Vector3f(x, y, z);
		stepsTransform.setTranslation(stepsVector);
		stepsTG.setTransform(stepsTransform);
		//sets texture
		Appearance ap = TextureDistributor.LoadAppearance("metallicTexture.jpg");
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		stepsTG.addChild(new Box(w, h, l, primflags, ap));
		
		return stepsTG;
	}
	
	private TransformGroup window(){
		TransformGroup windowTG = new TransformGroup();
		Transform3D windowTransform = new Transform3D();
		Vector3f windowVector = new Vector3f(-1.1f, 0.2f, 1.3f);
		windowTransform.setTranslation(windowVector);
		windowTG.setTransform(windowTransform);
		//sets texture
		Appearance ap = TextureDistributor.LoadAppearance("windowTexture.jpg");
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		windowTG.addChild(new Box(0.5f, 0.55f, 0.001f, primflags, ap));
		
		return windowTG;
	}
	
	private TransformGroup curtain(){
		TransformGroup curtainTG = new TransformGroup();
		Transform3D curtainTransform = new Transform3D();
		Vector3f curtainVector = new Vector3f(0f, 1.4f, 0f);
		curtainTransform.setTranslation(curtainVector);
		curtainTG.setTransform(curtainTransform);
		//sets texture
		Appearance ap = TextureDistributor.LoadAppearance("curtainTexture.jpg");
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		curtainTG.addChild(new Box(2.1f, 0.2f, 1.5f, primflags, ap));
		BranchGroup curtainBG = new BranchGroup();
		curtainTG.addChild(curtainBG);
		//adds next model in branch to the curtain BranchGroup
		curtainBG.addChild(roof());
		
		return curtainTG;
	}
	
	private TransformGroup roof(){
		TransformGroup roofTG = new TransformGroup();
		Transform3D roofTransform = new Transform3D();
		Vector3f roofVector = new Vector3f(0f, 0.3f, 0f);
		roofTransform.setTranslation(roofVector);
		roofTG.setTransform(roofTransform);
		//sets texture
		Appearance ap = TextureDistributor.LoadAppearance("woodTexture.jpg");
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		Box roofBox = new Box(2.3f, 0.2f, 1.6f, primflags, ap);
		Appearance wire = TextureDistributor.LoadAppearance("woodWireTexture.jpg");
		roofBox.getShape(Box.FRONT).setAppearance(wire);
		roofTG.addChild(roofBox);
		BranchGroup roofBG = new BranchGroup();
		//adds light bulbs
		roofBG.addChild(bulb(-1.2f, -0.07f, 1.67f, 0.2f, 0.2f, 1.0f, 1.0f, 1.0f, 0.0f));
		roofBG.addChild(bulb(-0.6f, -0.14f, 1.67f, 1.0f, 1.0f, 0.0f, 0.2f, 1f, 0.3f));
		roofBG.addChild(bulb(0.2f, -0.15f, 1.67f, 0.2f, 1f, 0.3f, 1f, 0.2f, 0.3f));
		roofBG.addChild(bulb(1f, -0.09f, 1.67f, 1f, 0.2f, 0.3f, 0.2f, 0.2f, 1.0f));
		roofBG.addChild(bulb(1.9f, -0.07f, 1.67f, 0.2f, 0.2f, 1.0f, 1.0f, 1.0f, 0.0f));
		
		roofTG.addChild(roofBG);
		
		return roofTG;
	}
}
