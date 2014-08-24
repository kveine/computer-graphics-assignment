package models;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import skybox.Planet;
import skybox.Stars;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Scene {
	//sets all the classes together and contains the root BranchGroup
	public static void main(String[] args) {
		
		BranchGroup rootBG = new BranchGroup();
		SimpleUniverse universe = new SimpleUniverse();
		universe.getViewingPlatform().setNominalViewingTransform();
		
		Alien alien = new Alien();
		Ufo ufo = new Ufo();
		
		
		//adds all the classes to rootBG. every class is a TransformGroup.
		rootBG.addChild(new Stars());
		rootBG.addChild(new Planet());
		rootBG.addChild(alien);
		rootBG.addChild(new Restaurant());
		rootBG.addChild(new RestaurantSign());
		rootBG.addChild(new ParkingSign());
		rootBG.addChild(new Ship());
		rootBG.addChild(ufo);
		
		//sets up ambient light
		BoundingSphere bounds1 = new BoundingSphere(new Point3d(0.0,0.0,0.0), 10000.0);
		//AmbientLight ambientLight = new AmbientLight(new Color3f(0.6f, 0.6f, 0.6f));
		AmbientLight ambientLight = new AmbientLight(new Color3f(0.9f, 0.9f, 0.9f));
		ambientLight.setInfluencingBounds(bounds1);
		rootBG.addChild(ambientLight);
		
		//sets up one directional light on the background models
		BoundingSphere bounds2 = new BoundingSphere(new Point3d(0,-2,-13), 100.0);
		Color3f directionalLightColor = new Color3f(0.2f, 0.2f, 0.2f);
		Vector3f directionalLightVector = new Vector3f(5f, 10f, -15f);
		DirectionalLight directionalLight = new DirectionalLight(directionalLightColor, directionalLightVector);
		directionalLight.setInfluencingBounds(bounds2);
		rootBG.addChild(directionalLight);

		universe.getCanvas().addKeyListener(new MyKeyListener(alien));
		universe.addBranchGraph(rootBG);

		
		Thread ufoThread = new Thread(ufo);
		ufoThread.start();
		
	}
	

}
