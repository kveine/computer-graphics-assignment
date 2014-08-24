package models;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import util.TextureDistributor;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;

public class Ufo extends TransformGroup implements Runnable{
	//makes a UFO that flies and park
	public Ufo(){
		
		setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		Transform3D ufoTransform = new Transform3D();
		//scales the sphere to make it look flat
		ufoTransform.setScale(new Vector3d(1.5,0.3,1.5));
		Vector3f ufoVector = new Vector3f(-9f, 4f, -13f);
		ufoTransform.setTranslation(ufoVector);
		
		//sets texture
		Appearance ap = TextureDistributor.LoadAppearance("shipTexture.jpg");
	    int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
	    addChild(new Sphere(0.6f, primflags, 300, ap));
        setTransform(ufoTransform);
        
        BranchGroup shipBG = new BranchGroup();
        addChild(shipBG);
        //adds next model in branch to ship BranchGroup
        shipBG.addChild(glass());
	}
	
	public void move(){
		
		Transform3D ufoTransform = new Transform3D();
		
		try{
	    	for(int i = 0; i < 90; i++){
	    		ufoTransform.setScale(new Vector3d(1.5,0.3,1.5));
	    		ufoTransform.setTranslation(new Vector3f(-9f + (0.1f*i), 4f- (0.01f*i), -13f));
		 	
	    		setTransform(ufoTransform);
	    		Thread.sleep(60);
	    	}
		} catch (InterruptedException e){
			return;
		}
	    
		try{
	    	for(int i = 0; i < 43; i++){
	    		ufoTransform.setScale(new Vector3d(1.5,0.3,1.5));
	    		ufoTransform.setTranslation(new Vector3f(0f + (0.1f*i), 3.1f - (0.1f*i), -13f));
		 	
	    		setTransform(ufoTransform);
	    		Thread.sleep(80);
	    	}
		} catch (InterruptedException e){
			return;
		}
	
	}
	
	private TransformGroup glass(){
		TransformGroup glassTG = new TransformGroup();
		Transform3D glassTransform = new Transform3D();
		//scale the sphere
		glassTransform.setScale(new Vector3d(0.5, 1, 0.5));
		Vector3f glassVector = new Vector3f(0f, 0.6f, 0f);
		glassTransform.setTranslation(glassVector);
		//sets texture
		Appearance ap = TextureDistributor.LoadAppearance("glassTexture.jpg");
	    int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
	    glassTG.addChild(new Sphere(0.6f, primflags, 300, ap));
        glassTG.setTransform(glassTransform);
        
		return glassTG;
	}

	@Override
	public void run() {
		move();
		
	}


}
